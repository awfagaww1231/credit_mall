package com.ruoyi.web.controller.app;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.customer.domain.ApiCategory;
import com.ruoyi.customer.service.IApiCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * APP商品类目Controller +
 * 
 * @author ruoyi
 * @date 2022-10-27
 */
@RestController
@RequestMapping("/api/category")
public class ApiCategoryController extends BaseController
{

    @Autowired
    private IApiCategoryService apiCategoryService;


    /**
     * 查询商品类别父类列表
     */
    @GetMapping("/categoryFatherList")
    public AjaxResult categoryFatherList(ApiCategory apiCategory)
    {
        startPage();
        //只查一级类目
        apiCategory.setCategoryLevel(1);
        //只查显示的，隐藏的类目不显示
        apiCategory.setIsVisible(0);
        List<ApiCategory> list = apiCategoryService.categorySonList(apiCategory);
        return AjaxResult.success(getDataTable(list));
    }

    /**
     * 查询商品类别子类列表
     */
    @GetMapping("/categoryListByParentId")
    public AjaxResult categoryListByParentId(ApiCategory apiCategory)
    {
        startPage();
        //只查显示的，隐藏的类目不显示
        apiCategory.setIsVisible(0);
        List<ApiCategory> list = apiCategoryService.categorySonList(apiCategory);
        for (int i = 0; i < list.size(); i++) {
            Long parentId = list.get(i).getId();
            apiCategory.setParentId(parentId);
            List<ApiCategory> sonList = apiCategoryService.categorySonList(apiCategory);
            list.get(i).setCategorySonList(sonList);
        }
        return AjaxResult.success(getDataTable(list));
    }

}
