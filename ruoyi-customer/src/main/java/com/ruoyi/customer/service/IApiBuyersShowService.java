package com.ruoyi.customer.service;


import com.ruoyi.customer.domain.ApiBuyersShow;

import java.util.List;

/**
 * 买家秀帖子Service接口
 * 
 * @author ruoyi
 * @date 2022-11-25
 */
public interface IApiBuyersShowService
{
    /**
     * 查询买家秀帖子
     * 
     * @param id 买家秀帖子主键
     * @return 买家秀帖子
     */
    public ApiBuyersShow selectBuyersShowById(Long id);

    /**
     * 查询买家秀帖子列表
     * 
     * @param buyersShow 买家秀帖子
     * @return 买家秀帖子集合
     */
    public List<ApiBuyersShow> selectBuyersShowList(ApiBuyersShow buyersShow);

    /**
     * 新增买家秀帖子
     * 
     * @param buyersShow 买家秀帖子
     * @return 结果
     */
    public int insertBuyersShow(ApiBuyersShow buyersShow);

    /**
     * 修改买家秀帖子
     * 
     * @param buyersShow 买家秀帖子
     * @return 结果
     */
    public int updateBuyersShow(ApiBuyersShow buyersShow);

    /**
     * 批量删除买家秀帖子
     * 
     * @param ids 需要删除的买家秀帖子主键集合
     * @return 结果
     */
    public int deleteBuyersShowByIds(Long[] ids);

    /**
     * 删除买家秀帖子信息
     * 
     * @param id 买家秀帖子主键
     * @return 结果
     */
    public int deleteBuyersShowById(Long id);
}
