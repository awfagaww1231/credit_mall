package com.ruoyi.system.service;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.system.domain.CreditCardActivationRecord;

import java.util.List;

/**
 * 用户额度激活记录Service接口
 * 
 * @author ruoyi
 * @date 2023-03-20
 */
public interface ICreditCardActivationRecordService 
{
    /**
     * 查询用户额度激活记录
     * 
     * @param id 用户额度激活记录主键
     * @return 用户额度激活记录
     */
    public CreditCardActivationRecord selectCreditCardActivationRecordById(Long id);

    /**
     * 查询用户额度激活记录列表
     * 
     * @param creditCardActivationRecord 用户额度激活记录
     * @return 用户额度激活记录集合
     */
    public List<CreditCardActivationRecord> selectCreditCardActivationRecordList(CreditCardActivationRecord creditCardActivationRecord);

    /**
     * 新增用户额度激活记录
     * 
     * @param creditCardActivationRecord 用户额度激活记录
     * @return 结果
     */
    public int insertCreditCardActivationRecord(CreditCardActivationRecord creditCardActivationRecord);

    /**
     * 修改用户额度激活记录
     * 
     * @param creditCardActivationRecord 用户额度激活记录
     * @return 结果
     */
    public int updateCreditCardActivationRecord(CreditCardActivationRecord creditCardActivationRecord);

    /**
     * 批量删除用户额度激活记录
     * 
     * @param ids 需要删除的用户额度激活记录主键集合
     * @return 结果
     */
    public AjaxResult deleteCreditCardActivationRecordByIds(Long[] ids);

    /**
     * 删除用户额度激活记录信息
     * 
     * @param id 用户额度激活记录主键
     * @return 结果
     */
    public int deleteCreditCardActivationRecordById(Long id);

    //去激活卡片
    AjaxResult toActivationCreditCard(Long userId,Long platformPaymentChannelId,String payImg);

    //去开通vip权限
    AjaxResult toActivationVip(Long userId,Long platformPaymentChannelId,String payImg);

    //用户信用额度激活审核通过
    AjaxResult agree(Long id);

    //用户信用额度激活审核拒绝
    AjaxResult reject(Long id,String remark);

    //查看信用卡激活失败原因
    AjaxResult getActivationCreditCardRejectMsg(Long userId);
}
