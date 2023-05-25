package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.SellerRechargeOrder;

import java.math.BigDecimal;
import java.util.List;

/**
 * 用户充值订单Mapper接口
 * 
 * @author ruoyi
 * @date 2022-11-09
 */
public interface SellerRechargeOrderMapper 
{
    /**
     * 查询用户充值订单
     * 
     * @param id 用户充值订单主键
     * @return 用户充值订单
     */
    public SellerRechargeOrder selectSellerRechargeOrderById(Long id);

    /**
     * 查询用户充值订单列表
     * 
     * @param sellerRechargeOrder 用户充值订单
     * @return 用户充值订单集合
     */
    public List<SellerRechargeOrder> selectSellerRechargeOrderList(SellerRechargeOrder sellerRechargeOrder);

    /**
     * 新增用户充值订单
     * 
     * @param sellerRechargeOrder 用户充值订单
     * @return 结果
     */
    public int insertSellerRechargeOrder(SellerRechargeOrder sellerRechargeOrder);

    /**
     * 修改用户充值订单
     * 
     * @param sellerRechargeOrder 用户充值订单
     * @return 结果
     */
    public int updateSellerRechargeOrder(SellerRechargeOrder sellerRechargeOrder);

    /**
     * 删除用户充值订单
     * 
     * @param id 用户充值订单主键
     * @return 结果
     */
    public int deleteSellerRechargeOrderById(Long id);

    /**
     * 批量删除用户充值订单
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSellerRechargeOrderByIds(Long[] ids);

    //根据商户id计算商户的充值总金额
    BigDecimal selectRechargeAmountBySellerId(Long sellerId);
}
