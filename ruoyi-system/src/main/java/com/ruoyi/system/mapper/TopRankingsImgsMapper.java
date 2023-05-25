package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.TopRankingsImgs;

import java.util.List;

/**
 * 人气排行榜的轮播图片Mapper接口
 * 
 * @author ruoyi
 * @date 2022-11-21
 */
public interface TopRankingsImgsMapper 
{
    /**
     * 查询人气排行榜的轮播图片
     * 
     * @param id 人气排行榜的轮播图片主键
     * @return 人气排行榜的轮播图片
     */
    public TopRankingsImgs selectTopRankingsImgsById(Long id);

    /**
     * 查询人气排行榜的轮播图片列表
     * 
     * @param topRankingsImgs 人气排行榜的轮播图片
     * @return 人气排行榜的轮播图片集合
     */
    public List<TopRankingsImgs> selectTopRankingsImgsList(TopRankingsImgs topRankingsImgs);

    /**
     * 新增人气排行榜的轮播图片
     * 
     * @param topRankingsImgs 人气排行榜的轮播图片
     * @return 结果
     */
    public int insertTopRankingsImgs(TopRankingsImgs topRankingsImgs);

    /**
     * 修改人气排行榜的轮播图片
     * 
     * @param topRankingsImgs 人气排行榜的轮播图片
     * @return 结果
     */
    public int updateTopRankingsImgs(TopRankingsImgs topRankingsImgs);

    /**
     * 删除人气排行榜的轮播图片
     * 
     * @param id 人气排行榜的轮播图片主键
     * @return 结果
     */
    public int deleteTopRankingsImgsById(Long id);

    /**
     * 批量删除人气排行榜的轮播图片
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTopRankingsImgsByIds(Long[] ids);
}
