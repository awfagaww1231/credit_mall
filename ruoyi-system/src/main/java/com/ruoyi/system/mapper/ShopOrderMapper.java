package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.ShopOrder;
import com.ruoyi.system.domain.SupplyOrder2;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 店铺订单Mapper接口
 * 
 * @author ruoyi
 * @date 2022-11-02
 */
public interface ShopOrderMapper 
{
    /**
     * 查询店铺订单
     * 
     * @param id 店铺订单主键
     * @return 店铺订单
     */
    public ShopOrder selectShopOrderById(Long id);

    /**
     * 查询店铺订单列表
     * 
     * @param shopOrder 店铺订单
     * @return 店铺订单集合
     */
    public List<ShopOrder> selectShopOrderList(ShopOrder shopOrder);

    /**
     * 新增店铺订单
     * 
     * @param shopOrder 店铺订单
     * @return 结果
     */
    public int insertShopOrder(ShopOrder shopOrder);

    /**
     * 修改店铺订单
     * 
     * @param shopOrder 店铺订单
     * @return 结果
     */
    public int updateShopOrder(ShopOrder shopOrder);

    /**
     * 删除店铺订单
     * 
     * @param id 店铺订单主键
     * @return 结果
     */
    public int deleteShopOrderById(Long id);

    /**
     * 批量删除店铺订单
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteShopOrderByIds(Long[] ids);

    /**
     * 提交订单至平台
     */
    int submitShopOrder(@Param("supplyOrders")List<SupplyOrder2> supplyOrders);

    /**
     * 根据店铺id查询卖家id
     */
    Long selectSellerIdByShopId(Long shopId);
}
