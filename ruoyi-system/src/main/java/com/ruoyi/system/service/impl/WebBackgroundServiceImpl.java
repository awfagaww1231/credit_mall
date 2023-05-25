package com.ruoyi.system.service.impl;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.redis.RedisCache;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.*;
import com.ruoyi.system.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class WebBackgroundServiceImpl implements IWebBackgroundService {


    @Autowired
    private RedisCache redisCache;

    @Resource
    //private CreditCardApplyRecordMapper creditCardApplyRecordMapper;
    private ICreditCardApplyRecordService creditCardApplyRecordService;

    @Resource
    //private CreditCardActivationRecordMapper creditCardActivationRecordMapper;
    private ICreditCardActivationRecordService creditCardActivationRecordService;

    @Resource
    //private UserInfoMapper userInfoMapper;
    private IUserInfoService userInfoService;

    @Resource
    //private VipActivationRecordMapper vipActivationRecordMapper;
    private IVipActivationRecordService vipActivationRecordService;
    @Resource
    //private UserLoanRecordMapper userLoanRecordMapper;
    private IUserLoanRecordService userLoanRecordService;


    //信用卡申请提醒
    private static String reminder_creditCardApply = "信用卡申请待审核";

    //信用卡激活提醒
    private String reminder_creditCardActivation = "信用卡激活待审核";

    //实名认证提醒
    private String reminder_realNameAuth = "实名认证待审核";

    //vip权限开通提醒
    private String reminder_vipActivation = "vip权限开通";

    //贷款申请审核提醒
    private String reminder_UserLoan= "贷款申请";

    @Override
    public void insertReminder(String type, Long userId ,String realName) {
        Map<String, Object> map = redisCache.getCacheMap(type);
        if (StringUtils.isNull(map)){
            map = new HashMap<>();
        }
        map.put(String.valueOf(userId),"真实姓名:" + realName);
        redisCache.setCacheMap(type,map);
    }

    @Override
    public AjaxResult getReminder() {
//        //信用卡片申请提醒
//        Map<String, Object> reminder_creditCardApply = redisCache.getCacheMap("reminder_creditCardApply");
//        //信用卡片激活提醒
//        Map<String, Object> reminder_creditCardActivation = redisCache.getCacheMap("reminder_creditCardActivation");
//        //实名认证提醒
//        Map<String, Object> reminder_realNameAuth = redisCache.getCacheMap("reminder_realNameAuth");
//        //vip权限开通提醒
//        Map<String, Object> reminder_vipActivation = redisCache.getCacheMap("reminder_vipActivation");

        //信用卡片申请提醒
        CreditCardApplyRecord creditCardApplyRecord = new CreditCardApplyRecord();
        creditCardApplyRecord.setStatus(0);
        List<CreditCardApplyRecord> reminder_creditCardApply = creditCardApplyRecordService.selectCreditCardApplyRecordList(creditCardApplyRecord);
        //信用卡片激活提醒
        CreditCardActivationRecord creditCardActivationRecord = new CreditCardActivationRecord();
        creditCardActivationRecord.setStatus(0);
        List<CreditCardActivationRecord> reminder_creditCardActivation = creditCardActivationRecordService.selectCreditCardActivationRecordList(creditCardActivationRecord);
        //实名认证提醒
        UserInfo userInfo = new UserInfo();
        userInfo.setRealNameAuthStatus(1);
        List<UserInfo> reminder_realNameAuth = userInfoService.selectUserInfoList(userInfo);
        //vip权限开通提醒
        VipActivationRecord vipActivationRecord = new VipActivationRecord();
        vipActivationRecord.setStatus(0);
        List<VipActivationRecord> reminder_vipActivation = vipActivationRecordService.selectVipActivationRecordList(vipActivationRecord);
        UserLoanRecord userLoanRecord = new UserLoanRecord();
        userLoanRecord.setStatus(0);
        List<UserLoanRecord>  reminder_UserLoan = userLoanRecordService.selectUserLoanRecordList(userLoanRecord);
        Map<String, Object> map = new HashMap<>();
        map.put("reminder_creditCardApply",reminder_creditCardApply);
        map.put("reminder_creditCardActivation",reminder_creditCardActivation);
        map.put("reminder_realNameAuth",reminder_realNameAuth);
        map.put("reminder_vipActivation",reminder_vipActivation);
        map.put("reminder_UserLoan",reminder_UserLoan);
        return AjaxResult.success(map);
    }

    @Override
    public void delReminder(String type, Long userId) {
        redisCache.deleteCacheMapValue(type,String.valueOf(userId));
    }
}
