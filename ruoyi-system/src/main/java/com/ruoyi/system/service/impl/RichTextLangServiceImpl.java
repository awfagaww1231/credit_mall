package com.ruoyi.system.service.impl;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.x.lang.LangUtils;
import com.ruoyi.system.domain.RichTextLang;
import com.ruoyi.system.mapper.RichTextLangMapper;
import com.ruoyi.system.service.IRichTextLangService;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * 系统app所有富文本多语言内容Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-03-26
 */
@Service
public class RichTextLangServiceImpl implements IRichTextLangService 
{
    @Autowired
    private RichTextLangMapper richTextLangMapper;

    /**
     * 查询系统app所有富文本多语言内容
     * 
     * @param id 系统app所有富文本多语言内容主键
     * @return 系统app所有富文本多语言内容
     */
    @Override
    public RichTextLang selectRichTextLangById(Long id)
    {
        return richTextLangMapper.selectRichTextLangById(id);
    }

    /**
     * 查询系统app所有富文本多语言内容列表
     * 
     * @param richTextLang 系统app所有富文本多语言内容
     * @return 系统app所有富文本多语言内容
     */
    @Override
    public List<RichTextLang> selectRichTextLangList(RichTextLang richTextLang) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        List<RichTextLang> richTextLangs = richTextLangMapper.selectRichTextLangList(richTextLang);
        //取多语言值
        if (richTextLang.getIsLang() != null){
            //语言
            String lang = LangUtils.getLang();
            if (!lang.equals("zh")){
                lang = LangUtils.toUpperCaseFirstIndex(lang);
                for (int i = 0; i < richTextLangs.size(); i++) {
                    richTextLang = richTextLangs.get(i);
                    Object o = PropertyUtils.describe(richTextLang).get("content" + lang);
                    String contentLangValue = "";
                    if (StringUtils.isNotNull(o)){
                        contentLangValue = o.toString();
                    }
                    richTextLang.setContent(contentLangValue);
                }
            }
        }
        return richTextLangs;
    }

    /**
     * 新增系统app所有富文本多语言内容
     * 
     * @param richTextLang 系统app所有富文本多语言内容
     * @return 结果
     */
    @Override
    public AjaxResult insertRichTextLang(RichTextLang richTextLang)
    {
        RichTextLang richTextLangVo = new RichTextLang();
        richTextLangVo.setTextType(richTextLang.getTextType());
        List<RichTextLang> richTextLangs = richTextLangMapper.selectRichTextLangList(richTextLangVo);
        if (richTextLangs.size() > 0){
            return AjaxResult.error("该类型的内容已存在");
        }
        int count = richTextLangMapper.insertRichTextLang(richTextLang);
        if (count <= 0){
            return AjaxResult.error();
        }
        return AjaxResult.success();
    }

    /**
     * 修改系统app所有富文本多语言内容
     * 
     * @param richTextLang 系统app所有富文本多语言内容
     * @return 结果
     */
    @Override
    public AjaxResult updateRichTextLang(RichTextLang richTextLang)
    {
        RichTextLang richTextLangVo = new RichTextLang();
        richTextLangVo.setTextType(richTextLang.getTextType());
        List<RichTextLang> richTextLangs = richTextLangMapper.selectRichTextLangList(richTextLangVo);
        if (richTextLangs.size() > 0){
            if (!richTextLangs.get(0).getId().equals(richTextLang.getId())){
                return AjaxResult.error("该类型的内容已存在");
            }
        }
        int count = richTextLangMapper.updateRichTextLang(richTextLang);
        if (count <= 0){
            return AjaxResult.error();
        }
        return AjaxResult.success();
    }

    /**
     * 批量删除系统app所有富文本多语言内容
     * 
     * @param ids 需要删除的系统app所有富文本多语言内容主键
     * @return 结果
     */
    @Override
    public int deleteRichTextLangByIds(Long[] ids)
    {
        return richTextLangMapper.deleteRichTextLangByIds(ids);
    }

    /**
     * 删除系统app所有富文本多语言内容信息
     * 
     * @param id 系统app所有富文本多语言内容主键
     * @return 结果
     */
    @Override
    public int deleteRichTextLangById(Long id)
    {
        return richTextLangMapper.deleteRichTextLangById(id);
    }
}
