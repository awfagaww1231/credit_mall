package com.ruoyi.customer.service.impl;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.customer.domain.ApiShoppingCart;
import com.ruoyi.customer.mapper.ApiShoppingCartMapper;
import com.ruoyi.customer.service.IApiShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 购物车信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-11-04
 */
@Service
public class ApiShoppingCartServiceImpl implements IApiShoppingCartService
{
    @Autowired
    private ApiShoppingCartMapper apiShoppingCartMapper;

    /**
     * 查询购物车信息
     * 
     * @param id 购物车信息主键
     * @return 购物车信息
     */
    @Override
    public ApiShoppingCart selectShoppingCartById(Long id)
    {
        return apiShoppingCartMapper.selectShoppingCartById(id);
    }

    @Override
    public ApiShoppingCart selectShoppingCart(Long shopGoodsInfoId, Long userId) {
        return apiShoppingCartMapper.selectShoppingCart(shopGoodsInfoId,userId);
    }

    /**
     * 查询购物车信息列表
     * 
     * @param apiShoppingCart 购物车信息
     * @return 购物车信息
     */
    @Override
    public List<ApiShoppingCart> selectShoppingCartList(ApiShoppingCart apiShoppingCart)
    {
        return apiShoppingCartMapper.selectShoppingCartList(apiShoppingCart);
    }

    /**
     * 新增购物车信息
     * 
     * @param apiShoppingCart 购物车信息
     * @return 结果
     */
    @Override
    public int insertShoppingCart(ApiShoppingCart apiShoppingCart)
    {
        return apiShoppingCartMapper.insertShoppingCart(apiShoppingCart);
    }

    /**
     * 修改购物车信息
     * 
     * @param apiShoppingCart 购物车信息
     * @return 结果
     */
    @Override
    public int updateShoppingCart(ApiShoppingCart apiShoppingCart)
    {
        return apiShoppingCartMapper.updateShoppingCart(apiShoppingCart);
    }

    /**
     * 批量删除购物车信息
     * 
     * @param ids 需要删除的购物车信息主键
     * @return 结果
     */
    @Override
    public int deleteShoppingCartByIds(Long[] ids)
    {
        return apiShoppingCartMapper.deleteShoppingCartByIds(ids);
    }

    /**
     * 新增购物车信息
     */
    @Override
    public AjaxResult toAddShoppingCart(ApiShoppingCart apiShoppingCart,Long userId) {
        //查询购物车中是否已经有此商品
        ApiShoppingCart apiShoppingCartVo = apiShoppingCartMapper.selectShoppingCart(apiShoppingCart.getShopGoodsInfoId(), userId);
        //如果购物车中已经有此商品，则加上数量
        if (StringUtils.isNotNull(apiShoppingCartVo)){
            apiShoppingCartVo.setCartQuantity(apiShoppingCartVo.getCartQuantity() + apiShoppingCart.getCartQuantity());
            int updateShoppingCart = apiShoppingCartMapper.updateShoppingCart(apiShoppingCartVo);
            if (updateShoppingCart <= 0){
                return AjaxResult.error();
            }
        }else {
            apiShoppingCart.setUserId(userId);
            int insertShoppingCart = apiShoppingCartMapper.insertShoppingCart(apiShoppingCart);
            if (insertShoppingCart <= 0){
                return AjaxResult.error();
            }
        }
        return AjaxResult.success();
    }
}
