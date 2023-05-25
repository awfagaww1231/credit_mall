package com.ruoyi.system.service;

import com.ruoyi.system.domain.BannerImg;

import java.util.List;

/**
 * 轮播图Service接口
 * 
 * @author ruoyi
 * @date 2023-04-04
 */
public interface IBannerImgService 
{
    /**
     * 查询轮播图
     * 
     * @param id 轮播图主键
     * @return 轮播图
     */
    public BannerImg selectBannerImgById(Long id);

    /**
     * 查询轮播图列表
     * 
     * @param bannerImg 轮播图
     * @return 轮播图集合
     */
    public List<BannerImg> selectBannerImgList(BannerImg bannerImg);

    /**
     * 新增轮播图
     * 
     * @param bannerImg 轮播图
     * @return 结果
     */
    public int insertBannerImg(BannerImg bannerImg);

    /**
     * 修改轮播图
     * 
     * @param bannerImg 轮播图
     * @return 结果
     */
    public int updateBannerImg(BannerImg bannerImg);

    /**
     * 批量删除轮播图
     * 
     * @param ids 需要删除的轮播图主键集合
     * @return 结果
     */
    public int deleteBannerImgByIds(Long[] ids);

    /**
     * 删除轮播图信息
     * 
     * @param id 轮播图主键
     * @return 结果
     */
    public int deleteBannerImgById(Long id);
}
