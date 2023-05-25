package com.ruoyi.web.controller.app;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.system.domain.InstallmentInterestConfig;
import com.ruoyi.system.service.IInstallmentInterestConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 贷款分期利息配置Controller
 * 
 * @author ruoyi
 * @date 2023-04-19
 */
@RestController
@RequestMapping("/api/installmentInterestConfig")
public class ApiInstallmentInterestConfigController extends BaseController
{
    @Autowired
    private IInstallmentInterestConfigService installmentInterestConfigService;

    /**
     * 查询贷款分期利息配置列表
     */
    @GetMapping("/list")
    public AjaxResult list(InstallmentInterestConfig installmentInterestConfig)
    {
        startPage();
        installmentInterestConfig.setStatus(0);
        List<InstallmentInterestConfig> list = installmentInterestConfigService.selectInstallmentInterestConfigList(installmentInterestConfig);
        return AjaxResult.success(getDataTable(list));
    }


}
