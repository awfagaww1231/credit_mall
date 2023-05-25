package com.ruoyi.web.controller.app;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.customer.domain.ApiBuyersShowTitle;
import com.ruoyi.customer.service.IApiBuyersShowTitleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * 购物灵感标题列表Controller
 * 
 * @author ruoyi
 * @date 2022-11-25
 */
@RestController
@RequestMapping("/api/buyersShowTitle")
public class ApiBuyersShowTitleController extends BaseController
{
    @Autowired
    private IApiBuyersShowTitleService apiBuyersShowTitleService;

    /**
     * 查询购物灵感标题列表
     */
    @GetMapping("/list")
    public AjaxResult list(ApiBuyersShowTitle buyersShowTitle)
    {
        startPage();
        List<ApiBuyersShowTitle> list = apiBuyersShowTitleService.selectBuyersShowTitleList(buyersShowTitle);
        return AjaxResult.success(getDataTable(list));
    }

}
