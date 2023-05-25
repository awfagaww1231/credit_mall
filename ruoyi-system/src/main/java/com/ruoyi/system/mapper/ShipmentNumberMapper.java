package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.ShipmentNumber;

import java.util.List;

/**
 * 店铺订单物流信息Mapper接口
 * 
 * @author ruoyi
 * @date 2022-11-02
 */
public interface ShipmentNumberMapper 
{
    /**
     * 查询店铺订单物流信息
     * 
     * @param id 店铺订单物流信息主键
     * @return 店铺订单物流信息
     */
    public ShipmentNumber selectShipmentNumberById(Long id);

    /**
     * 查询店铺订单物流信息
     *
     * @param shopOrderId 店铺订单信息id
     * @return 店铺订单物流信息
     */
    public ShipmentNumber selectShipmentNumberByShopOrderId(Long shopOrderId);

    /**
     * 查询店铺订单物流信息列表
     * 
     * @param shipmentNumber 店铺订单物流信息
     * @return 店铺订单物流信息集合
     */
    public List<ShipmentNumber> selectShipmentNumberList(ShipmentNumber shipmentNumber);

    /**
     * 新增店铺订单物流信息
     * 
     * @param shipmentNumber 店铺订单物流信息
     * @return 结果
     */
    public int insertShipmentNumber(ShipmentNumber shipmentNumber);

    /**
     * 修改店铺订单物流信息
     * 
     * @param shipmentNumber 店铺订单物流信息
     * @return 结果
     */
    public int updateShipmentNumber(ShipmentNumber shipmentNumber);

    /**
     * 删除店铺订单物流信息
     * 
     * @param id 店铺订单物流信息主键
     * @return 结果
     */
    public int deleteShipmentNumberById(Long id);

    /**
     * 批量删除店铺订单物流信息
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteShipmentNumberByIds(Long[] ids);
}
