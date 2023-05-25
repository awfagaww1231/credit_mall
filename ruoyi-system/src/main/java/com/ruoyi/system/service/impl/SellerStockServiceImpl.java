package com.ruoyi.system.service.impl;

import com.ruoyi.common.annotation.XXDataScope;
import com.ruoyi.system.domain.PlatformGoodsInfo;
import com.ruoyi.system.domain.SellerStock;
import com.ruoyi.system.domain.SupplyOrder;
import com.ruoyi.system.mapper.PlatformGoodsInfoMapper;
import com.ruoyi.system.mapper.SellerStockMapper;
import com.ruoyi.system.service.ICategoryService;
import com.ruoyi.system.service.ISellerStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 卖家的可售库存信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-10-30
 */
@Service
public class SellerStockServiceImpl implements ISellerStockService 
{
    @Autowired
    private SellerStockMapper sellerStockMapper;

    @Autowired
    private PlatformGoodsInfoMapper platformGoodsInfoMapper;

    @Autowired
    private ICategoryService categoryService;

    /**
     * 查询卖家的可售库存信息
     * 
     * @param id 卖家的可售库存信息主键
     * @return 卖家的可售库存信息
     */
    @Override
    public SellerStock selectSellerStockById(Long id)
    {
        return sellerStockMapper.selectSellerStockById(id);
    }

    /**
     * 查询卖家的可售库存信息列表
     * 
     * @param sellerStock 卖家的可售库存信息
     * @return 卖家的可售库存信息
     */
    @Override
    @XXDataScope(deptAlias = "d", userAlias = "u" ,sysUserIdAlias = "seller_id")
    public List<SellerStock> selectSellerStockList(SellerStock sellerStock)
    {
        List<SellerStock> sellerStocks = sellerStockMapper.selectSellerStockList(sellerStock);
        for (int i = 0; i < sellerStocks.size(); i++) {
            String categoryName = categoryService.getCategoryName(sellerStocks.get(i).getMinorClass());
            sellerStocks.get(i).setCategoryName(categoryName);
        }
        return sellerStocks;
    }

    /**
     * 新增卖家的可售库存信息
     * 
     * @param sellerStock 卖家的可售库存信息
     * @return 结果
     */
    @Override
    public int insertSellerStock(SellerStock sellerStock)
    {
        return sellerStockMapper.insertSellerStock(sellerStock);
    }

    /**
     * 修改卖家的可售库存信息
     * 
     * @param sellerStock 卖家的可售库存信息
     * @return 结果
     */
    @Override
    public int updateSellerStock(SellerStock sellerStock)
    {
        return sellerStockMapper.updateSellerStock(sellerStock);
    }

    /**
     * 批量删除卖家的可售库存信息
     * 
     * @param ids 需要删除的卖家的可售库存信息主键
     * @return 结果
     */
    @Override
    public int deleteSellerStockByIds(Long[] ids)
    {
        return sellerStockMapper.deleteSellerStockByIds(ids);
    }

    /**
     * 删除卖家的可售库存信息信息
     * 
     * @param id 卖家的可售库存信息主键
     * @return 结果
     */
    @Override
    public int deleteSellerStockById(Long id)
    {
        return sellerStockMapper.deleteSellerStockById(id);
    }

    @Override
    public List<PlatformGoodsInfo> availableForPurchase(PlatformGoodsInfo platformGoodsInfo) {
        platformGoodsInfo.setIsDel(0);
        platformGoodsInfo.setStatus(0);
        List<PlatformGoodsInfo> platformGoodsInfos = platformGoodsInfoMapper.selectPlatformGoodsInfoList(platformGoodsInfo);
        for (int i = 0; i < platformGoodsInfos.size(); i++) {
            String categoryName = categoryService.getCategoryName(platformGoodsInfos.get(i).getMinorClass());
            platformGoodsInfos.get(i).setCategoryName(categoryName);
        }
        return platformGoodsInfos;
    }

    @Override
    public int toRestock(List<SupplyOrder> supplyOrders) {
        return sellerStockMapper.toRestock(supplyOrders);
    }

    @Override
    public SellerStock selectSellerStockByGoodsIdAndSellerId(Long goodsId, Long sellerId) {
        return sellerStockMapper.selectSellerStockByGoodsIdAndSellerId(goodsId,sellerId);
    }

}
