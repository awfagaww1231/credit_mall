package com.ruoyi.web.controller.system;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.SellerVipLevel;
import com.ruoyi.system.service.ISellerVipLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 商户会员等级记录Controller
 * 
 * @author ruoyi
 * @date 2022-11-11
 */
@RestController
@RequestMapping("/system/level")
public class SellerVipLevelController extends BaseController
{
    @Autowired
    private ISellerVipLevelService sellerVipLevelService;

    /**
     * 查询商户会员等级记录列表
     */
    @PreAuthorize("@ss.hasPermi('system:level:list')")
    @GetMapping("/list")
    public TableDataInfo list(SellerVipLevel sellerVipLevel)
    {
        startPage();
        List<SellerVipLevel> list = sellerVipLevelService.selectSellerVipLevelList(sellerVipLevel);
        return getDataTable(list);
    }

    /**
     * 导出商户会员等级记录列表
     */
    @PreAuthorize("@ss.hasPermi('system:level:export')")
    @Log(title = "商户会员等级记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SellerVipLevel sellerVipLevel)
    {
        List<SellerVipLevel> list = sellerVipLevelService.selectSellerVipLevelList(sellerVipLevel);
        ExcelUtil<SellerVipLevel> util = new ExcelUtil<SellerVipLevel>(SellerVipLevel.class);
        util.exportExcel(response, list, "商户会员等级记录数据");
    }

    /**
     * 获取商户会员等级记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:level:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(sellerVipLevelService.selectSellerVipLevelById(id));
    }

    /**
     * 新增商户会员等级记录
     */
    @PreAuthorize("@ss.hasPermi('system:level:add')")
    @Log(title = "商户会员等级记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SellerVipLevel sellerVipLevel)
    {
        return toAjax(sellerVipLevelService.insertSellerVipLevel(sellerVipLevel));
    }

    /**
     * 修改商户会员等级记录
     */
    @PreAuthorize("@ss.hasPermi('system:level:edit')")
    @Log(title = "商户会员等级记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SellerVipLevel sellerVipLevel)
    {
        return toAjax(sellerVipLevelService.updateSellerVipLevel(sellerVipLevel));
    }

    /**
     * 删除商户会员等级记录
     */
    @PreAuthorize("@ss.hasPermi('system:level:remove')")
    @Log(title = "商户会员等级记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(sellerVipLevelService.deleteSellerVipLevelByIds(ids));
    }
}
