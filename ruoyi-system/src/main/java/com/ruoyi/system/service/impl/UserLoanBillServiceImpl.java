package com.ruoyi.system.service.impl;

import com.ruoyi.common.annotation.DataScope;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.x.keyUtil.KeyUtil;
import com.ruoyi.system.domain.UserBillDetails;
import com.ruoyi.system.domain.UserInfo;
import com.ruoyi.system.domain.UserLoanBill;
import com.ruoyi.system.domain.UserLoanRecord;
import com.ruoyi.system.mapper.UserBillDetailsMapper;
import com.ruoyi.system.mapper.UserInfoMapper;
import com.ruoyi.system.mapper.UserLoanBillMapper;
import com.ruoyi.system.mapper.UserLoanRecordMapper;
import com.ruoyi.system.service.IUserLoanBillService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 用户贷款还款账单Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-04-28
 */
@Service
public class UserLoanBillServiceImpl implements IUserLoanBillService 
{
    @Resource
    private UserLoanBillMapper userLoanBillMapper;

    @Resource
    private UserInfoMapper userInfoMapper;

    @Resource
    private UserBillDetailsMapper userBillDetailsMapper;

    @Resource
    private UserLoanRecordMapper userLoanRecordMapper;


    /**
     * 查询用户贷款还款账单
     * 
     * @param id 用户贷款还款账单主键
     * @return 用户贷款还款账单
     */
    @Override
    public UserLoanBill selectUserLoanBillById(Long id)
    {
        return userLoanBillMapper.selectUserLoanBillById(id);
    }

    /**
     * 查询用户贷款还款账单列表
     * 
     * @param userLoanBill 用户贷款还款账单
     * @return 用户贷款还款账单
     */
    @Override
    @DataScope(userAlias = "su")
    public List<UserLoanBill> selectUserLoanBillList(UserLoanBill userLoanBill)
    {
        return userLoanBillMapper.selectUserLoanBillList(userLoanBill);
    }

    /**
     * 新增用户贷款还款账单
     * 
     * @param userLoanBill 用户贷款还款账单
     * @return 结果
     */
    @Override
    public int insertUserLoanBill(UserLoanBill userLoanBill)
    {
        return userLoanBillMapper.insertUserLoanBill(userLoanBill);
    }

    /**
     * 修改用户贷款还款账单
     * 
     * @param userLoanBill 用户贷款还款账单
     * @return 结果
     */
    @Override
    public int updateUserLoanBill(UserLoanBill userLoanBill)
    {
        return userLoanBillMapper.updateUserLoanBill(userLoanBill);
    }

    /**
     * 批量删除用户贷款还款账单
     * 
     * @param ids 需要删除的用户贷款还款账单主键
     * @return 结果
     */
    @Override
    public int deleteUserLoanBillByIds(Long[] ids)
    {
        return userLoanBillMapper.deleteUserLoanBillByIds(ids);
    }

    /**
     * 删除用户贷款还款账单信息
     * 
     * @param id 用户贷款还款账单主键
     * @return 结果
     */
    @Override
    public int deleteUserLoanBillById(Long id)
    {
        return userLoanBillMapper.deleteUserLoanBillById(id);
    }

    /**
     * 去还款贷款每月账单
     */
    @Override
    @Transactional
    public AjaxResult toPayLoanBill(Long userId,Long userLoanBillId, BigDecimal payAmount){
        //用户信息
        UserInfo userInfo = userInfoMapper.selectUserInfoById(userId);
        //用户余额
        BigDecimal userAmountBefore = userInfo.getAmount();
        if (userAmountBefore.compareTo(payAmount) == -1){
            return AjaxResult.error("用户钱包余额不足");
        }
        //账单信息
        UserLoanBill userLoanBill = userLoanBillMapper.selectUserLoanBillById(userLoanBillId);
        if (userLoanBill == null){
            return AjaxResult.error("获取账单信息失效");
        }
        if (userLoanBill.getRepaymentStatus() != 0 && userLoanBill.getRepaymentStatus() != 2){
            return AjaxResult.error("此账单已经还款结清过");
        }
        //还款记录信息
        UserLoanRecord userLoanRecord = userLoanRecordMapper.selectUserLoanRecordById(userLoanBill.getUserLoanRecordId());
        if (userLoanRecord == null){
            return AjaxResult.error("获取还款记录信息异常");
        }

        //本期已还款
        BigDecimal repaidAmountThisPeriod = userLoanBill.getRepaidAmountThisPeriod();
        //本期需还款
        BigDecimal repaymentTotalAmountEveryPeriod = userLoanBill.getRepaymentTotalAmountEveryPeriod();
        //还需要还款
        BigDecimal subtract = repaymentTotalAmountEveryPeriod.subtract(repaidAmountThisPeriod);
        if (payAmount.compareTo(subtract) == 1){
            return AjaxResult.error("本月账单仅需还款"+subtract);
        }
        //用户余额变更后偶
        BigDecimal userAmountAfter = userAmountBefore.subtract(payAmount);

        UserLoanBill userLoanBillVo = new UserLoanBill();
        userLoanBillVo.setId(userLoanBill.getId());
        userLoanBillVo.setVersion(userLoanBill.getVersion());
        userLoanBillVo.setRepaidAmountThisPeriod(repaidAmountThisPeriod.add(payAmount));
        if (payAmount.compareTo(subtract) == 0){
            //如果直接还清账单
            //状态变为已经结清
            userLoanBillVo.setRepaymentStatus(1);

            UserLoanRecord userLoanRecordVo = new UserLoanRecord();
            userLoanRecordVo.setId(userLoanRecord.getId());
            //已经还款期数
            Integer alreadyPaidPeriodNum = userLoanRecord.getAlreadyPaidPeriodNum();
            //待还款期数
            Integer repaymentPeriodNum = userLoanRecord.getRepaymentPeriodNum();
            //如果最后一期已经还清
            if (alreadyPaidPeriodNum + 1 == repaymentPeriodNum){
                //状态变为已结清
                userLoanRecordVo.setStatus(3);
            }
            //已还款期数+1
            userLoanRecordVo.setAlreadyPaidPeriodNum(alreadyPaidPeriodNum + 1);
            int updateUserLoanRecord = userLoanRecordMapper.updateUserLoanRecord(userLoanRecordVo);
            if (updateUserLoanRecord <= 0){
                throw new RuntimeException();
            }

        }
        int updateUserLoanBill = userLoanBillMapper.updateUserLoanBill(userLoanBillVo);
        if (updateUserLoanBill <= 0){
            throw new RuntimeException();
        }
        int updateUserAmount = userInfoMapper.updateUserAmount(userId, userAmountAfter, userAmountBefore);
        if (updateUserAmount <= 0){
            throw new RuntimeException();
        }

        //插入流水记录
        UserBillDetails userBillDetails = new UserBillDetails();
        userBillDetails.setOrderCode(KeyUtil.getOrderCode());
        userBillDetails.setOrderAmount(payAmount);
        userBillDetails.setOrderType(1);
        //流水时间为后台审核的通过时间
        userBillDetails.setOrderTime(new Date());
        userBillDetails.setUserId(userId);
        userBillDetails.setOrderClass(5);
        userBillDetails.setAmountBefore(userAmountBefore);
        userBillDetails.setAmountAfter(userAmountAfter);
        int insertUserBillDetails = userBillDetailsMapper.insertUserBillDetails(userBillDetails);
        if (insertUserBillDetails <= 0){
            throw new RuntimeException();
        }
        return AjaxResult.success();
    }
}
