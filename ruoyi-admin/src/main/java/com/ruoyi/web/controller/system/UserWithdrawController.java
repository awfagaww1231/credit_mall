package com.ruoyi.web.controller.system;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.annotation.RepeatSubmit;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.UserWithdraw;
import com.ruoyi.system.service.IUserWithdrawService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 提现记录Controller
 * 
 * @author ruoyi
 * @date 2023-02-17
 */
@RestController
@RequestMapping("/system/userWithdraw")
public class UserWithdrawController extends BaseController
{
    @Autowired
    private IUserWithdrawService userWithdrawService;

    /**
     * 查询提现记录列表
     */
    @PreAuthorize("@ss.hasPermi('system:userWithdraw:list')")
    @GetMapping("/list")
    public TableDataInfo list(UserWithdraw userWithdraw)
    {
        startPage();
        List<UserWithdraw> list = userWithdrawService.selectUserWithdrawList(userWithdraw);
        return getDataTable(list);
    }

    /**
     * 导出提现记录列表
     */
    @PreAuthorize("@ss.hasPermi('system:userWithdraw:export')")
    @Log(title = "提现记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, UserWithdraw userWithdraw)
    {
        List<UserWithdraw> list = userWithdrawService.selectUserWithdrawList(userWithdraw);
        ExcelUtil<UserWithdraw> util = new ExcelUtil<UserWithdraw>(UserWithdraw.class);
        util.exportExcel(response, list, "提现记录数据");
    }

    /**
     * 获取提现记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:userWithdraw:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(userWithdrawService.selectUserWithdrawById(id));
    }

//    /**
//     * 新增提现记录
//     */
//    @PreAuthorize("@ss.hasPermi('system:userWithdraw:add')")
//    @Log(title = "提现记录", businessType = BusinessType.INSERT)
//    @PostMapping
//    public AjaxResult add(@RequestBody UserWithdraw userWithdraw)
//    {
//        return toAjax(userWithdrawService.insertUserWithdraw(userWithdraw));
//    }
//
//    /**
//     * 修改提现记录
//     */
//    @PreAuthorize("@ss.hasPermi('system:userWithdraw:edit')")
//    @Log(title = "提现记录", businessType = BusinessType.UPDATE)
//    @PutMapping
//    public AjaxResult edit(@RequestBody UserWithdraw userWithdraw)
//    {
//        return toAjax(userWithdrawService.updateUserWithdraw(userWithdraw));
//    }
//
//    /**
//     * 删除提现记录
//     */
//    @PreAuthorize("@ss.hasPermi('system:userWithdraw:remove')")
//    @Log(title = "提现记录", businessType = BusinessType.DELETE)
//	@DeleteMapping("/{ids}")
//    public AjaxResult remove(@PathVariable Long[] ids)
//    {
//        return toAjax(userWithdrawService.deleteUserWithdrawByIds(ids));
//    }

    /**
     * 通过提现申请
     */
    @GetMapping("/agree")
    @Log(title = "通过提现申请", businessType = BusinessType.UPDATE)
    @PreAuthorize("@ss.hasPermi('system:withdraw:updateStatus')")
    @RepeatSubmit
    public AjaxResult agree(Long id) throws Exception {
        return userWithdrawService.agree(id);
    }

    /**
     * 驳回提现申请
     */
    @GetMapping("/reject")
    @Log(title = "驳回提现申请", businessType = BusinessType.UPDATE)
    @PreAuthorize("@ss.hasPermi('system:withdraw:updateStatus')")
    @RepeatSubmit
    public AjaxResult reject(Long id) throws Exception {
        return userWithdrawService.reject(id);
    }
}
