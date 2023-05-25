package com.ruoyi.web.controller.system;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.annotation.RepeatSubmit;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.ApplySellerAccount;
import com.ruoyi.system.service.IApplySellerAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 申请成为商户记录Controller
 * 
 * @author ruoyi
 * @date 2023-02-17
 */
@RestController
@RequestMapping("/system/applySellerAccount")
public class ApplySellerAccountController extends BaseController
{
    @Autowired
    private IApplySellerAccountService applySellerAccountService;

    /**
     * 查询申请成为商户记录列表
     */
    @PreAuthorize("@ss.hasPermi('system:applySellerAccount:list')")
    @GetMapping("/list")
    public TableDataInfo list(ApplySellerAccount applySellerAccount)
    {
        startPage();
        List<ApplySellerAccount> list = applySellerAccountService.selectApplySellerAccountList(applySellerAccount);
        return getDataTable(list);
    }


    /**
     * 获取申请成为商户记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:applySellerAccount:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(applySellerAccountService.selectApplySellerAccountById(id));
    }

    /**
     * 通过商户账号申请
     */
    @PreAuthorize("@ss.hasPermi('system:applySellerAccount:updateStatus')")
    @Log(title = "通过商户账号申请", businessType = BusinessType.UPDATE)
    @RepeatSubmit
    @GetMapping("/agree")
    public AjaxResult agree(Long id){
        return applySellerAccountService.agree(id);
    }

    /**
     * 驳回商户账号申请
     */
    @PreAuthorize("@ss.hasPermi('system:applySellerAccount:updateStatus')")
    @Log(title = "驳回商户账号申请", businessType = BusinessType.UPDATE)
    @RepeatSubmit
    @GetMapping("/reject")
    public AjaxResult reject(Long id){
        return applySellerAccountService.reject(id);
    }
}
