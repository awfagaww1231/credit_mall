package com.ruoyi.system.service.impl;

import com.ruoyi.system.domain.SpecificationConfig;
import com.ruoyi.system.mapper.SpecificationConfigMapper;
import com.ruoyi.system.service.ISpecificationConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商品规格配置Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-11-18
 */
@Service
public class SpecificationConfigServiceImpl implements ISpecificationConfigService 
{
    @Autowired
    private SpecificationConfigMapper specificationConfigMapper;

    /**
     * 查询商品规格配置
     * 
     * @param id 商品规格配置主键
     * @return 商品规格配置
     */
    @Override
    public SpecificationConfig selectSpecificationConfigById(Long id)
    {
        return specificationConfigMapper.selectSpecificationConfigById(id);
    }

    /**
     * 查询商品规格配置列表
     * 
     * @param specificationConfig 商品规格配置
     * @return 商品规格配置
     */
    @Override
    public List<SpecificationConfig> selectSpecificationConfigList(SpecificationConfig specificationConfig)
    {
        return specificationConfigMapper.selectSpecificationConfigList(specificationConfig);
    }

    /**
     * 新增商品规格配置
     * 
     * @param specificationConfig 商品规格配置
     * @return 结果
     */
    @Override
    public int insertSpecificationConfig(SpecificationConfig specificationConfig)
    {
        return specificationConfigMapper.insertSpecificationConfig(specificationConfig);
    }

    /**
     * 修改商品规格配置
     * 
     * @param specificationConfig 商品规格配置
     * @return 结果
     */
    @Override
    public int updateSpecificationConfig(SpecificationConfig specificationConfig)
    {
        return specificationConfigMapper.updateSpecificationConfig(specificationConfig);
    }

    /**
     * 批量删除商品规格配置
     * 
     * @param ids 需要删除的商品规格配置主键
     * @return 结果
     */
    @Override
    public int deleteSpecificationConfigByIds(Long[] ids)
    {
        return specificationConfigMapper.deleteSpecificationConfigByIds(ids);
    }

    /**
     * 删除商品规格配置信息
     * 
     * @param id 商品规格配置主键
     * @return 结果
     */
    @Override
    public int deleteSpecificationConfigById(Long id)
    {
        return specificationConfigMapper.deleteSpecificationConfigById(id);
    }
}
