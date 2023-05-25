package com.ruoyi.customer.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 今日热门商品对象 hot_today
 * 
 * @author ruoyi
 * @date 2022-11-23
 */
public class ApiHotToday extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 店铺商品id */
    @Excel(name = "店铺商品id")
    private Long shopGoodsInfoId;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setShopGoodsInfoId(Long shopGoodsInfoId) 
    {
        this.shopGoodsInfoId = shopGoodsInfoId;
    }

    public Long getShopGoodsInfoId() 
    {
        return shopGoodsInfoId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("shopGoodsInfoId", getShopGoodsInfoId())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
