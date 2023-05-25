package com.ruoyi.customer.service;

import com.ruoyi.customer.domain.ApiCategory;

import java.util.List;

public interface IApiCategoryService {

    /**
     * 查询商品类别父类列表
     */
    List<ApiCategory> categorySonList(ApiCategory apiCategory);
}
