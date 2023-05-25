package com.ruoyi.system.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 申请成为商户记录对象 apply_seller_account
 * 
 * @author ruoyi
 * @date 2023-02-17
 */
public class ApplySellerAccount extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 商户账号 */
    @Excel(name = "商户账号")
    private String sellerAccount;

    /** 登陆密码 */
    @Excel(name = "登陆密码")
    private String password;

    /** 审核状态 0：待审核 1：审核通过 2：审核驳回 */
    @Excel(name = "审核状态 0：待审核 1：审核通过 2：审核驳回")
    private Integer status;

    /** 申请凭证 */
    @Excel(name = "申请凭证")
    private String applyImg;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setSellerAccount(String sellerAccount) 
    {
        this.sellerAccount = sellerAccount;
    }

    public String getSellerAccount() 
    {
        return sellerAccount;
    }
    public void setPassword(String password) 
    {
        this.password = password;
    }

    public String getPassword() 
    {
        return password;
    }
    public void setStatus(Integer status) 
    {
        this.status = status;
    }

    public Integer getStatus() 
    {
        return status;
    }
    public void setApplyImg(String applyImg) 
    {
        this.applyImg = applyImg;
    }

    public String getApplyImg() 
    {
        return applyImg;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("sellerAccount", getSellerAccount())
            .append("password", getPassword())
            .append("status", getStatus())
            .append("applyImg", getApplyImg())
            .toString();
    }
}
