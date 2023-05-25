package com.ruoyi.system.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 商品品牌对象 goods_brand
 * 
 * @author ruoyi
 * @date 2022-11-27
 */
public class GoodsBrand extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 品牌名称 */
    @Excel(name = "品牌名称")
    private String brandName;

    /** logo */
    @Excel(name = "logo")
    private String brandLogo;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setBrandName(String brandName) 
    {
        this.brandName = brandName;
    }

    public String getBrandName() 
    {
        return brandName;
    }
    public void setBrandLogo(String brandLogo) 
    {
        this.brandLogo = brandLogo;
    }

    public String getBrandLogo() 
    {
        return brandLogo;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("brandName", getBrandName())
            .append("brandLogo", getBrandLogo())
            .toString();
    }
}
