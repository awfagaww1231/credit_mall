package com.ruoyi.customer.service.impl;

import com.ruoyi.common.utils.ServletUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.customer.domain.ApiCategory;
import com.ruoyi.customer.mapper.ApiCategoryMapper;
import com.ruoyi.customer.service.IApiCategoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ApiCategoryServiceImpl implements IApiCategoryService {

    @Resource
    private ApiCategoryMapper apiCategoryMapper;


    @Override
    public List<ApiCategory> categorySonList(ApiCategory apiCategory) {
        return getLanguageValue(apiCategoryMapper.categorySonList(apiCategory));
    }

    //获取多语言值
    public List<ApiCategory> getLanguageValue(List<ApiCategory> apiCategories){
        String languageId = ServletUtils.getRequest().getHeader("languageId");
        //如果多语言id为空，默认多语言id为1，即显示中文
        if (StringUtils.isNull(languageId) | StringUtils.isEmpty(languageId)){
            return apiCategories;
        }
        //如果多语言id不为空
        if (languageId.equals(String.valueOf(1))){
            return apiCategories;
        }
        if (StringUtils.isNull(apiCategories)){
            return null;
        }
        for (int i = 0; i < apiCategories.size(); i++) {
            Long categoryId = apiCategories.get(i).getId();
            ApiCategory languageObject = apiCategoryMapper.selectLanguageObject(Long.valueOf(languageId), categoryId);
            if (StringUtils.isNull(languageObject)){
                languageObject = new ApiCategory();
            }
            if (StringUtils.isNotEmpty(languageObject.getCategoryName())){
                apiCategories.get(i).setCategoryName(languageObject.getCategoryName());
            }
        }
        return apiCategories;
    };
}
