package com.ruoyi.system.service;

import com.ruoyi.system.domain.ShopGoodsInfo;
import com.ruoyi.system.domain.WeeklyDealsClassificationGoods;

import java.util.List;

/**
 * WeeklyDeals活动分类中的商品信息Service接口
 * 
 * @author ruoyi
 * @date 2022-11-21
 */
public interface IWeeklyDealsClassificationGoodsService 
{
    /**
     * 查询WeeklyDeals活动分类中的商品信息
     * 
     * @param id WeeklyDeals活动分类中的商品信息主键
     * @return WeeklyDeals活动分类中的商品信息
     */
    public WeeklyDealsClassificationGoods selectWeeklyDealsClassificationGoodsById(Long id);

    /**
     * 查询WeeklyDeals活动分类中的商品信息列表
     * 
     * @param weeklyDealsClassificationGoods WeeklyDeals活动分类中的商品信息
     * @return WeeklyDeals活动分类中的商品信息集合
     */
    public List<WeeklyDealsClassificationGoods> selectWeeklyDealsClassificationGoodsList(WeeklyDealsClassificationGoods weeklyDealsClassificationGoods);

    /**
     * 新增WeeklyDeals活动分类中的商品信息
     * 
     * @param weeklyDealsClassificationGoods WeeklyDeals活动分类中的商品信息
     * @return 结果
     */
    public int insertWeeklyDealsClassificationGoods(WeeklyDealsClassificationGoods weeklyDealsClassificationGoods);

    /**
     * 修改WeeklyDeals活动分类中的商品信息
     * 
     * @param weeklyDealsClassificationGoods WeeklyDeals活动分类中的商品信息
     * @return 结果
     */
    public int updateWeeklyDealsClassificationGoods(WeeklyDealsClassificationGoods weeklyDealsClassificationGoods);

    /**
     * 批量删除WeeklyDeals活动分类中的商品信息
     * 
     * @param ids 需要删除的WeeklyDeals活动分类中的商品信息主键集合
     * @return 结果
     */
    public int deleteWeeklyDealsClassificationGoodsByIds(Long[] ids);

    /**
     * 删除WeeklyDeals活动分类中的商品信息信息
     * 
     * @param id WeeklyDeals活动分类中的商品信息主键
     * @return 结果
     */
    public int deleteWeeklyDealsClassificationGoodsById(Long id);

    /**
     * 查询店铺的商品信息列表
     *
     * @param shopGoodsInfo 店铺的商品信息
     * @return 店铺的商品信息集合
     */
    public List<ShopGoodsInfo> selectShopGoodsInfoList(ShopGoodsInfo shopGoodsInfo);
}
