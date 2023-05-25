package com.ruoyi.web.controller.system;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.annotation.RepeatSubmit;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.SellerBillDetails;
import com.ruoyi.system.service.ISellerBillDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * C端用户账单明细Controller
 * 
 * @author ruoyi
 * @date 2022-11-10
 */
@RestController
@RequestMapping("/system/sellerBillDetails")
public class SellerBillDetailsController extends BaseController
{
    @Autowired
    private ISellerBillDetailsService sellerBillDetailsService;

    /**
     * 查询C端用户账单明细列表
     */
    @PreAuthorize("@ss.hasPermi('system:sellerBillDetails:list')")
    @GetMapping("/list")
    public TableDataInfo list(SellerBillDetails sellerBillDetails)
    {
        startPage();
        List<SellerBillDetails> list = sellerBillDetailsService.selectSellerBillDetailsList(sellerBillDetails);
        return getDataTable(list);
    }

    /**
     * 导出C端用户账单明细列表
     */
    @PreAuthorize("@ss.hasPermi('system:sellerBillDetails:export')")
    @Log(title = "C端用户账单明细", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SellerBillDetails sellerBillDetails)
    {
        List<SellerBillDetails> list = sellerBillDetailsService.selectSellerBillDetailsList(sellerBillDetails);
        ExcelUtil<SellerBillDetails> util = new ExcelUtil<SellerBillDetails>(SellerBillDetails.class);
        util.exportExcel(response, list, "C端用户账单明细数据");
    }

    /**
     * 获取C端用户账单明细详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:sellerBillDetails:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(sellerBillDetailsService.selectSellerBillDetailsById(id));
    }

    /**
     * 新增C端用户账单明细
     */
    @PreAuthorize("@ss.hasPermi('system:sellerBillDetails:add')")
    @Log(title = "C端用户账单明细", businessType = BusinessType.INSERT)
    @RepeatSubmit
    @PostMapping
    public AjaxResult add(@RequestBody SellerBillDetails sellerBillDetails)
    {
        return toAjax(sellerBillDetailsService.insertSellerBillDetails(sellerBillDetails));
    }

    /**
     * 修改C端用户账单明细
     */
    @PreAuthorize("@ss.hasPermi('system:sellerBillDetails:edit')")
    @Log(title = "C端用户账单明细", businessType = BusinessType.UPDATE)
    @RepeatSubmit
    @PutMapping
    public AjaxResult edit(@RequestBody SellerBillDetails sellerBillDetails)
    {
        return toAjax(sellerBillDetailsService.updateSellerBillDetails(sellerBillDetails));
    }

    /**
     * 删除C端用户账单明细
     */
    @PreAuthorize("@ss.hasPermi('system:sellerBillDetails:remove')")
    @Log(title = "C端用户账单明细", businessType = BusinessType.DELETE)
    @RepeatSubmit
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(sellerBillDetailsService.deleteSellerBillDetailsByIds(ids));
    }
}
