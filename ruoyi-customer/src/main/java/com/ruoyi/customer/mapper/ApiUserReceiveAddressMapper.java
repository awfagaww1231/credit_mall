package com.ruoyi.customer.mapper;

import java.util.List;

import com.ruoyi.customer.domain.ApiUserReceiveAddress;


/**
 * 用户收货地址信息Mapper接口
 * 
 * @author ruoyi
 * @date 2022-11-10
 */
public interface ApiUserReceiveAddressMapper
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
     * @param userReceiveAddress 用户收货地址信息
     * @return 用户收货地址信息集合
     */
    public List<ApiUserReceiveAddress> selectUserReceiveAddressList(ApiUserReceiveAddress userReceiveAddress);

    /**
     * 新增用户收货地址信息
     * 
     * @param userReceiveAddress 用户收货地址信息
     * @return 结果
     */
    public int insertUserReceiveAddress(ApiUserReceiveAddress userReceiveAddress);

    /**
     * 修改用户收货地址信息
     * 
     * @param userReceiveAddress 用户收货地址信息
     * @return 结果
     */
    public int updateUserReceiveAddress(ApiUserReceiveAddress userReceiveAddress);

    /**
     * 删除用户收货地址信息
     * 
     * @param id 用户收货地址信息主键
     * @return 结果
     */
    public int deleteUserReceiveAddressById(Long id);

    /**
     * 批量删除用户收货地址信息
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteUserReceiveAddressByIds(Long[] ids);

    //清空用户的默认情况
    int clearDefaultReceiveAddress(Long userId);
}
