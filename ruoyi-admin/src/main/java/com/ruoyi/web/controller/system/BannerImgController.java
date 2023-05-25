package com.ruoyi.web.controller.system;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.annotation.RepeatSubmit;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.BannerImg;
import com.ruoyi.system.service.IBannerImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 轮播图Controller
 * 
 * @author ruoyi
 * @date 2023-04-04
 */
@RestController
@RequestMapping("/system/bannerImg")
public class BannerImgController extends BaseController
{
    @Autowired
    private IBannerImgService bannerImgService;

    /**
     * 查询轮播图列表
     */
    @PreAuthorize("@ss.hasPermi('system:bannerImg:list')")
    @GetMapping("/list")
    public AjaxResult list(BannerImg bannerImg)
    {
        startPage();
        if (StringUtils.isNull(bannerImg.getLanguageId())){
            return AjaxResult.error("请选择语言");
        }
        List<BannerImg> list = bannerImgService.selectBannerImgList(bannerImg);
        return AjaxResult.success(getDataTable(list));
    }


    /**
     * 获取轮播图详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:bannerImg:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(bannerImgService.selectBannerImgById(id));
    }

    /**
     * 新增轮播图
     */
    @PreAuthorize("@ss.hasPermi('system:bannerImg:add')")
    @Log(title = "轮播图", businessType = BusinessType.INSERT)
    @RepeatSubmit
    @PostMapping
    public AjaxResult add(@RequestBody BannerImg bannerImg)
    {
        if (StringUtils.isEmpty(bannerImg.getBannerImg())){
            return AjaxResult.error("请上传轮播图片");
        }
        if (StringUtils.isNull(bannerImg.getLanguageId())){
            return AjaxResult.error("请选择语言");
        }
        return toAjax(bannerImgService.insertBannerImg(bannerImg));
    }

    /**
     * 修改轮播图
     */
    @PreAuthorize("@ss.hasPermi('system:bannerImg:edit')")
    @Log(title = "轮播图", businessType = BusinessType.UPDATE)
    @RepeatSubmit
    @PutMapping
    public AjaxResult edit(@RequestBody BannerImg bannerImg)
    {
        return toAjax(bannerImgService.updateBannerImg(bannerImg));
    }

    /**
     * 删除轮播图
     */
    @PreAuthorize("@ss.hasPermi('system:bannerImg:remove')")
    @Log(title = "轮播图", businessType = BusinessType.DELETE)
    @RepeatSubmit
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(bannerImgService.deleteBannerImgByIds(ids));
    }
}
