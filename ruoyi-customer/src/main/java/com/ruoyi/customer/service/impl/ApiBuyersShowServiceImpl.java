package com.ruoyi.customer.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.customer.domain.ApiBuyersShow;
import com.ruoyi.customer.domain.ApiBuyersShowLabel;
import com.ruoyi.customer.mapper.ApiBuyersShowLabelMapper;
import com.ruoyi.customer.mapper.ApiBuyersShowMapper;
import com.ruoyi.customer.service.IApiBuyersShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 买家秀帖子Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-11-25
 */
@Service
public class ApiBuyersShowServiceImpl implements IApiBuyersShowService
{
    @Autowired
    private ApiBuyersShowMapper buyersShowMapper;

    /**
     * 查询买家秀帖子
     * 
     * @param id 买家秀帖子主键
     * @return 买家秀帖子
     */
    @Override
    public ApiBuyersShow selectBuyersShowById(Long id)
    {
        ApiBuyersShow apiBuyersShow = buyersShowMapper.selectBuyersShowById(id);
        //根据卖家秀id查找其标签集合
        List<ApiBuyersShowLabel> apiBuyersShowLabels = buyersShowMapper.selectBindLabelByBuyersShowId(id);
        apiBuyersShow.setApiBuyersShowLabels(apiBuyersShowLabels);
        //每查看一次就增加1浏览量
        apiBuyersShow.setViews(apiBuyersShow.getViews()+1);
        buyersShowMapper.updateBuyersShow(apiBuyersShow);
        return apiBuyersShow;
    }

    /**
     * 查询买家秀帖子列表
     * 
     * @param buyersShow 买家秀帖子
     * @return 买家秀帖子
     */
    @Override
    public List<ApiBuyersShow> selectBuyersShowList(ApiBuyersShow buyersShow)
    {
        List<ApiBuyersShow> apiBuyersShows = buyersShowMapper.selectBuyersShowList(buyersShow);
        for (int i = 0; i < apiBuyersShows.size(); i++) {
            ApiBuyersShow apiBuyersShow = apiBuyersShows.get(i);
            //卖家秀id
            Long buyersShowId = apiBuyersShow.getId();
            //根据卖家秀id查找其标签集合
            List<ApiBuyersShowLabel> apiBuyersShowLabels = buyersShowMapper.selectBindLabelByBuyersShowId(buyersShowId);
            apiBuyersShows.get(i).setApiBuyersShowLabels(apiBuyersShowLabels);
        }
        return apiBuyersShows;
    }

    /**
     * 新增买家秀帖子
     * 
     * @param buyersShow 买家秀帖子
     * @return 结果
     */
    @Override
    public int insertBuyersShow(ApiBuyersShow buyersShow)
    {
        buyersShow.setCreateTime(DateUtils.getNowDate());
        return buyersShowMapper.insertBuyersShow(buyersShow);
    }

    /**
     * 修改买家秀帖子
     * 
     * @param buyersShow 买家秀帖子
     * @return 结果
     */
    @Override
    public int updateBuyersShow(ApiBuyersShow buyersShow)
    {
        return buyersShowMapper.updateBuyersShow(buyersShow);
    }

    /**
     * 批量删除买家秀帖子
     * 
     * @param ids 需要删除的买家秀帖子主键
     * @return 结果
     */
    @Override
    public int deleteBuyersShowByIds(Long[] ids)
    {
        return buyersShowMapper.deleteBuyersShowByIds(ids);
    }

    /**
     * 删除买家秀帖子信息
     * 
     * @param id 买家秀帖子主键
     * @return 结果
     */
    @Override
    public int deleteBuyersShowById(Long id)
    {
        return buyersShowMapper.deleteBuyersShowById(id);
    }
}
