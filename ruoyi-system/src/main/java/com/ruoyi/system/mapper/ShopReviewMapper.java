package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.ShopReview;

import java.util.List;

/**
 * 店铺入驻审核Mapper接口
 * 
 * @author ruoyi
 * @date 2022-10-28
 */
public interface ShopReviewMapper 
{
    /**
     * 查询店铺入驻审核
     * 
     * @param id 店铺入驻审核主键
     * @return 店铺入驻审核
     */
    public ShopReview selectShopReviewById(Long id);

    /**
     * 查询店铺入驻审核列表
     * 
     * @param shopReview 店铺入驻审核
     * @return 店铺入驻审核集合
     */
    public List<ShopReview> selectShopReviewList(ShopReview shopReview);

    /**
     * 新增店铺入驻审核
     * 
     * @param shopReview 店铺入驻审核
     * @return 结果
     */
    public int insertShopReview(ShopReview shopReview);

    /**
     * 修改店铺入驻审核
     * 
     * @param shopReview 店铺入驻审核
     * @return 结果
     */
    public int updateShopReview(ShopReview shopReview);

    /**
     * 删除店铺入驻审核
     * 
     * @param id 店铺入驻审核主键
     * @return 结果
     */
    public int deleteShopReviewById(Long id);

    /**
     * 批量删除店铺入驻审核
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteShopReviewByIds(Long[] ids);
}
