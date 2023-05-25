package com.ruoyi.system.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;
import java.util.List;

/**
 * 商家会员等级条件配置信息对象 seller_vip_config
 * 
 * @author ruoyi
 * @date 2022-11-11
 */
public class SellerVipConfig extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 对应等级 */
    @Excel(name = "对应等级")
    private Integer vipLevel;

    /** 升级所需充值金额 */
    @Excel(name = "升级所需充值金额")
    private BigDecimal rechargeAmout;

    /** 发展下线人数 */
    @Excel(name = "发展下线人数")
    private Integer developedNumber;

    //绑定的波比信息
    private List<BobiRatioConfig> bobiRatioConfig;

    public List<BobiRatioConfig> getBobiRatioConfig() {
        return bobiRatioConfig;
    }

    public void setBobiRatioConfig(List<BobiRatioConfig> bobiRatioConfig) {
        this.bobiRatioConfig = bobiRatioConfig;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setVipLevel(Integer vipLevel) 
    {
        this.vipLevel = vipLevel;
    }

    public Integer getVipLevel() 
    {
        return vipLevel;
    }
    public void setRechargeAmout(BigDecimal rechargeAmout) 
    {
        this.rechargeAmout = rechargeAmout;
    }

    public BigDecimal getRechargeAmout() 
    {
        return rechargeAmout;
    }
    public void setDevelopedNumber(Integer developedNumber) 
    {
        this.developedNumber = developedNumber;
    }

    public Integer getDevelopedNumber() 
    {
        return developedNumber;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("vipLevel", getVipLevel())
            .append("rechargeAmout", getRechargeAmout())
            .append("developedNumber", getDevelopedNumber())
            .toString();
    }
}
