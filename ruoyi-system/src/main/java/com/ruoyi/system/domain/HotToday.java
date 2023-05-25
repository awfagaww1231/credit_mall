package com.ruoyi.system.domain;

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
public class HotToday extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 店铺商品id */
    @Excel(name = "店铺商品id")
    private Long shopGoodsInfoId;


    /** 商品名称 */
    @Excel(name = "商品名称")
    private String goodsName;

    /** 商品图片 */
    private String goodsImg;

    /** 店铺名称 */
    @Excel(name = "店铺名称")
    private String shopName;

    /** 排序 */
    @Excel(name = "排序")
    private Integer sort;

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsImg() {
        return goodsImg;
    }

    public void setGoodsImg(String goodsImg) {
        this.goodsImg = goodsImg;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

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
