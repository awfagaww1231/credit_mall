package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.CreditCardApplyRecord;

import java.util.List;

/**
 * 用户信用卡申请记录Mapper接口
 * 
 * @author ruoyi
 * @date 2023-03-20
 */
public interface CreditCardApplyRecordMapper 
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
     * 删除用户信用卡申请记录
     * 
     * @param id 用户信用卡申请记录主键
     * @return 结果
     */
    public int deleteCreditCardApplyRecordById(Long id);

    /**
     * 批量删除用户信用卡申请记录
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCreditCardApplyRecordByIds(Long[] ids);

    //获取最近一次被拒绝申请的拒绝原因
    String getApplyCreditCardRejectMsg(Long userId);
}
