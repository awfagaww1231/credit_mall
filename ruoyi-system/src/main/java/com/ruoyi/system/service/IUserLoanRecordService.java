package com.ruoyi.system.service;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.system.domain.UserLoanRecord;

import java.util.List;

/**
 * 用户贷款记录Service接口
 * 
 * @author ruoyi
 * @date 2023-04-19
 */
public interface IUserLoanRecordService 
{
    /**
     * 查询用户贷款记录
     * 
     * @param id 用户贷款记录主键
     * @return 用户贷款记录
     */
    public UserLoanRecord selectUserLoanRecordById(Long id);

    /**
     * 查询用户贷款记录列表
     * 
     * @param userLoanRecord 用户贷款记录
     * @return 用户贷款记录集合
     */
    public List<UserLoanRecord> selectUserLoanRecordList(UserLoanRecord userLoanRecord);

    /**
     * 新增用户贷款记录
     * 
     * @param userLoanRecord 用户贷款记录
     * @return 结果
     */
    public int insertUserLoanRecord(UserLoanRecord userLoanRecord);

    /**
     * 修改用户贷款记录
     * 
     * @param userLoanRecord 用户贷款记录
     * @return 结果
     */
    public int updateUserLoanRecord(UserLoanRecord userLoanRecord);

    /**
     * 修改收款银行卡信息
     *
     * @param userLoanRecord 用户贷款记录
     * @return 结果
     */
    public AjaxResult updateReceiveBankCardInfo(UserLoanRecord userLoanRecord);

    /**
     * 批量删除用户贷款记录
     * 
     * @param ids 需要删除的用户贷款记录主键集合
     * @return 结果
     */
    public int deleteUserLoanRecordByIds(Long[] ids);

    /**
     * 删除用户贷款记录信息
     * 
     * @param id 用户贷款记录主键
     * @return 结果
     */
    public int deleteUserLoanRecordById(Long id);

    /**
     * 信用额度提款
     */
    AjaxResult addUserLoanRecord(UserLoanRecord userLoanRecord);

    /**
     * 通过贷款申请
     */
    public AjaxResult agree(Long id);


    /**
     * 驳回贷款申请
     */
    public AjaxResult reject(Long id);
}
