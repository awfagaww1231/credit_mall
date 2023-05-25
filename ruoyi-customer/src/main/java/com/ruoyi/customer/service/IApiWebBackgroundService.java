package com.ruoyi.customer.service;

import com.ruoyi.common.core.domain.AjaxResult;

public interface IApiWebBackgroundService {

    //新增提醒
    public void insertReminder(String type, Long userId ,String realName);

    //获取后台提醒
    AjaxResult getReminder();
}
