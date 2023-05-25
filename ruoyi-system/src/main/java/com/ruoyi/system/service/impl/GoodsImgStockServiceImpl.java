package com.ruoyi.system.service.impl;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.GoodsImgStock;
import com.ruoyi.system.domain.PlatformGoodsInfo;
import com.ruoyi.system.mapper.GoodsImgStockMapper;
import com.ruoyi.system.mapper.PlatformGoodsInfoMapper;
import com.ruoyi.system.service.IGoodsImgStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 商品图片解析库Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-01-04
 */
@Service
public class GoodsImgStockServiceImpl implements IGoodsImgStockService 
{
    @Autowired
    private GoodsImgStockMapper goodsImgStockMapper;

    @Autowired
    private PlatformGoodsInfoMapper platformGoodsInfoMapper;

    /**
     * 查询商品图片解析库
     * 
     * @param id 商品图片解析库主键
     * @return 商品图片解析库
     */
    @Override
    public GoodsImgStock selectGoodsImgStockById(Long id)
    {
        return goodsImgStockMapper.selectGoodsImgStockById(id);
    }

    /**
     * 查询商品图片解析库列表
     * 
     * @param goodsImgStock 商品图片解析库
     * @return 商品图片解析库
     */
    @Override
    public List<GoodsImgStock> selectGoodsImgStockList(GoodsImgStock goodsImgStock)
    {
        return goodsImgStockMapper.selectGoodsImgStockList(goodsImgStock);
    }

    /**
     * 新增商品图片解析库
     * 
     * @param goodsImgStock 商品图片解析库
     * @return 结果
     */
    @Override
    public int insertGoodsImgStock(GoodsImgStock goodsImgStock)
    {
        return goodsImgStockMapper.insertGoodsImgStock(goodsImgStock);
    }

    /**
     * 修改商品图片解析库
     * 
     * @param goodsImgStock 商品图片解析库
     * @return 结果
     */
    @Override
    public int updateGoodsImgStock(GoodsImgStock goodsImgStock)
    {
        return goodsImgStockMapper.updateGoodsImgStock(goodsImgStock);
    }

    /**
     * 批量删除商品图片解析库
     * 
     * @param ids 需要删除的商品图片解析库主键
     * @return 结果
     */
    @Override
    public int deleteGoodsImgStockByIds(Long[] ids)
    {
        return goodsImgStockMapper.deleteGoodsImgStockByIds(ids);
    }

    /**
     * 删除商品图片解析库信息
     * 
     * @param id 商品图片解析库主键
     * @return 结果
     */
    @Override
    public int deleteGoodsImgStockById(Long id)
    {
        return goodsImgStockMapper.deleteGoodsImgStockById(id);
    }

    @Override
    public int selectCountByBatchName(String zipName) {
        return goodsImgStockMapper.selectCountByBatchName(zipName);
    }

    //批量绑定商品
    @Override
    public AjaxResult bindGoods(List<GoodsImgStock> goodsImgStocks) {
        HashMap<String, String> map = new HashMap<>();
        ArrayList<Long> list = new ArrayList<>();

        for (int i = 0; i < goodsImgStocks.size(); i++) {
            //平台商品id
            Long goodsId = goodsImgStocks.get(i).getGoodsId();
            //商品id如果不是空的，则是要为图片绑定商品
            if (StringUtils.isNotNull(goodsId)){
                PlatformGoodsInfo platformGoodsInfo = platformGoodsInfoMapper.selectPlatformGoodsInfoById(goodsId);
                if (StringUtils.isNotNull(platformGoodsInfo)){
                    String imgList = map.get("imgList-id:"+goodsId);
                    String goodsImgStocksIds = map.get("goodsImgStocks-id:"+goodsId);
                    if (StringUtils.isNull(imgList)){
                        imgList = goodsImgStocks.get(i).getGoodsImg();
                        goodsImgStocksIds = goodsImgStocks.get(i).getId().toString();
                    }else {
                        imgList = imgList + "," +  goodsImgStocks.get(i).getGoodsImg();
                        goodsImgStocksIds = goodsImgStocksIds + "," +  goodsImgStocks.get(i).getId();
                    }
                    if (imgList.split(",").length > 5){
                        return AjaxResult.error("单个商品的图片不允许超过五张");
                    }
                    //记录图片
                    map.put("imgList-id:"+goodsId,imgList);
                    map.put("goodsImgStocks-id:"+goodsId,goodsImgStocksIds);
                    //记录涉及到的平台商品id
                    list.add(goodsId);
                    //更新商品图片仓库的商品绑定信息
                    goodsImgStockMapper.updateGoodsImgStock(goodsImgStocks.get(i));
                }else{
                    return AjaxResult.error();
                }
            }else{
                //更新商品图片仓库的商品绑定信息
                goodsImgStockMapper.updateGoodsImgStock(goodsImgStocks.get(i));
            }
        }
        //为商品绑定图片
        for (int i = 0; i < list.size(); i++) {
            //商品id
            Long goodsId = list.get(i);
            //imgList
            String imgList = map.get("imgList-id:" + goodsId);
            if (StringUtils.isNotNull(imgList)){
                if (StringUtils.isNotEmpty(imgList)){
                    if (imgList.split(",").length > 0){
                        //第一张设置为图片封面
                        String goodsImg = imgList.split(",")[0];
                        PlatformGoodsInfo platformGoodsInfo = new PlatformGoodsInfo();
                        platformGoodsInfo.setId(goodsId);
                        platformGoodsInfo.setGoodsImg(goodsImg);
                        platformGoodsInfo.setImgList(imgList);
                        platformGoodsInfoMapper.updatePlatformGoodsInfo(platformGoodsInfo);
                    }
                }
            }
        }
        return AjaxResult.success();
    }
}
