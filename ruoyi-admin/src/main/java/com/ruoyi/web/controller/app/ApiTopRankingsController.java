package com.ruoyi.web.controller.app;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.customer.domain.ApiGoodsInfo;
import com.ruoyi.customer.service.IApiTopRankingsService;
import com.ruoyi.system.domain.TopRankingsImgs;
import com.ruoyi.system.service.ITopRankingsImgsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 人气排行榜Controller +
 * 
 * @author ruoyi
 * @date 2022-11-21
 */
@RestController
@RequestMapping("/api/topRankings")
public class ApiTopRankingsController extends BaseController
{

    @Autowired
    private IApiTopRankingsService apiTopRankingsService;


    /**
     * 查询人气排行商品列表
     */
    @GetMapping("/topRankingsList")
    public AjaxResult topRankingsList()
    {
        List<ApiGoodsInfo> list = apiTopRankingsService.topRankingsList();
        return AjaxResult.success(list);
    }

}
