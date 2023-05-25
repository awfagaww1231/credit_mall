package com.ruoyi.customer.service.impl;

import com.ruoyi.customer.domain.ApiUserBillDetails;
import com.ruoyi.customer.mapper.ApiUserBillDetailsMapper;
import com.ruoyi.customer.service.IApiUserBillDetailsService;
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
public class ApiUserBillDetailsServiceImpl implements IApiUserBillDetailsService
{
    @Autowired
    private ApiUserBillDetailsMapper apiUserBillDetailsMapper;

    /**
     * 查询C端用户账单明细
     * 
     * @param id C端用户账单明细主键
     * @return C端用户账单明细
     */
    @Override
    public ApiUserBillDetails selectUserBillDetailsById(Long id)
    {
        return apiUserBillDetailsMapper.selectUserBillDetailsById(id);
    }

    /**
     * 查询C端用户账单明细列表
     * 
     * @param apiUserBillDetails C端用户账单明细
     * @return C端用户账单明细
     */
    @Override
    public List<ApiUserBillDetails> selectUserBillDetailsList(ApiUserBillDetails apiUserBillDetails)
    {
        return apiUserBillDetailsMapper.selectUserBillDetailsList(apiUserBillDetails);
    }

    /**
     * 新增C端用户账单明细
     * 
     * @param apiUserBillDetails C端用户账单明细
     * @return 结果
     */
    @Override
    public int insertUserBillDetails(ApiUserBillDetails apiUserBillDetails)
    {
        return apiUserBillDetailsMapper.insertUserBillDetails(apiUserBillDetails);
    }

    /**
     * 修改C端用户账单明细
     * 
     * @param apiUserBillDetails C端用户账单明细
     * @return 结果
     */
    @Override
    public int updateUserBillDetails(ApiUserBillDetails apiUserBillDetails)
    {
        return apiUserBillDetailsMapper.updateUserBillDetails(apiUserBillDetails);
    }

    /**
     * 批量删除C端用户账单明细
     * 
     * @param ids 需要删除的C端用户账单明细主键
     * @return 结果
     */
    @Override
    public int deleteUserBillDetailsByIds(Long[] ids)
    {
        return apiUserBillDetailsMapper.deleteUserBillDetailsByIds(ids);
    }

    /**
     * 删除C端用户账单明细信息
     * 
     * @param id C端用户账单明细主键
     * @return 结果
     */
    @Override
    public int deleteUserBillDetailsById(Long id)
    {
        return apiUserBillDetailsMapper.deleteUserBillDetailsById(id);
    }
}
