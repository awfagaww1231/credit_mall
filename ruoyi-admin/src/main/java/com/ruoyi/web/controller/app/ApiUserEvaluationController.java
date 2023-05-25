package com.ruoyi.web.controller.app;

import com.ruoyi.common.annotation.RepeatSubmit;
import com.ruoyi.common.constant.HttpStatus;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.customer.common.AppletTokenService;
import com.ruoyi.customer.domain.ApiUserEvaluation;
import com.ruoyi.customer.domain.AppletLoginUser;
import com.ruoyi.customer.service.IApiUserEvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * App用户评价信息Controller +
 *
 * @author ruoyi
 * @date 2022-11-07
 */
@RestController
@RequestMapping("/api/evaluation")
public class ApiUserEvaluationController extends BaseController {

    @Autowired
    private IApiUserEvaluationService apiUserEvaluationService;

    @Autowired
    private AppletTokenService appletTokenService;

    /**
     * 新增用户评价信息
     */
    @PostMapping("/addEvaluation")
    @RepeatSubmit
    public AjaxResult addEvaluation(@RequestBody ApiUserEvaluation apiUserEvaluation)
    {
        AppletLoginUser loginUser = appletTokenService.getLoginUser ();
        if(StringUtils.isNull(loginUser)){
            return AjaxResult.error(HttpStatus.UNAUTHORIZED,"登录信息失效");
        }else {
            appletTokenService.refreshToken(loginUser);
        }
        if (StringUtils.isNull(apiUserEvaluation.getShopOrderId())){
            return AjaxResult.error("获取订单信息异常，请刷新后尝试","hint_50");
        }
        if (StringUtils.isEmpty(apiUserEvaluation.getContent())){
            return AjaxResult.error("请输入评论内容","hint_51");
        }
        if (StringUtils.isNull(apiUserEvaluation.getScore())){
            return AjaxResult.error("请对此商品进行评分","hint_52");
        }
        if (apiUserEvaluation.getScore() <= 0 | apiUserEvaluation.getScore() > 5){
            return AjaxResult.error("评分有误","hint_53");
        }
        if (StringUtils.isNull(apiUserEvaluation.getShopGoodsInfoId())){
            return AjaxResult.error("获取订单信息异常，请刷新后尝试","hint_50");
        }
        return apiUserEvaluationService.insertUserEvaluation(apiUserEvaluation,loginUser.getApiUserInfo().getId());
    }

    /**
     * 查询用户评价信息列表
     */
    @GetMapping("/evaluationListByShopGoodsInfoId")
    public AjaxResult list(ApiUserEvaluation apiUserEvaluation)
    {
        startPage();
        List<ApiUserEvaluation> list = apiUserEvaluationService.selectUserEvaluationList(apiUserEvaluation);
        return AjaxResult.success(getDataTable(list));
    }

    /**
     * 根据店铺商品id统计评论数据信息
     */
    @GetMapping("/evaluationDetail")
    public AjaxResult evaluationDetail(ApiUserEvaluation apiUserEvaluation)
    {
        Map<String, Object> result = apiUserEvaluationService.evaluationDetail(apiUserEvaluation);
        return AjaxResult.success(result);
    }

    /**
     * 根据店铺id查询评论列表
     */
    @GetMapping("/evaluationListByShopId")
    public AjaxResult evaluationListByShopId(ApiUserEvaluation apiUserEvaluation)
    {
        if (StringUtils.isNull(apiUserEvaluation.getShopId())){
            return AjaxResult.error("获取店铺信息异常，请刷新后重新尝试");
        }
        startPage();
        List<ApiUserEvaluation> list = apiUserEvaluationService.evaluationListByShopId(apiUserEvaluation);
        return AjaxResult.success(getDataTable(list));
    }

}
