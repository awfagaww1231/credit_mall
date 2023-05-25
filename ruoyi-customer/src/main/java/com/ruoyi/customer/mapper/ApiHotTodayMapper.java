package com.ruoyi.customer.mapper;

import com.ruoyi.customer.domain.ApiGoodsInfo;

import java.util.List;

public interface ApiHotTodayMapper {

    /**
     * 查询今日热门列表
     */
    List<ApiGoodsInfo> hotTodayList(Long languageId);
}
