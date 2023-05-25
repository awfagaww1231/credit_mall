package com.ruoyi.web.controller.app;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.customer.domain.ApiGoodsInfo;
import com.ruoyi.system.domain.TopRankingsImgs;
import com.ruoyi.system.service.ITopRankingsImgsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 人气排行榜的轮播图片Controller +
 * 
 * @author ruoyi
 * @date 2022-11-21
 */
@RestController
@RequestMapping("/api/topRankingsImgs")
public class ApiTopRankingsImgsController extends BaseController
{
    @Autowired
    private ITopRankingsImgsService topRankingsImgsService;

    /**
     * 查询人气排行榜的轮播图片列表
     */
    @GetMapping("/list")
    public AjaxResult list(TopRankingsImgs topRankingsImgs)
    {
        List<TopRankingsImgs> list = topRankingsImgsService.selectTopRankingsImgsList(topRankingsImgs);
        return AjaxResult.success(list);
    }
}
