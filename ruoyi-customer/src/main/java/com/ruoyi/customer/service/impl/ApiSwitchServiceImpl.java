package com.ruoyi.customer.service.impl;

import com.ruoyi.customer.mapper.ApiSwitchMapper;
import com.ruoyi.customer.service.IApiSwitchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApiSwitchServiceImpl implements IApiSwitchService {

    @Autowired
    private ApiSwitchMapper apiSwitchMapper;

}
