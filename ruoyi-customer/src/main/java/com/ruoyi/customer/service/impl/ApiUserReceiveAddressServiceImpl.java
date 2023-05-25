package com.ruoyi.customer.service.impl;

import com.ruoyi.customer.domain.ApiUserReceiveAddress;
import com.ruoyi.customer.domain.ApiUserRechargeOrder;
import com.ruoyi.customer.mapper.ApiUserReceiveAddressMapper;
import com.ruoyi.customer.mapper.ApiUserRechargeOrderMapper;
import com.ruoyi.customer.service.IApiUserReceiveAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * 用户收货地址信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-11-10
 */
@Service
public class ApiUserReceiveAddressServiceImpl implements IApiUserReceiveAddressService
{
    @Autowired
    private ApiUserReceiveAddressMapper apiUserReceiveAddressMapper;

    /**
     * 查询用户收货地址信息
     * 
     * @param id 用户收货地址信息主键
     * @return 用户收货地址信息
     */
    @Override
    public ApiUserReceiveAddress selectUserReceiveAddressById(Long id)
    {
        return apiUserReceiveAddressMapper.selectUserReceiveAddressById(id);
    }

    /**
     * 查询用户收货地址信息列表
     * 
     * @param apiUserReceiveAddress 用户收货地址信息
     * @return 用户收货地址信息
     */
    @Override
    public List<ApiUserReceiveAddress> selectUserReceiveAddressList(ApiUserReceiveAddress apiUserReceiveAddress)
    {
        return apiUserReceiveAddressMapper.selectUserReceiveAddressList(apiUserReceiveAddress);
    }

    /**
     * 新增用户收货地址信息
     * 
     * @param apiUserReceiveAddress 用户收货地址信息
     * @return 结果
     */
    @Override
    public int insertUserReceiveAddress(ApiUserReceiveAddress apiUserReceiveAddress)
    {
        return apiUserReceiveAddressMapper.insertUserReceiveAddress(apiUserReceiveAddress);
    }

    /**
     * 修改用户收货地址信息
     * 
     * @param apiUserReceiveAddress 用户收货地址信息
     * @return 结果
     */
    @Override
    public int updateUserReceiveAddress(ApiUserReceiveAddress apiUserReceiveAddress)
    {
        return apiUserReceiveAddressMapper.updateUserReceiveAddress(apiUserReceiveAddress);
    }

    /**
     * 批量删除用户收货地址信息
     * 
     * @param ids 需要删除的用户收货地址信息主键
     * @return 结果
     */
    @Override
    public int deleteUserReceiveAddressByIds(Long[] ids)
    {
        return apiUserReceiveAddressMapper.deleteUserReceiveAddressByIds(ids);
    }

    /**
     * 删除用户收货地址信息信息
     * 
     * @param id 用户收货地址信息主键
     * @return 结果
     */
    @Override
    public int deleteUserReceiveAddressById(Long id)
    {
        return apiUserReceiveAddressMapper.deleteUserReceiveAddressById(id);
    }

    @Override
    public int clearDefaultReceiveAddress(Long userId) {
        return apiUserReceiveAddressMapper.clearDefaultReceiveAddress(userId);
    }
}
