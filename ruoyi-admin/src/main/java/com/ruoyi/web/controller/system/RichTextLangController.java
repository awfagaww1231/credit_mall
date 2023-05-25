package com.ruoyi.web.controller.system;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.annotation.RepeatSubmit;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.RichTextLang;
import com.ruoyi.system.service.IRichTextLangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * 系统app所有富文本多语言内容Controller
 * 
 * @author ruoyi
 * @date 2023-03-26
 */
@RestController
@RequestMapping("/system/richTextLang")
public class RichTextLangController extends BaseController
{
    @Autowired
    private IRichTextLangService richTextLangService;

    /**
     * 查询系统app所有富文本多语言内容列表
     */
    @PreAuthorize("@ss.hasPermi('system:richTextLang:list')")
    @GetMapping("/list")
    public TableDataInfo list(RichTextLang richTextLang) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        startPage();
        List<RichTextLang> list = richTextLangService.selectRichTextLangList(richTextLang);
        return getDataTable(list);
    }

    /**
     * 获取系统app所有富文本多语言内容详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:richTextLang:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(richTextLangService.selectRichTextLangById(id));
    }

//    /**
//     * 新增系统app所有富文本多语言内容
//     */
//    @PreAuthorize("@ss.hasPermi('system:richTextLang:add')")
//    @Log(title = "系统app所有富文本多语言内容", businessType = BusinessType.INSERT)
//    @PostMapping
//    public AjaxResult add(@RequestBody RichTextLang richTextLang)
//    {
//        if (StringUtils.isEmpty(richTextLang.getContent())){
//            return AjaxResult.error("请输入中文内容");
//        }
//        if (StringUtils.isNull(richTextLang.getTextType())){
//            return AjaxResult.error("请选择文本内容");
//        }
//        return richTextLangService.insertRichTextLang(richTextLang);
//    }

    /**
     * 修改系统app所有富文本多语言内容
     */
    @PreAuthorize("@ss.hasPermi('system:richTextLang:edit')")
    @Log(title = "系统app所有富文本多语言内容", businessType = BusinessType.UPDATE)
    @RepeatSubmit
    @PutMapping
    public AjaxResult edit(@RequestBody RichTextLang richTextLang)
    {
        if (richTextLang.getId() == null){
            return AjaxResult.error("请选择需要修改的选项");
        }
        return richTextLangService.updateRichTextLang(richTextLang);
    }

//    /**
//     * 删除系统app所有富文本多语言内容
//     */
//    @PreAuthorize("@ss.hasPermi('system:richTextLang:remove')")
//    @Log(title = "系统app所有富文本多语言内容", businessType = BusinessType.DELETE)
//	@DeleteMapping("/{ids}")
//    public AjaxResult remove(@PathVariable Long[] ids)
//    {
//        return toAjax(richTextLangService.deleteRichTextLangByIds(ids));
//    }
}
