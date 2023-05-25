package com.ruoyi.web.controller.app;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.customer.common.AppletTokenService;
import com.ruoyi.customer.domain.ApiGoodsInfo;
import com.ruoyi.customer.domain.ApiShoppingCart;
import com.ruoyi.customer.domain.ApiSuperDealsClassification;
import com.ruoyi.customer.domain.AppletLoginUser;
import com.ruoyi.customer.service.IApiSuperDealsClassification;
import com.ruoyi.system.domain.ShopGoodsInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * SuperDealsController +
 *
 * @author ruoyi
 * @date 2022-11-04
 */
@RestController
@RequestMapping("/api/superDealsClassification")
public class ApiSuperDealsClassificationController extends BaseController {

    @Autowired
    private IApiSuperDealsClassification apiSuperDealsClassification;

    /**
     * 查询SuperDeals活动分类列表
     */
    @GetMapping("/superDealsClassificationList")
    public AjaxResult list()
    {
        List<ApiSuperDealsClassification> list = apiSuperDealsClassification.superDealsClassificationList();
        return AjaxResult.success(list);
    }

    /**
     * 根据SuperDeals活动分类查询商品
     */
    @GetMapping("/shopGoodsListClassification")
    public AjaxResult shopGoodsListClassification(Long classificationId)
    {
        startPage();
        List<ApiGoodsInfo> list = apiSuperDealsClassification.shopGoodsListClassification(classificationId);
        return AjaxResult.success(getDataTable(list));
    }

}
