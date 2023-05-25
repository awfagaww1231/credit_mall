package com.ruoyi.customer.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 用户充值订单对象 user_recharge_order
 * 
 * @author ruoyi
 * @date 2022-11-08
 */
public class ApiUserRechargeOrder extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 订单编号 */
    @Excel(name = "订单编号")
    private String orderCode;

    /** 充币地址 */
    @Excel(name = "充币地址")
    private String rechargeUrl;

    /** 充值金额 */
    @Excel(name = "充值金额")
    private BigDecimal amount;

    /** 凭证信息地址 */
    @Excel(name = "凭证信息地址")
    private String imgUrl;

    /** 会员id */
    @Excel(name = "会员id")
    private Long userId;

    /** 订单状态0：待支付 1：已支付 2：已驳回 3：已通过 */
    @Excel(name = "订单状态0：待支付 1：已支付 2：已驳回 3：已通过")
    private Integer orderStatus;

    /** 审核人名字 */
    @Excel(name = "审核人名字")
    private String approveName;

    /** 审批时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "审批时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date approveTime;

    /** 二维码图片地址 */
    @Excel(name = "二维码图片地址")
    private String rechargeImg;

    /** 充值方法：0：线上充值 1：线下充值 */
    @Excel(name = "充值方法：0：线上充值 1：线下充值")
    private Integer rechargeMethod;

    //充值通道id
    private Long platformPaymentChannelId;

    /** 支付类型0：加密货币 1：银行卡 */
    @Excel(name = "支付类型0：加密货币 1：银行卡")
    private Integer payType;

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public Long getPlatformPaymentChannelId() {
        return platformPaymentChannelId;
    }

    public void setPlatformPaymentChannelId(Long platformPaymentChannelId) {
        this.platformPaymentChannelId = platformPaymentChannelId;
    }

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
    public void setRechargeUrl(String rechargeUrl) 
    {
        this.rechargeUrl = rechargeUrl;
    }

    public String getRechargeUrl() 
    {
        return rechargeUrl;
    }
    public void setAmount(BigDecimal amount) 
    {
        this.amount = amount;
    }

    public BigDecimal getAmount() 
    {
        return amount;
    }
    public void setImgUrl(String imgUrl) 
    {
        this.imgUrl = imgUrl;
    }

    public String getImgUrl() 
    {
        return imgUrl;
    }
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public void setApproveName(String approveName)
    {
        this.approveName = approveName;
    }

    public String getApproveName() 
    {
        return approveName;
    }
    public void setApproveTime(Date approveTime) 
    {
        this.approveTime = approveTime;
    }

    public Date getApproveTime() 
    {
        return approveTime;
    }
    public void setRechargeImg(String rechargeImg) 
    {
        this.rechargeImg = rechargeImg;
    }

    public String getRechargeImg() 
    {
        return rechargeImg;
    }

    public Integer getRechargeMethod() {
        return rechargeMethod;
    }

    public void setRechargeMethod(Integer rechargeMethod) {
        this.rechargeMethod = rechargeMethod;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("orderCode", getOrderCode())
            .append("rechargeUrl", getRechargeUrl())
            .append("amount", getAmount())
            .append("remark", getRemark())
            .append("imgUrl", getImgUrl())
            .append("userId", getUserId())
            .append("orderStatus", getOrderStatus())
            .append("createTime", getCreateTime())
            .append("approveName", getApproveName())
            .append("approveTime", getApproveTime())
            .append("rechargeImg", getRechargeImg())
            .append("rechargeMethod", getRechargeMethod())
            .toString();
    }
}
