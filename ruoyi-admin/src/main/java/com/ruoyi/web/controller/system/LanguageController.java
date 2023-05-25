package com.ruoyi.web.controller.system;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.annotation.RepeatSubmit;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.Language;
import com.ruoyi.system.service.ILanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 国家语言Controller
 * 
 * @author ruoyi
 * @date 2022-11-17
 */
@RestController
@RequestMapping("/system/language")
public class LanguageController extends BaseController
{
    @Autowired
    private ILanguageService languageService;

    /**
     * 查询国家语言列表
     */
    @PreAuthorize("@ss.hasPermi('system:language:list')")
    @GetMapping("/list")
    public TableDataInfo list(Language language)
    {
        startPage();
        List<Language> list = languageService.selectLanguageList(language);
        return getDataTable(list);
    }

    /**
     * 导出国家语言列表
     */
    @PreAuthorize("@ss.hasPermi('system:language:export')")
    @Log(title = "国家语言", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Language language)
    {
        List<Language> list = languageService.selectLanguageList(language);
        ExcelUtil<Language> util = new ExcelUtil<Language>(Language.class);
        util.exportExcel(response, list, "国家语言数据");
    }

    /**
     * 获取国家语言详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:language:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(languageService.selectLanguageById(id));
    }

    /**
     * 新增国家语言
     */
    @PreAuthorize("@ss.hasPermi('system:language:add')")
    @Log(title = "国家语言", businessType = BusinessType.INSERT)
    @RepeatSubmit
    @PostMapping
    public AjaxResult add(@RequestBody Language language)
    {
        return toAjax(languageService.insertLanguage(language));
    }

    /**
     * 修改国家语言
     */
    @PreAuthorize("@ss.hasPermi('system:language:edit')")
    @Log(title = "国家语言", businessType = BusinessType.UPDATE)
    @RepeatSubmit
    @PutMapping
    public AjaxResult edit(@RequestBody Language language)
    {
        return toAjax(languageService.updateLanguage(language));
    }

    /**
     * 删除国家语言
     */
    @PreAuthorize("@ss.hasPermi('system:language:remove')")
    @Log(title = "国家语言", businessType = BusinessType.DELETE)
    @RepeatSubmit
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(languageService.deleteLanguageByIds(ids));
    }

    /**
     * 禁用启用语言
     */
    @GetMapping("/changeLanguageStatus")
    @PreAuthorize("@ss.hasPermi('system:language:changeLanguageStatus')")
    public AjaxResult changeLanguageStatus(Long id){
        if (id == null){
            return AjaxResult.error("未取得用户信息");
        }
        return languageService.changeLanguageStatus(id);
    }

    /**
     * 查询启用语言
     */
    @GetMapping("/getEnableList")
    public AjaxResult getEnableList(Language language)
    {
        language.setStatus(0);
        List<Language> list = languageService.selectLanguageList(language);
        return AjaxResult.success(list);
    }
}
