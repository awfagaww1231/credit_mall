package com.ruoyi.system.service;

import com.ruoyi.system.domain.GoodsBrand;

import java.util.List;

/**
 * 商品品牌Service接口
 * 
 * @author ruoyi
 * @date 2022-11-27
 */
public interface IGoodsBrandService 
{
    /**
     * 查询商品品牌
     * 
     * @param id 商品品牌主键
     * @return 商品品牌
     */
    public GoodsBrand selectGoodsBrandById(Long id);

    /**
     * 查询商品品牌列表
     * 
     * @param goodsBrand 商品品牌
     * @return 商品品牌集合
     */
    public List<GoodsBrand> selectGoodsBrandList(GoodsBrand goodsBrand);

    /**
     * 新增商品品牌
     * 
     * @param goodsBrand 商品品牌
     * @return 结果
     */
    public int insertGoodsBrand(GoodsBrand goodsBrand);

    /**
     * 修改商品品牌
     * 
     * @param goodsBrand 商品品牌
     * @return 结果
     */
    public int updateGoodsBrand(GoodsBrand goodsBrand);

    /**
     * 批量删除商品品牌
     * 
     * @param ids 需要删除的商品品牌主键集合
     * @return 结果
     */
    public int deleteGoodsBrandByIds(Long[] ids);

    /**
     * 删除商品品牌信息
     * 
     * @param id 商品品牌主键
     * @return 结果
     */
    public int deleteGoodsBrandById(Long id);
}
