package com.ruoyi.web.controller.app;

import com.ruoyi.common.annotation.RepeatSubmit;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.customer.common.AppletTokenService;
import com.ruoyi.customer.domain.AppletLoginUser;
import com.ruoyi.system.domain.LoanApplyInfo;
import com.ruoyi.system.service.ICreditCardActivationRecordService;
import com.ruoyi.system.service.ICreditCardApplyRecordService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户信用额度Controller
 * 
 * @author ruoyi
 * @date 2023-03-20
 */
@RestController
@RequestMapping("/api/creditCard")
public class ApiCreditCardController extends BaseController
{
    @Autowired
    private AppletTokenService appletTokenService;

    @Autowired
    private ICreditCardActivationRecordService creditCardActivationRecordService;

    @Autowired
    private ICreditCardApplyRecordService creditCardApplyRecordService;


    //申请信用卡
    @RequestMapping({"/toApplyCreditCard"})
    @ApiOperation("去申请信用卡")
    @RepeatSubmit
    public AjaxResult toApplyCreditCard(@RequestBody LoanApplyInfo loanApplyInfo) {
        if (loanApplyInfo != null){
            if (StringUtils.isEmpty(loanApplyInfo.getFirstName())){
                return AjaxResult.error("请填写姓氏","hint_21");
            }
            if (StringUtils.isEmpty(loanApplyInfo.getLastName())){
                return AjaxResult.error("请填写名字","hint_22");
            }
            if (StringUtils.isEmpty(loanApplyInfo.getBirthDay())){
                return AjaxResult.error("请填写生日","hint_23");
            }
            if (StringUtils.isEmpty(loanApplyInfo.getAddress())){
                return AjaxResult.error("请填写住址","hint_24");
            }
            if (StringUtils.isEmpty(loanApplyInfo.getCity())){
                return AjaxResult.error("请填写城市","hint_25");
            }
            if (StringUtils.isEmpty(loanApplyInfo.getState())){
                return AjaxResult.error("请填写州","hint_26");
            }
            if (StringUtils.isEmpty(loanApplyInfo.getZipCode())){
                return AjaxResult.error("请填写邮政编码","hint_27");
            }
            if (StringUtils.isEmpty(loanApplyInfo.getPhoneNumber())){
                return AjaxResult.error("请填写电话号码","hint_28");
            }
            if (StringUtils.isNull(loanApplyInfo.getYearlyIncome())){
                return AjaxResult.error("请填写年收入","hint_29");
            }
            if (StringUtils.isNull(loanApplyInfo.getSocialSecurityNumber())){
                return AjaxResult.error("请填写社会安全号码","hint_30");
            }
        }else {
            return AjaxResult.error("请先填写资料","hint_31");
        }
        if (loanApplyInfo.getUserApplyAmount() == null){
            return AjaxResult.error("请填写申请额度","hint_32");
        }
        AppletLoginUser loginUser = appletTokenService.getLoginUser();
        if (StringUtils.isNull(loginUser)) {
            return AjaxResult.error(401, "登录信息失效");
        } else {
            appletTokenService.refreshToken(loginUser);
        }
        return creditCardApplyRecordService.toApplyCreditCard(loginUser.getApiUserInfo().getId(),loanApplyInfo);
    }


    //激活信用卡
    @RequestMapping({"/toActivationCreditCard"})
    @ApiOperation("激活信用卡")
    @RepeatSubmit
    public AjaxResult toActivationCreditCard(Long platformPaymentChannelId,String payImg) {
        AppletLoginUser loginUser = appletTokenService.getLoginUser();
        if (StringUtils.isNull(loginUser)) {
            return AjaxResult.error(401, "登录信息失效");
        } else {
            appletTokenService.refreshToken(loginUser);
        }
        if (platformPaymentChannelId == null){
            return AjaxResult.error("请选择激活通道","hint_33");
        }
        if (platformPaymentChannelId == null){
            return AjaxResult.error("请上传付款凭证","hint_34");
        }
        return creditCardActivationRecordService.toActivationCreditCard(loginUser.getApiUserInfo().getId(),platformPaymentChannelId,payImg);
    }

    //开通vip权限
    @RequestMapping({"/toActivationVip"})
    @ApiOperation("去开通vip权限")
    @RepeatSubmit
    public AjaxResult toActivationVip(Long platformPaymentChannelId,String payImg) {
        AppletLoginUser loginUser = appletTokenService.getLoginUser();
        if (StringUtils.isNull(loginUser)) {
            return AjaxResult.error(401, "登录信息失效");
        } else {
            appletTokenService.refreshToken(loginUser);
        }
        if (platformPaymentChannelId == null){
            return AjaxResult.error("请选择激活通道","hint_33");
        }
        if (platformPaymentChannelId == null){
            return AjaxResult.error("请上传付款凭证","hint_34");
        }
        return creditCardActivationRecordService.toActivationVip(loginUser.getApiUserInfo().getId(),platformPaymentChannelId,payImg);
    }

    //查看信用卡申请失败原因
    @RequestMapping({"/getApplyCreditCardRejectMsg"})
    @ApiOperation("查看信用卡申请失败原因")
    public AjaxResult getApplyCreditCardRejectMsg() {
        AppletLoginUser loginUser = appletTokenService.getLoginUser();
        if (StringUtils.isNull(loginUser)) {
            return AjaxResult.error(401, "登录信息失效");
        } else {
            appletTokenService.refreshToken(loginUser);
        }
        return creditCardApplyRecordService.getApplyCreditCardRejectMsg(loginUser.getApiUserInfo().getId());
    }

    //查看信用卡激活失败原因
    @RequestMapping({"/getActivationCreditCardRejectMsg"})
    @ApiOperation("查看信用卡激活失败原因")
    public AjaxResult getActivationCreditCardRejectMsg() {
        AppletLoginUser loginUser = appletTokenService.getLoginUser();
        if (StringUtils.isNull(loginUser)) {
            return AjaxResult.error(401, "登录信息失效");
        } else {
            appletTokenService.refreshToken(loginUser);
        }
        return creditCardActivationRecordService.getActivationCreditCardRejectMsg(loginUser.getApiUserInfo().getId());
    }

    /**
     * 获取激活支付所需金额
     */
    @GetMapping("/getCreditActivationPayAmount")
    public AjaxResult getCreditActivationPayAmount()
    {
        AppletLoginUser loginUser = appletTokenService.getLoginUser();
        if (StringUtils.isNull(loginUser)) {
            return AjaxResult.error(401, "登录信息失效");
        } else {
            appletTokenService.refreshToken(loginUser);
        }
        return AjaxResult.success(creditCardApplyRecordService.getCreditActivationPayAmount(loginUser.getApiUserInfo().getId()));
    }
}
