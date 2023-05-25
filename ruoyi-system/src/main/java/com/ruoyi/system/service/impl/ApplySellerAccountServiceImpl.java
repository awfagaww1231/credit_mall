package com.ruoyi.system.service.impl;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.common.AdminTokenService;
import com.ruoyi.system.domain.ApplySellerAccount;
import com.ruoyi.system.mapper.ApplySellerAccountMapper;
import com.ruoyi.system.service.IApplySellerAccountService;
import com.ruoyi.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 申请成为商户记录Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-02-17
 */
@Service
public class ApplySellerAccountServiceImpl implements IApplySellerAccountService 
{
    @Autowired
    private ApplySellerAccountMapper applySellerAccountMapper;

    @Autowired
    private ISysUserService sysUserService;

    @Autowired
    private AdminTokenService tokenService;

    /**
     * 查询申请成为商户记录
     * 
     * @param id 申请成为商户记录主键
     * @return 申请成为商户记录
     */
    @Override
    public ApplySellerAccount selectApplySellerAccountById(Long id)
    {
        return applySellerAccountMapper.selectApplySellerAccountById(id);
    }

    /**
     * 查询申请成为商户记录列表
     * 
     * @param applySellerAccount 申请成为商户记录
     * @return 申请成为商户记录
     */
    @Override
    public List<ApplySellerAccount> selectApplySellerAccountList(ApplySellerAccount applySellerAccount)
    {
        return applySellerAccountMapper.selectApplySellerAccountList(applySellerAccount);
    }

    /**
     * 新增申请成为商户记录
     * 
     * @param applySellerAccount 申请成为商户记录
     * @return 结果
     */
    @Override
    public int insertApplySellerAccount(ApplySellerAccount applySellerAccount)
    {
        return applySellerAccountMapper.insertApplySellerAccount(applySellerAccount);
    }

    /**
     * 修改申请成为商户记录
     * 
     * @param applySellerAccount 申请成为商户记录
     * @return 结果
     */
    @Override
    public int updateApplySellerAccount(ApplySellerAccount applySellerAccount)
    {
        return applySellerAccountMapper.updateApplySellerAccount(applySellerAccount);
    }

    /**
     * 批量删除申请成为商户记录
     * 
     * @param ids 需要删除的申请成为商户记录主键
     * @return 结果
     */
    @Override
    public int deleteApplySellerAccountByIds(Long[] ids)
    {
        return applySellerAccountMapper.deleteApplySellerAccountByIds(ids);
    }

    /**
     * 删除申请成为商户记录信息
     * 
     * @param id 申请成为商户记录主键
     * @return 结果
     */
    @Override
    public int deleteApplySellerAccountById(Long id)
    {
        return applySellerAccountMapper.deleteApplySellerAccountById(id);
    }

    /**
     * 通过商户账号申请
     */
    @Override
    @Transactional
    public AjaxResult agree(Long id) {
        //申请信息
        ApplySellerAccount applySellerAccount = applySellerAccountMapper.selectApplySellerAccountById(id);
        if (StringUtils.isNull(applySellerAccount)){
            return AjaxResult.error("查询该申请信息异常，请刷新后重新尝试");
        }
        if (applySellerAccount.getStatus() != 0){
            return AjaxResult.error("此申请已经审核过了");
        }
        //变更状态为通过
        applySellerAccount.setStatus(1);
        int count = applySellerAccountMapper.updateApplySellerAccount(applySellerAccount);
        if (count <= 0){
            throw new RuntimeException();
        }
        //插入商户信息
        //商户账号（用户名）
        String sellerAccount = applySellerAccount.getSellerAccount();
        //商户密码
        String password = applySellerAccount.getPassword();
        SysUser sysUser = new SysUser();
        sysUser.setUserName(sellerAccount);
        sysUser.setPassword(SecurityUtils.encryptPassword(password));
        int addSeller = addSeller(sysUser);
        if (addSeller <= 0){
            throw new RuntimeException();
        }
        return AjaxResult.success();
    }

    /**
     * 新增商户
     */
    public int addSeller(SysUser user)
    {
        Long[] roleIds = new Long[1];
        roleIds[0] = 100L;
        user.setRoleIds(roleIds);
        user.setCreateBy(tokenService.getLoginUser().getUsername());
        user.setPassword(SecurityUtils.encryptPassword(user.getPassword()));
        return sysUserService.insertUser(user);
    }

    /**
     * 驳回商户账号申请
     */
    @Override
    public AjaxResult reject(Long id){
        ApplySellerAccount applySellerAccount = applySellerAccountMapper.selectApplySellerAccountById(id);
        if (StringUtils.isNull(applySellerAccount)){
            return AjaxResult.error("查询该申请信息异常，请刷新后重新尝试");
        }
        if (applySellerAccount.getStatus() != 0){
            return AjaxResult.error("此申请已经审核过了");
        }
        //变更状态为驳回
        applySellerAccount.setStatus(2);
        int count = applySellerAccountMapper.updateApplySellerAccount(applySellerAccount);
        if (count <= 0){
            throw new RuntimeException();
        }
        return AjaxResult.success();
    }
}
