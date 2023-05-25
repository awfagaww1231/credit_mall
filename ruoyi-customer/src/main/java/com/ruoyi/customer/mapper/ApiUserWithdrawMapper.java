package com.ruoyi.customer.mapper;

import com.ruoyi.customer.domain.ApiUserWithdraw;

import java.util.List;

public interface ApiUserWithdrawMapper {

    //用户提现列表
    List<ApiUserWithdraw> userWithdrawList(ApiUserWithdraw apiUserWithdraw);

    //用户提现
    int addWithdrawOrder(ApiUserWithdraw apiUserWithdraw);
}
