package com.ruoyi.web.controller.app;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.customer.common.AppletTokenService;
import com.ruoyi.customer.domain.ApiUserFollowShop;
import com.ruoyi.customer.domain.AppletLoginUser;
import com.ruoyi.customer.service.IApiUserFollowShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 用户订阅店铺信息Controller +
 * 
 * @author ruoyi
 * @date 2022-11-28
 */
@RestController
@RequestMapping("/api/userFollowShop")
public class ApiUserFollowShopController extends BaseController
{
    @Autowired
    private IApiUserFollowShopService userFollowShopService;

    @Autowired
    private AppletTokenService appletTokenService;

    /**
     * 店铺订阅、取消订阅
     */
    @GetMapping("/toFollow")
    public AjaxResult toFollow(ApiUserFollowShop userFollowShop)
    {
        AppletLoginUser loginUser = appletTokenService.getLoginUser();
        if (StringUtils.isNull(loginUser)) {
            return AjaxResult.error(401, "登录信息失效");
        } else {
            appletTokenService.refreshToken(loginUser);
        }
        if (StringUtils.isNull(userFollowShop.getShopId())){
            return AjaxResult.error("请选择需要关注的店铺","hint_54");
        }
        userFollowShop.setUserId(loginUser.getApiUserInfo().getId());
        return toAjax(userFollowShopService.toFollow(userFollowShop));
    }

    /**
     * 订阅的店铺列表
     */
    @GetMapping("/userFollowShopList")
    public AjaxResult userFollowShopList(ApiUserFollowShop userFollowShop)
    {
        AppletLoginUser loginUser = appletTokenService.getLoginUser();
        if (StringUtils.isNull(loginUser)) {
            return AjaxResult.error(401, "登录信息失效");
        } else {
            appletTokenService.refreshToken(loginUser);
        }
        startPage();
        userFollowShop.setUserId(loginUser.getApiUserInfo().getId());
        List<ApiUserFollowShop> apiUserFollowShops = userFollowShopService.selectUserFollowShopList(userFollowShop);
        return AjaxResult.success(getDataTable(apiUserFollowShops));
    }

}
