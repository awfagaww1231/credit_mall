package com.ruoyi.system.service;

import com.ruoyi.system.domain.ShopGoodsInfo;

import java.math.BigDecimal;
import java.util.List;

/**
 * 店铺的商品信息Service接口
 * 
 * @author ruoyi
 * @date 2022-11-01
 */
public interface IShopGoodsInfoService 
{
    /**
     * 查询店铺的商品信息
     * 
     * @param id 店铺的商品信息主键
     * @return 店铺的商品信息
     */
    public ShopGoodsInfo selectShopGoodsInfoById(Long id);

    /**
     * 查询店铺的商品信息
     *
     * @param shopId 店铺id
     * @param goodsId 商品信息id
     * @return 店铺的商品信息
     */
    public ShopGoodsInfo selectShopGoodsInfoByShopIdAndGoodsId(Long shopId,Long goodsId);

    /**
     * 查询店铺的商品信息列表
     * 
     * @param shopGoodsInfo 店铺的商品信息
     * @return 店铺的商品信息集合
     */
    public List<ShopGoodsInfo> selectShopGoodsInfoList(ShopGoodsInfo shopGoodsInfo);

    /**
     * 新增店铺的商品信息
     * 
     * @param shopGoodsInfo 店铺的商品信息
     * @return 结果
     */
    public int insertShopGoodsInfo(ShopGoodsInfo shopGoodsInfo);

    /**
     * 修改店铺的商品信息
     * 
     * @param shopGoodsInfo 店铺的商品信息
     * @return 结果
     */
    public int updateShopGoodsInfo(ShopGoodsInfo shopGoodsInfo);

    /**
     * 批量删除店铺的商品信息
     * 
     * @param ids 需要删除的店铺的商品信息主键集合
     * @return 结果
     */
    public int deleteShopGoodsInfoByIds(Long[] ids);

    /**
     * 删除店铺的商品信息信息
     * 
     * @param id 店铺的商品信息主键
     * @return 结果
     */
    public int deleteShopGoodsInfoById(Long id);

    /**
     * 批量设置精选
     */
    int toSetFeatured(List<Long> ids);

    /**
     * 批量取消精选
     */
    int toCancelFeatured(List<Long> ids);

    /**
     * 批量设置商品特价
     */
    int toSetSpecial(List<Long> ids);

    /**
     * 批量取消商品特价
     */
    int toCancelSpecial(List<Long> ids);

    /**
     * 获取平台进货价
     *
     * @param sellerId 商家id
     * @param userSinglePrice C端用户购买的价格
     * @return 结果
     */
    public BigDecimal getSupplyPrice(Long sellerId, BigDecimal userSinglePrice);
}
