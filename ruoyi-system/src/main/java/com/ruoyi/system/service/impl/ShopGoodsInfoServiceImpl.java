package com.ruoyi.system.service.impl;

import com.ruoyi.common.annotation.XXDataScope;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.BobiRatioConfig;
import com.ruoyi.system.domain.ShopGoodsInfo;
import com.ruoyi.system.mapper.BobiRatioConfigMapper;
import com.ruoyi.system.mapper.ShopGoodsInfoMapper;
import com.ruoyi.system.mapper.SysUserMapper;
import com.ruoyi.system.service.ICategoryService;
import com.ruoyi.system.service.IShopGoodsInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * 店铺的商品信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-11-01
 */
@Service
public class ShopGoodsInfoServiceImpl implements IShopGoodsInfoService 
{
    @Autowired
    private ShopGoodsInfoMapper shopGoodsInfoMapper;

    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private BobiRatioConfigMapper bobiRatioConfigMapper;
    /**
     * 查询店铺的商品信息
     * 
     * @param id 店铺的商品信息主键
     * @return 店铺的商品信息
     */
    @Override
    public ShopGoodsInfo selectShopGoodsInfoById(Long id)
    {
        return shopGoodsInfoMapper.selectShopGoodsInfoById(id);
    }

    @Override
    public ShopGoodsInfo selectShopGoodsInfoByShopIdAndGoodsId(Long shopId, Long goodsId) {
        return shopGoodsInfoMapper.selectShopGoodsInfoByShopIdAndGoodsId(shopId,goodsId);
    }

    /**
     * 查询店铺的商品信息列表
     * 
     * @param shopGoodsInfo 店铺的商品信息
     * @return 店铺的商品信息
     */
    @Override
    @XXDataScope(deptAlias = "d", userAlias = "u" ,sysUserIdAlias = "shop_owner_id")
    public List<ShopGoodsInfo> selectShopGoodsInfoList(ShopGoodsInfo shopGoodsInfo)
    {
        List<ShopGoodsInfo> shopGoodsInfos = shopGoodsInfoMapper.selectShopGoodsInfoList(shopGoodsInfo);
        for (int i = 0; i < shopGoodsInfos.size(); i++) {
            //类别
            String categoryName = categoryService.getCategoryName(shopGoodsInfos.get(i).getMinorClass());
            shopGoodsInfos.get(i).setCategoryName(categoryName);
            //获取进货价格
            //商家id
            Long sellerId = shopGoodsInfos.get(i).getSellerId();
            BigDecimal supplyPrice = this.getSupplyPrice(sellerId, shopGoodsInfos.get(i).getSinglePrice());
            shopGoodsInfos.get(i).setPlatformPrice(supplyPrice);
        }
        return shopGoodsInfos;
    }


    /**
     * 获取平台进货价
     *
     * @param sellerId 商家id
     * @param userSinglePrice C端用户购买的价格
     * @return 结果
     */
    //获取平台进货价(供应价)
    public BigDecimal getSupplyPrice(Long sellerId,BigDecimal userSinglePrice){
        //商家vip等级
        Integer sellerVipLevel = sysUserMapper.getVipLevelBySellerId(sellerId);
        if (StringUtils.isNull(sellerVipLevel)){
            sellerVipLevel = 0;
        }
        //波比比率配置信息
        BobiRatioConfig bobiRatioConfig = bobiRatioConfigMapper.selectBobiRatioConfigById(Long.valueOf(sellerVipLevel + 1));
        if (StringUtils.isNotNull(bobiRatioConfig)){
            //波比比率
            BigDecimal ratio = bobiRatioConfig.getRatio().divide(BigDecimal.valueOf(100));
            //到货天数
            Integer days = bobiRatioConfig.getDays();

            BigDecimal add = ratio.multiply(BigDecimal.valueOf(days)).add(BigDecimal.valueOf(1));
            if (userSinglePrice == null){
                return null;
            }
            BigDecimal platformPrice = userSinglePrice.divide(add,2,BigDecimal.ROUND_HALF_DOWN);
            return platformPrice;
        }else {
            return BigDecimal.ZERO;
        }
    }

    /**
     * 新增店铺的商品信息
     * 
     * @param shopGoodsInfo 店铺的商品信息
     * @return 结果
     */
    @Override
    public int insertShopGoodsInfo(ShopGoodsInfo shopGoodsInfo)
    {
        return shopGoodsInfoMapper.insertShopGoodsInfo(shopGoodsInfo);
    }

    /**
     * 修改店铺的商品信息
     * 
     * @param shopGoodsInfo 店铺的商品信息
     * @return 结果
     */
    @Override
    public int updateShopGoodsInfo(ShopGoodsInfo shopGoodsInfo)
    {
        return shopGoodsInfoMapper.updateShopGoodsInfo(shopGoodsInfo);
    }

    /**
     * 批量删除店铺的商品信息
     * 
     * @param ids 需要删除的店铺的商品信息主键
     * @return 结果
     */
    @Override
    public int deleteShopGoodsInfoByIds(Long[] ids)
    {
        return shopGoodsInfoMapper.deleteShopGoodsInfoByIds(ids);
    }

    /**
     * 删除店铺的商品信息信息
     * 
     * @param id 店铺的商品信息主键
     * @return 结果
     */
    @Override
    public int deleteShopGoodsInfoById(Long id)
    {
        return shopGoodsInfoMapper.deleteShopGoodsInfoById(id);
    }

    @Override
    public int toSetFeatured(List<Long> ids) {
        return shopGoodsInfoMapper.toSetFeatured(ids);
    }

    @Override
    public int toCancelFeatured(List<Long> ids) {
        return shopGoodsInfoMapper.toCancelFeatured(ids);
    }

    @Override
    public int toSetSpecial(List<Long> ids) {
        return shopGoodsInfoMapper.toSetSpecial(ids);
    }

    @Override
    public int toCancelSpecial(List<Long> ids) {
        return shopGoodsInfoMapper.toCancelSpecial(ids);
    }
}
