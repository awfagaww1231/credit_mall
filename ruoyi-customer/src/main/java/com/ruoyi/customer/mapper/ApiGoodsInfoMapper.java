package com.ruoyi.customer.mapper;

import com.ruoyi.customer.domain.ApiGoodsInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ApiGoodsInfoMapper {

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

    //根据多语言id和商品id查询多语言值对象
    ApiGoodsInfo selectLanguageObject(@Param("languageId")Long languageId,
                                      @Param("goodsId")Long goodsId);

    /**
     * 查看商品销量
     */
    Integer querySales(Long shopGoodsId);

}
