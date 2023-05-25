package com.ruoyi.system.service.impl;

import com.ruoyi.common.annotation.XXDataScope;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.ShipmentNumber;
import com.ruoyi.system.domain.ShopOrder;
import com.ruoyi.system.domain.SupplyOrder2;
import com.ruoyi.system.mapper.ShopOrderMapper;
import com.ruoyi.system.service.IShipmentNumberService;
import com.ruoyi.system.service.IShopGoodsInfoService;
import com.ruoyi.system.service.IShopOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * 店铺订单Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-11-02
 */
@Service
public class ShopOrderServiceImpl implements IShopOrderService 
{
    @Autowired
    private ShopOrderMapper shopOrderMapper;

    @Autowired
    private IShipmentNumberService shipmentNumberService;

    @Autowired
    private IShopGoodsInfoService shopGoodsInfoService;

    /**
     * 查询店铺订单
     * 
     * @param id 店铺订单主键
     * @return 店铺订单
     */
    @Override
    public ShopOrder selectShopOrderById(Long id)
    {
        return shopOrderMapper.selectShopOrderById(id);
    }

    /**
     * 查询店铺订单列表
     * 
     * @param shopOrder 店铺订单
     * @return 店铺订单
     */
    @Override
    @XXDataScope(deptAlias = "d", userAlias = "u" ,sysUserIdAlias = "shop_owner_id")
    public List<ShopOrder> selectShopOrderList(ShopOrder shopOrder)
    {
        List<ShopOrder> shopOrders = shopOrderMapper.selectShopOrderList(shopOrder);
        for (int i = 0; i < shopOrders.size(); i++) {
            //获取物流订单号
            if (shopOrders.get(i).getStatus() == 3 | shopOrders.get(i).getStatus() == 4) {
                Long shopOrderId = shopOrders.get(i).getId();
                ShipmentNumber shipmentNumber = shipmentNumberService.selectShipmentNumberByShopOrderId(shopOrderId);
                if (StringUtils.isNotNull(shipmentNumber)) {
                    shopOrders.get(i).setShipmentNumber(shipmentNumber.getShipmentnumber());
                }
            }
            //获取进货价格
            //商家id
            Long sellerId = shopOrders.get(i).getSellerId();
            BigDecimal supplyPrice = shopGoodsInfoService.getSupplyPrice(sellerId, shopOrders.get(i).getSinglePrice());
            shopOrders.get(i).setPlatformPrice(supplyPrice);

            //收货人名称
            if (StringUtils.isNotNull(shopOrders.get(i).getReceiveInfo())) {
                String[] split = shopOrders.get(i).getReceiveInfo().split("/");
                if (split.length > 0) {
                    shopOrders.get(i).setReceiveName(split[0]);
                }
            }
        }
        return shopOrders;
    }

    /**
     * 新增店铺订单
     * 
     * @param shopOrder 店铺订单
     * @return 结果
     */
    @Override
    public int insertShopOrder(ShopOrder shopOrder)
    {
        shopOrder.setCreateTime(DateUtils.getNowDate());
        return shopOrderMapper.insertShopOrder(shopOrder);
    }

    /**
     * 修改店铺订单
     * 
     * @param shopOrder 店铺订单
     * @return 结果
     */
    @Override
    public int updateShopOrder(ShopOrder shopOrder)
    {
        return shopOrderMapper.updateShopOrder(shopOrder);
    }

    /**
     * 批量删除店铺订单
     * 
     * @param ids 需要删除的店铺订单主键
     * @return 结果
     */
    @Override
    public int deleteShopOrderByIds(Long[] ids)
    {
        return shopOrderMapper.deleteShopOrderByIds(ids);
    }

    /**
     * 删除店铺订单信息
     * 
     * @param id 店铺订单主键
     * @return 结果
     */
    @Override
    public int deleteShopOrderById(Long id)
    {
        return shopOrderMapper.deleteShopOrderById(id);
    }

    @Override
    public int submitShopOrder(List<SupplyOrder2> supplyOrders) {
        return shopOrderMapper.submitShopOrder(supplyOrders);
    }

    @Override
    public Long selectSellerIdByShopId(Long shopId) {
        return shopOrderMapper.selectSellerIdByShopId(shopId);
    }
}
