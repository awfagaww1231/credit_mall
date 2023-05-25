package com.ruoyi.system.service;

import com.ruoyi.system.domain.WeeklyDealsClassification;

import java.util.List;

/**
 * WeeklyDeals活动分类配置Service接口
 * 
 * @author ruoyi
 * @date 2022-11-21
 */
public interface IWeeklyDealsClassificationService 
{
    /**
     * 查询WeeklyDeals活动分类配置
     * 
     * @param id WeeklyDeals活动分类配置主键
     * @return WeeklyDeals活动分类配置
     */
    public WeeklyDealsClassification selectWeeklyDealsClassificationById(Long id);

    /**
     * 查询WeeklyDeals活动分类配置列表
     * 
     * @param weeklyDealsClassification WeeklyDeals活动分类配置
     * @return WeeklyDeals活动分类配置集合
     */
    public List<WeeklyDealsClassification> selectWeeklyDealsClassificationList(WeeklyDealsClassification weeklyDealsClassification);

    /**
     * 新增WeeklyDeals活动分类配置
     * 
     * @param weeklyDealsClassification WeeklyDeals活动分类配置
     * @return 结果
     */
    public int insertWeeklyDealsClassification(WeeklyDealsClassification weeklyDealsClassification);

    /**
     * 修改WeeklyDeals活动分类配置
     * 
     * @param weeklyDealsClassification WeeklyDeals活动分类配置
     * @return 结果
     */
    public int updateWeeklyDealsClassification(WeeklyDealsClassification weeklyDealsClassification);

    /**
     * 批量删除WeeklyDeals活动分类配置
     * 
     * @param ids 需要删除的WeeklyDeals活动分类配置主键集合
     * @return 结果
     */
    public int deleteWeeklyDealsClassificationByIds(Long[] ids);

    /**
     * 删除WeeklyDeals活动分类配置信息
     * 
     * @param id WeeklyDeals活动分类配置主键
     * @return 结果
     */
    public int deleteWeeklyDealsClassificationById(Long id);
}
