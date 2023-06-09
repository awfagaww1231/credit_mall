package com.ruoyi.common.utils.x.lang;


import com.ruoyi.common.constant.LangMgr;
import com.ruoyi.common.core.redis.RedisCache;
import com.ruoyi.common.utils.ServletUtils;
import com.ruoyi.common.utils.StringUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

@Component
public class LangUtils {

    @Autowired
    private RedisCache redisCache;

    public static LangUtils langUtils;

    @PostConstruct
    public void init(){
        langUtils = this;
    }
    //获取多语言标识
    public static String getLang(){
        HttpServletRequest request = ServletUtils.getRequest();
        String lang = request.getHeader("langType");
        if (StringUtils.isBlank(lang)){
            return "zh";
        }
        return lang;
    }

    //获取多语言id
    public static Long getLanguageId(){
        HttpServletRequest request = ServletUtils.getRequest();
        String lang = request.getHeader("languageId");
        if (StringUtils.isEmpty(lang)){
            return 1L;
        }
        Long languageId = 1L;
        try {
            languageId = Long.valueOf(lang);
        }catch (Exception e){
            return languageId;
        }
        return languageId;
    }

    //第一位大写
    public static String toUpperCaseFirstIndex(String lang){
        return lang.substring(0, 1).toUpperCase() + lang.substring(1);
    }

//    //根据key获取语言包
//    public static LangMgr getLangMgrVo(String hKey){
//        try {
//            Object langMgrRedisCache = langUtils.getRedisCache().getCacheMapValue("langMgrs", hKey);
//            LangMgr langMgr = new LangMgr();
//            BeanUtils.copyProperties(langMgr,langMgrRedisCache);
//            return langMgr;
//        }catch (Exception e){
//            return null;
//        }
//    }

    //根据key获取语言值
    public static String getLangMgrValue(String hKey){
        Object langMgrRedisCache = langUtils.getRedisCache().getCacheMapValue("langMgrs", hKey);
        if (StringUtils.isNotNull(langMgrRedisCache)){
            LangMgr langMgr = new LangMgr();
            Object value = null;
            try {
                BeanUtils.copyProperties(langMgr,langMgrRedisCache);
                value = PropertyUtils.describe(langMgr).get(getLang());
            } catch (Exception ex) {
                return "";
            }
            if (StringUtils.isNotNull(value)){
                return String.valueOf(value);
            }
        }
        return "";
    }

    public RedisCache getRedisCache() {
        return redisCache;
    }

    public void setRedisCache(RedisCache redisCache) {
        this.redisCache = redisCache;
    }
}
