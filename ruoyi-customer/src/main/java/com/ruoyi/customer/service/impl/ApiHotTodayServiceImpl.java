package com.ruoyi.customer.service.impl;

import com.ruoyi.common.utils.x.lang.LangUtils;
import com.ruoyi.customer.domain.ApiGoodsInfo;
import com.ruoyi.customer.mapper.ApiHotTodayMapper;
import com.ruoyi.customer.service.IApiGoodsInfoService;
import com.ruoyi.customer.service.IApiHotTodayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApiHotTodayServiceImpl implements IApiHotTodayService {

    @Autowired
    private ApiHotTodayMapper apiHotTodayMapper;

    @Autowired
    private IApiGoodsInfoService apiGoodsInfoService;

    @Override
    public List<ApiGoodsInfo> hotTodayList() {
        List<ApiGoodsInfo> apiGoodsInfos = apiHotTodayMapper.hotTodayList(LangUtils.getLanguageId());
        for (int i = 0; i < apiGoodsInfos.size(); i++) {
            Long id = apiGoodsInfos.get(i).getId();
            int sales = apiGoodsInfoService.querySales(id);
            apiGoodsInfos.get(i).setSales(sales);
        }
        return apiGoodsInfos;
    }
}
