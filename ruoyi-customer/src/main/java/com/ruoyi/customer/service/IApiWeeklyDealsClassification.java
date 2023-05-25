package com.ruoyi.customer.service;

import com.ruoyi.customer.domain.ApiGoodsInfo;
import com.ruoyi.customer.domain.ApiSuperDealsClassification;
import com.ruoyi.customer.domain.ApiWeeklyDealsClassification;

import java.util.List;

public interface IApiWeeklyDealsClassification {

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
    List<ApiGoodsInfo> shopGoodsListClassification(Long classificationId);
}
