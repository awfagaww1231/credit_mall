package com.ruoyi.system.service.impl;

import com.ruoyi.common.annotation.XXDataScope;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.domain.ShopInfo;
import com.ruoyi.system.domain.ShopReview;
import com.ruoyi.system.mapper.ShopReviewMapper;
import com.ruoyi.system.service.IShopInfoService;
import com.ruoyi.system.service.IShopReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 店铺入驻审核Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-10-28
 */
@Service
public class ShopReviewServiceImpl implements IShopReviewService
{
    @Autowired
    private ShopReviewMapper shopReviewMapper;

    @Autowired
    private IShopInfoService shopInfoService;

    /**
     * 查询店铺入驻审核
     * 
     * @param id 店铺入驻审核主键
     * @return 店铺入驻审核
     */
    @Override
    public ShopReview selectShopReviewById(Long id)
    {
        return shopReviewMapper.selectShopReviewById(id);
    }

    /**
     * 查询店铺入驻审核列表
     * 
     * @param shopReview 店铺入驻审核
     * @return 店铺入驻审核
     */
    @Override
    @XXDataScope(deptAlias = "d", userAlias = "u" ,sysUserIdAlias = "shop_owner_id")
    public List<ShopReview> selectShopReviewList(ShopReview shopReview)
    {
        return shopReviewMapper.selectShopReviewList(shopReview);
    }

    /**
     * 新增店铺入驻审核
     * 
     * @param shopReview 店铺入驻审核
     * @return 结果
     */
    @Override
    public int insertShopReview(ShopReview shopReview)
    {
        shopReview.setCreateTime(DateUtils.getNowDate());
        return shopReviewMapper.insertShopReview(shopReview);
    }

    /**
     * 修改店铺入驻审核
     * 
     * @param shopReview 店铺入驻审核
     * @return 结果
     */
    @Override
    public int updateShopReview(ShopReview shopReview)
    {
        shopReview.setUpdateTime(DateUtils.getNowDate());
        return shopReviewMapper.updateShopReview(shopReview);
    }

    /**
     * 批量删除店铺入驻审核
     * 
     * @param ids 需要删除的店铺入驻审核主键
     * @return 结果
     */
    @Override
    public int deleteShopReviewByIds(Long[] ids)
    {
        return shopReviewMapper.deleteShopReviewByIds(ids);
    }

    /**
     * 删除店铺入驻审核信息
     * 
     * @param id 店铺入驻审核主键
     * @return 结果
     */
    @Override
    public int deleteShopReviewById(Long id)
    {
        return shopReviewMapper.deleteShopReviewById(id);
    }

    @Override
    public int updateStatus(ShopReview shopReview) {
        //如果通过申请，则添加新的店铺信息
        if (shopReview.getStatus() == 1){
            ShopInfo shopInfo = new ShopInfo();
            shopInfo.setShopName(shopReview.getShopName());
            shopInfo.setShopLogo(shopReview.getShopLogo());
            shopInfo.setShopTel(shopReview.getShopTel());
            shopInfo.setShopAddress(shopReview.getShopAddress());
            shopInfo.setReceiveAddress(shopReview.getReceiveAddress());
            shopInfo.setStatus(0);
            shopInfo.setShopOwnerId(shopReview.getShopOwnerId());
            shopInfoService.insertShopInfo(shopInfo);
        }
        shopReview.setUpdateTime(DateUtils.getNowDate());
        return shopReviewMapper.updateShopReview(shopReview);
    }
}
