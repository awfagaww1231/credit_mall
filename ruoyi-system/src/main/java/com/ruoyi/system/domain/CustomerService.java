package com.ruoyi.system.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 客服配置对象 customer_service
 * 
 * @author ruoyi
 * @date 2023-03-22
 */
public class CustomerService extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 客服名称 */
    @Excel(name = "客服名称")
    private String customerServiceName;

    /** 客服链接 */
    @Excel(name = "客服链接")
    private String customerServiceLine;

    /** 状态：0：启用 1：禁用 */
    @Excel(name = "状态：0：启用 1：禁用")
    private Integer status;

    /** 多语言key */
    @Excel(name = "多语言key")
    private String langKey;

    @Excel(name = "邀请码")
    private String inviteCode;



    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setCustomerServiceName(String customerServiceName) 
    {
        this.customerServiceName = customerServiceName;
    }

    public String getCustomerServiceName() 
    {
        return customerServiceName;
    }
    public void setCustomerServiceLine(String customerServiceLine) 
    {
        this.customerServiceLine = customerServiceLine;
    }

    public String getCustomerServiceLine() 
    {
        return customerServiceLine;
    }
    public void setStatus(Integer status) 
    {
        this.status = status;
    }

    public Integer getStatus() 
    {
        return status;
    }
    public void setLangKey(String langKey) 
    {
        this.langKey = langKey;
    }

    public String getLangKey() 
    {
        return langKey;
    }

    public String getInviteCode() {
        return inviteCode;
    }

    public void setInviteCode(String inviteCode) {
        this.inviteCode = inviteCode;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("customerServiceName", getCustomerServiceName())
            .append("customerServiceLine", getCustomerServiceLine())
            .append("status", getStatus())
            .append("langKey", getLangKey())
            .append("inviteCode", getInviteCode())
            .toString();
    }
}
