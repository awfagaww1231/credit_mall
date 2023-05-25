package com.ruoyi.system.service.impl;

import com.ruoyi.common.annotation.DataScope;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.system.domain.CreditActivationPaymentChannel;
import com.ruoyi.system.domain.CreditCardActivationRecord;
import com.ruoyi.system.domain.UserInfo;
import com.ruoyi.system.domain.VipActivationRecord;
import com.ruoyi.system.mapper.CreditActivationPaymentChannelMapper;
import com.ruoyi.system.mapper.CreditCardActivationRecordMapper;
import com.ruoyi.system.mapper.UserInfoMapper;
import com.ruoyi.system.mapper.VipActivationRecordMapper;
import com.ruoyi.system.service.ICreditCardActivationRecordService;
import com.ruoyi.system.service.ICreditCardApplyRecordService;
import com.ruoyi.system.service.IWebBackgroundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 用户额度激活记录Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-03-20
 */
@Service
public class CreditCardActivationRecordServiceImpl implements ICreditCardActivationRecordService 
{
    @Resource
    private CreditCardActivationRecordMapper creditCardActivationRecordMapper;

    @Resource
    private UserInfoMapper userInfoMapper;

    @Resource
    private CreditActivationPaymentChannelMapper creditActivationPaymentChannelMapper;

    @Autowired
    private ICreditCardApplyRecordService creditCardApplyRecordService;

    @Autowired
    private IWebBackgroundService webBackgroundService;

    @Resource
    private VipActivationRecordMapper vipActivationRecordMapper;

    /**
     * 查询用户额度激活记录
     * 
     * @param id 用户额度激活记录主键
     * @return 用户额度激活记录
     */
    @Override
    public CreditCardActivationRecord selectCreditCardActivationRecordById(Long id)
    {
        return creditCardActivationRecordMapper.selectCreditCardActivationRecordById(id);
    }

    /**
     * 查询用户额度激活记录列表
     * 
     * @param creditCardActivationRecord 用户额度激活记录
     * @return 用户额度激活记录
     */
    @Override
    @DataScope(userAlias = "su")
    public List<CreditCardActivationRecord> selectCreditCardActivationRecordList(CreditCardActivationRecord creditCardActivationRecord)
    {
        return creditCardActivationRecordMapper.selectCreditCardActivationRecordList(creditCardActivationRecord);
    }

    /**
     * 新增用户额度激活记录
     * 
     * @param creditCardActivationRecord 用户额度激活记录
     * @return 结果
     */
    @Override
    public int insertCreditCardActivationRecord(CreditCardActivationRecord creditCardActivationRecord)
    {
        creditCardActivationRecord.setCreateTime(new Date());
        return creditCardActivationRecordMapper.insertCreditCardActivationRecord(creditCardActivationRecord);
    }

    /**
     * 修改用户额度激活记录
     * 
     * @param creditCardActivationRecord 用户额度激活记录
     * @return 结果
     */
    @Override
    public int updateCreditCardActivationRecord(CreditCardActivationRecord creditCardActivationRecord)
    {
        creditCardActivationRecord.setUpdateTime(new Date());
        return creditCardActivationRecordMapper.updateCreditCardActivationRecord(creditCardActivationRecord);
    }

    /**
     * 批量删除用户额度激活记录
     * 
     * @param ids 需要删除的用户额度激活记录主键
     * @return 结果
     */
    @Override
    public AjaxResult deleteCreditCardActivationRecordByIds(Long[] ids)
    {
        for (int i = 0; i < ids.length; i++) {
            CreditCardActivationRecord creditCardActivationRecord = creditCardActivationRecordMapper.selectCreditCardActivationRecordById(ids[i]);
            if (creditCardActivationRecord.getStatus() == 0){
                return AjaxResult.error("有待审核的订单，操作失败");
            }
        }
        int count = creditCardActivationRecordMapper.deleteCreditCardActivationRecordByIds(ids);
        if (count <= 0){
            return AjaxResult.error();
        }
        return AjaxResult.success();
    }

    /**
     * 删除用户额度激活记录信息
     * 
     * @param id 用户额度激活记录主键
     * @return 结果
     */
    @Override
    public int deleteCreditCardActivationRecordById(Long id)
    {
        return creditCardActivationRecordMapper.deleteCreditCardActivationRecordById(id);
    }

    @Override
    @Transactional
    public AjaxResult toActivationCreditCard(Long userId,Long platformPaymentChannelId,String payImg) {
        UserInfo userInfo = userInfoMapper.selectUserInfoById(userId);
        if (userInfo == null){
            return AjaxResult.error("获取用户信息异常","hint_10");
        }
        //信用卡状态：0：未申请 1:卡片申请审核中 2：卡片申请成功，待激活 3:卡片申请失败 4:激活审核中 5：已激活 6：激活失败
        Integer creditCardStatus = userInfo.getCreditCardStatus();
        if (creditCardStatus != 2 & creditCardStatus != 6){
            if (creditCardStatus == 0 | creditCardStatus == 1 | creditCardStatus == 3){
                return AjaxResult.error("还未申请到卡片，请先去申请卡片","hint_110");
            }else if (creditCardStatus == 4){
                return AjaxResult.error("卡片激活中，请耐心等待","hint_111");
            }else {
                return AjaxResult.error();
            }
        }

        CreditActivationPaymentChannel creditActivationPaymentChannel = creditActivationPaymentChannelMapper.selectCreditActivationPaymentChannelById(platformPaymentChannelId);
        if (creditActivationPaymentChannel == null){
            return AjaxResult.error("获取激活通道信息异常","hint_112");
        }

        //所需支付金额
        BigDecimal creditActivationPayAmount = creditCardApplyRecordService.getCreditActivationPayAmount(userId);
        if (creditActivationPayAmount.compareTo(BigDecimal.ZERO) == 0){
            return AjaxResult.error("获取所需支付金额出错，请稍后重新尝试","hint_113");
        }
        //新增激活记录
        CreditCardActivationRecord creditCardActivationRecord = new CreditCardActivationRecord();
        creditCardActivationRecord.setUserId(userId);
        creditCardActivationRecord.setRealName(userInfo.getRealName());
        creditCardActivationRecord.setCreditCardNumber(userInfo.getCreditCardNumber());
        creditCardActivationRecord.setCreditCardTotalAmount(userInfo.getCreditCardTotalAmount());
        creditCardActivationRecord.setPayType(creditActivationPaymentChannel.getPayType());
        creditCardActivationRecord.setChannelName(creditActivationPaymentChannel.getChannelName());
        creditCardActivationRecord.setChannelAccount(creditActivationPaymentChannel.getChannelAccount());
        creditCardActivationRecord.setPayeeName(creditActivationPaymentChannel.getPayeeName());
        creditCardActivationRecord.setAccountOpenBank(creditActivationPaymentChannel.getAccountOpenBank());
        creditCardActivationRecord.setPayAmount(creditActivationPayAmount);
        creditCardActivationRecord.setPayImg(payImg);
        creditCardActivationRecord.setCreateTime(new Date());
        int insertCreditCardActivationRecord = creditCardActivationRecordMapper.insertCreditCardActivationRecord(creditCardActivationRecord);
        if (insertCreditCardActivationRecord <= 0){
            throw new RuntimeException();
        }

        //变更用户信用卡的激活状态
        UserInfo userInfoVo = new UserInfo();
        userInfoVo.setId(userInfo.getId());
        userInfoVo.setCreditCardStatus(4);
        int updateUserInfo = userInfoMapper.updateUserInfo(userInfoVo);
        if (updateUserInfo <= 0){
            throw new RuntimeException();
        }

//        //信用激活后台提醒
//        webBackgroundService.insertReminder("reminder_creditCardActivation",userId,userInfo.getRealName());
        return AjaxResult.success();
    }

    //去开通vip权限
    @Override
    @Transactional
    public AjaxResult toActivationVip(Long userId,Long platformPaymentChannelId,String payImg) {
        UserInfo userInfo = userInfoMapper.selectUserInfoById(userId);
        if (userInfo == null){
            return AjaxResult.error("获取用户信息异常","hint_10");
        }
        if (userInfo.getCreditCardStatus() != 5){
            return AjaxResult.error("卡片还未激活","hint_114");
        }
        //信用卡片是否可用状态
        Integer creditCardEnableStatus = userInfo.getCreditCardEnableStatus();
        if (creditCardEnableStatus != 3){
            if (creditCardEnableStatus == 0){
                return AjaxResult.error("卡片还未激活","hint_114");
            }else if (creditCardEnableStatus == 1 || creditCardEnableStatus == 2){
                return AjaxResult.error("已经开通过vip","hint_115");
            }
            return AjaxResult.error();
        }

        VipActivationRecord vipActivationRecordVo = new VipActivationRecord();
        vipActivationRecordVo.setUserId(userId);
        vipActivationRecordVo.setStatus(0);
        List<VipActivationRecord> vipActivationRecords = vipActivationRecordMapper.selectVipActivationRecordList(vipActivationRecordVo);
        if (vipActivationRecords.size() > 0){
            return AjaxResult.error("vip权限开通申请中，请耐心等待","hint_116");
        }
        CreditActivationPaymentChannel creditActivationPaymentChannel = creditActivationPaymentChannelMapper.selectCreditActivationPaymentChannelById(platformPaymentChannelId);
        if (creditActivationPaymentChannel == null){
            return AjaxResult.error("获取激活通道信息异常","hint_112");
        }

//        //所需支付金额
//        BigDecimal creditActivationPayAmount = creditCardApplyRecordService.getCreditActivationPayAmount(userId);
//        if (creditActivationPayAmount.compareTo(BigDecimal.ZERO) == 0){
//            return AjaxResult.error("获取所需支付金额出错，请稍后重新尝试","hint_113");
//        }
        //用户的信用额度
        BigDecimal creditCardTotalAmount = userInfo.getCreditCardTotalAmount();
        //所需支付金额
        BigDecimal creditActivationPayAmount = creditCardTotalAmount.multiply(new BigDecimal(0.05)).setScale(2,4);
        //新增vip权限开通记录
        VipActivationRecord vipActivationRecord = new VipActivationRecord();
        vipActivationRecord.setUserId(userId);
        vipActivationRecord.setRealName(userInfo.getRealName());
        vipActivationRecord.setCreditCardNumber(userInfo.getCreditCardNumber());
        vipActivationRecord.setCreditCardTotalAmount(userInfo.getCreditCardTotalAmount());
        vipActivationRecord.setPayType(creditActivationPaymentChannel.getPayType());
        vipActivationRecord.setChannelName(creditActivationPaymentChannel.getChannelName());
        vipActivationRecord.setChannelAccount(creditActivationPaymentChannel.getChannelAccount());
        vipActivationRecord.setPayeeName(creditActivationPaymentChannel.getPayeeName());
        vipActivationRecord.setAccountOpenBank(creditActivationPaymentChannel.getAccountOpenBank());
        vipActivationRecord.setPayAmount(creditActivationPayAmount);
        vipActivationRecord.setPayImg(payImg);
        vipActivationRecord.setCreateTime(new Date());
        int insertVipActivationRecord = vipActivationRecordMapper.insertVipActivationRecord(vipActivationRecord);
        if (insertVipActivationRecord <= 0){
            throw new RuntimeException();
        }

        UserInfo userInfoVo = new UserInfo();
        userInfoVo.setId(userInfo.getId());
        userInfoVo.setCreditCardEnableStatus(4);//已激活，vip权限申请中，额度不可用
        int updateUserInfo = userInfoMapper.updateUserInfo(userInfoVo);
        if (updateUserInfo <= 0){
            throw new RuntimeException();
        }
//        //vip权限后台提醒
//        webBackgroundService.insertReminder("reminder_vipActivation",userId,userInfo.getRealName());
        return AjaxResult.success();
    }

    @Override
    @Transactional
    public AjaxResult agree(Long id) {
        //激活信息
        CreditCardActivationRecord creditCardActivationRecord = creditCardActivationRecordMapper.selectCreditCardActivationRecordById(id);
        if (creditCardActivationRecord.getStatus() != 0){
            return AjaxResult.error("此用户申请激活的信用额度已经审核过");
        }

        //用户id
        Long userId = creditCardActivationRecord.getUserId();
        UserInfo userInfo = userInfoMapper.selectUserInfoById(userId);
        if (userInfo.getCreditCardStatus() != 4){
            return AjaxResult.error("此用户的信用额度激活状态异常");
        }

        //变更激活记录的状态为已激活
        creditCardActivationRecord.setStatus(1);
        creditCardActivationRecord.setUpdateTime(new Date());
        int updateCreditCardActivationRecord = creditCardActivationRecordMapper.updateCreditCardActivationRecord(creditCardActivationRecord);
        if (updateCreditCardActivationRecord <= 0){
            throw new RuntimeException();
        }

        UserInfo userInfoVo = new UserInfo();
        userInfoVo.setId(userInfo.getId());
        userInfoVo.setCreditCardStatus(5);
        //激活卡牌自动升级为vip1
        userInfoVo.setVipLevel(1);
        //信用卡片状态变为已激活，但不可用,还需开通vip权限
        userInfoVo.setCreditCardEnableStatus(3);
        //叠加信用总额度
        userInfoVo.setCreditCardTotalAmount(userInfo.getCreditCardTotalAmount().add(creditCardActivationRecord.getPayAmount()));
        int updateUserInfo = userInfoMapper.updateUserInfo(userInfoVo);
        if (updateUserInfo <= 0){
            throw new RuntimeException();
        }
//        //清除该用户卡片激活的后台提醒
//        webBackgroundService.delReminder("reminder_creditCardActivation",userId);
        return AjaxResult.success();
    }

    @Override
    @Transactional
    public AjaxResult reject(Long id,String remark) {
        //激活信息
        CreditCardActivationRecord creditCardActivationRecord = creditCardActivationRecordMapper.selectCreditCardActivationRecordById(id);
        if (creditCardActivationRecord.getStatus() != 0){
            return AjaxResult.error("此用户申请激活的信用额度已经审核过");
        }

        //用户id
        Long userId = creditCardActivationRecord.getUserId();
        UserInfo userInfo = userInfoMapper.selectUserInfoById(userId);
        if (userInfo.getCreditCardStatus() != 4){
            return AjaxResult.error("此用户的信用额度激活状态异常");
        }

        creditCardActivationRecord.setRemark(remark);
        //变更激活记录的状态为激活失败
        creditCardActivationRecord.setStatus(2);
        creditCardActivationRecord.setUpdateTime(new Date());
        int updateCreditCardActivationRecord = creditCardActivationRecordMapper.updateCreditCardActivationRecord(creditCardActivationRecord);
        if (updateCreditCardActivationRecord <= 0){
            throw new RuntimeException();
        }

        UserInfo userInfoVo = new UserInfo();
        userInfoVo.setId(userInfo.getId());
        userInfoVo.setCreditCardStatus(6);
        int updateUserInfo = userInfoMapper.updateUserInfo(userInfoVo);
        if (updateUserInfo <= 0){
            throw new RuntimeException();
        }
//        //清除该用户卡片激活的后台提醒
//        webBackgroundService.delReminder("reminder_creditCardActivation",userId);
        return AjaxResult.success();
    }

    @Override
    public AjaxResult getActivationCreditCardRejectMsg(Long userId) {
        return AjaxResult.success(creditCardActivationRecordMapper.getActivationCreditCardRejectMsg(userId));
    }
}
