package com.ruoyi.web.controller.app;

import com.ruoyi.common.constant.HttpStatus;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.customer.common.AppletTokenService;
import com.ruoyi.customer.domain.ApiShopInfo;
import com.ruoyi.customer.domain.ApiUserFollowShop;
import com.ruoyi.customer.domain.AppletLoginUser;
import com.ruoyi.customer.service.IApiShopInfoService;
import com.ruoyi.customer.service.IApiUserFollowShopService;
import com.ruoyi.system.domain.ShopInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * APP店铺信息心Controller +
 *
 * @author ruoyi
 * @date 2022-10-27
 */
@RestController
@RequestMapping("/api/shopInfo")
public class ApiShopInfoController extends BaseController {

    @Autowired
    private IApiShopInfoService apiShopInfoService;

    @Autowired
    private AppletTokenService appletTokenService;

    @Autowired
    private IApiUserFollowShopService apiUserFollowShopService;

    /**
     * 查询店铺信息
     */
    @GetMapping("/shopInfoById")
    public AjaxResult list(ApiShopInfo shopInfo)
    {
        //店铺的关注状态
        int followStatus = 1;
        //店铺关注人数
        ApiUserFollowShop apiUserFollowShop = new ApiUserFollowShop();
        apiUserFollowShop.setShopId(shopInfo.getId());
        apiUserFollowShop.setStatus(0);
        int followNum = apiUserFollowShopService.selectUserFollowShopList(apiUserFollowShop).size();

        AppletLoginUser loginUser = appletTokenService.getLoginUser ();
        if(StringUtils.isNotNull(loginUser)){
            //如果已经登录
            apiUserFollowShop = new ApiUserFollowShop();
            apiUserFollowShop.setUserId(loginUser.getApiUserInfo().getId());
            apiUserFollowShop.setShopId(shopInfo.getId());
            apiUserFollowShop = apiUserFollowShopService.selectUserFollowShop(apiUserFollowShop);
            if (StringUtils.isNotNull(apiUserFollowShop)){
                if (StringUtils.isNotNull(apiUserFollowShop.getStatus())){
                    followStatus = apiUserFollowShop.getStatus();
                }
            }

        }
        ApiShopInfo apiShopInfo = apiShopInfoService.selectShopInfoById(shopInfo.getId());
        apiShopInfo.setFollowStatus(followStatus);
        apiShopInfo.setFollowNum(followNum);
        return AjaxResult.success().put("apiShopInfo",apiShopInfo);
    }

}
