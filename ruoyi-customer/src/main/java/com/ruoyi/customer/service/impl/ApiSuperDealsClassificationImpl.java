package com.ruoyi.customer.service.impl;

import com.ruoyi.common.utils.x.lang.LangUtils;
import com.ruoyi.customer.domain.ApiGoodsInfo;
import com.ruoyi.customer.domain.ApiSuperDealsClassification;
import com.ruoyi.customer.mapper.ApiSuperDealsClassificationMapper;
import com.ruoyi.customer.service.IApiGoodsInfoService;
import com.ruoyi.customer.service.IApiSuperDealsClassification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApiSuperDealsClassificationImpl implements IApiSuperDealsClassification {

    @Autowired
    private ApiSuperDealsClassificationMapper apiSuperDealsClassificationMapper;

    @Autowired
    private IApiGoodsInfoService apiGoodsInfoService;

    @Override
    public List<ApiSuperDealsClassification> superDealsClassificationList() {
        return apiSuperDealsClassificationMapper.superDealsClassificationList();
    }

    @Override
    public List<ApiGoodsInfo> shopGoodsListClassification(Long classificationId) {
        List<ApiGoodsInfo> apiGoodsInfos = apiSuperDealsClassificationMapper.shopGoodsListClassification(classificationId, LangUtils.getLanguageId());
        for (int i = 0; i < apiGoodsInfos.size(); i++) {
            ApiGoodsInfo apiGoodsInfo = apiGoodsInfos.get(i);
            //商品销量
            int sales = apiGoodsInfoService.querySales(apiGoodsInfo.getId());
            apiGoodsInfo.setSales(sales);
        }
        return apiGoodsInfos;
    }
}
