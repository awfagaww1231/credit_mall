package com.ruoyi.web.controller.system;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.annotation.RepeatSubmit;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.ShipmentNumber;
import com.ruoyi.system.service.IShipmentNumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 店铺订单物流信息Controller
 * 
 * @author ruoyi
 * @date 2022-11-02
 */
@RestController
@RequestMapping("/system/number")
public class ShipmentNumberController extends BaseController
{
    @Autowired
    private IShipmentNumberService shipmentNumberService;

    /**
     * 查询店铺订单物流信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:number:list')")
    @GetMapping("/list")
    public TableDataInfo list(ShipmentNumber shipmentNumber)
    {
        startPage();
        List<ShipmentNumber> list = shipmentNumberService.selectShipmentNumberList(shipmentNumber);
        return getDataTable(list);
    }

    /**
     * 导出店铺订单物流信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:number:export')")
    @Log(title = "店铺订单物流信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ShipmentNumber shipmentNumber)
    {
        List<ShipmentNumber> list = shipmentNumberService.selectShipmentNumberList(shipmentNumber);
        ExcelUtil<ShipmentNumber> util = new ExcelUtil<ShipmentNumber>(ShipmentNumber.class);
        util.exportExcel(response, list, "店铺订单物流信息数据");
    }

    /**
     * 获取店铺订单物流信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:number:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(shipmentNumberService.selectShipmentNumberById(id));
    }

    /**
     * 新增店铺订单物流信息
     */
    @PreAuthorize("@ss.hasPermi('system:number:add')")
    @RepeatSubmit
    @Log(title = "店铺订单物流信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ShipmentNumber shipmentNumber)
    {
        return toAjax(shipmentNumberService.insertShipmentNumber(shipmentNumber));
    }

    /**
     * 修改店铺订单物流信息
     */
    @PreAuthorize("@ss.hasPermi('system:number:edit')")
    @Log(title = "店铺订单物流信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ShipmentNumber shipmentNumber)
    {
        return toAjax(shipmentNumberService.updateShipmentNumber(shipmentNumber));
    }

    /**
     * 删除店铺订单物流信息
     */
    @PreAuthorize("@ss.hasPermi('system:number:remove')")
    @Log(title = "店铺订单物流信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(shipmentNumberService.deleteShipmentNumberByIds(ids));
    }
}
