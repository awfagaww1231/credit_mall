package com.ruoyi.web.controller.system;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.annotation.RepeatSubmit;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.CustomerService;
import com.ruoyi.system.service.ICustomerServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 客服配置Controller
 * 
 * @author ruoyi
 * @date 2023-03-22
 */
@RestController
@RequestMapping("/system/customerService")
public class CustomerServiceController extends BaseController
{
    @Autowired
    private ICustomerServiceService customerServiceService;

    /**
     * 查询客服配置列表
     */
    @PreAuthorize("@ss.hasPermi('system:customerService:list')")
    @GetMapping("/list")
    public TableDataInfo list(CustomerService customerService)
    {
        startPage();
        List<CustomerService> list = customerServiceService.selectCustomerServiceList(customerService);
        return getDataTable(list);
    }

    /**
     * 导出客服配置列表
     */
    @PreAuthorize("@ss.hasPermi('system:customerService:export')")
    @Log(title = "客服配置", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CustomerService customerService)
    {
        List<CustomerService> list = customerServiceService.selectCustomerServiceList(customerService);
        ExcelUtil<CustomerService> util = new ExcelUtil<CustomerService>(CustomerService.class);
        util.exportExcel(response, list, "客服配置数据");
    }

    /**
     * 获取客服配置详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:customerService:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(customerServiceService.selectCustomerServiceById(id));
    }

    /**
     * 新增客服配置
     */
    @PreAuthorize("@ss.hasPermi('system:customerService:add')")
    @Log(title = "客服配置", businessType = BusinessType.INSERT)
    @RepeatSubmit
    @PostMapping
    public AjaxResult add(@RequestBody CustomerService customerService)
    {
        return toAjax(customerServiceService.insertCustomerService(customerService));
    }

    /**
     * 修改客服配置
     */
    @PreAuthorize("@ss.hasPermi('system:customerService:edit')")
    @Log(title = "客服配置", businessType = BusinessType.UPDATE)
    @RepeatSubmit
    @PutMapping
    public AjaxResult edit(@RequestBody CustomerService customerService)
    {
        return toAjax(customerServiceService.updateCustomerService(customerService));
    }

    /**
     * 删除客服配置
     */
    @PreAuthorize("@ss.hasPermi('system:customerService:remove')")
    @Log(title = "客服配置", businessType = BusinessType.DELETE)
    @RepeatSubmit
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(customerServiceService.deleteCustomerServiceByIds(ids));
    }
}
