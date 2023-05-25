package com.ruoyi.system.service;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.system.domain.GoodsImgStock;

import java.util.List;

/**
 * 商品图片解析库Service接口
 * 
 * @author ruoyi
 * @date 2023-01-04
 */
public interface IGoodsImgStockService 
{
    /**
     * 查询商品图片解析库
     * 
     * @param id 商品图片解析库主键
     * @return 商品图片解析库
     */
    public GoodsImgStock selectGoodsImgStockById(Long id);

    /**
     * 查询商品图片解析库列表
     * 
     * @param goodsImgStock 商品图片解析库
     * @return 商品图片解析库集合
     */
    public List<GoodsImgStock> selectGoodsImgStockList(GoodsImgStock goodsImgStock);

    /**
     * 新增商品图片解析库
     * 
     * @param goodsImgStock 商品图片解析库
     * @return 结果
     */
    public int insertGoodsImgStock(GoodsImgStock goodsImgStock);

    /**
     * 修改商品图片解析库
     * 
     * @param goodsImgStock 商品图片解析库
     * @return 结果
     */
    public int updateGoodsImgStock(GoodsImgStock goodsImgStock);

    /**
     * 批量删除商品图片解析库
     * 
     * @param ids 需要删除的商品图片解析库主键集合
     * @return 结果
     */
    public int deleteGoodsImgStockByIds(Long[] ids);

    /**
     * 删除商品图片解析库信息
     * 
     * @param id 商品图片解析库主键
     * @return 结果
     */
    public int deleteGoodsImgStockById(Long id);

    //根据zip名称查看此批次图片上传数量
    public int selectCountByBatchName(String zipName);

    //批量绑定商品
    public AjaxResult bindGoods(List<GoodsImgStock> goodsImgStocks);
}
