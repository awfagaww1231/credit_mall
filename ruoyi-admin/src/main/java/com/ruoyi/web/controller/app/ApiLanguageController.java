package com.ruoyi.web.controller.app;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.redis.RedisCache;
import com.ruoyi.system.domain.Language;
import com.ruoyi.system.service.ILanguageService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 国家语言Controller
 * 
 * @author ruoyi
 * @date 2022-11-17
 */
@RestController
@RequestMapping("/api/language")
public class ApiLanguageController extends BaseController
{
    @Autowired
    private ILanguageService languageService;

    @Autowired
    private RedisCache redisCache;

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

    /**
     * 查询多语言包配置列表
     */
    @RequestMapping("/getLangMgrs")
    public AjaxResult getLangMgrs(String lang) {
        if (StringUtils.isBlank(lang)){
            lang = "zh";
        }
        return AjaxResult.success(redisCache.getCacheMap("appLangMgrs/"+lang));
    }
}
