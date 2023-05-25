package com.ruoyi.system.service;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.system.domain.UserWithdraw;

import java.util.List;

/**
 * 提现记录Service接口
 * 
 * @author ruoyi
 * @date 2023-02-17
 */
public interface IUserWithdrawService 
{
    /**
     * 查询提现记录
     * 
     * @param id 提现记录主键
     * @return 提现记录
     */
    public UserWithdraw selectUserWithdrawById(Long id);

    /**
     * 查询提现记录列表
     * 
     * @param userWithdraw 提现记录
     * @return 提现记录集合
     */
    public List<UserWithdraw> selectUserWithdrawList(UserWithdraw userWithdraw);

    /**
     * 新增提现记录
     * 
     * @param userWithdraw 提现记录
     * @return 结果
     */
    public int insertUserWithdraw(UserWithdraw userWithdraw);

    /**
     * 修改提现记录
     * 
     * @param userWithdraw 提现记录
     * @return 结果
     */
    public int updateUserWithdraw(UserWithdraw userWithdraw);

    /**
     * 批量删除提现记录
     * 
     * @param ids 需要删除的提现记录主键集合
     * @return 结果
     */
    public int deleteUserWithdrawByIds(Long[] ids);

    /**
     * 删除提现记录信息
     * 
     * @param id 提现记录主键
     * @return 结果
     */
    public int deleteUserWithdrawById(Long id);

    //通过提现申请
    AjaxResult agree(Long id) throws Exception;

    //驳回提现申请
    AjaxResult reject(Long id) throws Exception;
}
