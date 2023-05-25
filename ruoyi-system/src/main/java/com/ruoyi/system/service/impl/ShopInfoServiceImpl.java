package com.ruoyi.system.service.impl;

import com.ruoyi.common.annotation.XXDataScope;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.domain.ShopGoodsInfo;
import com.ruoyi.system.domain.ShopInfo;
import com.ruoyi.system.mapper.ShopInfoMapper;
import com.ruoyi.system.service.IShopGoodsInfoService;
import com.ruoyi.system.service.IShopInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 店铺信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-10-28
 */
@Service
public class ShopInfoServiceImpl implements IShopInfoService 
{
    @Autowired
    private ShopInfoMapper shopInfoMapper;

    @Autowired
    private IShopGoodsInfoService shopGoodsInfoService;

    /**
     * 查询店铺信息
     * 
     * @param id 店铺信息主键
     * @return 店铺信息
     */
    @Override
    public ShopInfo selectShopInfoById(Long id)
    {
        return shopInfoMapper.selectShopInfoById(id);
    }

    /**
     * 查询店铺信息列表
     * 
     * @param shopInfo 店铺信息
     * @return 店铺信息
     */
    @Override
    @XXDataScope(deptAlias = "d", userAlias = "u" ,sysUserIdAlias = "shop_owner_id")
    public List<ShopInfo> selectShopInfoList(ShopInfo shopInfo)
    {
        return shopInfoMapper.selectShopInfoList(shopInfo);
    }

    /**
     * 新增店铺信息
     * 
     * @param shopInfo 店铺信息
     * @return 结果
     */
    @Override
    public int insertShopInfo(ShopInfo shopInfo)
    {
        shopInfo.setCreateTime(DateUtils.getNowDate());
        return shopInfoMapper.insertShopInfo(shopInfo);
    }

    /**
     * 修改店铺信息
     * 
     * @param shopInfo 店铺信息
     * @return 结果
     */
    @Override
    public int updateShopInfo(ShopInfo shopInfo)
    {
        shopInfo.setUpdateTime(DateUtils.getNowDate());
        return shopInfoMapper.updateShopInfo(shopInfo);
    }

    /**
     * 批量删除店铺信息
     * 
     * @param ids 需要删除的店铺信息主键
     * @return 结果
     */
    @Override
    public int deleteShopInfoByIds(Long[] ids)
    {
        for (int i = 0; i < ids.length; i++) {
            Long id = ids[i];
            //删除店铺的时候，删除店铺商品信息
            ShopGoodsInfo shopGoodsInfo = new ShopGoodsInfo();
            shopGoodsInfo.setShopId(id);
            List<ShopGoodsInfo> shopGoodsInfos = shopGoodsInfoService.selectShopGoodsInfoList(shopGoodsInfo);
            for (int k = 0; k < shopGoodsInfos.size(); k++) {
                Long shopGoodsInfoId = shopGoodsInfos.get(k).getId();
                shopGoodsInfoService.deleteShopGoodsInfoById(shopGoodsInfoId);
            }
            return shopInfoMapper.deleteShopInfoById(id);
        }
        return shopInfoMapper.deleteShopInfoByIds(ids);
    }

    /**
     * 删除店铺信息信息
     * 
     * @param id 店铺信息主键
     * @return 结果
     */
    @Override
    public int deleteShopInfoById(Long id)
    {
        //删除店铺的时候，删除店铺商品信息
        ShopGoodsInfo shopGoodsInfo = new ShopGoodsInfo();
        shopGoodsInfo.setShopId(id);
        List<ShopGoodsInfo> shopGoodsInfos = shopGoodsInfoService.selectShopGoodsInfoList(shopGoodsInfo);
        for (int k = 0; k < shopGoodsInfos.size(); k++) {
            Long shopGoodsInfoId = shopGoodsInfos.get(k).getId();
            shopGoodsInfoService.deleteShopGoodsInfoById(shopGoodsInfoId);
        }
        return shopInfoMapper.deleteShopInfoById(id);
    }
}
