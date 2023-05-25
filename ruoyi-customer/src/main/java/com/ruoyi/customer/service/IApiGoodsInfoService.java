package com.ruoyi.customer.service;

import com.ruoyi.customer.domain.ApiGoodsInfo;

import java.util.List;

public interface IApiGoodsInfoService {


    /**
     * 根据类目查找商品
     */
    List<ApiGoodsInfo> goodsInfoListByCategory(Long category);


    /**
     * 商品列表
     */
    List<ApiGoodsInfo> selectGoodsInfoList(ApiGoodsInfo apiGoodsInfo);

    /**
     * 特价商品列表
     */
    List<ApiGoodsInfo> specialGoodsList();

    /**
     * 精选商品列表
     */
    List<ApiGoodsInfo> featuredGoodsList();

    /**
     * 进入店铺查看店铺商品列表
     */
    List<ApiGoodsInfo> shopGoodsInfoByShopId(Long shopId);

    /**
     * 猜你喜欢的商品列表
     */
    List<ApiGoodsInfo> mayLikeShopGoodsList();

    /**
     * 根据店铺商品id找商品
     */
    ApiGoodsInfo goodsInfoByShopGoodsId(Long shopGoodsId);

    /**
     * 查看商品销量
     */
    int querySales(Long shopGoodsId);


}
