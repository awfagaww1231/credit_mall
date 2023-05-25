package com.ruoyi.customer.mapper;

import com.ruoyi.customer.domain.ApiSpecificationConfig;
import com.ruoyi.customer.domain.ApiSpecificationValue;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 商品对应的规格绑定信息Mapper接口
 * 
 * @author ruoyi
 * @date 2022-11-24
 */
public interface ApiGoodsSpecificationMapper
{

    /**
     * 批量删除商品对应的规格绑定信息
     *
     * @param goodsId 平台商品的id
     * @return 结果
     */
    List<ApiSpecificationConfig> selectSpecificationConfigsByGoodsId(Long goodsId);


    List<ApiSpecificationValue> selectSpecificationValues(@Param("goodsId") Long goodsId,
                                                          @Param("specificationConfigId") Long specificationConfigId);

}
