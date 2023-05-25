package com.ruoyi.web.controller.system;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.annotation.RepeatSubmit;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.SellerBillDetails;
import com.ruoyi.system.domain.ShipmentNumber;
import com.ruoyi.system.domain.ShopOrder;
import com.ruoyi.system.domain.SupplyOrder2;
import com.ruoyi.system.mapper.SysUserMapper;
import com.ruoyi.system.service.ISellerBillDetailsService;
import com.ruoyi.system.service.IShipmentNumberService;
import com.ruoyi.system.service.IShopOrderService;
import com.ruoyi.system.service.ISupplyOrder2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.List;

/**
 * 供应订单（卖家向平台的进货订单）Controller
 * 
 * @author ruoyi
 * @date 2022-11-02
 */
@RestController
@RequestMapping("/system/supplyOrder2")
public class SupplyOrder2Controller extends BaseController
{
    @Autowired
    private ISupplyOrder2Service supplyOrder2Service;

    @Autowired
    private IShipmentNumberService shipmentNumberService;

    @Autowired
    private IShopOrderService shopOrderService;

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private ISellerBillDetailsService sellerBillDetailsService;

    /**
     * 查询供应订单（卖家向平台的进货订单）列表
     */
    @PreAuthorize("@ss.hasPermi('system:supplyOrder2:list')")
    @GetMapping("/list")
    public TableDataInfo list(SupplyOrder2 supplyOrder2)
    {
        startPage();
        List<SupplyOrder2> list = supplyOrder2Service.selectSupplyOrder2List(supplyOrder2);
        return getDataTable(list);
    }

    /**
     * 导出供应订单（卖家向平台的进货订单）列表
     */
    @PreAuthorize("@ss.hasPermi('system:supplyOrder2:export')")
    @Log(title = "供应订单（卖家向平台的进货订单）", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SupplyOrder2 supplyOrder2)
    {
        List<SupplyOrder2> list = supplyOrder2Service.selectSupplyOrder2List(supplyOrder2);
        ExcelUtil<SupplyOrder2> util = new ExcelUtil<SupplyOrder2>(SupplyOrder2.class);
        util.exportExcel(response, list, "供应订单（卖家向平台的进货订单）数据");
    }

    /**
     * 获取供应订单（卖家向平台的进货订单）详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:supplyOrder2:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(supplyOrder2Service.selectSupplyOrder2ById(id));
    }

    /**
     * 新增供应订单（卖家向平台的进货订单）
     */
    @PreAuthorize("@ss.hasPermi('system:supplyOrder2:add')")
    @RepeatSubmit
    @Log(title = "供应订单（卖家向平台的进货订单）", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SupplyOrder2 supplyOrder2)
    {
        return toAjax(supplyOrder2Service.insertSupplyOrder2(supplyOrder2));
    }

    /**
     * 修改供应订单（卖家向平台的进货订单）
     */
    @PreAuthorize("@ss.hasPermi('system:supplyOrder2:edit')")
    @Log(title = "供应订单（卖家向平台的进货订单）", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SupplyOrder2 supplyOrder2)
    {
        return toAjax(supplyOrder2Service.updateSupplyOrder2(supplyOrder2));
    }

    /**
     * 删除供应订单（卖家向平台的进货订单）
     */
    @PreAuthorize("@ss.hasPermi('system:supplyOrder2:remove')")
    @Log(title = "供应订单（卖家向平台的进货订单）", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(supplyOrder2Service.deleteSupplyOrder2ByIds(ids));
    }

    /**
     * 通过、驳回审核按钮（平台审核进货订单）
     */
    @PreAuthorize("@ss.hasPermi('system:supplyOrder2:updateStatus')")
    @RepeatSubmit
    @GetMapping("/updateStatus")
    public AjaxResult updateStatus(Long id , Integer status ,String shipmentNumber)
    {
        if (StringUtils.isEmpty(shipmentNumber) && status == 4){
            return AjaxResult.error("请填写物流单号");
        }
        SupplyOrder2 supplyOrder = supplyOrder2Service.selectSupplyOrder2ById(id);
        if (StringUtils.isNull(supplyOrder)){
            return AjaxResult.error("获取订单信息错误");
        }
        //如果不是已支付状态
        if (supplyOrder.getStatus() != 1){
            if (supplyOrder.getStatus() == 0){
                return AjaxResult.error("订单号为" + supplyOrder.getOrderCode() + "的订单商家还未支付");
            }
            if (supplyOrder.getStatus() == 2){
                return AjaxResult.error("订单号为" + supplyOrder.getOrderCode() + "的订单商家已经取消，请重新下单");
            }
            if (supplyOrder.getStatus() == 3){
                return AjaxResult.error("订单号为" + supplyOrder.getOrderCode() + "的订单平台已经发货");
            }
            if (supplyOrder.getStatus() == 4 | supplyOrder.getStatus() == 5){
                return AjaxResult.error("订单号为" + supplyOrder.getOrderCode() + "的订单已经审核过");
            }
            else {
                return AjaxResult.error();
            }

        }
        //店铺订单信息
        ShopOrder shopOrder = shopOrderService.selectShopOrderById(supplyOrder.getShopOrderId());
        if (StringUtils.isNull(shopOrder)){
            return AjaxResult.error("订单信息异常，请联系管理员查看原因");
        }

        //如果已经支付且未审核，执行通过操作
        supplyOrder.setStatus(status);
        supplyOrder.setUpdateTime(DateUtils.getNowDate());
        //变更审核状态
        supplyOrder2Service.updateSupplyOrder2(supplyOrder);

        if (supplyOrder.getStatus() == 4){
            //如果审核是通过,则添加订单的物流信息
            ShipmentNumber shipmentNumberVo = new ShipmentNumber();
            shipmentNumberVo.setShipmentnumber(shipmentNumber);
            shipmentNumberVo.setShopOrderId(supplyOrder.getShopOrderId());
            shipmentNumberService.insertShipmentNumber(shipmentNumberVo);

            //变更店铺订单的状态为已发货
            shopOrder.setStatus(3);
            //更新店铺订单发货时间
            shopOrder.setShipTime(DateUtils.getNowDate());
            shopOrderService.updateShopOrder(shopOrder);
            //更新平台供应订单发货时间
            supplyOrder.setShipTime(shopOrder.getShipTime());
            supplyOrder2Service.updateSupplyOrder2(supplyOrder);

        }else if (supplyOrder.getStatus() == 5){
            //变更店铺订单的状态为平台驳回
            shopOrder.setStatus(6);
            shopOrderService.updateShopOrder(shopOrder);
            //退钱
            BigDecimal totalPrice = supplyOrder.getTotalPrice();
            SysUser user = sysUserMapper.selectUserById(supplyOrder.getSellerId());
            BigDecimal userAmount = user.getAmount();
            if (userAmount == null){
                userAmount = BigDecimal.ZERO;
            }
            user.setAmount(userAmount.add(totalPrice));
            sysUserMapper.updateUser(user);

            //插入流水记录
            SellerBillDetails sellerBillDetails = new SellerBillDetails();
            sellerBillDetails.setOrderCode(supplyOrder.getOrderCode());
            sellerBillDetails.setOrderAmount(supplyOrder.getTotalPrice());
            sellerBillDetails.setOrderType(0);
            //流水时间为订单提交至平台的时间
            sellerBillDetails.setOrderTime(supplyOrder.getCreateTime());
            sellerBillDetails.setUserId(supplyOrder.getSellerId());
            sellerBillDetails.setOrderClass(3);
            sellerBillDetails.setAmountBefore(userAmount);
            sellerBillDetails.setAmountAfter(userAmount.add(supplyOrder.getTotalPrice()));
            sellerBillDetailsService.insertSellerBillDetails(sellerBillDetails);
        }
        return AjaxResult.success();
    }
}
