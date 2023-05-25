package com.ruoyi.customer.service.impl;

import com.ruoyi.common.utils.x.lang.LangUtils;
import com.ruoyi.customer.domain.ApiGoodsInfo;
import com.ruoyi.customer.domain.ApiWeeklyDealsClassification;
import com.ruoyi.customer.mapper.ApiWeeklyDealsClassificationMapper;
import com.ruoyi.customer.service.IApiGoodsInfoService;
import com.ruoyi.customer.service.IApiWeeklyDealsClassification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApiWeeklyDealsClassificationImpl implements IApiWeeklyDealsClassification {

    @Autowired
    private ApiWeeklyDealsClassificationMapper apiWeeklyDealsClassificationMapper;

    @Autowired
    private IApiGoodsInfoService apiGoodsInfoService;

    @Override
    public List<ApiGoodsInfo> weeklyDealsTopGoods() {
        return apiWeeklyDealsClassificationMapper.weeklyDealsTopGoods();
    }

    @Override
    public List<ApiWeeklyDealsClassification> weeklyDealsClassificationList() {
        return apiWeeklyDealsClassificationMapper.weeklyDealsClassificationList();
    }

    @Override
    public List<ApiGoodsInfo> shopGoodsListClassification(Long classificationId) {
        return apiWeeklyDealsClassificationMapper.shopGoodsListClassification(classificationId, LangUtils.getLanguageId());
    }
}
