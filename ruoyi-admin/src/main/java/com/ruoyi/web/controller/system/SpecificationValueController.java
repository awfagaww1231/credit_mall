package com.ruoyi.web.controller.system;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.annotation.RepeatSubmit;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.SpecificationValue;
import com.ruoyi.system.service.ISpecificationValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 商品规格值Controller
 * 
 * @author ruoyi
 * @date 2022-11-18
 */
@RestController
@RequestMapping("/system/specificationValue")
public class SpecificationValueController extends BaseController
{
    @Autowired
    private ISpecificationValueService specificationValueService;

    /**
     * 查询商品规格值列表
     */
    @GetMapping("/list")
    public TableDataInfo list(SpecificationValue specificationValue)
    {
        startPage();
        List<SpecificationValue> list = specificationValueService.selectSpecificationValueList(specificationValue);
        return getDataTable(list);
    }

    /**
     * 导出商品规格值列表
     */
    @PreAuthorize("@ss.hasPermi('system:specificationValue:export')")
    @Log(title = "商品规格值", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SpecificationValue specificationValue)
    {
        List<SpecificationValue> list = specificationValueService.selectSpecificationValueList(specificationValue);
        ExcelUtil<SpecificationValue> util = new ExcelUtil<SpecificationValue>(SpecificationValue.class);
        util.exportExcel(response, list, "商品规格值数据");
    }

    /**
     * 获取商品规格值详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:specificationValue:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(specificationValueService.selectSpecificationValueById(id));
    }

    /**
     * 新增商品规格值
     */
    @PreAuthorize("@ss.hasPermi('system:specificationValue:add')")
    @Log(title = "商品规格值", businessType = BusinessType.INSERT)
    @PostMapping
    @RepeatSubmit
    public AjaxResult add(@RequestBody SpecificationValue specificationValue)
    {
        if (StringUtils.isNull(specificationValue.getId())){
            return AjaxResult.error("请先选择需要修改信息的规格值");
        }
        if (StringUtils.isNull(specificationValue.getSpecificationValue())){
            return AjaxResult.error("请输入规格值");
        }
        if (StringUtils.isEmpty(specificationValue.getSpecificationValue())){
            return AjaxResult.error("请输入规格值");
        }
        return toAjax(specificationValueService.insertSpecificationValue(specificationValue));
    }

    /**
     * 修改商品规格值
     */
    @PreAuthorize("@ss.hasPermi('system:specificationValue:edit')")
    @Log(title = "商品规格值", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SpecificationValue specificationValue)
    {
        if (StringUtils.isNull(specificationValue.getSpecificationValue())){
            return AjaxResult.error("请输入规格值");
        }
        if (StringUtils.isEmpty(specificationValue.getSpecificationValue())){
            return AjaxResult.error("请输入规格值");
        }
        return toAjax(specificationValueService.updateSpecificationValue(specificationValue));
    }

    /**
     * 删除商品规格值
     */
    @PreAuthorize("@ss.hasPermi('system:specificationValue:remove')")
    @Log(title = "商品规格值", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(specificationValueService.deleteSpecificationValueByIds(ids));
    }
}
