package com.ruoyi.customer.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 用户收货地址信息对象 user_receive_address
 * 
 * @author ruoyi
 * @date 2022-11-10
 */
public class ApiUserReceiveAddress extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 用户id */
    @Excel(name = "用户id")
    private Long userId;

    /** 收货人名称 */
    @Excel(name = "收货人名称")
    private String receiverName;

    /** 收货人手机号 */
    @Excel(name = "收货人手机号")
    private String receiverMobile;

    /** 收货详细地址 */
    @Excel(name = "收货详细地址")
    private String receiverAddress;

    /** 所在地区 */
    @Excel(name = "所在地区")
    private String receiverArea;

    /** 是否默认收货地址 ：0：默认 1：非默认 */
    @Excel(name = "是否默认收货地址 ：0：默认 1：非默认")
    private Integer isDefault;

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
    public void setReceiverName(String receiverName) 
    {
        this.receiverName = receiverName;
    }

    public String getReceiverName() 
    {
        return receiverName;
    }
    public void setReceiverMobile(String receiverMobile) 
    {
        this.receiverMobile = receiverMobile;
    }

    public String getReceiverMobile() 
    {
        return receiverMobile;
    }
    public void setReceiverAddress(String receiverAddress) 
    {
        this.receiverAddress = receiverAddress;
    }

    public String getReceiverAddress() 
    {
        return receiverAddress;
    }
    public void setReceiverArea(String receiverArea) 
    {
        this.receiverArea = receiverArea;
    }

    public String getReceiverArea() 
    {
        return receiverArea;
    }
    public void setIsDefault(Integer isDefault) 
    {
        this.isDefault = isDefault;
    }

    public Integer getIsDefault() 
    {
        return isDefault;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userId", getUserId())
            .append("receiverName", getReceiverName())
            .append("receiverMobile", getReceiverMobile())
            .append("receiverAddress", getReceiverAddress())
            .append("receiverArea", getReceiverArea())
            .append("isDefault", getIsDefault())
            .toString();
    }
}
