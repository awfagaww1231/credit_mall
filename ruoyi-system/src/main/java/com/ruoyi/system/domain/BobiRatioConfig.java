package com.ruoyi.system.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;

/**
 * 商家进货价波比配置对象 bobi_ratio_config
 * 
 * @author ruoyi
 * @date 2022-11-11
 */
public class BobiRatioConfig extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 商家会员等级 */
    @Excel(name = "商家会员等级")
    private Integer sellerVipLevel;

    /** 波比比率 */
    @Excel(name = "波比比率")
    private BigDecimal ratio;

    /** 到货天数 */
    @Excel(name = "到货天数")
    private Integer days;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setSellerVipLevel(Integer sellerVipLevel) 
    {
        this.sellerVipLevel = sellerVipLevel;
    }

    public Integer getSellerVipLevel() 
    {
        return sellerVipLevel;
    }
    public void setRatio(BigDecimal ratio) 
    {
        this.ratio = ratio;
    }

    public BigDecimal getRatio() 
    {
        return ratio;
    }
    public void setDays(Integer days) 
    {
        this.days = days;
    }

    public Integer getDays() 
    {
        return days;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("sellerVipLevel", getSellerVipLevel())
            .append("ratio", getRatio())
            .append("days", getDays())
            .toString();
    }
}
