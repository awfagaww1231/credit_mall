package com.ruoyi.web.controller.system;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.UserBillDetails;
import com.ruoyi.system.service.IUserBillDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * C端用户账单明细Controller
 * 
 * @author ruoyi
 * @date 2022-11-10
 */
@RestController
@RequestMapping("/system/userBillDetails")
public class UserBillDetailsController extends BaseController
{
    @Autowired
    private IUserBillDetailsService userBillDetailsService;

    /**
     * 查询C端用户账单明细列表
     */
    @PreAuthorize("@ss.hasPermi('system:userBillDetails:list')")
    @GetMapping("/list")
    public TableDataInfo list(UserBillDetails userBillDetails)
    {
        startPage();
        List<UserBillDetails> list = userBillDetailsService.selectUserBillDetailsList(userBillDetails);
        return getDataTable(list);
    }

    /**
     * 导出C端用户账单明细列表
     */
    @PreAuthorize("@ss.hasPermi('system:userBillDetails:export')")
    @Log(title = "C端用户账单明细", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, UserBillDetails userBillDetails)
    {
        List<UserBillDetails> list = userBillDetailsService.selectUserBillDetailsList(userBillDetails);
        ExcelUtil<UserBillDetails> util = new ExcelUtil<UserBillDetails>(UserBillDetails.class);
        util.exportExcel(response, list, "C端用户账单明细数据");
    }

    /**
     * 获取C端用户账单明细详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:userBillDetails:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(userBillDetailsService.selectUserBillDetailsById(id));
    }

    /**
     * 新增C端用户账单明细
     */
    @PreAuthorize("@ss.hasPermi('system:userBillDetails:add')")
    @Log(title = "C端用户账单明细", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody UserBillDetails userBillDetails)
    {
        return toAjax(userBillDetailsService.insertUserBillDetails(userBillDetails));
    }

    /**
     * 修改C端用户账单明细
     */
    @PreAuthorize("@ss.hasPermi('system:userBillDetails:edit')")
    @Log(title = "C端用户账单明细", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody UserBillDetails userBillDetails)
    {
        return toAjax(userBillDetailsService.updateUserBillDetails(userBillDetails));
    }

    /**
     * 删除C端用户账单明细
     */
    @PreAuthorize("@ss.hasPermi('system:userBillDetails:remove')")
    @Log(title = "C端用户账单明细", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(userBillDetailsService.deleteUserBillDetailsByIds(ids));
    }
}
