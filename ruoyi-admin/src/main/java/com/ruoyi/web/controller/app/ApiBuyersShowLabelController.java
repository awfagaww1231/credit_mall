package com.ruoyi.web.controller.app;

import java.util.List;

import com.ruoyi.customer.domain.ApiBuyersShowLabel;
import com.ruoyi.customer.service.IApiBuyersShowLabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 买家秀的标签Controller
 * 
 * @author ruoyi
 * @date 2022-11-25
 */
@RestController
@RequestMapping("/api/buyersShowLabel")
public class ApiBuyersShowLabelController extends BaseController
{
    @Autowired
    private IApiBuyersShowLabelService buyersShowLabelService;

    /**
     * 查询买家秀的标签列表
     */
    @GetMapping("/list")
    public TableDataInfo list(ApiBuyersShowLabel buyersShowLabel)
    {
        startPage();
        List<ApiBuyersShowLabel> list = buyersShowLabelService.selectBuyersShowLabelList(buyersShowLabel);
        return getDataTable(list);
    }

    /**
     * 随机获取30个标签
     */
    @GetMapping("/randLabelList")
    public AjaxResult randLabelList()
    {
        List<ApiBuyersShowLabel> list = buyersShowLabelService.randLabelList();
        return AjaxResult.success(list);
    }

    /**
     * 获取买家秀的标签详细信息
     */
    @GetMapping(value = "/getInfo")
    public AjaxResult getInfo(Long labelId)
    {
        return AjaxResult.success(buyersShowLabelService.selectBuyersShowLabelById(labelId));
    }

    /**
     * 新增买家秀的标签
     */
    @PostMapping
    public AjaxResult add(@RequestBody ApiBuyersShowLabel buyersShowLabel)
    {
        return toAjax(buyersShowLabelService.insertBuyersShowLabel(buyersShowLabel));
    }

}
