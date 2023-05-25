package com.ruoyi.system.service.impl;

import com.ruoyi.system.domain.GoodsSpecification;
import com.ruoyi.system.mapper.GoodsSpecificationMapper;
import com.ruoyi.system.service.IGoodsSpecificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商品对应的规格绑定信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-11-24
 */
@Service
public class GoodsSpecificationServiceImpl implements IGoodsSpecificationService 
{
    @Autowired
    private GoodsSpecificationMapper goodsSpecificationMapper;

    /**
     * 查询商品对应的规格绑定信息
     * 
     * @param id 商品对应的规格绑定信息主键
     * @return 商品对应的规格绑定信息
     */
    @Override
    public GoodsSpecification selectGoodsSpecificationById(Long id)
    {
        return goodsSpecificationMapper.selectGoodsSpecificationById(id);
    }

    /**
     * 查询商品对应的规格绑定信息列表
     * 
     * @param goodsSpecification 商品对应的规格绑定信息
     * @return 商品对应的规格绑定信息
     */
    @Override
    public List<GoodsSpecification> selectGoodsSpecificationList(GoodsSpecification goodsSpecification)
    {
        return goodsSpecificationMapper.selectGoodsSpecificationList(goodsSpecification);
    }

    /**
     * 新增商品对应的规格绑定信息
     * 
     * @param goodsSpecification 商品对应的规格绑定信息
     * @return 结果
     */
    @Override
    public int insertGoodsSpecification(GoodsSpecification goodsSpecification)
    {
        return goodsSpecificationMapper.insertGoodsSpecification(goodsSpecification);
    }

    /**
     * 修改商品对应的规格绑定信息
     * 
     * @param goodsSpecification 商品对应的规格绑定信息
     * @return 结果
     */
    @Override
    public int updateGoodsSpecification(GoodsSpecification goodsSpecification)
    {
        return goodsSpecificationMapper.updateGoodsSpecification(goodsSpecification);
    }

    /**
     * 批量删除商品对应的规格绑定信息
     * 
     * @param ids 需要删除的商品对应的规格绑定信息主键
     * @return 结果
     */
    @Override
    public int deleteGoodsSpecificationByIds(Long[] ids)
    {
        return goodsSpecificationMapper.deleteGoodsSpecificationByIds(ids);
    }

    /**
     * 删除商品对应的规格绑定信息信息
     * 
     * @param id 商品对应的规格绑定信息主键
     * @return 结果
     */
    @Override
    public int deleteGoodsSpecificationById(Long id)
    {
        return goodsSpecificationMapper.deleteGoodsSpecificationById(id);
    }
}
