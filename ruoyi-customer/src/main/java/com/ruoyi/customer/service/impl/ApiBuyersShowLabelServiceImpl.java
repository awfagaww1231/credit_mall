package com.ruoyi.customer.service.impl;

import java.util.List;

import com.ruoyi.customer.domain.ApiBuyersShowLabel;
import com.ruoyi.customer.mapper.ApiBuyersShowLabelMapper;
import com.ruoyi.customer.service.IApiBuyersShowLabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 买家秀的标签Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-11-25
 */
@Service
public class ApiBuyersShowLabelServiceImpl implements IApiBuyersShowLabelService
{
    @Autowired
    private ApiBuyersShowLabelMapper buyersShowLabelMapper;

    /**
     * 查询买家秀的标签
     * 
     * @param id 买家秀的标签主键
     * @return 买家秀的标签
     */
    @Override
    public ApiBuyersShowLabel selectBuyersShowLabelById(Long id)
    {
        ApiBuyersShowLabel apiBuyersShowLabel = buyersShowLabelMapper.selectBuyersShowLabelById(id);
        //标签采用数量
        Integer num = buyersShowLabelMapper.selectPostsNumberByLabelId(id);
        if (num == null){
            num = 0;
        }
        apiBuyersShowLabel.setPostsNumber(num);
        return apiBuyersShowLabel;
    }

    /**
     * 查询买家秀的标签列表
     * 
     * @param buyersShowLabel 买家秀的标签
     * @return 买家秀的标签
     */
    @Override
    public List<ApiBuyersShowLabel> selectBuyersShowLabelList(ApiBuyersShowLabel buyersShowLabel)
    {
        return buyersShowLabelMapper.selectBuyersShowLabelList(buyersShowLabel);
    }

    @Override
    public List<ApiBuyersShowLabel> randLabelList() {
        return buyersShowLabelMapper.randLabelList();
    }

    /**
     * 新增买家秀的标签
     * 
     * @param buyersShowLabel 买家秀的标签
     * @return 结果
     */
    @Override
    public int insertBuyersShowLabel(ApiBuyersShowLabel buyersShowLabel)
    {
        return buyersShowLabelMapper.insertBuyersShowLabel(buyersShowLabel);
    }

    /**
     * 修改买家秀的标签
     * 
     * @param buyersShowLabel 买家秀的标签
     * @return 结果
     */
    @Override
    public int updateBuyersShowLabel(ApiBuyersShowLabel buyersShowLabel)
    {
        return buyersShowLabelMapper.updateBuyersShowLabel(buyersShowLabel);
    }

    /**
     * 批量删除买家秀的标签
     * 
     * @param ids 需要删除的买家秀的标签主键
     * @return 结果
     */
    @Override
    public int deleteBuyersShowLabelByIds(Long[] ids)
    {
        return buyersShowLabelMapper.deleteBuyersShowLabelByIds(ids);
    }

    /**
     * 删除买家秀的标签信息
     * 
     * @param id 买家秀的标签主键
     * @return 结果
     */
    @Override
    public int deleteBuyersShowLabelById(Long id)
    {
        return buyersShowLabelMapper.deleteBuyersShowLabelById(id);
    }
}
