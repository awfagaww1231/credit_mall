package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.CreditCardActivationRecord;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户额度激活记录Mapper接口
 * 
 * @author ruoyi
 * @date 2023-03-20
 */
public interface CreditCardActivationRecordMapper 
{
    /**
     * 查询用户额度激活记录
     * 
     * @param id 用户额度激活记录主键
     * @return 用户额度激活记录
     */
    public CreditCardActivationRecord selectCreditCardActivationRecordById(Long id);

    /**
     * 查询用户额度激活记录
     *
     * @param userId 用户id
     * @return 用户额度激活记录
     */
    public CreditCardActivationRecord selectCreditCardActivationRecordByUserId(@Param("userId") Long userId,
                                                                               @Param("status")Integer status);

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
     * 删除用户额度激活记录
     * 
     * @param id 用户额度激活记录主键
     * @return 结果
     */
    public int deleteCreditCardActivationRecordById(Long id);

    /**
     * 批量删除用户额度激活记录
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCreditCardActivationRecordByIds(Long[] ids);

    String getActivationCreditCardRejectMsg(Long userId);
}
