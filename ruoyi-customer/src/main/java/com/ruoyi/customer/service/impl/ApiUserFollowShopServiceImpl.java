package com.ruoyi.customer.service.impl;

import java.util.List;

import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.customer.domain.ApiUserFollowShop;
import com.ruoyi.customer.mapper.ApiUserFollowShopMapper;
import com.ruoyi.customer.service.IApiUserFollowShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 用户订阅店铺信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-11-28
 */
@Service
public class ApiUserFollowShopServiceImpl implements IApiUserFollowShopService
{
    @Autowired
    private ApiUserFollowShopMapper userFollowShopMapper;

    /**
     * 查询用户订阅店铺信息
     * 
     * @param id 用户订阅店铺信息主键
     * @return 用户订阅店铺信息
     */
    @Override
    public ApiUserFollowShop selectUserFollowShopById(Long id)
    {
        return userFollowShopMapper.selectUserFollowShopById(id);
    }

    /**
     * 查询用户订阅店铺信息列表
     * 
     * @param userFollowShop 用户订阅店铺信息
     * @return 用户订阅店铺信息
     */
    @Override
    public List<ApiUserFollowShop> selectUserFollowShopList(ApiUserFollowShop userFollowShop)
    {
        return userFollowShopMapper.selectUserFollowShopList(userFollowShop);
    }

    /**
     * 新增用户订阅店铺信息
     * 
     * @param userFollowShop 用户订阅店铺信息
     * @return 结果
     */
    @Override
    public int insertUserFollowShop(ApiUserFollowShop userFollowShop)
    {
        return userFollowShopMapper.insertUserFollowShop(userFollowShop);
    }

    /**
     * 修改用户订阅店铺信息
     * 
     * @param userFollowShop 用户订阅店铺信息
     * @return 结果
     */
    @Override
    public int updateUserFollowShop(ApiUserFollowShop userFollowShop)
    {
        return userFollowShopMapper.updateUserFollowShop(userFollowShop);
    }


    @Override
    public int toFollow(ApiUserFollowShop userFollowShop) {
        ApiUserFollowShop apiUserFollowShop = userFollowShopMapper.selectUserFollowShop(userFollowShop);
        if (StringUtils.isNull(apiUserFollowShop)){
            userFollowShop.setStatus(0);
            return userFollowShopMapper.insertUserFollowShop(userFollowShop);
        }else {
            if (apiUserFollowShop.getStatus() == 0){
                apiUserFollowShop.setStatus(1);
            }else {
                apiUserFollowShop.setStatus(0);
            }
            return userFollowShopMapper.updateUserFollowShop(apiUserFollowShop);
        }
    }

    @Override
    public ApiUserFollowShop selectUserFollowShop(ApiUserFollowShop userFollowShop) {
        return userFollowShopMapper.selectUserFollowShop(userFollowShop);
    }
}
