package com.ruoyi.system.service.impl;

import com.ruoyi.system.domain.ShipmentNumber;
import com.ruoyi.system.mapper.ShipmentNumberMapper;
import com.ruoyi.system.service.IShipmentNumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 店铺订单物流信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-11-02
 */
@Service
public class ShipmentNumberServiceImpl implements IShipmentNumberService 
{
    @Autowired
    private ShipmentNumberMapper shipmentNumberMapper;

    /**
     * 查询店铺订单物流信息
     * 
     * @param id 店铺订单物流信息主键
     * @return 店铺订单物流信息
     */
    @Override
    public ShipmentNumber selectShipmentNumberById(Long id)
    {
        return shipmentNumberMapper.selectShipmentNumberById(id);
    }

    @Override
    public ShipmentNumber selectShipmentNumberByShopOrderId(Long shopOrderId) {
        return shipmentNumberMapper.selectShipmentNumberByShopOrderId(shopOrderId);
    }

    /**
     * 查询店铺订单物流信息列表
     * 
     * @param shipmentNumber 店铺订单物流信息
     * @return 店铺订单物流信息
     */
    @Override
    public List<ShipmentNumber> selectShipmentNumberList(ShipmentNumber shipmentNumber)
    {
        return shipmentNumberMapper.selectShipmentNumberList(shipmentNumber);
    }

    /**
     * 新增店铺订单物流信息
     * 
     * @param shipmentNumber 店铺订单物流信息
     * @return 结果
     */
    @Override
    public int insertShipmentNumber(ShipmentNumber shipmentNumber)
    {
        return shipmentNumberMapper.insertShipmentNumber(shipmentNumber);
    }

    /**
     * 修改店铺订单物流信息
     * 
     * @param shipmentNumber 店铺订单物流信息
     * @return 结果
     */
    @Override
    public int updateShipmentNumber(ShipmentNumber shipmentNumber)
    {
        return shipmentNumberMapper.updateShipmentNumber(shipmentNumber);
    }

    /**
     * 批量删除店铺订单物流信息
     * 
     * @param ids 需要删除的店铺订单物流信息主键
     * @return 结果
     */
    @Override
    public int deleteShipmentNumberByIds(Long[] ids)
    {
        return shipmentNumberMapper.deleteShipmentNumberByIds(ids);
    }

    /**
     * 删除店铺订单物流信息信息
     * 
     * @param id 店铺订单物流信息主键
     * @return 结果
     */
    @Override
    public int deleteShipmentNumberById(Long id)
    {
        return shipmentNumberMapper.deleteShipmentNumberById(id);
    }
}
