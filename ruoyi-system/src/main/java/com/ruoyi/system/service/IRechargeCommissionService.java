package com.ruoyi.system.service;

import com.ruoyi.system.domain.RechargeCommission;

import java.util.List;

/**
 * 充值返佣记录Service接口
 * 
 * @author ruoyi
 * @date 2022-11-14
 */
public interface IRechargeCommissionService 
{
    /**
     * 查询充值返佣记录
     * 
     * @param id 充值返佣记录主键
     * @return 充值返佣记录
     */
    public RechargeCommission selectRechargeCommissionById(Long id);

    /**
     * 查询充值返佣记录列表
     * 
     * @param rechargeCommission 充值返佣记录
     * @return 充值返佣记录集合
     */
    public List<RechargeCommission> selectRechargeCommissionList(RechargeCommission rechargeCommission);

    /**
     * 新增充值返佣记录
     * 
     * @param rechargeCommission 充值返佣记录
     * @return 结果
     */
    public int insertRechargeCommission(RechargeCommission rechargeCommission);

    /**
     * 修改充值返佣记录
     * 
     * @param rechargeCommission 充值返佣记录
     * @return 结果
     */
    public int updateRechargeCommission(RechargeCommission rechargeCommission);

    /**
     * 批量删除充值返佣记录
     * 
     * @param ids 需要删除的充值返佣记录主键集合
     * @return 结果
     */
    public int deleteRechargeCommissionByIds(Long[] ids);

    /**
     * 删除充值返佣记录信息
     * 
     * @param id 充值返佣记录主键
     * @return 结果
     */
    public int deleteRechargeCommissionById(Long id);
}
