package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.UserRechargeOrder;

import java.util.List;

/**
 * 用户充值订单Mapper接口
 * 
 * @author ruoyi
 * @date 2022-11-08
 */
public interface UserRechargeOrderMapper 
{
    /**
     * 查询用户充值订单
     * 
     * @param id 用户充值订单主键
     * @return 用户充值订单
     */
    public UserRechargeOrder selectUserRechargeOrderById(Long id);

    /**
     * 查询用户充值订单列表
     * 
     * @param userRechargeOrder 用户充值订单
     * @return 用户充值订单集合
     */
    public List<UserRechargeOrder> selectUserRechargeOrderList(UserRechargeOrder userRechargeOrder);

    /**
     * 新增用户充值订单
     * 
     * @param userRechargeOrder 用户充值订单
     * @return 结果
     */
    public int insertUserRechargeOrder(UserRechargeOrder userRechargeOrder);

    /**
     * 修改用户充值订单
     * 
     * @param userRechargeOrder 用户充值订单
     * @return 结果
     */
    public int updateUserRechargeOrder(UserRechargeOrder userRechargeOrder);

    /**
     * 删除用户充值订单
     * 
     * @param id 用户充值订单主键
     * @return 结果
     */
    public int deleteUserRechargeOrderById(Long id);

    /**
     * 批量删除用户充值订单
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteUserRechargeOrderByIds(Long[] ids);
}
