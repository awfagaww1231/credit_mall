package com.ruoyi.system.service.impl;

import com.ruoyi.system.domain.SuperDealsClassification;
import com.ruoyi.system.mapper.SuperDealsClassificationMapper;
import com.ruoyi.system.service.ISuperDealsClassificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * SuperDeals活动分类配置Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-11-21
 */
@Service
public class SuperDealsClassificationServiceImpl implements ISuperDealsClassificationService 
{
    @Autowired
    private SuperDealsClassificationMapper superDealsClassificationMapper;

    /**
     * 查询SuperDeals活动分类配置
     * 
     * @param id SuperDeals活动分类配置主键
     * @return SuperDeals活动分类配置
     */
    @Override
    public SuperDealsClassification selectSuperDealsClassificationById(Long id)
    {
        return superDealsClassificationMapper.selectSuperDealsClassificationById(id);
    }

    /**
     * 查询SuperDeals活动分类配置列表
     * 
     * @param superDealsClassification SuperDeals活动分类配置
     * @return SuperDeals活动分类配置
     */
    @Override
    public List<SuperDealsClassification> selectSuperDealsClassificationList(SuperDealsClassification superDealsClassification)
    {
        return superDealsClassificationMapper.selectSuperDealsClassificationList(superDealsClassification);
    }

    /**
     * 新增SuperDeals活动分类配置
     * 
     * @param superDealsClassification SuperDeals活动分类配置
     * @return 结果
     */
    @Override
    public int insertSuperDealsClassification(SuperDealsClassification superDealsClassification)
    {
        return superDealsClassificationMapper.insertSuperDealsClassification(superDealsClassification);
    }

    /**
     * 修改SuperDeals活动分类配置
     * 
     * @param superDealsClassification SuperDeals活动分类配置
     * @return 结果
     */
    @Override
    public int updateSuperDealsClassification(SuperDealsClassification superDealsClassification)
    {
        return superDealsClassificationMapper.updateSuperDealsClassification(superDealsClassification);
    }

    /**
     * 批量删除SuperDeals活动分类配置
     * 
     * @param ids 需要删除的SuperDeals活动分类配置主键
     * @return 结果
     */
    @Override
    public int deleteSuperDealsClassificationByIds(Long[] ids)
    {
        return superDealsClassificationMapper.deleteSuperDealsClassificationByIds(ids);
    }

    /**
     * 删除SuperDeals活动分类配置信息
     * 
     * @param id SuperDeals活动分类配置主键
     * @return 结果
     */
    @Override
    public int deleteSuperDealsClassificationById(Long id)
    {
        return superDealsClassificationMapper.deleteSuperDealsClassificationById(id);
    }
}
