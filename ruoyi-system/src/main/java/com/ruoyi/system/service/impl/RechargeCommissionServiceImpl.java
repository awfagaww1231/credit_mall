package com.ruoyi.system.service.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.domain.RechargeCommission;
import com.ruoyi.system.mapper.RechargeCommissionMapper;
import com.ruoyi.system.service.IRechargeCommissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 充值返佣记录Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-11-14
 */
@Service
public class RechargeCommissionServiceImpl implements IRechargeCommissionService 
{
    @Autowired
    private RechargeCommissionMapper rechargeCommissionMapper;

    /**
     * 查询充值返佣记录
     * 
     * @param id 充值返佣记录主键
     * @return 充值返佣记录
     */
    @Override
    public RechargeCommission selectRechargeCommissionById(Long id)
    {
        return rechargeCommissionMapper.selectRechargeCommissionById(id);
    }

    /**
     * 查询充值返佣记录列表
     * 
     * @param rechargeCommission 充值返佣记录
     * @return 充值返佣记录
     */
    @Override
    public List<RechargeCommission> selectRechargeCommissionList(RechargeCommission rechargeCommission)
    {
        return rechargeCommissionMapper.selectRechargeCommissionList(rechargeCommission);
    }

    /**
     * 新增充值返佣记录
     * 
     * @param rechargeCommission 充值返佣记录
     * @return 结果
     */
    @Override
    public int insertRechargeCommission(RechargeCommission rechargeCommission)
    {
        rechargeCommission.setCreateTime(DateUtils.getNowDate());
        return rechargeCommissionMapper.insertRechargeCommission(rechargeCommission);
    }

    /**
     * 修改充值返佣记录
     * 
     * @param rechargeCommission 充值返佣记录
     * @return 结果
     */
    @Override
    public int updateRechargeCommission(RechargeCommission rechargeCommission)
    {
        return rechargeCommissionMapper.updateRechargeCommission(rechargeCommission);
    }

    /**
     * 批量删除充值返佣记录
     * 
     * @param ids 需要删除的充值返佣记录主键
     * @return 结果
     */
    @Override
    public int deleteRechargeCommissionByIds(Long[] ids)
    {
        return rechargeCommissionMapper.deleteRechargeCommissionByIds(ids);
    }

    /**
     * 删除充值返佣记录信息
     * 
     * @param id 充值返佣记录主键
     * @return 结果
     */
    @Override
    public int deleteRechargeCommissionById(Long id)
    {
        return rechargeCommissionMapper.deleteRechargeCommissionById(id);
    }
}
