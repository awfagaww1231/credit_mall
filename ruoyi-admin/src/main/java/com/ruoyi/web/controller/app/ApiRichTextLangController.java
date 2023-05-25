package com.ruoyi.web.controller.app;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.RichTextLang;
import com.ruoyi.system.service.IRichTextLangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.InvocationTargetException;

/**
 * app所有富文本多语言内容Controller
 * 
 * @author ruoyi
 * @date 2023-03-26
 */
@RestController
@RequestMapping("/api/richTextLang")
public class ApiRichTextLangController extends BaseController
{
    @Autowired
    private IRichTextLangService richTextLangService;

    /**
     * 查询系统app所有富文本多语言内容列表
     */
    @GetMapping("/list")
    public AjaxResult list(RichTextLang richTextLang) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        if (StringUtils.isNull(richTextLang.getTextType())){
            return AjaxResult.error("请选择文本类型","hint_36");
        }
        richTextLang.setIsLang(0);
        return AjaxResult.success(richTextLangService.selectRichTextLangList(richTextLang));
    }
}
