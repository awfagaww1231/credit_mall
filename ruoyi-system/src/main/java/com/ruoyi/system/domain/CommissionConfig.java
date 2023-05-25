package com.ruoyi.system.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;

/**
 * 返佣配置对象 commission_config
 * 
 * @author ruoyi
 * @date 2022-11-14
 */
public class CommissionConfig extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 返佣名称 */
    @Excel(name = "返佣名称")
    private String commissionName;

    /** 分润 */
    @Excel(name = "分润")
    private BigDecimal commissionProfit;

    /** 类型：1：充值返佣 */
    @Excel(name = "类型：1：充值返佣")
    private Integer type;

    /** 返佣等级 */
    @Excel(name = "返佣等级")
    private Integer commissionLevel;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setCommissionName(String commissionName) 
    {
        this.commissionName = commissionName;
    }

    public String getCommissionName() 
    {
        return commissionName;
    }
    public void setCommissionProfit(BigDecimal commissionProfit) 
    {
        this.commissionProfit = commissionProfit;
    }

    public BigDecimal getCommissionProfit() 
    {
        return commissionProfit;
    }
    public void setType(Integer type) 
    {
        this.type = type;
    }

    public Integer getType() 
    {
        return type;
    }
    public void setCommissionLevel(Integer commissionLevel) 
    {
        this.commissionLevel = commissionLevel;
    }

    public Integer getCommissionLevel() 
    {
        return commissionLevel;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("commissionName", getCommissionName())
            .append("commissionProfit", getCommissionProfit())
            .append("type", getType())
            .append("commissionLevel", getCommissionLevel())
            .toString();
    }
}
