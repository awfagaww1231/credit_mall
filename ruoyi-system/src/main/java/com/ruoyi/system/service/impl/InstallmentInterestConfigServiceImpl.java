package com.ruoyi.system.service.impl;

import com.ruoyi.system.domain.InstallmentInterestConfig;
import com.ruoyi.system.mapper.InstallmentInterestConfigMapper;
import com.ruoyi.system.service.IInstallmentInterestConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 贷款分期利息配置Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-04-19
 */
@Service
public class InstallmentInterestConfigServiceImpl implements IInstallmentInterestConfigService 
{
    @Autowired
    private InstallmentInterestConfigMapper installmentInterestConfigMapper;

    /**
     * 查询贷款分期利息配置
     * 
     * @param id 贷款分期利息配置主键
     * @return 贷款分期利息配置
     */
    @Override
    public InstallmentInterestConfig selectInstallmentInterestConfigById(Long id)
    {
        return installmentInterestConfigMapper.selectInstallmentInterestConfigById(id);
    }

    /**
     * 查询贷款分期利息配置列表
     * 
     * @param installmentInterestConfig 贷款分期利息配置
     * @return 贷款分期利息配置
     */
    @Override
    public List<InstallmentInterestConfig> selectInstallmentInterestConfigList(InstallmentInterestConfig installmentInterestConfig)
    {
        return installmentInterestConfigMapper.selectInstallmentInterestConfigList(installmentInterestConfig);
    }

    /**
     * 新增贷款分期利息配置
     * 
     * @param installmentInterestConfig 贷款分期利息配置
     * @return 结果
     */
    @Override
    public int insertInstallmentInterestConfig(InstallmentInterestConfig installmentInterestConfig)
    {
        return installmentInterestConfigMapper.insertInstallmentInterestConfig(installmentInterestConfig);
    }

    /**
     * 修改贷款分期利息配置
     * 
     * @param installmentInterestConfig 贷款分期利息配置
     * @return 结果
     */
    @Override
    public int updateInstallmentInterestConfig(InstallmentInterestConfig installmentInterestConfig)
    {
        return installmentInterestConfigMapper.updateInstallmentInterestConfig(installmentInterestConfig);
    }

    /**
     * 批量删除贷款分期利息配置
     * 
     * @param ids 需要删除的贷款分期利息配置主键
     * @return 结果
     */
    @Override
    public int deleteInstallmentInterestConfigByIds(Long[] ids)
    {
        return installmentInterestConfigMapper.deleteInstallmentInterestConfigByIds(ids);
    }

    /**
     * 删除贷款分期利息配置信息
     * 
     * @param id 贷款分期利息配置主键
     * @return 结果
     */
    @Override
    public int deleteInstallmentInterestConfigById(Long id)
    {
        return installmentInterestConfigMapper.deleteInstallmentInterestConfigById(id);
    }
}
