package com.ruoyi.web.controller.system;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.annotation.RepeatSubmit;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.LangMgr;
import com.ruoyi.system.service.ILangMgrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * 多语言配置包Controller
 * 
 * @author ruoyi
 * @date 2023-03-22
 */
@RestController
@RequestMapping("/system/langMgr")
public class LangMgrController extends BaseController
{
    @Autowired
    private ILangMgrService langMgrService;

    /**
     * 查询多语言配置包列表
     */
    @PreAuthorize("@ss.hasPermi('system:langMgr:list')")
    @GetMapping("/list")
    public TableDataInfo list(LangMgr langMgr)
    {
        startPage();
        List<LangMgr> list = langMgrService.selectLangMgrList(langMgr);
        return getDataTable(list);
    }

    /**
     * 导出多语言配置包列表
     */
    @PreAuthorize("@ss.hasPermi('system:langMgr:export')")
    @Log(title = "多语言配置包", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, LangMgr langMgr)
    {
        List<LangMgr> list = langMgrService.selectLangMgrList(langMgr);
        ExcelUtil<LangMgr> util = new ExcelUtil<LangMgr>(LangMgr.class);
        util.exportExcel(response, list, "多语言配置包数据");
    }

    /**
     * 获取多语言配置包详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:langMgr:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(langMgrService.selectLangMgrById(id));
    }

    /**
     * 新增多语言配置包
     */
    @PreAuthorize("@ss.hasPermi('system:langMgr:add')")
    @Log(title = "多语言配置包", businessType = BusinessType.INSERT)
    @RepeatSubmit
    @PostMapping
    public AjaxResult add(@RequestBody LangMgr langMgr) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        return langMgrService.insertLangMgr(langMgr);
    }

    /**
     * 修改多语言配置包
     */
    @PreAuthorize("@ss.hasPermi('system:langMgr:edit')")
    @Log(title = "多语言配置包", businessType = BusinessType.UPDATE)
    @RepeatSubmit
    @PutMapping
    public AjaxResult edit(@RequestBody LangMgr langMgr) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        return langMgrService.updateLangMgr(langMgr);
    }

    /**
     * 删除多语言配置包
     */
    @PreAuthorize("@ss.hasPermi('system:langMgr:remove')")
    @Log(title = "多语言配置包", businessType = BusinessType.DELETE)
    @RepeatSubmit
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        return toAjax(langMgrService.deleteLangMgrByIds(ids));
    }
}
