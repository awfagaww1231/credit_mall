package com.ruoyi.web.controller.app;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.customer.common.AppletTokenService;
import com.ruoyi.customer.domain.ApiBrowseRecord;
import com.ruoyi.customer.domain.ApiGoodsInfo;
import com.ruoyi.customer.domain.AppletLoginUser;
import com.ruoyi.customer.service.IApiBrowseRecordService;
import com.ruoyi.customer.service.IApiGoodsInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * APP商城商品信息Controller +
 *
 * @author ruoyi
 * @date 2022-10-27
 */
@RestController
@RequestMapping("/api/goodsInfo")
public class ApiGoodsInfoController extends BaseController {

    @Autowired
    private IApiGoodsInfoService apiGoodsInfoService;

    @Autowired
    private AppletTokenService appletTokenService;

    @Autowired
    private IApiBrowseRecordService apiBrowseRecordService;

    /**
     * 根据类目查找商品
     */
    @GetMapping("/goodsInfoListByCategory")
    public AjaxResult goodsInfoListByCategory(Long category)
    {
        startPage();
        List<ApiGoodsInfo> apiGoodsInfos = apiGoodsInfoService.goodsInfoListByCategory(category);
        return AjaxResult.success(getDataTable(apiGoodsInfos));
    }

    /**
     * 商品列表
     */
    @GetMapping("/selectGoodsInfoList")
    public AjaxResult selectGoodsInfoList(ApiGoodsInfo apiGoodsInfo)
    {
        startPage();
        List<ApiGoodsInfo> apiGoodsInfos = apiGoodsInfoService.selectGoodsInfoList(apiGoodsInfo);
        return AjaxResult.success(getDataTable(apiGoodsInfos));
    }


    /**
     * 根据商品id查看商品详情
     */
    @GetMapping("/goodsInfoByShopGoodsId")
    public AjaxResult goodsInfoByShopGoodsId(Long shopGoodsId)
    {
        AppletLoginUser loginUser = appletTokenService.getLoginUser ();
        if(StringUtils.isNotNull(loginUser)){
            //如果已经登录，则记录足迹
            ApiBrowseRecord apiBrowseRecord = new ApiBrowseRecord();
            apiBrowseRecord.setUserId(loginUser.getApiUserInfo().getId());
            apiBrowseRecord.setShopGoodsInfoId(shopGoodsId);
            apiBrowseRecordService.insertBrowseRecord(apiBrowseRecord);
        }
        ApiGoodsInfo apiGoodsInfo = apiGoodsInfoService.goodsInfoByShopGoodsId(shopGoodsId);
        return AjaxResult.success(apiGoodsInfo);
    }

    /**
     * 特价商品列表
     */
    @GetMapping("/specialGoodsList")
    public AjaxResult specialGoodsList()
    {
        startPage();
        List<ApiGoodsInfo> apiGoodsInfos = apiGoodsInfoService.specialGoodsList();
        return AjaxResult.success(getDataTable(apiGoodsInfos));
    }

    /**
     * 精选商品列表
     */
    @GetMapping("/featuredGoodsList")
    public AjaxResult featuredGoodsList()
    {
//        AppletLoginUser loginUser = appletTokenService.getLoginUser ();
//        if(StringUtils.isNull(loginUser)){
//            return AjaxResult.error(HttpStatus.UNAUTHORIZED,"登录信息失效");
//        }else {
//            appletTokenService.refreshToken(loginUser);
//        }
        startPage();
        List<ApiGoodsInfo> apiGoodsInfos = apiGoodsInfoService.featuredGoodsList();
        return AjaxResult.success(getDataTable(apiGoodsInfos));
    }

    /**
     * 进入店铺查看店铺商品列表
     */
    @GetMapping("/shopGoodsInfoByShopId")
    public AjaxResult shopGoodsInfoByShopId(Long shopId)
    {
//        AppletLoginUser loginUser = appletTokenService.getLoginUser ();
//        if(StringUtils.isNull(loginUser)){
//            return AjaxResult.error(HttpStatus.UNAUTHORIZED,"登录信息失效");
//        }else {
//            appletTokenService.refreshToken(loginUser);
//        }
        startPage();
        List<ApiGoodsInfo> apiGoodsInfos = apiGoodsInfoService.shopGoodsInfoByShopId(shopId);
        return AjaxResult.success(getDataTable(apiGoodsInfos));
    }

    /**
     * 猜你喜欢的商品列表
     */
    @GetMapping("/mayLikeShopGoodsList")
    public AjaxResult mayLikeShopGoodsList()
    {
        startPage();
        List<ApiGoodsInfo> apiGoodsInfos = apiGoodsInfoService.mayLikeShopGoodsList();
        return AjaxResult.success(getDataTable(apiGoodsInfos));
    }

    /**
     * 查看商品销量
     */
    @GetMapping("/querySales")
    public AjaxResult querySales(Long shopGoodsId)
    {
//        AppletLoginUser loginUser = appletTokenService.getLoginUser ();
//        if(StringUtils.isNull(loginUser)){
//            return AjaxResult.error(HttpStatus.UNAUTHORIZED,"登录信息失效");
//        }else {
//            appletTokenService.refreshToken(loginUser);
//        }
        if (StringUtils.isNull(shopGoodsId)){
            return AjaxResult.error("请选择需要查看销量的商品","hint_35");
        }
        int sales = apiGoodsInfoService.querySales(shopGoodsId);
        return AjaxResult.success().put("sales",sales);
    }
}
