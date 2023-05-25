package com.ruoyi.system.service.impl;

import com.ruoyi.system.domain.CommissionConfig;
import com.ruoyi.system.mapper.CommissionConfigMapper;
import com.ruoyi.system.service.ICommissionConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 返佣配置Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-11-14
 */
@Service
public class CommissionConfigServiceImpl implements ICommissionConfigService 
{
    @Autowired
    private CommissionConfigMapper commissionConfigMapper;

    /**
     * 查询返佣配置
     * 
     * @param id 返佣配置主键
     * @return 返佣配置
     */
    @Override
    public CommissionConfig selectCommissionConfigById(Long id)
    {
        return commissionConfigMapper.selectCommissionConfigById(id);
    }

    @Override
    public CommissionConfig selectCommissionConfigByLevelAndType(Integer level, Integer type) {
        return commissionConfigMapper.selectCommissionConfigByLevelAndType(level,type);
    }

    /**
     * 查询返佣配置列表
     * 
     * @param commissionConfig 返佣配置
     * @return 返佣配置
     */
    @Override
    public List<CommissionConfig> selectCommissionConfigList(CommissionConfig commissionConfig)
    {
        return commissionConfigMapper.selectCommissionConfigList(commissionConfig);
    }

    /**
     * 新增返佣配置
     * 
     * @param commissionConfig 返佣配置
     * @return 结果
     */
    @Override
    public int insertCommissionConfig(CommissionConfig commissionConfig)
    {
        return commissionConfigMapper.insertCommissionConfig(commissionConfig);
    }

    /**
     * 修改返佣配置
     * 
     * @param commissionConfig 返佣配置
     * @return 结果
     */
    @Override
    public int updateCommissionConfig(CommissionConfig commissionConfig)
    {
        return commissionConfigMapper.updateCommissionConfig(commissionConfig);
    }

    /**
     * 批量删除返佣配置
     * 
     * @param ids 需要删除的返佣配置主键
     * @return 结果
     */
    @Override
    public int deleteCommissionConfigByIds(Long[] ids)
    {
        return commissionConfigMapper.deleteCommissionConfigByIds(ids);
    }

    /**
     * 删除返佣配置信息
     * 
     * @param id 返佣配置主键
     * @return 结果
     */
    @Override
    public int deleteCommissionConfigById(Long id)
    {
        return commissionConfigMapper.deleteCommissionConfigById(id);
    }
}
