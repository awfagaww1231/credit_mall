package com.ruoyi.customer.domain;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.common.utils.x.lang.LangUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 店铺信息对象 shop_info
 * 
 * @author ruoyi
 * @date 2022-10-28
 */
public class ApiShopInfo extends BaseEntity
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

    /** 状态： 0：营业中  1：停业中 */
    @Excel(name = "状态： 0：营业中  1：停业中")
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


    @Excel(name = "店铺名称多语言")
    private String shopNameLang;

    /** 店长手机号 */
    @Excel(name = "店铺地址多语言")
    private String shopAddressLang;


    //关注状态 0:关注 1:未关注
    private Integer followStatus;

    //关注人数
    private Integer followNum;

    public Integer getFollowNum() {
        return followNum;
    }

    public void setFollowNum(Integer followNum) {
        this.followNum = followNum;
    }

    public Integer getFollowStatus() {
        return followStatus;
    }

    public void setFollowStatus(Integer followStatus) {
        this.followStatus = followStatus;
    }

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
        String lang = LangUtils.getLang();
        if (getShopNameLang() != null){
            try{
                JSONObject parse = JSON.parseObject(getShopNameLang());
                String langName = parse.getString(lang);
                if (langName != null){
                    return langName;
                }
            }catch (Exception e){

            }
        }
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
        String lang = LangUtils.getLang();
        if (getShopAddressLang() != null){
            try{
                JSONObject parse = JSONObject.parseObject(getShopAddressLang());
                String langName = parse.getString(lang);
                if (langName != null){
                    return langName;
                }
            }catch (Exception e){

            }
        }
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

    public String getShopNameLang() {
        return shopNameLang;
    }

    public void setShopNameLang(String shopNameLang) {
        this.shopNameLang = shopNameLang;
    }

    public String getShopAddressLang() {
        return shopAddressLang;
    }

    public void setShopAddressLang(String shopAddressLang) {
        this.shopAddressLang = shopAddressLang;
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
            .toString();
    }
}
