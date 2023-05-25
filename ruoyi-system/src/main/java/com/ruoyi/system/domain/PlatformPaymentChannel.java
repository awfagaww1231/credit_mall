package com.ruoyi.system.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 平台收款通道配置对象 platform_payment_channel
 * 
 * @author ruoyi
 * @date 2023-03-20
 */
public class PlatformPaymentChannel extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 通道名称 */
    @Excel(name = "通道名称")
    private String channelName;

    /** 通道账户 */
    @Excel(name = "通道账户")
    private String channelAccount;

    /** 收款人 */
    @Excel(name = "收款人")
    private String payeeName;

    /** 开户行 */
    @Excel(name = "开户行")
    private String accountOpenBank;

    //银行身份代码
    private String swiftCode;

    //路由编号
    private String routingNumber;

    /** 通道图标 */
    @Excel(name = "通道图标")
    private String channelLogo;

    /** 支付类型0：加密货币 1：银行卡 */
    @Excel(name = "支付类型0：加密货币 1：银行卡")
    private Integer payType;

    /** 钱包收款码 */
    @Excel(name = "钱包收款码")
    private String walletReceiptCode;

    /** 支付介绍 */
    @Excel(name = "支付介绍")
    private String payIntroduce;

    /** 教学视频 */
    @Excel(name = "教学视频")
    private String teachingVideo;

    /** 状态 0：启用 1：禁用 */
    @Excel(name = "状态 0：启用 1：禁用")
    private Integer status;

    public String getTeachingVideo() {
        return teachingVideo;
    }

    public void setTeachingVideo(String teachingVideo) {
        this.teachingVideo = teachingVideo;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setChannelName(String channelName) 
    {
        this.channelName = channelName;
    }

    public String getPayeeName() {
        return payeeName;
    }

    public void setPayeeName(String payeeName) {
        this.payeeName = payeeName;
    }

    public String getAccountOpenBank() {
        return accountOpenBank;
    }

    public void setAccountOpenBank(String accountOpenBank) {
        this.accountOpenBank = accountOpenBank;
    }

    public String getChannelName()
    {
        return channelName;
    }

    public String getChannelAccount() {
        return channelAccount;
    }

    public void setChannelAccount(String channelAccount) {
        this.channelAccount = channelAccount;
    }

    public void setChannelLogo(String channelLogo)
    {
        this.channelLogo = channelLogo;
    }

    public String getChannelLogo() 
    {
        return channelLogo;
    }
    public void setPayType(Integer payType) 
    {
        this.payType = payType;
    }

    public Integer getPayType() 
    {
        return payType;
    }
    public void setWalletReceiptCode(String walletReceiptCode) 
    {
        this.walletReceiptCode = walletReceiptCode;
    }

    public String getWalletReceiptCode() 
    {
        return walletReceiptCode;
    }
    public void setPayIntroduce(String payIntroduce) 
    {
        this.payIntroduce = payIntroduce;
    }

    public String getPayIntroduce() 
    {
        return payIntroduce;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("channelName", getChannelName())
            .append("channelAccount", getChannelAccount())
            .append("channelLogo", getChannelLogo())
            .append("payType", getPayType())
            .append("walletReceiptCode", getWalletReceiptCode())
            .append("payIntroduce", getPayIntroduce())
            .toString();
    }
}
