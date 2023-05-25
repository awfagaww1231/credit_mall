package com.ruoyi.system.service.impl;

import com.ruoyi.common.annotation.XXDataScope;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.domain.SupplyOrder;
import com.ruoyi.system.mapper.SupplyOrderMapper;
import com.ruoyi.system.service.ICategoryService;
import com.ruoyi.system.service.ISupplyOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 供应订单（卖家向平台的进货订单）Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-10-29
 */
@Service
public class SupplyOrderServiceImpl implements ISupplyOrderService 
{
    @Autowired
    private SupplyOrderMapper supplyOrderMapper;

    @Autowired
    private ICategoryService categoryService;

    /**
     * 查询供应订单（卖家向平台的进货订单）
     * 
     * @param id 供应订单（卖家向平台的进货订单）主键
     * @return 供应订单（卖家向平台的进货订单）
     */
    @Override
    public SupplyOrder selectSupplyOrderById(Long id)
    {
        return supplyOrderMapper.selectSupplyOrderById(id);
    }

    /**
     * 查询供应订单（卖家向平台的进货订单）列表
     * 
     * @param supplyOrder 供应订单（卖家向平台的进货订单）
     * @return 供应订单（卖家向平台的进货订单）
     */
    @Override
    @XXDataScope(deptAlias = "d", userAlias = "u" ,sysUserIdAlias = "seller_id")
    public List<SupplyOrder> selectSupplyOrderList(SupplyOrder supplyOrder)
    {
        List<SupplyOrder> supplyOrders = supplyOrderMapper.selectSupplyOrderList(supplyOrder);
        for (int i = 0; i < supplyOrders.size(); i++) {
            String categoryName = categoryService.getCategoryName(supplyOrders.get(i).getMinorClass());
            supplyOrders.get(i).setCategoryName(categoryName);
        }
        return supplyOrders;
    }

    /**
     * 新增供应订单（卖家向平台的进货订单）
     * 
     * @param supplyOrder 供应订单（卖家向平台的进货订单）
     * @return 结果
     */
    @Override
    public int insertSupplyOrder(SupplyOrder supplyOrder)
    {
        supplyOrder.setCreateTime(DateUtils.getNowDate());
        return supplyOrderMapper.insertSupplyOrder(supplyOrder);
    }

    /**
     * 修改供应订单（卖家向平台的进货订单）
     * 
     * @param supplyOrder 供应订单（卖家向平台的进货订单）
     * @return 结果
     */
    @Override
    public int updateSupplyOrder(SupplyOrder supplyOrder)
    {
        return supplyOrderMapper.updateSupplyOrder(supplyOrder);
    }

    /**
     * 批量删除供应订单（卖家向平台的进货订单）
     * 
     * @param ids 需要删除的供应订单（卖家向平台的进货订单）主键
     * @return 结果
     */
    @Override
    public int deleteSupplyOrderByIds(Long[] ids)
    {
        return supplyOrderMapper.deleteSupplyOrderByIds(ids);
    }

    /**
     * 删除供应订单（卖家向平台的进货订单）信息
     * 
     * @param id 供应订单（卖家向平台的进货订单）主键
     * @return 结果
     */
    @Override
    public int deleteSupplyOrderById(Long id)
    {
        return supplyOrderMapper.deleteSupplyOrderById(id);
    }
}
