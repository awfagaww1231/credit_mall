package com.ruoyi.web.controller.system;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.annotation.RepeatSubmit;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.CommissionConfig;
import com.ruoyi.system.service.ICommissionConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 返佣配置Controller
 * 
 * @author ruoyi
 * @date 2022-11-14
 */
@RestController
@RequestMapping("/system/commissionConfig")
public class CommissionConfigController extends BaseController
{
    @Autowired
    private ICommissionConfigService commissionConfigService;

    /**
     * 查询返佣配置列表
     */
    @PreAuthorize("@ss.hasPermi('system:commissionConfig:list')")
    @GetMapping("/list")
    public TableDataInfo list(CommissionConfig commissionConfig)
    {
        startPage();
        List<CommissionConfig> list = commissionConfigService.selectCommissionConfigList(commissionConfig);
        return getDataTable(list);
    }

    /**
     * 导出返佣配置列表
     */
    @PreAuthorize("@ss.hasPermi('system:commissionConfig:export')")
    @Log(title = "返佣配置", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CommissionConfig commissionConfig)
    {
        List<CommissionConfig> list = commissionConfigService.selectCommissionConfigList(commissionConfig);
        ExcelUtil<CommissionConfig> util = new ExcelUtil<CommissionConfig>(CommissionConfig.class);
        util.exportExcel(response, list, "返佣配置数据");
    }

    /**
     * 获取返佣配置详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:commissionConfig:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(commissionConfigService.selectCommissionConfigById(id));
    }

    /**
     * 新增返佣配置
     */
    @PreAuthorize("@ss.hasPermi('system:commissionConfig:add')")
    @Log(title = "返佣配置", businessType = BusinessType.INSERT)
    @RepeatSubmit
    @PostMapping
    public AjaxResult add(@RequestBody CommissionConfig commissionConfig)
    {
        return toAjax(commissionConfigService.insertCommissionConfig(commissionConfig));
    }

    /**
     * 修改返佣配置
     */
    @PreAuthorize("@ss.hasPermi('system:commissionConfig:edit')")
    @Log(title = "返佣配置", businessType = BusinessType.UPDATE)
    @RepeatSubmit
    @PutMapping
    public AjaxResult edit(@RequestBody CommissionConfig commissionConfig)
    {
        return toAjax(commissionConfigService.updateCommissionConfig(commissionConfig));
    }

    /**
     * 删除返佣配置
     */
    @PreAuthorize("@ss.hasPermi('system:commissionConfig:remove')")
    @Log(title = "返佣配置", businessType = BusinessType.DELETE)
    @RepeatSubmit
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(commissionConfigService.deleteCommissionConfigByIds(ids));
    }
}
