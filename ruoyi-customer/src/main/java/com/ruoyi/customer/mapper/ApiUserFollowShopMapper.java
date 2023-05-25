package com.ruoyi.customer.mapper;


import com.ruoyi.customer.domain.ApiUserFollowShop;

import java.util.List;

/**
 * 用户订阅店铺信息Mapper接口
 * 
 * @author ruoyi
 * @date 2022-11-28
 */
public interface ApiUserFollowShopMapper
{
    /**
     * 查询用户订阅店铺信息
     * 
     * @param id 用户订阅店铺信息主键
     * @return 用户订阅店铺信息
     */
    public ApiUserFollowShop selectUserFollowShopById(Long id);

    /**
     * 查询用户订阅店铺信息
     *
     * @param userFollowShop 用户订阅店铺信息
     * @return 用户订阅店铺信息
     */
    public ApiUserFollowShop selectUserFollowShop (ApiUserFollowShop userFollowShop);

    /**
     * 查询用户订阅店铺信息列表
     * 
     * @param userFollowShop 用户订阅店铺信息
     * @return 用户订阅店铺信息集合
     */
    public List<ApiUserFollowShop> selectUserFollowShopList(ApiUserFollowShop userFollowShop);

    /**
     * 新增用户订阅店铺信息
     * 
     * @param userFollowShop 用户订阅店铺信息
     * @return 结果
     */
    public int insertUserFollowShop(ApiUserFollowShop userFollowShop);

    /**
     * 修改用户订阅店铺信息
     * 
     * @param userFollowShop 用户订阅店铺信息
     * @return 结果
     */
    public int updateUserFollowShop(ApiUserFollowShop userFollowShop);

}
