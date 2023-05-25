package com.ruoyi.system.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;

/**
 * 店铺的商品信息对象 shop_goods_info
 * 
 * @author ruoyi
 * @date 2022-11-01
 */
public class ShopGoodsInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 店铺id */
    @Excel(name = "店铺id")
    private Long shopId;

    /** 店铺名称 */
    @Excel(name = "店铺名称")
    private String shopName;

    /** 店铺logo */
    @Excel(name = "店铺logo")
    private String shopLogo;

    /** 店铺联系电话 */
    @Excel(name = "店铺联系电话")
    private String shopTel;

    /** 店铺状态 */
    @Excel(name = "店铺状态")
    private Integer shopStatus;

    /** 商品id */
    @Excel(name = "商品id")
    private Long goodsId;

    /** 商品名称 */
    @Excel(name = "商品名称")
    private String goodsName;

    /** 商品图片 */
    @Excel(name = "商品图片")
    private String goodsImg;

    /** 所属类别id(小类) */
    @Excel(name = "所属类别id(小类)")
    private Long minorClass;

    /** 平台价格 */
    @Excel(name = "平台价格")
    private BigDecimal platformPrice;

    /** 平台商品状态 */
    @Excel(name = "平台商品状态")
    private Integer platformGoodsStatus;

    /** 所属类别名称 */
    @Excel(name = "所属类别名称")
    private String categoryName;

    /** 商品详情 */
    @Excel(name = "商品详情")
    private String detail;

    /** 商品介绍 */
    @Excel(name = "商品介绍")
    private String introduce;

    /** 商品单价 */
    @Excel(name = "商品单价")
    private BigDecimal singlePrice;

    /** 优惠价 */
    @Excel(name = "优惠价")
    private BigDecimal discountPrice;

    /** 商品状态 0：上架  1：下架 */
    @Excel(name = "商品状态 0：上架  1：下架")
    private Integer status;

    /** 是否精选商品：0:不是 1:是 */
    @Excel(name = "是否精选商品：0:不是 1:是")
    private Integer isFeatured;

    /** 是否特价商品：0:不是 1:是 */
    @Excel(name = "是否特价商品：0:不是 1:是")
    private Integer isSpecial;

    /** 店铺商家id */
    @Excel(name = "店铺商家id")
    private Long sellerId;

    /** 销量 */
    @Excel(name = "销量")
    private Integer sales;

    public Integer getSales() {
        return sales;
    }

    public void setSales(Integer sales) {
        this.sales = sales;
    }

    public Long getSellerId() {
        return sellerId;
    }

    public Integer getIsSpecial() {
        return isSpecial;
    }

    public void setIsSpecial(Integer isSpecial) {
        this.isSpecial = isSpecial;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }

    public Integer getShopStatus() {
        return shopStatus;
    }

    public Integer getPlatformGoodsStatus() {
        return platformGoodsStatus;
    }

    public void setPlatformGoodsStatus(Integer platformGoodsStatus) {
        this.platformGoodsStatus = platformGoodsStatus;
    }

    public void setShopStatus(Integer shopStatus) {
        this.shopStatus = shopStatus;
    }

    public Integer getIsFeatured() {
        return isFeatured;
    }

    public void setIsFeatured(Integer isFeatured) {
        this.isFeatured = isFeatured;
    }

    public BigDecimal getPlatformPrice() {
        return platformPrice;
    }

    public void setPlatformPrice(BigDecimal platformPrice) {
        this.platformPrice = platformPrice;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopLogo() {
        return shopLogo;
    }

    public void setShopLogo(String shopLogo) {
        this.shopLogo = shopLogo;
    }

    public String getShopTel() {
        return shopTel;
    }

    public void setShopTel(String shopTel) {
        this.shopTel = shopTel;
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

    public Long getMinorClass() {
        return minorClass;
    }

    public void setMinorClass(Long minorClass) {
        this.minorClass = minorClass;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setShopId(Long shopId) 
    {
        this.shopId = shopId;
    }

    public Long getShopId() 
    {
        return shopId;
    }
    public void setGoodsId(Long goodsId) 
    {
        this.goodsId = goodsId;
    }

    public Long getGoodsId() 
    {
        return goodsId;
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
    public void setStatus(Integer status) 
    {
        this.status = status;
    }

    public Integer getStatus() 
    {
        return status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("shopId", getShopId())
            .append("goodsId", getGoodsId())
            .append("singlePrice", getSinglePrice())
            .append("discountPrice", getDiscountPrice())
            .append("status", getStatus())
            .toString();
    }
}
