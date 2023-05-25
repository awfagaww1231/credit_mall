package com.ruoyi.system.service;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.system.domain.ApplySellerAccount;

import java.util.List;

/**
 * 申请成为商户记录Service接口
 * 
 * @author ruoyi
 * @date 2023-02-17
 */
public interface IApplySellerAccountService 
{
    /**
     * 查询申请成为商户记录
     * 
     * @param id 申请成为商户记录主键
     * @return 申请成为商户记录
     */
    public ApplySellerAccount selectApplySellerAccountById(Long id);

    /**
     * 查询申请成为商户记录列表
     * 
     * @param applySellerAccount 申请成为商户记录
     * @return 申请成为商户记录集合
     */
    public List<ApplySellerAccount> selectApplySellerAccountList(ApplySellerAccount applySellerAccount);

    /**
     * 新增申请成为商户记录
     * 
     * @param applySellerAccount 申请成为商户记录
     * @return 结果
     */
    public int insertApplySellerAccount(ApplySellerAccount applySellerAccount);

    /**
     * 修改申请成为商户记录
     * 
     * @param applySellerAccount 申请成为商户记录
     * @return 结果
     */
    public int updateApplySellerAccount(ApplySellerAccount applySellerAccount);

    /**
     * 批量删除申请成为商户记录
     * 
     * @param ids 需要删除的申请成为商户记录主键集合
     * @return 结果
     */
    public int deleteApplySellerAccountByIds(Long[] ids);

    /**
     * 删除申请成为商户记录信息
     * 
     * @param id 申请成为商户记录主键
     * @return 结果
     */
    public int deleteApplySellerAccountById(Long id);

    /**
     * 通过商户账号申请
     */
    AjaxResult agree(Long id);


    /**
     * 驳回商户账号申请
     */
    AjaxResult reject(Long id);
}
