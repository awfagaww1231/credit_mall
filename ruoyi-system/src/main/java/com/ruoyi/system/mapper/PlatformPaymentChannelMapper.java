package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.PlatformPaymentChannel;

import java.util.List;

/**
 * 平台收款通道配置Mapper接口
 * 
 * @author ruoyi
 * @date 2023-03-20
 */
public interface PlatformPaymentChannelMapper 
{
    /**
     * 查询平台收款通道配置
     * 
     * @param id 平台收款通道配置主键
     * @return 平台收款通道配置
     */
    public PlatformPaymentChannel selectPlatformPaymentChannelById(Long id);

    /**
     * 查询平台收款通道配置列表
     * 
     * @param platformPaymentChannel 平台收款通道配置
     * @return 平台收款通道配置集合
     */
    public List<PlatformPaymentChannel> selectPlatformPaymentChannelList(PlatformPaymentChannel platformPaymentChannel);

    /**
     * 新增平台收款通道配置
     * 
     * @param platformPaymentChannel 平台收款通道配置
     * @return 结果
     */
    public int insertPlatformPaymentChannel(PlatformPaymentChannel platformPaymentChannel);

    /**
     * 修改平台收款通道配置
     * 
     * @param platformPaymentChannel 平台收款通道配置
     * @return 结果
     */
    public int updatePlatformPaymentChannel(PlatformPaymentChannel platformPaymentChannel);

    /**
     * 删除平台收款通道配置
     * 
     * @param id 平台收款通道配置主键
     * @return 结果
     */
    public int deletePlatformPaymentChannelById(Long id);

    /**
     * 批量删除平台收款通道配置
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deletePlatformPaymentChannelByIds(Long[] ids);
}
