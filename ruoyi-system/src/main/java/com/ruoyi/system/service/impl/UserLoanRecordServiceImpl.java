package com.ruoyi.system.service.impl;

import com.ruoyi.common.annotation.DataScope;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.*;
import com.ruoyi.system.mapper.*;
import com.ruoyi.system.service.IUserLoanRecordService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;

/**
 * 用户贷款记录Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-04-19
 */
@Service
public class UserLoanRecordServiceImpl implements IUserLoanRecordService 
{
    @Resource
    private UserLoanRecordMapper userLoanRecordMapper;

    @Resource
    private InstallmentInterestConfigMapper installmentInterestConfigMapper;

    @Resource
    private UserInfoMapper userInfoMapper;

    @Resource
    private UserLoanBillMapper userLoanBillMapper;

    @Resource
    private UserBankMapper userBankMapper;

    /**
     * 查询用户贷款记录
     * 
     * @param id 用户贷款记录主键
     * @return 用户贷款记录
     */
    @Override
    public UserLoanRecord selectUserLoanRecordById(Long id)
    {
        return userLoanRecordMapper.selectUserLoanRecordById(id);
    }

    /**
     * 查询用户贷款记录列表
     * 
     * @param userLoanRecord 用户贷款记录
     * @return 用户贷款记录
     */
    @Override
    @DataScope(userAlias = "su")
    public List<UserLoanRecord> selectUserLoanRecordList(UserLoanRecord userLoanRecord)
    {
        return userLoanRecordMapper.selectUserLoanRecordList(userLoanRecord);
    }

    /**
     * 新增用户贷款记录
     * 
     * @param userLoanRecord 用户贷款记录
     * @return 结果
     */
    @Override
    public int insertUserLoanRecord(UserLoanRecord userLoanRecord)
    {
        return userLoanRecordMapper.insertUserLoanRecord(userLoanRecord);
    }

    /**
     * 修改用户贷款记录
     * 
     * @param userLoanRecord 用户贷款记录
     * @return 结果
     */
    @Override
    public int updateUserLoanRecord(UserLoanRecord userLoanRecord)
    {
        return userLoanRecordMapper.updateUserLoanRecord(userLoanRecord);
    }

    /**
     * 修改收款银行卡信息
     *
     * @param userLoanRecord 用户贷款记录
     * @return 结果
     */
    @Override
    @Transactional
    public AjaxResult updateReceiveBankCardInfo(UserLoanRecord userLoanRecord){
        //贷款记录id
        Long id = userLoanRecord.getId();
        //贷款记录信息
        UserLoanRecord userLoanRecordVo = userLoanRecordMapper.selectUserLoanRecordById(id);
        //用户id
        Long userId = userLoanRecordVo.getUserId();
        UserBank userBank = new UserBank();
        userBank.setUserId(userId);
        List<UserBank> userBanks = userBankMapper.selectUserBankList(userBank);
        if (StringUtils.isEmpty(userBanks)){
            return AjaxResult.error();
        }
//        UserLoanRecord userLoanRecordVo = userLoanRecordMapper.selectUserLoanRecordById(userLoanRecord.getId());
        //绑定的银行卡信息
        userBank = userBanks.get(0);
//        //如果用户没有更改过银行卡信息
//        if (userBank.getBankName().equals(userLoanRecordVo.getBankName()) &&
//                userBank.getBankNo().equals(userLoanRecordVo.getBankNo()) &&
//                userBank.getBankAddress().equals(userLoanRecordVo.getBankAddress()) &&
//                userBank.getAccountHolder().equals(userLoanRecordVo.getAccountHolder()) &&
//                userBank.getSwiftCode().equals(userLoanRecordVo.getSwiftCode()) &&
//                userBank.getRoutingNumber().equals(userLoanRecordVo.getRoutingNumber())){
//        //变更记录收款银行卡信息的同时修改用户绑定的银行卡信息
//        UserBank userBankVo = new UserBank();
//        userBankVo.setId(userBank.getId());
//        userBankVo.setBankName(userLoanRecord.getBankName());
//        userBankVo.setBankNo(userLoanRecord.getBankNo());
//        userBankVo.setBankAddress(userLoanRecord.getBankAddress());
//        userBankVo.setAccountHolder(userLoanRecord.getAccountHolder());
//        userBankVo.setSwiftCode(userLoanRecord.getSwiftCode());
//        userBankVo.setRoutingNumber(userLoanRecord.getRoutingNumber());
//        int updateUserBank = userBankMapper.updateUserBank(userBankVo);
//        if (updateUserBank <= 0){
//            throw new RuntimeException();
//        }
//        }
        //变更记录收款银行卡信息的同时修改用户绑定的银行卡信息
        UserBank userBankVo = new UserBank();
        userBankVo.setId(userBank.getId());
        userBankVo.setBankName(userLoanRecord.getBankName());
        userBankVo.setBankNo(userLoanRecord.getBankNo());
        userBankVo.setBankAddress(userLoanRecord.getBankAddress());
        userBankVo.setAccountHolder(userLoanRecord.getAccountHolder());
        userBankVo.setSwiftCode(userLoanRecord.getSwiftCode());
        userBankVo.setRoutingNumber(userLoanRecord.getRoutingNumber());
        int updateUserBank = userBankMapper.updateUserBank(userBankVo);
        if (updateUserBank <= 0){
            throw new RuntimeException();
        }
        int updateUserLoanRecord = userLoanRecordMapper.updateUserLoanRecord(userLoanRecord);
        if (updateUserLoanRecord <= 0){
            throw new RuntimeException();
        }
        return AjaxResult.success();
    }

    /**
     * 批量删除用户贷款记录
     * 
     * @param ids 需要删除的用户贷款记录主键
     * @return 结果
     */
    @Override
    public int deleteUserLoanRecordByIds(Long[] ids)
    {
        return userLoanRecordMapper.deleteUserLoanRecordByIds(ids);
    }

    /**
     * 删除用户贷款记录信息
     * 
     * @param id 用户贷款记录主键
     * @return 结果
     */
    @Override
    public int deleteUserLoanRecordById(Long id)
    {
        return userLoanRecordMapper.deleteUserLoanRecordById(id);
    }

    /**
     * 信用额度提款
     */
    @Override
    @Transactional
    public AjaxResult addUserLoanRecord(UserLoanRecord userLoanRecord) {

        //贷款总金额
        BigDecimal loanTotalAmount = userLoanRecord.getLoanTotalAmount();
        if (loanTotalAmount.compareTo(BigDecimal.ZERO) <= 0){
            return AjaxResult.error("贷款金额必须大于0");
        }

        //用户id
        Long userId = userLoanRecord.getUserId();
        //用户信息
        UserInfo userInfo = userInfoMapper.selectUserInfoById(userId);
        //信用额度支付
        if (userInfo.getCreditCardStatus() != 5){
            return AjaxResult.error("信用卡片还未激活，无法使用信用额度","hint_91");
        }
        if (userInfo.getCreditCardEnableStatus() != 1){
            if (userInfo.getCreditCardEnableStatus() == 0){
                return AjaxResult.error("信用卡片还未激活，无法使用信用额度","hint_92");
            }else if (userInfo.getCreditCardEnableStatus() == 2){
                return AjaxResult.error("信用卡片已激活，但是无法使用，请联系客服","hint_93");
            }else if (userInfo.getCreditCardEnableStatus() == 3){
                return AjaxResult.error("请先前往开通vip权限","hint_109");
            }else {
                return AjaxResult.error();
            }
        }
        //信用卡剩余额度
        BigDecimal creditCardRemainingAmount = userInfo.getCreditCardRemainingAmount();
        if (creditCardRemainingAmount.compareTo(loanTotalAmount) < 0){
            return AjaxResult.error("可提款金额不足");
        }
        if (!SecurityUtils.matchesPassword(userLoanRecord.getPayPassword(),userInfo.getPayPassword())){
            return AjaxResult.error("资金密码错误");
        }
        int checkUndoneOrder = userLoanRecordMapper.checkUndoneOrder(userId);
        if (checkUndoneOrder >= 1){
            return AjaxResult.error("请先结束之前的分期贷款");
        }
        UserBank userBank = new UserBank();
        userBank.setUserId(userId);
        List<UserBank> userBanks = userBankMapper.selectUserBankList(userBank);
        if (StringUtils.isEmpty(userBanks)){
            return AjaxResult.error("请先绑定银行卡");
        }
        //绑定的银行卡信息
        userBank = userBanks.get(0);

        //分期配置id
        Long configId = userLoanRecord.getInstallmentInterestConfigId();
        //分期信息
        InstallmentInterestConfig config = installmentInterestConfigMapper.selectInstallmentInterestConfigById(configId);
        if (StringUtils.isNull(config)){
            return AjaxResult.error("获取分期信息异常，请稍后重新尝试");
        }
        //期数
        Integer repaymentPeriodNum = config.getRepaymentPeriodNum();
        //年利率
        BigDecimal annualInterestRate = config.getAnnualInterestRate();

        //年利息 = 本金*年利率/100
        BigDecimal annualInterestAmount = loanTotalAmount.multiply(annualInterestRate).divide(new BigDecimal(100),2,RoundingMode.HALF_UP);
        //月还款利息
        BigDecimal repaymentInterestEveryPeriod = annualInterestAmount.divide(new BigDecimal(12),2, RoundingMode.HALF_UP);
        //月还款本金
        BigDecimal repaymentPrincipalEveryPeriod = loanTotalAmount.divide(new BigDecimal(repaymentPeriodNum),2,RoundingMode.HALF_UP);
        //月还款总额
        BigDecimal repaymentTotalAmountEveryPeriod = repaymentInterestEveryPeriod.add(repaymentPrincipalEveryPeriod);
        //总利息
        BigDecimal interestAmountTotal = repaymentInterestEveryPeriod.multiply(new BigDecimal(repaymentPeriodNum));
        //需要还款总金额 = 贷款金额 + 总利息
        BigDecimal repaymentAmountTotal = loanTotalAmount.add(interestAmountTotal);


        UserLoanRecord userLoanRecordVo = new UserLoanRecord();
        userLoanRecordVo.setUserId(userId);
        userLoanRecordVo.setLoanTotalAmount(loanTotalAmount);
        userLoanRecordVo.setRepaymentAmountTotal(repaymentAmountTotal);
        userLoanRecordVo.setAnnualInterestRate(annualInterestRate);
        userLoanRecordVo.setRepaymentPeriodNum(repaymentPeriodNum);
        userLoanRecordVo.setAlreadyPaidPeriodNum(0);
        userLoanRecordVo.setRepaymentPrincipalEveryPeriod(repaymentPrincipalEveryPeriod);
        userLoanRecordVo.setRepaymentInterestEveryPeriod(repaymentInterestEveryPeriod);
        userLoanRecordVo.setRepaymentInterestTotal(interestAmountTotal);
        userLoanRecordVo.setRepaymentTotalAmountEveryPeriod(repaymentTotalAmountEveryPeriod);
        userLoanRecordVo.setStatus(0);

        //到账银行卡信息
        userLoanRecordVo.setBankName(userBank.getBankName());
        userLoanRecordVo.setBankNo(userBank.getBankNo());
        userLoanRecordVo.setBankAddress(userBank.getBankAddress());
        userLoanRecordVo.setAccountHolder(userBank.getAccountHolder());
        userLoanRecordVo.setSwiftCode(userBank.getSwiftCode());
        userLoanRecordVo.setRoutingNumber(userBank.getRoutingNumber());
        //新增分期订单
        int insertUserLoanRecord = userLoanRecordMapper.insertUserLoanRecord(userLoanRecordVo);
        if (insertUserLoanRecord <= 0){
            throw new RuntimeException();
        }

        //更新用户的已用信用额度
        BigDecimal creditCardUsedAmountBefore = userInfo.getCreditCardUsedAmount();
        BigDecimal creditCardUsedAmountAfter = creditCardUsedAmountBefore.add(loanTotalAmount);
        UserInfo userInfoVo = new UserInfo();
        userInfoVo.setId(userId);
        userInfoVo.setCreditCardUsedAmount(creditCardUsedAmountAfter);
        int updateUserInfo = userInfoMapper.updateUserInfo(userInfoVo);
        if (updateUserInfo <= 0){
            throw new RuntimeException();
        }
        return AjaxResult.success();
    }

    /**
     * 通过贷款申请
     */
    @Override
    @Transactional
    public AjaxResult agree(Long id){
        UserLoanRecord userLoanRecord = userLoanRecordMapper.selectUserLoanRecordById(id);
        if (userLoanRecord == null){
            return AjaxResult.error("获取贷款信息异常");
        }
        if (userLoanRecord.getStatus() != 0){
            return AjaxResult.error("此贷款记录已经审核过");
        }

        //生成本月账单
        UserLoanBill userLoanBill = new UserLoanBill();
        userLoanBill.setUserId(userLoanRecord.getUserId());
        userLoanBill.setRepaymentTotalAmountEveryPeriod(userLoanRecord.getRepaymentTotalAmountEveryPeriod());
        userLoanBill.setRepaidAmountThisPeriod(BigDecimal.ZERO);
        userLoanBill.setRepaymentStatus(0);
        userLoanBill.setUserLoanRecordId(id);
        userLoanBill.setBillTime(new Date());

        int insertUserLoanBill = userLoanBillMapper.insertUserLoanBill(userLoanBill);
        if (insertUserLoanBill <= 0){
            throw new RuntimeException();
        }

        //贷款记录状态变为审核通过
        UserLoanRecord userLoanRecordVo = new UserLoanRecord();
        userLoanRecordVo.setId(userLoanRecord.getId());
        userLoanRecordVo.setStatus(1);
        int updateUserLoanRecord = userLoanRecordMapper.updateUserLoanRecord(userLoanRecordVo);
        if (updateUserLoanRecord <= 0){
            throw new RuntimeException();
        }
        return AjaxResult.success();
    }


    /**
     * 驳回贷款申请
     */
    public AjaxResult reject(Long id){
        UserLoanRecord userLoanRecord = userLoanRecordMapper.selectUserLoanRecordById(id);
        if (userLoanRecord == null){
            return AjaxResult.error("获取贷款信息异常");
        }
        if (userLoanRecord.getStatus() != 0){
            return AjaxResult.error("此贷款记录已经审核过");
        }

        //贷款记录状态变为审核驳回
        UserLoanRecord userLoanRecordVo = new UserLoanRecord();
        userLoanRecordVo.setId(userLoanRecord.getId());
        userLoanRecordVo.setStatus(2);
        int updateUserLoanRecord = userLoanRecordMapper.updateUserLoanRecord(userLoanRecordVo);
        if (updateUserLoanRecord <= 0){
            return AjaxResult.error();
        }
        return AjaxResult.success();
    }
}
