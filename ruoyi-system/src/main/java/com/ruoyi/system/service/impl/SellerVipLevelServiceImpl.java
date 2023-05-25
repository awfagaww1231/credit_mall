package com.ruoyi.system.service.impl;

import com.ruoyi.system.domain.SellerVipLevel;
import com.ruoyi.system.mapper.SellerVipLevelMapper;
import com.ruoyi.system.service.ISellerVipLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商户会员等级记录Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-11-11
 */
@Service
public class SellerVipLevelServiceImpl implements ISellerVipLevelService 
{
    @Autowired
    private SellerVipLevelMapper sellerVipLevelMapper;

    /**
     * 查询商户会员等级记录
     * 
     * @param id 商户会员等级记录主键
     * @return 商户会员等级记录
     */
    @Override
    public SellerVipLevel selectSellerVipLevelById(Long id)
    {
        return sellerVipLevelMapper.selectSellerVipLevelById(id);
    }

    @Override
    public SellerVipLevel selectSellerVipLevelBySellerId(Long sellerId) {
        return sellerVipLevelMapper.selectSellerVipLevelBySellerId(sellerId);
    }

    /**
     * 查询商户会员等级记录列表
     * 
     * @param sellerVipLevel 商户会员等级记录
     * @return 商户会员等级记录
     */
    @Override
    public List<SellerVipLevel> selectSellerVipLevelList(SellerVipLevel sellerVipLevel)
    {
        return sellerVipLevelMapper.selectSellerVipLevelList(sellerVipLevel);
    }

    /**
     * 新增商户会员等级记录
     * 
     * @param sellerVipLevel 商户会员等级记录
     * @return 结果
     */
    @Override
    public int insertSellerVipLevel(SellerVipLevel sellerVipLevel)
    {
        return sellerVipLevelMapper.insertSellerVipLevel(sellerVipLevel);
    }

    /**
     * 修改商户会员等级记录
     * 
     * @param sellerVipLevel 商户会员等级记录
     * @return 结果
     */
    @Override
    public int updateSellerVipLevel(SellerVipLevel sellerVipLevel)
    {
        return sellerVipLevelMapper.updateSellerVipLevel(sellerVipLevel);
    }

    /**
     * 批量删除商户会员等级记录
     * 
     * @param ids 需要删除的商户会员等级记录主键
     * @return 结果
     */
    @Override
    public int deleteSellerVipLevelByIds(Long[] ids)
    {
        return sellerVipLevelMapper.deleteSellerVipLevelByIds(ids);
    }

    /**
     * 删除商户会员等级记录信息
     * 
     * @param id 商户会员等级记录主键
     * @return 结果
     */
    @Override
    public int deleteSellerVipLevelById(Long id)
    {
        return sellerVipLevelMapper.deleteSellerVipLevelById(id);
    }
}
