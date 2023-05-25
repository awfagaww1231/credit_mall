package com.ruoyi.customer.service;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.customer.domain.ApiShopOrder;

import java.util.List;

public interface IApiShopOrderService {

    /**
     * 新增店铺订单
     */
    AjaxResult insertShopOrder(List<ApiShopOrder> apiShopOrders,Long userId) throws InterruptedException;

    /**
     * 去结算店铺商品订单
     */
    AjaxResult toPayShopOrder(List<ApiShopOrder> apiShopOrders,Long userId);

    /**
     * 查看我的订单
     */
    List<ApiShopOrder> myShopOrder(ApiShopOrder apiShopOrder);

    /**
     * 查询店铺订单
     *
     * @param id 店铺订单主键
     * @return 店铺订单
     */
    public ApiShopOrder selectShopOrderById(Long id);

    /**
     * 修改店铺订单
     *
     * @param apiShopOrder 店铺订单
     * @return 结果
     */
    public int updateShopOrder(ApiShopOrder apiShopOrder);

    /**
     * 确认收货
     */
    AjaxResult toReceipt(Long id,Long userId);


    /**
     * 批量删除店铺订单
     *
     * @param ids 需要删除的店铺订单主键集合
     * @return 结果
     */
    public AjaxResult deleteShopOrderByIds(List<Long> ids);
}
