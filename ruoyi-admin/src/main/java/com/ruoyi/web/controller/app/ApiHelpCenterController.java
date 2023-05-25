package com.ruoyi.web.controller.app;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.system.domain.HelpCenter;
import com.ruoyi.system.service.IHelpCenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * APP帮助中心Controller +
 *
 * @author ruoyi
 * @date 2022-10-27
 */
@RestController
@RequestMapping("/api/helpCenter")
public class ApiHelpCenterController extends BaseController {

    @Autowired
    private IHelpCenterService helpCenterService;


    /**
     * 查询帮助中心列表
     */
    @GetMapping("/helpCenterList")
    public AjaxResult list(HelpCenter helpCenter) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        startPage();
        helpCenter.setIsLang(0);
        List<HelpCenter> list = helpCenterService.selectHelpCenterList(helpCenter);
        return AjaxResult.success(getDataTable(list));
    }
}
