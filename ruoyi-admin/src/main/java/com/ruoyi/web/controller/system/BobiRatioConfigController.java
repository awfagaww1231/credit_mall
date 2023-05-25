package com.ruoyi.web.controller.system;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.annotation.RepeatSubmit;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.BobiRatioConfig;
import com.ruoyi.system.service.IBobiRatioConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 商家进货价波比配置Controller
 * 
 * @author ruoyi
 * @date 2022-11-11
 */
@RestController
@RequestMapping("/system/bobiRatioConfig")
public class BobiRatioConfigController extends BaseController
{
    @Autowired
    private IBobiRatioConfigService bobiRatioConfigService;

    /**
     * 查询商家进货价波比配置列表
     */
    @PreAuthorize("@ss.hasPermi('system:bobiRatioConfig:list')")
    @GetMapping("/list")
    public TableDataInfo list(BobiRatioConfig bobiRatioConfig)
    {
        startPage();
        List<BobiRatioConfig> list = bobiRatioConfigService.selectBobiRatioConfigList(bobiRatioConfig);
        return getDataTable(list);
    }

    /**
     * 获取商家进货价波比配置详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:bobiRatioConfig:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(bobiRatioConfigService.selectBobiRatioConfigById(id));
    }

    /**
     * 新增商家进货价波比配置
     */
    @PreAuthorize("@ss.hasPermi('system:bobiRatioConfig:add')")
    @Log(title = "商家进货价波比配置", businessType = BusinessType.INSERT)
    @RepeatSubmit
    @PostMapping
    public AjaxResult add(@RequestBody BobiRatioConfig bobiRatioConfig)
    {
        return toAjax(bobiRatioConfigService.insertBobiRatioConfig(bobiRatioConfig));
    }

    /**
     * 修改商家进货价波比配置
     */
    @PreAuthorize("@ss.hasPermi('system:bobiRatioConfig:edit')")
    @Log(title = "商家进货价波比配置", businessType = BusinessType.UPDATE)
    @RepeatSubmit
    @PutMapping
    public AjaxResult edit(@RequestBody BobiRatioConfig bobiRatioConfig)
    {
        return toAjax(bobiRatioConfigService.updateBobiRatioConfig(bobiRatioConfig));
    }

    /**
     * 删除商家进货价波比配置
     */
    @PreAuthorize("@ss.hasPermi('system:bobiRatioConfig:remove')")
    @Log(title = "商家进货价波比配置", businessType = BusinessType.DELETE)
    @RepeatSubmit
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(bobiRatioConfigService.deleteBobiRatioConfigByIds(ids));
    }
}
