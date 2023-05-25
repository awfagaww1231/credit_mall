package com.ruoyi.system.service.impl;

import com.ruoyi.common.annotation.XXDataScope;
import com.ruoyi.system.domain.SellerBillDetails;
import com.ruoyi.system.mapper.SellerBillDetailsMapper;
import com.ruoyi.system.service.ISellerBillDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * C端用户账单明细Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-11-10
 */
@Service
public class SellerBillDetailsServiceImpl implements ISellerBillDetailsService 
{
    @Autowired
    private SellerBillDetailsMapper sellerBillDetailsMapper;

    /**
     * 查询C端用户账单明细
     * 
     * @param id C端用户账单明细主键
     * @return C端用户账单明细
     */
    @Override
    public SellerBillDetails selectSellerBillDetailsById(Long id)
    {
        return sellerBillDetailsMapper.selectSellerBillDetailsById(id);
    }

    /**
     * 查询C端用户账单明细列表
     * 
     * @param sellerBillDetails C端用户账单明细
     * @return C端用户账单明细
     */
    @Override
    @XXDataScope(deptAlias = "d", userAlias = "u" ,sysUserIdAlias = "a.user_id")
    public List<SellerBillDetails> selectSellerBillDetailsList(SellerBillDetails sellerBillDetails)
    {
        return sellerBillDetailsMapper.selectSellerBillDetailsList(sellerBillDetails);
    }

    /**
     * 新增C端用户账单明细
     * 
     * @param sellerBillDetails C端用户账单明细
     * @return 结果
     */
    @Override
    public int insertSellerBillDetails(SellerBillDetails sellerBillDetails)
    {
        return sellerBillDetailsMapper.insertSellerBillDetails(sellerBillDetails);
    }

    /**
     * 修改C端用户账单明细
     * 
     * @param sellerBillDetails C端用户账单明细
     * @return 结果
     */
    @Override
    public int updateSellerBillDetails(SellerBillDetails sellerBillDetails)
    {
        return sellerBillDetailsMapper.updateSellerBillDetails(sellerBillDetails);
    }

    /**
     * 批量删除C端用户账单明细
     * 
     * @param ids 需要删除的C端用户账单明细主键
     * @return 结果
     */
    @Override
    public int deleteSellerBillDetailsByIds(Long[] ids)
    {
        return sellerBillDetailsMapper.deleteSellerBillDetailsByIds(ids);
    }

    /**
     * 删除C端用户账单明细信息
     * 
     * @param id C端用户账单明细主键
     * @return 结果
     */
    @Override
    public int deleteSellerBillDetailsById(Long id)
    {
        return sellerBillDetailsMapper.deleteSellerBillDetailsById(id);
    }
}
