package com.ruoyi.web.controller.system;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.annotation.RepeatSubmit;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.Category;
import com.ruoyi.system.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 商品类目Controller
 * 
 * @author ruoyi
 * @date 2022-10-27
 */
@RestController
@RequestMapping("/system/category")
public class CategoryController extends BaseController
{
    @Autowired
    private ICategoryService categoryService;

    /**
     * 查询商品类目列表
     */
    @PreAuthorize("@ss.hasPermi('system:category:list')")
    @GetMapping("/list")
    public TableDataInfo list(Category category)
    {
        startPage();
        List<Category> list = categoryService.selectCategoryList(category);
        return getDataTable(list);
    }

    /**
     * 导出商品类目列表
     */
    @PreAuthorize("@ss.hasPermi('system:category:export')")
    @Log(title = "商品类目", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Category category)
    {
        List<Category> list = categoryService.selectCategoryList(category);
        ExcelUtil<Category> util = new ExcelUtil<Category>(Category.class);
        util.exportExcel(response, list, "商品类目数据");
    }

    /**
     * 获取商品类目详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:category:query')")
    @GetMapping(value = "/{id}/{languageId}")
    public AjaxResult getInfo(@PathVariable("id") Long id,@PathVariable("languageId") Long languageId)
    {
        return AjaxResult.success(categoryService.getLanguageValue(categoryService.selectCategoryById(id),languageId));
    }

    /**
     * 新增商品类目
     */
    @PreAuthorize("@ss.hasPermi('system:category:add')")
    @Log(title = "商品类目", businessType = BusinessType.INSERT)
    @RepeatSubmit
    @PostMapping
    public AjaxResult add(@RequestBody Category category)
    {
        if (StringUtils.isEmpty(category.getCategoryName())){
            return AjaxResult.error("请输入类别名称");
        }
        if (StringUtils.isEmpty(category.getImg())){
            return AjaxResult.error("请上传分类图片");
        }
        if (StringUtils.isNull(category.getSort())){
            return AjaxResult.error("请输入类别排序");
        }
        return categoryService.insertCategory(category);
    }

    /**
     * 修改商品类目
     */
    @PreAuthorize("@ss.hasPermi('system:category:edit')")
    @Log(title = "商品类目", businessType = BusinessType.UPDATE)
    @RepeatSubmit
    @PutMapping
    public AjaxResult edit(@RequestBody Category category)
    {
        if (StringUtils.isNull(category.getId())){
            return AjaxResult.error("请先选择需要修改信息的类别");
        }
        return categoryService.updateCategory(category);
    }

    /**
     * 删除商品类目
     */
    @PreAuthorize("@ss.hasPermi('system:category:remove')")
    @Log(title = "商品类目", businessType = BusinessType.DELETE)
    @RepeatSubmit
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(categoryService.deleteCategoryByIds(ids));
    }

}
