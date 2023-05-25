package com.ruoyi.web.controller.app;

import com.ruoyi.common.annotation.RepeatSubmit;
import com.ruoyi.common.constant.HttpStatus;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.model.LoginBody;
import com.ruoyi.common.core.redis.RedisCache;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.customer.common.AppletTokenService;
import com.ruoyi.customer.domain.ApiUserInfo;
import com.ruoyi.customer.domain.AppletLoginUser;
import com.ruoyi.customer.service.IApiAuthService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * APP用户登录Controller +
 *
 * @author ruoyi
 * @date 2022-10-27
 */
@RestController
@RequestMapping("/api/auth")
public class ApiAuthController extends BaseController {

    @Autowired
    private IApiAuthService apiAuthService;

    @Autowired
    private AppletTokenService appletTokenService;

    @Autowired
    private RedisCache redisCache;

    @PostMapping("/userLogin")
    @ApiOperation("用户登录接口")
    @RepeatSubmit
    public AjaxResult userLogin(@RequestBody LoginBody loginBody) {
        if (StringUtils.isNull(loginBody) || StringUtils.isEmpty(loginBody.getMobile()) || StringUtils.isEmpty(loginBody.getPassword())) {
            return AjaxResult.error("账号或者密码为空","hint_1");
        }
        return apiAuthService.userLogin(loginBody);
    }


    @PostMapping({"register"})
    @ApiOperation("注册接口")
    @RepeatSubmit
    public AjaxResult register(@RequestBody ApiUserInfo apiUserInfo) {
        if (StringUtils.isEmpty(apiUserInfo.getPassword())){
            return AjaxResult.error("请填写密码","hint_2");
        }
        if (StringUtils.isEmpty(apiUserInfo.getMobile())){
            return AjaxResult.error("请填写手机号","hint_3");
        }
        //判断手机号格式是否正确
        //最少六位数
        String mobile = apiUserInfo.getMobile();
        //验证手机号格式是否正确
        if (!StringUtils.isNumeric(mobile)){
            return AjaxResult.error("请填写有效的手机号码！","hint_4");
        }
        if (mobile.length() < 6) {
            return AjaxResult.error("至少输入六位手机号","hint_5");
        }
        if (StringUtils.isEmpty(apiUserInfo.getEmail())){
            return AjaxResult.error("请填写邮箱","hint_6");
        }
        if (!apiUserInfo.getEmail().contains("@")){
            return AjaxResult.error("请填写有效的邮箱","hint_7");
        }
        if (StringUtils.isEmpty(apiUserInfo.getUserName())){
            return AjaxResult.error("请填写用户名","hint_8");
        }
        if (StringUtils.isEmpty(apiUserInfo.getInviteCode())){
            return AjaxResult.error("请填写有效的邀请码","hint_75");
        }
        //注册用户
        return apiAuthService.register(apiUserInfo);
    }

    /**
     * 修改C端用户信息
     */
    @PostMapping("/updateUserInfo")
    @RepeatSubmit
    public AjaxResult updateUserInfo(@RequestBody ApiUserInfo apiUserInfo)
    {
        AppletLoginUser loginUser = appletTokenService.getLoginUser ();
        if(StringUtils.isNull(loginUser)){
            return AjaxResult.error(HttpStatus.UNAUTHORIZED,"登录信息失效");
        }else {
            appletTokenService.refreshToken(loginUser);
        }
        //确认是否本人操作
        if (!apiUserInfo.getId().equals(loginUser.getApiUserInfo().getId())){
            return AjaxResult.error("出错啦，请重新登录后尝试","hint_9");
        }
        //只取接口中的这几个需要的参数
        ApiUserInfo userInfo = new ApiUserInfo();
        userInfo.setId(apiUserInfo.getId());
        userInfo.setMobile(apiUserInfo.getMobile());
        userInfo.setNickName(apiUserInfo.getNickName());
        userInfo.setAvatar(apiUserInfo.getAvatar());
        return apiAuthService.updateUserInfo(userInfo);
    }

    @PostMapping({"userInfo"})
    @ApiOperation("获取用户信息")
    public AjaxResult userInfo() {
        AppletLoginUser loginUser = appletTokenService.getLoginUser ();
        if(StringUtils.isNull(loginUser)){
            return AjaxResult.error(HttpStatus.UNAUTHORIZED,"登录信息失效");
        }else {
            appletTokenService.refreshToken(loginUser);
        }
        ApiUserInfo apiUserInfo = apiAuthService.selectUserById (loginUser.getApiUserInfo ().getId ());
        if (StringUtils.isNull(apiUserInfo)){
            return AjaxResult.error("获取用户信息异常，请重新登陆后再次尝试","hint_10");
        }
        return AjaxResult.success(apiUserInfo);
    }

    //修改密码
    @PostMapping({"/changePassword"})
    @ApiOperation("修改密码")
    @RepeatSubmit
    public AjaxResult changePassword(@RequestBody ApiUserInfo apiUserInfo) {
        AppletLoginUser loginUser = appletTokenService.getLoginUser();
        if (StringUtils.isNull(loginUser)) {
            return AjaxResult.error(401, "登录信息失效");
        } else {
            appletTokenService.refreshToken(loginUser);
        }
        if (StringUtils.isEmpty(apiUserInfo.getPasswordCheck())) {
            return AjaxResult.error("请输入原密码","hint_11");
        }
        if (StringUtils.isEmpty(apiUserInfo.getPassword())) {
            return AjaxResult.error("请输入新密码","hint_12");
        }
        apiUserInfo.setId(loginUser.getApiUserInfo().getId());
        return apiAuthService.changePassword(apiUserInfo);
    }

    //修改资金密码
    @PostMapping({"/changePayPassword"})
    @ApiOperation("修改资金密码")
    @RepeatSubmit
    public AjaxResult changePayPassword(@RequestBody ApiUserInfo apiUserInfo) {
        AppletLoginUser loginUser = appletTokenService.getLoginUser();
        if (StringUtils.isNull(loginUser)) {
            return AjaxResult.error(401, "登录信息失效");
        } else {
            appletTokenService.refreshToken(loginUser);
        }
        if (StringUtils.isEmpty(apiUserInfo.getPayPassword())) {
            return AjaxResult.error( "请输入新密码","hint_13");
        }
        apiUserInfo.setId(loginUser.getApiUserInfo().getId());
        return apiAuthService.changePayPassword(apiUserInfo);
    }

    //查看是否设置资金密码
    @GetMapping({"/isSetPayPassword"})
    @ApiOperation("查看是否设置资金密码")
    public AjaxResult isSetPayPassword() {
        AppletLoginUser loginUser = appletTokenService.getLoginUser();
        if (StringUtils.isNull(loginUser)) {
            return AjaxResult.error(401, "登录信息失效");
        } else {
            appletTokenService.refreshToken(loginUser);
        }
        ApiUserInfo apiUserInfo = apiAuthService.selectUserById(loginUser.getApiUserInfo().getId());
        if (StringUtils.isNull(apiUserInfo)){
            return AjaxResult.error("获取用户信息异常，请重新登陆后再次尝试","hint_10");
        }
        //资金密码
        String payPassword = apiUserInfo.getPayPassword();
        if (StringUtils.isEmpty(payPassword)){
            //未设置
            return AjaxResult.success(1);
        }
        //已设置
        return AjaxResult.success(0);
    }



    //去实名认证
    @PostMapping({"/toAuth"})
    @ApiOperation("去实名认证")
    @RepeatSubmit
    public AjaxResult toAuth(@RequestBody ApiUserInfo apiUserInfo) {
        AppletLoginUser loginUser = appletTokenService.getLoginUser();
        if (StringUtils.isNull(loginUser)) {
            return AjaxResult.error(401, "登录信息失效");
        } else {
            appletTokenService.refreshToken(loginUser);
        }
        if (StringUtils.isEmpty(apiUserInfo.getIdNumber())){
            return AjaxResult.error("请填写身份证号码","hint_15");
        }
        if (StringUtils.isEmpty(apiUserInfo.getRealName())){
            return AjaxResult.error("请填写身份证姓名","hint_16");
        }
        if (StringUtils.isEmpty(apiUserInfo.getIdCardImg1())){
            return AjaxResult.error("请上传身份正面照片","hint_17");
        }
        if (StringUtils.isEmpty(apiUserInfo.getIdCardImg2())){
            return AjaxResult.error("请上传身份背面照片","hint_18");
        }
        if (StringUtils.isEmpty(apiUserInfo.getIdCardImg3())){
            return AjaxResult.error("请上传手持身份证照片","hint_19");
        }
//        if (StringUtils.isNull(apiUserInfo.getApiUserBank())){
//            return AjaxResult.error("请填写银行卡信息");
//        }
        apiUserInfo.setId(loginUser.getApiUserInfo().getId());
        return apiAuthService.toAuth(apiUserInfo,apiUserInfo.getApiUserBank());
    }

    @GetMapping({"/viewInvitedRecords"})
    @ApiOperation("查看推广记录")
    public AjaxResult viewInvitedRecords() {
        AppletLoginUser loginUser = appletTokenService.getLoginUser();
        if (StringUtils.isNull(loginUser)) {
            return AjaxResult.error(401, "登录信息失效");
        } else {
            appletTokenService.refreshToken(loginUser);
        }
        return apiAuthService.viewInvitedRecords(loginUser.getApiUserInfo().getId());
    }
}
