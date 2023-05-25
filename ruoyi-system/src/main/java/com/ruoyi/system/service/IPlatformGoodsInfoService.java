package com.ruoyi.system.service;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.system.domain.Category;
import com.ruoyi.system.domain.PlatformGoodsInfo;

import java.util.List;

/**
 * 平台商品信息(平台的)Service接口
 * 
 * @author ruoyi
 * @date 2022-10-27
 */
public interface IPlatformGoodsInfoService 
{
    /**
     * 查询平台商品信息(平台的)
     * 
     * @param id 平台商品信息(平台的)主键
     * @return 平台商品信息(平台的)
     */
    public PlatformGoodsInfo selectPlatformGoodsInfoById(Long id);

    /**
     * 查询平台商品信息(平台的)列表
     * 
     * @param platformGoodsInfo 平台商品信息(平台的)
     * @return 平台商品信息(平台的)集合
     */
    public List<PlatformGoodsInfo> selectPlatformGoodsInfoList(PlatformGoodsInfo platformGoodsInfo);

    /**
     * 新增平台商品信息(平台的)
     * 
     * @param platformGoodsInfo 平台商品信息(平台的)
     * @return 结果
     */
    public AjaxResult insertPlatformGoodsInfo(PlatformGoodsInfo platformGoodsInfo);

    /**
     * 修改平台商品信息(平台的)
     * 
     * @param platformGoodsInfo 平台商品信息(平台的)
     * @return 结果
     */
    public AjaxResult updatePlatformGoodsInfo(PlatformGoodsInfo platformGoodsInfo);

    /**
     * 批量删除平台商品信息(平台的)
     * 
     * @param ids 需要删除的平台商品信息(平台的)主键集合
     * @return 结果
     */
    public int deletePlatformGoodsInfoByIds(Long[] ids);

    /**
     * 删除平台商品信息(平台的)信息
     * 
     * @param id 平台商品信息(平台的)主键
     * @return 结果
     */
    public int deletePlatformGoodsInfoById(Long id);

    //批量上架
    AjaxResult onTheShelf(List<Long> ids);

    //批量下架
    AjaxResult offTheShelf(List<Long> ids);

    /**
     * 导入平台商品数据
     *
     * @param platformGoodsInfos 平台商品列表
     * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据
     * @return 结果
     */
    public String importUser(List<PlatformGoodsInfo> platformGoodsInfos, Boolean isUpdateSupport);

    //爬虫拉取亚马逊商品数据
    AjaxResult getAmazonGoodsInfo(List<Long> categoryIds,Long collNum) throws Exception;

    int changeLanguageValue(PlatformGoodsInfo platformGoodsInfo);

    PlatformGoodsInfo selectPlatformGoodsInfoById(Long id, Long languageId);

    List<Category> getMinorClassList(Category category);
}
