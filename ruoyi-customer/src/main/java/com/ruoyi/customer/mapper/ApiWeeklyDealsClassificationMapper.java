package com.ruoyi.customer.mapper;

import com.ruoyi.customer.domain.ApiGoodsInfo;
import com.ruoyi.customer.domain.ApiWeeklyDealsClassification;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ApiWeeklyDealsClassificationMapper {

    /**
     * 查询weeklyDeals置顶的商品
     */
    List<ApiGoodsInfo> weeklyDealsTopGoods();

    /**
     * 查询SuperDeals活动分类列表
     */
    List<ApiWeeklyDealsClassification> weeklyDealsClassificationList();

    /**
     * 根据SuperDeals活动分类查询商品
     */
    List<ApiGoodsInfo> shopGoodsListClassification(@Param("classificationId") Long classificationId,@Param("languageId") Long languageId);
}
