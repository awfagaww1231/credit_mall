package com.ruoyi.customer.service.impl;

import java.util.List;

import com.ruoyi.customer.domain.ApiUserFavoriteGoods;
import com.ruoyi.customer.mapper.ApiUserFavoriteGoodsMapper;
import com.ruoyi.customer.service.IApiUserFavoriteGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * C端用户收藏商品信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-11-10
 */
@Service
public class ApiUserFavoriteGoodsServiceImpl implements IApiUserFavoriteGoodsService
{
    @Autowired
    private ApiUserFavoriteGoodsMapper apiUserFavoriteGoodsMapper;

    /**
     * 查询C端用户收藏商品信息
     * 
     * @param id C端用户收藏商品信息主键
     * @return C端用户收藏商品信息
     */
    @Override
    public ApiUserFavoriteGoods selectUserFavoriteGoodsById(Long id)
    {
        return apiUserFavoriteGoodsMapper.selectUserFavoriteGoodsById(id);
    }

    @Override
    public ApiUserFavoriteGoods selectUserFavoriteGoods(ApiUserFavoriteGoods userFavoriteGoods) {
        return apiUserFavoriteGoodsMapper.selectUserFavoriteGoods(userFavoriteGoods);
    }

    /**
     * 查询C端用户收藏商品信息列表
     * 
     * @param userFavoriteGoods C端用户收藏商品信息
     * @return C端用户收藏商品信息
     */
    @Override
    public List<ApiUserFavoriteGoods> selectUserFavoriteGoodsList(ApiUserFavoriteGoods userFavoriteGoods)
    {
        List<ApiUserFavoriteGoods> apiUserFavoriteGoods = apiUserFavoriteGoodsMapper.selectUserFavoriteGoodsList(userFavoriteGoods);
        for (int i = 0; i < apiUserFavoriteGoods.size(); i++) {
            Long shopGoodsInfoId = apiUserFavoriteGoods.get(i).getShopGoodsInfoId();
            int favoriteQuantity = apiUserFavoriteGoodsMapper.selectFavoriteQuantityByShopGoodsInfoId(shopGoodsInfoId);
            apiUserFavoriteGoods.get(i).setFavoriteQuantity(favoriteQuantity);
        }
        return apiUserFavoriteGoods;
    }

    /**
     * 新增C端用户收藏商品信息
     * 
     * @param userFavoriteGoods C端用户收藏商品信息
     * @return 结果
     */
    @Override
    public int insertUserFavoriteGoods(ApiUserFavoriteGoods userFavoriteGoods)
    {
        return apiUserFavoriteGoodsMapper.insertUserFavoriteGoods(userFavoriteGoods);
    }

    /**
     * 修改C端用户收藏商品信息
     * 
     * @param userFavoriteGoods C端用户收藏商品信息
     * @return 结果
     */
    @Override
    public int updateUserFavoriteGoods(ApiUserFavoriteGoods userFavoriteGoods)
    {
        return apiUserFavoriteGoodsMapper.updateUserFavoriteGoods(userFavoriteGoods);
    }

    /**
     * 批量删除C端用户收藏商品信息
     * 
     * @param ids 需要删除的C端用户收藏商品信息主键
     * @return 结果
     */
    @Override
    public int deleteUserFavoriteGoodsByIds(Long[] ids)
    {
        return apiUserFavoriteGoodsMapper.deleteUserFavoriteGoodsByIds(ids);
    }

    /**
     * 删除C端用户收藏商品信息信息
     * 
     * @param id C端用户收藏商品信息主键
     * @return 结果
     */
    @Override
    public int deleteUserFavoriteGoodsById(Long id)
    {
        return apiUserFavoriteGoodsMapper.deleteUserFavoriteGoodsById(id);
    }
}
