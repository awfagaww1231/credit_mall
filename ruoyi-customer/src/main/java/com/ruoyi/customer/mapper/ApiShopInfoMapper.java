package com.ruoyi.customer.mapper;

import com.ruoyi.customer.domain.ApiShopInfo;

public interface ApiShopInfoMapper {

    /**
     * 查询店铺信息
     *
     * @param id 店铺信息主键
     * @return 店铺信息
     */
    public ApiShopInfo selectShopInfoById(Long id);
}
