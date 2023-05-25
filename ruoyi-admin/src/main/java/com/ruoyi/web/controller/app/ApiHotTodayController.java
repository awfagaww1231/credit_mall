package com.ruoyi.web.controller.app;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.customer.domain.ApiGoodsInfo;
import com.ruoyi.customer.domain.ApiShopInfo;
import com.ruoyi.customer.service.IApiHotTodayService;
import com.ruoyi.system.domain.ShopInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * APP今日热门Controller +
 *
 * @author ruoyi
 * @date 2022-10-27
 */
@RestController
@RequestMapping("/api/hotToday")
public class ApiHotTodayController extends BaseController {

    @Autowired
    private IApiHotTodayService apiHotTodayService;

    /**
     * 查询今日热门列表
     */
    @GetMapping("/hotTodayList")
    public AjaxResult hotTodayList()
    {
        startPage();
        List<ApiGoodsInfo> apiGoodsInfos = apiHotTodayService.hotTodayList();
        return AjaxResult.success(getDataTable(apiGoodsInfos));
    }
}
