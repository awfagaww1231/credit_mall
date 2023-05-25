package com.ruoyi.web.controller.app;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.customer.domain.ApiGoodsInfo;
import com.ruoyi.customer.service.IApiGoodsInfoService;
import com.ruoyi.system.domain.GoodsBrand;
import com.ruoyi.system.service.IGoodsBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/goodsBrand")
public class ApiGoodsBrandController extends BaseController {

    @Autowired
    private IGoodsBrandService goodsBrandService;

    /**
     * 查询商品品牌列表
     */
    @GetMapping("/list")
    public AjaxResult list(GoodsBrand goodsBrand)
    {
        startPage();
        List<GoodsBrand> list = goodsBrandService.selectGoodsBrandList(goodsBrand);
        return AjaxResult.success(getDataTable(list));
    }

}
