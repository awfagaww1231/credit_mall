package com.ruoyi.customer.mapper;


import com.ruoyi.customer.domain.ApiBuyersShow;
import com.ruoyi.customer.domain.ApiBuyersShowLabel;

import java.util.List;

/**
 * 买家秀帖子Mapper接口
 * 
 * @author ruoyi
 * @date 2022-11-25
 */
public interface ApiBuyersShowMapper
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
     * 删除买家秀帖子
     * 
     * @param id 买家秀帖子主键
     * @return 结果
     */
    public int deleteBuyersShowById(Long id);

    /**
     * 批量删除买家秀帖子
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBuyersShowByIds(Long[] ids);

    //根据卖家秀id查找其标签集合
    List<ApiBuyersShowLabel> selectBindLabelByBuyersShowId(Long buyersShowId);
}
