package com.ruoyi.web.controller.system;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.annotation.RepeatSubmit;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.LoanApplyInfo;
import com.ruoyi.system.service.ILoanApplyInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 贷款申请资料Controller
 * 
 * @author ruoyi
 * @date 2023-04-03
 */
@RestController
@RequestMapping("/system/loanApplyInfo")
public class LoanApplyInfoController extends BaseController
{
    @Autowired
    private ILoanApplyInfoService loanApplyInfoService;

    /**
     * 查询贷款申请资料列表
     */
    @PreAuthorize("@ss.hasPermi('system:loanApplyInfo:list')")
    @GetMapping("/list")
    public TableDataInfo list(LoanApplyInfo loanApplyInfo)
    {
        startPage();
        List<LoanApplyInfo> list = loanApplyInfoService.selectLoanApplyInfoList(loanApplyInfo);
        return getDataTable(list);
    }


    /**
     * 导出贷款申请资料列表
     */
    @PreAuthorize("@ss.hasPermi('system:loanApplyInfo:export')")
    @Log(title = "贷款申请资料", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, LoanApplyInfo loanApplyInfo)
    {
        List<LoanApplyInfo> list = loanApplyInfoService.selectLoanApplyInfoList(loanApplyInfo);
        ExcelUtil<LoanApplyInfo> util = new ExcelUtil<LoanApplyInfo>(LoanApplyInfo.class);
        util.exportExcel(response, list, "贷款申请资料数据");
    }

    /**
     * 获取贷款申请资料详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:loanApplyInfo:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(loanApplyInfoService.selectLoanApplyInfoById(id));
    }

    /**
     * 获取贷款申请资料详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:loanApplyInfo:getInfoByUserId')")
    @GetMapping(value = "/getInfoByUserId")
    public AjaxResult getInfoByUserId(Long userId)
    {
        return AjaxResult.success(loanApplyInfoService.selectLoanApplyInfoByUserId(userId));
    }

    /**
     * 新增贷款申请资料
     */
    @PreAuthorize("@ss.hasPermi('system:loanApplyInfo:add')")
    @Log(title = "贷款申请资料", businessType = BusinessType.INSERT)
    @RepeatSubmit
    @PostMapping
    public AjaxResult add(@RequestBody LoanApplyInfo loanApplyInfo)
    {
        return toAjax(loanApplyInfoService.insertLoanApplyInfo(loanApplyInfo));
    }

    /**
     * 修改贷款申请资料
     */
    @PreAuthorize("@ss.hasPermi('system:loanApplyInfo:edit')")
    @Log(title = "贷款申请资料", businessType = BusinessType.UPDATE)
    @RepeatSubmit
    @PutMapping
    public AjaxResult edit(@RequestBody LoanApplyInfo loanApplyInfo)
    {
        return toAjax(loanApplyInfoService.updateLoanApplyInfo(loanApplyInfo));
    }

    /**
     * 删除贷款申请资料
     */
    @PreAuthorize("@ss.hasPermi('system:loanApplyInfo:remove')")
    @Log(title = "贷款申请资料", businessType = BusinessType.DELETE)
    @RepeatSubmit
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(loanApplyInfoService.deleteLoanApplyInfoByIds(ids));
    }
}
