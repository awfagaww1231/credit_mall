package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.ShopGoodsInfo;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

/**
 * 店铺的商品信息Mapper接口
 * 
 * @author ruoyi
 * @date 2022-11-01
 */
public interface ShopGoodsInfoMapper 
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
    public ShopGoodsInfo selectShopGoodsInfoByShopIdAndGoodsId(@Param("shopId") Long shopId,
                                                               @Param("goodsId") Long goodsId);

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
     * 删除店铺的商品信息
     * 
     * @param id 店铺的商品信息主键
     * @return 结果
     */
    public int deleteShopGoodsInfoById(Long id);

    /**
     * 批量删除店铺的商品信息
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteShopGoodsInfoByIds(Long[] ids);

    /**
     * 批量设置精选
     */
    int toSetFeatured(@Param("ids") List<Long> ids);

    /**
     * 批量取消精选
     */
    int toCancelFeatured(@Param("ids") List<Long> ids);

    /**
     * 批量设置精选
     */
    int toSetSpecial(@Param("ids") List<Long> ids);

    /**
     * 批量取消精选
     */
    int toCancelSpecial(@Param("ids") List<Long> ids);

    //根据goodsId修改价格
    int updatePriceByGoodsId(@Param("goodsId")Long goodsId,
                             @Param("price")BigDecimal price);

    //根据goodsId修改优惠价格
    int updateDiscountPriceByGoodsId(@Param("goodsId")Long goodsId,
                             @Param("discountPrice")BigDecimal discountPrice);

    ShopGoodsInfo selectShopGoodsInfoRandom();
}
