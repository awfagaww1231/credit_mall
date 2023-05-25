package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.ApplySellerAccount;

import java.util.List;

/**
 * 申请成为商户记录Mapper接口
 * 
 * @author ruoyi
 * @date 2023-02-17
 */
public interface ApplySellerAccountMapper 
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
     * 删除申请成为商户记录
     * 
     * @param id 申请成为商户记录主键
     * @return 结果
     */
    public int deleteApplySellerAccountById(Long id);

    /**
     * 批量删除申请成为商户记录
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteApplySellerAccountByIds(Long[] ids);
}
