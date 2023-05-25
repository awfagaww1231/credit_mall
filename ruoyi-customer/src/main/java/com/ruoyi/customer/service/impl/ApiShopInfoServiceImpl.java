package com.ruoyi.customer.service.impl;

import com.ruoyi.customer.domain.ApiShopInfo;
import com.ruoyi.customer.mapper.ApiShopInfoMapper;
import com.ruoyi.customer.service.IApiShopInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApiShopInfoServiceImpl implements IApiShopInfoService {

    @Autowired
    private ApiShopInfoMapper apiShopInfoMapper;


    @Override
    public ApiShopInfo selectShopInfoById(Long id) {
        return apiShopInfoMapper.selectShopInfoById(id);
    }
}
