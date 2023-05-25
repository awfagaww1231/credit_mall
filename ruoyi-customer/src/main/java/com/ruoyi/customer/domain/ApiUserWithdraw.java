package com.ruoyi.customer.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 提现记录对象 user_withdraw
 * 
 * @author ruoyi
 * @date 2023-02-17
 */
public class ApiUserWithdraw extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 提现订单号 */
    @Excel(name = "提现订单号")
    private String withdrawOrder;

    /** 提现金额 */
    @Excel(name = "提现金额")
    private BigDecimal withdrawAmount;

    /** 手续费 */
    @Excel(name = "手续费")
    private BigDecimal handlingFee;

    /** 到账金额，扣去手续费之后 */
    @Excel(name = "到账金额，扣去手续费之后")
    private BigDecimal arrivalMount;

    /** 提现用户ID */
    @Excel(name = "提现用户ID")
    private Long userId;

    /** 提现地址 */
    @Excel(name = "提现地址")
    private String withdrawUrl;

    /** 提现截图 */
    @Excel(name = "提现截图")
    private String withdrawImg;

    /** 订单状态 0：待审核 1：审核 2：已驳回 */
    @Excel(name = "订单状态 0：待审核 1：审核 2：已驳回")
    private Integer status;

    /** 提现时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "提现时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date withdrawTime;

    /** 审核人 */
    @Excel(name = "审核人")
    private String approveName;

    /** 审核时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "审核时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date approveTime;

    /** 提现密码 */
    private String payPassword;

    public String getPayPassword() {
        return payPassword;
    }

    public void setPayPassword(String payPassword) {
        this.payPassword = payPassword;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setWithdrawOrder(String withdrawOrder) 
    {
        this.withdrawOrder = withdrawOrder;
    }

    public String getWithdrawOrder() 
    {
        return withdrawOrder;
    }
    public void setWithdrawAmount(BigDecimal withdrawAmount) 
    {
        this.withdrawAmount = withdrawAmount;
    }

    public BigDecimal getWithdrawAmount() 
    {
        return withdrawAmount;
    }
    public void setHandlingFee(BigDecimal handlingFee) 
    {
        this.handlingFee = handlingFee;
    }

    public BigDecimal getHandlingFee() 
    {
        return handlingFee;
    }
    public void setArrivalMount(BigDecimal arrivalMount) 
    {
        this.arrivalMount = arrivalMount;
    }

    public BigDecimal getArrivalMount() 
    {
        return arrivalMount;
    }
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setWithdrawUrl(String withdrawUrl) 
    {
        this.withdrawUrl = withdrawUrl;
    }

    public String getWithdrawUrl() 
    {
        return withdrawUrl;
    }
    public void setWithdrawImg(String withdrawImg) 
    {
        this.withdrawImg = withdrawImg;
    }

    public String getWithdrawImg() 
    {
        return withdrawImg;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setWithdrawTime(Date withdrawTime)
    {
        this.withdrawTime = withdrawTime;
    }

    public Date getWithdrawTime() 
    {
        return withdrawTime;
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

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("withdrawOrder", getWithdrawOrder())
            .append("withdrawAmount", getWithdrawAmount())
            .append("handlingFee", getHandlingFee())
            .append("arrivalMount", getArrivalMount())
            .append("userId", getUserId())
            .append("withdrawUrl", getWithdrawUrl())
            .append("withdrawImg", getWithdrawImg())
            .append("status", getStatus())
            .append("withdrawTime", getWithdrawTime())
            .append("approveName", getApproveName())
            .append("approveTime", getApproveTime())
            .toString();
    }
}
