package com.ruoyi.web.controller.system;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.annotation.RepeatSubmit;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.GoodsSku;
import com.ruoyi.system.service.IGoodsSkuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 商品skuController
 * 
 * @author ruoyi
 * @date 2022-11-18
 */
@RestController
@RequestMapping("/system/goodsSku")
public class GoodsSkuController extends BaseController
{
    @Autowired
    private IGoodsSkuService goodsSkuService;

    /**
     * 查询商品sku列表
     */
    @PreAuthorize("@ss.hasPermi('system:goodsSku:list')")
    @GetMapping("/list")
    public TableDataInfo list(GoodsSku goodsSku)
    {
        startPage();
        List<GoodsSku> list = goodsSkuService.selectGoodsSkuList(goodsSku);
        return getDataTable(list);
    }

    /**
     * 导出商品sku列表
     */
    @PreAuthorize("@ss.hasPermi('system:goodsSku:export')")
    @Log(title = "商品sku", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, GoodsSku goodsSku)
    {
        List<GoodsSku> list = goodsSkuService.selectGoodsSkuList(goodsSku);
        ExcelUtil<GoodsSku> util = new ExcelUtil<GoodsSku>(GoodsSku.class);
        util.exportExcel(response, list, "商品sku数据");
    }

    /**
     * 获取商品sku详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:goodsSku:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(goodsSkuService.selectGoodsSkuById(id));
    }

    /**
     * 新增商品sku
     */
    @PreAuthorize("@ss.hasPermi('system:goodsSku:add')")
    @Log(title = "商品sku", businessType = BusinessType.INSERT)
    @RepeatSubmit
    @PostMapping
    public AjaxResult add(@RequestBody GoodsSku goodsSku)
    {
        return toAjax(goodsSkuService.insertGoodsSku(goodsSku));
    }

    /**
     * 修改商品sku
     */
    @PreAuthorize("@ss.hasPermi('system:goodsSku:edit')")
    @Log(title = "商品sku", businessType = BusinessType.UPDATE)
    @RepeatSubmit
    @PutMapping
    public AjaxResult edit(@RequestBody GoodsSku goodsSku)
    {
        return toAjax(goodsSkuService.updateGoodsSku(goodsSku));
    }

    /**
     * 删除商品sku
     */
    @PreAuthorize("@ss.hasPermi('system:goodsSku:remove')")
    @Log(title = "商品sku", businessType = BusinessType.DELETE)
    @RepeatSubmit
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(goodsSkuService.deleteGoodsSkuByIds(ids));
    }
}
