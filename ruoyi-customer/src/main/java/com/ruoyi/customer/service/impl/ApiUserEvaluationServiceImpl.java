package com.ruoyi.customer.service.impl;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.customer.domain.ApiShopOrder;
import com.ruoyi.customer.domain.ApiUserEvaluation;
import com.ruoyi.customer.mapper.ApiShopOrderMapper;
import com.ruoyi.customer.mapper.ApiUserEvaluationMapper;
import com.ruoyi.customer.service.IApiUserEvaluationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ApiUserEvaluationServiceImpl implements IApiUserEvaluationService {

    @Resource
    private ApiUserEvaluationMapper apiUserEvaluationMapper;

    @Resource
    private ApiShopOrderMapper apiShopOrderMapper;



    @Override
    @Transactional
    public AjaxResult insertUserEvaluation(ApiUserEvaluation apiUserEvaluation,Long userId) {
        //商品订单id
        Long shopOrderId = apiUserEvaluation.getShopOrderId();
        ApiShopOrder apiShopOrder = apiShopOrderMapper.selectShopOrderById(shopOrderId);
        //验证订单信息是否缺失
        if (StringUtils.isNull(apiShopOrder)){
            return AjaxResult.error("获取订单信息异常，请刷新后尝试","hint_50");
        }
        if (StringUtils.isNull(apiShopOrder.getUserId())){
            return AjaxResult.error("获取订单信息异常，请刷新后尝试","hint_50");
        }
        if (StringUtils.isNull(apiShopOrder.getStatus())){
            return AjaxResult.error("获取订单信息异常，请刷新后尝试","hint_50");
        }
        //验证是否本人在操作
        if (!userId.equals(apiShopOrder.getUserId())){
            return AjaxResult.error("出错啦，请重新登录后尝试","hint_9");
        }
        if (apiShopOrder.getStatus() != 7){
            return AjaxResult.error("此订单状态异常，请刷新后尝试","hint_102");
        }
        apiUserEvaluation.setUserId(userId);
        //变更订单为已完成
        apiShopOrder.setStatus(4);
        int updateShopOrder = apiShopOrderMapper.updateShopOrder(apiShopOrder);
        if (updateShopOrder <= 0){
            throw new RuntimeException("操作失败");
        }
        apiUserEvaluation.setCreateTime(DateUtils.getNowDate());
        int insertUserEvaluation = apiUserEvaluationMapper.insertUserEvaluation(apiUserEvaluation);
        if (insertUserEvaluation <= 0){
            throw new RuntimeException("操作失败");
        }
        return AjaxResult.success();
    }

    @Override
    public List<ApiUserEvaluation> selectUserEvaluationList(ApiUserEvaluation apiUserEvaluation) {
        return apiUserEvaluationMapper.selectUserEvaluationList(apiUserEvaluation);
    }

    @Override
    public List<ApiUserEvaluation> evaluationListByShopId(ApiUserEvaluation apiUserEvaluation) {
        return apiUserEvaluationMapper.evaluationListByShopId(apiUserEvaluation);
    }

    @Override
    public Map<String, Object> evaluationDetail(ApiUserEvaluation apiUserEvaluation) {
        //五星评论条数
        int fiveScoreNum = 0;
        //四星评论条数
        int fourScoreNum = 0;
        //三星
        int threeScoreNum = 0;
        //二星
        int twoScoreNum = 0;
        //一星
        int oneScoreNum = 0;
        //平均分
        BigDecimal average = BigDecimal.ZERO;
        List<ApiUserEvaluation> apiUserEvaluations = apiUserEvaluationMapper.selectUserEvaluationList(apiUserEvaluation);
        //如果有评论
        if (apiUserEvaluations.size() > 0){
            Long shopGoodsInfoId = apiUserEvaluation.getShopGoodsInfoId();
            //五星评论条数
            fiveScoreNum = apiUserEvaluationMapper.selectNumByScore(shopGoodsInfoId, 5);
            //四星评论条数
            fourScoreNum = apiUserEvaluationMapper.selectNumByScore(shopGoodsInfoId, 4);
            //三星
            threeScoreNum = apiUserEvaluationMapper.selectNumByScore(shopGoodsInfoId, 3);
            //二星
            twoScoreNum = apiUserEvaluationMapper.selectNumByScore(shopGoodsInfoId, 2);
            //一星
            oneScoreNum = apiUserEvaluationMapper.selectNumByScore(shopGoodsInfoId, 1);
            int sum = fiveScoreNum * 5 + fourScoreNum * 4 + threeScoreNum * 3 + twoScoreNum * 2 + oneScoreNum * 1;
            average = BigDecimal.valueOf(sum).divide(BigDecimal.valueOf(apiUserEvaluations.size()),1,BigDecimal.ROUND_HALF_UP);
        }

        Map<String, Object> result = new HashMap<>();
        result.put("fiveScoreNum",fiveScoreNum);
        result.put("fourScoreNum",fourScoreNum);
        result.put("threeScoreNum",threeScoreNum);
        result.put("twoScoreNum",twoScoreNum);
        result.put("oneScoreNum",oneScoreNum);
        result.put("average",average);

        return result;
    }
}
