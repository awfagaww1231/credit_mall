package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.SupplyOrder2;
import com.ruoyi.system.domain.UserReceiveAddress;

import java.util.List;

/**
 * 供应订单（卖家向平台的进货订单）Mapper接口
 * 
 * @author ruoyi
 * @date 2022-11-02
 */
public interface SupplyOrder2Mapper 
{
    /**
     * 查询供应订单（卖家向平台的进货订单）
     * 
     * @param id 供应订单（卖家向平台的进货订单）主键
     * @return 供应订单（卖家向平台的进货订单）
     */
    public SupplyOrder2 selectSupplyOrder2ById(Long id);

    /**
     * 查询供应订单（卖家向平台的进货订单）列表
     * 
     * @param supplyOrder2 供应订单（卖家向平台的进货订单）
     * @return 供应订单（卖家向平台的进货订单）集合
     */
    public List<SupplyOrder2> selectSupplyOrder2List(SupplyOrder2 supplyOrder2);

    /**
     * 新增供应订单（卖家向平台的进货订单）
     * 
     * @param supplyOrder2 供应订单（卖家向平台的进货订单）
     * @return 结果
     */
    public int insertSupplyOrder2(SupplyOrder2 supplyOrder2);

    /**
     * 修改供应订单（卖家向平台的进货订单）
     * 
     * @param supplyOrder2 供应订单（卖家向平台的进货订单）
     * @return 结果
     */
    public int updateSupplyOrder2(SupplyOrder2 supplyOrder2);

    /**
     * 删除供应订单（卖家向平台的进货订单）
     * 
     * @param id 供应订单（卖家向平台的进货订单）主键
     * @return 结果
     */
    public int deleteSupplyOrder2ById(Long id);

    /**
     * 批量删除供应订单（卖家向平台的进货订单）
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSupplyOrder2ByIds(Long[] ids);

    //根据用户收货地址信息id查询信息
    UserReceiveAddress selectUserReceiveAddressById(Long id);
}
