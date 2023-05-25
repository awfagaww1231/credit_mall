package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.SellerVipConfig;

import java.util.List;

/**
 * 商家会员等级条件配置信息Mapper接口
 * 
 * @author ruoyi
 * @date 2022-11-11
 */
public interface SellerVipConfigMapper 
{
    /**
     * 查询商家会员等级条件配置信息
     * 
     * @param id 商家会员等级条件配置信息主键
     * @return 商家会员等级条件配置信息
     */
    public SellerVipConfig selectSellerVipConfigById(Long id);

    /**
     * 查询商家会员等级条件配置信息列表
     * 
     * @param sellerVipConfig 商家会员等级条件配置信息
     * @return 商家会员等级条件配置信息集合
     */
    public List<SellerVipConfig> selectSellerVipConfigList(SellerVipConfig sellerVipConfig);

    /**
     * 新增商家会员等级条件配置信息
     * 
     * @param sellerVipConfig 商家会员等级条件配置信息
     * @return 结果
     */
    public int insertSellerVipConfig(SellerVipConfig sellerVipConfig);

    /**
     * 修改商家会员等级条件配置信息
     * 
     * @param sellerVipConfig 商家会员等级条件配置信息
     * @return 结果
     */
    public int updateSellerVipConfig(SellerVipConfig sellerVipConfig);

    /**
     * 删除商家会员等级条件配置信息
     * 
     * @param id 商家会员等级条件配置信息主键
     * @return 结果
     */
    public int deleteSellerVipConfigById(Long id);

    /**
     * 批量删除商家会员等级条件配置信息
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSellerVipConfigByIds(Long[] ids);
}
