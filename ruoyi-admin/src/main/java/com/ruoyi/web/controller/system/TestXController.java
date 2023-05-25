package com.ruoyi.web.controller.system;

import com.ruoyi.common.core.domain.AjaxResult;
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

    public static void main(String[] args) {
        String s = "abcdef";
        String substring = s.substring(0, s.length() - 1);
        System.out.println(substring);
    }
}
