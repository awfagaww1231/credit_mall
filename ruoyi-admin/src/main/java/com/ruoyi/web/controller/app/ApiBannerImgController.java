package com.ruoyi.web.controller.app;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.x.lang.LangUtils;
import com.ruoyi.system.domain.BannerImg;
import com.ruoyi.system.service.IBannerImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 轮播图Controller
 * 
 * @author ruoyi
 * @date 2023-04-04
 */
@RestController
@RequestMapping("/api/bannerImg")
public class ApiBannerImgController extends BaseController
{
    @Autowired
    private IBannerImgService bannerImgService;

    /**
     * 查询轮播图列表
     */
    @GetMapping("/list")
    public AjaxResult list(BannerImg bannerImg)
    {
        startPage();
        bannerImg.setLanguageId(LangUtils.getLanguageId());
        bannerImg.setStatus(0);
        List<BannerImg> list = bannerImgService.selectBannerImgList(bannerImg);
        return AjaxResult.success(getDataTable(list));
    }

}
