package com.ruoyi.customer.service;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.customer.domain.ApiUserWithdraw;

import java.util.List;

public interface IApiUserWithdrawService {

    //用户提现列表
    List<ApiUserWithdraw> userWithdrawList(ApiUserWithdraw apiUserWithdraw);

    //用户提现
    AjaxResult addWithdrawOrder(ApiUserWithdraw apiUserWithdraw) throws Exception;
}
