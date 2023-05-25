package com.ruoyi.system.service.impl;

import com.ruoyi.system.domain.CreditActivationPaymentChannel;
import com.ruoyi.system.mapper.CreditActivationPaymentChannelMapper;
import com.ruoyi.system.service.ICreditActivationPaymentChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 信用卡激活收款通道配置Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-03-27
 */
@Service
public class CreditActivationPaymentChannelServiceImpl implements ICreditActivationPaymentChannelService 
{
    @Autowired
    private CreditActivationPaymentChannelMapper creditActivationPaymentChannelMapper;

    /**
     * 查询信用卡激活收款通道配置
     * 
     * @param id 信用卡激活收款通道配置主键
     * @return 信用卡激活收款通道配置
     */
    @Override
    public CreditActivationPaymentChannel selectCreditActivationPaymentChannelById(Long id)
    {
        return creditActivationPaymentChannelMapper.selectCreditActivationPaymentChannelById(id);
    }

    /**
     * 查询信用卡激活收款通道配置列表
     * 
     * @param creditActivationPaymentChannel 信用卡激活收款通道配置
     * @return 信用卡激活收款通道配置
     */
    @Override
    public List<CreditActivationPaymentChannel> selectCreditActivationPaymentChannelList(CreditActivationPaymentChannel creditActivationPaymentChannel)
    {
        return creditActivationPaymentChannelMapper.selectCreditActivationPaymentChannelList(creditActivationPaymentChannel);
    }

    /**
     * 新增信用卡激活收款通道配置
     * 
     * @param creditActivationPaymentChannel 信用卡激活收款通道配置
     * @return 结果
     */
    @Override
    public int insertCreditActivationPaymentChannel(CreditActivationPaymentChannel creditActivationPaymentChannel)
    {
        return creditActivationPaymentChannelMapper.insertCreditActivationPaymentChannel(creditActivationPaymentChannel);
    }

    /**
     * 修改信用卡激活收款通道配置
     * 
     * @param creditActivationPaymentChannel 信用卡激活收款通道配置
     * @return 结果
     */
    @Override
    public int updateCreditActivationPaymentChannel(CreditActivationPaymentChannel creditActivationPaymentChannel)
    {
        return creditActivationPaymentChannelMapper.updateCreditActivationPaymentChannel(creditActivationPaymentChannel);
    }

    /**
     * 批量删除信用卡激活收款通道配置
     * 
     * @param ids 需要删除的信用卡激活收款通道配置主键
     * @return 结果
     */
    @Override
    public int deleteCreditActivationPaymentChannelByIds(Long[] ids)
    {
        return creditActivationPaymentChannelMapper.deleteCreditActivationPaymentChannelByIds(ids);
    }

    /**
     * 删除信用卡激活收款通道配置信息
     * 
     * @param id 信用卡激活收款通道配置主键
     * @return 结果
     */
    @Override
    public int deleteCreditActivationPaymentChannelById(Long id)
    {
        return creditActivationPaymentChannelMapper.deleteCreditActivationPaymentChannelById(id);
    }
}
