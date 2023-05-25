package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.SpecificationValue;

import java.util.List;

/**
 * 商品规格值Mapper接口
 * 
 * @author ruoyi
 * @date 2022-11-18
 */
public interface SpecificationValueMapper 
{
    /**
     * 查询商品规格值
     * 
     * @param id 商品规格值主键
     * @return 商品规格值
     */
    public SpecificationValue selectSpecificationValueById(Long id);

    /**
     * 查询商品规格值列表
     * 
     * @param specificationValue 商品规格值
     * @return 商品规格值集合
     */
    public List<SpecificationValue> selectSpecificationValueList(SpecificationValue specificationValue);

    /**
     * 新增商品规格值
     * 
     * @param specificationValue 商品规格值
     * @return 结果
     */
    public int insertSpecificationValue(SpecificationValue specificationValue);

    /**
     * 修改商品规格值
     * 
     * @param specificationValue 商品规格值
     * @return 结果
     */
    public int updateSpecificationValue(SpecificationValue specificationValue);

    /**
     * 删除商品规格值
     * 
     * @param id 商品规格值主键
     * @return 结果
     */
    public int deleteSpecificationValueById(Long id);

    /**
     * 批量删除商品规格值
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSpecificationValueByIds(Long[] ids);
}
