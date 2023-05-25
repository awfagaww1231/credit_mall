package com.ruoyi.system.service;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.system.domain.VipActivationRecord;

import java.util.List;

/**
 * vip权限激活记录Service接口
 * 
 * @author ruoyi
 * @date 2023-04-19
 */
public interface IVipActivationRecordService 
{
    /**
     * 查询vip权限激活记录
     * 
     * @param id vip权限激活记录主键
     * @return vip权限激活记录
     */
    public VipActivationRecord selectVipActivationRecordById(Long id);

    /**
     * 查询vip权限激活记录列表
     * 
     * @param vipActivationRecord vip权限激活记录
     * @return vip权限激活记录集合
     */
    public List<VipActivationRecord> selectVipActivationRecordList(VipActivationRecord vipActivationRecord);

    /**
     * 新增vip权限激活记录
     * 
     * @param vipActivationRecord vip权限激活记录
     * @return 结果
     */
    public int insertVipActivationRecord(VipActivationRecord vipActivationRecord);

    /**
     * 修改vip权限激活记录
     * 
     * @param vipActivationRecord vip权限激活记录
     * @return 结果
     */
    public int updateVipActivationRecord(VipActivationRecord vipActivationRecord);

    /**
     * 批量删除vip权限激活记录
     * 
     * @param ids 需要删除的vip权限激活记录主键集合
     * @return 结果
     */
    public int deleteVipActivationRecordByIds(Long[] ids);

    /**
     * 删除vip权限激活记录信息
     * 
     * @param id vip权限激活记录主键
     * @return 结果
     */
    public int deleteVipActivationRecordById(Long id);

    //vip权限开通审核通过
    AjaxResult agree(Long id);

    //vip权限开通审核驳回
    AjaxResult reject(Long id,String remark);
}
