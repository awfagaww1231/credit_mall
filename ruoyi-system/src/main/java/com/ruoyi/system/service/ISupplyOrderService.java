package com.ruoyi.system.service;

import com.ruoyi.system.domain.SupplyOrder;

import java.util.List;

/**
 * 供应订单（卖家向平台的进货订单）Service接口
 * 
 * @author ruoyi
 * @date 2022-10-29
 */
public interface ISupplyOrderService 
{
    /**
     * 查询供应订单（卖家向平台的进货订单）
     * 
     * @param id 供应订单（卖家向平台的进货订单）主键
     * @return 供应订单（卖家向平台的进货订单）
     */
    public SupplyOrder selectSupplyOrderById(Long id);

    /**
     * 查询供应订单（卖家向平台的进货订单）列表
     * 
     * @param supplyOrder 供应订单（卖家向平台的进货订单）
     * @return 供应订单（卖家向平台的进货订单）集合
     */
    public List<SupplyOrder> selectSupplyOrderList(SupplyOrder supplyOrder);

    /**
     * 新增供应订单（卖家向平台的进货订单）
     * 
     * @param supplyOrder 供应订单（卖家向平台的进货订单）
     * @return 结果
     */
    public int insertSupplyOrder(SupplyOrder supplyOrder);

    /**
     * 修改供应订单（卖家向平台的进货订单）
     * 
     * @param supplyOrder 供应订单（卖家向平台的进货订单）
     * @return 结果
     */
    public int updateSupplyOrder(SupplyOrder supplyOrder);

    /**
     * 批量删除供应订单（卖家向平台的进货订单）
     * 
     * @param ids 需要删除的供应订单（卖家向平台的进货订单）主键集合
     * @return 结果
     */
    public int deleteSupplyOrderByIds(Long[] ids);

    /**
     * 删除供应订单（卖家向平台的进货订单）信息
     * 
     * @param id 供应订单（卖家向平台的进货订单）主键
     * @return 结果
     */
    public int deleteSupplyOrderById(Long id);
}
