package com.ruoyi.system.service.impl;

import com.ruoyi.system.domain.GoodsSku;
import com.ruoyi.system.mapper.GoodsSkuMapper;
import com.ruoyi.system.service.IGoodsSkuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商品skuService业务层处理
 * 
 * @author ruoyi
 * @date 2022-11-18
 */
@Service
public class GoodsSkuServiceImpl implements IGoodsSkuService 
{
    @Autowired
    private GoodsSkuMapper goodsSkuMapper;

    /**
     * 查询商品sku
     * 
     * @param id 商品sku主键
     * @return 商品sku
     */
    @Override
    public GoodsSku selectGoodsSkuById(Long id)
    {
        return goodsSkuMapper.selectGoodsSkuById(id);
    }

    /**
     * 查询商品sku列表
     * 
     * @param goodsSku 商品sku
     * @return 商品sku
     */
    @Override
    public List<GoodsSku> selectGoodsSkuList(GoodsSku goodsSku)
    {
        return goodsSkuMapper.selectGoodsSkuList(goodsSku);
    }

    /**
     * 新增商品sku
     * 
     * @param goodsSku 商品sku
     * @return 结果
     */
    @Override
    public int insertGoodsSku(GoodsSku goodsSku)
    {
        return goodsSkuMapper.insertGoodsSku(goodsSku);
    }

    /**
     * 修改商品sku
     * 
     * @param goodsSku 商品sku
     * @return 结果
     */
    @Override
    public int updateGoodsSku(GoodsSku goodsSku)
    {
        return goodsSkuMapper.updateGoodsSku(goodsSku);
    }

    /**
     * 批量删除商品sku
     * 
     * @param ids 需要删除的商品sku主键
     * @return 结果
     */
    @Override
    public int deleteGoodsSkuByIds(Long[] ids)
    {
        return goodsSkuMapper.deleteGoodsSkuByIds(ids);
    }

    /**
     * 删除商品sku信息
     * 
     * @param id 商品sku主键
     * @return 结果
     */
    @Override
    public int deleteGoodsSkuById(Long id)
    {
        return goodsSkuMapper.deleteGoodsSkuById(id);
    }
}
