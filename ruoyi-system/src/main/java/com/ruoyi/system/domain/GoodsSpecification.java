package com.ruoyi.system.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 商品对应的规格绑定信息对象 goods_specification
 * 
 * @author ruoyi
 * @date 2022-11-24
 */
public class GoodsSpecification extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 平台商品id */
    @Excel(name = "平台商品id")
    private Long goodsId;

    /** 规格配置id */
    @Excel(name = "规格配置id")
    private Long specificationConfigId;

    /** 规格值id */
    @Excel(name = "规格值id")
    private Long specificationValueId;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setGoodsId(Long goodsId) 
    {
        this.goodsId = goodsId;
    }

    public Long getGoodsId() 
    {
        return goodsId;
    }
    public void setSpecificationConfigId(Long specificationConfigId) 
    {
        this.specificationConfigId = specificationConfigId;
    }

    public Long getSpecificationConfigId() 
    {
        return specificationConfigId;
    }
    public void setSpecificationValueId(Long specificationValueId) 
    {
        this.specificationValueId = specificationValueId;
    }

    public Long getSpecificationValueId() 
    {
        return specificationValueId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("goodsId", getGoodsId())
            .append("specificationConfigId", getSpecificationConfigId())
            .append("specificationValueId", getSpecificationValueId())
            .toString();
    }
}
