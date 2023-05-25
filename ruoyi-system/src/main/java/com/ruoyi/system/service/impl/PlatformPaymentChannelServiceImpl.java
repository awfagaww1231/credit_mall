package com.ruoyi.system.service.impl;

import com.ruoyi.system.domain.PlatformPaymentChannel;
import com.ruoyi.system.mapper.PlatformPaymentChannelMapper;
import com.ruoyi.system.service.IPlatformPaymentChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 平台收款通道配置Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-03-20
 */
@Service
public class PlatformPaymentChannelServiceImpl implements IPlatformPaymentChannelService 
{
    @Autowired
    private PlatformPaymentChannelMapper platformPaymentChannelMapper;

    /**
     * 查询平台收款通道配置
     * 
     * @param id 平台收款通道配置主键
     * @return 平台收款通道配置
     */
    @Override
    public PlatformPaymentChannel selectPlatformPaymentChannelById(Long id)
    {
        return platformPaymentChannelMapper.selectPlatformPaymentChannelById(id);
    }

    /**
     * 查询平台收款通道配置列表
     * 
     * @param platformPaymentChannel 平台收款通道配置
     * @return 平台收款通道配置
     */
    @Override
    public List<PlatformPaymentChannel> selectPlatformPaymentChannelList(PlatformPaymentChannel platformPaymentChannel)
    {
        return platformPaymentChannelMapper.selectPlatformPaymentChannelList(platformPaymentChannel);
    }

    /**
     * 新增平台收款通道配置
     * 
     * @param platformPaymentChannel 平台收款通道配置
     * @return 结果
     */
    @Override
    public int insertPlatformPaymentChannel(PlatformPaymentChannel platformPaymentChannel)
    {
        return platformPaymentChannelMapper.insertPlatformPaymentChannel(platformPaymentChannel);
    }

    /**
     * 修改平台收款通道配置
     * 
     * @param platformPaymentChannel 平台收款通道配置
     * @return 结果
     */
    @Override
    public int updatePlatformPaymentChannel(PlatformPaymentChannel platformPaymentChannel)
    {
        return platformPaymentChannelMapper.updatePlatformPaymentChannel(platformPaymentChannel);
    }

    /**
     * 批量删除平台收款通道配置
     * 
     * @param ids 需要删除的平台收款通道配置主键
     * @return 结果
     */
    @Override
    public int deletePlatformPaymentChannelByIds(Long[] ids)
    {
        return platformPaymentChannelMapper.deletePlatformPaymentChannelByIds(ids);
    }

    /**
     * 删除平台收款通道配置信息
     * 
     * @param id 平台收款通道配置主键
     * @return 结果
     */
    @Override
    public int deletePlatformPaymentChannelById(Long id)
    {
        return platformPaymentChannelMapper.deletePlatformPaymentChannelById(id);
    }
}
