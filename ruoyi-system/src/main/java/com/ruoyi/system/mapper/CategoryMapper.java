package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.Category;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 商品类目Mapper接口
 * 
 * @author ruoyi
 * @date 2022-10-27
 */
public interface CategoryMapper 
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
     * 查询商品类目列表
     *
     * @return 商品类目集合
     */
    public List<Category> selectCategoryListJsoup(@RequestParam("list") List<Long> categoryIds);

    /**
     * 新增商品类目
     * 
     * @param category 商品类目
     * @return 结果
     */
    public int insertCategory(Category category);

    /**
     * 修改商品类目
     * 
     * @param category 商品类目
     * @return 结果
     */
    public int updateCategory(Category category);

    /**
     * 删除商品类目
     * 
     * @param id 商品类目主键
     * @return 结果
     */
    public int deleteCategoryById(Long id);

    /**
     * 批量删除商品类目
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCategoryByIds(Long[] ids);

    //根据多语言id和类目id查询多语言值对象
    public Category selectLanguageObject(@Param("languageId")Long languageId,
                                     @Param("categoryId")Long categoryId);

    //变更多语言值
    int changeLanguageValue(Category category);

    //新增多语言值
    int insertLanguageValue(Category category);
}
