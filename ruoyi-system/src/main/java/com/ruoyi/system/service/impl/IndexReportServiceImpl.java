package com.ruoyi.system.service.impl;

import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.IndexReport;
import com.ruoyi.system.mapper.IndexReportMapper;
import com.ruoyi.system.service.IIndexReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class IndexReportServiceImpl implements IIndexReportService {

    @Autowired
    private IndexReportMapper indexReportMapper;

    @Override
    public IndexReport indexReport() {
        IndexReport indexReport = new IndexReport();

        //平台商户数
        int sellerQuantity = indexReportMapper.sellerQuantity();
        indexReport.setSellerQuantity(sellerQuantity);

        //今日新增的平台商户数
        int sellerQuantityNewRegisterToday = indexReportMapper.sellerQuantityNewRegisterToday();
        indexReport.setSellerQuantityNewRegisterToday(sellerQuantityNewRegisterToday);

        //C端用户总数
        int userQuantity = indexReportMapper.userQuantity();
        indexReport.setUserQuantity(userQuantity);

        //今日新增C端用户数
        int userQuantityNewRegisterToday = indexReportMapper.userQuantityNewRegisterToday();
        indexReport.setUserQuantityNewRegisterToday(userQuantityNewRegisterToday);

        //平台余额
        //商户钱包总余额
        BigDecimal sellerWalletAmountTotal = indexReportMapper.sellerWalletAmountTotal();
        if (StringUtils.isNull(sellerWalletAmountTotal)){
            sellerWalletAmountTotal = BigDecimal.ZERO;
        }
        //C端用户钱包总余额
        BigDecimal userWalletAmountTotal = indexReportMapper.userWalletAmountTotal();
        if (StringUtils.isNull(userWalletAmountTotal)){
            userWalletAmountTotal = BigDecimal.ZERO;
        }
        //平台余额 = 商户钱包总余额 + C端用户钱包总余额
        indexReport.setPlatformTotalAmount(sellerWalletAmountTotal.add(userWalletAmountTotal));

        //平台总收入
        //商户总充值
        BigDecimal sellerRechargeAmount = indexReportMapper.sellerRechargeAmount();
        if (StringUtils.isNull(sellerRechargeAmount)){
            sellerRechargeAmount = BigDecimal.ZERO;
        }
        //C端用户总充值
        BigDecimal userRechargeAmount = indexReportMapper.userRechargeAmount();
        if (StringUtils.isNull(userRechargeAmount)){
            userRechargeAmount = BigDecimal.ZERO;
        }
        //平台总收入 = 商户总充值 + C端用户总充值
        indexReport.setPlatformIncome(sellerRechargeAmount.add(userRechargeAmount));

        //平台今日支出
        indexReport.setPlatformExpenses(BigDecimal.ZERO);

        //商品订单总数
        Integer shopOrderQuantity = indexReportMapper.shopOrderQuantity();
        indexReport.setShopOrderQuantity(shopOrderQuantity);

        //今日商品订单数
        Integer shopOrderQuantityToday = indexReportMapper.shopOrderQuantityToday();
        indexReport.setShopOrderQuantityToday(shopOrderQuantityToday);

        return indexReport;
    }
}
