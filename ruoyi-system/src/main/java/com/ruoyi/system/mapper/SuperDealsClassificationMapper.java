package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.SuperDealsClassification;

import java.util.List;

/**
 * SuperDeals活动分类配置Mapper接口
 *
 * @author ruoyi
 * @date 2023-05-08
 */
public interface SuperDealsClassificationMapper
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
     * 删除SuperDeals活动分类配置
     *
     * @param id SuperDeals活动分类配置主键
     * @return 结果
     */
    public int deleteSuperDealsClassificationById(Long id);

    /**
     * 批量删除SuperDeals活动分类配置
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSuperDealsClassificationByIds(Long[] ids);
}
