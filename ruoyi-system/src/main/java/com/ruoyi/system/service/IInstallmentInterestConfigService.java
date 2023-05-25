package com.ruoyi.system.service;

import com.ruoyi.system.domain.InstallmentInterestConfig;

import java.util.List;

/**
 * 贷款分期利息配置Service接口
 * 
 * @author ruoyi
 * @date 2023-04-19
 */
public interface IInstallmentInterestConfigService 
{
    /**
     * 查询贷款分期利息配置
     * 
     * @param id 贷款分期利息配置主键
     * @return 贷款分期利息配置
     */
    public InstallmentInterestConfig selectInstallmentInterestConfigById(Long id);

    /**
     * 查询贷款分期利息配置列表
     * 
     * @param installmentInterestConfig 贷款分期利息配置
     * @return 贷款分期利息配置集合
     */
    public List<InstallmentInterestConfig> selectInstallmentInterestConfigList(InstallmentInterestConfig installmentInterestConfig);

    /**
     * 新增贷款分期利息配置
     * 
     * @param installmentInterestConfig 贷款分期利息配置
     * @return 结果
     */
    public int insertInstallmentInterestConfig(InstallmentInterestConfig installmentInterestConfig);

    /**
     * 修改贷款分期利息配置
     * 
     * @param installmentInterestConfig 贷款分期利息配置
     * @return 结果
     */
    public int updateInstallmentInterestConfig(InstallmentInterestConfig installmentInterestConfig);

    /**
     * 批量删除贷款分期利息配置
     * 
     * @param ids 需要删除的贷款分期利息配置主键集合
     * @return 结果
     */
    public int deleteInstallmentInterestConfigByIds(Long[] ids);

    /**
     * 删除贷款分期利息配置信息
     * 
     * @param id 贷款分期利息配置主键
     * @return 结果
     */
    public int deleteInstallmentInterestConfigById(Long id);
}
