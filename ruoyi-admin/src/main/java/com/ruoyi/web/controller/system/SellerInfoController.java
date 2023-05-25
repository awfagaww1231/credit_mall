package com.ruoyi.web.controller.system;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.annotation.RepeatSubmit;
import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 商户信息Controller
 *
 * @author ruoyi
 * @date 2022-11-01
 */
@RestController
@RequestMapping("/system/sellerInfo")
public class SellerInfoController extends BaseController {

    @Autowired
    private ISysUserService userService;


    /**
     * 获取商户信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:sellerInfo:sellerList')")
    @GetMapping("/sellerList")
    public TableDataInfo sellerList(SysUser user)
    {
        startPage();
        List<SysUser> list = userService.sellerList(user);
        for (int i = 0; i < list.size(); i++) {
            //会员等级
            Integer vipLevel = list.get(i).getVipLevel();
            if (vipLevel == null){
                list.get(i).setVipLevel(0);
            }
            //代理线
            String referrerLine = "暂无上级";
            List<Long> referrerList = userService.queryReferrer(list.get(i).getUserId());
            if (referrerList.size() > 0){
                referrerLine = list.get(i).getUserName();
            }
            for (int j = 0; j < referrerList.size(); j++) {
                Long referrerId = referrerList.get(j);
                SysUser sysUser = userService.selectUserById(referrerId);
                if (StringUtils.isNotNull(sysUser)){
                    referrerLine =sysUser.getUserName()  + ">>" + referrerLine;
                }else {
                    break;
                }
            }
            list.get(i).setReferrerLine(referrerLine);
        }
        return getDataTable(list);
    }


    /**
     * 新增商户
     */
    @PreAuthorize("@ss.hasPermi('system:sellerInfo:addSeller')")
    @Log(title = "新增商户", businessType = BusinessType.INSERT)
    @RepeatSubmit
    @PostMapping("/addSeller")
    public AjaxResult addSeller(@Validated @RequestBody SysUser user)
    {
        Long[] roleIds = new Long[1];
        roleIds[0] = 100L;
        user.setRoleIds(roleIds);
        if (UserConstants.NOT_UNIQUE.equals(userService.checkUserNameUnique(user)))
        {
            return AjaxResult.error("新增商户'" + user.getUserName() + "'失败，登录账号已存在");
        }
        else if (StringUtils.isNotEmpty(user.getPhonenumber())
                && UserConstants.NOT_UNIQUE.equals(userService.checkPhoneUnique(user)))
        {
            return AjaxResult.error("新增商户'" + user.getUserName() + "'失败，手机号码已存在");
        }
        else if (StringUtils.isNotEmpty(user.getEmail())
                && UserConstants.NOT_UNIQUE.equals(userService.checkEmailUnique(user)))
        {
            return AjaxResult.error("新增商户'" + user.getUserName() + "'失败，邮箱账号已存在");
        }
        user.setCreateBy(getUsername());
        user.setPassword(SecurityUtils.encryptPassword(user.getPassword()));
        return toAjax(userService.insertUser(user));
    }


    /**
     * 变更上级代理
     */
    @PreAuthorize("@ss.hasPermi('system:sellerInfo:setReferrer')")
    @GetMapping("/setReferrer")
    public AjaxResult setReferrer(Long userId,Long referrerId)
    {
        SysUser sysUser = userService.selectUserById(referrerId);
        if (StringUtils.isNull(sysUser)){
            return AjaxResult.error("此商户id不存在");
        }
        if (referrerId.equals(userId)){
            return AjaxResult.error("不允许设置该商户为自己的上级");
        }
        List<Long> referrerList = userService.queryReferrer(referrerId);
        for (int i = 0; i < referrerList.size(); i++) {
            if (referrerList.get(i).equals(userId)){
                return AjaxResult.error("填入的商户为该商户的下级，不允许设置为其上级");
            }
        }
        return toAjax(userService.setReferrer(userId,referrerId));
    }

    /**
     * 重置登录密码（默认密码123456）
     */
    @PostMapping("/resetPassword")
    @PreAuthorize("@ss.hasPermi('system:sellerInfo:resetPassword')")
    public AjaxResult resetPassword(@RequestBody SysUser sysUser) {
        return toAjax(userService.resetPassword (sysUser.getUserId(),sysUser.getPassword()));
    }
}
