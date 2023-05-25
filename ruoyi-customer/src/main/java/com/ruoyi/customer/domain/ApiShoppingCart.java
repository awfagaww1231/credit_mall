package com.ruoyi.customer.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import java.math.BigDecimal;

/**
 * 购物车信息对象 shopping_cart
 * 
 * @author ruoyi
 * @date 2022-11-04
 */
public class ApiShoppingCart extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 用户id */
    @Excel(name = "用户id")
    private Long userId;

    /** 在售商品详情的id */
    @Excel(name = "在售商品详情的id")
    private Long shopGoodsInfoId;

    /** 店铺id */
    @Excel(name = "店铺id")
    private Long shopId;

    /** 店铺名称 */
    @Excel(name = "店铺名称")
    private String shopName;

    /** 店铺logo */
    @Excel(name = "店铺logo")
    private String shopLogo;

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

    /** 购物车数量 */
    @Excel(name = "购物车数量")
    private Integer cartQuantity;

    /** 商品下单的规格 */
    @Excel(name = "商品下单的规格")
    private String specification;

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
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

    public BigDecimal getSinglePrice() {
        return singlePrice;
    }

    public void setSinglePrice(BigDecimal singlePrice) {
        this.singlePrice = singlePrice;
    }

    public BigDecimal getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(BigDecimal discountPrice) {
        this.discountPrice = discountPrice;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setShopGoodsInfoId(Long shopGoodsInfoId) 
    {
        this.shopGoodsInfoId = shopGoodsInfoId;
    }

    public Long getShopGoodsInfoId() 
    {
        return shopGoodsInfoId;
    }
    public void setCartQuantity(Integer cartQuantity) 
    {
        this.cartQuantity = cartQuantity;
    }

    public Integer getCartQuantity() 
    {
        return cartQuantity;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
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

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userId", getUserId())
            .append("shopGoodsInfoId", getShopGoodsInfoId())
            .append("cartQuantity", getCartQuantity())
            .toString();
    }
}
