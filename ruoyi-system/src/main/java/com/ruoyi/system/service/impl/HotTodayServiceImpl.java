package com.ruoyi.system.service.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.domain.HotToday;
import com.ruoyi.system.domain.ShopGoodsInfo;
import com.ruoyi.system.mapper.HotTodayMapper;
import com.ruoyi.system.service.IHotTodayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 今日热门商品Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-11-23
 */
@Service
public class HotTodayServiceImpl implements IHotTodayService 
{
    @Autowired
    private HotTodayMapper hotTodayMapper;

    /**
     * 查询今日热门商品
     * 
     * @param id 今日热门商品主键
     * @return 今日热门商品
     */
    @Override
    public HotToday selectHotTodayById(Long id)
    {
        return hotTodayMapper.selectHotTodayById(id);
    }

    /**
     * 查询今日热门商品列表
     * 
     * @param hotToday 今日热门商品
     * @return 今日热门商品
     */
    @Override
    public List<HotToday> selectHotTodayList(HotToday hotToday)
    {
        return hotTodayMapper.selectHotTodayList(hotToday);
    }

    /**
     * 新增今日热门商品
     * 
     * @param hotToday 今日热门商品
     * @return 结果
     */
    @Override
    public int insertHotToday(HotToday hotToday)
    {
        hotToday.setCreateTime(DateUtils.getNowDate());
        //排序最大
        Integer maxSort = hotTodayMapper.getMaxSort();
        if (maxSort == null){
            maxSort = 0;

        }
        hotToday.setSort(maxSort+1);
        return hotTodayMapper.insertHotToday(hotToday);
    }

    /**
     * 修改今日热门商品
     * 
     * @param hotToday 今日热门商品
     * @return 结果
     */
    @Override
    public int updateHotToday(HotToday hotToday)
    {
        hotToday.setUpdateTime(DateUtils.getNowDate());
        return hotTodayMapper.updateHotToday(hotToday);
    }

    /**
     * 批量删除今日热门商品
     * 
     * @param ids 需要删除的今日热门商品主键
     * @return 结果
     */
    @Override
    public int deleteHotTodayByIds(Long[] ids)
    {
        return hotTodayMapper.deleteHotTodayByIds(ids);
    }

    /**
     * 删除今日热门商品信息
     * 
     * @param id 今日热门商品主键
     * @return 结果
     */
    @Override
    public int deleteHotTodayById(Long id)
    {
        return hotTodayMapper.deleteHotTodayById(id);
    }

    @Override
    public List<ShopGoodsInfo> selectShopGoodsInfoList(ShopGoodsInfo shopGoodsInfo) {
        return hotTodayMapper.selectShopGoodsInfoList(shopGoodsInfo);
    }
}
