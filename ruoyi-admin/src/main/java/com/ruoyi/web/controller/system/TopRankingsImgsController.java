package com.ruoyi.web.controller.system;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.annotation.RepeatSubmit;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.TopRankingsImgs;
import com.ruoyi.system.service.ITopRankingsImgsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 人气排行榜的轮播图片Controller
 * 
 * @author ruoyi
 * @date 2022-11-21
 */
@RestController
@RequestMapping("/system/topRankingsImgs")
public class TopRankingsImgsController extends BaseController
{
    @Autowired
    private ITopRankingsImgsService topRankingsImgsService;

    /**
     * 查询人气排行榜的轮播图片列表
     */
    @PreAuthorize("@ss.hasPermi('system:topRankingsImgs:list')")
    @GetMapping("/list")
    public TableDataInfo list(TopRankingsImgs topRankingsImgs)
    {
        startPage();
        List<TopRankingsImgs> list = topRankingsImgsService.selectTopRankingsImgsList(topRankingsImgs);
        return getDataTable(list);
    }

    /**
     * 导出人气排行榜的轮播图片列表
     */
    @PreAuthorize("@ss.hasPermi('system:topRankingsImgs:export')")
    @Log(title = "人气排行榜的轮播图片", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TopRankingsImgs topRankingsImgs)
    {
        List<TopRankingsImgs> list = topRankingsImgsService.selectTopRankingsImgsList(topRankingsImgs);
        ExcelUtil<TopRankingsImgs> util = new ExcelUtil<TopRankingsImgs>(TopRankingsImgs.class);
        util.exportExcel(response, list, "人气排行榜的轮播图片数据");
    }

    /**
     * 获取人气排行榜的轮播图片详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:topRankingsImgs:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(topRankingsImgsService.selectTopRankingsImgsById(id));
    }

    /**
     * 新增人气排行榜的轮播图片
     */
    @PreAuthorize("@ss.hasPermi('system:topRankingsImgs:add')")
    @Log(title = "人气排行榜的轮播图片", businessType = BusinessType.INSERT)
    @PostMapping
    @RepeatSubmit
    public AjaxResult add(@RequestBody TopRankingsImgs topRankingsImgs)
    {
        if (StringUtils.isNull(topRankingsImgs.getImg())){
            return AjaxResult.success("请先上传轮播图片");
        }
        if (StringUtils.isEmpty(topRankingsImgs.getImg())){
            return AjaxResult.success("请先上传轮播图片");
        }
        return toAjax(topRankingsImgsService.insertTopRankingsImgs(topRankingsImgs));
    }

    /**
     * 修改人气排行榜的轮播图片
     */
    @PreAuthorize("@ss.hasPermi('system:topRankingsImgs:edit')")
    @Log(title = "人气排行榜的轮播图片", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TopRankingsImgs topRankingsImgs)
    {
        if (StringUtils.isNull(topRankingsImgs.getId())){
            return AjaxResult.success("请先选择需要修改的轮播图片");
        }
        if (StringUtils.isNull(topRankingsImgs.getImg())){
            return AjaxResult.success("请先上传轮播图片");
        }
        if (StringUtils.isEmpty(topRankingsImgs.getImg())){
            return AjaxResult.success("请先上传轮播图片");
        }
        return toAjax(topRankingsImgsService.updateTopRankingsImgs(topRankingsImgs));
    }

    /**
     * 删除人气排行榜的轮播图片
     */
    @PreAuthorize("@ss.hasPermi('system:topRankingsImgs:remove')")
    @Log(title = "人气排行榜的轮播图片", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(topRankingsImgsService.deleteTopRankingsImgsByIds(ids));
    }
}
