package com.ruoyi.web.controller.system;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.annotation.RepeatSubmit;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.GoodsImgStock;
import com.ruoyi.system.service.IGoodsImgStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 商品图片解析库Controller
 * 
 * @author ruoyi
 * @date 2023-01-04
 */
@RestController
@RequestMapping("/system/goodsImgStock")
public class GoodsImgStockController extends BaseController
{
    @Autowired
    private IGoodsImgStockService goodsImgStockService;

    /**
     * 查询商品图片解析库列表
     */
    @PreAuthorize("@ss.hasPermi('system:goodsImgStock:list')")
    @GetMapping("/list")
    public TableDataInfo list(GoodsImgStock goodsImgStock)
    {
        startPage();
        List<GoodsImgStock> list = goodsImgStockService.selectGoodsImgStockList(goodsImgStock);
        return getDataTable(list);
    }

    /**
     * 导出商品图片解析库列表
     */
    @PreAuthorize("@ss.hasPermi('system:goodsImgStock:export')")
    @Log(title = "商品图片解析库", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, GoodsImgStock goodsImgStock)
    {
        List<GoodsImgStock> list = goodsImgStockService.selectGoodsImgStockList(goodsImgStock);
        ExcelUtil<GoodsImgStock> util = new ExcelUtil<GoodsImgStock>(GoodsImgStock.class);
        util.exportExcel(response, list, "商品图片解析库数据");
    }

    /**
     * 获取商品图片解析库详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:goodsImgStock:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(goodsImgStockService.selectGoodsImgStockById(id));
    }

    /**
     * 新增商品图片解析库
     */
    @PreAuthorize("@ss.hasPermi('system:goodsImgStock:add')")
    @Log(title = "商品图片解析库", businessType = BusinessType.INSERT)
    @RepeatSubmit
    @PostMapping
    public AjaxResult add(@RequestBody GoodsImgStock goodsImgStock)
    {
        return toAjax(goodsImgStockService.insertGoodsImgStock(goodsImgStock));
    }

    /**
     * 修改商品图片解析库
     */
    @PreAuthorize("@ss.hasPermi('system:goodsImgStock:edit')")
    @Log(title = "商品图片解析库", businessType = BusinessType.UPDATE)
    @RepeatSubmit
    @PutMapping
    public AjaxResult edit(@RequestBody GoodsImgStock goodsImgStock)
    {
        return toAjax(goodsImgStockService.updateGoodsImgStock(goodsImgStock));
    }

    /**
     * 删除商品图片解析库
     */
    @PreAuthorize("@ss.hasPermi('system:goodsImgStock:remove')")
    @Log(title = "商品图片解析库", businessType = BusinessType.DELETE)
    @RepeatSubmit
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(goodsImgStockService.deleteGoodsImgStockByIds(ids));
    }

    /**
     * 批量绑定商品
     */
    @PreAuthorize("@ss.hasPermi('system:goodsImgStock:bindGoods')")
    @Log(title = "商品图片解析库", businessType = BusinessType.UPDATE)
    @PostMapping("/bindGoods")
    public AjaxResult bindGoods(@RequestBody List<GoodsImgStock> goodsImgStocks)
    {
        return goodsImgStockService.bindGoods(goodsImgStocks);
    }
}
