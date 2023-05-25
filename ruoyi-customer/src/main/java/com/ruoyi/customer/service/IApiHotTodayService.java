package com.ruoyi.customer.service;

import com.ruoyi.customer.domain.ApiGoodsInfo;

import java.util.List;

public interface IApiHotTodayService {

    /**
     * 查询今日热门列表
     */
    List<ApiGoodsInfo> hotTodayList();
}
