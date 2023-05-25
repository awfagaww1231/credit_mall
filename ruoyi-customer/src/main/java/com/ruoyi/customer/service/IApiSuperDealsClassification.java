package com.ruoyi.customer.service;

import com.ruoyi.customer.domain.ApiGoodsInfo;
import com.ruoyi.customer.domain.ApiSuperDealsClassification;

import java.util.List;

public interface IApiSuperDealsClassification {

    /**
     * 查询SuperDeals活动分类列表
     */
    List<ApiSuperDealsClassification> superDealsClassificationList();

    /**
     * 根据SuperDeals活动分类查询商品
     */
    List<ApiGoodsInfo> shopGoodsListClassification(Long classificationId);
}
