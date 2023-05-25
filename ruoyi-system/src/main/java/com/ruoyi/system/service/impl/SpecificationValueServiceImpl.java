package com.ruoyi.system.service.impl;

import com.ruoyi.system.domain.SpecificationValue;
import com.ruoyi.system.mapper.SpecificationValueMapper;
import com.ruoyi.system.service.ISpecificationValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商品规格值Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-11-18
 */
@Service
public class SpecificationValueServiceImpl implements ISpecificationValueService 
{
    @Autowired
    private SpecificationValueMapper specificationValueMapper;

    /**
     * 查询商品规格值
     * 
     * @param id 商品规格值主键
     * @return 商品规格值
     */
    @Override
    public SpecificationValue selectSpecificationValueById(Long id)
    {
        return specificationValueMapper.selectSpecificationValueById(id);
    }

    /**
     * 查询商品规格值列表
     * 
     * @param specificationValue 商品规格值
     * @return 商品规格值
     */
    @Override
    public List<SpecificationValue> selectSpecificationValueList(SpecificationValue specificationValue)
    {
        return specificationValueMapper.selectSpecificationValueList(specificationValue);
    }

    /**
     * 新增商品规格值
     * 
     * @param specificationValue 商品规格值
     * @return 结果
     */
    @Override
    public int insertSpecificationValue(SpecificationValue specificationValue)
    {
        return specificationValueMapper.insertSpecificationValue(specificationValue);
    }

    /**
     * 修改商品规格值
     * 
     * @param specificationValue 商品规格值
     * @return 结果
     */
    @Override
    public int updateSpecificationValue(SpecificationValue specificationValue)
    {
        return specificationValueMapper.updateSpecificationValue(specificationValue);
    }

    /**
     * 批量删除商品规格值
     * 
     * @param ids 需要删除的商品规格值主键
     * @return 结果
     */
    @Override
    public int deleteSpecificationValueByIds(Long[] ids)
    {
        return specificationValueMapper.deleteSpecificationValueByIds(ids);
    }

    /**
     * 删除商品规格值信息
     * 
     * @param id 商品规格值主键
     * @return 结果
     */
    @Override
    public int deleteSpecificationValueById(Long id)
    {
        return specificationValueMapper.deleteSpecificationValueById(id);
    }
}
