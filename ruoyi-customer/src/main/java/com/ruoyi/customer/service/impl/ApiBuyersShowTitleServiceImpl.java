package com.ruoyi.customer.service.impl;

import java.util.List;

import com.ruoyi.customer.domain.ApiBuyersShowTitle;
import com.ruoyi.customer.mapper.ApiBuyersShowTitleMapper;
import com.ruoyi.customer.service.IApiBuyersShowTitleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 购物灵感标题Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-11-25
 */
@Service
public class ApiBuyersShowTitleServiceImpl implements IApiBuyersShowTitleService
{
    @Autowired
    private ApiBuyersShowTitleMapper apiBuyersShowTitleMapper;

    /**
     * 查询购物灵感标题
     * 
     * @param id 购物灵感标题主键
     * @return 购物灵感标题
     */
    @Override
    public ApiBuyersShowTitle selectBuyersShowTitleById(Long id)
    {
        return apiBuyersShowTitleMapper.selectBuyersShowTitleById(id);
    }

    /**
     * 查询购物灵感标题列表
     * 
     * @param buyersShowTitle 购物灵感标题
     * @return 购物灵感标题
     */
    @Override
    public List<ApiBuyersShowTitle> selectBuyersShowTitleList(ApiBuyersShowTitle buyersShowTitle)
    {
        return apiBuyersShowTitleMapper.selectBuyersShowTitleList(buyersShowTitle);
    }

    /**
     * 新增购物灵感标题
     * 
     * @param buyersShowTitle 购物灵感标题
     * @return 结果
     */
    @Override
    public int insertBuyersShowTitle(ApiBuyersShowTitle buyersShowTitle)
    {
        return apiBuyersShowTitleMapper.insertBuyersShowTitle(buyersShowTitle);
    }

    /**
     * 修改购物灵感标题
     * 
     * @param buyersShowTitle 购物灵感标题
     * @return 结果
     */
    @Override
    public int updateBuyersShowTitle(ApiBuyersShowTitle buyersShowTitle)
    {
        return apiBuyersShowTitleMapper.updateBuyersShowTitle(buyersShowTitle);
    }

    /**
     * 批量删除购物灵感标题
     * 
     * @param ids 需要删除的购物灵感标题主键
     * @return 结果
     */
    @Override
    public int deleteBuyersShowTitleByIds(Long[] ids)
    {
        return apiBuyersShowTitleMapper.deleteBuyersShowTitleByIds(ids);
    }

    /**
     * 删除购物灵感标题信息
     * 
     * @param id 购物灵感标题主键
     * @return 结果
     */
    @Override
    public int deleteBuyersShowTitleById(Long id)
    {
        return apiBuyersShowTitleMapper.deleteBuyersShowTitleById(id);
    }
}
