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
import com.ruoyi.framework.web.service.TokenService;
import com.ruoyi.system.domain.PlatformGoodsInfo;
import com.ruoyi.system.domain.SellerStock;
import com.ruoyi.system.domain.SupplyOrder;
import com.ruoyi.system.service.IPlatformGoodsInfoService;
import com.ruoyi.system.service.ISellerStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.List;

/**
 * 卖家的可售库存信息Controller
 * 
 * @author ruoyi
 * @date 2022-10-30
 */
@RestController
@RequestMapping("/system/sellerStock")
public class SellerStockController extends BaseController
{
    @Autowired
    private ISellerStockService sellerStockService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private IPlatformGoodsInfoService platformGoodsInfoService;

    /**
     * 查询卖家的可售库存信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:sellerStock:list')")
    @GetMapping("/list")
    public TableDataInfo list(SellerStock sellerStock)
    {
        startPage();
        List<SellerStock> list = sellerStockService.selectSellerStockList(sellerStock);
        return getDataTable(list);
    }

    /**
     * 导出卖家的可售库存信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:sellerStock:export')")
    @Log(title = "卖家的可售库存信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SellerStock sellerStock)
    {
        List<SellerStock> list = sellerStockService.selectSellerStockList(sellerStock);
        ExcelUtil<SellerStock> util = new ExcelUtil<SellerStock>(SellerStock.class);
        util.exportExcel(response, list, "卖家的可售库存信息数据");
    }

    /**
     * 获取卖家的可售库存信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:sellerStock:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(sellerStockService.selectSellerStockById(id));
    }

    /**
     * 新增卖家的可售库存信息
     */
    @PreAuthorize("@ss.hasPermi('system:sellerStock:add')")
    @Log(title = "卖家的可售库存信息", businessType = BusinessType.INSERT)
    @RepeatSubmit
    @PostMapping
    public AjaxResult add(@RequestBody SellerStock sellerStock)
    {
        return toAjax(sellerStockService.insertSellerStock(sellerStock));
    }

    /**
     * 修改卖家的可售库存信息
     */
    @PreAuthorize("@ss.hasPermi('system:sellerStock:edit')")
    @Log(title = "卖家的可售库存信息", businessType = BusinessType.UPDATE)
    @RepeatSubmit
    @PutMapping
    public AjaxResult edit(@RequestBody SellerStock sellerStock)
    {
        return toAjax(sellerStockService.updateSellerStock(sellerStock));
    }

    /**
     * 删除卖家的可售库存信息
     */
    @PreAuthorize("@ss.hasPermi('system:sellerStock:remove')")
    @Log(title = "卖家的可售库存信息", businessType = BusinessType.DELETE)
    @RepeatSubmit
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(sellerStockService.deleteSellerStockByIds(ids));
    }

    /**
     * 查看目前可进货的商品信息
     */
    @GetMapping("/availableForPurchase")
    public TableDataInfo availableForPurchase(PlatformGoodsInfo platformGoodsInfo)
    {
        startPage();
        List<PlatformGoodsInfo> platformGoodsInfos = sellerStockService.availableForPurchase(platformGoodsInfo);
        return getDataTable(platformGoodsInfos);
    }

    /**
     * 去进货(补货)
     */
    @PostMapping("/toRestock")
    @RepeatSubmit
    @PreAuthorize("@ss.hasPermi('system:sellerStock:toRestock')")
    public AjaxResult toRestock(@RequestBody List<SupplyOrder> supplyOrders)
    {
        SysUser user = tokenService.getLoginUser().getUser();
        Long sellerId = null;
        if (StringUtils.isNotNull(user)){
            sellerId = user.getUserId();
        }else {
            return AjaxResult.error("未获得登录信息");
        }

        //判断平台货物是否充足
        for (int i = 0; i < supplyOrders.size(); i++) {
            //订单号
            String orderCode =  String.valueOf(System.currentTimeMillis()) + String.valueOf(sellerId);
            //商品id
            Long goodsId = supplyOrders.get(i).getGoodsId();
            PlatformGoodsInfo platformGoodsInfo = platformGoodsInfoService.selectPlatformGoodsInfoById(goodsId);
            if (StringUtils.isNull(platformGoodsInfo)){
                return AjaxResult.error("商品id为" + goodsId + "的商品不存在");
            }
            if (platformGoodsInfo.getIsDel() == 1){
                return AjaxResult.error("商品id为" + goodsId + "的商品已被平台删除");
            }
            if (platformGoodsInfo.getStatus() == 1){
                return AjaxResult.error("商品id为" + goodsId + "的商品已被平台下架");
            }
            //验证商品的平台库存数量是否足够
            if (platformGoodsInfo.getGoodsInventory() < supplyOrders.get(i).getQuantity()){
                return AjaxResult.error("商品id为" + goodsId + "的商品库存不足");
            }

            //如果可以购买
            supplyOrders.get(i).setOrderCode(orderCode);
            supplyOrders.get(i).setGoodsId(goodsId);
            supplyOrders.get(i).setSinglePrice(platformGoodsInfo.getPrice());
            supplyOrders.get(i).setTotalPrice(platformGoodsInfo.getPrice().multiply(BigDecimal.valueOf(supplyOrders.get(i).getQuantity())));
            supplyOrders.get(i).setCreateTime(DateUtils.getNowDate());
            supplyOrders.get(i).setStatus(0);
            supplyOrders.get(i).setSellerId(sellerId);
        }
        return toAjax(sellerStockService.toRestock(supplyOrders));
    }

}
