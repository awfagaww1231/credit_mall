package com.ruoyi.customer.service;

import com.ruoyi.customer.domain.ApiGoodsInfo;

import java.util.List;

public interface IApiTopRankingsService {

    /**
     * 查询人气排行商品列表
     */
    List<ApiGoodsInfo> topRankingsList();
}
