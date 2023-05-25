package com.ruoyi.system.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 商品图片解析库对象 goods_img_stock
 * 
 * @author ruoyi
 * @date 2023-01-04
 */
public class GoodsImgStock extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 商品图片 */
    @Excel(name = "商品图片")
    private String goodsImg;

    /** 批次 */
    @Excel(name = "批次")
    private String batch;

    /** 是否对应商品 */
    @Excel(name = "是否对应商品")
    private Integer isUse;

    //平台商品id
    private Long goodsId;

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setGoodsImg(String goodsImg) 
    {
        this.goodsImg = goodsImg;
    }

    public String getGoodsImg() 
    {
        return goodsImg;
    }
    public void setBatch(String batch) 
    {
        this.batch = batch;
    }

    public String getBatch() 
    {
        return batch;
    }
    public void setIsUse(Integer isUse) 
    {
        this.isUse = isUse;
    }

    public Integer getIsUse() 
    {
        return isUse;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("goodsImg", getGoodsImg())
            .append("batch", getBatch())
            .append("isUse", getIsUse())
            .toString();
    }
}
