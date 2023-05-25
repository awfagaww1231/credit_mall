package com.ruoyi.system.service;

import com.ruoyi.system.domain.HotToday;
import com.ruoyi.system.domain.ShopGoodsInfo;

import java.util.List;

/**
 * 今日热门商品Service接口
 * 
 * @author ruoyi
 * @date 2022-11-23
 */
public interface IHotTodayService 
{
    /**
     * 查询今日热门商品
     * 
     * @param id 今日热门商品主键
     * @return 今日热门商品
     */
    public HotToday selectHotTodayById(Long id);

    /**
     * 查询今日热门商品列表
     * 
     * @param hotToday 今日热门商品
     * @return 今日热门商品集合
     */
    public List<HotToday> selectHotTodayList(HotToday hotToday);

    /**
     * 新增今日热门商品
     * 
     * @param hotToday 今日热门商品
     * @return 结果
     */
    public int insertHotToday(HotToday hotToday);

    /**
     * 修改今日热门商品
     * 
     * @param hotToday 今日热门商品
     * @return 结果
     */
    public int updateHotToday(HotToday hotToday);

    /**
     * 批量删除今日热门商品
     * 
     * @param ids 需要删除的今日热门商品主键集合
     * @return 结果
     */
    public int deleteHotTodayByIds(Long[] ids);

    /**
     * 删除今日热门商品信息
     * 
     * @param id 今日热门商品主键
     * @return 结果
     */
    public int deleteHotTodayById(Long id);

    /**
     * 查询店铺的商品信息列表
     *
     * @param shopGoodsInfo 店铺的商品信息
     * @return 店铺的商品信息集合
     */
    public List<ShopGoodsInfo> selectShopGoodsInfoList(ShopGoodsInfo shopGoodsInfo);
}
