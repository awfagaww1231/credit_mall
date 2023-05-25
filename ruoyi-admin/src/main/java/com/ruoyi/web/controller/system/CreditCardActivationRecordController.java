package com.ruoyi.web.controller.system;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.annotation.RepeatSubmit;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.CreditCardActivationRecord;
import com.ruoyi.system.service.ICreditCardActivationRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 用户额度激活记录Controller
 * 
 * @author ruoyi
 * @date 2023-03-20
 */
@RestController
@RequestMapping("/system/creditCardActivationRecord")
public class CreditCardActivationRecordController extends BaseController
{
    @Autowired
    private ICreditCardActivationRecordService creditCardActivationRecordService;

    /**
     * 查询用户额度激活记录列表
     */
    @PreAuthorize("@ss.hasPermi('system:creditCardActivationRecord:list')")
    @GetMapping("/list")
    public TableDataInfo list(CreditCardActivationRecord creditCardActivationRecord)
    {
        startPage();
        List<CreditCardActivationRecord> list = creditCardActivationRecordService.selectCreditCardActivationRecordList(creditCardActivationRecord);
        return getDataTable(list);
    }

    /**
     * 导出用户额度激活记录列表
     */
    @PreAuthorize("@ss.hasPermi('system:creditCardActivationRecord:export')")
    @Log(title = "用户额度激活记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CreditCardActivationRecord creditCardActivationRecord)
    {
        List<CreditCardActivationRecord> list = creditCardActivationRecordService.selectCreditCardActivationRecordList(creditCardActivationRecord);
        ExcelUtil<CreditCardActivationRecord> util = new ExcelUtil<CreditCardActivationRecord>(CreditCardActivationRecord.class);
        util.exportExcel(response, list, "用户额度激活记录数据");
    }

    /**
     * 获取用户额度激活记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:creditCardActivationRecord:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(creditCardActivationRecordService.selectCreditCardActivationRecordById(id));
    }

    /**
     * 新增用户额度激活记录
     */
    @PreAuthorize("@ss.hasPermi('system:creditCardActivationRecord:add')")
    @Log(title = "用户额度激活记录", businessType = BusinessType.INSERT)
    @RepeatSubmit
    @PostMapping
    public AjaxResult add(@RequestBody CreditCardActivationRecord creditCardActivationRecord)
    {
        return toAjax(creditCardActivationRecordService.insertCreditCardActivationRecord(creditCardActivationRecord));
    }

    /**
     * 修改用户额度激活记录
     */
    @PreAuthorize("@ss.hasPermi('system:creditCardActivationRecord:edit')")
    @Log(title = "用户额度激活记录", businessType = BusinessType.UPDATE)
    @RepeatSubmit
    @PutMapping
    public AjaxResult edit(@RequestBody CreditCardActivationRecord creditCardActivationRecord)
    {
        return toAjax(creditCardActivationRecordService.updateCreditCardActivationRecord(creditCardActivationRecord));
    }

    /**
     * 删除用户额度激活记录
     */
    @PreAuthorize("@ss.hasPermi('system:creditCardActivationRecord:remove')")
    @Log(title = "用户额度激活记录", businessType = BusinessType.DELETE)
    @RepeatSubmit
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return creditCardActivationRecordService.deleteCreditCardActivationRecordByIds(ids);
    }

    /**
     * 用户信用额度激活审核通过
     */
    @PreAuthorize("@ss.hasPermi('system:creditCardActivationRecord:agree')")
    @Log(title = "用户信用额度激活审核通过", businessType = BusinessType.UPDATE)
    @RepeatSubmit
    @GetMapping("/agree")
    public AjaxResult agree(Long id)
    {
        return creditCardActivationRecordService.agree(id);
    }

    /**
     * 用户信用额度激活审核拒绝
     */
    @PreAuthorize("@ss.hasPermi('system:creditCardActivationRecord:reject')")
    @Log(title = "用户信用额度激活审核拒绝", businessType = BusinessType.UPDATE)
    @RepeatSubmit
    @GetMapping("/reject")
    public AjaxResult reject(Long id,String remark)
    {
        if (StringUtils.isEmpty(remark)){
            return AjaxResult.error("请输入激活失败的原因");
        }
        return creditCardActivationRecordService.reject(id,remark);
    }
}
