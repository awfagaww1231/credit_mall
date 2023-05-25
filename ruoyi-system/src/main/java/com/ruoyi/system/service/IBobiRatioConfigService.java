package com.ruoyi.system.service;

import com.ruoyi.system.domain.BobiRatioConfig;

import java.util.List;

/**
 * 商家进货价波比配置Service接口
 * 
 * @author ruoyi
 * @date 2022-11-11
 */
public interface IBobiRatioConfigService 
{
    /**
     * 查询商家进货价波比配置
     * 
     * @param id 商家进货价波比配置主键
     * @return 商家进货价波比配置
     */
    public BobiRatioConfig selectBobiRatioConfigById(Long id);

    /**
     * 查询商家进货价波比配置列表
     * 
     * @param bobiRatioConfig 商家进货价波比配置
     * @return 商家进货价波比配置集合
     */
    public List<BobiRatioConfig> selectBobiRatioConfigList(BobiRatioConfig bobiRatioConfig);

    /**
     * 新增商家进货价波比配置
     * 
     * @param bobiRatioConfig 商家进货价波比配置
     * @return 结果
     */
    public int insertBobiRatioConfig(BobiRatioConfig bobiRatioConfig);

    /**
     * 修改商家进货价波比配置
     * 
     * @param bobiRatioConfig 商家进货价波比配置
     * @return 结果
     */
    public int updateBobiRatioConfig(BobiRatioConfig bobiRatioConfig);

    /**
     * 批量删除商家进货价波比配置
     * 
     * @param ids 需要删除的商家进货价波比配置主键集合
     * @return 结果
     */
    public int deleteBobiRatioConfigByIds(Long[] ids);

    /**
     * 删除商家进货价波比配置信息
     * 
     * @param id 商家进货价波比配置主键
     * @return 结果
     */
    public int deleteBobiRatioConfigById(Long id);
}
