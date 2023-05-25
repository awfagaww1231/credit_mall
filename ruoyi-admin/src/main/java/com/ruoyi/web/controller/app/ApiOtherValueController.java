package com.ruoyi.web.controller.app;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.system.domain.OtherValue;
import com.ruoyi.system.service.IOtherValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 其他值Controller
 * 
 * @author ruoyi
 * @date 2023-03-27
 */
@RestController
@RequestMapping("/api/otherValue")
public class ApiOtherValueController extends BaseController
{
    @Autowired
    private IOtherValueService otherValueService;

    /**
     * 查询其他值列表
     */
    @GetMapping("/list")
    public AjaxResult list(OtherValue otherValue)
    {
        startPage();
        List<OtherValue> list = otherValueService.selectOtherValueList(otherValue);
        return AjaxResult.success(getDataTable(list));
    }

    /**
     * 获取其他值详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(otherValueService.selectOtherValueById(id));
    }

    /**
     * 根据key获取其他值详细信息
     */
    @GetMapping(value = "/selectOtherValueByKey")
    public AjaxResult selectOtherValueByKey(String key)
    {
        return AjaxResult.success(otherValueService.selectOtherValueByKey(key).getOtherValue());
    }
}
