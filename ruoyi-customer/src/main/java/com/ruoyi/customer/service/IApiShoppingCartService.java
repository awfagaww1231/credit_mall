package com.ruoyi.customer.service;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.customer.domain.ApiShoppingCart;

import java.util.List;

/**
 * 购物车信息Service接口
 * 
 * @author ruoyi
 * @date 2022-11-04
 */
public interface IApiShoppingCartService
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
    public ApiShoppingCart selectShoppingCart(Long shopGoodsInfoId,Long userId);

    /**
     * 查询购物车信息列表
     * 
     * @param shoppingCart 购物车信息
     * @return 购物车信息集合
     */
    public List<ApiShoppingCart> selectShoppingCartList(ApiShoppingCart shoppingCart);

    /**
     * 新增购物车信息
     * 
     * @param shoppingCart 购物车信息
     * @return 结果
     */
    public int insertShoppingCart(ApiShoppingCart shoppingCart);

    /**
     * 修改购物车信息
     * 
     * @param shoppingCart 购物车信息
     * @return 结果
     */
    public int updateShoppingCart(ApiShoppingCart shoppingCart);

    /**
     * 批量删除购物车信息
     * 
     * @param ids 需要删除的购物车信息主键集合
     * @return 结果
     */
    public int deleteShoppingCartByIds(Long[] ids);

    /**
     * 新增购物车信息
     */
    AjaxResult toAddShoppingCart(ApiShoppingCart apiShoppingCart,Long userId);
}
