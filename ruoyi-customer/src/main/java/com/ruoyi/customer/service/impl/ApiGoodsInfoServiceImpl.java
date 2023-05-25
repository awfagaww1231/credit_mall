package com.ruoyi.customer.service.impl;

import com.ruoyi.common.utils.ServletUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.x.lang.LangUtils;
import com.ruoyi.customer.domain.ApiGoodsInfo;
import com.ruoyi.customer.domain.ApiSpecificationConfig;
import com.ruoyi.customer.domain.ApiSpecificationValue;
import com.ruoyi.customer.mapper.ApiGoodsInfoMapper;
import com.ruoyi.customer.mapper.ApiGoodsSpecificationMapper;
import com.ruoyi.customer.service.IApiGoodsInfoService;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ApiGoodsInfoServiceImpl implements IApiGoodsInfoService {

    @Autowired
    private ApiGoodsInfoMapper apiGoodsInfoMapper;

    @Resource
    private ApiGoodsSpecificationMapper apiGoodsSpecificationMapper;

    @Autowired
    private IApiGoodsInfoService apiGoodsInfoService;

    @Override
    public List<ApiGoodsInfo> goodsInfoListByCategory(Long category) {
        return getLanguageValue(apiGoodsInfoMapper.goodsInfoListByCategory(category));
    }

    @Override
    public List<ApiGoodsInfo> selectGoodsInfoList(ApiGoodsInfo apiGoodsInfo) {
        if (apiGoodsInfo.getBrandIds().size() == 0){
            apiGoodsInfo.setBrandIds(null);
        }
        return getLanguageValue(apiGoodsInfoMapper.selectGoodsInfoList(apiGoodsInfo));
    }

    @Override
    public List<ApiGoodsInfo> specialGoodsList() {
        return getLanguageValue(apiGoodsInfoMapper.specialGoodsList());
    }

    @Override
    public List<ApiGoodsInfo> featuredGoodsList() {
        return getLanguageValue(apiGoodsInfoMapper.featuredGoodsList());
    }

    @Override
    public List<ApiGoodsInfo> shopGoodsInfoByShopId(Long shopId) {
        List<ApiGoodsInfo> apiGoodsInfos = apiGoodsInfoMapper.shopGoodsInfoByShopId(shopId);
        for (int i = 0; i < apiGoodsInfos.size(); i++) {
            int sales = apiGoodsInfoService.querySales(apiGoodsInfos.get(i).getId());
            apiGoodsInfos.get(i).setSales(sales);
        }
        return getLanguageValue(apiGoodsInfos);
    }

    @Override
    public List<ApiGoodsInfo> mayLikeShopGoodsList() {
        return getLanguageValue(apiGoodsInfoMapper.mayLikeShopGoodsList());
    }

    @Override
    public ApiGoodsInfo goodsInfoByShopGoodsId(Long shopGoodsId) {
        ApiGoodsInfo apiGoodsInfo = apiGoodsInfoMapper.goodsInfoByShopGoodsId(shopGoodsId);
        //规格
        List<ApiSpecificationConfig> specificationConfigs = getSpecification(apiGoodsInfo.getGoodsId());
        apiGoodsInfo.setSpecificationConfigs(specificationConfigs);
        //订单数量
        int sales = apiGoodsInfoService.querySales(shopGoodsId);
        apiGoodsInfo.setSales(sales);
        return getLanguageValue(apiGoodsInfo);
    }

    public List<ApiSpecificationConfig> getSpecification(Long goodsId){
        String lang = LangUtils.getLang();
        List<ApiSpecificationConfig> specificationConfigs = apiGoodsSpecificationMapper.selectSpecificationConfigsByGoodsId(goodsId);
        for (int i = 0; i < specificationConfigs.size(); i++) {
            //多语言
            ApiSpecificationConfig apiSpecificationConfig = specificationConfigs.get(i);
            Object langObj = null;
            try {
                langObj = PropertyUtils.describe(apiSpecificationConfig).get("specificationName" + LangUtils.toUpperCaseFirstIndex(lang));
            } catch (Exception e) {

            }
            if (langObj != null){
                String specificationNameLang = String.valueOf(langObj);
                if (StringUtils.isNotEmpty(specificationNameLang)){
                    apiSpecificationConfig.setSpecificationName(specificationNameLang);
                }
            }
            List<ApiSpecificationValue> specificationValues = apiGoodsSpecificationMapper.selectSpecificationValues(goodsId, specificationConfigs.get(i).getId());
            for (int j = 0; j < specificationValues.size(); j++) {
                //多语言
                ApiSpecificationValue apiSpecificationValue = specificationValues.get(j);
                Object langObj2 = null;
                try {
                    langObj2 = PropertyUtils.describe(apiSpecificationValue).get("specificationValue" + LangUtils.toUpperCaseFirstIndex(lang));
                } catch (Exception e) {

                }
                if (langObj2 != null){
                    String specificationValueLang = String.valueOf(langObj2);
                    if (StringUtils.isNotEmpty(specificationValueLang)){
                        apiSpecificationValue.setSpecificationValue(specificationValueLang);
                    }
                }
            }
            specificationConfigs.get(i).setSpecificationValues(specificationValues);
        }
        return specificationConfigs;
    }

    @Override
    public int querySales(Long shopGoodsId) {
        Integer sales = apiGoodsInfoMapper.querySales(shopGoodsId);
        if (sales == null){
            sales = 0;
        }
        return sales;
    }

    //获取多语言值
    public ApiGoodsInfo getLanguageValue(ApiGoodsInfo apiGoodsInfo){
        String languageId = ServletUtils.getRequest().getHeader("languageId");
        //如果多语言id为空，默认多语言id为1，即显示中文
        if (StringUtils.isNull(languageId) | StringUtils.isEmpty(languageId)){
            return apiGoodsInfo;
        }
        //如果多语言id不为空
        if (languageId.equals(String.valueOf(1))){
            return apiGoodsInfo;
        }
        if (StringUtils.isNull(apiGoodsInfo)){
            return null;
        }
        Long goodsId = apiGoodsInfo.getGoodsId();
        ApiGoodsInfo languageObject = apiGoodsInfoMapper.selectLanguageObject(Long.valueOf(languageId), goodsId);
        if (StringUtils.isNull(languageObject)){
            languageObject = new ApiGoodsInfo();
        }
        apiGoodsInfo.setGoodsName(languageObject.getGoodsName());
        apiGoodsInfo.setDetail(languageObject.getDetail());
        apiGoodsInfo.setIntroduce(languageObject.getIntroduce());
        return apiGoodsInfo;
    }

    //获取多语言值
    public List<ApiGoodsInfo> getLanguageValue(List<ApiGoodsInfo> apiGoodsInfos){
        String languageId = ServletUtils.getRequest().getHeader("languageId");
        //如果多语言id为空，默认多语言id为1，即显示中文
        if (StringUtils.isNull(languageId) | StringUtils.isEmpty(languageId)){
            return apiGoodsInfos;
        }
        //如果多语言id不为空
        if (languageId.equals(String.valueOf(1))){
            return apiGoodsInfos;
        }
        if (StringUtils.isNull(apiGoodsInfos)){
            return null;
        }
        for (int i = 0; i < apiGoodsInfos.size(); i++) {
            Long goodsId = apiGoodsInfos.get(i).getGoodsId();
            ApiGoodsInfo languageObject = apiGoodsInfoMapper.selectLanguageObject(Long.valueOf(languageId), goodsId);
            if (StringUtils.isNull(languageObject)){
                languageObject = new ApiGoodsInfo();
            }
            apiGoodsInfos.get(i).setGoodsName(languageObject.getGoodsName());
            apiGoodsInfos.get(i).setDetail(languageObject.getDetail());
            apiGoodsInfos.get(i).setIntroduce(languageObject.getIntroduce());
        }
        return apiGoodsInfos;
    }
}
