package com.ruoyi.web.controller.system;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.UserEvaluation;
import com.ruoyi.system.service.IUserEvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 用户评价信息Controller
 * 
 * @author ruoyi
 * @date 2022-11-07
 */
@RestController
@RequestMapping("/system/evaluation")
public class UserEvaluationController extends BaseController
{
    @Autowired
    private IUserEvaluationService userEvaluationService;

    /**
     * 查询用户评价信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:evaluation:list')")
    @GetMapping("/list")
    public TableDataInfo list(UserEvaluation userEvaluation)
    {
        startPage();
        List<UserEvaluation> list = userEvaluationService.selectUserEvaluationList(userEvaluation);
        return getDataTable(list);
    }

    /**
     * 导出用户评价信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:evaluation:export')")
    @Log(title = "用户评价信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, UserEvaluation userEvaluation)
    {
        List<UserEvaluation> list = userEvaluationService.selectUserEvaluationList(userEvaluation);
        ExcelUtil<UserEvaluation> util = new ExcelUtil<UserEvaluation>(UserEvaluation.class);
        util.exportExcel(response, list, "用户评价信息数据");
    }

    /**
     * 获取用户评价信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:evaluation:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(userEvaluationService.selectUserEvaluationById(id));
    }

    /**
     * 新增用户评价信息
     */
    @PreAuthorize("@ss.hasPermi('system:evaluation:add')")
    @Log(title = "用户评价信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody UserEvaluation userEvaluation)
    {
        return toAjax(userEvaluationService.insertUserEvaluation(userEvaluation));
    }

    /**
     * 修改用户评价信息
     */
    @PreAuthorize("@ss.hasPermi('system:evaluation:edit')")
    @Log(title = "用户评价信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody UserEvaluation userEvaluation)
    {
        return toAjax(userEvaluationService.updateUserEvaluation(userEvaluation));
    }

    /**
     * 删除用户评价信息
     */
    @PreAuthorize("@ss.hasPermi('system:evaluation:remove')")
    @Log(title = "用户评价信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(userEvaluationService.deleteUserEvaluationByIds(ids));
    }
}
