package com.ruoyi.system.service.impl;

import com.ruoyi.system.domain.ShopGoodsInfo;
import com.ruoyi.system.domain.WeeklyDealsClassificationGoods;
import com.ruoyi.system.mapper.WeeklyDealsClassificationGoodsMapper;
import com.ruoyi.system.service.IWeeklyDealsClassificationGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * WeeklyDeals活动分类中的商品信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-11-21
 */
@Service
public class WeeklyDealsClassificationGoodsServiceImpl implements IWeeklyDealsClassificationGoodsService 
{
    @Autowired
    private WeeklyDealsClassificationGoodsMapper weeklyDealsClassificationGoodsMapper;

    /**
     * 查询WeeklyDeals活动分类中的商品信息
     * 
     * @param id WeeklyDeals活动分类中的商品信息主键
     * @return WeeklyDeals活动分类中的商品信息
     */
    @Override
    public WeeklyDealsClassificationGoods selectWeeklyDealsClassificationGoodsById(Long id)
    {
        return weeklyDealsClassificationGoodsMapper.selectWeeklyDealsClassificationGoodsById(id);
    }

    /**
     * 查询WeeklyDeals活动分类中的商品信息列表
     * 
     * @param weeklyDealsClassificationGoods WeeklyDeals活动分类中的商品信息
     * @return WeeklyDeals活动分类中的商品信息
     */
    @Override
    public List<WeeklyDealsClassificationGoods> selectWeeklyDealsClassificationGoodsList(WeeklyDealsClassificationGoods weeklyDealsClassificationGoods)
    {
        return weeklyDealsClassificationGoodsMapper.selectWeeklyDealsClassificationGoodsList(weeklyDealsClassificationGoods);
    }

    /**
     * 新增WeeklyDeals活动分类中的商品信息
     * 
     * @param weeklyDealsClassificationGoods WeeklyDeals活动分类中的商品信息
     * @return 结果
     */
    @Override
    public int insertWeeklyDealsClassificationGoods(WeeklyDealsClassificationGoods weeklyDealsClassificationGoods)
    {
        return weeklyDealsClassificationGoodsMapper.insertWeeklyDealsClassificationGoods(weeklyDealsClassificationGoods);
    }

    /**
     * 修改WeeklyDeals活动分类中的商品信息
     * 
     * @param weeklyDealsClassificationGoods WeeklyDeals活动分类中的商品信息
     * @return 结果
     */
    @Override
    public int updateWeeklyDealsClassificationGoods(WeeklyDealsClassificationGoods weeklyDealsClassificationGoods)
    {
        return weeklyDealsClassificationGoodsMapper.updateWeeklyDealsClassificationGoods(weeklyDealsClassificationGoods);
    }

    /**
     * 批量删除WeeklyDeals活动分类中的商品信息
     * 
     * @param ids 需要删除的WeeklyDeals活动分类中的商品信息主键
     * @return 结果
     */
    @Override
    public int deleteWeeklyDealsClassificationGoodsByIds(Long[] ids)
    {
        return weeklyDealsClassificationGoodsMapper.deleteWeeklyDealsClassificationGoodsByIds(ids);
    }

    /**
     * 删除WeeklyDeals活动分类中的商品信息信息
     * 
     * @param id WeeklyDeals活动分类中的商品信息主键
     * @return 结果
     */
    @Override
    public int deleteWeeklyDealsClassificationGoodsById(Long id)
    {
        return weeklyDealsClassificationGoodsMapper.deleteWeeklyDealsClassificationGoodsById(id);
    }

    @Override
    public List<ShopGoodsInfo> selectShopGoodsInfoList(ShopGoodsInfo shopGoodsInfo) {
        return weeklyDealsClassificationGoodsMapper.selectShopGoodsInfoList(shopGoodsInfo);
    }
}
