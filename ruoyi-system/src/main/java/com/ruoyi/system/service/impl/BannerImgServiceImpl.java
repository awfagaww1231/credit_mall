package com.ruoyi.system.service.impl;

import com.ruoyi.system.domain.BannerImg;
import com.ruoyi.system.mapper.BannerImgMapper;
import com.ruoyi.system.service.IBannerImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 轮播图Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-04-04
 */
@Service
public class BannerImgServiceImpl implements IBannerImgService 
{
    @Autowired
    private BannerImgMapper bannerImgMapper;

    /**
     * 查询轮播图
     * 
     * @param id 轮播图主键
     * @return 轮播图
     */
    @Override
    public BannerImg selectBannerImgById(Long id)
    {
        return bannerImgMapper.selectBannerImgById(id);
    }

    /**
     * 查询轮播图列表
     * 
     * @param bannerImg 轮播图
     * @return 轮播图
     */
    @Override
    public List<BannerImg> selectBannerImgList(BannerImg bannerImg)
    {
        return bannerImgMapper.selectBannerImgList(bannerImg);
    }

    /**
     * 新增轮播图
     * 
     * @param bannerImg 轮播图
     * @return 结果
     */
    @Override
    public int insertBannerImg(BannerImg bannerImg)
    {
        return bannerImgMapper.insertBannerImg(bannerImg);
    }

    /**
     * 修改轮播图
     * 
     * @param bannerImg 轮播图
     * @return 结果
     */
    @Override
    public int updateBannerImg(BannerImg bannerImg)
    {
        return bannerImgMapper.updateBannerImg(bannerImg);
    }

    /**
     * 批量删除轮播图
     * 
     * @param ids 需要删除的轮播图主键
     * @return 结果
     */
    @Override
    public int deleteBannerImgByIds(Long[] ids)
    {
        return bannerImgMapper.deleteBannerImgByIds(ids);
    }

    /**
     * 删除轮播图信息
     * 
     * @param id 轮播图主键
     * @return 结果
     */
    @Override
    public int deleteBannerImgById(Long id)
    {
        return bannerImgMapper.deleteBannerImgById(id);
    }
}
