package com.ruoyi.system.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 商品类目对象 category
 * 
 * @author ruoyi
 * @date 2022-10-27
 */
public class Category extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 类别名称 */
    @Excel(name = "类别名称")
    private String categoryName;

    /** 类别图标 */
    @Excel(name = "类别图标")
    private String img;

    /** 父类id */
    @Excel(name = "父类id")
    private Long parentId;

    /** 类别等级 */
    @Excel(name = "类别等级")
    private Integer categoryLevel;

    /** 排序 */
    @Excel(name = "排序")
    private Integer sort;

    /** 是否显示 0：显示 1：不显示 */
    @Excel(name = "是否显示")
    private Integer isVisible;

    private String searchKeyword;

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setCategoryName(String categoryName) 
    {
        this.categoryName = categoryName;
    }

    public String getCategoryName() 
    {
        return categoryName;
    }
    public void setParentId(Long parentId) 
    {
        this.parentId = parentId;
    }

    public Long getParentId() 
    {
        return parentId;
    }
    public void setCategoryLevel(Integer categoryLevel) 
    {
        this.categoryLevel = categoryLevel;
    }

    public Integer getCategoryLevel() 
    {
        return categoryLevel;
    }
    public void setSort(Integer sort) 
    {
        this.sort = sort;
    }
    public Integer getSort() 
    {
        return sort;
    }

    public Integer getIsVisible() {
        return isVisible;
    }

    public void setIsVisible(Integer isVisible) {
        this.isVisible = isVisible;
    }

    public String getSearchKeyword() {
        return searchKeyword;
    }

    public void setSearchKeyword(String searchKeyword) {
        this.searchKeyword = searchKeyword;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("categoryName", getCategoryName())
            .append("parentId", getParentId())
            .append("categoryLevel", getCategoryLevel())
            .append("sort", getSort())
            .append("searchKeyword", getSearchKeyword())
            .toString();
    }
}
