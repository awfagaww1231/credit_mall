package com.ruoyi.system.service;

import com.ruoyi.system.domain.ShopGoodsInfo;
import com.ruoyi.system.domain.SuperDealsClassificationGoods;

import java.util.List;

/**
 * SuperDeals活动分类中的商品信息Service接口
 * 
 * @author ruoyi
 * @date 2022-11-21
 */
public interface ISuperDealsClassificationGoodsService 
{
    /**
     * 查询SuperDeals活动分类中的商品信息
     * 
     * @param id SuperDeals活动分类中的商品信息主键
     * @return SuperDeals活动分类中的商品信息
     */
    public SuperDealsClassificationGoods selectSuperDealsClassificationGoodsById(Long id);

    /**
     * 查询SuperDeals活动分类中的商品信息列表
     * 
     * @param superDealsClassificationGoods SuperDeals活动分类中的商品信息
     * @return SuperDeals活动分类中的商品信息集合
     */
    public List<SuperDealsClassificationGoods> selectSuperDealsClassificationGoodsList(SuperDealsClassificationGoods superDealsClassificationGoods);

    /**
     * 新增SuperDeals活动分类中的商品信息
     * 
     * @param superDealsClassificationGoods SuperDeals活动分类中的商品信息
     * @return 结果
     */
    public int insertSuperDealsClassificationGoods(SuperDealsClassificationGoods superDealsClassificationGoods);

    /**
     * 修改SuperDeals活动分类中的商品信息
     * 
     * @param superDealsClassificationGoods SuperDeals活动分类中的商品信息
     * @return 结果
     */
    public int updateSuperDealsClassificationGoods(SuperDealsClassificationGoods superDealsClassificationGoods);

    /**
     * 批量删除SuperDeals活动分类中的商品信息
     * 
     * @param ids 需要删除的SuperDeals活动分类中的商品信息主键集合
     * @return 结果
     */
    public int deleteSuperDealsClassificationGoodsByIds(Long[] ids);

    /**
     * 删除SuperDeals活动分类中的商品信息信息
     * 
     * @param id SuperDeals活动分类中的商品信息主键
     * @return 结果
     */
    public int deleteSuperDealsClassificationGoodsById(Long id);

    /**
     * 查询店铺的商品信息列表
     *
     * @param shopGoodsInfo 店铺的商品信息
     * @return 店铺的商品信息集合
     */
    public List<ShopGoodsInfo> selectShopGoodsInfoList(ShopGoodsInfo shopGoodsInfo);
}
