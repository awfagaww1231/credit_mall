package com.ruoyi.system.service;

import com.ruoyi.system.domain.SellerBillDetails;

import java.util.List;

/**
 * C端用户账单明细Service接口
 * 
 * @author ruoyi
 * @date 2022-11-10
 */
public interface ISellerBillDetailsService 
{
    /**
     * 查询C端用户账单明细
     * 
     * @param id C端用户账单明细主键
     * @return C端用户账单明细
     */
    public SellerBillDetails selectSellerBillDetailsById(Long id);

    /**
     * 查询C端用户账单明细列表
     * 
     * @param sellerBillDetails C端用户账单明细
     * @return C端用户账单明细集合
     */
    public List<SellerBillDetails> selectSellerBillDetailsList(SellerBillDetails sellerBillDetails);

    /**
     * 新增C端用户账单明细
     * 
     * @param sellerBillDetails C端用户账单明细
     * @return 结果
     */
    public int insertSellerBillDetails(SellerBillDetails sellerBillDetails);

    /**
     * 修改C端用户账单明细
     * 
     * @param sellerBillDetails C端用户账单明细
     * @return 结果
     */
    public int updateSellerBillDetails(SellerBillDetails sellerBillDetails);

    /**
     * 批量删除C端用户账单明细
     * 
     * @param ids 需要删除的C端用户账单明细主键集合
     * @return 结果
     */
    public int deleteSellerBillDetailsByIds(Long[] ids);

    /**
     * 删除C端用户账单明细信息
     * 
     * @param id C端用户账单明细主键
     * @return 结果
     */
    public int deleteSellerBillDetailsById(Long id);
}
