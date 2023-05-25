package com.ruoyi.web.controller.system;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.UserLoanBill;
import com.ruoyi.system.service.IUserLoanBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 用户贷款还款账单Controller
 * 
 * @author ruoyi
 * @date 2023-04-28
 */
@RestController
@RequestMapping("/system/bill")
public class UserLoanBillController extends BaseController
{
    @Autowired
    private IUserLoanBillService userLoanBillService;

    /**
     * 查询用户贷款还款账单列表
     */
    @PreAuthorize("@ss.hasPermi('system:bill:list')")
    @GetMapping("/list")
    public TableDataInfo list(UserLoanBill userLoanBill)
    {
        startPage();
        List<UserLoanBill> list = userLoanBillService.selectUserLoanBillList(userLoanBill);
        return getDataTable(list);
    }

    /**
     * 导出用户贷款还款账单列表
     */
    @PreAuthorize("@ss.hasPermi('system:bill:export')")
    @Log(title = "用户贷款还款账单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, UserLoanBill userLoanBill)
    {
        List<UserLoanBill> list = userLoanBillService.selectUserLoanBillList(userLoanBill);
        ExcelUtil<UserLoanBill> util = new ExcelUtil<UserLoanBill>(UserLoanBill.class);
        util.exportExcel(response, list, "用户贷款还款账单数据");
    }

    /**
     * 获取用户贷款还款账单详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:bill:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(userLoanBillService.selectUserLoanBillById(id));
    }

    /**
     * 新增用户贷款还款账单
     */
    @PreAuthorize("@ss.hasPermi('system:bill:add')")
    @Log(title = "用户贷款还款账单", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody UserLoanBill userLoanBill)
    {
        return toAjax(userLoanBillService.insertUserLoanBill(userLoanBill));
    }

    /**
     * 修改用户贷款还款账单
     */
    @PreAuthorize("@ss.hasPermi('system:bill:edit')")
    @Log(title = "用户贷款还款账单", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody UserLoanBill userLoanBill)
    {
        return toAjax(userLoanBillService.updateUserLoanBill(userLoanBill));
    }

    /**
     * 删除用户贷款还款账单
     */
    @PreAuthorize("@ss.hasPermi('system:bill:remove')")
    @Log(title = "用户贷款还款账单", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(userLoanBillService.deleteUserLoanBillByIds(ids));
    }
}
