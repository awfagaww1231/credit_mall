package com.ruoyi.web.controller.system;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.annotation.RepeatSubmit;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.RechargeCommission;
import com.ruoyi.system.service.IRechargeCommissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 充值返佣记录Controller
 * 
 * @author ruoyi
 * @date 2022-11-14
 */
@RestController
@RequestMapping("/system/commission")
public class RechargeCommissionController extends BaseController
{
    @Autowired
    private IRechargeCommissionService rechargeCommissionService;

    /**
     * 查询充值返佣记录列表
     */
    @PreAuthorize("@ss.hasPermi('system:commission:list')")
    @GetMapping("/list")
    public TableDataInfo list(RechargeCommission rechargeCommission)
    {
        startPage();
        List<RechargeCommission> list = rechargeCommissionService.selectRechargeCommissionList(rechargeCommission);
        return getDataTable(list);
    }

    /**
     * 导出充值返佣记录列表
     */
    @PreAuthorize("@ss.hasPermi('system:commission:export')")
    @Log(title = "充值返佣记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, RechargeCommission rechargeCommission)
    {
        List<RechargeCommission> list = rechargeCommissionService.selectRechargeCommissionList(rechargeCommission);
        ExcelUtil<RechargeCommission> util = new ExcelUtil<RechargeCommission>(RechargeCommission.class);
        util.exportExcel(response, list, "充值返佣记录数据");
    }

    /**
     * 获取充值返佣记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:commission:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(rechargeCommissionService.selectRechargeCommissionById(id));
    }

    /**
     * 新增充值返佣记录
     */
    @PreAuthorize("@ss.hasPermi('system:commission:add')")
    @Log(title = "充值返佣记录", businessType = BusinessType.INSERT)
    @RepeatSubmit
    @PostMapping
    public AjaxResult add(@RequestBody RechargeCommission rechargeCommission)
    {
        return toAjax(rechargeCommissionService.insertRechargeCommission(rechargeCommission));
    }

    /**
     * 修改充值返佣记录
     */
    @PreAuthorize("@ss.hasPermi('system:commission:edit')")
    @Log(title = "充值返佣记录", businessType = BusinessType.UPDATE)
    @RepeatSubmit
    @PutMapping
    public AjaxResult edit(@RequestBody RechargeCommission rechargeCommission)
    {
        return toAjax(rechargeCommissionService.updateRechargeCommission(rechargeCommission));
    }

    /**
     * 删除充值返佣记录
     */
    @PreAuthorize("@ss.hasPermi('system:commission:remove')")
    @Log(title = "充值返佣记录", businessType = BusinessType.DELETE)
    @RepeatSubmit
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(rechargeCommissionService.deleteRechargeCommissionByIds(ids));
    }
}
