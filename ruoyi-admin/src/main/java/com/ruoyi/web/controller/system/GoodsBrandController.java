package com.ruoyi.web.controller.system;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.annotation.RepeatSubmit;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.GoodsBrand;
import com.ruoyi.system.service.IGoodsBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 商品品牌Controller
 * 
 * @author ruoyi
 * @date 2022-11-27
 */
@RestController
@RequestMapping("/system/goodsBrand")
public class GoodsBrandController extends BaseController
{
    @Autowired
    private IGoodsBrandService goodsBrandService;

    /**
     * 查询商品品牌列表
     */
    @PreAuthorize("@ss.hasPermi('system:goodsBrand:list')")
    @GetMapping("/list")
    public TableDataInfo list(GoodsBrand goodsBrand)
    {
        startPage();
        List<GoodsBrand> list = goodsBrandService.selectGoodsBrandList(goodsBrand);
        return getDataTable(list);
    }

    /**
     * 导出商品品牌列表
     */
    @PreAuthorize("@ss.hasPermi('system:goodsBrand:export')")
    @Log(title = "商品品牌", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, GoodsBrand goodsBrand)
    {
        List<GoodsBrand> list = goodsBrandService.selectGoodsBrandList(goodsBrand);
        ExcelUtil<GoodsBrand> util = new ExcelUtil<GoodsBrand>(GoodsBrand.class);
        util.exportExcel(response, list, "商品品牌数据");
    }

    /**
     * 获取商品品牌详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:goodsBrand:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(goodsBrandService.selectGoodsBrandById(id));
    }

    /**
     * 新增商品品牌
     */
    @PreAuthorize("@ss.hasPermi('system:goodsBrand:add')")
    @Log(title = "商品品牌", businessType = BusinessType.INSERT)
    @RepeatSubmit
    @PostMapping
    public AjaxResult add(@RequestBody GoodsBrand goodsBrand)
    {
        return toAjax(goodsBrandService.insertGoodsBrand(goodsBrand));
    }

    /**
     * 修改商品品牌
     */
    @PreAuthorize("@ss.hasPermi('system:goodsBrand:edit')")
    @Log(title = "商品品牌", businessType = BusinessType.UPDATE)
    @RepeatSubmit
    @PutMapping
    public AjaxResult edit(@RequestBody GoodsBrand goodsBrand)
    {
        return toAjax(goodsBrandService.updateGoodsBrand(goodsBrand));
    }

    /**
     * 删除商品品牌
     */
    @PreAuthorize("@ss.hasPermi('system:goodsBrand:remove')")
    @Log(title = "商品品牌", businessType = BusinessType.DELETE)
    @RepeatSubmit
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(goodsBrandService.deleteGoodsBrandByIds(ids));
    }
}
