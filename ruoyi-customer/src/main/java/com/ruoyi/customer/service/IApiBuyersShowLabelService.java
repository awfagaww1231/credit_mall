package com.ruoyi.customer.service;


import com.ruoyi.customer.domain.ApiBuyersShowLabel;

import java.util.List;

/**
 * 买家秀的标签Service接口
 * 
 * @author ruoyi
 * @date 2022-11-25
 */
public interface IApiBuyersShowLabelService
{
    /**
     * 查询买家秀的标签
     * 
     * @param id 买家秀的标签主键
     * @return 买家秀的标签
     */
    public ApiBuyersShowLabel selectBuyersShowLabelById(Long id);

    /**
     * 查询买家秀的标签列表
     * 
     * @param buyersShowLabel 买家秀的标签
     * @return 买家秀的标签集合
     */
    public List<ApiBuyersShowLabel> selectBuyersShowLabelList(ApiBuyersShowLabel buyersShowLabel);


    /**
     * 随机获取30个标签
     */
    public List<ApiBuyersShowLabel> randLabelList();

    /**
     * 新增买家秀的标签
     * 
     * @param buyersShowLabel 买家秀的标签
     * @return 结果
     */
    public int insertBuyersShowLabel(ApiBuyersShowLabel buyersShowLabel);

    /**
     * 修改买家秀的标签
     * 
     * @param buyersShowLabel 买家秀的标签
     * @return 结果
     */
    public int updateBuyersShowLabel(ApiBuyersShowLabel buyersShowLabel);

    /**
     * 批量删除买家秀的标签
     * 
     * @param ids 需要删除的买家秀的标签主键集合
     * @return 结果
     */
    public int deleteBuyersShowLabelByIds(Long[] ids);

    /**
     * 删除买家秀的标签信息
     * 
     * @param id 买家秀的标签主键
     * @return 结果
     */
    public int deleteBuyersShowLabelById(Long id);
}
