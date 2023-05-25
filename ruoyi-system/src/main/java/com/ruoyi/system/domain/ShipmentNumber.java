package com.ruoyi.system.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 店铺订单物流信息对象 shipment_number
 * 
 * @author ruoyi
 * @date 2022-11-02
 */
public class ShipmentNumber extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 物流订单号 */
    @Excel(name = "物流订单号")
    private String shipmentnumber;

    /** 店铺订单id */
    @Excel(name = "店铺订单id")
    private Long shopOrderId;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setShipmentnumber(String shipmentnumber) 
    {
        this.shipmentnumber = shipmentnumber;
    }

    public String getShipmentnumber() 
    {
        return shipmentnumber;
    }
    public void setShopOrderId(Long shopOrderId) 
    {
        this.shopOrderId = shopOrderId;
    }

    public Long getShopOrderId() 
    {
        return shopOrderId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("shipmentnumber", getShipmentnumber())
            .append("shopOrderId", getShopOrderId())
            .toString();
    }
}
