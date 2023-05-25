package com.ruoyi.web.controller.system;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.annotation.RepeatSubmit;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.SuperDealsClassification;
import com.ruoyi.system.service.ISuperDealsClassificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * SuperDeals活动分类配置Controller
 * 
 * @author ruoyi
 * @date 2022-11-21
 */
@RestController
@RequestMapping("/system/superDealsClassification")
public class SuperDealsClassificationController extends BaseController
{
    @Autowired
    private ISuperDealsClassificationService superDealsClassificationService;

    /**
     * 查询SuperDeals活动分类配置列表
     */
    @PreAuthorize("@ss.hasPermi('system:superDealsClassification:list')")
    @GetMapping("/list")
    public TableDataInfo list(SuperDealsClassification superDealsClassification)
    {
        startPage();
        List<SuperDealsClassification> list = superDealsClassificationService.selectSuperDealsClassificationList(superDealsClassification);
        return getDataTable(list);
    }

    /**
     * 获取SuperDeals活动分类配置详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:superDealsClassification:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(superDealsClassificationService.selectSuperDealsClassificationById(id));
    }

    /**
     * 新增SuperDeals活动分类配置
     */
    @PreAuthorize("@ss.hasPermi('system:superDealsClassification:add')")
    @Log(title = "SuperDeals活动分类配置", businessType = BusinessType.INSERT)
    @PostMapping
    @RepeatSubmit
    public AjaxResult add(@RequestBody SuperDealsClassification superDealsClassification)
    {
        if (StringUtils.isNull(superDealsClassification.getClassificationName())){
            return AjaxResult.error("请输入活动分类名称");
        }
        if (StringUtils.isEmpty(superDealsClassification.getClassificationName())){
            return AjaxResult.error("请输入活动分类名称");
        }
        return toAjax(superDealsClassificationService.insertSuperDealsClassification(superDealsClassification));
    }

    /**
     * 修改SuperDeals活动分类配置
     */
    @PreAuthorize("@ss.hasPermi('system:superDealsClassification:edit')")
    @Log(title = "SuperDeals活动分类配置", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SuperDealsClassification superDealsClassification)
    {
        if (StringUtils.isNull(superDealsClassification.getId())){
            return AjaxResult.error("清先选择需要修改信息的活动分类");
        }
        if (StringUtils.isNull(superDealsClassification.getClassificationName())){
            return AjaxResult.error("请输入活动分类名称");
        }
        if (StringUtils.isEmpty(superDealsClassification.getClassificationName())){
            return AjaxResult.error("请输入活动分类名称");
        }
        return toAjax(superDealsClassificationService.updateSuperDealsClassification(superDealsClassification));
    }

    /**
     * 删除SuperDeals活动分类配置
     */
    @PreAuthorize("@ss.hasPermi('system:superDealsClassification:remove')")
    @Log(title = "SuperDeals活动分类配置", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(superDealsClassificationService.deleteSuperDealsClassificationByIds(ids));
    }
}
