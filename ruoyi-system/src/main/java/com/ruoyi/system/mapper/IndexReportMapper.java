package com.ruoyi.system.mapper;

import java.math.BigDecimal;

public interface IndexReportMapper {

    //平台商户数
    int sellerQuantity();

    //今日新增的平台商户数
    int sellerQuantityNewRegisterToday();

    //C端用户总数
    int userQuantity();

    //今日新增C端用户数
    int userQuantityNewRegisterToday();

//    //平台供货订单总数
//    int supplyOrderQuantity();
//
//    //今日平台供货订单数
//    int supplyOrderQuantityToday();

    //商户钱包总余额
    BigDecimal sellerWalletAmountTotal();

    //C端用户钱包总余额
    BigDecimal userWalletAmountTotal();

    //商户总充值
    BigDecimal sellerRechargeAmount();

    //C端用户总充值
    BigDecimal userRechargeAmount();

    //今日商品订单数
    Integer shopOrderQuantityToday();

    //商品订单总数
    Integer shopOrderQuantity();

}
