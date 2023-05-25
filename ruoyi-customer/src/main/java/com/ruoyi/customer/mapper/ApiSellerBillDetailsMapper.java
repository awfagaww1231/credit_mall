package com.ruoyi.customer.mapper;

import com.ruoyi.customer.domain.ApiSellerBillDetails;

/**
 * C端用户账单明细Mapper接口
 * 
 * @author ruoyi
 * @date 2022-11-10
 */
public interface ApiSellerBillDetailsMapper
{

    /**
     * 新增C端用户账单明细
     *
     * @param sellerBillDetails C端用户账单明细
     * @return 结果
     */
    public int insertSellerBillDetails(ApiSellerBillDetails sellerBillDetails);
}
