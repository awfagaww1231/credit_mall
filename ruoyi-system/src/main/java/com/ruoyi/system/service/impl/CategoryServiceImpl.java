package com.ruoyi.system.service.impl;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.Category;
import com.ruoyi.system.mapper.CategoryMapper;
import com.ruoyi.system.service.ICategoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 商品类目Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-10-27
 */
@Service
public class CategoryServiceImpl implements ICategoryService 
{
    @Resource
    private CategoryMapper categoryMapper;

    /**
     * 查询商品类目
     * 
     * @param id 商品类目主键
     * @return 商品类目
     */
    @Override
    public Category selectCategoryById(Long id)
    {
        return categoryMapper.selectCategoryById(id);
    }

    @Override
    public Category selectCategoryByName(String name) {
        return categoryMapper.selectCategoryByName(name);
    }

    /**
     * 查询商品类目列表
     * 
     * @param category 商品类目
     * @return 商品类目
     */
    @Override
    public List<Category> selectCategoryList(Category category)
    {
        return getLanguageValue(categoryMapper.selectCategoryList(category),category.getLanguageId());
    }

    /**
     * 新增商品类目
     * 
     * @param category 商品类目
     * @return 结果
     */
    @Override
    public AjaxResult insertCategory(Category category)
    {
        Category categoryVo = categoryMapper.selectCategoryByName(category.getCategoryName());
        if (StringUtils.isNotNull(categoryVo)){
            return AjaxResult.error("此类别名称已存在");
        }

        //如果是创建大类
        if (StringUtils.isNull(category.getParentId())){
            //父类id为0，表示没有父类
            category.setParentId(0L);
            //设置类别等级为1级
            category.setCategoryLevel(1);
        }else {
            //如果是创建小类
            Category categoryFather = categoryMapper.selectCategoryById(category.getParentId());
            if (StringUtils.isNull(categoryFather)){
                return AjaxResult.error("获取父类信息异常");
            }
            if (categoryFather.getCategoryLevel()+1 > 3){
                return AjaxResult.error("目前只允许创建至三级分类");
            }
            category.setCategoryLevel(categoryFather.getCategoryLevel()+1);
        }
        int insertCategory = categoryMapper.insertCategory(category);
        if (insertCategory <= 0){
            return AjaxResult.error();
        }
        return  AjaxResult.success();
    }

    /**
     * 修改商品类目
     * 
     * @param category 商品类目
     * @return 结果
     */
    @Override
    @Transactional
    public AjaxResult updateCategory(Category category)
    {
        Category categoryVo = categoryMapper.selectCategoryByName(category.getCategoryName());
        if (StringUtils.isNotNull(categoryVo)){
            if (!category.getId().equals(categoryVo.getId())){
                return AjaxResult.error("此类目名称已存在");
            }
        }
        //如果是要修改多语言
        if (!category.getLanguageId().equals(1L)){
            int changeLanguageValue = changeLanguageValue(category);
            if (changeLanguageValue <= 0){
                throw new RuntimeException();
            }
            category.setCategoryName(null);
        }
        int updateCategory = categoryMapper.updateCategory(category);
        if (updateCategory <= 0){
            throw new RuntimeException();
        }
        return AjaxResult.success();
    }

    /**
     * 批量删除商品类目
     * 
     * @param ids 需要删除的商品类目主键
     * @return 结果
     */
    @Override
    public int deleteCategoryByIds(Long[] ids)
    {
        return categoryMapper.deleteCategoryByIds(ids);
    }

    /**
     * 删除商品类目信息
     * 
     * @param id 商品类目主键
     * @return 结果
     */
    @Override
    public int deleteCategoryById(Long id)
    {
        return categoryMapper.deleteCategoryById(id);
    }


    @Override
    public String getCategoryName(Long minorClass) {
        Category category = categoryMapper.selectCategoryById(minorClass);
        if (StringUtils.isNull(category)){
            return "";
        }
        //类别名称
        String categoryName = category.getCategoryName();
        //类别等级
        //父类id
        Long parentId = category.getParentId();
        Integer categoryLevel = category.getCategoryLevel();
        for (int j = 0; j < categoryLevel-1; j++) {
            Category categoryFather = categoryMapper.selectCategoryById(parentId);
            if (StringUtils.isNotNull(categoryFather)){
                String categoryNameFather = categoryFather.getCategoryName();
                if (StringUtils.isNotEmpty(categoryNameFather)){
                    parentId = categoryFather.getParentId();
                    categoryName = categoryNameFather + "|" + categoryName;
                }
            }
        }
        return categoryName;
    }


    @Override
    public String getCategoryName(Long minorClass, Long language) {
        Category category = categoryMapper.selectCategoryById(minorClass);
        if (StringUtils.isNull(category)){
            return "";
        }
        category = getLanguageValue(category, language);
        //类别名称
        String categoryName = category.getCategoryName();
        //类别等级
        //父类id
        Long parentId = category.getParentId();
        Integer categoryLevel = category.getCategoryLevel();
        for (int j = 0; j < categoryLevel-1; j++) {
            Category categoryFather = categoryMapper.selectCategoryById(parentId);
            categoryFather = getLanguageValue(categoryFather, language);
            if (StringUtils.isNotNull(categoryFather)){
                String categoryNameFather = categoryFather.getCategoryName();
                if (StringUtils.isNotEmpty(categoryNameFather)){
                    parentId = categoryFather.getParentId();
                    categoryName = categoryNameFather + "|" + categoryName;
                }
            }
        }
        return categoryName;
    }

    //获取多语言值
    public List<Category> getLanguageValue(List<Category> categorys,Long languageId){
        //如果多语言id为空，默认多语言id为1，即显示中文
        if (StringUtils.isNull(languageId)){
            return categorys;
        }
        //如果多语言id不为空
        if (languageId.equals(1L)){
            return categorys;
        }
        for (int i = 0; i < categorys.size(); i++) {
            Long categoryId = categorys.get(i).getId();
            Category languageObject = categoryMapper.selectLanguageObject(languageId, categoryId);
            if (StringUtils.isNull(languageObject)){
                languageObject = new Category();
            }
            if (StringUtils.isNotEmpty(languageObject.getCategoryName())){
                categorys.get(i).setCategoryName(languageObject.getCategoryName());
            }
        }
        return categorys;
    }

    //获取多语言值
    public Category getLanguageValue(Category category,Long languageId){
        //如果多语言id为空，默认多语言id为1，即显示中文
        if (StringUtils.isNull(languageId)){
            return category;
        }
        //如果多语言id不为空
        if (languageId.equals(1L)){
            return category;
        }
        Long categoryId = category.getId();
        Category languageObject = categoryMapper.selectLanguageObject(languageId, categoryId);
        if (StringUtils.isNull(languageObject)){
            languageObject = new Category();
        }
        if (StringUtils.isNotEmpty(languageObject.getCategoryName())){
            category.setCategoryName(languageObject.getCategoryName());
        }
        return category;
    }

    @Override
    public int changeLanguageValue(Category category) {
        Long languageId = category.getLanguageId();
        Long categoryId = category.getId();
        Category languageObject = categoryMapper.selectLanguageObject(languageId, categoryId);
        //如果多语言信息不存在
        if (StringUtils.isNull(languageObject)){
            return categoryMapper.insertLanguageValue(category);
        }else {
            return categoryMapper.changeLanguageValue(category);
        }
    }
}
