package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.RechargeCommission;

import java.util.List;

/**
 * 充值返佣记录Mapper接口
 * 
 * @author ruoyi
 * @date 2022-11-14
 */
public interface RechargeCommissionMapper 
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
     * 删除充值返佣记录
     * 
     * @param id 充值返佣记录主键
     * @return 结果
     */
    public int deleteRechargeCommissionById(Long id);

    /**
     * 批量删除充值返佣记录
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteRechargeCommissionByIds(Long[] ids);
}
