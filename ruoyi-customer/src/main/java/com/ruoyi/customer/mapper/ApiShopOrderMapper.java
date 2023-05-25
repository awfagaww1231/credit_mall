package com.ruoyi.customer.mapper;

import com.ruoyi.customer.domain.ApiShipmentNumber;
import com.ruoyi.customer.domain.ApiShopInfo;
import com.ruoyi.customer.domain.ApiShopOrder;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

@Mapper
public interface ApiShopOrderMapper {

    /**
     * 新增店铺订单
     */
    int insertShopOrder(ApiShopOrder apiShopOrder);

    /**
     * 批量新增店铺订单
     */
    int insertShopOrders(@Param("apiShopOrders") List<ApiShopOrder> apiShopOrders);

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
     * 批量删除店铺订单
     *
     * @param ids 需要删除的店铺订单主键集合
     * @return 结果
     */
    public int deleteShopOrderByIds(@Param("ids") List<Long> ids);

    /**
     * 查询店铺订单物流信息
     *
     * @param shopOrderId 店铺订单信息id
     * @return 店铺订单物流信息
     */
    public ApiShipmentNumber selectShipmentNumberByShopOrderId(Long shopOrderId);

}
