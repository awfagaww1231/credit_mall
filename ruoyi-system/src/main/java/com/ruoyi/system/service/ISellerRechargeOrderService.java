package com.ruoyi.system.service;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.system.domain.SellerRechargeOrder;

import java.util.List;

/**
 * 用户充值订单Service接口
 * 
 * @author ruoyi
 * @date 2022-11-09
 */
public interface ISellerRechargeOrderService 
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
     * 批量删除用户充值订单
     * 
     * @param ids 需要删除的用户充值订单主键集合
     * @return 结果
     */
    public int deleteSellerRechargeOrderByIds(Long[] ids);

    /**
     * 删除用户充值订单信息
     * 
     * @param id 用户充值订单主键
     * @return 结果
     */
    public int deleteSellerRechargeOrderById(Long id);

    /**
     * 通过审核
     */
    AjaxResult agree(List<SellerRechargeOrder> sellerRechargeOrders);

    /**
     * 驳回审核
     */
    AjaxResult reject(List<SellerRechargeOrder> sellerRechargeOrders);
}
