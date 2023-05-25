package com.ruoyi.system.service.impl;

import com.ruoyi.system.domain.UserBank;
import com.ruoyi.system.mapper.UserBankMapper;
import com.ruoyi.system.service.IUserBankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 用户银行Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-02-16
 */
@Service
public class UserBankServiceImpl implements IUserBankService 
{
    @Autowired
    private UserBankMapper userBankMapper;

    /**
     * 查询用户银行
     * 
     * @param id 用户银行主键
     * @return 用户银行
     */
    @Override
    public UserBank selectUserBankById(Long id)
    {
        return userBankMapper.selectUserBankById(id);
    }

    /**
     * 查询用户银行列表
     * 
     * @param userBank 用户银行
     * @return 用户银行
     */
    @Override
    public List<UserBank> selectUserBankList(UserBank userBank)
    {
        return userBankMapper.selectUserBankList(userBank);
    }

    /**
     * 新增用户银行
     * 
     * @param userBank 用户银行
     * @return 结果
     */
    @Override
    public int insertUserBank(UserBank userBank)
    {
        userBank.setAddTime(new Date());
        return userBankMapper.insertUserBank(userBank);
    }

    /**
     * 修改用户银行
     * 
     * @param userBank 用户银行
     * @return 结果
     */
    @Override
    public int updateUserBank(UserBank userBank)
    {
        return userBankMapper.updateUserBank(userBank);
    }

    /**
     * 批量删除用户银行
     * 
     * @param ids 需要删除的用户银行主键
     * @return 结果
     */
    @Override
    public int deleteUserBankByIds(Long[] ids)
    {
        return userBankMapper.deleteUserBankByIds(ids);
    }

    /**
     * 删除用户银行信息
     * 
     * @param id 用户银行主键
     * @return 结果
     */
    @Override
    public int deleteUserBankById(Long id)
    {
        return userBankMapper.deleteUserBankById(id);
    }
}
