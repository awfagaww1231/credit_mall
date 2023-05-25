package com.ruoyi.system.service;

import com.ruoyi.system.domain.SuperDealsClassification;

import java.util.List;

/**
 * SuperDeals活动分类配置Service接口
 * 
 * @author ruoyi
 * @date 2022-11-21
 */
public interface ISuperDealsClassificationService 
{
    /**
     * 查询SuperDeals活动分类配置
     * 
     * @param id SuperDeals活动分类配置主键
     * @return SuperDeals活动分类配置
     */
    public SuperDealsClassification selectSuperDealsClassificationById(Long id);

    /**
     * 查询SuperDeals活动分类配置列表
     * 
     * @param superDealsClassification SuperDeals活动分类配置
     * @return SuperDeals活动分类配置集合
     */
    public List<SuperDealsClassification> selectSuperDealsClassificationList(SuperDealsClassification superDealsClassification);

    /**
     * 新增SuperDeals活动分类配置
     * 
     * @param superDealsClassification SuperDeals活动分类配置
     * @return 结果
     */
    public int insertSuperDealsClassification(SuperDealsClassification superDealsClassification);

    /**
     * 修改SuperDeals活动分类配置
     * 
     * @param superDealsClassification SuperDeals活动分类配置
     * @return 结果
     */
    public int updateSuperDealsClassification(SuperDealsClassification superDealsClassification);

    /**
     * 批量删除SuperDeals活动分类配置
     * 
     * @param ids 需要删除的SuperDeals活动分类配置主键集合
     * @return 结果
     */
    public int deleteSuperDealsClassificationByIds(Long[] ids);

    /**
     * 删除SuperDeals活动分类配置信息
     * 
     * @param id SuperDeals活动分类配置主键
     * @return 结果
     */
    public int deleteSuperDealsClassificationById(Long id);
}
