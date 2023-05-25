package com.ruoyi.customer.mapper;

import com.ruoyi.customer.domain.ApiCategory;
import com.ruoyi.customer.domain.ApiGoodsInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ApiCategoryMapper {

    /**
     * 查询商品类别父类列表
     */
    List<ApiCategory> categorySonList(ApiCategory apiCategory);

    //根据多语言id和类目id查询多语言值对象
    ApiCategory selectLanguageObject(@Param("languageId")Long languageId,
                                      @Param("categoryId")Long categoryId);
}
