package com.ruoyi.web.controller.system;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.annotation.RepeatSubmit;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.HotToday;
import com.ruoyi.system.domain.ShopGoodsInfo;
import com.ruoyi.system.service.IHotTodayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 今日热门商品Controller
 * 
 * @author ruoyi
 * @date 2022-11-23
 */
@RestController
@RequestMapping("/system/hotToday")
public class HotTodayController extends BaseController
{
    @Autowired
    private IHotTodayService hotTodayService;

    /**
     * 查询今日热门商品列表
     */
    @PreAuthorize("@ss.hasPermi('system:hotToday:list')")
    @GetMapping("/list")
    public TableDataInfo list(HotToday hotToday)
    {
        startPage();
        List<HotToday> list = hotTodayService.selectHotTodayList(hotToday);
        return getDataTable(list);
    }

    /**
     * 导出今日热门商品列表
     */
    @PreAuthorize("@ss.hasPermi('system:hotToday:export')")
    @Log(title = "今日热门商品", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, HotToday hotToday)
    {
        List<HotToday> list = hotTodayService.selectHotTodayList(hotToday);
        ExcelUtil<HotToday> util = new ExcelUtil<HotToday>(HotToday.class);
        util.exportExcel(response, list, "今日热门商品数据");
    }

    /**
     * 获取今日热门商品详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:hotToday:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(hotTodayService.selectHotTodayById(id));
    }

    /**
     * 新增今日热门商品
     */
    @PreAuthorize("@ss.hasPermi('system:hotToday:add')")
    @Log(title = "今日热门商品", businessType = BusinessType.INSERT)
    @RepeatSubmit
    @PostMapping
    public AjaxResult add(@RequestBody HotToday hotToday)
    {
        if (StringUtils.isNull(hotToday.getShopGoodsInfoId())){
            return AjaxResult.error("请先选择需要添加的今日热门商品");
        }
        return toAjax(hotTodayService.insertHotToday(hotToday));
    }

    /**
     * 修改今日热门商品
     */
    @PreAuthorize("@ss.hasPermi('system:hotToday:edit')")
    @Log(title = "今日热门商品", businessType = BusinessType.UPDATE)
    @RepeatSubmit
    @PutMapping
    public AjaxResult edit(@RequestBody HotToday hotToday)
    {
        if (StringUtils.isNull(hotToday.getSort())){
            return AjaxResult.error("请先输入活动商品的排序");
        }
        return toAjax(hotTodayService.updateHotToday(hotToday));
    }

    /**
     * 删除今日热门商品
     */
    @PreAuthorize("@ss.hasPermi('system:hotToday:remove')")
    @Log(title = "今日热门商品", businessType = BusinessType.DELETE)
    @RepeatSubmit
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(hotTodayService.deleteHotTodayByIds(ids));
    }

    /**
     * 查询可添加的商品列表
     */
    @GetMapping("/shopGoodsInfoList")
    public TableDataInfo platformGoodsInfoList(ShopGoodsInfo shopGoodsInfo)
    {
        startPage();
        shopGoodsInfo.setStatus(0);
        List<ShopGoodsInfo> list = hotTodayService.selectShopGoodsInfoList(shopGoodsInfo);
        return getDataTable(list);
    }
}
