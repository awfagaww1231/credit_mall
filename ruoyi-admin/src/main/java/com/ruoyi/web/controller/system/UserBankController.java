package com.ruoyi.web.controller.system;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.UserBank;
import com.ruoyi.system.service.IUserBankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 用户银行Controller
 * 
 * @author ruoyi
 * @date 2023-02-16
 */
@RestController
@RequestMapping("/system/userBank")
public class UserBankController extends BaseController
{
    @Autowired
    private IUserBankService userBankService;

    /**
     * 查询用户银行列表
     */
    @PreAuthorize("@ss.hasPermi('system:bank:list')")
    @GetMapping("/list")
    public TableDataInfo list(UserBank userBank)
    {
        startPage();
        List<UserBank> list = userBankService.selectUserBankList(userBank);
        return getDataTable(list);
    }

    /**
     * 导出用户银行列表
     */
    @PreAuthorize("@ss.hasPermi('system:bank:export')")
    @Log(title = "用户银行", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, UserBank userBank)
    {
        List<UserBank> list = userBankService.selectUserBankList(userBank);
        ExcelUtil<UserBank> util = new ExcelUtil<UserBank>(UserBank.class);
        util.exportExcel(response, list, "用户银行数据");
    }

    /**
     * 获取用户银行详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:bank:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(userBankService.selectUserBankById(id));
    }

    /**
     * 新增用户银行
     */
    @PreAuthorize("@ss.hasPermi('system:bank:add')")
    @Log(title = "用户银行", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody UserBank userBank)
    {
        return toAjax(userBankService.insertUserBank(userBank));
    }

    /**
     * 修改用户银行
     */
    @PreAuthorize("@ss.hasPermi('system:bank:edit')")
    @Log(title = "用户银行", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody UserBank userBank)
    {
        return toAjax(userBankService.updateUserBank(userBank));
    }

    /**
     * 删除用户银行
     */
    @PreAuthorize("@ss.hasPermi('system:bank:remove')")
    @Log(title = "用户银行", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(userBankService.deleteUserBankByIds(ids));
    }
}
