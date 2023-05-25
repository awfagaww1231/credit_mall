package com.ruoyi.system.service.impl;

import com.ruoyi.system.domain.ShopGoodsInfo;
import com.ruoyi.system.domain.SuperDealsClassificationGoods;
import com.ruoyi.system.mapper.SuperDealsClassificationGoodsMapper;
import com.ruoyi.system.service.ISuperDealsClassificationGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * SuperDeals活动分类中的商品信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-11-21
 */
@Service
public class SuperDealsClassificationGoodsServiceImpl implements ISuperDealsClassificationGoodsService 
{
    @Autowired
    private SuperDealsClassificationGoodsMapper superDealsClassificationGoodsMapper;

    /**
     * 查询SuperDeals活动分类中的商品信息
     * 
     * @param id SuperDeals活动分类中的商品信息主键
     * @return SuperDeals活动分类中的商品信息
     */
    @Override
    public SuperDealsClassificationGoods selectSuperDealsClassificationGoodsById(Long id)
    {
        return superDealsClassificationGoodsMapper.selectSuperDealsClassificationGoodsById(id);
    }

    /**
     * 查询SuperDeals活动分类中的商品信息列表
     * 
     * @param superDealsClassificationGoods SuperDeals活动分类中的商品信息
     * @return SuperDeals活动分类中的商品信息
     */
    @Override
    public List<SuperDealsClassificationGoods> selectSuperDealsClassificationGoodsList(SuperDealsClassificationGoods superDealsClassificationGoods)
    {
        return superDealsClassificationGoodsMapper.selectSuperDealsClassificationGoodsList(superDealsClassificationGoods);
    }

    /**
     * 新增SuperDeals活动分类中的商品信息
     * 
     * @param superDealsClassificationGoods SuperDeals活动分类中的商品信息
     * @return 结果
     */
    @Override
    public int insertSuperDealsClassificationGoods(SuperDealsClassificationGoods superDealsClassificationGoods)
    {
        return superDealsClassificationGoodsMapper.insertSuperDealsClassificationGoods(superDealsClassificationGoods);
    }

    /**
     * 修改SuperDeals活动分类中的商品信息
     * 
     * @param superDealsClassificationGoods SuperDeals活动分类中的商品信息
     * @return 结果
     */
    @Override
    public int updateSuperDealsClassificationGoods(SuperDealsClassificationGoods superDealsClassificationGoods)
    {
        return superDealsClassificationGoodsMapper.updateSuperDealsClassificationGoods(superDealsClassificationGoods);
    }

    /**
     * 批量删除SuperDeals活动分类中的商品信息
     * 
     * @param ids 需要删除的SuperDeals活动分类中的商品信息主键
     * @return 结果
     */
    @Override
    public int deleteSuperDealsClassificationGoodsByIds(Long[] ids)
    {
        return superDealsClassificationGoodsMapper.deleteSuperDealsClassificationGoodsByIds(ids);
    }

    /**
     * 删除SuperDeals活动分类中的商品信息信息
     * 
     * @param id SuperDeals活动分类中的商品信息主键
     * @return 结果
     */
    @Override
    public int deleteSuperDealsClassificationGoodsById(Long id)
    {
        return superDealsClassificationGoodsMapper.deleteSuperDealsClassificationGoodsById(id);
    }

    @Override
    public List<ShopGoodsInfo> selectShopGoodsInfoList(ShopGoodsInfo shopGoodsInfo) {
        return superDealsClassificationGoodsMapper.selectShopGoodsInfoList(shopGoodsInfo);
    }
}
