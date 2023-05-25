package com.ruoyi.web.controller.app;

import com.ruoyi.common.annotation.RepeatSubmit;
import com.ruoyi.common.constant.HttpStatus;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.customer.common.AppletTokenService;
import com.ruoyi.customer.domain.AppletLoginUser;
import com.ruoyi.system.domain.UserLoanBill;
import com.ruoyi.system.mapper.UserLoanBillMapper;
import com.ruoyi.system.service.IUserLoanBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

/**
 * 用户贷款还款账单Controller
 *
 * @author ruoyi
 * @date 2023-04-28
 */
@RestController
@RequestMapping("/api/userLoanBill")
public class ApiUserLoanBillController extends BaseController
{
    @Autowired
    private IUserLoanBillService userLoanBillService;

    @Autowired
    private UserLoanBillMapper userLoanBillMapper;

    @Autowired
    private AppletTokenService appletTokenService;

    /**
     * 查询用户贷款还款账单列表
     */
    @GetMapping("/userLoanBillList")
    public AjaxResult list(UserLoanBill userLoanBill)
    {
        if (userLoanBill.getUserLoanRecordId() == null){
            return AjaxResult.error("请选择需要查看账单的贷款记录");
        }
        AppletLoginUser loginUser = appletTokenService.getLoginUser ();
        if(StringUtils.isNull(loginUser)){
            return AjaxResult.error(HttpStatus.UNAUTHORIZED,"登录信息失效");
        }else {
            appletTokenService.refreshToken(loginUser);
        }
        startPage();
        userLoanBill.setUserId(loginUser.getApiUserInfo().getId());
        List<UserLoanBill> list = userLoanBillMapper.selectUserLoanBillList(userLoanBill);
        return AjaxResult.success(getDataTable(list));
    }

    /**
     * 去还款贷款每月账单
     */
    @GetMapping("/toPayLoanBill")
    @RepeatSubmit
    public AjaxResult toPayLoanBill(Long userLoanBillId, BigDecimal payAmount)
    {
        if (userLoanBillId == null){
            return AjaxResult.error("请选择还款账单");
        }
        if (payAmount == null){
            return AjaxResult.error("请输入还款金额");
        }
        if (payAmount.compareTo(BigDecimal.ZERO) <= 0){
            return AjaxResult.error("还款金额必须不少于0");
        }
        AppletLoginUser loginUser = appletTokenService.getLoginUser ();
        if(StringUtils.isNull(loginUser)){
            return AjaxResult.error(HttpStatus.UNAUTHORIZED,"登录信息失效");
        }else {
            appletTokenService.refreshToken(loginUser);
        }
        return userLoanBillService.toPayLoanBill(loginUser.getApiUserInfo().getId(),userLoanBillId,payAmount);
    }


}
