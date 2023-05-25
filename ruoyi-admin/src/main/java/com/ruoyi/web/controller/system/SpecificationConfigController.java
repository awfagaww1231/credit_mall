package com.ruoyi.web.controller.system;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.annotation.RepeatSubmit;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.SpecificationConfig;
import com.ruoyi.system.service.ISpecificationConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 商品规格配置Controller
 * 
 * @author ruoyi
 * @date 2022-11-18
 */
@RestController
@RequestMapping("/system/specificationConfig")
public class SpecificationConfigController extends BaseController
{
    @Autowired
    private ISpecificationConfigService specificationConfigService;

    /**
     * 查询商品规格配置列表
     */
    @PreAuthorize("@ss.hasPermi('system:specificationConfig:list')")
    @GetMapping("/list")
    public TableDataInfo list(SpecificationConfig specificationConfig)
    {
        startPage();
        List<SpecificationConfig> list = specificationConfigService.selectSpecificationConfigList(specificationConfig);
        return getDataTable(list);
    }

    /**
     * 导出商品规格配置列表
     */
    @PreAuthorize("@ss.hasPermi('system:specificationConfig:export')")
    @Log(title = "商品规格配置", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SpecificationConfig specificationConfig)
    {
        List<SpecificationConfig> list = specificationConfigService.selectSpecificationConfigList(specificationConfig);
        ExcelUtil<SpecificationConfig> util = new ExcelUtil<SpecificationConfig>(SpecificationConfig.class);
        util.exportExcel(response, list, "商品规格配置数据");
    }

    /**
     * 获取商品规格配置详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:specificationConfig:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(specificationConfigService.selectSpecificationConfigById(id));
    }

    /**
     * 新增商品规格配置
     */
    @PreAuthorize("@ss.hasPermi('system:specificationConfig:add')")
    @Log(title = "商品规格配置", businessType = BusinessType.INSERT)
    @PostMapping
    @RepeatSubmit
    public AjaxResult add(@RequestBody SpecificationConfig specificationConfig)
    {
        if (StringUtils.isNull(specificationConfig.getSpecificationName())){
            return AjaxResult.error("请输入规格名称");
        }
        if (StringUtils.isEmpty(specificationConfig.getSpecificationName())){
            return AjaxResult.error("请输入规格名称");
        }
        return toAjax(specificationConfigService.insertSpecificationConfig(specificationConfig));
    }

    /**
     * 修改商品规格配置
     */
    @PreAuthorize("@ss.hasPermi('system:specificationConfig:edit')")
    @Log(title = "商品规格配置", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SpecificationConfig specificationConfig)
    {
        if (StringUtils.isNull(specificationConfig.getId())){
            return AjaxResult.error("请先选择需要修改信息的规格");
        }
        if (StringUtils.isNull(specificationConfig.getSpecificationName())){
            return AjaxResult.error("请输入规格名称");
        }
        if (StringUtils.isEmpty(specificationConfig.getSpecificationName())){
            return AjaxResult.error("请输入规格名称");
        }
        return toAjax(specificationConfigService.updateSpecificationConfig(specificationConfig));
    }

    /**
     * 删除商品规格配置
     */
    @PreAuthorize("@ss.hasPermi('system:specificationConfig:remove')")
    @Log(title = "商品规格配置", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(specificationConfigService.deleteSpecificationConfigByIds(ids));
    }
}
