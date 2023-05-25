package com.ruoyi.customer.service;


import com.ruoyi.customer.domain.ApiBuyersShowTitle;

import java.util.List;

/**
 * 购物灵感标题Service接口
 * 
 * @author ruoyi
 * @date 2022-11-25
 */
public interface IApiBuyersShowTitleService
{
    /**
     * 查询购物灵感标题
     * 
     * @param id 购物灵感标题主键
     * @return 购物灵感标题
     */
    public ApiBuyersShowTitle selectBuyersShowTitleById(Long id);

    /**
     * 查询购物灵感标题列表
     * 
     * @param buyersShowTitle 购物灵感标题
     * @return 购物灵感标题集合
     */
    public List<ApiBuyersShowTitle> selectBuyersShowTitleList(ApiBuyersShowTitle buyersShowTitle);

    /**
     * 新增购物灵感标题
     * 
     * @param buyersShowTitle 购物灵感标题
     * @return 结果
     */
    public int insertBuyersShowTitle(ApiBuyersShowTitle buyersShowTitle);

    /**
     * 修改购物灵感标题
     * 
     * @param buyersShowTitle 购物灵感标题
     * @return 结果
     */
    public int updateBuyersShowTitle(ApiBuyersShowTitle buyersShowTitle);

    /**
     * 批量删除购物灵感标题
     * 
     * @param ids 需要删除的购物灵感标题主键集合
     * @return 结果
     */
    public int deleteBuyersShowTitleByIds(Long[] ids);

    /**
     * 删除购物灵感标题信息
     * 
     * @param id 购物灵感标题主键
     * @return 结果
     */
    public int deleteBuyersShowTitleById(Long id);
}
