package com.ruoyi.web.controller.app;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.customer.service.IApiSwitchService;
import com.ruoyi.system.service.ISwitchService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "开关")
@RestController
@CrossOrigin
@RequestMapping("/api/switch")
public class ApiSwitchController {

    @Autowired
    private ISwitchService switchService;

    //是否需要注册验证码开关
    //查询状态
    @GetMapping(value = "/registerValidateCode")
    public AjaxResult registerValidateCode()
    {
        return AjaxResult.success(switchService.registerValidateCode());
    }
}
