package com.ruoyi.system.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;

/**
 * 用户信用卡申请记录对象 credit_card_apply_record
 * 
 * @author ruoyi
 * @date 2023-03-20
 */
public class CreditCardApplyRecord extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 用户id */
    @Excel(name = "用户id")
    private Long userId;

    /** 真实姓名 */
    @Excel(name = "真实姓名")
    private String realName;

    /** 信用卡号 */
    @Excel(name = "信用卡号")
    private String creditCardNumber;

    /** 信用卡总额度 */
    @Excel(name = "信用卡总额度")
    private BigDecimal creditCardTotalAmount;

    /** 用户申请额度 */
    @Excel(name = "用户申请额度")
    private BigDecimal userApplyAmount;

    /** 状态：0：审核中 1：已通过 2：已驳回 */
    @Excel(name = "状态：0：审核中 1：已通过 2：已驳回")
    private Integer status;

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

    public BigDecimal getUserApplyAmount() {
        return userApplyAmount;
    }

    public void setUserApplyAmount(BigDecimal userApplyAmount) {
        this.userApplyAmount = userApplyAmount;
    }

    public Long getUserId()
    {
        return userId;
    }
    public void setRealName(String realName) 
    {
        this.realName = realName;
    }

    public String getRealName() 
    {
        return realName;
    }
    public void setCreditCardNumber(String creditCardNumber) 
    {
        this.creditCardNumber = creditCardNumber;
    }

    public String getCreditCardNumber() 
    {
        return creditCardNumber;
    }
    public void setCreditCardTotalAmount(BigDecimal creditCardTotalAmount) 
    {
        this.creditCardTotalAmount = creditCardTotalAmount;
    }

    public BigDecimal getCreditCardTotalAmount() 
    {
        return creditCardTotalAmount;
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
            .append("userId", getUserId())
            .append("realName", getRealName())
            .append("creditCardNumber", getCreditCardNumber())
            .append("creditCardTotalAmount", getCreditCardTotalAmount())
            .append("status", getStatus())
            .append("remark", getRemark())
            .toString();
    }
}
