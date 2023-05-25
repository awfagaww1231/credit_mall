package com.ruoyi.system.service.impl;

import com.ruoyi.common.annotation.DataScope;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.common.AdminTokenService;
import com.ruoyi.system.domain.UserBillDetails;
import com.ruoyi.system.domain.UserInfo;
import com.ruoyi.system.domain.UserRechargeOrder;
import com.ruoyi.system.mapper.UserInfoMapper;
import com.ruoyi.system.mapper.UserRechargeOrderMapper;
import com.ruoyi.system.service.IUserBillDetailsService;
import com.ruoyi.system.service.IUserRechargeOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

/**
 * 用户充值订单Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-11-08
 */
@Service
public class UserRechargeOrderServiceImpl implements IUserRechargeOrderService 
{
    @Resource
    private UserRechargeOrderMapper userRechargeOrderMapper;

    @Resource
    private UserInfoMapper userInfoMapper;

    @Autowired
    private IUserBillDetailsService userBillDetailsService;

    @Autowired
    private AdminTokenService tokenService;
    /**
     * 查询用户充值订单
     * 
     * @param id 用户充值订单主键
     * @return 用户充值订单
     */
    @Override
    public UserRechargeOrder selectUserRechargeOrderById(Long id)
    {
        return userRechargeOrderMapper.selectUserRechargeOrderById(id);
    }

    /**
     * 查询用户充值订单列表
     * 
     * @param userRechargeOrder 用户充值订单
     * @return 用户充值订单
     */
    @Override
    @DataScope(userAlias = "su")
    public List<UserRechargeOrder> selectUserRechargeOrderList(UserRechargeOrder userRechargeOrder)
    {
        return userRechargeOrderMapper.selectUserRechargeOrderList(userRechargeOrder);
    }

    /**
     * 新增用户充值订单
     * 
     * @param userRechargeOrder 用户充值订单
     * @return 结果
     */
    @Override
    public int insertUserRechargeOrder(UserRechargeOrder userRechargeOrder)
    {
        userRechargeOrder.setCreateTime(DateUtils.getNowDate());
        return userRechargeOrderMapper.insertUserRechargeOrder(userRechargeOrder);
    }

    /**
     * 修改用户充值订单
     * 
     * @param userRechargeOrder 用户充值订单
     * @return 结果
     */
    @Override
    public int updateUserRechargeOrder(UserRechargeOrder userRechargeOrder)
    {
        return userRechargeOrderMapper.updateUserRechargeOrder(userRechargeOrder);
    }

    /**
     * 批量删除用户充值订单
     * 
     * @param ids 需要删除的用户充值订单主键
     * @return 结果
     */
    @Override
    public int deleteUserRechargeOrderByIds(Long[] ids)
    {
        return userRechargeOrderMapper.deleteUserRechargeOrderByIds(ids);
    }

    /**
     * 删除用户充值订单信息
     * 
     * @param id 用户充值订单主键
     * @return 结果
     */
    @Override
    public int deleteUserRechargeOrderById(Long id)
    {
        return userRechargeOrderMapper.deleteUserRechargeOrderById(id);
    }

    /**
     * 通过审核
     */
    @Override
    @Transactional
    public AjaxResult agree(List<UserRechargeOrder> userRechargeOrders){
        for (int i = 0; i < userRechargeOrders.size(); i++) {
            //订单信息
            UserRechargeOrder userRechargeOrder = userRechargeOrderMapper.selectUserRechargeOrderById(userRechargeOrders.get(i).getId());
            if (StringUtils.isNull(userRechargeOrder)){
                throw new RuntimeException("获取订单号为"+userRechargeOrders.get(i).getOrderCode()+"的订单信息异常，请刷新后尝试");
            }
            //订单编号
            String orderCode = userRechargeOrder.getOrderCode();
            if (!orderCode.equals(userRechargeOrders.get(i).getOrderCode())){
                throw new RuntimeException("订单号为"+orderCode+"的订单获取订单信息异常");
            }
            if (userRechargeOrder.getOrderStatus() != 1){
                if (userRechargeOrder.getOrderStatus() == 0){
                    throw new RuntimeException("订单号为"+orderCode+"的订单还未支付，无法进行审核操作");
                }
                if (userRechargeOrder.getOrderStatus() == 2 || userRechargeOrder.getOrderStatus() == 3){
                    throw new RuntimeException("订单号为"+orderCode+"的订单已经审核过了");
                }else{
                    throw new RuntimeException("订单号为"+orderCode+"的订单获取订单信息异常");
                }
            }


            //如果订单正常，可审核
            //用户id
            Long userId = userRechargeOrder.getUserId();
            //用户信息
            UserInfo userInfo = userInfoMapper.selectUserInfoById(userId);
            if (StringUtils.isNull(userInfo)){
                throw new RuntimeException("订单号为"+orderCode+"的订单充值用户不存在");
            }
            //充值金额
            BigDecimal rechargeOrderAmount = userRechargeOrder.getAmount();
            //用户余额变更前
            BigDecimal amountBefore = userInfo.getAmount();
            //用户余额变更后
            BigDecimal amountAfter = amountBefore.add(rechargeOrderAmount);
            int updateUserAmount = userInfoMapper.updateUserAmount(userId, amountAfter,amountBefore);
            if (updateUserAmount <= 0){
                throw new RuntimeException("订单号为"+orderCode+"的订单更新用户余额异常");
            }

            //插入流水记录
            UserBillDetails userBillDetails = new UserBillDetails();
            userBillDetails.setOrderCode(userRechargeOrder.getOrderCode());
            userBillDetails.setOrderAmount(rechargeOrderAmount);
            userBillDetails.setOrderType(0);
            //流水时间为后台审核的通过时间
            userBillDetails.setOrderTime(userRechargeOrder.getApproveTime());
            userBillDetails.setUserId(userId);
            userBillDetails.setOrderClass(0);
            userBillDetails.setAmountBefore(amountBefore);
            userBillDetails.setAmountAfter(amountAfter);
            int insertUserBillDetails = userBillDetailsService.insertUserBillDetails(userBillDetails);
            if (insertUserBillDetails <= 0){
                throw new RuntimeException("订单号为"+orderCode+"的订单插入充值订单流水记录异常");
            }


            //填入审核人信息,充值订单状态变更为通过
            UserRechargeOrder userRechargeOrderVo = new UserRechargeOrder();
            userRechargeOrderVo.setId(userRechargeOrder.getId());
            userRechargeOrderVo.setApproveTime(DateUtils.getNowDate());
            userRechargeOrderVo.setApproveName(tokenService.getLoginUser().getUser().getUserName());
            userRechargeOrderVo.setOrderStatus(3);
            int updateUserRechargeOrder = userRechargeOrderMapper.updateUserRechargeOrder(userRechargeOrderVo);
            if (updateUserRechargeOrder <= 0){
                throw new RuntimeException("订单号为"+orderCode+"的订单更新充值订单信息信息异常");
            }
        }
        return AjaxResult.success();
    }

    /**
     * 驳回审核
     */
    @Override
    @Transactional
    public AjaxResult reject(List<UserRechargeOrder> userRechargeOrders) {
        for (int i = 0; i < userRechargeOrders.size(); i++) {
            //订单信息
            UserRechargeOrder userRechargeOrder = userRechargeOrderMapper.selectUserRechargeOrderById(userRechargeOrders.get(i).getId());
            if (StringUtils.isNull(userRechargeOrder)){
                throw new RuntimeException("获取订单号为"+userRechargeOrders.get(i).getOrderCode()+"的订单信息异常，请刷新后尝试");
            }
            //订单编号
            String orderCode = userRechargeOrder.getOrderCode();
            if (!orderCode.equals(userRechargeOrders.get(i).getOrderCode())){
                throw new RuntimeException("订单号为"+orderCode+"的订单获取订单信息异常");
            }
            if (userRechargeOrder.getOrderStatus() != 1){
                if (userRechargeOrder.getOrderStatus() == 0){
                    throw new RuntimeException("订单号为"+orderCode+"的订单还未支付，无法进行审核操作");
                }
                if (userRechargeOrder.getOrderStatus() == 2 || userRechargeOrder.getOrderStatus() == 3){
                    throw new RuntimeException("订单号为"+orderCode+"的订单已经审核过了");
                }else{
                    throw new RuntimeException("订单号为"+orderCode+"的订单获取订单信息异常");
                }
            }

            //如果订单正常，可审核
            //填入审核人信息,充值订单状态变更为通过
            UserRechargeOrder userRechargeOrderVo = new UserRechargeOrder();
            userRechargeOrderVo.setId(userRechargeOrder.getId());
            userRechargeOrderVo.setApproveTime(DateUtils.getNowDate());
            userRechargeOrderVo.setApproveName(tokenService.getLoginUser().getUser().getUserName());
            userRechargeOrderVo.setOrderStatus(2);
            int updateUserRechargeOrder = userRechargeOrderMapper.updateUserRechargeOrder(userRechargeOrderVo);
            if (updateUserRechargeOrder <= 0){
                throw new RuntimeException("订单号为"+orderCode+"的订单更新充值订单信息信息异常");
            }
        }
        return AjaxResult.success();
    }

}
