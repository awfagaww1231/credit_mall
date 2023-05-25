package com.ruoyi.web.controller.system;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.annotation.RepeatSubmit;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.ShopGoodsInfo;
import com.ruoyi.system.domain.WeeklyDealsClassification;
import com.ruoyi.system.domain.WeeklyDealsClassificationGoods;
import com.ruoyi.system.service.IWeeklyDealsClassificationGoodsService;
import com.ruoyi.system.service.IWeeklyDealsClassificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * WeeklyDeals活动分类中的商品信息Controller
 * 
 * @author ruoyi
 * @date 2022-11-21
 */
@RestController
@RequestMapping("/system/weeklyDealsClassificationGoods")
public class WeeklyDealsClassificationGoodsController extends BaseController
{
    @Autowired
    private IWeeklyDealsClassificationGoodsService weeklyDealsClassificationGoodsService;

    @Autowired
    private IWeeklyDealsClassificationService weeklyDealsClassificationService;
    /**
     * 查询WeeklyDeals活动分类中的商品信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:weeklyDealsClassificationGoods:list')")
    @GetMapping("/list")
    public TableDataInfo list(WeeklyDealsClassificationGoods weeklyDealsClassificationGoods)
    {
        startPage();
        List<WeeklyDealsClassificationGoods> list = weeklyDealsClassificationGoodsService.selectWeeklyDealsClassificationGoodsList(weeklyDealsClassificationGoods);
        return getDataTable(list);
    }

    /**
     * 导出WeeklyDeals活动分类中的商品信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:weeklyDealsClassificationGoods:export')")
    @Log(title = "WeeklyDeals活动分类中的商品信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, WeeklyDealsClassificationGoods weeklyDealsClassificationGoods)
    {
        List<WeeklyDealsClassificationGoods> list = weeklyDealsClassificationGoodsService.selectWeeklyDealsClassificationGoodsList(weeklyDealsClassificationGoods);
        ExcelUtil<WeeklyDealsClassificationGoods> util = new ExcelUtil<WeeklyDealsClassificationGoods>(WeeklyDealsClassificationGoods.class);
        util.exportExcel(response, list, "WeeklyDeals活动分类中的商品信息数据");
    }

    /**
     * 获取WeeklyDeals活动分类中的商品信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:weeklyDealsClassificationGoods:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(weeklyDealsClassificationGoodsService.selectWeeklyDealsClassificationGoodsById(id));
    }

    /**
     * 新增WeeklyDeals活动分类中的商品信息
     */
    @PreAuthorize("@ss.hasPermi('system:weeklyDealsClassificationGoods:add')")
    @Log(title = "WeeklyDeals活动分类中的商品信息", businessType = BusinessType.INSERT)
    @PostMapping
    @RepeatSubmit
    public AjaxResult add(@RequestBody WeeklyDealsClassificationGoods weeklyDealsClassificationGoods)
    {
        if (StringUtils.isNull(weeklyDealsClassificationGoods.getShopGoodsInfoId())){
            return AjaxResult.error("请选择要添加的商品");
        }
        if (StringUtils.isNull(weeklyDealsClassificationGoods.getClassificationId())){
            return AjaxResult.error("请选择该商品所要添入的所属分类");
        }
        List<WeeklyDealsClassificationGoods> weeklyDealsClassificationGoodsList = weeklyDealsClassificationGoodsService.selectWeeklyDealsClassificationGoodsList(weeklyDealsClassificationGoods);
        if (weeklyDealsClassificationGoodsList.size() >= 1){
            return AjaxResult.error("该活动分类已经添加过此商品了");
        }
        return toAjax(weeklyDealsClassificationGoodsService.insertWeeklyDealsClassificationGoods(weeklyDealsClassificationGoods));
    }

    /**
     * 修改WeeklyDeals活动分类中的商品信息
     */
    @PreAuthorize("@ss.hasPermi('system:weeklyDealsClassificationGoods:edit')")
    @Log(title = "WeeklyDeals活动分类中的商品信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WeeklyDealsClassificationGoods weeklyDealsClassificationGoods)
    {
        if (StringUtils.isNull(weeklyDealsClassificationGoods.getShopGoodsInfoId())){
            return AjaxResult.error("请选择要添加的商品");
        }
        if (StringUtils.isNull(weeklyDealsClassificationGoods.getClassificationId())){
            return AjaxResult.error("请选择该商品所要添入的所属分类");
        }
        List<WeeklyDealsClassificationGoods> weeklyDealsClassificationGoodsList = weeklyDealsClassificationGoodsService.selectWeeklyDealsClassificationGoodsList(weeklyDealsClassificationGoods);
        if (weeklyDealsClassificationGoodsList.size() >= 1){
            return AjaxResult.error("该活动分类已经添加过此商品了");
        }
        return toAjax(weeklyDealsClassificationGoodsService.updateWeeklyDealsClassificationGoods(weeklyDealsClassificationGoods));
    }

    /**
     * 删除WeeklyDeals活动分类中的商品信息
     */
    @PreAuthorize("@ss.hasPermi('system:weeklyDealsClassificationGoods:remove')")
    @Log(title = "WeeklyDeals活动分类中的商品信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(weeklyDealsClassificationGoodsService.deleteWeeklyDealsClassificationGoodsByIds(ids));
    }

    /**
     * 查询可添加的商品列表
     */
    @GetMapping("/shopGoodsInfoList")
    public TableDataInfo platformGoodsInfoList(ShopGoodsInfo shopGoodsInfo)
    {
        startPage();
        shopGoodsInfo.setStatus(0);
        List<ShopGoodsInfo> list = weeklyDealsClassificationGoodsService.selectShopGoodsInfoList(shopGoodsInfo);
        return getDataTable(list);
    }

    /**
     * 查询SuperDeals活动分类配置列表
     */
    @GetMapping("/weeklyDealsDealsClassificationList")
    public AjaxResult list(WeeklyDealsClassification weeklyDealsClassification)
    {
        startPage();
        List<WeeklyDealsClassification> list = weeklyDealsClassificationService.selectWeeklyDealsClassificationList(weeklyDealsClassification);
        return AjaxResult.success(list);
    }
}
