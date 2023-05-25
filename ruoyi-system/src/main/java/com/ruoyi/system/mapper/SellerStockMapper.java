package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.SellerStock;
import com.ruoyi.system.domain.SupplyOrder;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 卖家的可售库存信息Mapper接口
 * 
 * @author ruoyi
 * @date 2022-10-30
 */
public interface SellerStockMapper 
{
    /**
     * 查询卖家的可售库存信息
     * 
     * @param id 卖家的可售库存信息主键
     * @return 卖家的可售库存信息
     */
    public SellerStock selectSellerStockById(Long id);

    /**
     * 查询卖家的可售库存信息列表
     * 
     * @param sellerStock 卖家的可售库存信息
     * @return 卖家的可售库存信息集合
     */
    public List<SellerStock> selectSellerStockList(SellerStock sellerStock);

    /**
     * 新增卖家的可售库存信息
     * 
     * @param sellerStock 卖家的可售库存信息
     * @return 结果
     */
    public int insertSellerStock(SellerStock sellerStock);

    /**
     * 修改卖家的可售库存信息
     * 
     * @param sellerStock 卖家的可售库存信息
     * @return 结果
     */
    public int updateSellerStock(SellerStock sellerStock);

    /**
     * 删除卖家的可售库存信息
     * 
     * @param id 卖家的可售库存信息主键
     * @return 结果
     */
    public int deleteSellerStockById(Long id);

    /**
     * 批量删除卖家的可售库存信息
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSellerStockByIds(Long[] ids);


    /**
     * 去进货(补货)
     */
    int toRestock(@Param("supplyOrders")List<SupplyOrder> supplyOrders);

    /**
     * 查询卖家的可售库存信息
     *
     * @param goodsId 卖家的可售库存信息主键
     * @param sellerId 卖家的可售库存信息主键
     * @return 卖家的可售库存信息
     */
    SellerStock selectSellerStockByGoodsIdAndSellerId(@Param("goodsId") Long goodsId,
                                                      @Param("sellerId")Long sellerId);
}
