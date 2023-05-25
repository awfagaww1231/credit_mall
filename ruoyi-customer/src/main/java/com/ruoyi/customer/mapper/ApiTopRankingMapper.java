package com.ruoyi.customer.mapper;

import com.ruoyi.customer.domain.ApiGoodsInfo;

import java.util.List;

public interface ApiTopRankingMapper {

    /**
     * 查询人气排行商品列表
     */
    List<ApiGoodsInfo> topRankingsList();
}
