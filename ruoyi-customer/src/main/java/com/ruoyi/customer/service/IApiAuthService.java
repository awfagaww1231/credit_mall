package com.ruoyi.customer.service;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.model.LoginBody;
import com.ruoyi.customer.domain.ApiUserBank;
import com.ruoyi.customer.domain.ApiUserInfo;

public interface IApiAuthService {

    //用户登陆
    AjaxResult userLogin(LoginBody loginBody);

    //用户注册
    AjaxResult register(ApiUserInfo apiUserInfo);

    //根据id查找用户
    ApiUserInfo selectUserById(Long userId);

    /**
     * 修改C端用户信息
     *
     * @param apiUserInfo C端用户信息
     * @return 结果
     */
    public AjaxResult updateUserInfo(ApiUserInfo apiUserInfo);

    //更新密码
    AjaxResult changePassword(ApiUserInfo apiUserInfo);

    //更新支付密码
    AjaxResult changePayPassword(ApiUserInfo apiUserInfo);

    //实名认证
    public AjaxResult toAuth(ApiUserInfo apiUserInfo, ApiUserBank apiUserBank);

    //查看推广记录
    AjaxResult viewInvitedRecords(Long userId);

}
