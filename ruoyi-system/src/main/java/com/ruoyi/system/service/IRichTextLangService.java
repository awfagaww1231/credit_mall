package com.ruoyi.system.service;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.system.domain.RichTextLang;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * 系统app所有富文本多语言内容Service接口
 * 
 * @author ruoyi
 * @date 2023-03-26
 */
public interface IRichTextLangService 
{
    /**
     * 查询系统app所有富文本多语言内容
     * 
     * @param id 系统app所有富文本多语言内容主键
     * @return 系统app所有富文本多语言内容
     */
    public RichTextLang selectRichTextLangById(Long id);

    /**
     * 查询系统app所有富文本多语言内容列表
     * 
     * @param richTextLang 系统app所有富文本多语言内容
     * @return 系统app所有富文本多语言内容集合
     */
    public List<RichTextLang> selectRichTextLangList(RichTextLang richTextLang) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException;

    /**
     * 新增系统app所有富文本多语言内容
     * 
     * @param richTextLang 系统app所有富文本多语言内容
     * @return 结果
     */
    public AjaxResult insertRichTextLang(RichTextLang richTextLang);

    /**
     * 修改系统app所有富文本多语言内容
     * 
     * @param richTextLang 系统app所有富文本多语言内容
     * @return 结果
     */
    public AjaxResult updateRichTextLang(RichTextLang richTextLang);

    /**
     * 批量删除系统app所有富文本多语言内容
     * 
     * @param ids 需要删除的系统app所有富文本多语言内容主键集合
     * @return 结果
     */
    public int deleteRichTextLangByIds(Long[] ids);

    /**
     * 删除系统app所有富文本多语言内容信息
     * 
     * @param id 系统app所有富文本多语言内容主键
     * @return 结果
     */
    public int deleteRichTextLangById(Long id);
}
