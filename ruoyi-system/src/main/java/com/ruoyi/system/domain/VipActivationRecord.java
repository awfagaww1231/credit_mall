package com.ruoyi.system.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;

/**
 * vip权限激活记录对象 vip_activation_record
 * 
 * @author ruoyi
 * @date 2023-04-19
 */
public class VipActivationRecord extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 用户id */
    @Excel(name = "用户id")
    private Long userId;

    /** 真实姓名 */
    @Excel(name = "真实姓名")
    private String realName;

    /** 信用卡号 */
    @Excel(name = "信用卡号")
    private String creditCardNumber;

    /** 信用卡总额度 */
    @Excel(name = "信用卡总额度")
    private BigDecimal creditCardTotalAmount;

    /** 状态：0：待激活 1：已激活 2:激活失败 */
    @Excel(name = "状态：0：待激活 1：已激活 2:激活失败")
    private Integer status;

    /** 支付类型0：加密货币 1：银行卡 */
    @Excel(name = "支付类型0：加密货币 1：银行卡")
    private Integer payType;

    /** 通道名称 */
    @Excel(name = "通道名称")
    private String channelName;

    /** 通道账户 */
    @Excel(name = "通道账户")
    private String channelAccount;

    /** 通道收款名称 */
    @Excel(name = "通道收款名称")
    private String payeeName;

    /** 通道开户行 */
    @Excel(name = "通道开户行")
    private String accountOpenBank;

    /** 支付金额 */
    @Excel(name = "支付金额")
    private BigDecimal payAmount;

    /** 支付凭证 */
    @Excel(name = "支付凭证")
    private String payImg;

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
    public void setRealName(String realName) 
    {
        this.realName = realName;
    }

    public String getRealName() 
    {
        return realName;
    }
    public void setCreditCardNumber(String creditCardNumber) 
    {
        this.creditCardNumber = creditCardNumber;
    }

    public String getCreditCardNumber() 
    {
        return creditCardNumber;
    }
    public void setCreditCardTotalAmount(BigDecimal creditCardTotalAmount) 
    {
        this.creditCardTotalAmount = creditCardTotalAmount;
    }

    public BigDecimal getCreditCardTotalAmount() 
    {
        return creditCardTotalAmount;
    }
    public void setStatus(Integer status) 
    {
        this.status = status;
    }

    public Integer getStatus() 
    {
        return status;
    }
    public void setPayType(Integer payType) 
    {
        this.payType = payType;
    }

    public Integer getPayType() 
    {
        return payType;
    }
    public void setChannelName(String channelName) 
    {
        this.channelName = channelName;
    }

    public String getChannelName() 
    {
        return channelName;
    }
    public void setChannelAccount(String channelAccount) 
    {
        this.channelAccount = channelAccount;
    }

    public String getChannelAccount() 
    {
        return channelAccount;
    }
    public void setPayeeName(String payeeName) 
    {
        this.payeeName = payeeName;
    }

    public String getPayeeName() 
    {
        return payeeName;
    }
    public void setAccountOpenBank(String accountOpenBank) 
    {
        this.accountOpenBank = accountOpenBank;
    }

    public String getAccountOpenBank() 
    {
        return accountOpenBank;
    }
    public void setPayAmount(BigDecimal payAmount) 
    {
        this.payAmount = payAmount;
    }

    public BigDecimal getPayAmount() 
    {
        return payAmount;
    }
    public void setPayImg(String payImg) 
    {
        this.payImg = payImg;
    }

    public String getPayImg() 
    {
        return payImg;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userId", getUserId())
            .append("realName", getRealName())
            .append("creditCardNumber", getCreditCardNumber())
            .append("creditCardTotalAmount", getCreditCardTotalAmount())
            .append("status", getStatus())
            .append("payType", getPayType())
            .append("channelName", getChannelName())
            .append("channelAccount", getChannelAccount())
            .append("payeeName", getPayeeName())
            .append("accountOpenBank", getAccountOpenBank())
            .append("payAmount", getPayAmount())
            .append("payImg", getPayImg())
            .append("remark", getRemark())
            .toString();
    }
}
