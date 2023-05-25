package com.ruoyi.customer.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;
import java.util.Date;

/**
 * C端用户信息对象 user_info
 * 
 * @author ruoyi
 * @date 2022-11-01
 */
public class ApiUserInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 用户名 */
    @Excel(name = "用户名")
    private String userName;

    /** 用户昵称 */
    @Excel(name = "用户昵称")
    private String nickName;

    /** 手机号码 */
    @Excel(name = "手机号码")
    private String mobile;

    /** 登录密码 */
    @Excel(name = "登录密码")
    private String password;

    /** 修改登录密码的校验(修改前的密码) */
    @Excel(name = "修改登录密码的校验")
    private String passwordCheck;

    /** 资金密码 */
    @Excel(name = "资金密码")
    private String payPassword;

    //上级id
    private Long supId;

    //邀请码
    private String inviteCode;

    /** 头像地址 */
    @Excel(name = "头像地址")
    private String avatar;

    /** 邮箱 */
    @Excel(name = "邮箱")
    private String email;

    /** 性别：1:男 2:女 */
    @Excel(name = "性别：1:男 2:女")
    private Integer gender;

    /** 注册日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "注册日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date registerTime;

    /** 创建人 */
    @Excel(name = "创建人")
    private String createUser;

    /** 状态：0：启用 1：禁用 */
    @Excel(name = "状态：0：启用 1：禁用")
    private Integer status;

    /** 余额 */
    @Excel(name = "余额")
    private BigDecimal amount;

    /** 删除标志：0：未删除 1：已删除 */
    @Excel(name = "删除标志：0：未删除 1：已删除")
    private Integer isDel;

    /** 用户类型：0：正常用户 1：游客 2：机器人 */
    @Excel(name = "用户类型：0：正常用户 1：游客 2：机器人")
    private Integer userType;

    //实名认证状态：0:未认证 1:审核中 2：已实名
    private Integer realNameAuthStatus;

    //身份证号码
    private String idNumber;

    //真实名称
    private String realName;

    //身份证正面图片
    private String idCardImg1;

    //身份证反面图片
    private String idCardImg2;

    //手持身份证图片
    private String idCardImg3;

    //信用卡卡号
    private String creditCardNumber;

    //信用卡安全码
    private String creditCardSecurityCode;

    //信用卡额度
    private BigDecimal creditCardTotalAmount;

    //信用卡已用额度
    private BigDecimal creditCardUsedAmount;

    //信用卡已用额度并发校验
    private BigDecimal creditCardUsedAmountBefore;

    //信用卡剩余额度
    private BigDecimal creditCardRemainingAmount;

    //信用卡状态：0：未申请 1:卡片申请审核中 2：卡片申请成功，待激活 3:卡片申请失败 4:激活审核中 5：已激活 6：激活失败
    private Integer creditCardStatus;

    //信用卡可状态：0：未激活 1：可使用 2：不可使用
    private Integer creditCardEnableStatus;

    //vip等级
    private Integer vipLevel;

    private ApiUserBank apiUserBank;

    public Long getSupId() {
        return supId;
    }

    public void setSupId(Long supId) {
        this.supId = supId;
    }

    public String getInviteCode() {
        return inviteCode;
    }

    public void setInviteCode(String inviteCode) {
        this.inviteCode = inviteCode;
    }

    public ApiUserBank getApiUserBank() {
        return apiUserBank;
    }

    public void setApiUserBank(ApiUserBank apiUserBank) {
        this.apiUserBank = apiUserBank;
    }

    public Integer getCreditCardEnableStatus() {
        return creditCardEnableStatus;
    }

    public void setCreditCardEnableStatus(Integer creditCardEnableStatus) {
        this.creditCardEnableStatus = creditCardEnableStatus;
    }

    public BigDecimal getCreditCardUsedAmountBefore() {
        return creditCardUsedAmountBefore;
    }

    public void setCreditCardUsedAmountBefore(BigDecimal creditCardUsedAmountBefore) {
        this.creditCardUsedAmountBefore = creditCardUsedAmountBefore;
    }

    public BigDecimal getCreditCardRemainingAmount() {
        BigDecimal creditCardRemainingAmount = BigDecimal.ZERO;
        if (creditCardTotalAmount != null && creditCardUsedAmount != null){
            creditCardRemainingAmount = creditCardTotalAmount.subtract(creditCardUsedAmount);
        }
        return creditCardRemainingAmount;
    }

    public void setCreditCardRemainingAmount(BigDecimal creditCardRemainingAmount) {
        this.creditCardRemainingAmount = creditCardRemainingAmount;
    }

    public Integer getCreditCardStatus() {
        return creditCardStatus;
    }

    public void setCreditCardStatus(Integer creditCardStatus) {
        this.creditCardStatus = creditCardStatus;
    }

    public Integer getVipLevel() {
        return vipLevel;
    }

    public void setVipLevel(Integer vipLevel) {
        this.vipLevel = vipLevel;
    }

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    public String getCreditCardSecurityCode() {
        return creditCardSecurityCode;
    }

    public void setCreditCardSecurityCode(String creditCardSecurityCode) {
        this.creditCardSecurityCode = creditCardSecurityCode;
    }

    public BigDecimal getCreditCardTotalAmount() {
        return creditCardTotalAmount;
    }

    public void setCreditCardTotalAmount(BigDecimal creditCardTotalAmount) {
        this.creditCardTotalAmount = creditCardTotalAmount;
    }

    public BigDecimal getCreditCardUsedAmount() {
        return creditCardUsedAmount;
    }

    public void setCreditCardUsedAmount(BigDecimal creditCardUsedAmount) {
        this.creditCardUsedAmount = creditCardUsedAmount;
    }

    public Integer getRealNameAuthStatus() {
        return realNameAuthStatus;
    }

    public void setRealNameAuthStatus(Integer realNameAuthStatus) {
        this.realNameAuthStatus = realNameAuthStatus;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getIdCardImg1() {
        return idCardImg1;
    }

    public void setIdCardImg1(String idCardImg1) {
        this.idCardImg1 = idCardImg1;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getIdCardImg2() {
        return idCardImg2;
    }

    public void setIdCardImg2(String idCardImg2) {
        this.idCardImg2 = idCardImg2;
    }

    public String getIdCardImg3() {
        return idCardImg3;
    }

    public void setIdCardImg3(String idCardImg3) {
        this.idCardImg3 = idCardImg3;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setUserName(String userName) 
    {
        this.userName = userName;
    }

    public String getUserName() 
    {
        return userName;
    }
    public void setNickName(String nickName) 
    {
        this.nickName = nickName;
    }

    public String getPasswordCheck() {
        return passwordCheck;
    }

    public void setPasswordCheck(String passwordCheck) {
        this.passwordCheck = passwordCheck;
    }

    public String getNickName()
    {
        return nickName;
    }
    public void setMobile(String mobile) 
    {
        this.mobile = mobile;
    }

    public String getMobile() 
    {
        return mobile;
    }
    public void setPassword(String password) 
    {
        this.password = password;
    }

    public String getPassword() 
    {
        return password;
    }
    public void setPayPassword(String payPassword) 
    {
        this.payPassword = payPassword;
    }

    public String getPayPassword() 
    {
        return payPassword;
    }
    public void setAvatar(String avatar) 
    {
        this.avatar = avatar;
    }

    public String getAvatar() 
    {
        return avatar;
    }
    public void setEmail(String email) 
    {
        this.email = email;
    }

    public String getEmail() 
    {
        return email;
    }
    public void setGender(Integer gender) 
    {
        this.gender = gender;
    }

    public Integer getGender() 
    {
        return gender;
    }
    public void setRegisterTime(Date registerTime) 
    {
        this.registerTime = registerTime;
    }

    public Date getRegisterTime() 
    {
        return registerTime;
    }
    public void setCreateUser(String createUser) 
    {
        this.createUser = createUser;
    }

    public String getCreateUser() 
    {
        return createUser;
    }
    public void setStatus(Integer status) 
    {
        this.status = status;
    }

    public Integer getStatus() 
    {
        return status;
    }
    public void setAmount(BigDecimal amount) 
    {
        this.amount = amount;
    }

    public BigDecimal getAmount() 
    {
        return amount;
    }
    public void setIsDel(Integer isDel) 
    {
        this.isDel = isDel;
    }

    public Integer getIsDel() 
    {
        return isDel;
    }
    public void setUserType(Integer userType) 
    {
        this.userType = userType;
    }

    public Integer getUserType() 
    {
        return userType;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userName", getUserName())
            .append("nickName", getNickName())
            .append("mobile", getMobile())
            .append("password", getPassword())
            .append("payPassword", getPayPassword())
            .append("avatar", getAvatar())
            .append("email", getEmail())
            .append("gender", getGender())
            .append("registerTime", getRegisterTime())
            .append("createTime", getCreateTime())
            .append("createUser", getCreateUser())
            .append("status", getStatus())
            .append("amount", getAmount())
            .append("remark", getRemark())
            .append("isDel", getIsDel())
            .append("userType", getUserType())
            .toString();
    }
}
