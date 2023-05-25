package com.ruoyi.customer.mapper;

import com.ruoyi.customer.domain.ApiUserEvaluation;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ApiUserEvaluationMapper {
    /**
     * 新增用户评价信息
     *
     * @param apiUserEvaluation 用户评价信息
     * @return 结果
     */
    public int insertUserEvaluation(ApiUserEvaluation apiUserEvaluation);

    /**
     * 查询用户评价信息列表
     *
     * @param apiUserEvaluation 用户评价信息
     * @return 用户评价信息集合
     */
    public List<ApiUserEvaluation> selectUserEvaluationList(ApiUserEvaluation apiUserEvaluation);

    /**
     * 查询用户评价信息列表
     *
     * @param apiUserEvaluation 用户评价信息
     * @return 用户评价信息集合
     */
    public List<ApiUserEvaluation> evaluationListByShopId(ApiUserEvaluation apiUserEvaluation);

    int selectNumByScore(@Param("shopGoodsId") Long shopGoodsId,
                             @Param("score")int score);
}
