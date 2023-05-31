package com.ruoyi.web.controller.app;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.redis.RedisCache;
import com.ruoyi.system.domain.Language;
import com.ruoyi.system.service.ILanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Properties;

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
        FileInputStream fileInputStream = null;
        String url = "ruoyi-admin/src/main/resources/lang/"+lang+".properties";
        try {
            Properties properties = new Properties();
            fileInputStream = new FileInputStream(url);
            properties.load(fileInputStream);
//            props = new Props("lang/"+lang+".properties", StandardCharsets.UTF_8);
            return AjaxResult.success(properties);
        } catch (Exception e) {
            //如果出现异常则用redis的缓存语言包
            Map<String, Object> cacheMap = redisCache.getCacheMap("appLangMgrs/" + lang);
            return AjaxResult.success(cacheMap);
        }finally {
            if (fileInputStream != null){
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    System.out.println(url+"IO流读取后关闭异常");
                }
            }
        }
    }
}
