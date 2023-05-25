package com.ruoyi.system.service.impl;

import com.ruoyi.common.annotation.DataScope;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.*;
import com.ruoyi.system.mapper.CreditCardApplyRecordMapper;
import com.ruoyi.system.mapper.LoanApplyInfoMapper;
import com.ruoyi.system.mapper.UserBankMapper;
import com.ruoyi.system.mapper.UserInfoMapper;
import com.ruoyi.system.service.ICreditCardApplyRecordService;
import com.ruoyi.system.service.IOtherValueService;
import com.ruoyi.system.service.IWebBackgroundService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.List;

/**
 * 用户信用卡申请记录Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-03-20
 */
@Service
public class CreditCardApplyRecordServiceImpl implements ICreditCardApplyRecordService 
{
    @Autowired
    private CreditCardApplyRecordMapper creditCardApplyRecordMapper;

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private IOtherValueService otherValueService;

    @Resource
    private LoanApplyInfoMapper loanApplyInfoMapper;

    @Resource
    private UserBankMapper userBankMapper;

    @Autowired
    private IWebBackgroundService webBackgroundService;


    /**
     * 查询用户信用卡申请记录
     * 
     * @param id 用户信用卡申请记录主键
     * @return 用户信用卡申请记录
     */
    @Override
    public CreditCardApplyRecord selectCreditCardApplyRecordById(Long id)
    {
        return creditCardApplyRecordMapper.selectCreditCardApplyRecordById(id);
    }

    /**
     * 查询用户信用卡申请记录列表
     *
     * @param creditCardApplyRecord 用户信用卡申请记录
     * @return 用户信用卡申请记录
     */
    @Override
    @DataScope(userAlias = "su")
    public List<CreditCardApplyRecord> selectCreditCardApplyRecordList(CreditCardApplyRecord creditCardApplyRecord)
    {
        return creditCardApplyRecordMapper.selectCreditCardApplyRecordList(creditCardApplyRecord);
    }

    /**
     * 新增用户信用卡申请记录
     * 
     * @param creditCardApplyRecord 用户信用卡申请记录
     * @return 结果
     */
    @Override
    public int insertCreditCardApplyRecord(CreditCardApplyRecord creditCardApplyRecord)
    {
        return creditCardApplyRecordMapper.insertCreditCardApplyRecord(creditCardApplyRecord);
    }

    /**
     * 修改用户信用卡申请记录
     * 
     * @param creditCardApplyRecord 用户信用卡申请记录
     * @return 结果
     */
    @Override
    public int updateCreditCardApplyRecord(CreditCardApplyRecord creditCardApplyRecord)
    {
        return creditCardApplyRecordMapper.updateCreditCardApplyRecord(creditCardApplyRecord);
    }

    /**
     * 批量删除用户信用卡申请记录
     * 
     * @param ids 需要删除的用户信用卡申请记录主键
     * @return 结果
     */
    @Override
    public AjaxResult deleteCreditCardApplyRecordByIds(Long[] ids)
    {
        for (int i = 0; i < ids.length; i++) {
            CreditCardApplyRecord creditCardApplyRecord = creditCardApplyRecordMapper.selectCreditCardApplyRecordById(ids[i]);
            if (creditCardApplyRecord.getStatus() == 0){
                return AjaxResult.error("有待审核的订单，操作失败");
            }
        }
        int count = creditCardApplyRecordMapper.deleteCreditCardApplyRecordByIds(ids);
        if (count <= 0){
            return AjaxResult.error();
        }
        return AjaxResult.success();
    }

    /**
     * 删除用户信用卡申请记录信息
     * 
     * @param id 用户信用卡申请记录主键
     * @return 结果
     */
    @Override
    public int deleteCreditCardApplyRecordById(Long id)
    {
        return creditCardApplyRecordMapper.deleteCreditCardApplyRecordById(id);
    }

    @Override
    @Transactional
    public AjaxResult toApplyCreditCard(Long userId, LoanApplyInfo loanApplyInfo) {
        UserInfo userInfo = userInfoMapper.selectUserInfoById(userId);
        if (userInfo.getRealNameAuthStatus() != 2){
            return AjaxResult.error("请先进行实名认证！");
        }
        UserBank search = new UserBank();
        search.setUserId(userId);
        List<UserBank> list = userBankMapper.selectUserBankList(search);
        if (list.size() <= 0){
            return AjaxResult.error("请先绑定银行卡");
        }

        //用户申请额度
        BigDecimal userApplyAmount = loanApplyInfo.getUserApplyAmount();
        //卡片未申请或者申请失败的时候才可以再次发其申请
        if (userInfo.getCreditCardStatus() != 0 & userInfo.getCreditCardStatus() != 3){
            if (userInfo.getCreditCardStatus() == 1){
                return AjaxResult.error("信用卡正在申请中，请耐心等候");
            }else {
                return AjaxResult.error("此账号已经申请过信用卡");
            }
        }

        CreditCardApplyRecord creditCardApplyRecord = new CreditCardApplyRecord();
        creditCardApplyRecord.setUserId(userId);
        creditCardApplyRecord.setRealName(userInfo.getRealName());
        creditCardApplyRecord.setUserApplyAmount(userApplyAmount);
        //新增卡片审核记录
        int insertCreditCardApplyRecord = creditCardApplyRecordMapper.insertCreditCardApplyRecord(creditCardApplyRecord);
        if (insertCreditCardApplyRecord <= 0){
            throw new RuntimeException("新增卡片审核记录异常");
        }

        //变更用户卡片状态为卡片申请审核中
        UserInfo userInfoVo = new UserInfo();
        userInfoVo.setId(userId);
        userInfoVo.setCreditCardStatus(1);
        int updateUserInfo = userInfoMapper.updateUserInfo(userInfoVo);
        if (updateUserInfo <= 0){
            throw new RuntimeException("变更用户卡片状态异常");
        }

        //保存申请贷款的基础信息
        loanApplyInfo.setUserId(userId);
        int loanApplyInfoCount = 0;
        LoanApplyInfo loanApplyInfoVo = loanApplyInfoMapper.selectLoanApplyInfoByUserId(userId);
        if (StringUtils.isNull(loanApplyInfoVo)){
            loanApplyInfoCount = loanApplyInfoMapper.insertLoanApplyInfo(loanApplyInfo);
        }else {
            loanApplyInfo.setId(loanApplyInfoVo.getId());
            loanApplyInfoCount = loanApplyInfoMapper.updateLoanApplyInfo(loanApplyInfo);
        }
        if (loanApplyInfoCount <= 0){
            throw new RuntimeException("保存申请贷款的基础信息异常");
        }

//        //信用卡片后台提醒
//        webBackgroundService.insertReminder("reminder_creditCardApply",userId,userInfo.getRealName());
        return AjaxResult.success();
    }

    /**
     * 申请信用卡通过
     */
    @Override
    @Transactional
    public AjaxResult agreeApplyCreditCard(CreditCardApplyRecord creditCardApplyRecord) {
        Long id = creditCardApplyRecord.getId();
        CreditCardApplyRecord creditCardApplyRecordVo = creditCardApplyRecordMapper.selectCreditCardApplyRecordById(id);
        if (creditCardApplyRecordVo.getStatus() != 0){
            return AjaxResult.error("已经审核过");
        }
        Long userId = creditCardApplyRecordVo.getUserId();
        UserInfo userInfoVo = new UserInfo();
        userInfoVo.setId(userId);
        //生成信用卡号,并且不重复
        while (true){
            //前缀固定4288
            String creditCardNumber = "4288"+ RandomStringUtils.randomNumeric(14);
            Integer countByCreditCardNumber = userInfoMapper.selectCountByCreditCardNumber(creditCardNumber);
            if (countByCreditCardNumber == 0){
                //信用卡号
                userInfoVo.setCreditCardNumber(creditCardNumber);
                creditCardApplyRecordVo.setCreditCardNumber(creditCardNumber);
                break;
            }
        }
        //信用卡安全码
        userInfoVo.setCreditCardSecurityCode(RandomStringUtils.randomNumeric(3));
        //更改申请状态为已通过
        userInfoVo.setCreditCardStatus(2);
        userInfoVo.setCreditCardTotalAmount(creditCardApplyRecord.getCreditCardTotalAmount());
        int updateUserInfo = userInfoMapper.updateUserInfo(userInfoVo);
        if (updateUserInfo <= 0){
            throw new RuntimeException();
        }

        creditCardApplyRecordVo.setStatus(1);
        creditCardApplyRecordVo.setCreditCardTotalAmount(creditCardApplyRecord.getCreditCardTotalAmount());
        int updateCreditCardApplyRecord = creditCardApplyRecordMapper.updateCreditCardApplyRecord(creditCardApplyRecordVo);
        if (updateCreditCardApplyRecord <= 0){
            throw new RuntimeException();
        }
//        //清除该用户卡片申请的后台提醒
//        webBackgroundService.delReminder("reminder_creditCardApply",userId);
        return AjaxResult.success();
    }

    @Override
    @Transactional
    public AjaxResult rejectApplyCreditCard(CreditCardApplyRecord creditCardApplyRecord) {
        Long id = creditCardApplyRecord.getId();
        CreditCardApplyRecord creditCardApplyRecordVo = creditCardApplyRecordMapper.selectCreditCardApplyRecordById(id);
        if (creditCardApplyRecordVo.getStatus() != 0){
            return AjaxResult.error("已经审核过");
        }
        Long userId = creditCardApplyRecordVo.getUserId();
        UserInfo userInfoVo = new UserInfo();
        userInfoVo.setId(userId);
        //更改申请状态为已拒绝
        userInfoVo.setCreditCardStatus(3);
        int updateUserInfo = userInfoMapper.updateUserInfo(userInfoVo);
        if (updateUserInfo <= 0){
            throw new RuntimeException();
        }

        creditCardApplyRecordVo.setStatus(2);
        creditCardApplyRecordVo.setCreditCardTotalAmount(BigDecimal.ZERO);
        creditCardApplyRecordVo.setRemark(creditCardApplyRecord.getRemark());
        int updateCreditCardApplyRecord = creditCardApplyRecordMapper.updateCreditCardApplyRecord(creditCardApplyRecordVo);
        if (updateCreditCardApplyRecord <= 0){
            throw new RuntimeException();
        }
//        //清除该用户卡片申请的后台提醒
//        webBackgroundService.delReminder("reminder_creditCardApply",userId);
        return AjaxResult.success();
    }

    /**
     * 查看信用卡申请失败原因
     */
    @Override
    public AjaxResult getApplyCreditCardRejectMsg(Long userId) {
        return AjaxResult.success(creditCardApplyRecordMapper.getApplyCreditCardRejectMsg(userId));
    }

    @Override
    public AjaxResult getCreditActivationPayInfo() {
        String creditActivationFixedPrice = "0";
        String creditActivationPayPercent = "0";
        String creditActivationPaySwitch = "0";

        //激活固定支付的额度
        OtherValue creditActivationFixedPriceOtherValue = otherValueService.selectOtherValueByKey("creditActivation_fixedPrice");
        if (StringUtils.isNotEmpty(creditActivationFixedPriceOtherValue.getOtherValue())){
            creditActivationFixedPrice = creditActivationFixedPriceOtherValue.getOtherValue();
        }

        //激活所需支付的百分比
        OtherValue creditActivationPayPercentOtherValue = otherValueService.selectOtherValueByKey("creditActivation_payPercent");
        if (StringUtils.isNotEmpty(creditActivationPayPercentOtherValue.getOtherValue())){
            creditActivationPayPercent = creditActivationPayPercentOtherValue.getOtherValue();
        }

        //支付方式开关 信用额度激活支付方式（0：固定额度 1：信用额度的百分比）
        OtherValue creditActivationPaySwitchOtherValue = otherValueService.selectOtherValueByKey("creditActivation_paySwitch");
        if (StringUtils.isNotEmpty(creditActivationPaySwitchOtherValue.getOtherValue())){
            creditActivationPaySwitch = creditActivationPaySwitchOtherValue.getOtherValue();
        }

        HashMap<String, Object> map = new HashMap<>();
        map.put("creditActivationFixedPrice",creditActivationFixedPrice);
        map.put("creditActivationPayPercent",creditActivationPayPercent);
        map.put("creditActivationPaySwitch",creditActivationPaySwitch);
        return AjaxResult.success(map);
    }

    @Override
    public BigDecimal getCreditActivationPayAmount(Long userId) {
        //用户信息
        UserInfo userInfo = userInfoMapper.selectUserInfoById(userId);
        //用户所需支付金额
        BigDecimal creditActivationPayAmount = BigDecimal.ZERO;

        //支付方式默认为固定金额
        Integer creditActivationPaySwitch = 0;
        //支付方式开关 信用额度激活支付方式（0：固定额度 1：信用额度的百分比）
        OtherValue creditActivationPaySwitchOtherValue = otherValueService.selectOtherValueByKey("creditActivation_paySwitch");
        if (StringUtils.isNotEmpty(creditActivationPaySwitchOtherValue.getOtherValue())){
            try {
                creditActivationPaySwitch = Integer.valueOf(creditActivationPaySwitchOtherValue.getOtherValue());
            }catch (Exception e){

            }
        }
        //如果是按固定金额
        if (creditActivationPaySwitch == 0){
            //激活固定支付的额度
            OtherValue creditActivationFixedPriceOtherValue = otherValueService.selectOtherValueByKey("creditActivation_fixedPrice");
            if (StringUtils.isNotEmpty(creditActivationFixedPriceOtherValue.getOtherValue())){
                try {
                    //固定金额
                    creditActivationPayAmount = new BigDecimal(creditActivationFixedPriceOtherValue.getOtherValue());
                }catch (Exception e){

                }
            }
        }else {
            //如果不是按固定金额
            //激活所需支付的百分比
            OtherValue creditActivationPayPercentOtherValue = otherValueService.selectOtherValueByKey("creditActivation_payPercent");
            if (StringUtils.isNotEmpty(creditActivationPayPercentOtherValue.getOtherValue())){
                try {
                    //百分比
                    BigDecimal creditActivationPayPercent = new BigDecimal(creditActivationPayPercentOtherValue.getOtherValue());
                    //用户的信用额度
                    BigDecimal creditCardTotalAmount = userInfo.getCreditCardTotalAmount();
                    creditActivationPayAmount = creditCardTotalAmount.multiply(creditActivationPayPercent);
                    creditActivationPayAmount = creditActivationPayAmount.divide(new BigDecimal(100),2, RoundingMode.HALF_UP);
                }catch (Exception e){

                }
            }
        }
        return creditActivationPayAmount;
    }

    @Override
    @Transactional
    public AjaxResult updateCreditActivationPayInfo(BigDecimal creditActivationFixedPrice, BigDecimal creditActivationPayPercent, Integer creditActivationPaySwitch) {
        int count = 0;
        count = otherValueService.updateOtherValue("creditActivation_fixedPrice", String.valueOf(creditActivationFixedPrice));
        if (count <= 0){
            throw new RuntimeException();
        }
        count = otherValueService.updateOtherValue("creditActivation_payPercent", String.valueOf(creditActivationPayPercent));
        if (count <= 0){
            throw new RuntimeException();
        }
        count = otherValueService.updateOtherValue("creditActivation_paySwitch", String.valueOf(creditActivationPaySwitch));
        if (count <= 0){
            throw new RuntimeException();
        }
        return AjaxResult.success();
    }
}
