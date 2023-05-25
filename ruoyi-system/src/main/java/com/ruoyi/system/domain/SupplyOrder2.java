package com.ruoyi.system.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 供应订单（卖家向平台的进货订单）对象 supply_order2
 * 
 * @author ruoyi
 * @date 2022-11-02
 */
public class SupplyOrder2 extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 订单编号 */
    @Excel(name = "订单编号")
    private String orderCode;

    /** 平台商品的id */
    @Excel(name = "平台商品的id")
    private Long goodsId;

    /** 商品名称 */
    @Excel(name = "商品名称")
    private String goodsName;

    /** 商品图片 */
    @Excel(name = "商品图片")
    private String goodsImg;

    /** 所属类别名称 */
    @Excel(name = "所属类别名称")
    private String categoryName;

    /** 商品详情 */
    @Excel(name = "商品详情")
    private String detail;

    /** 商品介绍 */
    @Excel(name = "商品介绍")
    private String introduce;


    /** 所属类别id(小类) */
    @Excel(name = "所属类别id(小类)")
    private Long minorClass;

    /** 供货数量 */
    @Excel(name = "供货数量")
    private Integer quantity;

    /** 单价（进货时的价格） */
    @Excel(name = "单价", readConverterExp = "进=货时的价格")
    private BigDecimal singlePrice;

    /** 总价 */
    @Excel(name = "总价")
    private BigDecimal totalPrice;

    /** 付款时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "付款时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date payTime;

    /** 发货时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "发货时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date shipTime;

    /** 订单结束时间(确认收货时间) */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "订单结束时间(确认收货时间)", width = 30, dateFormat = "yyyy-MM-dd")
    private Date finishTime;

    /** 收货地址 */
    @Excel(name = "收货地址")
    private String receiveAddress;

    /** 订单状态：0：待支付 1：已支付 2：已取消 3：已发货 4：审核已通过 5：审核已驳回 */
    @Excel(name = "订单状态：0：待支付 1：已支付 2：已取消 3：已发货 4：审核已通过 5：审核已驳回")
    private Integer status;

    /** 卖家id */
    @Excel(name = "卖家id")
    private Long sellerId;

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

    /** 店铺订单的id */
    @Excel(name = "店铺订单的id")
    private Long shopOrderId;

    /** C端用户收货信息 */
    @Excel(name = "C端用户收货信息")
    private String receiveInfo;

    /** C端用户收货人名称 */
    @Excel(name = "C端用户收货人名称")
    private String receiveName;

    public String getReceiveName() {
        return receiveName;
    }

    public void setReceiveName(String receiveName) {
        this.receiveName = receiveName;
    }

    public String getReceiveInfo() {
        return receiveInfo;
    }

    public void setReceiveInfo(String receiveInfo) {
        this.receiveInfo = receiveInfo;
    }

    public Long getShopOrderId() {
        return shopOrderId;
    }

    public void setShopOrderId(Long shopOrderId) {
        this.shopOrderId = shopOrderId;
    }

    public String getOwnerUserName() {
        return ownerUserName;
    }

    public void setOwnerUserName(String ownerUserName) {
        this.ownerUserName = ownerUserName;
    }

    public String getOwnerNickName() {
        return ownerNickName;
    }

    public void setOwnerNickName(String ownerNickName) {
        this.ownerNickName = ownerNickName;
    }

    public String getOwnerEmail() {
        return ownerEmail;
    }

    public void setOwnerEmail(String ownerEmail) {
        this.ownerEmail = ownerEmail;
    }

    public String getOwnerTel() {
        return ownerTel;
    }

    public void setOwnerTel(String ownerTel) {
        this.ownerTel = ownerTel;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsImg() {
        return goodsImg;
    }

    public void setGoodsImg(String goodsImg) {
        this.goodsImg = goodsImg;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public Long getMinorClass() {
        return minorClass;
    }

    public void setMinorClass(Long minorClass) {
        this.minorClass = minorClass;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setOrderCode(String orderCode)
    {
        this.orderCode = orderCode;
    }

    public String getOrderCode()
    {
        return orderCode;
    }
    public void setGoodsId(Long goodsId)
    {
        this.goodsId = goodsId;
    }

    public Long getGoodsId()
    {
        return goodsId;
    }
    public void setQuantity(Integer quantity)
    {
        this.quantity = quantity;
    }

    public Integer getQuantity()
    {
        return quantity;
    }
    public void setSinglePrice(BigDecimal singlePrice)
    {
        this.singlePrice = singlePrice;
    }

    public BigDecimal getSinglePrice()
    {
        return singlePrice;
    }
    public void setTotalPrice(BigDecimal totalPrice)
    {
        this.totalPrice = totalPrice;
    }

    public BigDecimal getTotalPrice()
    {
        return totalPrice;
    }
    public void setPayTime(Date payTime)
    {
        this.payTime = payTime;
    }

    public Date getPayTime()
    {
        return payTime;
    }
    public void setShipTime(Date shipTime)
    {
        this.shipTime = shipTime;
    }

    public Date getShipTime()
    {
        return shipTime;
    }
    public void setFinishTime(Date finishTime)
    {
        this.finishTime = finishTime;
    }

    public Date getFinishTime()
    {
        return finishTime;
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
    public void setSellerId(Long sellerId)
    {
        this.sellerId = sellerId;
    }

    public Long getSellerId()
    {
        return sellerId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("orderCode", getOrderCode())
                .append("goodsId", getGoodsId())
                .append("quantity", getQuantity())
                .append("singlePrice", getSinglePrice())
                .append("totalPrice", getTotalPrice())
                .append("createTime", getCreateTime())
                .append("updateTime", getUpdateTime())
                .append("payTime", getPayTime())
                .append("shipTime", getShipTime())
                .append("finishTime", getFinishTime())
                .append("receiveAddress", getReceiveAddress())
                .append("remark", getRemark())
                .append("status", getStatus())
                .append("sellerId", getSellerId())
                .toString();
    }
}
