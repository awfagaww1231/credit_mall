package com.ruoyi.system.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;
import java.util.Date;

/**
 * C端用户账单明细对象 user_bill_details
 * 
 * @author ruoyi
 * @date 2022-11-10
 */
public class UserBillDetails extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 订单号 */
    @Excel(name = "订单号")
    private String orderCode;

    /** 订单金额 */
    @Excel(name = "订单金额")
    private BigDecimal orderAmount;

    /** 订单收入支出类型0：收入 1：支出 */
    @Excel(name = "订单收入支出类型0：收入 1：支出")
    private Integer orderType;

    /** 订单时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "订单时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date orderTime;

    /** 用户ID */
    @Excel(name = "用户ID")
    private Long userId;

    /** 用户名称 */
    @Excel(name = "用户名称")
    private String userName;

    @Excel(name = "邀请码")
    private String inviteCode;

    @Excel(name = "我邀请的用户")
    private String myInvite;

    /** 用户手机号 */
    @Excel(name = "用户手机号")
    private String mobile;

    /** 订单类型：0:充值收入 1:提现  2:商品订单金额支出 3：商品订单金额退回*/
    @Excel(name = "订单类型：0:充值收入 1:提现  2:商品订单金额支出 3：商品订单金额退回")
    private Integer orderClass;

    /** 订单前的余额 */
    @Excel(name = "订单前的余额")
    private BigDecimal amountBefore;

    /** 订单后的余额 */
    @Excel(name = "订单后的余额")
    private BigDecimal amountAfter;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setOrderCode(String orderCode) 
    {
        this.orderCode = orderCode;
    }

    public String getOrderCode() 
    {
        return orderCode;
    }
    public void setOrderAmount(BigDecimal orderAmount) 
    {
        this.orderAmount = orderAmount;
    }

    public BigDecimal getOrderAmount() 
    {
        return orderAmount;
    }
    public void setOrderType(Integer orderType) 
    {
        this.orderType = orderType;
    }

    public Integer getOrderType() 
    {
        return orderType;
    }
    public void setOrderTime(Date orderTime) 
    {
        this.orderTime = orderTime;
    }

    public Date getOrderTime() 
    {
        return orderTime;
    }
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setOrderClass(Integer orderClass) 
    {
        this.orderClass = orderClass;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Integer getOrderClass()
    {
        return orderClass;
    }
    public void setAmountBefore(BigDecimal amountBefore) 
    {
        this.amountBefore = amountBefore;
    }

    public BigDecimal getAmountBefore() 
    {
        return amountBefore;
    }
    public void setAmountAfter(BigDecimal amountAfter) 
    {
        this.amountAfter = amountAfter;
    }

    public BigDecimal getAmountAfter() 
    {
        return amountAfter;
    }

    public String getInviteCode() {
        return inviteCode;
    }

    public void setInviteCode(String inviteCode) {
        this.inviteCode = inviteCode;
    }

    public String getMyInvite() {
        return myInvite;
    }

    public void setMyInvite(String myInvite) {
        this.myInvite = myInvite;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("orderCode", getOrderCode())
            .append("orderAmount", getOrderAmount())
            .append("orderType", getOrderType())
            .append("orderTime", getOrderTime())
            .append("userId", getUserId())
            .append("orderClass", getOrderClass())
            .append("amountBefore", getAmountBefore())
            .append("amountAfter", getAmountAfter())
            .toString();
    }
}
