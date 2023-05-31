package com.ruoyi.system.service.impl;

import cn.hutool.json.JSONUtil;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.redis.RedisCache;
import com.ruoyi.system.domain.LangMgr;
import com.ruoyi.system.domain.Language;
import com.ruoyi.system.mapper.LangMgrMapper;
import com.ruoyi.system.mapper.LanguageMapper;
import com.ruoyi.system.service.ILangMgrService;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

/**
 * 多语言配置包Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-03-22
 */
@Service
public class LangMgrServiceImpl implements ILangMgrService 
{
    @Resource
    private LangMgrMapper langMgrMapper;

    @Autowired
    private RedisCache redisCache;

    @Resource
    private LanguageMapper languageMapper;

    /**
     * 查询多语言配置包
     * 
     * @param id 多语言配置包主键
     * @return 多语言配置包
     */
    @Override
    public LangMgr selectLangMgrById(Long id)
    {
        return langMgrMapper.selectLangMgrById(id);
    }

    /**
     * 查询多语言配置包列表
     * 
     * @param langMgr 多语言配置包
     * @return 多语言配置包
     */
    @Override
    public List<LangMgr> selectLangMgrList(LangMgr langMgr)
    {
        return langMgrMapper.selectLangMgrList(langMgr);
    }

    /**
     * 新增多语言配置包
     * 
     * @param langMgr 多语言配置包
     * @return 结果
     */
    @Override
    public AjaxResult insertLangMgr(LangMgr langMgr) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        LangMgr langMgrVo = new LangMgr();
        langMgrVo.setLangKey(langMgr.getLangKey());
        List<LangMgr> langMgrs = langMgrMapper.selectLangMgrList(langMgrVo);
        if (langMgrs.size() > 0){
            return AjaxResult.error("此key已存在");
        }
        int count = langMgrMapper.insertLangMgr(langMgr);
        if (count <= 0){
            return AjaxResult.error();
        }
        //重新加载语言包缓存
        reloadLangMgrCache();
        return AjaxResult.success();
    }

    /**
     * 修改多语言配置包
     * 
     * @param langMgr 多语言配置包
     * @return 结果
     */
    @Override
    public AjaxResult updateLangMgr(LangMgr langMgr) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        LangMgr langMgrVo = new LangMgr();
        langMgrVo.setLangKey(langMgr.getLangKey());
        List<LangMgr> langMgrs = langMgrMapper.selectLangMgrList(langMgrVo);
        if (langMgrs.size() > 0){
            if (!langMgrs.get(0).getId().equals(langMgr.getId())){
                return AjaxResult.error("此key已存在");
            }
        }
        int count = langMgrMapper.updateLangMgr(langMgr);
        if (count <= 0){
            return AjaxResult.error();
        }
        //重新加载语言包缓存
        reloadLangMgrCache();
        return AjaxResult.success();
    }

    /**
     * 批量删除多语言配置包
     * 
     * @param ids 需要删除的多语言配置包主键
     * @return 结果
     */
    @Override
    public int deleteLangMgrByIds(Long[] ids) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        int count = langMgrMapper.deleteLangMgrByIds(ids);
        //重新加载语言包缓存
        reloadLangMgrCache();
        return count;
    }

    /**
     * 删除多语言配置包信息
     * 
     * @param id 多语言配置包主键
     * @return 结果
     */
    @Override
    public int deleteLangMgrById(Long id)
    {
        return langMgrMapper.deleteLangMgrById(id);
    }

    @Override
    public void reloadLangMgrCache() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        //语言包信息
        List<LangMgr> langMgrs = langMgrMapper.selectLangMgrList(null);

        //app静态语言包
        List<LangMgr> appLangMgrs = langMgrs.stream().filter(a -> a.getType() == 0).collect(Collectors.toList());

        Language language = new Language();
        language.setStatus(0);

        //启用的语言列表
        List<Language> languages = languageMapper.selectLanguageList(language);
        for (int i = 0; i < languages.size(); i++) {
            //缓存到properties文件
            Properties properties = new Properties();

            //app调用语言包
            HashMap<String, String> appMap = new HashMap<>();
            //lang:语言标识
            String abbreviations = languages.get(i).getAbbreviations();
            for (int j = 0; j < appLangMgrs.size(); j++) {
                LangMgr langMgr = appLangMgrs.get(j);
                String value = "";
                Object o = PropertyUtils.describe(langMgr).get(abbreviations);
                if (o != null){
                    value = String.valueOf(o);
                }
                appMap.put(langMgr.getLangKey(), value);

                ////缓存到properties文件
                properties.setProperty(langMgr.getLangKey(),value);
            }
            //清空redis语言包缓存
            redisCache.cleanCacheMap("appLangMgrs/"+abbreviations);
            //app调用语言包
            redisCache.setCacheMap("appLangMgrs/"+abbreviations,appMap);

            //缓存到properties文件
            FileOutputStream fileOutputStream = null;
            String url = "ruoyi-admin/src/main/resources/lang/"+abbreviations+".properties";
            try {
                File file = new File(url);
                if (!file.exists()){
                    file.createNewFile();
                }
                fileOutputStream = new FileOutputStream(url);
                properties.store(fileOutputStream,null);
            } catch (Exception e) {
                System.out.println(url+"的语言包更新异常");
            }finally {
                if (fileOutputStream != null){
                    try {
                        fileOutputStream.close();
                    } catch (IOException e) {
                        System.out.println(url+"的IO输出流关闭异常");
                    }
                }
            }
        }

        //清空redis语言包缓存
        redisCache.cleanCacheMap("langMgrs");
        //后端提示语言包语言包
        Map<String, LangMgr> webLangMgrs = langMgrs.stream().filter(a -> a.getType() == 1).collect(Collectors.toMap(LangMgr::getLangKey, LangMgr -> LangMgr));
        redisCache.setCacheMap("langMgrs",webLangMgrs);
        //缓存到properties文件
        Properties properties = new Properties();

        for (Map.Entry<String, LangMgr> entry : webLangMgrs.entrySet()) {
            //缓存到properties文件
            properties.setProperty(entry.getKey(), JSONUtil.toJsonStr(entry.getValue()));
        }
        //缓存到properties文件
        FileOutputStream fileOutputStream = null;
        String url = "ruoyi-admin/src/main/resources/lang/all.properties";
        try {
            File file = new File(url);
            if (!file.exists()){
                file.createNewFile();
            }
            fileOutputStream = new FileOutputStream(url);
            properties.store(fileOutputStream,null);
        } catch (Exception e) {
            System.out.println(url+"的语言包更新异常");
        }finally {
            if (fileOutputStream != null){
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    System.out.println(url+"的IO输出流关闭异常");
                }
            }
        }
    }
}
