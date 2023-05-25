package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.OtherValue;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 其他值Mapper接口
 * 
 * @author ruoyi
 * @date 2023-03-27
 */
public interface OtherValueMapper 
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
    public int insertOtherValue(OtherValue otherValue);

    /**
     * 修改其他值
     * 
     * @param otherValue 其他值
     * @return 结果
     */
    public int updateOtherValue(OtherValue otherValue);

    /**
     * 修改其他值
     *
     * @param otherValue 其他值
     * @return 结果
     */
    public int updateOtherValueByKey(@Param("key") String key,@Param("otherValue") String otherValue);

    /**
     * 删除其他值
     * 
     * @param id 其他值主键
     * @return 结果
     */
    public int deleteOtherValueById(Long id);

    /**
     * 批量删除其他值
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteOtherValueByIds(Long[] ids);
}
