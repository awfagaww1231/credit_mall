package com.ruoyi.system.service.impl;

import com.ruoyi.common.annotation.XXDataScope;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.ShopOrder;
import com.ruoyi.system.domain.SupplyOrder2;
import com.ruoyi.system.mapper.SupplyOrder2Mapper;
import com.ruoyi.system.service.ICategoryService;
import com.ruoyi.system.service.IShopOrderService;
import com.ruoyi.system.service.ISupplyOrder2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 供应订单（卖家向平台的进货订单）Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-11-02
 */
@Service
public class SupplyOrder2ServiceImpl implements ISupplyOrder2Service 
{
    @Autowired
    private SupplyOrder2Mapper supplyOrder2Mapper;

    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private IShopOrderService shopOrderService;


    /**
     * 查询供应订单（卖家向平台的进货订单）
     * 
     * @param id 供应订单（卖家向平台的进货订单）主键
     * @return 供应订单（卖家向平台的进货订单）
     */
    @Override
    public SupplyOrder2 selectSupplyOrder2ById(Long id)
    {
        return supplyOrder2Mapper.selectSupplyOrder2ById(id);
    }

    /**
     * 查询供应订单（卖家向平台的进货订单）列表
     * 
     * @param supplyOrder2 供应订单（卖家向平台的进货订单）
     * @return 供应订单（卖家向平台的进货订单）
     */
    @Override
    @XXDataScope(deptAlias = "d", userAlias = "u" ,sysUserIdAlias = "seller_id")
    public List<SupplyOrder2> selectSupplyOrder2List(SupplyOrder2 supplyOrder2)
    {
        List<SupplyOrder2> supplyOrder2s = supplyOrder2Mapper.selectSupplyOrder2List(supplyOrder2);
        for (int i = 0; i < supplyOrder2s.size(); i++) {
            //类目
            String categoryName = categoryService.getCategoryName(supplyOrder2s.get(i).getMinorClass());
            supplyOrder2s.get(i).setCategoryName(categoryName);

            //收货信息
            Long shopOrderId = supplyOrder2s.get(i).getShopOrderId();
            ShopOrder shopOrder = shopOrderService.selectShopOrderById(shopOrderId);
            if (StringUtils.isNotNull(shopOrder)){
                supplyOrder2s.get(i).setReceiveInfo(shopOrder.getReceiveInfo());
                if (StringUtils.isNotNull(shopOrder.getReceiveInfo())){
                    String[] split = shopOrder.getReceiveInfo().split("/");
                    if (split.length > 0){
                        supplyOrder2s.get(i).setReceiveName(split[0]);
                    }
                }
            }
        }
        return supplyOrder2s;
    }

    /**
     * 新增供应订单（卖家向平台的进货订单）
     * 
     * @param supplyOrder2 供应订单（卖家向平台的进货订单）
     * @return 结果
     */
    @Override
    public int insertSupplyOrder2(SupplyOrder2 supplyOrder2)
    {
        supplyOrder2.setCreateTime(DateUtils.getNowDate());
        return supplyOrder2Mapper.insertSupplyOrder2(supplyOrder2);
    }

    /**
     * 修改供应订单（卖家向平台的进货订单）
     * 
     * @param supplyOrder2 供应订单（卖家向平台的进货订单）
     * @return 结果
     */
    @Override
    public int updateSupplyOrder2(SupplyOrder2 supplyOrder2)
    {
        supplyOrder2.setUpdateTime(DateUtils.getNowDate());
        return supplyOrder2Mapper.updateSupplyOrder2(supplyOrder2);
    }

    /**
     * 批量删除供应订单（卖家向平台的进货订单）
     * 
     * @param ids 需要删除的供应订单（卖家向平台的进货订单）主键
     * @return 结果
     */
    @Override
    public int deleteSupplyOrder2ByIds(Long[] ids)
    {
        return supplyOrder2Mapper.deleteSupplyOrder2ByIds(ids);
    }

    /**
     * 删除供应订单（卖家向平台的进货订单）信息
     * 
     * @param id 供应订单（卖家向平台的进货订单）主键
     * @return 结果
     */
    @Override
    public int deleteSupplyOrder2ById(Long id)
    {
        return supplyOrder2Mapper.deleteSupplyOrder2ById(id);
    }
}
