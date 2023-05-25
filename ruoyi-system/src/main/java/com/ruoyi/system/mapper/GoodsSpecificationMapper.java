package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.GoodsSpecification;
import com.ruoyi.system.domain.SpecificationConfig;
import com.ruoyi.system.domain.SpecificationValue;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 商品对应的规格绑定信息Mapper接口
 * 
 * @author ruoyi
 * @date 2022-11-24
 */
public interface GoodsSpecificationMapper 
{
    /**
     * 查询商品对应的规格绑定信息
     * 
     * @param id 商品对应的规格绑定信息主键
     * @return 商品对应的规格绑定信息
     */
    public GoodsSpecification selectGoodsSpecificationById(Long id);

    /**
     * 查询商品对应的规格绑定信息
     *
     * @return 商品对应的规格绑定信息
     */
    public GoodsSpecification selectGoodsSpecification(GoodsSpecification goodsSpecification);

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
     * 删除商品对应的规格绑定信息
     * 
     * @param id 商品对应的规格绑定信息主键
     * @return 结果
     */
    public int deleteGoodsSpecificationById(Long id);

    /**
     * 删除商品对应的规格绑定信息
     *
     * @param goodsId 平台商品id
     * @return 结果
     */
    public int deleteGoodsSpecificationByGoodsId(Long goodsId);

    /**
     * 批量删除商品对应的规格绑定信息
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteGoodsSpecificationByIds(Long[] ids);

    /**
     * 批量删除商品对应的规格绑定信息
     *
     * @param goodsId 平台商品的id
     * @return 结果
     */
    List<SpecificationConfig> selectSpecificationConfigsByGoodsId(Long goodsId);


    List<SpecificationValue> selectSpecificationValues(@Param("goodsId") Long goodsId,
                                                       @Param("specificationConfigId") Long specificationConfigId);

}
