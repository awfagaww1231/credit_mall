package com.ruoyi.web.controller.system;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.annotation.RepeatSubmit;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.GoodsSpecification;
import com.ruoyi.system.service.IGoodsSpecificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 商品对应的规格绑定信息Controller
 * 
 * @author ruoyi
 * @date 2022-11-24
 */
@RestController
@RequestMapping("/system/specification")
public class GoodsSpecificationController extends BaseController
{
    @Autowired
    private IGoodsSpecificationService goodsSpecificationService;

    /**
     * 查询商品对应的规格绑定信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:specification:list')")
    @GetMapping("/list")
    public TableDataInfo list(GoodsSpecification goodsSpecification)
    {
        startPage();
        List<GoodsSpecification> list = goodsSpecificationService.selectGoodsSpecificationList(goodsSpecification);
        return getDataTable(list);
    }

    /**
     * 获取商品对应的规格绑定信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:specification:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(goodsSpecificationService.selectGoodsSpecificationById(id));
    }

    /**
     * 新增商品对应的规格绑定信息
     */
    @PreAuthorize("@ss.hasPermi('system:specification:add')")
    @Log(title = "商品对应的规格绑定信息", businessType = BusinessType.INSERT)
    @RepeatSubmit
    @PostMapping
    public AjaxResult add(@RequestBody GoodsSpecification goodsSpecification)
    {
        return toAjax(goodsSpecificationService.insertGoodsSpecification(goodsSpecification));
    }

    /**
     * 修改商品对应的规格绑定信息
     */
    @PreAuthorize("@ss.hasPermi('system:specification:edit')")
    @Log(title = "商品对应的规格绑定信息", businessType = BusinessType.UPDATE)
    @RepeatSubmit
    @PutMapping
    public AjaxResult edit(@RequestBody GoodsSpecification goodsSpecification)
    {
        return toAjax(goodsSpecificationService.updateGoodsSpecification(goodsSpecification));
    }

    /**
     * 删除商品对应的规格绑定信息
     */
    @PreAuthorize("@ss.hasPermi('system:specification:remove')")
    @Log(title = "商品对应的规格绑定信息", businessType = BusinessType.DELETE)
    @RepeatSubmit
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(goodsSpecificationService.deleteGoodsSpecificationByIds(ids));
    }
}
