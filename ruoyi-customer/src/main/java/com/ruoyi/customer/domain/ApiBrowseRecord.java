package com.ruoyi.customer.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 用户浏览足迹记录对象 browse_record
 * 
 * @author ruoyi
 * @date 2022-11-07
 */
public class ApiBrowseRecord extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 用户id */
    @Excel(name = "用户id")
    private Long userId;

    /** 商品单价（需支付价格） */
    @Excel(name = "单价", readConverterExp = "需=支付价格")
    private BigDecimal singlePrice;

    /** 商品图片 */
    @Excel(name = "商品图片")
    private String goodsImg;

    /** 店铺商品信息id */
    @Excel(name = "店铺商品信息id")
    private Long shopGoodsInfoId;

    /** 浏览时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "浏览时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date browseTime;

    /** 筛选开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date startTime;

    /** 筛选结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date endTime;

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public BigDecimal getSinglePrice() {
        return singlePrice;
    }

    public void setSinglePrice(BigDecimal singlePrice) {
        this.singlePrice = singlePrice;
    }

    public String getGoodsImg() {
        return goodsImg;
    }

    public void setGoodsImg(String goodsImg) {
        this.goodsImg = goodsImg;
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
    public void setBrowseTime(Date browseTime) 
    {
        this.browseTime = browseTime;
    }

    public Date getBrowseTime() 
    {
        return browseTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userId", getUserId())
            .append("shopGoodsInfoId", getShopGoodsInfoId())
            .append("browseTime", getBrowseTime())
            .toString();
    }
}
