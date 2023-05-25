package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.UserLoanRecord;

import java.util.List;

/**
 * 用户贷款记录Mapper接口
 * 
 * @author ruoyi
 * @date 2023-04-19
 */
public interface UserLoanRecordMapper 
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
     * 删除用户贷款记录
     * 
     * @param id 用户贷款记录主键
     * @return 结果
     */
    public int deleteUserLoanRecordById(Long id);

    /**
     * 批量删除用户贷款记录
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteUserLoanRecordByIds(Long[] ids);

    //查看用户是否有待审核或待完成的贷款订单
    int checkUndoneOrder(Long userId);
}
