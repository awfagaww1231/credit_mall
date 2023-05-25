package com.ruoyi.system.domain;

import com.ruoyi.common.core.domain.BaseEntity;

import java.math.BigDecimal;

public class IndexReport extends BaseEntity {

    //平台商户数
    private Integer sellerQuantity;

    //今日新增的平台商户数
    private Integer sellerQuantityNewRegisterToday;

    //C端用户总数
    private Integer userQuantity;

    //今日新增C端用户数
    private Integer userQuantityNewRegisterToday;

    //平台余额
    private BigDecimal platformTotalAmount;

    //平台总收入
    private BigDecimal platformIncome;

    //平台今日支出
    private BigDecimal platformExpenses;

    //今日商品订单数
    private Integer shopOrderQuantityToday;

    //商品订单总数
    private Integer shopOrderQuantity;

    public Integer getShopOrderQuantityToday() {
        return shopOrderQuantityToday;
    }

    public void setShopOrderQuantityToday(Integer shopOrderQuantityToday) {
        this.shopOrderQuantityToday = shopOrderQuantityToday;
    }

    public Integer getShopOrderQuantity() {
        return shopOrderQuantity;
    }

    public void setShopOrderQuantity(Integer shopOrderQuantity) {
        this.shopOrderQuantity = shopOrderQuantity;
    }

    public BigDecimal getPlatformExpenses() {
        return platformExpenses;
    }

    public void setPlatformExpenses(BigDecimal platformExpenses) {
        this.platformExpenses = platformExpenses;
    }

    public Integer getSellerQuantity() {
        return sellerQuantity;
    }

    public void setSellerQuantity(Integer sellerQuantity) {
        this.sellerQuantity = sellerQuantity;
    }

    public Integer getSellerQuantityNewRegisterToday() {
        return sellerQuantityNewRegisterToday;
    }

    public void setSellerQuantityNewRegisterToday(Integer sellerQuantityNewRegisterToday) {
        this.sellerQuantityNewRegisterToday = sellerQuantityNewRegisterToday;
    }

    public Integer getUserQuantity() {
        return userQuantity;
    }

    public void setUserQuantity(Integer userQuantity) {
        this.userQuantity = userQuantity;
    }

    public Integer getUserQuantityNewRegisterToday() {
        return userQuantityNewRegisterToday;
    }

    public void setUserQuantityNewRegisterToday(Integer userQuantityNewRegisterToday) {
        this.userQuantityNewRegisterToday = userQuantityNewRegisterToday;
    }

    public BigDecimal getPlatformTotalAmount() {
        return platformTotalAmount;
    }

    public void setPlatformTotalAmount(BigDecimal platformTotalAmount) {
        this.platformTotalAmount = platformTotalAmount;
    }

    public BigDecimal getPlatformIncome() {
        return platformIncome;
    }

    public void setPlatformIncome(BigDecimal platformIncome) {
        this.platformIncome = platformIncome;
    }
}
