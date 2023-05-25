package com.ruoyi.system.service.impl;

import com.ruoyi.system.domain.GoodsBrand;
import com.ruoyi.system.mapper.GoodsBrandMapper;
import com.ruoyi.system.service.IGoodsBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商品品牌Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-11-27
 */
@Service
public class GoodsBrandServiceImpl implements IGoodsBrandService 
{
    @Autowired
    private GoodsBrandMapper goodsBrandMapper;

    /**
     * 查询商品品牌
     * 
     * @param id 商品品牌主键
     * @return 商品品牌
     */
    @Override
    public GoodsBrand selectGoodsBrandById(Long id)
    {
        return goodsBrandMapper.selectGoodsBrandById(id);
    }

    /**
     * 查询商品品牌列表
     * 
     * @param goodsBrand 商品品牌
     * @return 商品品牌
     */
    @Override
    public List<GoodsBrand> selectGoodsBrandList(GoodsBrand goodsBrand)
    {
        return goodsBrandMapper.selectGoodsBrandList(goodsBrand);
    }

    /**
     * 新增商品品牌
     * 
     * @param goodsBrand 商品品牌
     * @return 结果
     */
    @Override
    public int insertGoodsBrand(GoodsBrand goodsBrand)
    {
        return goodsBrandMapper.insertGoodsBrand(goodsBrand);
    }

    /**
     * 修改商品品牌
     * 
     * @param goodsBrand 商品品牌
     * @return 结果
     */
    @Override
    public int updateGoodsBrand(GoodsBrand goodsBrand)
    {
        return goodsBrandMapper.updateGoodsBrand(goodsBrand);
    }

    /**
     * 批量删除商品品牌
     * 
     * @param ids 需要删除的商品品牌主键
     * @return 结果
     */
    @Override
    public int deleteGoodsBrandByIds(Long[] ids)
    {
        return goodsBrandMapper.deleteGoodsBrandByIds(ids);
    }

    /**
     * 删除商品品牌信息
     * 
     * @param id 商品品牌主键
     * @return 结果
     */
    @Override
    public int deleteGoodsBrandById(Long id)
    {
        return goodsBrandMapper.deleteGoodsBrandById(id);
    }
}
