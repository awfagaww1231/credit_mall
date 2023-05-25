package com.ruoyi.system.service.impl;

import com.ruoyi.system.domain.BobiRatioConfig;
import com.ruoyi.system.mapper.BobiRatioConfigMapper;
import com.ruoyi.system.service.IBobiRatioConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商家进货价波比配置Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-11-11
 */
@Service
public class BobiRatioConfigServiceImpl implements IBobiRatioConfigService 
{
    @Autowired
    private BobiRatioConfigMapper bobiRatioConfigMapper;

    /**
     * 查询商家进货价波比配置
     * 
     * @param id 商家进货价波比配置主键
     * @return 商家进货价波比配置
     */
    @Override
    public BobiRatioConfig selectBobiRatioConfigById(Long id)
    {
        return bobiRatioConfigMapper.selectBobiRatioConfigById(id);
    }

    /**
     * 查询商家进货价波比配置列表
     * 
     * @param bobiRatioConfig 商家进货价波比配置
     * @return 商家进货价波比配置
     */
    @Override
    public List<BobiRatioConfig> selectBobiRatioConfigList(BobiRatioConfig bobiRatioConfig)
    {
        return bobiRatioConfigMapper.selectBobiRatioConfigList(bobiRatioConfig);
    }

    /**
     * 新增商家进货价波比配置
     * 
     * @param bobiRatioConfig 商家进货价波比配置
     * @return 结果
     */
    @Override
    public int insertBobiRatioConfig(BobiRatioConfig bobiRatioConfig)
    {
        return bobiRatioConfigMapper.insertBobiRatioConfig(bobiRatioConfig);
    }

    /**
     * 修改商家进货价波比配置
     * 
     * @param bobiRatioConfig 商家进货价波比配置
     * @return 结果
     */
    @Override
    public int updateBobiRatioConfig(BobiRatioConfig bobiRatioConfig)
    {
        return bobiRatioConfigMapper.updateBobiRatioConfig(bobiRatioConfig);
    }

    /**
     * 批量删除商家进货价波比配置
     * 
     * @param ids 需要删除的商家进货价波比配置主键
     * @return 结果
     */
    @Override
    public int deleteBobiRatioConfigByIds(Long[] ids)
    {
        return bobiRatioConfigMapper.deleteBobiRatioConfigByIds(ids);
    }

    /**
     * 删除商家进货价波比配置信息
     * 
     * @param id 商家进货价波比配置主键
     * @return 结果
     */
    @Override
    public int deleteBobiRatioConfigById(Long id)
    {
        return bobiRatioConfigMapper.deleteBobiRatioConfigById(id);
    }
}
