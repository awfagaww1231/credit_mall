package com.ruoyi.system.service.impl;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.OtherValue;
import com.ruoyi.system.mapper.OtherValueMapper;
import com.ruoyi.system.service.IOtherValueService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 其他值Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-03-27
 */
@Service
public class OtherValueServiceImpl implements IOtherValueService 
{
    @Resource
    private OtherValueMapper otherValueMapper;

    /**
     * 查询其他值
     * 
     * @param id 其他值主键
     * @return 其他值
     */
    @Override
    public OtherValue selectOtherValueById(Long id)
    {
        return otherValueMapper.selectOtherValueById(id);
    }

    /**
     * 查询其他值
     *
     * @param key key
     * @return 其他值
     */
    @Override
    public OtherValue selectOtherValueByKey(String key) {
        OtherValue otherValue = otherValueMapper.selectOtherValueByKey(key);
        if (StringUtils.isNull(otherValue)){
            otherValue = new OtherValue();
            otherValue.setOtherKey(key);
        }
        return otherValue;
    }

    /**
     * 查询其他值列表
     * 
     * @param otherValue 其他值
     * @return 其他值
     */
    @Override
    public List<OtherValue> selectOtherValueList(OtherValue otherValue)
    {
        return otherValueMapper.selectOtherValueList(otherValue);
    }

    /**
     * 新增其他值
     * 
     * @param otherValue 其他值
     * @return 结果
     */
    @Override
    public AjaxResult insertOtherValue(OtherValue otherValue)
    {
        OtherValue otherValueVo = otherValueMapper.selectOtherValueByKey(otherValue.getOtherKey());
        if (StringUtils.isNotNull(otherValueVo)){
            return AjaxResult.error("此key已存在");
        }
        int insertOtherValue = otherValueMapper.insertOtherValue(otherValue);
        if (insertOtherValue <= 0){
            return AjaxResult.error();
        }
        return AjaxResult.success();
    }

    /**
     * 修改其他值
     * 
     * @param otherValue 其他值
     * @return 结果
     */
    @Override
    public AjaxResult updateOtherValue(OtherValue otherValue)
    {
        int updateOtherValue = otherValueMapper.updateOtherValue(otherValue);
        if (updateOtherValue <= 0){
            return AjaxResult.error();
        }
        return AjaxResult.success();
    }

    @Override
    public int updateOtherValue(String key, String otherValue) {
        return otherValueMapper.updateOtherValueByKey(key,otherValue);
    }

    /**
     * 批量删除其他值
     * 
     * @param ids 需要删除的其他值主键
     * @return 结果
     */
    @Override
    public int deleteOtherValueByIds(Long[] ids)
    {
        return otherValueMapper.deleteOtherValueByIds(ids);
    }

    /**
     * 删除其他值信息
     * 
     * @param id 其他值主键
     * @return 结果
     */
    @Override
    public int deleteOtherValueById(Long id)
    {
        return otherValueMapper.deleteOtherValueById(id);
    }
}
