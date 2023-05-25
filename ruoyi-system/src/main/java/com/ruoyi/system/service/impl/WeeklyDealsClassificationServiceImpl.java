package com.ruoyi.system.service.impl;

import com.ruoyi.system.domain.WeeklyDealsClassification;
import com.ruoyi.system.mapper.WeeklyDealsClassificationMapper;
import com.ruoyi.system.service.IWeeklyDealsClassificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * WeeklyDeals活动分类配置Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-11-21
 */
@Service
public class WeeklyDealsClassificationServiceImpl implements IWeeklyDealsClassificationService 
{
    @Autowired
    private WeeklyDealsClassificationMapper weeklyDealsClassificationMapper;

    /**
     * 查询WeeklyDeals活动分类配置
     * 
     * @param id WeeklyDeals活动分类配置主键
     * @return WeeklyDeals活动分类配置
     */
    @Override
    public WeeklyDealsClassification selectWeeklyDealsClassificationById(Long id)
    {
        return weeklyDealsClassificationMapper.selectWeeklyDealsClassificationById(id);
    }

    /**
     * 查询WeeklyDeals活动分类配置列表
     * 
     * @param weeklyDealsClassification WeeklyDeals活动分类配置
     * @return WeeklyDeals活动分类配置
     */
    @Override
    public List<WeeklyDealsClassification> selectWeeklyDealsClassificationList(WeeklyDealsClassification weeklyDealsClassification)
    {
        return weeklyDealsClassificationMapper.selectWeeklyDealsClassificationList(weeklyDealsClassification);
    }

    /**
     * 新增WeeklyDeals活动分类配置
     * 
     * @param weeklyDealsClassification WeeklyDeals活动分类配置
     * @return 结果
     */
    @Override
    public int insertWeeklyDealsClassification(WeeklyDealsClassification weeklyDealsClassification)
    {
        return weeklyDealsClassificationMapper.insertWeeklyDealsClassification(weeklyDealsClassification);
    }

    /**
     * 修改WeeklyDeals活动分类配置
     * 
     * @param weeklyDealsClassification WeeklyDeals活动分类配置
     * @return 结果
     */
    @Override
    public int updateWeeklyDealsClassification(WeeklyDealsClassification weeklyDealsClassification)
    {
        return weeklyDealsClassificationMapper.updateWeeklyDealsClassification(weeklyDealsClassification);
    }

    /**
     * 批量删除WeeklyDeals活动分类配置
     * 
     * @param ids 需要删除的WeeklyDeals活动分类配置主键
     * @return 结果
     */
    @Override
    public int deleteWeeklyDealsClassificationByIds(Long[] ids)
    {
        return weeklyDealsClassificationMapper.deleteWeeklyDealsClassificationByIds(ids);
    }

    /**
     * 删除WeeklyDeals活动分类配置信息
     * 
     * @param id WeeklyDeals活动分类配置主键
     * @return 结果
     */
    @Override
    public int deleteWeeklyDealsClassificationById(Long id)
    {
        return weeklyDealsClassificationMapper.deleteWeeklyDealsClassificationById(id);
    }
}
