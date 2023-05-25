package com.ruoyi.customer.service.impl;

import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.domain.model.LoginBody;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.x.keyUtil.KeyUtil;
import com.ruoyi.customer.common.AppletTokenService;
import com.ruoyi.customer.domain.ApiUserBank;
import com.ruoyi.customer.domain.ApiUserInfo;
import com.ruoyi.customer.domain.AppletLoginUser;
import com.ruoyi.customer.mapper.ApiAuthMapper;
import com.ruoyi.customer.mapper.ApiOtherValueMapper;
import com.ruoyi.customer.mapper.ApiSysUserMapper;
import com.ruoyi.customer.mapper.ApiUserBankMapper;
import com.ruoyi.customer.service.IApiAuthService;
import com.ruoyi.customer.service.IApiWebBackgroundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class ApiAuthServiceImpl implements IApiAuthService {

    @Resource
    private ApiAuthMapper apiAuthMapper;

    @Autowired
    private AppletTokenService appletTokenService;

    @Autowired
    private IApiWebBackgroundService webBackgroundService;

    @Resource
    private ApiUserBankMapper apiUserBankMapper;

    @Resource
    private ApiOtherValueMapper apiOtherValueMapper;

    @Resource
    private ApiSysUserMapper apiSysUserMapper;

    //用户登陆
    @Override
    public AjaxResult userLogin(LoginBody loginBody) {
        ApiUserInfo apiUserInfo = apiAuthMapper.selectUserByMobile (loginBody.getMobile ());
        //如果用户信息为空
        if (StringUtils.isNull(apiUserInfo)) {
            return AjaxResult.error("账号或者密码错误","hint_69");
        }
        if (apiUserInfo.getIsDel () == 1){
            return AjaxResult.error("此账号已注销，如需恢复请联系客服","hint_70");
        }
        //用户登录密码
        String password = apiUserInfo.getPassword ();
        if (!SecurityUtils.matchesPassword (loginBody.getPassword(),password)){
            return AjaxResult.error("账号或者密码错误","hint_71");
        }
        if (apiUserInfo.getStatus() == 1) {
            return AjaxResult.error("账号已被冻结","hint_72");
        }

        AjaxResult ajax = AjaxResult.success();
        //token
        // 生成令牌
        AppletLoginUser appletLoginUser = new AppletLoginUser();
        appletLoginUser.setApiUserInfo (apiUserInfo);
        String token = appletTokenService.createToken(appletLoginUser);
        ajax.put(Constants.TOKEN, token);
        ajax.put("user", apiUserInfo);
        return ajax;
    }

    //用户注册
    @Override
    @Transactional
    public AjaxResult register(ApiUserInfo apiUserInfo) {
        //判断手机号是否已经被注册
        ApiUserInfo apiUserInfoVo = apiAuthMapper.selectUserByMobile (apiUserInfo.getMobile());
        if (StringUtils.isNotNull(apiUserInfoVo)) {
            return AjaxResult.error( "此手机号已被注册","hint_73");
        }
        //判断邮箱是否已经被注册
        apiUserInfoVo = apiAuthMapper.selectUserByEmail (apiUserInfo.getEmail());
        if (StringUtils.isNotNull(apiUserInfoVo)) {
            return AjaxResult.error( "此邮箱已被注册","hint_74");
        }
        //上级的邀请码
        String supInviteCode = apiUserInfo.getInviteCode();
        //上级用户id
        Long supId = null;
        //如果有填写邀请码
        if (StringUtils.isNotEmpty(supInviteCode)){
            //上级用户信息
            SysUser supUser = apiSysUserMapper.selectUserByReferralCode(supInviteCode);
            if (StringUtils.isNull(supUser)){
                return AjaxResult.error("请输入有效的邀请码","hint_75");
            }
            supId = supUser.getUserId();
            /*
            ApiUserInfo supUser = apiAuthMapper.selectUserByInviteCode(supInviteCode);
            if (StringUtils.isNull(supUser)){
                return AjaxResult.error("请输入有效的邀请码","hint_75");
            }
            //如果该用户已经申请到信用额度，则为其增加信用额度
            if (supUser.getCreditCardStatus() != 0 && supUser.getCreditCardStatus() != 1){
                supId = supUser.getId();
                //邀请人增加信用额度
                ApiUserInfo supUserVo = new ApiUserInfo();
                //变更前的信用总额度
                BigDecimal creditCardTotalAmountBefore = supUser.getCreditCardTotalAmount();
                //邀请用户的奖励金额
                ApiOtherValue invitingUsersRewardsCreditAmountVo = apiOtherValueMapper.selectOtherValueByKey("invitingUsers_rewardsCreditAmount");
                if (StringUtils.isNotNull(invitingUsersRewardsCreditAmountVo)){
                    try {
                        //奖励金额
                        BigDecimal invitingUsersRewardsCreditAmount = new BigDecimal(invitingUsersRewardsCreditAmountVo.getOtherValue());
                        BigDecimal creditCardTotalAmountAfter = creditCardTotalAmountBefore.add(invitingUsersRewardsCreditAmount);
                        supUserVo.setId(supUser.getId());
                        supUserVo.setCreditCardTotalAmount(creditCardTotalAmountAfter);
                        int updateUserInfo = apiAuthMapper.updateUserInfo(supUserVo);
                        if (updateUserInfo <= 0){
                            throw new RuntimeException("操作失败");
                        }
                    } catch (Exception e) {

                    }
                }
            }*/
        }
//        else {
//            return AjaxResult.error("请填写邀请码");
//        }

        //新增的用户信息
        ApiUserInfo userInfo = new ApiUserInfo();
        //手机号
        userInfo.setMobile(apiUserInfo.getMobile());
        //邮箱
        userInfo.setEmail(apiUserInfo.getEmail());
        //用户名
        userInfo.setUserName(apiUserInfo.getUserName());
        //昵称
        userInfo.setNickName (apiUserInfo.getUserName());
        //密码加密
        userInfo.setPassword (SecurityUtils.encryptPassword (apiUserInfo.getPassword ()));
        //上级id
        userInfo.setSupId(supId);
        //注册时间
        userInfo.setRegisterTime (DateUtils.getNowDate());
        //创建时间
        userInfo.setCreateTime (DateUtils.getNowDate());
        //生成自己的邀请码
        while (true){
            //邀请码
            String inviteCode = KeyUtil.getInviteCode(6);
            //根据邀请码查看用户信息
            ApiUserInfo userInfoVo = apiAuthMapper.selectUserByInviteCode(inviteCode);
            //如果有人占用，重新获取新的邀请码
            if (StringUtils.isNull(userInfoVo)){
                userInfo.setInviteCode(inviteCode);
                break;
            }
        }
        //新增用户
        int insertUser = apiAuthMapper.insertUser(userInfo);
        if (insertUser <= 0){
            throw new RuntimeException("操作失败");
        }
        return AjaxResult.success();
    }


    @Override
    public ApiUserInfo selectUserById(Long userId) {
        return apiAuthMapper.selectUserById (userId);
    }

    @Override
    public AjaxResult updateUserInfo(ApiUserInfo apiUserInfo) {
        int updateUserInfo = apiAuthMapper.updateUserInfo(apiUserInfo);
        if (updateUserInfo <= 0){
            return AjaxResult.error();
        }
        return AjaxResult.success();
    }

    //更新密码
    @Override
    public AjaxResult changePassword(ApiUserInfo apiUserInfo) {
        //用户id
        Long userId = apiUserInfo.getId();
        //用户信息
        ApiUserInfo apiUserInfoVo = apiAuthMapper.selectUserById(userId);
        if (StringUtils.isNull(apiUserInfoVo)){
            return AjaxResult.error("获取用户信息异常，请重新登陆后再次尝试","hint_10");
        }
        //验证原密码
        if (!SecurityUtils.matchesPassword (apiUserInfo.getPasswordCheck(),apiUserInfoVo.getPassword())){
            return AjaxResult.error("原密码错误","hint_77");
        }
        //更新密码
        ApiUserInfo userInfo = new ApiUserInfo();
        userInfo.setId(userId);
        userInfo.setPassword(SecurityUtils.encryptPassword(apiUserInfo.getPassword()));
        int updateUserInfo = apiAuthMapper.updateUserInfo(userInfo);
        if (updateUserInfo <= 0){
            return AjaxResult.error();
        }
        return AjaxResult.success();
    }

    //更新支付密码
    @Override
    public AjaxResult changePayPassword(ApiUserInfo apiUserInfo) {
        //用户id
        Long userId = apiUserInfo.getId();
        //用户信息
        ApiUserInfo apiUserInfoVo = apiAuthMapper.selectUserById(userId);
        if (StringUtils.isNull(apiUserInfoVo)){
            return AjaxResult.error("获取用户信息异常，请重新登陆后再次尝试","hint_10");
        }
        //验证原密码
        String payPasswordBefore = apiUserInfoVo.getPayPassword();
        if (StringUtils.isNotEmpty(payPasswordBefore)){
            if (StringUtils.isEmpty(apiUserInfo.getPasswordCheck())) {
                return AjaxResult.error( "请输入旧密码","hint_79");
            }
            if (!SecurityUtils.matchesPassword (apiUserInfo.getPasswordCheck(),payPasswordBefore)){
                return AjaxResult.error( "旧密码错误","hint_80");
            }
        }

        //更新支付密码
        ApiUserInfo userInfo = new ApiUserInfo();
        userInfo.setId(userId);
        userInfo.setPayPassword(SecurityUtils.encryptPassword(apiUserInfo.getPayPassword()));
        int updateUserInfo = apiAuthMapper.updateUserInfo(userInfo);
        if (updateUserInfo <= 0){
            return AjaxResult.error();
        }
        return AjaxResult.success();
    }

    //实名认证
    @Override
    @Transactional
    public AjaxResult toAuth(ApiUserInfo apiUserInfo,ApiUserBank apiUserBank) {
        //用户id
        Long userId = apiUserInfo.getId();
        //实名认证的同时需要绑定银行卡
        ApiUserBank apiUserBankVo = new ApiUserBank();
        apiUserBankVo.setUserId(userId);
        List<ApiUserBank> apiUserBanks = apiUserBankMapper.selectUserBankList(apiUserBankVo);
        //如果还没绑定银行卡
        if (apiUserBanks.size() == 0){
            return AjaxResult.error("请先去绑定银行卡","hint_81");
//            if (StringUtils.isEmpty(apiUserBank.getBankName())){
//                return AjaxResult.error("请输入银行卡名称");
//            }
//            if (StringUtils.isEmpty(apiUserBank.getBankImg())){
//                return AjaxResult.error("请上传银行卡图片");
//            }
//            if (StringUtils.isEmpty(apiUserBank.getBankNo())){
//                return AjaxResult.error("请输入银行卡号码");
//            }
//            if (StringUtils.isEmpty(apiUserBank.getBankAddress())){
//                return AjaxResult.error("请输入银行卡开户地址");
//            }
//            if (StringUtils.isEmpty(apiUserBank.getAccountHolder())){
//                return AjaxResult.error("请输入持有人");
//            }
//            if (StringUtils.isEmpty(apiUserBank.getRoutingNumber())){
//                return AjaxResult.error("请输入路由编号");
//            }
//            if (!apiUserBank.getAccountHolder().equals(apiUserInfo.getRealName())){
//                return AjaxResult.error("银行卡持有人与真实姓名不符");
//            }
//            apiUserBank.setUserId(userId);
//            apiUserBank.setAddTime(new Date());
//            int insertUserBank = apiUserBankMapper.insertUserBank(apiUserBank);
//            if (insertUserBank <= 0){
//                throw new RuntimeException("操作失败");
//            }
        }else {
            if (!apiUserBanks.get(0).getAccountHolder().equals(apiUserInfo.getRealName())){
                return AjaxResult.error("银行卡持有人与真实姓名不符","hint_82");
            }
        }
        ApiUserInfo apiUserInfoVo = new ApiUserInfo();
        apiUserInfoVo.setIdNumber(apiUserInfo.getIdNumber());
        apiUserInfoVo.setRealName(apiUserInfo.getRealName());
        apiUserInfoVo.setIdCardImg1(apiUserInfo.getIdCardImg1());
        apiUserInfoVo.setIdCardImg2(apiUserInfo.getIdCardImg2());
        apiUserInfoVo.setIdCardImg3(apiUserInfo.getIdCardImg3());
        apiUserInfoVo.setId(apiUserInfo.getId());
        //更改实名认证状态为待审核
        apiUserInfoVo.setRealNameAuthStatus(1);
        int updateUserInfo = apiAuthMapper.updateUserInfo(apiUserInfoVo);
        if (updateUserInfo <= 0){
            throw new RuntimeException("操作失败");
        }
        //实名认证后台提醒
        webBackgroundService.insertReminder("reminder_realNameAuth",apiUserInfoVo.getId(),apiUserInfoVo.getRealName());
        return AjaxResult.success();
    }

    @Override
    public AjaxResult viewInvitedRecords(Long userId) {
        List<ApiUserInfo> result = new ArrayList<>();
        List<Long> list = new ArrayList<>();
        list.add(userId);
        //下级团队ids
        List<Long> teamIds = apiAuthMapper.queryMyTeam(list);
        for (int i = 0; i < teamIds.size(); i++) {
            Long id = teamIds.get(i);
            ApiUserInfo userInfo = apiAuthMapper.selectUserById(id);
            if (StringUtils.isNotNull(userInfo)){
                ApiUserInfo userInfoVo = new ApiUserInfo();
                //用户名
                userInfoVo.setUserName(userInfo.getUserName());
                //注册时间
                userInfoVo.setRegisterTime(userInfo.getRegisterTime());
                result.add(userInfoVo);
            }
        }
        return AjaxResult.success(result);
    }

}
