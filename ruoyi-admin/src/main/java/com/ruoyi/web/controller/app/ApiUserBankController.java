package com.ruoyi.web.controller.app;

import com.ruoyi.common.annotation.RepeatSubmit;
import com.ruoyi.common.constant.HttpStatus;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.customer.common.AppletTokenService;
import com.ruoyi.customer.domain.ApiUserBank;
import com.ruoyi.customer.domain.AppletLoginUser;
import com.ruoyi.customer.service.IApiUserBankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 用户银行Controller
 * 
 * @author ruoyi
 * @date 2023-02-16
 */
@RestController
@RequestMapping("/api/userBank")
public class ApiUserBankController extends BaseController
{
    @Autowired
    private IApiUserBankService apiUserBankService;

    @Autowired
    private AppletTokenService appletTokenService;


    /**
     * 查询用户银行列表
     */
    @GetMapping("/list")
    public AjaxResult list(ApiUserBank userBank)
    {
        AppletLoginUser loginUser = appletTokenService.getLoginUser ();
        if(StringUtils.isNull(loginUser)){
            return AjaxResult.error(HttpStatus.UNAUTHORIZED,"登录信息失效");
        }else {
            appletTokenService.refreshToken(loginUser);
        }
        startPage();
        userBank.setUserId(loginUser.getApiUserInfo().getId());
        List<ApiUserBank> list = apiUserBankService.selectUserBankList(userBank);
        return AjaxResult.success(getDataTable(list));
    }


    /**
     * 获取用户银行详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        AppletLoginUser loginUser = appletTokenService.getLoginUser ();
        if(StringUtils.isNull(loginUser)){
            return AjaxResult.error(HttpStatus.UNAUTHORIZED,"登录信息失效");
        }else {
            appletTokenService.refreshToken(loginUser);
        }
        return AjaxResult.success(apiUserBankService.selectUserBankById(id));
    }

    /**
     * 新增用户银行
     */
    @PostMapping("/add")
    @RepeatSubmit
    public AjaxResult add(@RequestBody ApiUserBank userBank)
    {
        AppletLoginUser loginUser = appletTokenService.getLoginUser ();
        if(StringUtils.isNull(loginUser)){
            return AjaxResult.error(HttpStatus.UNAUTHORIZED,"登录信息失效");
        }else {
            appletTokenService.refreshToken(loginUser);
        }
        ApiUserBank search = new ApiUserBank();
        search.setUserId(loginUser.getApiUserInfo().getId());
        List<ApiUserBank> list = apiUserBankService.selectUserBankList(search);
        if (list.size() > 0){
            return AjaxResult.error("此账号已绑定银行卡","hint_41");
        }
        if (StringUtils.isEmpty(userBank.getBankName())){
            return AjaxResult.error("请输入银行卡名称","hint_42");
        }
        if (StringUtils.isEmpty(userBank.getBankImg())){
            return AjaxResult.error("请上传银行卡图片","hint_43");
        }
        if (StringUtils.isEmpty(userBank.getBankNo())){
            return AjaxResult.error("请输入银行卡号码","hint_44");
        }
        if (StringUtils.isEmpty(userBank.getBankAddress())){
            return AjaxResult.error("请输入银行卡开户地址","hint_45");
        }
        if (StringUtils.isEmpty(userBank.getAccountHolder())){
            return AjaxResult.error("请输入持有人","hint_46");
        }
        if (StringUtils.isEmpty(userBank.getRoutingNumber())){
            return AjaxResult.error("请输入路由编号","hint_47");
        }
        userBank.setUserId(loginUser.getApiUserInfo().getId());
        return toAjax(apiUserBankService.insertUserBank(userBank));
    }

    /**
     * 修改用户银行
     */
    @PostMapping("/update")
    public AjaxResult edit(@RequestBody ApiUserBank userBank)
    {
        AppletLoginUser loginUser = appletTokenService.getLoginUser ();
        if(StringUtils.isNull(loginUser)){
            return AjaxResult.error(HttpStatus.UNAUTHORIZED,"登录信息失效");
        }else {
            appletTokenService.refreshToken(loginUser);
        }
        ApiUserBank search = new ApiUserBank();
        search.setUserId(loginUser.getApiUserInfo().getId());
        List<ApiUserBank> list = apiUserBankService.selectUserBankList(search);
        if (list.size() <= 0){
            return AjaxResult.error("暂无查询到有绑定的银行卡","hint_48");
        }else{
            List<Long> collect = list.stream().map(ApiUserBank::getId).filter(a -> a.equals(userBank.getId())).collect(Collectors.toList());
            if (collect.size() != 1){
                return AjaxResult.error("系统繁忙，请刷新后重新尝试","hint_49");
            }
        }
        if (StringUtils.isEmpty(userBank.getBankName())){
            return AjaxResult.error("请输入银行卡名称","hint_42");
        }
        if (StringUtils.isEmpty(userBank.getBankImg())){
            return AjaxResult.error("请上传银行卡图片","hint_43");
        }
        if (StringUtils.isEmpty(userBank.getBankNo())){
            return AjaxResult.error("请输入银行卡号码","hint_44");
        }
        if (StringUtils.isEmpty(userBank.getBankAddress())){
            return AjaxResult.error("请输入银行卡开户地址","hint_45");
        }
        if (StringUtils.isEmpty(userBank.getAccountHolder())){
            return AjaxResult.error("请输入持有人","hint_46");
        }
        if (StringUtils.isEmpty(userBank.getRoutingNumber())){
            return AjaxResult.error("请输入路由编号","hint_47");
        }
        return toAjax(apiUserBankService.updateUserBank(userBank));
    }

    /**
     * 删除用户银行
     */
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        AppletLoginUser loginUser = appletTokenService.getLoginUser ();
        if(StringUtils.isNull(loginUser)){
            return AjaxResult.error(HttpStatus.UNAUTHORIZED,"登录信息失效");
        }else {
            appletTokenService.refreshToken(loginUser);
        }
        return toAjax(apiUserBankService.deleteUserBankByIds(ids));
    }
}
