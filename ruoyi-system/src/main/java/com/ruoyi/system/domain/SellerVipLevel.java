package com.ruoyi.system.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 商户会员等级记录对象 seller_vip_level
 * 
 * @author ruoyi
 * @date 2022-11-11
 */
public class SellerVipLevel extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 商户id */
    @Excel(name = "商户id")
    private Long sellerId;

    /** vip等级 */
    @Excel(name = "vip等级")
    private Integer vipLevel;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setSellerId(Long sellerId) 
    {
        this.sellerId = sellerId;
    }

    public Long getSellerId() 
    {
        return sellerId;
    }
    public void setVipLevel(Integer vipLevel) 
    {
        this.vipLevel = vipLevel;
    }

    public Integer getVipLevel() 
    {
        return vipLevel;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("sellerId", getSellerId())
            .append("vipLevel", getVipLevel())
            .toString();
    }
}
