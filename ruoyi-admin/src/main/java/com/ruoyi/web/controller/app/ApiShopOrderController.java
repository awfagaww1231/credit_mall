package com.ruoyi.web.controller.app;


import com.ruoyi.common.annotation.RepeatSubmit;
import com.ruoyi.common.constant.HttpStatus;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.customer.common.AppletTokenService;
import com.ruoyi.customer.domain.ApiShopOrder;
import com.ruoyi.customer.domain.AppletLoginUser;
import com.ruoyi.customer.service.IApiShopOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * APP商城商品订单信息Controller +
 *
 * @author ruoyi
 * @date 2022-10-27
 */
@RestController
@RequestMapping("/api/shopOrder")
public class ApiShopOrderController extends BaseController {

    @Autowired
    private IApiShopOrderService apiShopOrderService;

    @Autowired
    private AppletTokenService appletTokenService;


    /**
     * 新增店铺商品订单
     */
    @PostMapping("/insertShopOrder")
    @RepeatSubmit
    public AjaxResult insertShopOrder(@RequestBody List<ApiShopOrder> apiShopOrders) throws InterruptedException {
        AppletLoginUser loginUser = appletTokenService.getLoginUser ();
        if(StringUtils.isNull(loginUser)){
            return AjaxResult.error(HttpStatus.UNAUTHORIZED,"登录信息失效");
        }else {
            appletTokenService.refreshToken(loginUser);
        }
        if (apiShopOrders.size() == 0){
            return AjaxResult.error("至少选择一笔订单结算","hint_37");
        }
        return apiShopOrderService.insertShopOrder(apiShopOrders,loginUser.getApiUserInfo().getId());
    }

    /**
     * 去结算店铺商品订单
     */
    @PostMapping("/toPayShopOrder")
    @RepeatSubmit
    public AjaxResult toPayShopOrder(@RequestBody List<ApiShopOrder> apiShopOrders)  {
        AppletLoginUser loginUser = appletTokenService.getLoginUser ();
        if(StringUtils.isNull(loginUser)){
            return AjaxResult.error(HttpStatus.UNAUTHORIZED,"登录信息失效");
        }else {
            appletTokenService.refreshToken(loginUser);
        }
        return apiShopOrderService.toPayShopOrder(apiShopOrders,loginUser.getApiUserInfo().getId());
    }

    /**
     * 查看我的订单
     */
    @GetMapping("/myShopOrder")
    public AjaxResult myShopOrder(ApiShopOrder apiShopOrder)
    {
        AppletLoginUser loginUser = appletTokenService.getLoginUser ();
        if(StringUtils.isNull(loginUser)){
            return AjaxResult.error(HttpStatus.UNAUTHORIZED,"登录信息失效");
        }else {
            appletTokenService.refreshToken(loginUser);
        }
        startPage();
        apiShopOrder.setUserId(loginUser.getApiUserInfo().getId());
        List<ApiShopOrder> apiShopOrders = apiShopOrderService.myShopOrder(apiShopOrder);
        return AjaxResult.success(getDataTable(apiShopOrders));
    }

    /**
     * 确认收货
     */
    @GetMapping("/toReceipt")
    @RepeatSubmit
    public AjaxResult toReceipt(Long id)
    {
        AppletLoginUser loginUser = appletTokenService.getLoginUser ();
        if(StringUtils.isNull(loginUser)){
            return AjaxResult.error(HttpStatus.UNAUTHORIZED,"登录信息失效");
        }else {
            appletTokenService.refreshToken(loginUser);
        }
        return apiShopOrderService.toReceipt(id,loginUser.getApiUserInfo().getId());
    }

    /**
     * 删除店铺订单
     */
    @PostMapping("/deleteShopOrderByIds")
    @RepeatSubmit
    public AjaxResult deleteShopOrderByIds(@RequestBody List<Long> ids)
    {
        AppletLoginUser loginUser = appletTokenService.getLoginUser ();
        if(StringUtils.isNull(loginUser)){
            return AjaxResult.error(HttpStatus.UNAUTHORIZED,"登录信息失效");
        }else {
            appletTokenService.refreshToken(loginUser);
        }
        return apiShopOrderService.deleteShopOrderByIds(ids);
    }


}
