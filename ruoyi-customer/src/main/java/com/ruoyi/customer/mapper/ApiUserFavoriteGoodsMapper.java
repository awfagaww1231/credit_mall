package com.ruoyi.customer.mapper;

import java.util.List;

import com.ruoyi.customer.domain.ApiUserFavoriteGoods;

/**
 * C端用户收藏商品信息Mapper接口
 * 
 * @author ruoyi
 * @date 2022-11-10
 */
public interface ApiUserFavoriteGoodsMapper
{
    /**
     * 查询C端用户收藏商品信息
     * 
     * @param id C端用户收藏商品信息主键
     * @return C端用户收藏商品信息
     */
    public ApiUserFavoriteGoods selectUserFavoriteGoodsById(Long id);

    /**
     * 查询C端用户收藏商品信息
     *
     * @param userFavoriteGoods
     * @return C端用户收藏商品信息
     */
    public ApiUserFavoriteGoods selectUserFavoriteGoods(ApiUserFavoriteGoods userFavoriteGoods);

    /**
     * 查询C端用户收藏商品信息列表
     * 
     * @param userFavoriteGoods C端用户收藏商品信息
     * @return C端用户收藏商品信息集合
     */
    public List<ApiUserFavoriteGoods> selectUserFavoriteGoodsList(ApiUserFavoriteGoods userFavoriteGoods);

    /**
     * 新增C端用户收藏商品信息
     * 
     * @param userFavoriteGoods C端用户收藏商品信息
     * @return 结果
     */
    public int insertUserFavoriteGoods(ApiUserFavoriteGoods userFavoriteGoods);

    /**
     * 修改C端用户收藏商品信息
     * 
     * @param userFavoriteGoods C端用户收藏商品信息
     * @return 结果
     */
    public int updateUserFavoriteGoods(ApiUserFavoriteGoods userFavoriteGoods);

    /**
     * 删除C端用户收藏商品信息
     * 
     * @param id C端用户收藏商品信息主键
     * @return 结果
     */
    public int deleteUserFavoriteGoodsById(Long id);

    /**
     * 批量删除C端用户收藏商品信息
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteUserFavoriteGoodsByIds(Long[] ids);

    /**
     * 某商品的收藏人数
     */
    public int selectFavoriteQuantityByShopGoodsInfoId(Long shopGoodsInfoId);
}
