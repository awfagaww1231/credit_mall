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
        //语言包存放文件夹路径
        String dirPath = System.getProperty("user.dir")+"\\lang\\";
        String path = dirPath+"zh"+".properties";
        Props props = new Props(path, StandardCharsets.UTF_8);
        props.clear();
        props.setProperty("aaa","aaa");
        props.store(new FileOutputStream(path),null);

//        PropertyUtil.initCache(path);

        Props props2 = new Props(path, StandardCharsets.UTF_8);
        System.out.println(props2);
    }
}
