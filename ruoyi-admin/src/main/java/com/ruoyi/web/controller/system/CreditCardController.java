package com.ruoyi.web.controller.system;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.annotation.RepeatSubmit;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.CreditCardApplyRecord;
import com.ruoyi.system.service.ICreditCardApplyRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

/**
 * 用户信用卡Controller
 * 
 * @author ruoyi
 * @date 2023-03-20
 */
@RestController
@RequestMapping("/system/creditCard")
public class CreditCardController extends BaseController
{
    @Autowired
    private ICreditCardApplyRecordService creditCardApplyRecordService;


    /**
     * 查询用户信用卡申请列表
     */
    @PreAuthorize("@ss.hasPermi('system:creditCard:creditCardApplyRecordList')")
    @GetMapping("/creditCardApplyRecordList")
    public TableDataInfo creditCardApplyRecordList(CreditCardApplyRecord creditCardApplyRecord)
    {
        startPage();
        List<CreditCardApplyRecord> list = creditCardApplyRecordService.selectCreditCardApplyRecordList(creditCardApplyRecord);
        return getDataTable(list);
    }

    /**
     * 申请信用卡通过
     */
    @PreAuthorize("@ss.hasPermi('system:creditCard:agreeApplyCreditCard')")
    @Log(title = "申请信用卡通过", businessType = BusinessType.UPDATE)
    @RepeatSubmit
    @PostMapping("/agreeApplyCreditCard")
    public AjaxResult agreeApplyCreditCard(@RequestBody CreditCardApplyRecord creditCardApplyRecord)
    {
        if (StringUtils.isNull(creditCardApplyRecord.getId())){
            return AjaxResult.error("请选择需要通过的选项");
        }
        if (StringUtils.isNull(creditCardApplyRecord.getCreditCardTotalAmount())){
            return AjaxResult.error("请输入信用卡额度");
        }
        if (creditCardApplyRecord.getCreditCardTotalAmount().compareTo(BigDecimal.ZERO) <= 0){
            return AjaxResult.error("信用额度必须大于0");
        }
        return creditCardApplyRecordService.agreeApplyCreditCard(creditCardApplyRecord);
    }

    /**
     * 申请信用卡拒绝
     */
    @PreAuthorize("@ss.hasPermi('system:creditCard:rejectApplyCreditCard')")
    @Log(title = "申请信用卡拒绝", businessType = BusinessType.UPDATE)
    @RepeatSubmit
    @PostMapping("/rejectApplyCreditCard")
    public AjaxResult rejectApplyCreditCard(@RequestBody CreditCardApplyRecord creditCardApplyRecord)
    {
        if (StringUtils.isEmpty(creditCardApplyRecord.getRemark())){
            return AjaxResult.error("请输入驳回原因");
        }
        return creditCardApplyRecordService.rejectApplyCreditCard(creditCardApplyRecord);
    }

    /**
     * 删除申请信用卡
     */
    @PreAuthorize("@ss.hasPermi('system:creditCard:deleteApplyCreditCard')")
    @Log(title = "删除申请信用卡", businessType = BusinessType.DELETE)
    @RepeatSubmit
    @DeleteMapping("/deleteApplyCreditCard/{ids}")
    public AjaxResult deleteApplyCreditCard(@PathVariable Long[] ids)
    {
        return creditCardApplyRecordService.deleteCreditCardApplyRecordByIds(ids);
    }

    /**
     * 获取激活支付信息
     */
    @PreAuthorize("@ss.hasPermi('system:creditCard:getCreditActivationPayInfo')")
    @GetMapping("/getCreditActivationPayInfo")
    public AjaxResult getCreditActivationPayInfo()
    {
        return creditCardApplyRecordService.getCreditActivationPayInfo();
    }

    /**
     * 修改激活支付信息
     */
    @PreAuthorize("@ss.hasPermi('system:creditCard:updateCreditActivationPayInfo')")
    @GetMapping("/updateCreditActivationPayInfo")
    @RepeatSubmit
    public AjaxResult updateCreditActivationPayInfo(BigDecimal creditActivationFixedPrice, BigDecimal creditActivationPayPercent, Integer creditActivationPaySwitch)
    {
        if (StringUtils.isNull(creditActivationFixedPrice)){
            return AjaxResult.error("请输入信用额度激活固定金额");
        }
        if (creditActivationFixedPrice.compareTo(BigDecimal.ZERO) < 0){
            return AjaxResult.error("信用额度激活固定金额不允许小于0");
        }
        if (StringUtils.isNull(creditActivationPayPercent)){
            return AjaxResult.error("请输入信用额度激活支付的百分比");
        }
        if (creditActivationPayPercent.compareTo(BigDecimal.ZERO) < 0){
            return AjaxResult.error("信用额度激活支付的百分比不允许小于0%");
        }
        if (StringUtils.isNull(creditActivationPaySwitch)){
            return AjaxResult.error("请选择信用额度激活支付方式");
        }
        return creditCardApplyRecordService.updateCreditActivationPayInfo(creditActivationFixedPrice,creditActivationPayPercent,creditActivationPaySwitch);
    }
}
