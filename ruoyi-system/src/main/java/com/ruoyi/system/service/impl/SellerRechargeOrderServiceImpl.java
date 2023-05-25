package com.ruoyi.system.service.impl;

import com.ruoyi.common.annotation.XXDataScope;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.common.AdminTokenService;
import com.ruoyi.system.domain.*;
import com.ruoyi.system.mapper.SellerRechargeOrderMapper;
import com.ruoyi.system.mapper.SysUserMapper;
import com.ruoyi.system.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 用户充值订单Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-11-09
 */
@Service
public class SellerRechargeOrderServiceImpl implements ISellerRechargeOrderService 
{
    @Autowired
    private SellerRechargeOrderMapper sellerRechargeOrderMapper;

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private ISellerBillDetailsService sellerBillDetailsService;

    @Autowired
    private ISellerVipConfigService sellerVipConfigService;

    @Autowired
    private ISellerVipLevelService sellerVipLevelService;

    @Autowired
    private ICommissionConfigService commissionConfigService;

    @Autowired
    private IRechargeCommissionService rechargeCommissionService;

    @Autowired
    private AdminTokenService tokenService;

    /**
     * 查询用户充值订单
     * 
     * @param id 用户充值订单主键
     * @return 用户充值订单
     */
    @Override
    public SellerRechargeOrder selectSellerRechargeOrderById(Long id)
    {
        return sellerRechargeOrderMapper.selectSellerRechargeOrderById(id);
    }

    /**
     * 查询用户充值订单列表
     * 
     * @param sellerRechargeOrder 用户充值订单
     * @return 用户充值订单
     */
    @Override
    @XXDataScope(deptAlias = "d", userAlias = "u" ,sysUserIdAlias = "b.user_id")
    public List<SellerRechargeOrder> selectSellerRechargeOrderList(SellerRechargeOrder sellerRechargeOrder)
    {
        return sellerRechargeOrderMapper.selectSellerRechargeOrderList(sellerRechargeOrder);
    }

    /**
     * 新增用户充值订单
     * 
     * @param sellerRechargeOrder 用户充值订单
     * @return 结果
     */
    @Override
    public int insertSellerRechargeOrder(SellerRechargeOrder sellerRechargeOrder)
    {
        sellerRechargeOrder.setCreateTime(DateUtils.getNowDate());
        sellerRechargeOrder.setRechargeMethod(0);
        return sellerRechargeOrderMapper.insertSellerRechargeOrder(sellerRechargeOrder);
    }

    /**
     * 修改用户充值订单
     * 
     * @param sellerRechargeOrder 用户充值订单
     * @return 结果
     */
    @Override
    public int updateSellerRechargeOrder(SellerRechargeOrder sellerRechargeOrder)
    {
        return sellerRechargeOrderMapper.updateSellerRechargeOrder(sellerRechargeOrder);
    }

    /**
     * 批量删除用户充值订单
     * 
     * @param ids 需要删除的用户充值订单主键
     * @return 结果
     */
    @Override
    public int deleteSellerRechargeOrderByIds(Long[] ids)
    {
        return sellerRechargeOrderMapper.deleteSellerRechargeOrderByIds(ids);
    }

    /**
     * 删除用户充值订单信息
     * 
     * @param id 用户充值订单主键
     * @return 结果
     */
    @Override
    public int deleteSellerRechargeOrderById(Long id)
    {
        return sellerRechargeOrderMapper.deleteSellerRechargeOrderById(id);
    }


    //商户审核通过操作
    @Override
    @Transactional
    public AjaxResult agree(List<SellerRechargeOrder> sellerRechargeOrders) {
        for (int i = 0; i < sellerRechargeOrders.size(); i++) {
            SellerRechargeOrder sellerRechargeOrder = sellerRechargeOrderMapper.selectSellerRechargeOrderById(sellerRechargeOrders.get(i).getId());
            if (StringUtils.isNull(sellerRechargeOrder)){
                return AjaxResult.error("订单信息异常，请刷新后尝试");
            }
            if (sellerRechargeOrder.getOrderStatus() != 1){
                if (sellerRechargeOrder.getOrderStatus() == 0){
                    return AjaxResult.error("此订单还未支付，无法进行审核操作");
                }
                if (sellerRechargeOrder.getOrderStatus() == 2 || sellerRechargeOrder.getOrderStatus() == 3){
                    return AjaxResult.error("此订单已经审核过了！");
                }else{
                    return AjaxResult.error();
                }
            }
            //如果订单正常，可审核
            //填入审核人信息
            sellerRechargeOrders.get(i).setApproveTime(DateUtils.getNowDate());
            sellerRechargeOrders.get(i).setApproveName(tokenService.getLoginUser().getUser().getUserName());

            Long userId = sellerRechargeOrder.getUserId();
            SysUser sysUser = sysUserMapper.selectUserById(userId);
            if (StringUtils.isNull(sysUser)){
                return AjaxResult.error("此订单用户不存在");
            }
            //充值金额
            BigDecimal rechargeOrderAmount = sellerRechargeOrder.getAmount();
            if (rechargeOrderAmount == null){
                rechargeOrderAmount = BigDecimal.ZERO;
            }
            //用户余额
            BigDecimal userAmount = sysUser.getAmount();
            if (userAmount == null){
                userAmount = BigDecimal.ZERO;
            }
            sysUser.setAmount(userAmount.add(rechargeOrderAmount));
            int updateUser = sysUserMapper.updateUser(sysUser);
            if (updateUser <= 0){
                throw new RuntimeException();
            }

            //插入流水记录
            SellerBillDetails sellerBillDetails = new SellerBillDetails();
            sellerBillDetails.setOrderCode(sellerRechargeOrder.getOrderCode());
            sellerBillDetails.setOrderAmount(sellerRechargeOrder.getAmount());
            sellerBillDetails.setOrderType(0);
            //流水时间为订单提交至平台的时间
            sellerBillDetails.setOrderTime(sellerRechargeOrder.getApproveTime());
            sellerBillDetails.setUserId(userId);
            sellerBillDetails.setOrderClass(0);
            sellerBillDetails.setAmountBefore(userAmount);
            sellerBillDetails.setAmountAfter(userAmount.add(sellerRechargeOrder.getAmount()));
            int insertSellerBillDetails = sellerBillDetailsService.insertSellerBillDetails(sellerBillDetails);
            if (insertSellerBillDetails <= 0){
                throw new RuntimeException();
            }

            //充值时上级返佣
            this.rechargeCommission(sellerRechargeOrder);

            //变更会员等级
            Integer vipLevel = 0;
            //计算商户的充值总金额
            BigDecimal rechargeAmount = sellerRechargeOrderMapper.selectRechargeAmountBySellerId(userId);
            if (rechargeAmount == null){
                rechargeAmount = BigDecimal.ZERO;
            }
            //计算商户的发展下线人数
            Integer developedNumber = 0;

            //查询vip等级列表
            List<SellerVipConfig> sellerVipConfigs = sellerVipConfigService.selectSellerVipConfigList(null);
            for (int k = sellerVipConfigs.size() - 1; k >= 0; k--) {
                SellerVipConfig sellerVipConfig = sellerVipConfigs.get(k);
                //此等级所需的充值金额
                BigDecimal needRechargeAmount = sellerVipConfig.getRechargeAmout();
                //此等级所需的发展下线人数
                Integer needDevelopedNumber = sellerVipConfig.getDevelopedNumber();
                //如果符合条件
                if (rechargeAmount.compareTo(needRechargeAmount) != -1 & developedNumber >= needDevelopedNumber){
                    vipLevel = sellerVipConfig.getVipLevel();
                    break;
                }
            }
            //更新商户vip等级
            SellerVipLevel sellerVipLevel = sellerVipLevelService.selectSellerVipLevelBySellerId(userId);
            //如果还没有等级信息，则新增等级信息
            if (StringUtils.isNull(sellerVipLevel)){
                sellerVipLevel = new SellerVipLevel();
                sellerVipLevel.setSellerId(userId);
                sellerVipLevel.setVipLevel(vipLevel);
                int insertSellerVipLevel = sellerVipLevelService.insertSellerVipLevel(sellerVipLevel);
                if (insertSellerVipLevel <= 0){
                    throw new RuntimeException();
                }
            }else {
                sellerVipLevel.setVipLevel(vipLevel);
                int updateSellerVipLevel = sellerVipLevelService.updateSellerVipLevel(sellerVipLevel);
                if (updateSellerVipLevel <= 0){
                    throw new RuntimeException();
                }
            }

            //充值订单状态变更为通过
            sellerRechargeOrder.setOrderStatus(3);
            int updateSellerRechargeOrder = sellerRechargeOrderMapper.updateSellerRechargeOrder(sellerRechargeOrder);
            if (updateSellerRechargeOrder <= 0){
                throw new RuntimeException();
            }
        }
        return AjaxResult.success();
    }

    void rechargeCommission(SellerRechargeOrder sellerRechargeOrder){
        //充值的商户id
        Long userId = sellerRechargeOrder.getUserId();
        //充值的金额
        BigDecimal rechargeAmount = sellerRechargeOrder.getAmount();
        //此充值订单的订单编号
        String rechargeOrderCode = sellerRechargeOrder.getOrderCode();
        //订单通过审核的时间
        Date approveTime = sellerRechargeOrder.getApproveTime();

        //需返利的代理级别数
        int teamInfoLevelNum = 5;
        ArrayList<Long> list = new ArrayList<> ();

        Long id = userId;
        //上一级代理
        for (int i = 0; i < teamInfoLevelNum; i++) {
            if (id != null){
                id = sysUserMapper.queryReferrerId (id);
                if (id != null){
                    list.add (id);
                }
            }else{
                break;
            }
        }

        //共有多少个上级需要返佣
        int referrerNum = list.size ();

        //返佣等级
        Integer commissionLevel = 1;

        for (int i = 0; i < referrerNum; i++) {
            //逐渐获取上级id
            CommissionConfig commissionConfig = commissionConfigService.selectCommissionConfigByLevelAndType(commissionLevel, 1);
            //返佣比例
            BigDecimal commissionProfit = BigDecimal.ZERO;
            if (StringUtils.isNotNull (commissionConfig)){
                commissionProfit = commissionConfig.getCommissionProfit();
            }
            commissionLevel ++;
            //上级返利额度
            BigDecimal commissionAmount = rechargeAmount.multiply (commissionProfit).multiply (BigDecimal.valueOf (0.01));
            //如果返利额度超过0，则记录
            if (commissionAmount.compareTo (BigDecimal.ZERO) == 1){
                //上级id
                Long supId = list.get (i);
                //获取上级商户信息
                SysUser referrerUser = sysUserMapper.selectUserById(supId);
                //获取上级之前的余额
                BigDecimal totalAmountBefore = referrerUser.getAmount ();
                if (totalAmountBefore == null) {
                    totalAmountBefore = BigDecimal.ZERO;
                }
                //加上返佣后的余额
                BigDecimal totalAmountAfter = totalAmountBefore.add (commissionAmount);
                referrerUser.setAmount(totalAmountAfter);
                //更新返利后的总额
                sysUserMapper.updateUser (referrerUser);

                String billOrderId =  String.valueOf(System.currentTimeMillis()) + String.valueOf(supId);
                //插入流水记录(充值返佣收入账单)
                SellerBillDetails sellerBillDetails = new SellerBillDetails();
                sellerBillDetails.setOrderCode(billOrderId);
                sellerBillDetails.setOrderAmount(commissionAmount);
                sellerBillDetails.setOrderType(0);
                //流水时间为充值订单的审核时间
                sellerBillDetails.setOrderTime(approveTime);
                sellerBillDetails.setUserId(supId);
                sellerBillDetails.setOrderClass(5);
                sellerBillDetails.setAmountBefore(totalAmountBefore);
                sellerBillDetails.setAmountAfter(totalAmountAfter);
                sellerBillDetailsService.insertSellerBillDetails(sellerBillDetails);

                //返佣信息记录
                RechargeCommission rechargeCommission = new RechargeCommission();
                rechargeCommission.setSuperId (supId);
                rechargeCommission.setLowerId (userId);
                //返佣等级
                int commisonLevel = i + 1;
                rechargeCommission.setCommissionLevel (Long.valueOf (commisonLevel));
                rechargeCommission.setCommissionAmont (commissionAmount);
                rechargeCommission.setCommissionProfit (commissionProfit);
                rechargeCommission.setOrderCodeSource (rechargeOrderCode);
                rechargeCommission.setOrderCodeCommission (billOrderId);
                rechargeCommission.setCreateTime (approveTime);
                rechargeCommissionService.insertRechargeCommission(rechargeCommission);
            }
        }
    };


    @Override
    @Transactional
    public AjaxResult reject(List<SellerRechargeOrder> sellerRechargeOrders) {
        for (int i = 0; i < sellerRechargeOrders.size(); i++) {
            SellerRechargeOrder sellerRechargeOrder = sellerRechargeOrderMapper.selectSellerRechargeOrderById(sellerRechargeOrders.get(i).getId());
            if (StringUtils.isNull(sellerRechargeOrder)){
                return AjaxResult.error("订单信息异常，请刷新后尝试");
            }
            if (sellerRechargeOrder.getOrderStatus() != 1){
                if (sellerRechargeOrder.getOrderStatus() == 0){
                    return AjaxResult.error("此订单还未支付，无法进行审核操作");
                }
                if (sellerRechargeOrder.getOrderStatus() == 2 | sellerRechargeOrder.getOrderStatus() == 3){
                    return AjaxResult.error("此订单已经审核过了！");
                }else{
                    return AjaxResult.error();
                }
            }
            //如果订单正常，可审核
            //填入审核人信息
            sellerRechargeOrders.get(i).setApproveTime(DateUtils.getNowDate());
            sellerRechargeOrders.get(i).setApproveName(tokenService.getLoginUser().getUser().getUserName());

            //充值订单状态变更为驳回
            sellerRechargeOrder.setOrderStatus(2);
            int updateSellerRechargeOrder = sellerRechargeOrderMapper.updateSellerRechargeOrder(sellerRechargeOrder);
            if (updateSellerRechargeOrder <= 0){
                throw new RuntimeException();
            }
        }
        return AjaxResult.success();
    }
}
