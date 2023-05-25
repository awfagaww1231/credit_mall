package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.SpecificationConfig;

import java.util.List;

/**
 * 商品规格配置Mapper接口
 * 
 * @author ruoyi
 * @date 2022-11-18
 */
public interface SpecificationConfigMapper 
{
    /**
     * 查询商品规格配置
     * 
     * @param id 商品规格配置主键
     * @return 商品规格配置
     */
    public SpecificationConfig selectSpecificationConfigById(Long id);

    /**
     * 查询商品规格配置列表
     * 
     * @param specificationConfig 商品规格配置
     * @return 商品规格配置集合
     */
    public List<SpecificationConfig> selectSpecificationConfigList(SpecificationConfig specificationConfig);

    /**
     * 新增商品规格配置
     * 
     * @param specificationConfig 商品规格配置
     * @return 结果
     */
    public int insertSpecificationConfig(SpecificationConfig specificationConfig);

    /**
     * 修改商品规格配置
     * 
     * @param specificationConfig 商品规格配置
     * @return 结果
     */
    public int updateSpecificationConfig(SpecificationConfig specificationConfig);

    /**
     * 删除商品规格配置
     * 
     * @param id 商品规格配置主键
     * @return 结果
     */
    public int deleteSpecificationConfigById(Long id);

    /**
     * 批量删除商品规格配置
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSpecificationConfigByIds(Long[] ids);
}
