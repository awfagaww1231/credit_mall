package com.ruoyi.common.utils.spring;

import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.net.URL;

@Component
public class PropertyUtil {

    private PropertyUtil() {
    }

    public static PropertiesConfiguration getProps(String propertiesFile){
        return init(propertiesFile);
    }
    /**
     * 初始化
     * @param propertiesFile
     * @see
     */
    private static PropertiesConfiguration init(String propertiesFile) {
        PropertiesConfiguration  PropertyUtil = null;
        try {
//            File file = new File(propertiesFile);
            PropertyUtil = new PropertiesConfiguration(propertiesFile);
            //自动重新加载
            PropertyUtil.setReloadingStrategy(new FileChangedReloadingStrategy());
            //自动保存
            PropertyUtil.setAutoSave(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return PropertyUtil;
    }


    /**
     * 初始化内存缓存
     */
    public static void initCache(String propertiesFile) throws Exception {
        PropertiesConfiguration props = getProps(propertiesFile);
//        props.load(new FileInputStream("ruoyi-admin/src/main/resources/lang/"+lang+".properties"));
        props.clear();
        props.load(new FileInputStream(propertiesFile));
        URL url = props.getURL();
        props.save(url);
    }
}
