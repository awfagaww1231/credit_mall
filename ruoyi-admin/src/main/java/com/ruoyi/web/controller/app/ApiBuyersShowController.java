package com.ruoyi.web.controller.app;

import com.ruoyi.common.annotation.RepeatSubmit;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.customer.domain.ApiBuyersShow;
import com.ruoyi.customer.service.IApiBuyersShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 买家秀帖子Controller
 * 
 * @author ruoyi
 * @date 2022-11-25
 */
@RestController
@RequestMapping("/api/buyersShow")
public class ApiBuyersShowController extends BaseController
{
    @Autowired
    private IApiBuyersShowService buyersShowService;

    /**
     * 查询买家秀帖子列表
     */
    @GetMapping("/list")
    public AjaxResult list(ApiBuyersShow buyersShow)
    {
        startPage();
        List<ApiBuyersShow> list = buyersShowService.selectBuyersShowList(buyersShow);
        return AjaxResult.success(getDataTable(list));
    }

    /**
     * 获取买家秀帖子详细信息
     */
    @GetMapping(value = "/buyersShowDetail")
    public AjaxResult getInfo(Long id)
    {
        if (StringUtils.isNull(id)){
            return AjaxResult.error("参数不能为空","hint_20");
        }
        return AjaxResult.success(buyersShowService.selectBuyersShowById(id));
    }

    /**
     * 新增买家秀帖子
     */
    @PostMapping
    @RepeatSubmit
    public AjaxResult add(@RequestBody ApiBuyersShow buyersShow)
    {
        return toAjax(buyersShowService.insertBuyersShow(buyersShow));
    }

    /**
     * 删除买家秀帖子
     */
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(buyersShowService.deleteBuyersShowByIds(ids));
    }
}
