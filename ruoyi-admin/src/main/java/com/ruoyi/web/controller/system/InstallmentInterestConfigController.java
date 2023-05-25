package com.ruoyi.web.controller.system;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.InstallmentInterestConfig;
import com.ruoyi.system.service.IInstallmentInterestConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.List;

/**
 * 贷款分期利息配置Controller
 * 
 * @author ruoyi
 * @date 2023-04-19
 */
@RestController
@RequestMapping("/system/installmentInterestConfig")
public class InstallmentInterestConfigController extends BaseController
{
    @Autowired
    private IInstallmentInterestConfigService installmentInterestConfigService;

    /**
     * 查询贷款分期利息配置列表
     */
    @PreAuthorize("@ss.hasPermi('system:installmentInterestConfig:list')")
    @GetMapping("/list")
    public TableDataInfo list(InstallmentInterestConfig installmentInterestConfig)
    {
        startPage();
        List<InstallmentInterestConfig> list = installmentInterestConfigService.selectInstallmentInterestConfigList(installmentInterestConfig);
        return getDataTable(list);
    }

    /**
     * 导出贷款分期利息配置列表
     */
    @PreAuthorize("@ss.hasPermi('system:installmentInterestConfig:export')")
    @Log(title = "贷款分期利息配置", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, InstallmentInterestConfig installmentInterestConfig)
    {
        List<InstallmentInterestConfig> list = installmentInterestConfigService.selectInstallmentInterestConfigList(installmentInterestConfig);
        ExcelUtil<InstallmentInterestConfig> util = new ExcelUtil<InstallmentInterestConfig>(InstallmentInterestConfig.class);
        util.exportExcel(response, list, "贷款分期利息配置数据");
    }

    /**
     * 获取贷款分期利息配置详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:installmentInterestConfig:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(installmentInterestConfigService.selectInstallmentInterestConfigById(id));
    }

    /**
     * 新增贷款分期利息配置
     */
    @PreAuthorize("@ss.hasPermi('system:installmentInterestConfig:add')")
    @Log(title = "贷款分期利息配置", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody InstallmentInterestConfig installmentInterestConfig)
    {
        if (StringUtils.isNull(installmentInterestConfig.getRepaymentPeriodNum())){
            return AjaxResult.error("请输入期数");
        }
        if (StringUtils.isNull(installmentInterestConfig.getAnnualInterestRate())){
            return AjaxResult.error("请输入年利率");
        }
        if (installmentInterestConfig.getRepaymentPeriodNum() <= 0){
            return AjaxResult.error("期数必须大于0");
        }
        if (installmentInterestConfig.getAnnualInterestRate().compareTo(BigDecimal.ZERO) < 0){
            return AjaxResult.error("年利率不允许小于0");
        }
        return toAjax(installmentInterestConfigService.insertInstallmentInterestConfig(installmentInterestConfig));
    }

    /**
     * 修改贷款分期利息配置
     */
    @PreAuthorize("@ss.hasPermi('system:installmentInterestConfig:edit')")
    @Log(title = "贷款分期利息配置", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody InstallmentInterestConfig installmentInterestConfig)
    {
        if (StringUtils.isNull(installmentInterestConfig.getRepaymentPeriodNum())){
            return AjaxResult.error("请输入期数");
        }
        if (StringUtils.isNull(installmentInterestConfig.getAnnualInterestRate())){
            return AjaxResult.error("请输入年利率");
        }
        if (installmentInterestConfig.getRepaymentPeriodNum() <= 0){
            return AjaxResult.error("期数必须大于0");
        }
        if (installmentInterestConfig.getAnnualInterestRate().compareTo(BigDecimal.ZERO) < 0){
            return AjaxResult.error("年利率不允许小于0");
        }
        return toAjax(installmentInterestConfigService.updateInstallmentInterestConfig(installmentInterestConfig));
    }

    /**
     * 删除贷款分期利息配置
     */
    @PreAuthorize("@ss.hasPermi('system:installmentInterestConfig:remove')")
    @Log(title = "贷款分期利息配置", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(installmentInterestConfigService.deleteInstallmentInterestConfigByIds(ids));
    }
}
