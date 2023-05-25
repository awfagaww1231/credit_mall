package com.ruoyi.system.service;

import com.ruoyi.system.domain.CommissionConfig;

import java.util.List;

/**
 * 返佣配置Service接口
 * 
 * @author ruoyi
 * @date 2022-11-14
 */
public interface ICommissionConfigService 
{
    /**
     * 查询返佣配置
     * 
     * @param id 返佣配置主键
     * @return 返佣配置
     */
    public CommissionConfig selectCommissionConfigById(Long id);

    /**
     * 查询返佣配置
     *
     * @param level 返佣等级
     * @param type 返佣类型
     * @return 返佣配置
     */
    public CommissionConfig selectCommissionConfigByLevelAndType(Integer level,Integer type);

    /**
     * 查询返佣配置列表
     * 
     * @param commissionConfig 返佣配置
     * @return 返佣配置集合
     */
    public List<CommissionConfig> selectCommissionConfigList(CommissionConfig commissionConfig);

    /**
     * 新增返佣配置
     * 
     * @param commissionConfig 返佣配置
     * @return 结果
     */
    public int insertCommissionConfig(CommissionConfig commissionConfig);

    /**
     * 修改返佣配置
     * 
     * @param commissionConfig 返佣配置
     * @return 结果
     */
    public int updateCommissionConfig(CommissionConfig commissionConfig);

    /**
     * 批量删除返佣配置
     * 
     * @param ids 需要删除的返佣配置主键集合
     * @return 结果
     */
    public int deleteCommissionConfigByIds(Long[] ids);

    /**
     * 删除返佣配置信息
     * 
     * @param id 返佣配置主键
     * @return 结果
     */
    public int deleteCommissionConfigById(Long id);
}
