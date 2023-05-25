package com.ruoyi.customer.domain;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.common.utils.x.lang.LangUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;
import java.util.List;

public class ApiGoodsInfo extends BaseEntity {


    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 店铺id */
    @Excel(name = "店铺id")
    private Long shopId;

    /** 店铺名称 */
    @Excel(name = "店铺名称")
    private String shopName;

    /** 店铺名称 */
    @Excel(name = "店铺名称多语言")
    private String shopNameLang;

    /** 店铺logo */
    @Excel(name = "店铺logo")
    private String shopLogo;

    /** 店铺联系电话 */
    @Excel(name = "店铺联系电话")
    private String shopTel;

    /** 商品id */
    @Excel(name = "商品id")
    private Long goodsId;

    /** 商品名称 */
    @Excel(name = "商品名称")
    private String goodsName;

    /** 商品图片 */
    @Excel(name = "商品图片")
    private String goodsImg;

    /** 图片集合 */
    private String imgList;

    /** 所属类别id(小类) */
    @Excel(name = "所属类别id(小类)")
    private Long minorClass;

    /** 平台价格 */
    @Excel(name = "平台价格")
    private BigDecimal platformPrice;

    /** 所属类别名称 */
    @Excel(name = "所属类别名称")
    private String categoryName;

    /** 商品详情 */
    @Excel(name = "商品详情")
    private String detail;

    /** 商品介绍 */
    @Excel(name = "商品介绍")
    private String introduce;

    /** 平台商品的状态 */
    @Excel(name = "平台商品的状态")
    private Integer platformGoodsStatus;

    /** 商品单价 */
    @Excel(name = "商品单价")
    private BigDecimal singlePrice;

    /** 优惠价 */
    @Excel(name = "优惠价")
    private BigDecimal discountPrice;

    /** 商品状态 0：上架  1：下架 */
    @Excel(name = "商品状态 0：上架  1：下架")
    private Integer status;

    /** 商品销量 */
    @Excel(name = "商品销量")
    private Integer sales;

    /** 品牌id */
    @Excel(name = "品牌id")
    private List<Long> brandIds;

    /** 商品规格 */
    private List<ApiSpecificationConfig> specificationConfigs;

    public List<ApiSpecificationConfig> getSpecificationConfigs() {
        return specificationConfigs;
    }

    public List<Long> getBrandIds() {
        return brandIds;
    }

    public void setBrandIds(List<Long> brandIds) {
        this.brandIds = brandIds;
    }

    public void setSpecificationConfigs(List<ApiSpecificationConfig> specificationConfigs) {
        this.specificationConfigs = specificationConfigs;
    }

    public Integer getSales() {
        return sales;
    }

    public void setSales(Integer sales) {
        this.sales = sales;
    }

    public String getImgList() {
        return imgList;
    }

    public void setImgList(String imgList) {
        this.imgList = imgList;
    }

    public BigDecimal getPlatformPrice() {
        return platformPrice;
    }

    public void setPlatformPrice(BigDecimal platformPrice) {
        this.platformPrice = platformPrice;
    }

    public String getShopName() {
        String lang = LangUtils.getLang();
        if (getShopNameLang() != null){
            try{
                JSONObject parse = JSON.parseObject(getShopNameLang());
                String langName = parse.getString(lang);
                if (langName != null){
                    return langName;
                }
            }catch (Exception e){

            }
        }
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopNameLang() {
        return shopNameLang;
    }

    public void setShopNameLang(String shopNameLang) {
        this.shopNameLang = shopNameLang;
    }

    public String getShopLogo() {
        return shopLogo;
    }

    public Integer getPlatformGoodsStatus() {
        return platformGoodsStatus;
    }

    public void setPlatformGoodsStatus(Integer platformGoodsStatus) {
        this.platformGoodsStatus = platformGoodsStatus;
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
