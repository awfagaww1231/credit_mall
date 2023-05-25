package com.ruoyi.customer.service;

import com.ruoyi.customer.domain.ApiUserRechargeOrder;

import java.util.List;

public interface IApiUserRechargeOrderService {


    /**
     * 查询用户充值订单
     *
     * @param id 用户充值订单主键
     * @return 用户充值订单
     */
    public ApiUserRechargeOrder selectUserRechargeOrderById(Long id);

    /**
     * 查询用户充值订单列表
     *
     * @param apiUserRechargeOrder 用户充值订单
     * @return 用户充值订单集合
     */
    public List<ApiUserRechargeOrder> selectUserRechargeOrderList(ApiUserRechargeOrder apiUserRechargeOrder);

    /**
     * 新增用户充值订单
     *
     * @param apiUserRechargeOrder 用户充值订单
     * @return 结果
     */
    public int insertUserRechargeOrder(ApiUserRechargeOrder apiUserRechargeOrder);

    /**
     * 修改用户充值订单
     *
     * @param apiUserRechargeOrder 用户充值订单
     * @return 结果
     */
    public int updateUserRechargeOrder(ApiUserRechargeOrder apiUserRechargeOrder);

    /**
     * 删除用户充值订单信息
     *
     * @param id 用户充值订单主键
     * @return 结果
     */
    public int deleteUserRechargeOrderById(Long id);
}
