package com.ruoyi.system.service.impl;

import com.ruoyi.common.annotation.DataScope;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.x.keyUtil.KeyUtil;
import com.ruoyi.system.domain.UserInfo;
import com.ruoyi.system.domain.UserPromotionStatistics;
import com.ruoyi.system.mapper.UserInfoMapper;
import com.ruoyi.system.service.IUserInfoService;
import com.ruoyi.system.service.IWebBackgroundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

/**
 * C端用户信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-11-01
 */
@Service
public class UserInfoServiceImpl implements IUserInfoService 
{
    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private IWebBackgroundService webBackgroundService;

    /**
     * 查询C端用户信息
     * 
     * @param id C端用户信息主键
     * @return C端用户信息
     */
    @Override
    public UserInfo selectUserInfoById(Long id)
    {
        return userInfoMapper.selectUserInfoById(id);
    }

    @Override
    public UserInfo selectUserInfoByMobile(String mobile) {
        return userInfoMapper.selectUserInfoByMobile (mobile);
    }

    /**
     * 查询C端用户信息列表
     * 
     * @param userInfo C端用户信息
     * @return C端用户信息
     */
    @Override
    @DataScope(userAlias = "su")
    public List<UserInfo> selectUserInfoList(UserInfo userInfo)
    {
        String myInvite = userInfo.getMyInvite();
        if ("1".equals(myInvite)){//仅查询登录用户邀请的用户信息。
            userInfo.setInviteCode(SecurityUtils.getLoginUser().getUserId().toString());
        }
        return userInfoMapper.selectUserInfoList(userInfo);
    }

    /**
     * 新增C端用户信息
     * 
     * @param userInfo C端用户信息
     * @return 结果
     */
    @Override
    public AjaxResult insertUserInfo(UserInfo userInfo)
    {
        //根据手机号查询的用户信息
        UserInfo userVo = userInfoMapper.selectUserInfoByMobile (userInfo.getMobile ());
        if (StringUtils.isNotNull (userVo)){
            return AjaxResult.error ("此手机号已被注册!");
        }

        //即将新增的用户信息
        userVo = new UserInfo();
        //用户名
        userVo.setUserName(userInfo.getUserName());
        //用户昵称
        userVo.setNickName(userInfo.getNickName());
        //用户手机号
        userVo.setMobile(userInfo.getMobile());
        //邮箱
        userVo.setEmail(userInfo.getEmail());
        //用户登陆密码
        userVo.setPassword(SecurityUtils.encryptPassword(userInfo.getPassword()));
        //用户资金密码
        userVo.setPayPassword(SecurityUtils.encryptPassword(userInfo.getPayPassword()));
        //用户类型
        userVo.setUserType(userInfo.getUserType());
        //创建人
        userVo.setCreateUser(userInfo.getCreateUser());
        //注册时间
        userVo.setRegisterTime (DateUtils.getNowDate());
        //创建时间
        userVo.setCreateTime (DateUtils.getNowDate());
        //如果是机器人
        if (userInfo.getUserType() == 2){
            userVo.setAmount(BigDecimal.valueOf(999999));
        }
        //生成自己的邀请码
        while (true){
            //邀请码
            String inviteCode = KeyUtil.getInviteCode(6);
            //根据邀请码查看用户信息
            UserInfo userInfoVo = userInfoMapper.selectUserByInviteCode(inviteCode);
            //如果有人占用，重新获取新的邀请码
            if (StringUtils.isNull(userInfoVo)){
                userVo.setInviteCode(inviteCode);
                break;
            }
        }
        int insertUserInfo = userInfoMapper.insertUserInfo(userVo);
        if (insertUserInfo <= 0){
            return AjaxResult.error();
        }
        return AjaxResult.success();
    }

    /**
     * 修改C端用户信息
     * 
     * @param userInfo C端用户信息
     * @return 结果
     */
    @Override
    public int updateUserInfo(UserInfo userInfo)
    {
        return userInfoMapper.updateUserInfo(userInfo);
    }

    /**
     * 批量删除C端用户信息
     * 
     * @param ids 需要删除的C端用户信息主键
     * @return 结果
     */
    @Override
    public int deleteUserInfoByIds(Long[] ids)
    {
//        for (int i = 0; i < ids.length; i++) {
//            //清除该用户卡片激活的后台提醒
//            webBackgroundService.delReminder("reminder_creditCardActivation",ids[i]);
//            webBackgroundService.delReminder("reminder_creditCardApply",ids[i]);
//            webBackgroundService.delReminder("reminder_realNameAuth",ids[i]);
//        }
        return userInfoMapper.deleteUserInfoByIds(ids);
    }

    /**
     * 删除C端用户信息信息
     * 
     * @param id C端用户信息主键
     * @return 结果
     */
    @Override
    public int deleteUserInfoById(Long id)
    {
        return userInfoMapper.deleteUserInfoById(id);
    }


    //启用、禁用
    @Override
    public int updateStatus(Long id) {
        //查询用户现有状态
        int status = userInfoMapper.queryUserStatus (id);
        if (status == 0) {
            status = 1;
        }else{
            status = 0;
        }
        //更改状态
        return userInfoMapper.chuangeUserStatus (id, status);
    }

    @Override
    public int resetPassword(Long id,String password) {
        //密码加密
        String passwordSecurity = SecurityUtils.encryptPassword (password);
        return userInfoMapper.resetPassword (id, passwordSecurity);
    }

    @Override
    public int resetPayPassword(Long id,String payPassword) {
        //密码加密
        String passwordSecurity = SecurityUtils.encryptPassword (payPassword);
        return userInfoMapper.resetPayPassword (id, passwordSecurity);
    }

    /**
     * 配置信用卡片额度与可用状态
     */
    @Override
    public AjaxResult setCreditCardInfo(UserInfo userInfo) {
        Long id = userInfo.getId();
        UserInfo userInfoVo = userInfoMapper.selectUserInfoById(id);
        if (userInfoVo.getCreditCardStatus() != 5){
            return AjaxResult.error("此用户信用卡片还未激活");
        }

        int count = userInfoMapper.updateUserInfo(userInfo);
        if (count <= 0){
            return AjaxResult.error();
        }
        return AjaxResult.success();
    }

    /**
     * 查看用户所有信息（包括房产证。驾驶证。行驶证等。。）
     */
    @Override
    public AjaxResult getUserAllAssetInformation(Long userId) {
        HashMap<String, Object> map = new HashMap<>();
        UserInfo userInfo = userInfoMapper.selectUserInfoById(userId);
        if (userInfo == null){
            return AjaxResult.error("获取用户信息异常");
        }

        map.put("userInfo",userInfo);
        map.put("estateInfo",null);
        map.put("driverLicense",null);
        return AjaxResult.success(map);
    }

    /**
     * 修改实名认证信息
     */
    @Override
    public AjaxResult updateAuthInfo(UserInfo userInfo) {
        int count = userInfoMapper.updateUserInfo(userInfo);
        if (count <= 0){
            return AjaxResult.error();
        }
//        webBackgroundService.delReminder("reminder_realNameAuth",userInfo.getId());
        return AjaxResult.success();
    }

    @Override
    @DataScope(userAlias = "su")
    public List<UserPromotionStatistics> queryUserPromotionStatistics(UserPromotionStatistics userPromotionStatistics) {
        return userInfoMapper.queryUserPromotionStatistics(userPromotionStatistics);
    }
}
