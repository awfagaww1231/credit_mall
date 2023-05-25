package com.ruoyi.web.controller.app;

import com.ruoyi.common.annotation.RepeatSubmit;
import com.ruoyi.common.constant.HttpStatus;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.customer.common.AppletTokenService;
import com.ruoyi.customer.domain.ApiUserFavoriteGoods;
import com.ruoyi.customer.domain.AppletLoginUser;
import com.ruoyi.customer.service.IApiUserFavoriteGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * C端用户收藏商品信息Controller +
 * 
 * @author ruoyi
 * @date 2022-11-10
 */
@RestController
@RequestMapping("/api/userFavoriteGoods")
public class ApiUserFavoriteGoodsController extends BaseController
{
    @Autowired
    private IApiUserFavoriteGoodsService apiUserFavoriteGoodsService;

    @Autowired
    private AppletTokenService appletTokenService;

    /**
     * 查询C端用户收藏商品信息列表
     */
    @GetMapping("/favoriteGoodsList")
    public AjaxResult list(ApiUserFavoriteGoods apiUserFavoriteGoods)
    {
        AppletLoginUser loginUser = appletTokenService.getLoginUser ();
        if(StringUtils.isNull(loginUser)){
            return AjaxResult.error(HttpStatus.UNAUTHORIZED,"登录信息失效");
        }else {
            appletTokenService.refreshToken(loginUser);
        }
        apiUserFavoriteGoods.setUserId(loginUser.getApiUserInfo().getId());
        apiUserFavoriteGoods.setStatus(0);
        startPage();
        List<ApiUserFavoriteGoods> list = apiUserFavoriteGoodsService.selectUserFavoriteGoodsList(apiUserFavoriteGoods);
        return AjaxResult.success(getDataTable(list));
    }



    /**
     * 商品收藏、取消收藏接口
     */
    @PostMapping("/editFavoriteGoodsInfo")
    @RepeatSubmit(interval = 500)
    public AjaxResult editFavoriteGoodsInfo(@RequestBody ApiUserFavoriteGoods apiUserFavoriteGoods)
    {
        AppletLoginUser loginUser = appletTokenService.getLoginUser ();
        if(StringUtils.isNull(loginUser)){
            return AjaxResult.error(HttpStatus.UNAUTHORIZED,"登录信息失效");
        }else {
            appletTokenService.refreshToken(loginUser);
        }
        apiUserFavoriteGoods.setUserId(loginUser.getApiUserInfo().getId());
        ApiUserFavoriteGoods apiUserFavoriteGoodsVo = apiUserFavoriteGoodsService.selectUserFavoriteGoods(apiUserFavoriteGoods);
        //如果没有相应数据，则新增数据
        if (StringUtils.isNull(apiUserFavoriteGoodsVo)){
            apiUserFavoriteGoods.setStatus(0);
            return toAjax(apiUserFavoriteGoodsService.insertUserFavoriteGoods(apiUserFavoriteGoods));
        }else {
            if (apiUserFavoriteGoodsVo.getStatus() == 0){
                apiUserFavoriteGoodsVo.setStatus(1);
            }else {
                apiUserFavoriteGoodsVo.setStatus(0);
            }
            //如果有相应数据，则变更收藏状态
            return toAjax(apiUserFavoriteGoodsService.updateUserFavoriteGoods(apiUserFavoriteGoodsVo));
        }
    }

    /**
     * 查询用户与商品的收藏状态
     */
    @GetMapping("/selectFavoriteGoodsStatus")
    public AjaxResult selectFavoriteGoodsStatus(ApiUserFavoriteGoods apiUserFavoriteGoods)
    {
        AppletLoginUser loginUser = appletTokenService.getLoginUser ();
        if(StringUtils.isNull(loginUser)){
            return AjaxResult.success().put("FavoriteGoodsStatus",1);
        }else {
            appletTokenService.refreshToken(loginUser);
        }
        apiUserFavoriteGoods.setUserId(loginUser.getApiUserInfo().getId());
        ApiUserFavoriteGoods apiUserFavoriteGoodsVo = apiUserFavoriteGoodsService.selectUserFavoriteGoods(apiUserFavoriteGoods);
        Integer FavoriteGoodsStatus = 1;
        if (StringUtils.isNotNull(apiUserFavoriteGoodsVo)){
            if (StringUtils.isNotNull(apiUserFavoriteGoodsVo.getStatus())){
                FavoriteGoodsStatus = apiUserFavoriteGoodsVo.getStatus();
            }
        }
        return AjaxResult.success().put("FavoriteGoodsStatus",FavoriteGoodsStatus);
    }
}
