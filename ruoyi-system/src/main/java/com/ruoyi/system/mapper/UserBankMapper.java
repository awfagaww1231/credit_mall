package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.UserBank;

import java.util.List;

/**
 * 用户银行Mapper接口
 * 
 * @author ruoyi
 * @date 2023-02-16
 */
public interface UserBankMapper 
{
    /**
     * 查询用户银行
     * 
     * @param id 用户银行主键
     * @return 用户银行
     */
    public UserBank selectUserBankById(Long id);

    /**
     * 查询用户银行列表
     * 
     * @param userBank 用户银行
     * @return 用户银行集合
     */
    public List<UserBank> selectUserBankList(UserBank userBank);

    /**
     * 新增用户银行
     * 
     * @param userBank 用户银行
     * @return 结果
     */
    public int insertUserBank(UserBank userBank);

    /**
     * 修改用户银行
     * 
     * @param userBank 用户银行
     * @return 结果
     */
    public int updateUserBank(UserBank userBank);

    /**
     * 删除用户银行
     * 
     * @param id 用户银行主键
     * @return 结果
     */
    public int deleteUserBankById(Long id);

    /**
     * 批量删除用户银行
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteUserBankByIds(Long[] ids);
}
