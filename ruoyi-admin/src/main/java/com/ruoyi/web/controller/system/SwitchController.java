package com.ruoyi.web.controller.system;


import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.system.service.ISwitchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//开关
@RestController
@RequestMapping("/system/switch")
public class SwitchController extends BaseController {

    @Autowired
    private ISwitchService switchService;

    //是否需要注册验证码开关
    //查询状态
    @GetMapping(value = "/registerValidateCode")
    public AjaxResult registerValidateCode()
    {
        return AjaxResult.success(switchService.registerValidateCode());
    }

    /**
     * 是否需要注册验证码开关
     */
    @GetMapping(value = "/updateRegisterValidateCode")
    public AjaxResult updateRegisterValidateCode(int status)
    {
        return AjaxResult.success(switchService.updateRegisterValidateCode(status));
    }
}
