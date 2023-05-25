package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.UserLoanBill;

import java.util.List;

/**
 * 用户贷款还款账单Mapper接口
 * 
 * @author ruoyi
 * @date 2023-04-28
 */
public interface UserLoanBillMapper 
{
    /**
     * 查询用户贷款还款账单
     * 
     * @param id 用户贷款还款账单主键
     * @return 用户贷款还款账单
     */
    public UserLoanBill selectUserLoanBillById(Long id);

    /**
     * 查询用户贷款还款账单列表
     * 
     * @param userLoanBill 用户贷款还款账单
     * @return 用户贷款还款账单集合
     */
    public List<UserLoanBill> selectUserLoanBillList(UserLoanBill userLoanBill);

    /**
     * 新增用户贷款还款账单
     * 
     * @param userLoanBill 用户贷款还款账单
     * @return 结果
     */
    public int insertUserLoanBill(UserLoanBill userLoanBill);

    /**
     * 修改用户贷款还款账单
     * 
     * @param userLoanBill 用户贷款还款账单
     * @return 结果
     */
    public int updateUserLoanBill(UserLoanBill userLoanBill);

    /**
     * 删除用户贷款还款账单
     * 
     * @param id 用户贷款还款账单主键
     * @return 结果
     */
    public int deleteUserLoanBillById(Long id);

    /**
     * 批量删除用户贷款还款账单
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteUserLoanBillByIds(Long[] ids);
}
