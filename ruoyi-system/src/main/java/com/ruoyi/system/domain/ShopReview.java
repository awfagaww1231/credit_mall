package com.ruoyi.system.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 店铺入驻审核对象 shop_review
 * 
 * @author ruoyi
 * @date 2022-10-28
 */
public class ShopReview extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 店铺名称 */
    @Excel(name = "店铺名称")
    private String shopName;

    /** 店铺logo */
    @Excel(name = "店铺logo")
    private String shopLogo;

    /** 店铺联系电话 */
    @Excel(name = "店铺联系电话")
    private String shopTel;

    /** 店铺地址 */
    @Excel(name = "店铺地址")
    private String shopAddress;

    /** 收货地址 */
    @Excel(name = "收货地址")
    private String receiveAddress;

    /** 状态：0：审核中  1：已通过 2：已拒绝 */
    @Excel(name = "状态：0：审核中  1：已通过 2：已拒绝")
    private Integer status;

    /** 店长id */
    @Excel(name = "店长id")
    private Long shopOwnerId;

    /** 店长账号 */
    @Excel(name = "店长账号")
    private String ownerUserName;

    /** 店长昵称 */
    @Excel(name = "店长昵称")
    private String ownerNickName;

    /** 店长邮箱 */
    @Excel(name = "店长邮箱")
    private String ownerEmail;

    /** 店长手机号 */
    @Excel(name = "店长手机号")
    private String ownerTel;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setShopName(String shopName) 
    {
        this.shopName = shopName;
    }

    public String getShopName() 
    {
        return shopName;
    }
    public void setShopLogo(String shopLogo) 
    {
        this.shopLogo = shopLogo;
    }

    public String getShopLogo() 
    {
        return shopLogo;
    }
    public void setShopTel(String shopTel) 
    {
        this.shopTel = shopTel;
    }

    public String getShopTel() 
    {
        return shopTel;
    }
    public void setShopAddress(String shopAddress) 
    {
        this.shopAddress = shopAddress;
    }

    public String getShopAddress() 
    {
        return shopAddress;
    }
    public void setReceiveAddress(String receiveAddress) 
    {
        this.receiveAddress = receiveAddress;
    }

    public String getReceiveAddress() 
    {
        return receiveAddress;
    }
    public void setStatus(Integer status) 
    {
        this.status = status;
    }

    public Integer getStatus() 
    {
        return status;
    }
    public void setShopOwnerId(Long shopOwnerId) 
    {
        this.shopOwnerId = shopOwnerId;
    }

    public Long getShopOwnerId() 
    {
        return shopOwnerId;
    }
    public void setOwnerUserName(String ownerUserName) 
    {
        this.ownerUserName = ownerUserName;
    }

    public String getOwnerUserName() 
    {
        return ownerUserName;
    }
    public void setOwnerNickName(String ownerNickName) 
    {
        this.ownerNickName = ownerNickName;
    }

    public String getOwnerNickName() 
    {
        return ownerNickName;
    }
    public void setOwnerEmail(String ownerEmail) 
    {
        this.ownerEmail = ownerEmail;
    }

    public String getOwnerEmail() 
    {
        return ownerEmail;
    }
    public void setOwnerTel(String ownerTel) 
    {
        this.ownerTel = ownerTel;
    }

    public String getOwnerTel() 
    {
        return ownerTel;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("shopName", getShopName())
            .append("shopLogo", getShopLogo())
            .append("shopTel", getShopTel())
            .append("shopAddress", getShopAddress())
            .append("receiveAddress", getReceiveAddress())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("status", getStatus())
            .append("shopOwnerId", getShopOwnerId())
            .append("ownerUserName", getOwnerUserName())
            .append("ownerNickName", getOwnerNickName())
            .append("ownerEmail", getOwnerEmail())
            .append("ownerTel", getOwnerTel())
            .append("remark", getRemark())
            .toString();
    }
}
