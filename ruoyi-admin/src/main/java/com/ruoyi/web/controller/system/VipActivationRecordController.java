package com.ruoyi.web.controller.system;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.annotation.RepeatSubmit;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.VipActivationRecord;
import com.ruoyi.system.service.IVipActivationRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * vip权限激活记录Controller
 * 
 * @author ruoyi
 * @date 2023-04-19
 */
@RestController
@RequestMapping("/system/vipActivationRecord")
public class VipActivationRecordController extends BaseController
{
    @Autowired
    private IVipActivationRecordService vipActivationRecordService;

    /**
     * 查询vip权限激活记录列表
     */
    @PreAuthorize("@ss.hasPermi('system:vipActivationRecord:list')")
    @GetMapping("/list")
    public TableDataInfo list(VipActivationRecord vipActivationRecord)
    {
        startPage();
        List<VipActivationRecord> list = vipActivationRecordService.selectVipActivationRecordList(vipActivationRecord);
        return getDataTable(list);
    }

    /**
     * 导出vip权限激活记录列表
     */
    @PreAuthorize("@ss.hasPermi('system:vipActivationRecord:export')")
    @Log(title = "vip权限激活记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, VipActivationRecord vipActivationRecord)
    {
        List<VipActivationRecord> list = vipActivationRecordService.selectVipActivationRecordList(vipActivationRecord);
        ExcelUtil<VipActivationRecord> util = new ExcelUtil<VipActivationRecord>(VipActivationRecord.class);
        util.exportExcel(response, list, "vip权限激活记录数据");
    }

    /**
     * 获取vip权限激活记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:vipActivationRecord:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(vipActivationRecordService.selectVipActivationRecordById(id));
    }

    /**
     * 新增vip权限激活记录
     */
    @PreAuthorize("@ss.hasPermi('system:vipActivationRecord:add')")
    @Log(title = "vip权限激活记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody VipActivationRecord vipActivationRecord)
    {
        return toAjax(vipActivationRecordService.insertVipActivationRecord(vipActivationRecord));
    }

    /**
     * 修改vip权限激活记录
     */
    @PreAuthorize("@ss.hasPermi('system:vipActivationRecord:edit')")
    @Log(title = "vip权限激活记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody VipActivationRecord vipActivationRecord)
    {
        return toAjax(vipActivationRecordService.updateVipActivationRecord(vipActivationRecord));
    }

    /**
     * 删除vip权限激活记录
     */
    @PreAuthorize("@ss.hasPermi('system:vipActivationRecord:remove')")
    @Log(title = "vip权限激活记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(vipActivationRecordService.deleteVipActivationRecordByIds(ids));
    }

    /**
     * vip权限开通审核通过
     */
    @PreAuthorize("@ss.hasPermi('system:vipActivationRecord:agree')")
    @Log(title = "vip权限开通审核通过", businessType = BusinessType.UPDATE)
    @RepeatSubmit
    @GetMapping("/agree")
    public AjaxResult agree(Long id)
    {
        return vipActivationRecordService.agree(id);
    }

    /**
     * vip权限开通审核驳回
     */
    @PreAuthorize("@ss.hasPermi('system:vipActivationRecord:reject')")
    @Log(title = "vip权限开通审核驳回", businessType = BusinessType.UPDATE)
    @RepeatSubmit
    @GetMapping("/reject")
    public AjaxResult reject(Long id,String remark)
    {
        if (StringUtils.isEmpty(remark)){
            return AjaxResult.error("请输入开通失败的原因");
        }
        return vipActivationRecordService.reject(id,remark);
    }
}
