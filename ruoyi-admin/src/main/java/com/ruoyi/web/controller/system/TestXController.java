package com.ruoyi.web.controller.system;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.spring.PropertyUtil;
import com.ruoyi.system.service.IPlatformGoodsInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
//        Props props = new Props("lang/"+"zh"+".properties", StandardCharsets.UTF_8);
//        System.out.println(props);
//        System.out.println("===========================================");
//        String url = "ruoyi-admin/src/main/resources/lang/"+"zh"+".properties";
//
//        PropertiesConfiguration props1 = PropertyUtil.getProps();
//        props1.reload();
//        props1.setProperty("aa","aa2");
//        props1.reload();
//        Props props2 = new Props("lang/"+"zh"+".properties", StandardCharsets.UTF_8);
//        System.out.println(props2);
//        System.out.println("===========================================");
//
//
//        try {
//            PropertyUtil.initCache("zh");
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//        Props props3 = new Props("lang/"+"zh"+".properties", StandardCharsets.UTF_8);
//        System.out.println(props3);
//        System.out.println("===========================================");
        PropertyUtil.initCache("zh");
    }
}
