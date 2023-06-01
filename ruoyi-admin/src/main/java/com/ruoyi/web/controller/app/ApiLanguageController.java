package com.ruoyi.web.controller.app;

import cn.hutool.setting.dialect.Props;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.redis.RedisCache;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.x.lang.LangUtils;
import com.ruoyi.system.domain.LangMgr;
import com.ruoyi.system.domain.Language;
import com.ruoyi.system.service.ILangMgrService;
import com.ruoyi.system.service.ILanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
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

    @Resource
    private ILangMgrService langMgrService;

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
        String langMgrValue = LangUtils.getLangMgrValue("hint_1");
        if (StringUtils.isEmpty(lang)){
            lang = "zh";
        }
        //语言包存放文件夹路径
        String dirPath = System.getProperty("user.dir")+"\\lang\\";
        try {
            Props props = new Props(dirPath+lang+".properties", StandardCharsets.UTF_8);
            return AjaxResult.success(props);
        } catch (Exception e) {
//            //如果出现异常则用redis的缓存语言包
//            Map<String, Object> cacheMap = redisCache.getCacheMap("appLangMgrs/" + lang);
//            return AjaxResult.success(cacheMap);
            return AjaxResult.success();
        }
    }

    /**
     * 查询多语言包配置列表
     */
    @RequestMapping("/test")
    public AjaxResult test(String a) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        LangMgr langMgr = new LangMgr();
        langMgr.setId(658L);
        langMgr.setLangKey("customer_cName1199");
        langMgr.setTc(a);
        langMgrService.updateLangMgr(langMgr);
        return AjaxResult.success();
    }

    public static void main(String[] args) throws IOException {
        URL resource = Thread.currentThread().getContextClassLoader().getResource("lang/text.txt");
        String file1 = resource.getFile();
        File file = new File(file1);
        FileInputStream fileInputStream = new FileInputStream(file);
        FileOutputStream fileOutputStream = new FileOutputStream(file);

    }
}
