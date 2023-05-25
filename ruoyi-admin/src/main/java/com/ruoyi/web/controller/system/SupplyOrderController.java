package com.ruoyi.web.controller.system;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.annotation.RepeatSubmit;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.SellerStock;
import com.ruoyi.system.domain.SupplyOrder;
import com.ruoyi.system.service.ISellerStockService;
import com.ruoyi.system.service.ISupplyOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 供应订单（卖家向平台的进货订单）Controller
 * 
 * @author ruoyi
 * @date 2022-10-29
 */
@RestController
@RequestMapping("/system/supplyOrder")
public class SupplyOrderController extends BaseController
{
    @Autowired
    private ISupplyOrderService supplyOrderService;

    @Autowired
    private ISellerStockService sellerStockService;

    /**
     * 查询供应订单（卖家向平台的进货订单）列表
     */
    @PreAuthorize("@ss.hasPermi('system:supplyOrder:list')")
    @GetMapping("/list")
    public TableDataInfo list(SupplyOrder supplyOrder)
    {
        startPage();
        List<SupplyOrder> list = supplyOrderService.selectSupplyOrderList(supplyOrder);
        return getDataTable(list);
    }

    /**
     * 导出供应订单（卖家向平台的进货订单）列表
     */
    @PreAuthorize("@ss.hasPermi('system:supplyOrder:export')")
    @Log(title = "供应订单（卖家向平台的进货订单）", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SupplyOrder supplyOrder)
    {
        List<SupplyOrder> list = supplyOrderService.selectSupplyOrderList(supplyOrder);
        ExcelUtil<SupplyOrder> util = new ExcelUtil<SupplyOrder>(SupplyOrder.class);
        util.exportExcel(response, list, "供应订单（卖家向平台的进货订单）数据");
    }

    /**
     * 获取供应订单（卖家向平台的进货订单）详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:supplyOrder:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(supplyOrderService.selectSupplyOrderById(id));
    }

    /**
     * 新增供应订单（卖家向平台的进货订单）
     */
    @PreAuthorize("@ss.hasPermi('system:supplyOrder:add')")
    @RepeatSubmit
    @Log(title = "供应订单（卖家向平台的进货订单）", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SupplyOrder supplyOrder)
    {
        return toAjax(supplyOrderService.insertSupplyOrder(supplyOrder));
    }

    /**
     * 修改供应订单（卖家向平台的进货订单）
     */
    @PreAuthorize("@ss.hasPermi('system:supplyOrder:edit')")
    @Log(title = "供应订单（卖家向平台的进货订单）", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SupplyOrder supplyOrder)
    {
        return toAjax(supplyOrderService.updateSupplyOrder(supplyOrder));
    }

    /**
     * 删除供应订单（卖家向平台的进货订单）
     */
    @PreAuthorize("@ss.hasPermi('system:supplyOrder:remove')")
    @Log(title = "供应订单（卖家向平台的进货订单）", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(supplyOrderService.deleteSupplyOrderByIds(ids));
    }

    /**
     * 去付款（商家付款按钮）
     */
    @PreAuthorize("@ss.hasPermi('system:supplyOrder:toPayForSupplyOrder')")
    @RepeatSubmit
    @PostMapping("/toPayForSupplyOrder")
    public AjaxResult toPayForSupplyOrder(@RequestBody List<SupplyOrder> supplyOrder)
    {
        for (int i = 0; i < supplyOrder.size(); i++) {
            //供货订单的id
            Long supplyOrderId = supplyOrder.get(i).getId();
            SupplyOrder supplyOrderVo = supplyOrderService.selectSupplyOrderById(supplyOrderId);
            if (StringUtils.isNull(supplyOrder)){
                return AjaxResult.error("有订单发生错误");
            }
            //如果不是未支付状态
            if (supplyOrderVo.getStatus() != 0){
                if (supplyOrderVo.getStatus() == 3){
                    return AjaxResult.error("订单号为" + supplyOrderVo.getOrderCode() + "的订单已经取消，请重新下单");
                }
                if (supplyOrderVo.getStatus() == 1){
                    return AjaxResult.error("订单号为" + supplyOrderVo.getOrderCode() + "的订单已经支付过");
                }
                if (supplyOrderVo.getStatus() == 4 | supplyOrderVo.getStatus() == 5){
                    return AjaxResult.error("订单号为" + supplyOrderVo.getOrderCode() + "的订单已经完成");
                }
            }
        }
        //如果所有订单都正常
        for (int i = 0; i < supplyOrder.size(); i++) {
            supplyOrder.get(i).setStatus(1);
            supplyOrder.get(i).setPayTime(DateUtils.getNowDate());
            supplyOrderService.updateSupplyOrder(supplyOrder.get(i));
        }
        return AjaxResult.success();
    }

    /**
     * 通过、驳回审核按钮（平台审核进货订单）
     */
    @PreAuthorize("@ss.hasPermi('system:supplyOrder:updateStatus')")
    @RepeatSubmit
    @GetMapping("/updateStatus")
    public AjaxResult updateStatus(Long id , Integer status)
    {
        SupplyOrder supplyOrder = supplyOrderService.selectSupplyOrderById(id);
        if (StringUtils.isNull(supplyOrder)){
            return AjaxResult.error("获取订单信息错误");
        }
        //如果不是已支付状态
        if (supplyOrder.getStatus() != 1){
            if (supplyOrder.getStatus() == 0){
                return AjaxResult.error("订单号为" + supplyOrder.getOrderCode() + "的订单还未支付");
            }
            if (supplyOrder.getStatus() == 2){
                return AjaxResult.error("订单号为" + supplyOrder.getOrderCode() + "的订单已经取消，请重新下单");
            }
            if (supplyOrder.getStatus() == 4 | supplyOrder.getStatus() == 5){
                return AjaxResult.error("订单号为" + supplyOrder.getOrderCode() + "的订单已经完成");
            }
        }
        //如果已经支付且未审核，执行通过操作
        supplyOrder.setStatus(status);
        supplyOrder.setUpdateTime(DateUtils.getNowDate());
        //变更审核状态
        supplyOrderService.updateSupplyOrder(supplyOrder);
        //如果审核是通过,则向商家仓库添加库存
        if (supplyOrder.getStatus() == 4){
            SellerStock sellerStock = sellerStockService.selectSellerStockByGoodsIdAndSellerId(supplyOrder.getGoodsId(), supplyOrder.getSellerId());
            //如果此商家还未有此商品的库存信息，则添加信息
            if (StringUtils.isNull(sellerStock)){
                sellerStock = new SellerStock();
                sellerStock.setGoodsId(supplyOrder.getGoodsId());
                sellerStock.setSellerId(supplyOrder.getSellerId());
                sellerStock.setStockQuantity(supplyOrder.getQuantity());
                sellerStockService.insertSellerStock(sellerStock);
            }else {
                int stockQuantityAfter = sellerStock.getStockQuantity() + supplyOrder.getQuantity();
                //变更库存数量
                sellerStock.setStockQuantity(stockQuantityAfter);
                sellerStockService.updateSellerStock(sellerStock);
            }
        }
        return AjaxResult.success();
    }
}
