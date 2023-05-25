package com.ruoyi.system.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;

/**
 * 商品sku对象 goods_sku
 * 
 * @author ruoyi
 * @date 2022-11-18
 */
public class GoodsSku extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 平台商品id */
    @Excel(name = "平台商品id")
    private Long goodsId;

    /** 规格值数组 */
    @Excel(name = "规格值数组")
    private String properties;

    /** 规格信息 */
    @Excel(name = "规格信息")
    private SpecificationValue specificationValue;

    /** 商品单价 */
    @Excel(name = "商品单价")
    private BigDecimal singlePrice;

    /** 优惠价 */
    @Excel(name = "优惠价")
    private BigDecimal discountPrice;

    /** 库存数量 */
    @Excel(name = "库存数量")
    private Long stockQuantity;

    /** 重量 */
    @Excel(name = "重量")
    private Long weight;

    /** 体积 */
    @Excel(name = "体积")
    private Long volume;

    public SpecificationValue getSpecificationValue() {
        return specificationValue;
    }

    public void setSpecificationValue(SpecificationValue specificationValue) {
        this.specificationValue = specificationValue;
    }

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
    public void setProperties(String properties) 
    {
        this.properties = properties;
    }

    public String getProperties() 
    {
        return properties;
    }
    public void setSinglePrice(BigDecimal singlePrice) 
    {
        this.singlePrice = singlePrice;
    }

    public BigDecimal getSinglePrice() 
    {
        return singlePrice;
    }
    public void setDiscountPrice(BigDecimal discountPrice) 
    {
        this.discountPrice = discountPrice;
    }

    public BigDecimal getDiscountPrice() 
    {
        return discountPrice;
    }
    public void setStockQuantity(Long stockQuantity) 
    {
        this.stockQuantity = stockQuantity;
    }

    public Long getStockQuantity() 
    {
        return stockQuantity;
    }
    public void setWeight(Long weight) 
    {
        this.weight = weight;
    }

    public Long getWeight() 
    {
        return weight;
    }
    public void setVolume(Long volume) 
    {
        this.volume = volume;
    }

    public Long getVolume() 
    {
        return volume;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("goodsId", getGoodsId())
            .append("properties", getProperties())
            .append("singlePrice", getSinglePrice())
            .append("discountPrice", getDiscountPrice())
            .append("stockQuantity", getStockQuantity())
            .append("weight", getWeight())
            .append("volume", getVolume())
            .toString();
    }
}
