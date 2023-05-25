package com.ruoyi.web.controller.app;

import com.ruoyi.common.annotation.RepeatSubmit;
import com.ruoyi.common.constant.HttpStatus;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.customer.common.AppletTokenService;
import com.ruoyi.customer.domain.ApiUserWithdraw;
import com.ruoyi.customer.domain.AppletLoginUser;
import com.ruoyi.customer.service.IApiUserWithdrawService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 提现记录Controller
 *
 * @author ruoyi
 * @date 2023-02-17
 */
@RestController
@RequestMapping("/api/userWithdraw")
public class ApiUserWithdrawController extends BaseController {

    @Autowired
    private IApiUserWithdrawService apiUserWithdrawService;

    @Autowired
    private AppletTokenService appletTokenService;

    //查询列表
    @ApiOperation(value = "订单列表")
    @GetMapping("/list")
    public AjaxResult list(ApiUserWithdraw apiUserWithdraw){
        AppletLoginUser loginUser = appletTokenService.getLoginUser ();
        if(StringUtils.isNull(loginUser)){
            return AjaxResult.error(HttpStatus.UNAUTHORIZED,"登录信息失效");
        }else {
            appletTokenService.refreshToken(loginUser);
        }
        apiUserWithdraw.setUserId(loginUser.getApiUserInfo().getId());
        startPage();
        List<ApiUserWithdraw> list = apiUserWithdrawService.userWithdrawList(apiUserWithdraw);
        return  AjaxResult.success(list);
    }

    @ApiOperation(value = "用户提交提现订单")
    @PostMapping("/addWithdrawOrder")
    @RepeatSubmit
    public AjaxResult addWithdrawOrder(@RequestBody ApiUserWithdraw apiUserWithdraw) throws Exception {
        AppletLoginUser loginUser = appletTokenService.getLoginUser ();
        if(StringUtils.isNull(loginUser)){
            return AjaxResult.error(HttpStatus.UNAUTHORIZED,"登录信息失效");
        }else {
            appletTokenService.refreshToken(loginUser);
        }
        if (StringUtils.isNull(apiUserWithdraw.getWithdrawAmount())){
            return AjaxResult.error("请输入提现金额","hint_67");
        }
        if (StringUtils.isEmpty(apiUserWithdraw.getPayPassword())){
            return AjaxResult.error("请输入提现密码","hint_68");
        }
        apiUserWithdraw.setUserId(loginUser.getApiUserInfo().getId());
        return apiUserWithdrawService.addWithdrawOrder(apiUserWithdraw);
    }

}
