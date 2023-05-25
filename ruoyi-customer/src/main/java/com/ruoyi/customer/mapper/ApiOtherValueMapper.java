package com.ruoyi.customer.mapper;

import com.ruoyi.customer.domain.ApiOtherValue;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 其他值Mapper接口
 * 
 * @author ruoyi
 * @date 2023-03-27
 */
public interface ApiOtherValueMapper
{
    /**
     * 查询其他值
     * 
     * @param id 其他值主键
     * @return 其他值
     */
    public ApiOtherValue selectOtherValueById(Long id);

    /**
     * 查询其他值
     *
     * @param key key
     * @return 其他值
     */
    public ApiOtherValue selectOtherValueByKey(String key);

    /**
     * 查询其他值列表
     * 
     * @param otherValue 其他值
     * @return 其他值集合
     */
    public List<ApiOtherValue> selectOtherValueList(ApiOtherValue otherValue);

    /**
     * 新增其他值
     * 
     * @param otherValue 其他值
     * @return 结果
     */
    public int insertOtherValue(ApiOtherValue otherValue);

    /**
     * 修改其他值
     * 
     * @param otherValue 其他值
     * @return 结果
     */
    public int updateOtherValue(ApiOtherValue otherValue);

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
