package com.ruoyi.customer.mapper;

import java.util.List;

import com.ruoyi.customer.domain.ApiShoppingCart;
import org.apache.ibatis.annotations.Param;

/**
 * 购物车信息Mapper接口
 * 
 * @author ruoyi
 * @date 2022-11-04
 */
public interface ApiShoppingCartMapper
{
    /**
     * 查询购物车信息
     * 
     * @param id 购物车信息主键
     * @return 购物车信息
     */
    public ApiShoppingCart selectShoppingCartById(Long id);

    /**
     * 查询购物车信息
     *
     * @param shopGoodsInfoId 购物车信息主键
     * @param userId 购物车信息主键
     * @return 购物车信息
     */
    public ApiShoppingCart selectShoppingCart(@Param("shopGoodsInfoId") Long shopGoodsInfoId,
                                              @Param("userId")Long userId);

    /**
     * 查询购物车信息列表
     * 
     * @param apiShoppingCart 购物车信息
     * @return 购物车信息集合
     */
    public List<ApiShoppingCart> selectShoppingCartList(ApiShoppingCart apiShoppingCart);

    /**
     * 新增购物车信息
     * 
     * @param apiShoppingCart 购物车信息
     * @return 结果
     */
    public int insertShoppingCart(ApiShoppingCart apiShoppingCart);

    /**
     * 修改购物车信息
     * 
     * @param apiShoppingCart 购物车信息
     * @return 结果
     */
    public int updateShoppingCart(ApiShoppingCart apiShoppingCart);

    /**
     * 删除购物车信息
     * 
     * @param id 购物车信息主键
     * @return 结果
     */
    public int deleteShoppingCartById(Long id);

    /**
     * 批量删除购物车信息
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteShoppingCartByIds(Long[] ids);
}
