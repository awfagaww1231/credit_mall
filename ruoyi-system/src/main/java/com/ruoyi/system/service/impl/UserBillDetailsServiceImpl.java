package com.ruoyi.system.service.impl;

import com.ruoyi.common.annotation.DataScope;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.system.domain.UserBillDetails;
import com.ruoyi.system.mapper.UserBillDetailsMapper;
import com.ruoyi.system.service.IUserBillDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * C端用户账单明细Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-11-10
 */
@Service
public class UserBillDetailsServiceImpl implements IUserBillDetailsService 
{
    @Autowired
    private UserBillDetailsMapper userBillDetailsMapper;

    /**
     * 查询C端用户账单明细
     * 
     * @param id C端用户账单明细主键
     * @return C端用户账单明细
     */
    @Override
    public UserBillDetails selectUserBillDetailsById(Long id)
    {
        return userBillDetailsMapper.selectUserBillDetailsById(id);
    }

    /**
     * 查询C端用户账单明细列表
     * 
     * @param userBillDetails C端用户账单明细
     * @return C端用户账单明细
     */
    @Override
    @DataScope(userAlias = "su")
    public List<UserBillDetails> selectUserBillDetailsList(UserBillDetails userBillDetails)
    {
        if ("1".equals(userBillDetails.getMyInvite())){//仅查询我要的用户的流水
            String inviteCode = SecurityUtils.getLoginUser().getUserId().toString();
            userBillDetails.setInviteCode(inviteCode);
        }
        return userBillDetailsMapper.selectUserBillDetailsList(userBillDetails);
    }

    /**
     * 新增C端用户账单明细
     * 
     * @param userBillDetails C端用户账单明细
     * @return 结果
     */
    @Override
    public int insertUserBillDetails(UserBillDetails userBillDetails)
    {
        return userBillDetailsMapper.insertUserBillDetails(userBillDetails);
    }

    /**
     * 修改C端用户账单明细
     * 
     * @param userBillDetails C端用户账单明细
     * @return 结果
     */
    @Override
    public int updateUserBillDetails(UserBillDetails userBillDetails)
    {
        return userBillDetailsMapper.updateUserBillDetails(userBillDetails);
    }

    /**
     * 批量删除C端用户账单明细
     * 
     * @param ids 需要删除的C端用户账单明细主键
     * @return 结果
     */
    @Override
    public int deleteUserBillDetailsByIds(Long[] ids)
    {
        return userBillDetailsMapper.deleteUserBillDetailsByIds(ids);
    }

    /**
     * 删除C端用户账单明细信息
     * 
     * @param id C端用户账单明细主键
     * @return 结果
     */
    @Override
    public int deleteUserBillDetailsById(Long id)
    {
        return userBillDetailsMapper.deleteUserBillDetailsById(id);
    }
}
