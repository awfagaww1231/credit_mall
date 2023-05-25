package com.ruoyi.system.service.impl;

import com.ruoyi.common.annotation.DataScope;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.common.AdminTokenService;
import com.ruoyi.system.domain.UserBillDetails;
import com.ruoyi.system.domain.UserInfo;
import com.ruoyi.system.domain.UserWithdraw;
import com.ruoyi.system.mapper.UserBillDetailsMapper;
import com.ruoyi.system.mapper.UserInfoMapper;
import com.ruoyi.system.mapper.UserWithdrawMapper;
import com.ruoyi.system.service.IUserWithdrawService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 提现记录Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-02-17
 */
@Service
public class UserWithdrawServiceImpl implements IUserWithdrawService 
{
    @Resource
    private UserWithdrawMapper userWithdrawMapper;

    @Autowired
    private AdminTokenService tokenService;

    @Resource
    private UserInfoMapper userInfoMapper;

    @Resource
    private UserBillDetailsMapper userBillDetailsMapper;

    /**
     * 查询提现记录
     * 
     * @param id 提现记录主键
     * @return 提现记录
     */
    @Override
    public UserWithdraw selectUserWithdrawById(Long id)
    {
        return userWithdrawMapper.selectUserWithdrawById(id);
    }

    /**
     * 查询提现记录列表
     * 
     * @param userWithdraw 提现记录
     * @return 提现记录
     */
    @Override
    @DataScope(userAlias = "su")
    public List<UserWithdraw> selectUserWithdrawList(UserWithdraw userWithdraw)
    {
        return userWithdrawMapper.selectUserWithdrawList(userWithdraw);
    }

    /**
     * 新增提现记录
     * 
     * @param userWithdraw 提现记录
     * @return 结果
     */
    @Override
    public int insertUserWithdraw(UserWithdraw userWithdraw)
    {
        return userWithdrawMapper.insertUserWithdraw(userWithdraw);
    }

    /**
     * 修改提现记录
     * 
     * @param userWithdraw 提现记录
     * @return 结果
     */
    @Override
    public int updateUserWithdraw(UserWithdraw userWithdraw)
    {
        return userWithdrawMapper.updateUserWithdraw(userWithdraw);
    }

    /**
     * 批量删除提现记录
     * 
     * @param ids 需要删除的提现记录主键
     * @return 结果
     */
    @Override
    public int deleteUserWithdrawByIds(Long[] ids)
    {
        return userWithdrawMapper.deleteUserWithdrawByIds(ids);
    }

    /**
     * 删除提现记录信息
     * 
     * @param id 提现记录主键
     * @return 结果
     */
    @Override
    public int deleteUserWithdrawById(Long id)
    {
        return userWithdrawMapper.deleteUserWithdrawById(id);
    }


    @Override
    @Transactional
    public AjaxResult agree(Long id) throws Exception {
        LoginUser loginUser = tokenService.getLoginUser ();
        UserWithdraw userWithdraw = userWithdrawMapper.selectUserWithdrawById (id);
        if (StringUtils.isNull(userWithdraw)){
            return AjaxResult.error("获取提现订单信息出错，请刷新后重新尝试");
        }
        if (userWithdraw.getStatus() != 0){
            return AjaxResult.error("订单已经审核过，无须重复审核");
        }

        //通过订单操作
        userWithdraw.setStatus(1);
        userWithdraw.setApproveTime(new Date());
        userWithdraw.setApproveName(loginUser.getUsername());
        int updateUserWithdraw = userWithdrawMapper.updateUserWithdraw(userWithdraw);
        if (updateUserWithdraw <= 0){
            throw new RuntimeException();
        }
        return AjaxResult.success();
    }

    @Override
    @Transactional
    public AjaxResult reject(Long id) throws Exception {
        LoginUser loginUser = tokenService.getLoginUser ();
        UserWithdraw userWithdraw = userWithdrawMapper.selectUserWithdrawById (id);
        if (StringUtils.isNull(userWithdraw)){
            return AjaxResult.error("获取提现订单信息出错，请刷新后重新尝试");
        }
        if (userWithdraw.getStatus() != 0){
            return AjaxResult.error("订单已经审核过，无须重复审核");
        }
        Long userId = userWithdraw.getUserId();
        UserInfo userInfo = userInfoMapper.selectUserInfoById(userId);
        if (StringUtils.isNull(userInfo)){
            return AjaxResult.error("该订单的用户不存在");
        }

        //驳回订单操作
        userWithdraw.setStatus(2);
        userWithdraw.setApproveTime(new Date());
        userWithdraw.setApproveName(loginUser.getUsername());
        int updateUserWithdraw = userWithdrawMapper.updateUserWithdraw(userWithdraw);
        if (updateUserWithdraw <= 0){
            throw new RuntimeException();
        }

        //变更前金额
        BigDecimal userAmountBefore = userInfo.getAmount();
        //变更后金额
        BigDecimal userAmountAfter = userAmountBefore.add(userWithdraw.getWithdrawAmount());
        //变更用户金额
        int updateUserAmount = userInfoMapper.updateUserAmount(userId, userAmountAfter,userAmountBefore);
        if (updateUserAmount <= 0){
            throw new RuntimeException();
        }

        //提现驳回资金退回流水记录
        UserBillDetails userBillDetails = new UserBillDetails();
        userBillDetails.setOrderCode(userWithdraw.getWithdrawOrder());
        userBillDetails.setOrderAmount(userWithdraw.getWithdrawAmount());
        userBillDetails.setOrderType(0);
        //流水时间为后台审核的通过时间
        userBillDetails.setOrderTime(userWithdraw.getApproveTime());
        userBillDetails.setUserId(userId);
        userBillDetails.setOrderClass(4);
        userBillDetails.setAmountBefore(userAmountBefore);
        userBillDetails.setAmountAfter(userAmountAfter);
        int insertUserBillDetails = userBillDetailsMapper.insertUserBillDetails(userBillDetails);
        if (insertUserBillDetails <= 0){
            throw new RuntimeException();
        }
        return AjaxResult.success();
    }
}
