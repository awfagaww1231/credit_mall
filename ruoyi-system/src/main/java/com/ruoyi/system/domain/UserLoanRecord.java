package com.ruoyi.system.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;

/**
 * 用户贷款记录对象 user_loan_record
 * 
 * @author ruoyi
 * @date 2023-04-19
 */
public class UserLoanRecord extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 用户id */
    @Excel(name = "用户id")
    private Long userId;

    /** 用户名 */
    @Excel(name = "用户名")
    private String userName;

    //真实名称
    private String realName;

    /** 贷款总金额 */
    @Excel(name = "贷款总金额")
    private BigDecimal loanTotalAmount;

    /** 需还款总额 */
    @Excel(name = "需还款总额")
    private BigDecimal repaymentAmountTotal;

    /** 年利率 */
    @Excel(name = "年利率")
    private BigDecimal annualInterestRate;

    /** 总还款期数 */
    @Excel(name = "总还款期数")
    private Integer repaymentPeriodNum;

    /** 已还款期数 */
    @Excel(name = "已还款期数")
    private Integer alreadyPaidPeriodNum;

    /** 每期还款本金 */
    @Excel(name = "每期还款本金")
    private BigDecimal repaymentPrincipalEveryPeriod;

    /** 每期还款利息 */
    @Excel(name = "每期还款利息")
    private BigDecimal repaymentInterestEveryPeriod;

    /** 总共应该还利息 */
    @Excel(name = "总共应该还利息")
    private BigDecimal repaymentInterestTotal;

    /** 分期配置id */
    @Excel(name = "分期配置id")
    private Long installmentInterestConfigId;

    //每期总还款
    private BigDecimal repaymentTotalAmountEveryPeriod;


    /** 状态 0：待审核 1：审核通过 2：审核驳回 3：分期还款结束 */
    @Excel(name = "状态 0：待审核 1：审核通过 2：审核驳回 3：分期还款结束")
    private Integer status;

    //资金密码
    private String payPassword;

    /**银行名称**/
    private String bankName;

    /**银行卡号**/
    private String bankNo;

    /**开户地址**/
    private String bankAddress;

    //持有人
    private String accountHolder;

    //银行身份代码
    private String swiftCode;

    //路由编号
    private String routingNumber;

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankNo() {
        return bankNo;
    }

    public void setBankNo(String bankNo) {
        this.bankNo = bankNo;
    }

    public String getBankAddress() {
        return bankAddress;
    }

    public void setBankAddress(String bankAddress) {
        this.bankAddress = bankAddress;
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

    public String getRoutingNumber() {
        return routingNumber;
    }

    public void setRoutingNumber(String routingNumber) {
        this.routingNumber = routingNumber;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getPayPassword() {
        return payPassword;
    }

    public void setPayPassword(String payPassword) {
        this.payPassword = payPassword;
    }

    public BigDecimal getRepaymentInterestTotal() {
        return repaymentInterestTotal;
    }


    public BigDecimal getRepaymentTotalAmountEveryPeriod() {
        return repaymentTotalAmountEveryPeriod;
    }

    public void setRepaymentTotalAmountEveryPeriod(BigDecimal repaymentTotalAmountEveryPeriod) {
        this.repaymentTotalAmountEveryPeriod = repaymentTotalAmountEveryPeriod;
    }

    public void setRepaymentInterestTotal(BigDecimal repaymentInterestTotal) {
        this.repaymentInterestTotal = repaymentInterestTotal;
    }

    public Long getInstallmentInterestConfigId() {
        return installmentInterestConfigId;
    }

    public void setInstallmentInterestConfigId(Long installmentInterestConfigId) {
        this.installmentInterestConfigId = installmentInterestConfigId;
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
    public void setLoanTotalAmount(BigDecimal loanTotalAmount) 
    {
        this.loanTotalAmount = loanTotalAmount;
    }

    public BigDecimal getLoanTotalAmount() 
    {
        return loanTotalAmount;
    }
    public void setRepaymentAmountTotal(BigDecimal repaymentAmountTotal) 
    {
        this.repaymentAmountTotal = repaymentAmountTotal;
    }

    public BigDecimal getRepaymentAmountTotal() 
    {
        return repaymentAmountTotal;
    }
    public void setAnnualInterestRate(BigDecimal annualInterestRate) 
    {
        this.annualInterestRate = annualInterestRate;
    }

    public BigDecimal getAnnualInterestRate() 
    {
        return annualInterestRate;
    }
    public void setRepaymentPeriodNum(Integer repaymentPeriodNum) 
    {
        this.repaymentPeriodNum = repaymentPeriodNum;
    }

    public Integer getRepaymentPeriodNum() 
    {
        return repaymentPeriodNum;
    }
    public void setAlreadyPaidPeriodNum(Integer alreadyPaidPeriodNum) 
    {
        this.alreadyPaidPeriodNum = alreadyPaidPeriodNum;
    }

    public Integer getAlreadyPaidPeriodNum() 
    {
        return alreadyPaidPeriodNum;
    }
    public void setRepaymentPrincipalEveryPeriod(BigDecimal repaymentPrincipalEveryPeriod) 
    {
        this.repaymentPrincipalEveryPeriod = repaymentPrincipalEveryPeriod;
    }

    public BigDecimal getRepaymentPrincipalEveryPeriod() 
    {
        return repaymentPrincipalEveryPeriod;
    }
    public void setRepaymentInterestEveryPeriod(BigDecimal repaymentInterestEveryPeriod) 
    {
        this.repaymentInterestEveryPeriod = repaymentInterestEveryPeriod;
    }

    public BigDecimal getRepaymentInterestEveryPeriod() 
    {
        return repaymentInterestEveryPeriod;
    }
    public void setStatus(Integer status) 
    {
        this.status = status;
    }

    public Integer getStatus() 
    {
        return status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userId", getUserId())
            .append("loanTotalAmount", getLoanTotalAmount())
            .append("repaymentAmountTotal", getRepaymentAmountTotal())
            .append("annualInterestRate", getAnnualInterestRate())
            .append("repaymentPeriodNum", getRepaymentPeriodNum())
            .append("alreadyPaidPeriodNum", getAlreadyPaidPeriodNum())
            .append("repaymentPrincipalEveryPeriod", getRepaymentPrincipalEveryPeriod())
            .append("repaymentInterestEveryPeriod", getRepaymentInterestEveryPeriod())
            .append("status", getStatus())
            .toString();
    }
}
