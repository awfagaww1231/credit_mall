package com.ruoyi.customer.mapper;

import com.ruoyi.customer.domain.ApiGoodsInfo;
import com.ruoyi.customer.domain.ApiSuperDealsClassification;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ApiSuperDealsClassificationMapper {

    /**
     * 查询SuperDeals活动分类列表
     */
    List<ApiSuperDealsClassification> superDealsClassificationList();

    /**
     * 根据SuperDeals活动分类查询商品
     */
    List<ApiGoodsInfo> shopGoodsListClassification(@Param("classificationId")Long classificationId, @Param("languageId")Long languageId);
}
