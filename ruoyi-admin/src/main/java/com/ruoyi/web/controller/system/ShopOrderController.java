package com.ruoyi.web.controller.system;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.annotation.RepeatSubmit;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.service.TokenService;
import com.ruoyi.system.domain.PlatformGoodsInfo;
import com.ruoyi.system.domain.SellerBillDetails;
import com.ruoyi.system.domain.ShopOrder;
import com.ruoyi.system.domain.SupplyOrder2;
import com.ruoyi.system.mapper.SysUserMapper;
import com.ruoyi.system.service.IPlatformGoodsInfoService;
import com.ruoyi.system.service.ISellerBillDetailsService;
import com.ruoyi.system.service.IShopGoodsInfoService;
import com.ruoyi.system.service.IShopOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 店铺订单Controller
 * 
 * @author ruoyi
 * @date 2022-11-02
 */
@RestController
@RequestMapping("/system/shopOrder")
public class ShopOrderController extends BaseController
{
    @Autowired
    private IShopOrderService shopOrderService;

    @Autowired
    private IPlatformGoodsInfoService platformGoodsInfoService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private ISellerBillDetailsService sellerBillDetailsService;

    @Autowired
    private IShopGoodsInfoService shopGoodsInfoService;
    /**
     * 查询店铺订单列表
     */
    @PreAuthorize("@ss.hasPermi('system:shopOrder:list')")
    @GetMapping("/list")
    public TableDataInfo list(ShopOrder shopOrder)
    {
        startPage();
        List<ShopOrder> list = shopOrderService.selectShopOrderList(shopOrder);
        return getDataTable(list);
    }

    /**
     * 导出店铺订单列表
     */
    @PreAuthorize("@ss.hasPermi('system:shopOrder:export')")
    @Log(title = "店铺订单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ShopOrder shopOrder)
    {
        List<ShopOrder> list = shopOrderService.selectShopOrderList(shopOrder);
        ExcelUtil<ShopOrder> util = new ExcelUtil<ShopOrder>(ShopOrder.class);
        util.exportExcel(response, list, "店铺订单数据");
    }

    /**
     * 获取店铺订单详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:shopOrder:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(shopOrderService.selectShopOrderById(id));
    }

    /**
     * 新增店铺订单
     */
    @PreAuthorize("@ss.hasPermi('system:shopOrder:add')")
    @RepeatSubmit
    @Log(title = "店铺订单", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ShopOrder shopOrder)
    {
        return toAjax(shopOrderService.insertShopOrder(shopOrder));
    }

    /**
     * 修改店铺订单
     */
    @PreAuthorize("@ss.hasPermi('system:shopOrder:edit')")
    @Log(title = "店铺订单", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ShopOrder shopOrder)
    {
        return toAjax(shopOrderService.updateShopOrder(shopOrder));
    }

    /**
     * 删除店铺订单
     */
    @PreAuthorize("@ss.hasPermi('system:shopOrder:remove')")
    @Log(title = "店铺订单", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(shopOrderService.deleteShopOrderByIds(ids));
    }

    /**
     * 提交订单至平台
     */
    @PreAuthorize("@ss.hasPermi('system:shopOrder:submitShopOrder')")
    @RepeatSubmit
    @PostMapping("/submitShopOrder")
    public AjaxResult submitShopOrder(@RequestBody List<ShopOrder> shopOrders)
    {
        LoginUser loginUser = tokenService.getLoginUser();
        SysUser user = sysUserMapper.selectUserById(loginUser.getUser().getUserId());
        if (StringUtils.isNull(user)){
            return AjaxResult.error("获取用户信息异常，请重新登录后尝试");
        }
        ArrayList<SupplyOrder2> supplyOrders = new ArrayList<>();
        BigDecimal totalPrice = null;
        BigDecimal userAmount = null;
        for (int i = 0; i < shopOrders.size(); i++) {
            totalPrice = shopOrders.get(i).getPlatformPrice().multiply(BigDecimal.valueOf(shopOrders.get(i).getQuantity()));
            userAmount = user.getAmount();
            if (userAmount == null){
                userAmount = BigDecimal.ZERO;
            }
            if (userAmount.compareTo(totalPrice) == -1){
                return AjaxResult.error("您的余额不足，清先前往个人中心充值");
            }
        }
        if (userAmount == null | totalPrice == null){
            return AjaxResult.error();
        }
        for (int i = 0; i < shopOrders.size(); i++) {
            ShopOrder shopOrder = shopOrders.get(i);
            //根据店铺id查询卖家id
            Long sellerId = shopOrderService.selectSellerIdByShopId(shopOrder.getShopId());
            //订单号
            String orderCode =  String.valueOf(System.currentTimeMillis()) + String.valueOf(sellerId);
            //店铺订单状态
            if (shopOrder.getStatus() != 1){
                if (shopOrder.getStatus() == 0){
                    return AjaxResult.error("订单编号为" + shopOrder.getOrderCode() + "的订单用户还未支付");
                }
                if (shopOrder.getStatus() == 2){
                    return AjaxResult.error("订单编号为" + shopOrder.getOrderCode() + "的订单用户已经取消");
                }
                if (shopOrder.getStatus() == 3){
                    return AjaxResult.error("订单编号为" + shopOrder.getOrderCode() + "的订单平台已经发货");
                }
                if (shopOrder.getStatus() == 4){
                    return AjaxResult.error("订单编号为" + shopOrder.getOrderCode() + "的订单已经完成");
                }
                if (shopOrder.getStatus() == 5){
                    return AjaxResult.error("订单编号为" + shopOrder.getOrderCode() + "的订单已经提交至平台");
                }
                if (shopOrder.getStatus() == 7){
                    return AjaxResult.error("订单编号为" + shopOrder.getOrderCode() + "的订单已经完成");
                }if (shopOrder.getStatus() == 6){

                }
                else {
                    return AjaxResult.error();
                }
            }
            //商品id
            Long goodsId = shopOrder.getGoodsId();
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
            if (platformGoodsInfo.getGoodsInventory() < shopOrders.get(i).getQuantity()){
                return AjaxResult.error("商品id为" + goodsId + "的商品库存不足");
            }

            //变更店铺订单状态
            shopOrder.setStatus(5);
            shopOrderService.updateShopOrder(shopOrder);

            //新增平台供应订单
            SupplyOrder2 supplyOrder2 = new SupplyOrder2();
            supplyOrder2.setOrderCode(orderCode);
            supplyOrder2.setGoodsId(goodsId);
            supplyOrder2.setQuantity(shopOrder.getQuantity());
            BigDecimal supplyPrice = shopGoodsInfoService.getSupplyPrice(sellerId, shopOrder.getSinglePrice());
            supplyOrder2.setSinglePrice(supplyPrice);
            supplyOrder2.setTotalPrice(supplyPrice.multiply(BigDecimal.valueOf(shopOrder.getQuantity())));
            supplyOrder2.setCreateTime(DateUtils.getNowDate());
            supplyOrder2.setPayTime(DateUtils.getNowDate());
            supplyOrder2.setReceiveAddress(shopOrder.getReceiveInfo());
            supplyOrder2.setSellerId(sellerId);
            supplyOrder2.setShopOrderId(shopOrder.getId());
            supplyOrder2.setStatus(1);
            supplyOrder2.setRemark(shopOrder.getRemark());
            supplyOrders.add(supplyOrder2);


            //插入流水记录
            SellerBillDetails sellerBillDetails = new SellerBillDetails();
            sellerBillDetails.setOrderCode(supplyOrder2.getOrderCode());
            sellerBillDetails.setOrderAmount(supplyOrder2.getTotalPrice());
            sellerBillDetails.setOrderType(1);
            //流水时间为订单提交至平台的时间
            sellerBillDetails.setOrderTime(supplyOrder2.getCreateTime());
            sellerBillDetails.setUserId(sellerId);
            sellerBillDetails.setOrderClass(2);
            sellerBillDetails.setAmountBefore(userAmount);
            sellerBillDetails.setAmountAfter(userAmount.subtract(supplyOrder2.getTotalPrice()));
            userAmount = userAmount.subtract(supplyOrder2.getTotalPrice());
            sellerBillDetailsService.insertSellerBillDetails(sellerBillDetails);
        }

        //如果一切正常
        //扣钱
        user.setAmount(userAmount);
        sysUserMapper.updateUser(user);

        return toAjax(shopOrderService.submitShopOrder(supplyOrders));

    }

}
