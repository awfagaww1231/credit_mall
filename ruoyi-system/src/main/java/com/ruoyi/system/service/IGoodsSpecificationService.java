package com.ruoyi.system.service;

import com.ruoyi.system.domain.GoodsSpecification;

import java.util.List;

/**
 * 商品对应的规格绑定信息Service接口
 * 
 * @author ruoyi
 * @date 2022-11-24
 */
public interface IGoodsSpecificationService 
{
    /**
     * 查询商品对应的规格绑定信息
     * 
     * @param id 商品对应的规格绑定信息主键
     * @return 商品对应的规格绑定信息
     */
    public GoodsSpecification selectGoodsSpecificationById(Long id);

    /**
     * 查询商品对应的规格绑定信息列表
     * 
     * @param goodsSpecification 商品对应的规格绑定信息
     * @return 商品对应的规格绑定信息集合
     */
    public List<GoodsSpecification> selectGoodsSpecificationList(GoodsSpecification goodsSpecification);

    /**
     * 新增商品对应的规格绑定信息
     * 
     * @param goodsSpecification 商品对应的规格绑定信息
     * @return 结果
     */
    public int insertGoodsSpecification(GoodsSpecification goodsSpecification);

    /**
     * 修改商品对应的规格绑定信息
     * 
     * @param goodsSpecification 商品对应的规格绑定信息
     * @return 结果
     */
    public int updateGoodsSpecification(GoodsSpecification goodsSpecification);

    /**
     * 批量删除商品对应的规格绑定信息
     * 
     * @param ids 需要删除的商品对应的规格绑定信息主键集合
     * @return 结果
     */
    public int deleteGoodsSpecificationByIds(Long[] ids);

    /**
     * 删除商品对应的规格绑定信息信息
     * 
     * @param id 商品对应的规格绑定信息主键
     * @return 结果
     */
    public int deleteGoodsSpecificationById(Long id);
}
