package com.ruoyi.system.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;

/**
 * 贷款分期利息配置对象 installment_interest_config
 * 
 * @author ruoyi
 * @date 2023-04-19
 */
public class InstallmentInterestConfig extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 还款期数 */
    @Excel(name = "还款期数")
    private Integer repaymentPeriodNum;

    /** 年利率 */
    @Excel(name = "年利率")
    private BigDecimal annualInterestRate;

    /** 状态0：启用 1：禁用 */
    @Excel(name = "状态0：启用 1：禁用")
    private Integer status;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setRepaymentPeriodNum(Integer repaymentPeriodNum) 
    {
        this.repaymentPeriodNum = repaymentPeriodNum;
    }

    public Integer getRepaymentPeriodNum() 
    {
        return repaymentPeriodNum;
    }
    public void setAnnualInterestRate(BigDecimal annualInterestRate) 
    {
        this.annualInterestRate = annualInterestRate;
    }

    public BigDecimal getAnnualInterestRate() 
    {
        return annualInterestRate;
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
            .append("repaymentPeriodNum", getRepaymentPeriodNum())
            .append("annualInterestRate", getAnnualInterestRate())
            .append("status", getStatus())
            .toString();
    }
}
