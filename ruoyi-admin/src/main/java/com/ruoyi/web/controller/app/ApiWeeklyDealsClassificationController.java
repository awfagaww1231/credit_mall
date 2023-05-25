package com.ruoyi.web.controller.app;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.customer.domain.ApiCategory;
import com.ruoyi.customer.domain.ApiGoodsInfo;
import com.ruoyi.customer.domain.ApiWeeklyDealsClassification;
import com.ruoyi.customer.service.IApiWeeklyDealsClassification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * weeklyDealsController +
 *
 * @author ruoyi
 * @date 2022-11-04
 */
@RestController
@RequestMapping("/api/weeklyDealsClassification")
public class ApiWeeklyDealsClassificationController extends BaseController {

    @Autowired
    private IApiWeeklyDealsClassification apiWeeklyDealsClassification;

    /**
     * 查询weeklyDeals置顶的商品
     */
    @GetMapping("/weeklyDealsTopGoods")
    public AjaxResult weeklyDealsTopGoods()
    {
        List<ApiGoodsInfo> list = apiWeeklyDealsClassification.weeklyDealsTopGoods();
        return AjaxResult.success(list);
    }

    /**
     * 查询每周特惠列表
     */
    @GetMapping("/weeklyDealsList")
    public AjaxResult weeklyDealsList()
    {
        //只查显示的，隐藏的类目不显示
        List<ApiWeeklyDealsClassification> list = apiWeeklyDealsClassification.weeklyDealsClassificationList();
        for (int i = 0; i < list.size(); i++) {
            Long classificationId = list.get(i).getId();
            List<ApiGoodsInfo> apiGoodsInfos = apiWeeklyDealsClassification.shopGoodsListClassification(classificationId);
            list.get(i).setGoodsInfos(apiGoodsInfos);
        }
        return AjaxResult.success(getDataTable(list));
    }
}
