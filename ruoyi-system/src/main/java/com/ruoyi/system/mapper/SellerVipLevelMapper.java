package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.SellerVipLevel;

import java.util.List;

/**
 * 商户会员等级记录Mapper接口
 * 
 * @author ruoyi
 * @date 2022-11-11
 */
public interface SellerVipLevelMapper 
{
    /**
     * 查询商户会员等级记录
     * 
     * @param id 商户会员等级记录主键
     * @return 商户会员等级记录
     */
    public SellerVipLevel selectSellerVipLevelById(Long id);

    /**
     * 查询商户会员等级记录
     *
     * @param sellerId 商户id
     * @return 商户会员等级记录
     */
    public SellerVipLevel selectSellerVipLevelBySellerId(Long sellerId);


    /**
     * 查询商户会员等级记录列表
     * 
     * @param sellerVipLevel 商户会员等级记录
     * @return 商户会员等级记录集合
     */
    public List<SellerVipLevel> selectSellerVipLevelList(SellerVipLevel sellerVipLevel);

    /**
     * 新增商户会员等级记录
     * 
     * @param sellerVipLevel 商户会员等级记录
     * @return 结果
     */
    public int insertSellerVipLevel(SellerVipLevel sellerVipLevel);

    /**
     * 修改商户会员等级记录
     * 
     * @param sellerVipLevel 商户会员等级记录
     * @return 结果
     */
    public int updateSellerVipLevel(SellerVipLevel sellerVipLevel);

    /**
     * 删除商户会员等级记录
     * 
     * @param id 商户会员等级记录主键
     * @return 结果
     */
    public int deleteSellerVipLevelById(Long id);

    /**
     * 批量删除商户会员等级记录
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSellerVipLevelByIds(Long[] ids);
}
