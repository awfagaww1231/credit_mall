package com.ruoyi.web.controller.system;


import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.system.service.IWebBackgroundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 系统后台
 *
 * @author ruoyi
 */
@RestController
@RequestMapping("/admin/webBackground")
public class WebBackgroundController extends BaseController {


    @Autowired
    private IWebBackgroundService webBackgroundService;

    //后台提醒
    @GetMapping("/reminder")
    public AjaxResult reminder(){
        return webBackgroundService.getReminder();
    }


}
