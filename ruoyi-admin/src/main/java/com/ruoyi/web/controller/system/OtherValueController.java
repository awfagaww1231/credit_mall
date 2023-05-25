package com.ruoyi.web.controller.system;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.annotation.RepeatSubmit;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.OtherValue;
import com.ruoyi.system.service.IOtherValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 其他值Controller
 * 
 * @author ruoyi
 * @date 2023-03-27
 */
@RestController
@RequestMapping("/system/otherValue")
public class OtherValueController extends BaseController
{
    @Autowired
    private IOtherValueService otherValueService;

    /**
     * 查询其他值列表
     */
    //@PreAuthorize("@ss.hasPermi('system:otherValue:list')")
    @GetMapping("/list")
    public TableDataInfo list(OtherValue otherValue)
    {
        startPage();
        List<OtherValue> list = otherValueService.selectOtherValueList(otherValue);
        return getDataTable(list);
    }

    /**
     * 获取其他值详细信息
     */
    //@PreAuthorize("@ss.hasPermi('system:otherValue:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(otherValueService.selectOtherValueById(id));
    }

    /**
     * 新增其他值
     */
    @PreAuthorize("@ss.hasPermi('system:otherValue:add')")
    @Log(title = "其他值", businessType = BusinessType.INSERT)
    @RepeatSubmit
    @PostMapping
    public AjaxResult add(@RequestBody OtherValue otherValue)
    {
        if (StringUtils.isEmpty(otherValue.getOtherKey())){
            return AjaxResult.error("请输入key");
        }
        if (StringUtils.isEmpty(otherValue.getName())){
            return AjaxResult.error("请输入名称");
        }
        if (StringUtils.isEmpty(otherValue.getOtherValue())){
            return AjaxResult.error("请输入value");
        }
        return otherValueService.insertOtherValue(otherValue);
    }

    /**
     * 修改其他值
     */
    @PreAuthorize("@ss.hasPermi('system:otherValue:edit')")
    @Log(title = "其他值", businessType = BusinessType.UPDATE)
    @RepeatSubmit
    @PutMapping
    public AjaxResult edit(@RequestBody OtherValue otherValue)
    {
        if (StringUtils.isNotEmpty(otherValue.getOtherKey())){
            otherValue.setOtherKey(null);
//            return AjaxResult.error("key不允许修改");
        }
        return otherValueService.updateOtherValue(otherValue);
    }

    /**
     * 删除其他值
     */
    @PreAuthorize("@ss.hasPermi('system:otherValue:remove')")
    @Log(title = "其他值", businessType = BusinessType.DELETE)
    @RepeatSubmit
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(otherValueService.deleteOtherValueByIds(ids));
    }


}
