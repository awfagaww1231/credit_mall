package com.ruoyi.system.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 卖家的可售库存信息对象 seller_stock
 * 
 * @author ruoyi
 * @date 2022-10-30
 */
public class SellerStock extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 平台商品的id */
    @Excel(name = "平台商品的id")
    private Long goodsId;

    /** 商品名称 */
    @Excel(name = "商品名称")
    private String goodsName;

    /** 商品图片 */
    @Excel(name = "商品图片")
    private String goodsImg;

    /** 所属类别名称 */
    @Excel(name = "所属类别名称")
    private String categoryName;

    /** 商品详情 */
    @Excel(name = "商品详情")
    private String detail;

    /** 商品介绍 */
    @Excel(name = "商品介绍")
    private String introduce;


    /** 所属类别id(小类) */
    @Excel(name = "所属类别id(小类)")
    private Long minorClass;


    /** 剩余数量 */
    @Excel(name = "剩余数量")
    private Integer stockQuantity;

    /** 卖家id */
    @Excel(name = "卖家id")
    private Long sellerId;

    /** 店长账号 */
    @Excel(name = "店长账号")
    private String ownerUserName;

    /** 店长昵称 */
    @Excel(name = "店长昵称")
    private String ownerNickName;

    /** 店长邮箱 */
    @Excel(name = "店长邮箱")
    private String ownerEmail;

    /** 店长手机号 */
    @Excel(name = "店长手机号")
    private String ownerTel;

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

    public String getOwnerUserName() {
        return ownerUserName;
    }

    public void setOwnerUserName(String ownerUserName) {
        this.ownerUserName = ownerUserName;
    }

    public String getOwnerNickName() {
        return ownerNickName;
    }

    public void setOwnerNickName(String ownerNickName) {
        this.ownerNickName = ownerNickName;
    }

    public String getOwnerEmail() {
        return ownerEmail;
    }

    public void setOwnerEmail(String ownerEmail) {
        this.ownerEmail = ownerEmail;
    }

    public String getOwnerTel() {
        return ownerTel;
    }

    public Long getMinorClass() {
        return minorClass;
    }

    public void setMinorClass(Long minorClass) {
        this.minorClass = minorClass;
    }

    public void setOwnerTel(String ownerTel) {
        this.ownerTel = ownerTel;
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
    public void setStockQuantity(Integer stockQuantity) 
    {
        this.stockQuantity = stockQuantity;
    }

    public Integer getStockQuantity() 
    {
        return stockQuantity;
    }
    public void setSellerId(Long sellerId) 
    {
        this.sellerId = sellerId;
    }

    public Long getSellerId() 
    {
        return sellerId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("goodsId", getGoodsId())
            .append("stockQuantity", getStockQuantity())
            .append("sellerId", getSellerId())
            .toString();
    }
}
