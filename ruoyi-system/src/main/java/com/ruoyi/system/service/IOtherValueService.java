package com.ruoyi.system.service;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.system.domain.OtherValue;

import java.util.List;

/**
 * 其他值Service接口
 * 
 * @author ruoyi
 * @date 2023-03-27
 */
public interface IOtherValueService 
{
    /**
     * 查询其他值
     * 
     * @param id 其他值主键
     * @return 其他值
     */
    public OtherValue selectOtherValueById(Long id);

    /**
     * 查询其他值
     *
     * @param key key
     * @return 其他值
     */
    public OtherValue selectOtherValueByKey(String key);

    /**
     * 查询其他值列表
     * 
     * @param otherValue 其他值
     * @return 其他值集合
     */
    public List<OtherValue> selectOtherValueList(OtherValue otherValue);

    /**
     * 新增其他值
     * 
     * @param otherValue 其他值
     * @return 结果
     */
    public AjaxResult insertOtherValue(OtherValue otherValue);

    /**
     * 修改其他值
     * 
     * @param otherValue 其他值
     * @return 结果
     */
    public AjaxResult updateOtherValue(OtherValue otherValue);

    /**
     * 修改其他值
     *
     * @param otherValue 其他值
     * @return 结果
     */
    public int updateOtherValue(String key,String otherValue);

    /**
     * 批量删除其他值
     * 
     * @param ids 需要删除的其他值主键集合
     * @return 结果
     */
    public int deleteOtherValueByIds(Long[] ids);

    /**
     * 删除其他值信息
     * 
     * @param id 其他值主键
     * @return 结果
     */
    public int deleteOtherValueById(Long id);
}
