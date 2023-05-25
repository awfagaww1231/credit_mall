package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.GoodsSku;

import java.util.List;

/**
 * 商品skuMapper接口
 * 
 * @author ruoyi
 * @date 2022-11-18
 */
public interface GoodsSkuMapper 
{
    /**
     * 查询商品sku
     * 
     * @param id 商品sku主键
     * @return 商品sku
     */
    public GoodsSku selectGoodsSkuById(Long id);

    /**
     * 查询商品sku列表
     * 
     * @param goodsSku 商品sku
     * @return 商品sku集合
     */
    public List<GoodsSku> selectGoodsSkuList(GoodsSku goodsSku);

    /**
     * 新增商品sku
     * 
     * @param goodsSku 商品sku
     * @return 结果
     */
    public int insertGoodsSku(GoodsSku goodsSku);

    /**
     * 修改商品sku
     * 
     * @param goodsSku 商品sku
     * @return 结果
     */
    public int updateGoodsSku(GoodsSku goodsSku);

    /**
     * 删除商品sku
     * 
     * @param id 商品sku主键
     * @return 结果
     */
    public int deleteGoodsSkuById(Long id);

    /**
     * 批量删除商品sku
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteGoodsSkuByIds(Long[] ids);
}
