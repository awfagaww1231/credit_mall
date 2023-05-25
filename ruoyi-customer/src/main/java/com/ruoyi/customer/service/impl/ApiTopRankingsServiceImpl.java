package com.ruoyi.customer.service.impl;

import com.ruoyi.customer.domain.ApiGoodsInfo;
import com.ruoyi.customer.mapper.ApiTopRankingMapper;
import com.ruoyi.customer.service.IApiGoodsInfoService;
import com.ruoyi.customer.service.IApiTopRankingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApiTopRankingsServiceImpl implements IApiTopRankingsService {

    @Autowired
    private ApiTopRankingMapper apiTopRankingMapper;

    @Autowired
    private IApiGoodsInfoService apiGoodsInfoService;

    @Override
    public List<ApiGoodsInfo> topRankingsList() {
        List<ApiGoodsInfo> apiGoodsInfos = apiTopRankingMapper.topRankingsList();
        for (int i = 0; i < apiGoodsInfos.size(); i++) {
            int sales = apiGoodsInfoService.querySales(apiGoodsInfos.get(i).getId());
            apiGoodsInfos.get(i).setSales(sales);
        }
        return apiGoodsInfos;
    }
}
