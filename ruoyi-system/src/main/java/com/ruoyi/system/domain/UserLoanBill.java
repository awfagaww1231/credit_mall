package com.ruoyi.system.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 用户贷款还款账单对象 user_loan_bill
 * 
 * @author ruoyi
 * @date 2023-04-28
 */
public class UserLoanBill extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 用户id */
    @Excel(name = "用户id")
    private Long userId;

    /** 每期总还款 */
    @Excel(name = "每期总还款")
    private BigDecimal repaymentTotalAmountEveryPeriod;

    /** 本期已还款金额 */
    @Excel(name = "本期已还款金额")
    private BigDecimal repaidAmountThisPeriod;

    /** 还款状态：0:待结清 1：已结清 2：已逾期 */
    @Excel(name = "还款状态：0:待结清 1：已结清 2：已逾期")
    private Integer repaymentStatus;

    /** 用户贷款记录id */
    @Excel(name = "用户贷款记录id")
    private Long userLoanRecordId;

    /** 账单时间 */
    @Excel(name = "账单时间")
    @JsonFormat(pattern = "yyyy-MM")
    private Date billTime;

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
    public void setRepaymentTotalAmountEveryPeriod(BigDecimal repaymentTotalAmountEveryPeriod) 
    {
        this.repaymentTotalAmountEveryPeriod = repaymentTotalAmountEveryPeriod;
    }

    public BigDecimal getRepaymentTotalAmountEveryPeriod() 
    {
        return repaymentTotalAmountEveryPeriod;
    }
    public void setRepaidAmountThisPeriod(BigDecimal repaidAmountThisPeriod) 
    {
        this.repaidAmountThisPeriod = repaidAmountThisPeriod;
    }

    public BigDecimal getRepaidAmountThisPeriod() 
    {
        return repaidAmountThisPeriod;
    }
    public void setRepaymentStatus(Integer repaymentStatus) 
    {
        this.repaymentStatus = repaymentStatus;
    }

    public Integer getRepaymentStatus() 
    {
        return repaymentStatus;
    }
    public void setUserLoanRecordId(Long userLoanRecordId) 
    {
        this.userLoanRecordId = userLoanRecordId;
    }

    public Long getUserLoanRecordId() 
    {
        return userLoanRecordId;
    }

    public Date getBillTime() {
        return billTime;
    }

    public void setBillTime(Date billTime) {
        this.billTime = billTime;
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userId", getUserId())
            .append("repaymentTotalAmountEveryPeriod", getRepaymentTotalAmountEveryPeriod())
            .append("repaidAmountThisPeriod", getRepaidAmountThisPeriod())
            .append("repaymentStatus", getRepaymentStatus())
            .append("userLoanRecordId", getUserLoanRecordId())
            .append("billTime", getBillTime())
            .toString();
    }
}
