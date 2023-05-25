package com.ruoyi.web.controller.app;

import com.ruoyi.common.annotation.RepeatSubmit;
import com.ruoyi.common.constant.HttpStatus;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.customer.common.AppletTokenService;
import com.ruoyi.customer.domain.ApiUserReceiveAddress;
import com.ruoyi.customer.domain.AppletLoginUser;
import com.ruoyi.customer.service.IApiUserReceiveAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户收货地址信息Controller
 * 
 * @author ruoyi
 * @date 2022-11-10
 */
@RestController
@RequestMapping("/api/userReceiveAddress")
public class ApiUserReceiveAddressController extends BaseController
{
    @Autowired
    private IApiUserReceiveAddressService apiUserReceiveAddressService;

    @Autowired
    private AppletTokenService appletTokenService;
    /**
     * 查询用户收货地址信息列表
     */
    @GetMapping("/userReceiveAddressList")
    public AjaxResult list(ApiUserReceiveAddress userReceiveAddress)
    {
        AppletLoginUser loginUser = appletTokenService.getLoginUser ();
        if(StringUtils.isNull(loginUser)){
            return AjaxResult.error(HttpStatus.UNAUTHORIZED,"登录信息失效");
        }else {
            appletTokenService.refreshToken(loginUser);
        }
        userReceiveAddress.setUserId(loginUser.getApiUserInfo().getId());
        startPage();
        List<ApiUserReceiveAddress> list = apiUserReceiveAddressService.selectUserReceiveAddressList(userReceiveAddress);
        return AjaxResult.success(getDataTable(list));
    }

//    /**
//     * 获取用户收货地址信息详细信息
//     */
//    @GetMapping(value = "/{id}")
//    public AjaxResult getInfo(@PathVariable("id") Long id)
//    {
//        AppletLoginUser loginUser = appletTokenService.getLoginUser ();
//        if(StringUtils.isNull(loginUser)){
//            return AjaxResult.error(HttpStatus.UNAUTHORIZED,"登录信息失效");
//        }else {
//            appletTokenService.refreshToken(loginUser);
//        }
//        return AjaxResult.success(apiUserReceiveAddressService.selectUserReceiveAddressById(id));
//    }

    /**
     * 新增用户收货地址信息
     */
    @PostMapping("/addUserReceiveAddress")
    @RepeatSubmit
    public AjaxResult add(@RequestBody ApiUserReceiveAddress userReceiveAddress)
    {
        AppletLoginUser loginUser = appletTokenService.getLoginUser ();
        if(StringUtils.isNull(loginUser)){
            return AjaxResult.error(HttpStatus.UNAUTHORIZED,"登录信息失效");
        }else {
            appletTokenService.refreshToken(loginUser);
        }
        //验证输入信息是否缺失
        if (StringUtils.isEmpty(userReceiveAddress.getReceiverName())){
            return AjaxResult.error("请输入收货人名称","hint_55");
        }
        if (StringUtils.isEmpty(userReceiveAddress.getReceiverMobile())){
            return AjaxResult.error("请输入收货手机号","hint_56");
        }
        if (!StringUtils.isNumeric(userReceiveAddress.getReceiverMobile())){
            return AjaxResult.error("手机号格式不正确！","hint_57");
        }
        if (StringUtils.isEmpty(userReceiveAddress.getReceiverArea())){
            return AjaxResult.error("请输入收货地区","hint_58");
        }
        if (StringUtils.isEmpty(userReceiveAddress.getReceiverAddress())){
            return AjaxResult.error("请输入收货详细地址","hint_59");
        }
        userReceiveAddress.setUserId(loginUser.getApiUserInfo().getId());
        userReceiveAddress.setIsDefault(1);
        return toAjax(apiUserReceiveAddressService.insertUserReceiveAddress(userReceiveAddress));
    }

    /**
     * 修改用户收货地址信息
     */
    @PostMapping("/editUserReceiveAddress")
    public AjaxResult edit(@RequestBody ApiUserReceiveAddress userReceiveAddress)
    {
        AppletLoginUser loginUser = appletTokenService.getLoginUser ();
        if(StringUtils.isNull(loginUser)){
            return AjaxResult.error(HttpStatus.UNAUTHORIZED,"登录信息失效");
        }else {
            appletTokenService.refreshToken(loginUser);
        }
        if (!StringUtils.isNumeric(userReceiveAddress.getReceiverMobile())){
            return AjaxResult.error("手机号格式不正确！","hint_57");
        }
        return toAjax(apiUserReceiveAddressService.updateUserReceiveAddress(userReceiveAddress));
    }

    /**
     * 删除用户收货地址信息
     */
	@GetMapping("/deleteUserReceiveAddress")
    public AjaxResult remove(Long id)
    {
        AppletLoginUser loginUser = appletTokenService.getLoginUser ();
        if(StringUtils.isNull(loginUser)){
            return AjaxResult.error(HttpStatus.UNAUTHORIZED,"登录信息失效");
        }else {
            appletTokenService.refreshToken(loginUser);
        }
        return toAjax(apiUserReceiveAddressService.deleteUserReceiveAddressById(id));
    }

    /**
     * 设置默认地址
     */
    @PostMapping("/setDefaultAddress")
    public AjaxResult setDefaultAddress(@RequestBody ApiUserReceiveAddress userReceiveAddress)
    {
        AppletLoginUser loginUser = appletTokenService.getLoginUser ();
        if(StringUtils.isNull(loginUser)){
            return AjaxResult.error(HttpStatus.UNAUTHORIZED,"登录信息失效");
        }else {
            appletTokenService.refreshToken(loginUser);
        }
        //先清空用户的默认情况
        apiUserReceiveAddressService.clearDefaultReceiveAddress(loginUser.getApiUserInfo().getId());
        //变为默认
        userReceiveAddress.setIsDefault(0);
        return toAjax(apiUserReceiveAddressService.updateUserReceiveAddress(userReceiveAddress));
    }

}
