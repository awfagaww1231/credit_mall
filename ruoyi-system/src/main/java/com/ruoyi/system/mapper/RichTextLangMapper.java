package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.RichTextLang;

import java.util.List;

/**
 * 系统app所有富文本多语言内容Mapper接口
 * 
 * @author ruoyi
 * @date 2023-03-26
 */
public interface RichTextLangMapper 
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
    public List<RichTextLang> selectRichTextLangList(RichTextLang richTextLang);

    /**
     * 新增系统app所有富文本多语言内容
     * 
     * @param richTextLang 系统app所有富文本多语言内容
     * @return 结果
     */
    public int insertRichTextLang(RichTextLang richTextLang);

    /**
     * 修改系统app所有富文本多语言内容
     * 
     * @param richTextLang 系统app所有富文本多语言内容
     * @return 结果
     */
    public int updateRichTextLang(RichTextLang richTextLang);

    /**
     * 删除系统app所有富文本多语言内容
     * 
     * @param id 系统app所有富文本多语言内容主键
     * @return 结果
     */
    public int deleteRichTextLangById(Long id);

    /**
     * 批量删除系统app所有富文本多语言内容
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteRichTextLangByIds(Long[] ids);
}
