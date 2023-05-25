package com.ruoyi.system.service.impl;

import com.ruoyi.system.domain.BobiRatioConfig;
import com.ruoyi.system.domain.SellerVipConfig;
import com.ruoyi.system.mapper.BobiRatioConfigMapper;
import com.ruoyi.system.mapper.SellerVipConfigMapper;
import com.ruoyi.system.service.ISellerVipConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商家会员等级条件配置信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-11-11
 */
@Service
public class SellerVipConfigServiceImpl implements ISellerVipConfigService 
{
    @Autowired
    private SellerVipConfigMapper sellerVipConfigMapper;

    @Autowired
    private BobiRatioConfigMapper bobiRatioConfigMapper;

    /**
     * 查询商家会员等级条件配置信息
     * 
     * @param id 商家会员等级条件配置信息主键
     * @return 商家会员等级条件配置信息
     */
    @Override
    public SellerVipConfig selectSellerVipConfigById(Long id)
    {
        SellerVipConfig sellerVipConfig = sellerVipConfigMapper.selectSellerVipConfigById(id);
        //填入波比信息
        Integer vipLevel = sellerVipConfig.getVipLevel();
        BobiRatioConfig bobiRatioConfig = new BobiRatioConfig();
        bobiRatioConfig.setSellerVipLevel(vipLevel);
        List<BobiRatioConfig> bobiRatioConfigs = bobiRatioConfigMapper.selectBobiRatioConfigList(bobiRatioConfig);
        sellerVipConfig.setBobiRatioConfig(bobiRatioConfigs);
        return sellerVipConfig;
    }

    /**
     * 查询商家会员等级条件配置信息列表
     * 
     * @param sellerVipConfig 商家会员等级条件配置信息
     * @return 商家会员等级条件配置信息
     */
    @Override
    public List<SellerVipConfig> selectSellerVipConfigList(SellerVipConfig sellerVipConfig)
    {
        List<SellerVipConfig> sellerVipConfigs = sellerVipConfigMapper.selectSellerVipConfigList(sellerVipConfig);
        for (int i = 0; i < sellerVipConfigs.size(); i++) {
            //填入波比信息
            SellerVipConfig sellerVipConfigVo = sellerVipConfigs.get(i);
            Integer vipLevel = sellerVipConfigVo.getVipLevel();
            BobiRatioConfig bobiRatioConfig = new BobiRatioConfig();
            bobiRatioConfig.setSellerVipLevel(vipLevel);
            List<BobiRatioConfig> bobiRatioConfigs = bobiRatioConfigMapper.selectBobiRatioConfigList(bobiRatioConfig);
            sellerVipConfigVo.setBobiRatioConfig(bobiRatioConfigs);
        }
        return sellerVipConfigs;
    }

    /**
     * 新增商家会员等级条件配置信息
     * 
     * @param sellerVipConfig 商家会员等级条件配置信息
     * @return 结果
     */
    @Override
    public int insertSellerVipConfig(SellerVipConfig sellerVipConfig)
    {
        return sellerVipConfigMapper.insertSellerVipConfig(sellerVipConfig);
    }

    /**
     * 修改商家会员等级条件配置信息
     * 
     * @param sellerVipConfig 商家会员等级条件配置信息
     * @return 结果
     */
    @Override
    public int updateSellerVipConfig(SellerVipConfig sellerVipConfig)
    {
        List<BobiRatioConfig> bobiRatioConfig = sellerVipConfig.getBobiRatioConfig();
        for (int i = 0; i < bobiRatioConfig.size(); i++) {
            bobiRatioConfigMapper.updateBobiRatioConfig(bobiRatioConfig.get(i));
        }
        return sellerVipConfigMapper.updateSellerVipConfig(sellerVipConfig);
    }

    /**
     * 批量删除商家会员等级条件配置信息
     * 
     * @param ids 需要删除的商家会员等级条件配置信息主键
     * @return 结果
     */
    @Override
    public int deleteSellerVipConfigByIds(Long[] ids)
    {
        return sellerVipConfigMapper.deleteSellerVipConfigByIds(ids);
    }

    /**
     * 删除商家会员等级条件配置信息信息
     * 
     * @param id 商家会员等级条件配置信息主键
     * @return 结果
     */
    @Override
    public int deleteSellerVipConfigById(Long id)
    {
        return sellerVipConfigMapper.deleteSellerVipConfigById(id);
    }
}
