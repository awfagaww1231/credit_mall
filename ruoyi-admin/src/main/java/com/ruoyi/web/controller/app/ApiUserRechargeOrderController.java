package com.ruoyi.web.controller.app;


import com.ruoyi.common.annotation.RepeatSubmit;
import com.ruoyi.common.constant.HttpStatus;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.customer.common.AppletTokenService;
import com.ruoyi.customer.domain.ApiUserRechargeOrder;
import com.ruoyi.customer.domain.AppletLoginUser;
import com.ruoyi.customer.service.IApiUserRechargeOrderService;
import com.ruoyi.system.domain.PlatformPaymentChannel;
import com.ruoyi.system.service.IPlatformPaymentChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * App用户充值订单Controller +
 *
 * @author ruoyi
 * @date 2022-11-08
 */
@RestController
@RequestMapping("/api/userRechargeOrder")
public class ApiUserRechargeOrderController extends BaseController {

    @Autowired
    private IApiUserRechargeOrderService apiUserRechargeOrderService;

    @Autowired
    private IPlatformPaymentChannelService platformPaymentChannelService;

    @Autowired
    private AppletTokenService appletTokenService;


    /**
     * 查询用户充值订单列表
     */
    @GetMapping("/userRechargeOrderList")
    public AjaxResult list(ApiUserRechargeOrder apiUserRechargeOrder)
    {
        AppletLoginUser loginUser = appletTokenService.getLoginUser ();
        if(StringUtils.isNull(loginUser)){
            return AjaxResult.error(HttpStatus.UNAUTHORIZED,"登录信息失效");
        }else {
            appletTokenService.refreshToken(loginUser);
        }
        startPage();
        apiUserRechargeOrder.setUserId(loginUser.getApiUserInfo().getId());
        List<ApiUserRechargeOrder> list = apiUserRechargeOrderService.selectUserRechargeOrderList(apiUserRechargeOrder);
        return AjaxResult.success(getDataTable(list));
    }


    /**
     * 新增用户充值订单
     */
    @PostMapping("/addUserRechargeOrder")
    @RepeatSubmit
    public AjaxResult addUserRechargeOrder(@RequestBody ApiUserRechargeOrder apiUserRechargeOrder)
    {
        AppletLoginUser loginUser = appletTokenService.getLoginUser ();
        if(StringUtils.isNull(loginUser)){
            return AjaxResult.error(HttpStatus.UNAUTHORIZED,"登录信息失效");
        }else {
            appletTokenService.refreshToken(loginUser);
        }
        if (StringUtils.isNull(apiUserRechargeOrder.getPlatformPaymentChannelId())){
            return AjaxResult.error("请选择支付通道","hint_60");
        }
        if (StringUtils.isNull(apiUserRechargeOrder.getAmount())){
            return AjaxResult.error("请输入要充值的金额","hint_61");
        }

        apiUserRechargeOrder.setUserId(loginUser.getApiUserInfo().getId());
        //订单号
        String orderCode =  String.valueOf(System.currentTimeMillis()) + String.valueOf(loginUser.getApiUserInfo().getId());
        apiUserRechargeOrder.setOrderCode(orderCode);
        Long platformPaymentChannelId = apiUserRechargeOrder.getPlatformPaymentChannelId();
        PlatformPaymentChannel platformPaymentChannel = platformPaymentChannelService.selectPlatformPaymentChannelById(platformPaymentChannelId);
        if (StringUtils.isNull(platformPaymentChannel)){
            return AjaxResult.error("获取支付通道信息异常","hint_62");
        }
        //支付通道名称
        String channelName = platformPaymentChannel.getChannelName();
        //支付通道账户
        String channelAccount = platformPaymentChannel.getChannelAccount();
        apiUserRechargeOrder.setRechargeUrl(channelName+"/"+channelAccount);
        apiUserRechargeOrder.setPayType(platformPaymentChannel.getPayType());
        int result = apiUserRechargeOrderService.insertUserRechargeOrder(apiUserRechargeOrder);
        if (result == 0){
            return AjaxResult.error();
        }
        return AjaxResult.success().put("apiUserRechargeOrder",apiUserRechargeOrder);
    }


    /**
     * 去支付充值订单
     */
    @PostMapping("/toPayRechargeOrder")
    @RepeatSubmit
    public AjaxResult toPayRechargeOrder(@RequestBody ApiUserRechargeOrder apiUserRechargeOrder)
    {
        AppletLoginUser loginUser = appletTokenService.getLoginUser ();
        if(StringUtils.isNull(loginUser)){
            return AjaxResult.error(HttpStatus.UNAUTHORIZED,"登录信息失效");
        }else {
            appletTokenService.refreshToken(loginUser);
        }
        //验证订单信息是否缺失
        if (StringUtils.isEmpty(apiUserRechargeOrder.getImgUrl())){
            return AjaxResult.error("请先上传充值凭证","hint_63");
        }
        if (StringUtils.isNull(apiUserRechargeOrder.getUserId())){
            return AjaxResult.error("获取订单信息异常，请刷新后尝试","hint_50");
        }
        ApiUserRechargeOrder apiUserRechargeOrderVo = apiUserRechargeOrderService.selectUserRechargeOrderById(apiUserRechargeOrder.getId());
        if (StringUtils.isNull(apiUserRechargeOrderVo)){
            return AjaxResult.error("获取订单信息异常，请刷新后尝试","hint_50");
        }
        if (StringUtils.isNull(apiUserRechargeOrderVo.getUserId())){
            return AjaxResult.error("获取订单信息异常，请刷新后尝试","hint_50");
        }
        //验证是否本人在操作
        if (!apiUserRechargeOrderVo.getUserId().equals(apiUserRechargeOrder.getUserId())){
            return AjaxResult.error("登录信息异常，请重新登录后尝试","hint_65");
        }
        if (!loginUser.getApiUserInfo().getId().equals(apiUserRechargeOrder.getUserId())){
            return AjaxResult.error("登录信息异常，请重新登录后尝试","hint_65");
        }
        if (!apiUserRechargeOrderVo.getUserId().equals(loginUser.getApiUserInfo().getId())){
            return AjaxResult.error("登录信息异常，请重新登录后尝试","hint_65");
        }

        if (apiUserRechargeOrderVo.getOrderStatus() != 0){
            return AjaxResult.error("此订单已经支付过!","hint_66");
        }

        apiUserRechargeOrder.setOrderStatus(1);
        return toAjax(apiUserRechargeOrderService.updateUserRechargeOrder(apiUserRechargeOrder));
    }

    /**
     * 删除用户充值订单
     */
    @GetMapping("/deleteRechargeOrder")
    public AjaxResult deleteRechargeOrder(Long id)
    {
        return toAjax(apiUserRechargeOrderService.deleteUserRechargeOrderById(id));
    }
}
