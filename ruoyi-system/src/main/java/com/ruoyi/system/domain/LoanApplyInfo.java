package com.ruoyi.system.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;

/**
 * 贷款申请资料对象 loan_apply_info
 * 
 * @author ruoyi
 * @date 2023-04-03
 */
public class LoanApplyInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 用户id */
    @Excel(name = "用户id")
    private Long userId;

    /** 姓 */
    @Excel(name = "姓")
    private String firstName;

    /** 名 */
    @Excel(name = "名")
    private String lastName;

    /** 生日 */
    @Excel(name = "生日")
    private String birthDay;

    /** 住址 */
    @Excel(name = "住址")
    private String address;

    /** 城市 */
    @Excel(name = "城市")
    private String city;

    /** 州 */
    @Excel(name = "州")
    private String state;

    /** 邮政编码 */
    @Excel(name = "邮政编码")
    private String zipCode;

    /** 电话号码 */
    @Excel(name = "电话号码")
    private String phoneNumber;

    /** 年收入 */
    @Excel(name = "年收入")
    private BigDecimal yearlyIncome;

    /** 社会安全号码 */
    @Excel(name = "社会安全号码")
    private String socialSecurityNumber;

    //用户申请额度
    private BigDecimal userApplyAmount;

    public BigDecimal getUserApplyAmount() {
        return userApplyAmount;
    }

    public void setUserApplyAmount(BigDecimal userApplyAmount) {
        this.userApplyAmount = userApplyAmount;
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
    public void setFirstName(String firstName) 
    {
        this.firstName = firstName;
    }

    public String getFirstName() 
    {
        return firstName;
    }
    public void setLastName(String lastName) 
    {
        this.lastName = lastName;
    }

    public String getLastName() 
    {
        return lastName;
    }
    public void setBirthDay(String birthDay) 
    {
        this.birthDay = birthDay;
    }

    public String getBirthDay() 
    {
        return birthDay;
    }
    public void setAddress(String address) 
    {
        this.address = address;
    }

    public String getAddress() 
    {
        return address;
    }
    public void setCity(String city) 
    {
        this.city = city;
    }

    public String getCity() 
    {
        return city;
    }
    public void setState(String state) 
    {
        this.state = state;
    }

    public String getState() 
    {
        return state;
    }
    public void setZipCode(String zipCode) 
    {
        this.zipCode = zipCode;
    }

    public String getZipCode() 
    {
        return zipCode;
    }
    public void setPhoneNumber(String phoneNumber) 
    {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() 
    {
        return phoneNumber;
    }
    public void setYearlyIncome(BigDecimal yearlyIncome) 
    {
        this.yearlyIncome = yearlyIncome;
    }

    public BigDecimal getYearlyIncome() 
    {
        return yearlyIncome;
    }
    public void setSocialSecurityNumber(String socialSecurityNumber) 
    {
        this.socialSecurityNumber = socialSecurityNumber;
    }

    public String getSocialSecurityNumber() 
    {
        return socialSecurityNumber;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userId", getUserId())
            .append("firstName", getFirstName())
            .append("lastName", getLastName())
            .append("birthDay", getBirthDay())
            .append("address", getAddress())
            .append("city", getCity())
            .append("state", getState())
            .append("zipCode", getZipCode())
            .append("phoneNumber", getPhoneNumber())
            .append("yearlyIncome", getYearlyIncome())
            .append("socialSecurityNumber", getSocialSecurityNumber())
            .toString();
    }
}
