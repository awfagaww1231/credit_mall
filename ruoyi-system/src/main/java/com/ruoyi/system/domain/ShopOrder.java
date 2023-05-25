package com.ruoyi.system.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 店铺订单对象 shop_order
 * 
 * @author ruoyi
 * @date 2022-11-02
 */
public class ShopOrder extends BaseEntity
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


    /** 商品价格(平台价格) */
    @Excel(name = "平台价格")
    private BigDecimal platformPrice;

    /** 购买数量 */
    @Excel(name = "购买数量")
    private Integer quantity;

    /** 单价（需支付价格） */
    @Excel(name = "单价", readConverterExp = "需=支付价格")
    private BigDecimal singlePrice;

    /** 总价（需支付价格） */
    @Excel(name = "总价", readConverterExp = "需=支付价格")
    private BigDecimal totalPrice;

    /** 付款时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "付款时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date payTime;

    /** 发货时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "发货时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date shipTime;

    /** 订单结束时间(确认收货时间) */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "订单结束时间(确认收货时间)", width = 30, dateFormat = "yyyy-MM-dd")
    private Date finishTime;

    /** 收货信息 */
    @Excel(name = "收货信息")
    private String receiveInfo;

    /** C端用户收货人名称 */
    @Excel(name = "C端用户收货人名称")
    private String receiveName;

    /** 订单状态：0：待支付 1：已支付 2：已取消 3：已发货 4:已完成 */
    @Excel(name = "订单状态：0：待支付 1：已支付 2：已取消 3：已发货 4:已完成")
    private Integer status;

    /** 店铺id */
    @Excel(name = "店铺id")
    private Long shopId;

    /** 店铺商家id */
    @Excel(name = "店铺商家id")
    private Long sellerId;

    /** 店铺名称 */
    @Excel(name = "店铺名称")
    private String shopName;

    /** 购买用户的id */
    @Excel(name = "购买用户的id")
    private Long userId;

    /** 用户名 */
    @Excel(name = "用户名")
    private String userName;

    /** 用户昵称 */
    @Excel(name = "用户昵称")
    private String nickName;

    /** 手机号码 */
    @Excel(name = "手机号码")
    private String mobile;

    /** 物流编号 */
    @Excel(name = "物流编号")
    private String shipmentNumber;

    /** 店铺商品信息的id */
    @Excel(name = "店铺商品信息的id")
    private Long shopGoodsInfoId;

    /** 筛选开始时间 */
    @Excel(name = "筛选开始时间")
    private Date startTime;

    /** 筛选结束时间 */
    @Excel(name = "筛选结束时间")
    private Date endTime;

    public String getReceiveName() {
        return receiveName;
    }

    public void setReceiveName(String receiveName) {
        this.receiveName = receiveName;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Long getShopGoodsInfoId() {
        return shopGoodsInfoId;
    }

    public void setShopGoodsInfoId(Long shopGoodsInfoId) {
        this.shopGoodsInfoId = shopGoodsInfoId;
    }

    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }

    public String getShipmentNumber() {
        return shipmentNumber;
    }

    public void setShipmentNumber(String shipmentNumber) {
        this.shipmentNumber = shipmentNumber;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }


    public BigDecimal getPlatformPrice() {
        return platformPrice;
    }

    public void setPlatformPrice(BigDecimal platformPrice) {
        this.platformPrice = platformPrice;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
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
    public void setReceiveInfo(String receiveInfo) 
    {
        this.receiveInfo = receiveInfo;
    }

    public String getReceiveInfo() 
    {
        return receiveInfo;
    }
    public void setStatus(Integer status) 
    {
        this.status = status;
    }

    public Integer getStatus() 
    {
        return status;
    }
    public void setShopId(Long shopId) 
    {
        this.shopId = shopId;
    }

    public Long getShopId() 
    {
        return shopId;
    }
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
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
            .append("payTime", getPayTime())
            .append("shipTime", getShipTime())
            .append("finishTime", getFinishTime())
            .append("receiveInfo", getReceiveInfo())
            .append("remark", getRemark())
            .append("status", getStatus())
            .append("shopId", getShopId())
            .append("userId", getUserId())
            .toString();
    }
}
