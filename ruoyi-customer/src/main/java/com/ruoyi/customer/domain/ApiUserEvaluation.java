package com.ruoyi.customer.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 用户评价信息对象 user_evaluation
 * 
 * @author ruoyi
 * @date 2022-11-07
 */
public class ApiUserEvaluation extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 店铺订单信息id */
    @Excel(name = "店铺订单信息id")
    private Long shopOrderId;

    /** 评价内容 */
    @Excel(name = "评价内容")
    private String content;

    /** 店铺商品信息id */
    @Excel(name = "店铺商品信息id")
    private Long shopGoodsInfoId;

    /** 店铺id */
    @Excel(name = "店铺id")
    private Long shopId;

    /** 评分 */
    @Excel(name = "评分")
    private Integer score;

    /** 评价人id */
    @Excel(name = "评价人id")
    private Long userId;

    /** 评价人名称 */
    @Excel(name = "评价人名称")
    private String userName;

    /** 评价人头像 */
    @Excel(name = "评价人头像")
    private String avatar;

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setShopOrderId(Long shopOrderId) 
    {
        this.shopOrderId = shopOrderId;
    }

    public Long getShopOrderId() 
    {
        return shopOrderId;
    }
    public void setContent(String content) 
    {
        this.content = content;
    }

    public String getContent() 
    {
        return content;
    }
    public void setShopGoodsInfoId(Long shopGoodsInfoId) 
    {
        this.shopGoodsInfoId = shopGoodsInfoId;
    }

    public Long getShopGoodsInfoId() 
    {
        return shopGoodsInfoId;
    }
    public void setScore(Integer score) 
    {
        this.score = score;
    }

    public Integer getScore() 
    {
        return score;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("shopOrderId", getShopOrderId())
            .append("content", getContent())
            .append("createTime", getCreateTime())
            .append("shopGoodsInfoId", getShopGoodsInfoId())
            .append("score", getScore())
            .toString();
    }
}
