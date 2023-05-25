package com.ruoyi.customer.domain;

import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 用户银行对象 user_bank
 * 
 * @author ruoyi
 * @date 2023-02-16
 */
public class ApiUserBank extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /**用户id**/
    private Long userId;

    /**银行名称**/
    private String bankName;

    /**银行卡号**/
    private String bankNo;

    /**开户地址**/
    private String bankAddress;

    /**银行卡图片**/
    private String bankImg;

    /**绑定手机**/
    private String bankPhone;

    /**添加时间**/
    private Date addTime;

    //持有人
    private String accountHolder;

    //银行身份代码
    private String swiftCode;

    //路由编号
    private String routingNumber;

    public String getRoutingNumber() {
        return routingNumber;
    }

    public void setRoutingNumber(String routingNumber) {
        this.routingNumber = routingNumber;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public void setAccountHolder(String accountHolder) {
        this.accountHolder = accountHolder;
    }

    public String getSwiftCode() {
        return swiftCode;
    }

    public void setSwiftCode(String swiftCode) {
        this.swiftCode = swiftCode;
    }

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
    public void setBankName(String bankName) 
    {
        this.bankName = bankName;
    }

    public String getBankName() 
    {
        return bankName;
    }
    public void setBankNo(String bankNo) 
    {
        this.bankNo = bankNo;
    }

    public String getBankNo() 
    {
        return bankNo;
    }
    public void setBankAddress(String bankAddress) 
    {
        this.bankAddress = bankAddress;
    }

    public String getBankAddress() 
    {
        return bankAddress;
    }
    public void setBankImg(String bankImg) 
    {
        this.bankImg = bankImg;
    }

    public String getBankImg() 
    {
        return bankImg;
    }
    public void setBankPhone(String bankPhone) 
    {
        this.bankPhone = bankPhone;
    }

    public String getBankPhone() 
    {
        return bankPhone;
    }
    public void setAddTime(Date addTime) 
    {
        this.addTime = addTime;
    }

    public Date getAddTime() 
    {
        return addTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userId", getUserId())
            .append("bankName", getBankName())
            .append("bankNo", getBankNo())
            .append("bankAddress", getBankAddress())
            .append("bankImg", getBankImg())
            .append("bankPhone", getBankPhone())
            .append("addTime", getAddTime())
            .toString();
    }
}
