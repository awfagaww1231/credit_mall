package com.ruoyi.system.service.impl;

import com.ruoyi.system.domain.TopRankingsImgs;
import com.ruoyi.system.mapper.TopRankingsImgsMapper;
import com.ruoyi.system.service.ITopRankingsImgsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 人气排行榜的轮播图片Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-11-21
 */
@Service
public class TopRankingsImgsServiceImpl implements ITopRankingsImgsService 
{
    @Autowired
    private TopRankingsImgsMapper topRankingsImgsMapper;

    /**
     * 查询人气排行榜的轮播图片
     * 
     * @param id 人气排行榜的轮播图片主键
     * @return 人气排行榜的轮播图片
     */
    @Override
    public TopRankingsImgs selectTopRankingsImgsById(Long id)
    {
        return topRankingsImgsMapper.selectTopRankingsImgsById(id);
    }

    /**
     * 查询人气排行榜的轮播图片列表
     * 
     * @param topRankingsImgs 人气排行榜的轮播图片
     * @return 人气排行榜的轮播图片
     */
    @Override
    public List<TopRankingsImgs> selectTopRankingsImgsList(TopRankingsImgs topRankingsImgs)
    {
        return topRankingsImgsMapper.selectTopRankingsImgsList(topRankingsImgs);
    }

    /**
     * 新增人气排行榜的轮播图片
     * 
     * @param topRankingsImgs 人气排行榜的轮播图片
     * @return 结果
     */
    @Override
    public int insertTopRankingsImgs(TopRankingsImgs topRankingsImgs)
    {
        return topRankingsImgsMapper.insertTopRankingsImgs(topRankingsImgs);
    }

    /**
     * 修改人气排行榜的轮播图片
     * 
     * @param topRankingsImgs 人气排行榜的轮播图片
     * @return 结果
     */
    @Override
    public int updateTopRankingsImgs(TopRankingsImgs topRankingsImgs)
    {
        return topRankingsImgsMapper.updateTopRankingsImgs(topRankingsImgs);
    }

    /**
     * 批量删除人气排行榜的轮播图片
     * 
     * @param ids 需要删除的人气排行榜的轮播图片主键
     * @return 结果
     */
    @Override
    public int deleteTopRankingsImgsByIds(Long[] ids)
    {
        return topRankingsImgsMapper.deleteTopRankingsImgsByIds(ids);
    }

    /**
     * 删除人气排行榜的轮播图片信息
     * 
     * @param id 人气排行榜的轮播图片主键
     * @return 结果
     */
    @Override
    public int deleteTopRankingsImgsById(Long id)
    {
        return topRankingsImgsMapper.deleteTopRankingsImgsById(id);
    }
}
