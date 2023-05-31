package com.ruoyi.web.controller.system;

import cn.hutool.setting.dialect.Props;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.system.service.IPlatformGoodsInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("/api/test")
public class TestXController {


    @Autowired
    private IPlatformGoodsInfoService platformGoodsInfoService;

    //爬虫拉取亚马逊商品数据
    /**
     * 爬虫拉取亚马逊商品数据
     */
    @GetMapping("/getAmazonGoodsInfo")
    public AjaxResult getAmazonGoodsInfo() throws Exception {
        //platformGoodsInfoService.getAmazonGoodsInfo();
        return null;
    }

    public static void main(String[] args) throws Exception {
        Props props = new Props("lang/"+"zh"+".properties", StandardCharsets.UTF_8);
        System.out.println(props);
        System.out.println("===========================================");

        String url = "ruoyi-admin/src/main/resources/lang/"+"zh"+".properties";
        FileOutputStream fileOutputStream = new FileOutputStream(url);
//        Properties properties = new Properties();
        props.clear();
        props.setProperty("aa","aa");
        props.setProperty("bb","bb");
        props.autoLoad(true);
        props.store(fileOutputStream,null);

        props = new Props();
        props = new Props("lang/"+"zh"+".properties", StandardCharsets.UTF_8);
        props.autoLoad(true);
        System.out.println(props);
        System.out.println("===========================================");
        props = new Props();
//        props.load(new FileInputStream(url));
        System.out.println(props);
        System.out.println("===========================================");
        fileOutputStream.close();

        Props prop = Props.getProp("lang/" + "zh" + ".properties", StandardCharsets.UTF_8);
        System.out.println(prop);
        System.out.println("===========================================");
    }
}
