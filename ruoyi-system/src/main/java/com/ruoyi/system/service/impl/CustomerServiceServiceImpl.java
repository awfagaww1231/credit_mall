package com.ruoyi.system.service.impl;


import com.ruoyi.common.core.redis.RedisCache;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.x.lang.LangUtils;
import com.ruoyi.system.domain.CustomerService;
import com.ruoyi.system.domain.LangMgr;
import com.ruoyi.system.mapper.CustomerServiceMapper;
import com.ruoyi.system.service.ICustomerServiceService;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

/**
 * 客服配置Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-03-22
 */
@Service
public class CustomerServiceServiceImpl implements ICustomerServiceService 
{
    @Autowired
    private CustomerServiceMapper customerServiceMapper;

    @Autowired
    private RedisCache redisCache;

    /**
     * 查询客服配置
     * 
     * @param id 客服配置主键
     * @return 客服配置
     */
    @Override
    public CustomerService selectCustomerServiceById(Long id)
    {
        return customerServiceMapper.selectCustomerServiceById(id);
    }

    /**
     * 查询客服配置列表
     * 
     * @param customerService 客服配置
     * @return 客服配置
     */
    @Override
    public List<CustomerService> selectCustomerServiceList(CustomerService customerService)
    {
        return customerServiceMapper.selectCustomerServiceList(customerService);
    }

    /**
     * 查询客服配置列表多语言
     *
     * @param customerService 客服配置
     * @return 客服配置
     */
    @Override
    public List<CustomerService> selectCustomerServiceListLang(CustomerService customerService) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        //语言
        String lang = LangUtils.getLang();
        List<CustomerService> customerServices = customerServiceMapper.selectCustomerServiceList(customerService);
        Map<String, LangMgr> langMgrs = redisCache.getCacheMap("langMgrs");
        if (StringUtils.isNull(langMgrs)){
            return customerServices;
        }
        for (int i = 0; i < customerServices.size(); i++) {
            customerService = customerServices.get(i);
            String langKey = customerService.getLangKey();
            if (StringUtils.isNotEmpty(langKey)){
                LangMgr langMgr = langMgrs.get(langKey);
                if (StringUtils.isNotNull(langMgr)){
                    customerService.setCustomerServiceName(PropertyUtils.describe(langMgr).get(lang).toString());
                }
            }
        }
        return customerServices;
    }

    /**
     * 新增客服配置
     * 
     * @param customerService 客服配置
     * @return 结果
     */
    @Override
    public int insertCustomerService(CustomerService customerService)
    {
        return customerServiceMapper.insertCustomerService(customerService);
    }

    /**
     * 修改客服配置
     * 
     * @param customerService 客服配置
     * @return 结果
     */
    @Override
    public int updateCustomerService(CustomerService customerService)
    {
        return customerServiceMapper.updateCustomerService(customerService);
    }

    /**
     * 批量删除客服配置
     * 
     * @param ids 需要删除的客服配置主键
     * @return 结果
     */
    @Override
    public int deleteCustomerServiceByIds(Long[] ids)
    {
        return customerServiceMapper.deleteCustomerServiceByIds(ids);
    }

    /**
     * 删除客服配置信息
     * 
     * @param id 客服配置主键
     * @return 结果
     */
    @Override
    public int deleteCustomerServiceById(Long id)
    {
        return customerServiceMapper.deleteCustomerServiceById(id);
    }
}
