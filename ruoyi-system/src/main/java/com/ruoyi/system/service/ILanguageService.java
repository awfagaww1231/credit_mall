package com.ruoyi.system.service;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.system.domain.Language;

import java.util.List;

/**
 * 国家语言Service接口
 * 
 * @author ruoyi
 * @date 2022-11-17
 */
public interface ILanguageService 
{
    /**
     * 查询国家语言
     * 
     * @param id 国家语言主键
     * @return 国家语言
     */
    public Language selectLanguageById(Long id);

    /**
     * 查询国家语言列表
     * 
     * @param language 国家语言
     * @return 国家语言集合
     */
    public List<Language> selectLanguageList(Language language);

    /**
     * 新增国家语言
     * 
     * @param language 国家语言
     * @return 结果
     */
    public int insertLanguage(Language language);

    /**
     * 修改国家语言
     * 
     * @param language 国家语言
     * @return 结果
     */
    public int updateLanguage(Language language);

    /**
     * 批量删除国家语言
     * 
     * @param ids 需要删除的国家语言主键集合
     * @return 结果
     */
    public int deleteLanguageByIds(Long[] ids);

    /**
     * 删除国家语言信息
     * 
     * @param id 国家语言主键
     * @return 结果
     */
    public int deleteLanguageById(Long id);

    /**
     * 禁用启用语言
     */
    AjaxResult changeLanguageStatus(Long id);
}
