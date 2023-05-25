package com.ruoyi.system.service;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.system.domain.CreditCardApplyRecord;
import com.ruoyi.system.domain.LoanApplyInfo;

import java.math.BigDecimal;
import java.util.List;

/**
 * 用户信用卡申请记录Service接口
 * 
 * @author ruoyi
 * @date 2023-03-20
 */
public interface ICreditCardApplyRecordService 
{
    /**
     * 查询用户信用卡申请记录
     * 
     * @param id 用户信用卡申请记录主键
     * @return 用户信用卡申请记录
     */
    public CreditCardApplyRecord selectCreditCardApplyRecordById(Long id);

    /**
     * 查询用户信用卡申请记录列表
     * 
     * @param creditCardApplyRecord 用户信用卡申请记录
     * @return 用户信用卡申请记录集合
     */
    public List<CreditCardApplyRecord> selectCreditCardApplyRecordList(CreditCardApplyRecord creditCardApplyRecord);

    /**
     * 新增用户信用卡申请记录
     * 
     * @param creditCardApplyRecord 用户信用卡申请记录
     * @return 结果
     */
    public int insertCreditCardApplyRecord(CreditCardApplyRecord creditCardApplyRecord);

    /**
     * 修改用户信用卡申请记录
     * 
     * @param creditCardApplyRecord 用户信用卡申请记录
     * @return 结果
     */
    public int updateCreditCardApplyRecord(CreditCardApplyRecord creditCardApplyRecord);

    /**
     * 批量删除用户信用卡申请记录
     * 
     * @param ids 需要删除的用户信用卡申请记录主键集合
     * @return 结果
     */
    public AjaxResult deleteCreditCardApplyRecordByIds(Long[] ids);

    /**
     * 删除用户信用卡申请记录信息
     * 
     * @param id 用户信用卡申请记录主键
     * @return 结果
     */
    public int deleteCreditCardApplyRecordById(Long id);

    //申请信用卡
    public AjaxResult toApplyCreditCard(Long userId,LoanApplyInfo loanApplyInfo);

    /**
     * 申请信用卡通过
     */
    public AjaxResult agreeApplyCreditCard(CreditCardApplyRecord creditCardApplyRecord);

    /**
     * 申请信用卡拒绝
     */
    public AjaxResult rejectApplyCreditCard(CreditCardApplyRecord creditCardApplyRecord);

    /**
     * 查看信用卡申请失败原因
     */
    public AjaxResult getApplyCreditCardRejectMsg(Long userId);

    /**
     * 获取激活所需支付信息
     */
    AjaxResult getCreditActivationPayInfo();

    /**
     * 获取激活支付所需金额
     */
    BigDecimal getCreditActivationPayAmount(Long userId);

    /**
     * 修改激活支付信息
     */
    AjaxResult updateCreditActivationPayInfo(BigDecimal creditActivationFixedPrice, BigDecimal creditActivationPayPercent, Integer creditActivationPaySwitch);
}
