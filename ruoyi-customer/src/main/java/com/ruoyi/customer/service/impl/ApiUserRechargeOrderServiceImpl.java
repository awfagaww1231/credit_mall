package com.ruoyi.customer.service.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.customer.domain.ApiUserRechargeOrder;
import com.ruoyi.customer.mapper.ApiUserRechargeOrderMapper;
import com.ruoyi.customer.service.IApiUserRechargeOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApiUserRechargeOrderServiceImpl implements IApiUserRechargeOrderService {

    @Autowired
    private ApiUserRechargeOrderMapper apiUserRechargeOrderMapper;

    @Override
    public ApiUserRechargeOrder selectUserRechargeOrderById(Long id) {
        return apiUserRechargeOrderMapper.selectUserRechargeOrderById(id);
    }

    @Override
    public List<ApiUserRechargeOrder> selectUserRechargeOrderList(ApiUserRechargeOrder apiUserRechargeOrder) {
        return apiUserRechargeOrderMapper.selectUserRechargeOrderList(apiUserRechargeOrder);
    }

    @Override
    public int insertUserRechargeOrder(ApiUserRechargeOrder apiUserRechargeOrder) {
        apiUserRechargeOrder.setCreateTime(DateUtils.getNowDate());
        apiUserRechargeOrder.setRechargeMethod(0);
        apiUserRechargeOrder.setOrderStatus(0);
        return apiUserRechargeOrderMapper.insertUserRechargeOrder(apiUserRechargeOrder);
    }

    @Override
    public int updateUserRechargeOrder(ApiUserRechargeOrder apiUserRechargeOrder) {
        return apiUserRechargeOrderMapper.updateUserRechargeOrder(apiUserRechargeOrder);
    }

    @Override
    public int deleteUserRechargeOrderById(Long id) {
        return apiUserRechargeOrderMapper.deleteUserRechargeOrderById(id);
    }
}
