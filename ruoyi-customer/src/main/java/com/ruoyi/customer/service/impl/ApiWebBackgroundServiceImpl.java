package com.ruoyi.customer.service.impl;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.redis.RedisCache;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.customer.service.IApiWebBackgroundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ApiWebBackgroundServiceImpl implements IApiWebBackgroundService {


    @Autowired
    private RedisCache redisCache;


    //信用卡申请提醒
    private static String reminder_creditCardApply = "信用卡申请待审核";

    //信用卡激活提醒
    private String reminder_creditCardActivation = "信用卡激活待审核";

    //实名认证提醒
    private String reminder_realNameAuth = "实名认证待审核";

    @Override
    public void insertReminder(String type, Long userId ,String realName) {
        //卡片申请后台提醒
        Map<String, Object> map = redisCache.getCacheMap(type);
        if (StringUtils.isNull(map)){
            map = new HashMap<>();
        }
        map.put(String.valueOf(userId),"真实姓名:" + realName);
        redisCache.setCacheMap(type,map);
    }

    @Override
    public AjaxResult getReminder() {
        ////信用卡片申请提醒
        Map<String, Object> reminder_creditCardApply = redisCache.getCacheMap("reminder_creditCardApply");
        //信用卡片激活提醒
        Map<String, Object> reminder_creditCardActivation = redisCache.getCacheMap("reminder_creditCardActivation");
        //实名认证提醒
        Map<String, Object> reminder_realNameAuth = redisCache.getCacheMap("reminder_realNameAuth");

        Map<String, Object> map = new HashMap<>();
        map.put("reminder_creditCardApply",reminder_creditCardApply.values());
        map.put("reminder_creditCardActivation",reminder_creditCardActivation.values());
        map.put("reminder_realNameAuth",reminder_realNameAuth.values());
        return AjaxResult.success(map);
    }
}
