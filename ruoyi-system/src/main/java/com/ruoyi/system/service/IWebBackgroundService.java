package com.ruoyi.system.service;

import com.ruoyi.common.core.domain.AjaxResult;

public interface IWebBackgroundService {

    //新增提醒
    public void insertReminder(String type, Long userId ,String realName);

    //获取后台提醒
    AjaxResult getReminder();

    //审核后删除提醒
    public void delReminder(String type, Long userId);
}
