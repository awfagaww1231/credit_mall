package com.ruoyi.system.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.annotation.Excels;
import com.ruoyi.common.core.domain.BaseEntity;

import java.math.BigDecimal;

public class UserPromotionStatistics  extends BaseEntity
{
    @Excel(name = "用户Id")
    private Long id;
    @Excels({
            @Excel(name = "用户名", targetAttr = "userName", type = Excel.Type.EXPORT),
            @Excel(name = "用户昵称", targetAttr = "nickName", type = Excel.Type.EXPORT),
            @Excel(name = "用户电话", targetAttr = "mobile", type = Excel.Type.EXPORT),
            @Excel(name = "账户余额", targetAttr = "amount", type = Excel.Type.EXPORT),
            @Excel(name = "信用卡额度", targetAttr = "creditCardTotalAmount", type = Excel.Type.EXPORT),
            @Excel(name = "信用卡已用额度", targetAttr = "creditCardUsedAmount", type = Excel.Type.EXPORT)
    })
    private UserInfo userInfo;
    @Excel(name = "充值金额")
    private BigDecimal rechargeAmount;
    @Excel(name = "提现金额")
    private BigDecimal withdrawAmount;
    @Excel(name = "额度激活费用")
    private BigDecimal caPayAmount;
    @Excel(name = "VIP激活费用")
    private BigDecimal vaPayAmount;
    @Excel(name = "邀请用户ID")
    private Long inviteUserId;
    @Excel(name = "邀请用户名称")
    private String inviteUserName;
    @Excel(name = "邀请用户姓名")
    private String inviteNickName;
    private String keyword;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public BigDecimal getRechargeAmount() {
        return rechargeAmount;
    }

    public void setRechargeAmount(BigDecimal rechargeAmount) {
        this.rechargeAmount = rechargeAmount;
    }

    public BigDecimal getWithdrawAmount() {
        return withdrawAmount;
    }

    public void setWithdrawAmount(BigDecimal withdrawAmount) {
        this.withdrawAmount = withdrawAmount;
    }

    public BigDecimal getCaPayAmount() {
        return caPayAmount;
    }

    public void setCaPayAmount(BigDecimal caPayAmount) {
        this.caPayAmount = caPayAmount;
    }

    public BigDecimal getVaPayAmount() {
        return vaPayAmount;
    }

    public void setVaPayAmount(BigDecimal vaPayAmount) {
        this.vaPayAmount = vaPayAmount;
    }

    public Long getInviteUserId() {
        return inviteUserId;
    }

    public void setInviteUserId(Long inviteUserId) {
        this.inviteUserId = inviteUserId;
    }

    public String getInviteUserName() {
        return inviteUserName;
    }

    public void setInviteUserName(String inviteUserName) {
        this.inviteUserName = inviteUserName;
    }

    public String getInviteNickName() {
        return inviteNickName;
    }

    public void setInviteNickName(String inviteNickName) {
        this.inviteNickName = inviteNickName;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
