package com.ruoyi.system.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;
import java.util.List;

/**
 * 平台商品信息(平台的)对象 platform_goods_info
 * 
 * @author ruoyi
 * @date 2022-10-27
 */
public class PlatformGoodsInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 商品id */
    @Excel(name = "商品id")
    private Long id;

    /** 商品名称 */
    @Excel(name = "商品名称")
    private String goodsName;

    /** 商品图片 */
    private String goodsImg;

    /** 图片集合 */
    private String imgList;

    /** 商品价格 */
    @Excel(name = "商品价格")
    private BigDecimal price;

    /** 商品优惠价 */
    @Excel(name = "商品优惠价")
    private BigDecimal discountPrice;

    /** 所属类别id(小类) */
    private Long minorClass;

    /** 所属类别名称 */
    @Excel(name = "所属类别")
    private String categoryName;

    /** 商品库存数量 */
    @Excel(name = "商品数量")
    private Long goodsInventory;

    /** 商品详情 */
    @Excel(name = "商品详情")
    private String detail;

    /** 商品介绍 */
    @Excel(name = "商品介绍")
    private String introduce;

    /** 平台商品上下架状态 0：上架 1：下架 */
    private Integer status;

    /** 逻辑删除标志 0：未删除 1：已删除 */
    private Integer isDel;

    private String itemCode;

    private String itemUrl;

    /** 商品规格 */
    private List<SpecificationConfig> specificationConfigs;

    public List<SpecificationConfig> getSpecificationConfigs() {
        return specificationConfigs;
    }

    public void setSpecificationConfigs(List<SpecificationConfig> specificationConfigs) {
        this.specificationConfigs = specificationConfigs;
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
    public void setGoodsName(String goodsName) 
    {
        this.goodsName = goodsName;
    }

    public String getGoodsName() 
    {
        return goodsName;
    }
    public void setGoodsImg(String goodsImg) 
    {
        this.goodsImg = goodsImg;
    }

    public String getGoodsImg() 
    {
        return goodsImg;
    }
    public void setPrice(BigDecimal price)
    {
        this.price = price;
    }

    public String getImgList() {
        return imgList;
    }

    public void setImgList(String imgList) {
        this.imgList = imgList;
    }

    public BigDecimal getPrice()
    {
        return price;
    }
    public void setMinorClass(Long minorClass) 
    {
        this.minorClass = minorClass;
    }

    public Long getMinorClass() 
    {
        return minorClass;
    }
    public void setGoodsInventory(Long goodsInventory) 
    {
        this.goodsInventory = goodsInventory;
    }

    public Long getGoodsInventory() 
    {
        return goodsInventory;
    }
    public void setDetail(String detail) 
    {
        this.detail = detail;
    }

    public String getDetail() 
    {
        return detail;
    }
    public void setIntroduce(String introduce) 
    {
        this.introduce = introduce;
    }

    public String getIntroduce() 
    {
        return introduce;
    }
    public void setStatus(Integer status) 
    {
        this.status = status;
    }

    public Integer getStatus() 
    {
        return status;
    }
    public void setIsDel(Integer isDel) 
    {
        this.isDel = isDel;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Integer getIsDel()
    {
        return isDel;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getItemUrl() {
        return itemUrl;
    }

    public void setItemUrl(String itemUrl) {
        this.itemUrl = itemUrl;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("goodsName", getGoodsName())
            .append("goodsImg", getGoodsImg())
            .append("price", getPrice())
            .append("minorClass", getMinorClass())
            .append("goodsInventory", getGoodsInventory())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("detail", getDetail())
            .append("introduce", getIntroduce())
            .append("status", getStatus())
            .append("isDel", getIsDel())
            .toString();
    }
}
