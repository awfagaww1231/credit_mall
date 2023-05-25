package com.ruoyi.system.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * SuperDeals活动分类中的商品信息对象 super_deals_classification_goods
 * 
 * @author ruoyi
 * @date 2022-11-21
 */
public class SuperDealsClassificationGoods extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** SuperDeals活动分类id */
    @Excel(name = "SuperDeals活动分类id")
    private Long classificationId;

    /** 活动分类名称 */
    @Excel(name = "活动分类名称")
    private String classificationName;

    /** 商品名称 */
    @Excel(name = "商品名称")
    private String goodsName;

    /** 商品图片 */
    private String goodsImg;

    /** 店铺商品信息的id */
    @Excel(name = "店铺商品信息的id")
    private Long shopGoodsInfoId;

    /** 店铺名称 */
    @Excel(name = "店铺名称")
    private String shopName;

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getClassificationName() {
        return classificationName;
    }

    public void setClassificationName(String classificationName) {
        this.classificationName = classificationName;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public String getGoodsImg() {
        return goodsImg;
    }

    public void setGoodsImg(String goodsImg) {
        this.goodsImg = goodsImg;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setClassificationId(Long classificationId) 
    {
        this.classificationId = classificationId;
    }

    public Long getClassificationId() 
    {
        return classificationId;
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
            .append("classificationId", getClassificationId())
            .append("shopGoodsInfoId", getShopGoodsInfoId())
            .toString();
    }
}
