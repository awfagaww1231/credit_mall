package com.ruoyi.web.controller.system;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.annotation.RepeatSubmit;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.WeeklyDealsClassification;
import com.ruoyi.system.service.IWeeklyDealsClassificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * WeeklyDeals活动分类配置Controller
 * 
 * @author ruoyi
 * @date 2022-11-21
 */
@RestController
@RequestMapping("/system/weeklyDealsClassification")
public class WeeklyDealsClassificationController extends BaseController
{
    @Autowired
    private IWeeklyDealsClassificationService weeklyDealsClassificationService;

    /**
     * 查询WeeklyDeals活动分类配置列表
     */
    @PreAuthorize("@ss.hasPermi('system:classification:list')")
    @GetMapping("/list")
    public TableDataInfo list(WeeklyDealsClassification weeklyDealsClassification)
    {
        startPage();
        List<WeeklyDealsClassification> list = weeklyDealsClassificationService.selectWeeklyDealsClassificationList(weeklyDealsClassification);
        return getDataTable(list);
    }

    /**
     * 导出WeeklyDeals活动分类配置列表
     */
    @PreAuthorize("@ss.hasPermi('system:classification:export')")
    @Log(title = "WeeklyDeals活动分类配置", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, WeeklyDealsClassification weeklyDealsClassification)
    {
        List<WeeklyDealsClassification> list = weeklyDealsClassificationService.selectWeeklyDealsClassificationList(weeklyDealsClassification);
        ExcelUtil<WeeklyDealsClassification> util = new ExcelUtil<WeeklyDealsClassification>(WeeklyDealsClassification.class);
        util.exportExcel(response, list, "WeeklyDeals活动分类配置数据");
    }

    /**
     * 获取WeeklyDeals活动分类配置详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:classification:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(weeklyDealsClassificationService.selectWeeklyDealsClassificationById(id));
    }

    /**
     * 新增WeeklyDeals活动分类配置
     */
    @PreAuthorize("@ss.hasPermi('system:classification:add')")
    @Log(title = "WeeklyDeals活动分类配置", businessType = BusinessType.INSERT)
    @PostMapping
    @RepeatSubmit
    public AjaxResult add(@RequestBody WeeklyDealsClassification weeklyDealsClassification)
    {
        if (StringUtils.isNull(weeklyDealsClassification.getClassificationName())){
            return AjaxResult.error("请输入活动分类名称");
        }
        if (StringUtils.isEmpty(weeklyDealsClassification.getClassificationName())){
            return AjaxResult.error("请输入活动分类名称");
        }
        return toAjax(weeklyDealsClassificationService.insertWeeklyDealsClassification(weeklyDealsClassification));
    }

    /**
     * 修改WeeklyDeals活动分类配置
     */
    @PreAuthorize("@ss.hasPermi('system:classification:edit')")
    @Log(title = "WeeklyDeals活动分类配置", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WeeklyDealsClassification weeklyDealsClassification)
    {
        if (StringUtils.isNull(weeklyDealsClassification.getId())){
            return AjaxResult.error("清先选择需要修改信息的活动分类");
        }
        if (StringUtils.isNull(weeklyDealsClassification.getClassificationName())){
            return AjaxResult.error("请输入活动分类名称");
        }
        if (StringUtils.isEmpty(weeklyDealsClassification.getClassificationName())){
            return AjaxResult.error("请输入活动分类名称");
        }
        return toAjax(weeklyDealsClassificationService.updateWeeklyDealsClassification(weeklyDealsClassification));
    }

    /**
     * 删除WeeklyDeals活动分类配置
     */
    @PreAuthorize("@ss.hasPermi('system:classification:remove')")
    @Log(title = "WeeklyDeals活动分类配置", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        //如果要删除置顶
        for (int i = 0; i < ids.length; i++) {
            Long id = ids[i];
            if (id.equals(1L)){
                return AjaxResult.error("置顶的活动分类不允许删除");
            }
        }
        return toAjax(weeklyDealsClassificationService.deleteWeeklyDealsClassificationByIds(ids));
    }
}
