package com.ruoyi.system.service;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.system.domain.Category;

import java.util.List;

/**
 * 商品类目Service接口
 * 
 * @author ruoyi
 * @date 2022-10-27
 */
public interface ICategoryService 
{
    /**
     * 查询商品类目
     * 
     * @param id 商品类目主键
     * @return 商品类目
     */
    public Category selectCategoryById(Long id);

    /**
     * 查询商品类目
     *
     * @param name 商品类目名称
     * @return 商品类目
     */
    public Category selectCategoryByName(String name);

    /**
     * 查询商品类目列表
     * 
     * @param category 商品类目
     * @return 商品类目集合
     */
    public List<Category> selectCategoryList(Category category);

    /**
     * 新增商品类目
     * 
     * @param category 商品类目
     * @return 结果
     */
    public AjaxResult insertCategory(Category category);

    /**
     * 修改商品类目
     * 
     * @param category 商品类目
     * @return 结果
     */
    public AjaxResult updateCategory(Category category);

    /**
     * 批量删除商品类目
     * 
     * @param ids 需要删除的商品类目主键集合
     * @return 结果
     */
    public int deleteCategoryByIds(Long[] ids);

    /**
     * 删除商品类目信息
     * 
     * @param id 商品类目主键
     * @return 结果
     */
    public int deleteCategoryById(Long id);

    /**
     * 获取所属类别名称
     */
    String getCategoryName(Long minorClass);

    /**
     * 变更多语言值
     */
    int changeLanguageValue(Category category);

    String getCategoryName(Long minorClass, Long language);

    //获取多语言值
    public Category getLanguageValue(Category category,Long languageId);
}
