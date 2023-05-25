package com.ruoyi.web.controller.system;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.annotation.RepeatSubmit;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.service.TokenService;
import com.ruoyi.system.domain.SellerRechargeOrder;
import com.ruoyi.system.service.ISellerRechargeOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 用户充值订单Controller
 * 
 * @author ruoyi
 * @date 2022-11-09
 */
@RestController
@RequestMapping("/system/sellerRechargeOrder")
public class SellerRechargeOrderController extends BaseController
{
    @Autowired
    private ISellerRechargeOrderService sellerRechargeOrderService;

    @Autowired
    private TokenService tokenService;

    /**
     * 查询用户充值订单列表
     */
    @PreAuthorize("@ss.hasPermi('system:sellerRechargeOrder:list')")
    @GetMapping("/list")
    public TableDataInfo list(SellerRechargeOrder sellerRechargeOrder)
    {
        startPage();
        List<SellerRechargeOrder> list = sellerRechargeOrderService.selectSellerRechargeOrderList(sellerRechargeOrder);
        return getDataTable(list);
    }

    /**
     * 导出用户充值订单列表
     */
    @PreAuthorize("@ss.hasPermi('system:sellerRechargeOrder:export')")
    @Log(title = "用户充值订单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SellerRechargeOrder sellerRechargeOrder)
    {
        List<SellerRechargeOrder> list = sellerRechargeOrderService.selectSellerRechargeOrderList(sellerRechargeOrder);
        ExcelUtil<SellerRechargeOrder> util = new ExcelUtil<SellerRechargeOrder>(SellerRechargeOrder.class);
        util.exportExcel(response, list, "用户充值订单数据");
    }

    /**
     * 获取用户充值订单详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:sellerRechargeOrder:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(sellerRechargeOrderService.selectSellerRechargeOrderById(id));
    }

    /**
     * 新增用户充值订单
     */
    @PreAuthorize("@ss.hasPermi('system:sellerRechargeOrder:add')")
    @Log(title = "用户充值订单", businessType = BusinessType.INSERT)
    @RepeatSubmit
    @PostMapping
    public AjaxResult add(@RequestBody SellerRechargeOrder sellerRechargeOrder)
    {
        return toAjax(sellerRechargeOrderService.insertSellerRechargeOrder(sellerRechargeOrder));
    }

    /**
     * 修改用户充值订单
     */
    @PreAuthorize("@ss.hasPermi('system:sellerRechargeOrder:edit')")
    @Log(title = "用户充值订单", businessType = BusinessType.UPDATE)
    @RepeatSubmit
    @PutMapping
    public AjaxResult edit(@RequestBody SellerRechargeOrder sellerRechargeOrder)
    {
        return toAjax(sellerRechargeOrderService.updateSellerRechargeOrder(sellerRechargeOrder));
    }

    /**
     * 删除用户充值订单
     */
    @PreAuthorize("@ss.hasPermi('system:sellerRechargeOrder:remove')")
    @Log(title = "用户充值订单", businessType = BusinessType.DELETE)
    @RepeatSubmit
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(sellerRechargeOrderService.deleteSellerRechargeOrderByIds(ids));
    }


    /**
     * 通过审核
     */
    @PreAuthorize("@ss.hasPermi('system:sellerRechargeOrder:agree')")
    @Log(title = "商户充值订单-审核通过操作", businessType = BusinessType.UPDATE)
    @RepeatSubmit
    @PostMapping("/agree")
    public AjaxResult agree(@RequestBody List<SellerRechargeOrder> sellerRechargeOrders)
    {
        return sellerRechargeOrderService.agree(sellerRechargeOrders);
    }

    /**
     * 驳回审核
     */
    @PreAuthorize("@ss.hasPermi('system:sellerRechargeOrder:reject')")
    @Log(title = "商户充值订单-审核驳回操作", businessType = BusinessType.UPDATE)
    @RepeatSubmit
    @PostMapping("/reject")
    public AjaxResult reject(@RequestBody List<SellerRechargeOrder> sellerRechargeOrders)
    {
        return sellerRechargeOrderService.reject(sellerRechargeOrders);
    }

    /**
     * 新增用户充值订单
     */
    @PostMapping("/addSellerRechargeOrder")
    @RepeatSubmit
    public AjaxResult addSellerRechargeOrder(@RequestBody SellerRechargeOrder sellerRechargeOrder)
    {
        LoginUser loginUser = tokenService.getLoginUser();
        sellerRechargeOrder.setUserId(loginUser.getUser().getUserId());
        //订单号
        String orderCode =  String.valueOf(System.currentTimeMillis()) + String.valueOf(loginUser.getUser().getUserId());
        sellerRechargeOrder.setOrderCode(orderCode);
        if (StringUtils.isNull(sellerRechargeOrder.getImgUrl()) | StringUtils.isEmpty(sellerRechargeOrder.getImgUrl())){
            return AjaxResult.error("请先上传充值凭证");
        }
        //状态为已支付
        sellerRechargeOrder.setOrderStatus(1);
        int result = sellerRechargeOrderService.insertSellerRechargeOrder(sellerRechargeOrder);
        if (result == 0){
            return AjaxResult.error();
        }
        return AjaxResult.success().put("apiUserRechargeOrder",sellerRechargeOrder);
    }

//    /**
//     * 去支付充值订单
//     */
//    @PostMapping("/toPayRechargeOrder")
//    @RepeatSubmit
//    public AjaxResult toPayRechargeOrder(@RequestBody SellerRechargeOrder sellerRechargeOrder)
//    {
//        LoginUser loginUser = tokenService.getLoginUser ();
//        if (StringUtils.isNull(sellerRechargeOrder.getImgUrl()) | StringUtils.isEmpty(sellerRechargeOrder.getImgUrl())){
//            return AjaxResult.error("请先上传充值凭证");
//        }
//        SellerRechargeOrder sellerRechargeOrderVo = sellerRechargeOrderService.selectSellerRechargeOrderById(sellerRechargeOrder.getId());
//        if (!sellerRechargeOrderVo.getUserId().equals(sellerRechargeOrder.getUserId())){
//            return AjaxResult.error("登录信息异常，请重新登录后尝试");
//        }
//        if (StringUtils.isNull(sellerRechargeOrderVo)){
//            return AjaxResult.error("订单信息异常，请刷新后尝试");
//        }
//        if (sellerRechargeOrderVo.getOrderStatus() != 0){
//            return AjaxResult.error("此订单已经支付过!");
//        }
//
//        sellerRechargeOrderVo.setOrderStatus(1);
//        return toAjax(sellerRechargeOrderService.updateSellerRechargeOrder(sellerRechargeOrder));
//    }
}
