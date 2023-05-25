package com.ruoyi.customer.service;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.customer.domain.ApiUserEvaluation;

import java.util.List;
import java.util.Map;

public interface IApiUserEvaluationService {

    /**
     * 新增用户评价信息
     *
     * @param apiUserEvaluation 用户评价信息
     * @return 结果
     */
    public AjaxResult insertUserEvaluation(ApiUserEvaluation apiUserEvaluation,Long userId);

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

    /**
     * 根据店铺商品id统计评论数据信息
     */
    Map<String,Object> evaluationDetail(ApiUserEvaluation apiUserEvaluation);
}
