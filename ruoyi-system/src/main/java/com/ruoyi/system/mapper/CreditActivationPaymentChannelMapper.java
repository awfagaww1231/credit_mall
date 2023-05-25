package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.CreditActivationPaymentChannel;

import java.util.List;

/**
 * 信用卡激活收款通道配置Mapper接口
 * 
 * @author ruoyi
 * @date 2023-03-27
 */
public interface CreditActivationPaymentChannelMapper 
{
    /**
     * 查询信用卡激活收款通道配置
     * 
     * @param id 信用卡激活收款通道配置主键
     * @return 信用卡激活收款通道配置
     */
    public CreditActivationPaymentChannel selectCreditActivationPaymentChannelById(Long id);

    /**
     * 查询信用卡激活收款通道配置列表
     * 
     * @param creditActivationPaymentChannel 信用卡激活收款通道配置
     * @return 信用卡激活收款通道配置集合
     */
    public List<CreditActivationPaymentChannel> selectCreditActivationPaymentChannelList(CreditActivationPaymentChannel creditActivationPaymentChannel);

    /**
     * 新增信用卡激活收款通道配置
     * 
     * @param creditActivationPaymentChannel 信用卡激活收款通道配置
     * @return 结果
     */
    public int insertCreditActivationPaymentChannel(CreditActivationPaymentChannel creditActivationPaymentChannel);

    /**
     * 修改信用卡激活收款通道配置
     * 
     * @param creditActivationPaymentChannel 信用卡激活收款通道配置
     * @return 结果
     */
    public int updateCreditActivationPaymentChannel(CreditActivationPaymentChannel creditActivationPaymentChannel);

    /**
     * 删除信用卡激活收款通道配置
     * 
     * @param id 信用卡激活收款通道配置主键
     * @return 结果
     */
    public int deleteCreditActivationPaymentChannelById(Long id);

    /**
     * 批量删除信用卡激活收款通道配置
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCreditActivationPaymentChannelByIds(Long[] ids);
}
