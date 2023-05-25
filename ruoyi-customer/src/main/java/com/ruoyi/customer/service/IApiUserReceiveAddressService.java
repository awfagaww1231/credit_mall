package com.ruoyi.customer.service;

import java.util.List;

import com.ruoyi.customer.domain.ApiUserReceiveAddress;

/**
 * 用户收货地址信息Service接口
 * 
 * @author ruoyi
 * @date 2022-11-10
 */
public interface IApiUserReceiveAddressService
{
    /**
     * 查询用户收货地址信息
     * 
     * @param id 用户收货地址信息主键
     * @return 用户收货地址信息
     */
    public ApiUserReceiveAddress selectUserReceiveAddressById(Long id);

    /**
     * 查询用户收货地址信息列表
     * 
     * @param apiUserReceiveAddress 用户收货地址信息
     * @return 用户收货地址信息集合
     */
    public List<ApiUserReceiveAddress> selectUserReceiveAddressList(ApiUserReceiveAddress apiUserReceiveAddress);

    /**
     * 新增用户收货地址信息
     * 
     * @param apiUserReceiveAddress 用户收货地址信息
     * @return 结果
     */
    public int insertUserReceiveAddress(ApiUserReceiveAddress apiUserReceiveAddress);

    /**
     * 修改用户收货地址信息
     * 
     * @param userReceiveAddress 用户收货地址信息
     * @return 结果
     */
    public int updateUserReceiveAddress(ApiUserReceiveAddress userReceiveAddress);

    /**
     * 批量删除用户收货地址信息
     * 
     * @param ids 需要删除的用户收货地址信息主键集合
     * @return 结果
     */
    public int deleteUserReceiveAddressByIds(Long[] ids);

    /**
     * 删除用户收货地址信息信息
     * 
     * @param id 用户收货地址信息主键
     * @return 结果
     */
    public int deleteUserReceiveAddressById(Long id);

    //清空用户的默认情况
    int clearDefaultReceiveAddress(Long userId);
}
