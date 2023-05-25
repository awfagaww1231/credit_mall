package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.VipActivationRecord;

import java.util.List;

/**
 * vip权限激活记录Mapper接口
 * 
 * @author ruoyi
 * @date 2023-04-19
 */
public interface VipActivationRecordMapper 
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
     * 删除vip权限激活记录
     * 
     * @param id vip权限激活记录主键
     * @return 结果
     */
    public int deleteVipActivationRecordById(Long id);

    /**
     * 批量删除vip权限激活记录
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteVipActivationRecordByIds(Long[] ids);
}
