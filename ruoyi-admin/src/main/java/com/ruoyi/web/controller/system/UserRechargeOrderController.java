package com.ruoyi.web.controller.system;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.annotation.RepeatSubmit;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.UserRechargeOrder;
import com.ruoyi.system.service.IUserRechargeOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户充值订单Controller
 * 
 * @author ruoyi
 * @date 2022-11-08
 */
@RestController
@RequestMapping("/system/userRechargeOrder")
public class UserRechargeOrderController extends BaseController
{
    @Autowired
    private IUserRechargeOrderService userRechargeOrderService;

    /**
     * 查询用户充值订单列表
     */
    @PreAuthorize("@ss.hasPermi('system:userRechargeOrder:list')")
    @GetMapping("/list")
    public TableDataInfo list(UserRechargeOrder userRechargeOrder)
    {
        startPage();
        List<UserRechargeOrder> list = userRechargeOrderService.selectUserRechargeOrderList(userRechargeOrder);
        return getDataTable(list);
    }

//    /**
//     * 导出用户充值订单列表
//     */
//    @PreAuthorize("@ss.hasPermi('system:userRechargeOrder:export')")
//    @Log(title = "用户充值订单", businessType = BusinessType.EXPORT)
//    @PostMapping("/export")
//    public void export(HttpServletResponse response, UserRechargeOrder userRechargeOrder)
//    {
//        List<UserRechargeOrder> list = userRechargeOrderService.selectUserRechargeOrderList(userRechargeOrder);
//        ExcelUtil<UserRechargeOrder> util = new ExcelUtil<UserRechargeOrder>(UserRechargeOrder.class);
//        util.exportExcel(response, list, "用户充值订单数据");
//    }

    /**
     * 获取用户充值订单详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:userRechargeOrder:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(userRechargeOrderService.selectUserRechargeOrderById(id));
    }

//    /**
//     * 新增用户充值订单
//     */
//    @PreAuthorize("@ss.hasPermi('system:userRechargeOrder:add')")
//    @Log(title = "用户充值订单", businessType = BusinessType.INSERT)
//    @PostMapping
//    public AjaxResult add(@RequestBody UserRechargeOrder userRechargeOrder)
//    {
//        return toAjax(userRechargeOrderService.insertUserRechargeOrder(userRechargeOrder));
//    }

//    /**
//     * 修改用户充值订单
//     */
//    @PreAuthorize("@ss.hasPermi('system:userRechargeOrder:edit')")
//    @Log(title = "用户充值订单", businessType = BusinessType.UPDATE)
//    @PutMapping
//    public AjaxResult edit(@RequestBody UserRechargeOrder userRechargeOrder)
//    {
//        return toAjax(userRechargeOrderService.updateUserRechargeOrder(userRechargeOrder));
//    }

//    /**
//     * 删除用户充值订单
//     */
//    @PreAuthorize("@ss.hasPermi('system:userRechargeOrder:remove')")
//    @Log(title = "用户充值订单", businessType = BusinessType.DELETE)
//	@DeleteMapping("/{ids}")
//    public AjaxResult remove(@PathVariable Long[] ids)
//    {
//        return toAjax(userRechargeOrderService.deleteUserRechargeOrderByIds(ids));
//    }

    /**
     * 通过审核
     */
    @PreAuthorize("@ss.hasPermi('system:userRechargeOrder:agree')")
    @Log(title = "用户充值订单审核通过操作", businessType = BusinessType.UPDATE)
    @RepeatSubmit
    @PostMapping("/agree")
    public AjaxResult agree(@RequestBody List<UserRechargeOrder> userRechargeOrders){
        return userRechargeOrderService.agree(userRechargeOrders);
    }

    /**
     * 驳回审核
     */
    @PreAuthorize("@ss.hasPermi('system:userRechargeOrder:reject')")
    @Log(title = "用户充值订单审核驳回操作", businessType = BusinessType.UPDATE)
    @RepeatSubmit
    @PostMapping("/reject")
    public AjaxResult reject(@RequestBody List<UserRechargeOrder> UserRechargeOrders)
    {
        return userRechargeOrderService.reject(UserRechargeOrders);
    }
}
