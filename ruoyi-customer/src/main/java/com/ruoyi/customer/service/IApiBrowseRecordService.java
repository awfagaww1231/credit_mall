package com.ruoyi.customer.service;

import java.util.List;

import com.ruoyi.customer.domain.ApiBrowseRecord;

/**
 * 用户浏览足迹记录Service接口
 * 
 * @author ruoyi
 * @date 2022-11-07
 */
public interface IApiBrowseRecordService
{
    /**
     * 查询用户浏览足迹记录
     * 
     * @param id 用户浏览足迹记录主键
     * @return 用户浏览足迹记录
     */
    public ApiBrowseRecord selectBrowseRecordById(Long id);

    /**
     * 查询用户浏览足迹记录列表
     * 
     * @param apiBrowseRecord 用户浏览足迹记录
     * @return 用户浏览足迹记录集合
     */
    public List<ApiBrowseRecord> selectBrowseRecordList(ApiBrowseRecord apiBrowseRecord);

    /**
     * 新增用户浏览足迹记录
     * 
     * @param apiBrowseRecord 用户浏览足迹记录
     * @return 结果
     */
    public int insertBrowseRecord(ApiBrowseRecord apiBrowseRecord);

    /**
     * 修改用户浏览足迹记录
     * 
     * @param apiBrowseRecord 用户浏览足迹记录
     * @return 结果
     */
    public int updateBrowseRecord(ApiBrowseRecord apiBrowseRecord);

    /**
     * 批量删除用户浏览足迹记录
     * 
     * @param ids 需要删除的用户浏览足迹记录主键集合
     * @return 结果
     */
    public int deleteBrowseRecordByIds(Long[] ids);

    /**
     * 删除用户浏览足迹记录信息
     * 
     * @param id 用户浏览足迹记录主键
     * @return 结果
     */
    public int deleteBrowseRecordById(Long id);
}
