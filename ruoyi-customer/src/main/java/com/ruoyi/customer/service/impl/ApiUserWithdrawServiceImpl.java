package com.ruoyi.customer.service.impl;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.customer.domain.ApiUserBank;
import com.ruoyi.customer.domain.ApiUserBillDetails;
import com.ruoyi.customer.domain.ApiUserInfo;
import com.ruoyi.customer.domain.ApiUserWithdraw;
import com.ruoyi.customer.mapper.ApiAuthMapper;
import com.ruoyi.customer.mapper.ApiUserBankMapper;
import com.ruoyi.customer.mapper.ApiUserBillDetailsMapper;
import com.ruoyi.customer.mapper.ApiUserWithdrawMapper;
import com.ruoyi.customer.service.IApiUserWithdrawService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
public class ApiUserWithdrawServiceImpl implements IApiUserWithdrawService {

    @Resource
    private ApiUserWithdrawMapper apiUserWithdrawMapper;

    @Resource
    private ApiAuthMapper apiAuthMapper;

    @Resource
    private ApiUserBillDetailsMapper apiUserBillDetailsMapper;

    @Resource
    private ApiUserBankMapper apiUserBankMapper;

    //用户提现列表
    @Override
    public List<ApiUserWithdraw> userWithdrawList(ApiUserWithdraw apiUserWithdraw) {
        return apiUserWithdrawMapper.userWithdrawList(apiUserWithdraw);
    }

    //用户提现
    @Override
    @Transactional
    public AjaxResult addWithdrawOrder(ApiUserWithdraw apiUserWithdraw) throws Exception {
        //用户id
        Long userId = apiUserWithdraw.getUserId();

        ApiUserBank apiUserBank = new ApiUserBank();
        apiUserBank.setUserId(userId);
        List<ApiUserBank> apiUserBanks = apiUserBankMapper.selectUserBankList(apiUserBank);
        if (apiUserBanks.size() == 0){
            return AjaxResult.error("请先绑定银行卡","hint_103");
        }
        apiUserBank = apiUserBanks.get(0);
        ApiUserInfo apiUserInfo = apiAuthMapper.selectUserById(userId);
        //提现密码
        String  userPayPassword = apiUserInfo.getPayPassword();
        if (StringUtils.isEmpty(userPayPassword)){
            return AjaxResult.error("请先设置资金密码","hint_104");
        }
        if (!SecurityUtils.matchesPassword (apiUserWithdraw.getPayPassword(),userPayPassword)){
            return AjaxResult.error ("提现密码错误","hint_105");
        }

        //用户原金额
        BigDecimal userAmountBefore = apiUserInfo.getAmount();
        if (StringUtils.isNull(userAmountBefore)){
            userAmountBefore = BigDecimal.ZERO;
        }
        if(userAmountBefore.compareTo(apiUserWithdraw.getWithdrawAmount()) == -1){
            return  AjaxResult.error ("账户余额不够","hint_106");
        }
        //如果余额够
        //扣钱
        BigDecimal userAmountAfter = userAmountBefore.subtract(apiUserWithdraw.getWithdrawAmount());
        int updateUserAmount = apiAuthMapper.updateUserAmount(userId, userAmountAfter,userAmountBefore);
        if (updateUserAmount <= 0){
            throw new RuntimeException("操作失败");
        }

        //提现地址
        apiUserWithdraw.setWithdrawUrl(apiUserBank.getBankName()+ ":" +apiUserBank.getBankNo());
        //插入提现订单
        //订单号
        String orderId = String.valueOf(System.currentTimeMillis()) + String.valueOf(userId);
        apiUserWithdraw.setWithdrawOrder(orderId);
        apiUserWithdraw.setWithdrawTime(new Date());
        apiUserWithdraw.setUserId(userId);
        apiUserWithdraw.setStatus(0);
        int addWithdrawOrder = apiUserWithdrawMapper.addWithdrawOrder(apiUserWithdraw);
        if (addWithdrawOrder <= 0){
            throw new RuntimeException("操作失败");
        }

        //插入流水记录
        ApiUserBillDetails userBillDetails = new ApiUserBillDetails();
        userBillDetails.setOrderCode(orderId);
        userBillDetails.setOrderAmount(apiUserWithdraw.getWithdrawAmount());
        userBillDetails.setOrderType(1);
        //流水时间(提现时间)
        userBillDetails.setOrderTime(apiUserWithdraw.getWithdrawTime());
        userBillDetails.setUserId(userId);
        userBillDetails.setOrderClass(1);
        userBillDetails.setAmountBefore(userAmountBefore);
        userBillDetails.setAmountAfter(userAmountAfter);
        int insertUserBillDetails = apiUserBillDetailsMapper.insertUserBillDetails(userBillDetails);
        if (insertUserBillDetails <= 0){
            throw new RuntimeException("操作失败");
        }
        return  AjaxResult.success();
    }
}
