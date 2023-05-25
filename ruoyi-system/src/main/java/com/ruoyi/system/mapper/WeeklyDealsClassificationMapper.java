package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.WeeklyDealsClassification;

import java.util.List;

/**
 * WeeklyDeals活动分类配置Mapper接口
 * 
 * @author ruoyi
 * @date 2022-11-21
 */
public interface WeeklyDealsClassificationMapper 
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
     * 删除WeeklyDeals活动分类配置
     * 
     * @param id WeeklyDeals活动分类配置主键
     * @return 结果
     */
    public int deleteWeeklyDealsClassificationById(Long id);

    /**
     * 批量删除WeeklyDeals活动分类配置
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteWeeklyDealsClassificationByIds(Long[] ids);
}
