package com.ruoyi.system.service.impl;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.Language;
import com.ruoyi.system.mapper.LanguageMapper;
import com.ruoyi.system.service.ILanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 国家语言Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-11-17
 */
@Service
public class LanguageServiceImpl implements ILanguageService 
{
    @Autowired
    private LanguageMapper languageMapper;

    /**
     * 查询国家语言
     * 
     * @param id 国家语言主键
     * @return 国家语言
     */
    @Override
    public Language selectLanguageById(Long id)
    {
        return languageMapper.selectLanguageById(id);
    }

    /**
     * 查询国家语言列表
     * 
     * @param language 国家语言
     * @return 国家语言
     */
    @Override
    public List<Language> selectLanguageList(Language language)
    {
        return languageMapper.selectLanguageList(language);
    }

    /**
     * 新增国家语言
     * 
     * @param language 国家语言
     * @return 结果
     */
    @Override
    public int insertLanguage(Language language)
    {
        return languageMapper.insertLanguage(language);
    }

    /**
     * 修改国家语言
     * 
     * @param language 国家语言
     * @return 结果
     */
    @Override
    public int updateLanguage(Language language)
    {
        return languageMapper.updateLanguage(language);
    }

    /**
     * 批量删除国家语言
     * 
     * @param ids 需要删除的国家语言主键
     * @return 结果
     */
    @Override
    public int deleteLanguageByIds(Long[] ids)
    {
        return languageMapper.deleteLanguageByIds(ids);
    }

    /**
     * 删除国家语言信息
     * 
     * @param id 国家语言主键
     * @return 结果
     */
    @Override
    public int deleteLanguageById(Long id)
    {
        return languageMapper.deleteLanguageById(id);
    }

        @Override
    public AjaxResult changeLanguageStatus(Long id) {
            Language languageVo = languageMapper.selectLanguageById(id);
            if (StringUtils.isNull(languageVo)){
                return AjaxResult.error("出错啦，请刷新页面后重新尝试");
            }
            if (languageVo.getId() == 1 | languageVo.getId() == 2 | languageVo.getId() == 3){
                return AjaxResult.error("此语种不允许禁用");
            }

            //如果原先开着
            if (languageVo.getStatus() == 0){
                languageVo.setStatus(1);
            }else {
                languageVo.setStatus(0);
            }
            int i = languageMapper.updateLanguage(languageVo);
            if (i > 0){
                return AjaxResult.success();
            }else {
                return AjaxResult.error("出错啦，请刷新页面后重新尝试");
            }
        }

}
