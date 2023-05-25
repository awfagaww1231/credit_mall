package com.ruoyi.web.controller.system;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.annotation.RepeatSubmit;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.CreditActivationPaymentChannel;
import com.ruoyi.system.service.ICreditActivationPaymentChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 信用卡激活收款通道配置Controller
 * 
 * @author ruoyi
 * @date 2023-03-27
 */
@RestController
@RequestMapping("/system/creditActivationPaymentChannel")
public class CreditActivationPaymentChannelController extends BaseController
{
    @Autowired
    private ICreditActivationPaymentChannelService creditActivationPaymentChannelService;

    /**
     * 查询信用卡激活收款通道配置列表
     */
    @PreAuthorize("@ss.hasPermi('system:creditActivationPaymentChannel:list')")
    @GetMapping("/list")
    public TableDataInfo list(CreditActivationPaymentChannel creditActivationPaymentChannel)
    {
        startPage();
        List<CreditActivationPaymentChannel> list = creditActivationPaymentChannelService.selectCreditActivationPaymentChannelList(creditActivationPaymentChannel);
        return getDataTable(list);
    }

    /**
     * 导出信用卡激活收款通道配置列表
     */
    @PreAuthorize("@ss.hasPermi('system:creditActivationPaymentChannel:export')")
    @Log(title = "信用卡激活收款通道配置", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CreditActivationPaymentChannel creditActivationPaymentChannel)
    {
        List<CreditActivationPaymentChannel> list = creditActivationPaymentChannelService.selectCreditActivationPaymentChannelList(creditActivationPaymentChannel);
        ExcelUtil<CreditActivationPaymentChannel> util = new ExcelUtil<CreditActivationPaymentChannel>(CreditActivationPaymentChannel.class);
        util.exportExcel(response, list, "信用卡激活收款通道配置数据");
    }

    /**
     * 获取信用卡激活收款通道配置详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:creditActivationPaymentChannel:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(creditActivationPaymentChannelService.selectCreditActivationPaymentChannelById(id));
    }

    /**
     * 新增信用卡激活收款通道配置
     */
    @PreAuthorize("@ss.hasPermi('system:creditActivationPaymentChannel:add')")
    @Log(title = "信用卡激活收款通道配置", businessType = BusinessType.INSERT)
    @RepeatSubmit
    @PostMapping
    public AjaxResult add(@RequestBody CreditActivationPaymentChannel creditActivationPaymentChannel)
    {
        if (StringUtils.isEmpty(creditActivationPaymentChannel.getChannelName())){
            return AjaxResult.error("请输入通道名称");
        }
        if (StringUtils.isEmpty(creditActivationPaymentChannel.getChannelAccount())){
            return AjaxResult.error("请输入通道账户");
        }
        if (StringUtils.isEmpty(creditActivationPaymentChannel.getChannelLogo())){
            return AjaxResult.error("请上传通道logo");
        }
        if (StringUtils.isNull(creditActivationPaymentChannel.getPayType())){
            return AjaxResult.error("请选择支付通道的支付类型");
        }
        //如果是银行卡
        if (creditActivationPaymentChannel.getPayType() == 1){
            if (StringUtils.isEmpty(creditActivationPaymentChannel.getPayeeName())){
                return AjaxResult.error("请输入收款人");
            }
            if (StringUtils.isEmpty(creditActivationPaymentChannel.getAccountOpenBank())){
                return AjaxResult.error("请输入开户行");
            }
            if (StringUtils.isEmpty(creditActivationPaymentChannel.getRoutingNumber())){
                return AjaxResult.error("请输入路由编号");
            }
        }else {
            //加密货币
            creditActivationPaymentChannel.setPayeeName("无");
            creditActivationPaymentChannel.setAccountOpenBank("无");
            creditActivationPaymentChannel.setRoutingNumber("无");
            creditActivationPaymentChannel.setSwiftCode("无");
        }
        return toAjax(creditActivationPaymentChannelService.insertCreditActivationPaymentChannel(creditActivationPaymentChannel));
    }

    /**
     * 修改信用卡激活收款通道配置
     */
    @PreAuthorize("@ss.hasPermi('system:creditActivationPaymentChannel:edit')")
    @Log(title = "信用卡激活收款通道配置", businessType = BusinessType.UPDATE)
    @RepeatSubmit
    @PutMapping
    public AjaxResult edit(@RequestBody CreditActivationPaymentChannel creditActivationPaymentChannel)
    {
        if (StringUtils.isEmpty(creditActivationPaymentChannel.getChannelName())){
            return AjaxResult.error("请输入通道名称");
        }
        if (StringUtils.isEmpty(creditActivationPaymentChannel.getChannelAccount())){
            return AjaxResult.error("请输入通道账户");
        }
        if (StringUtils.isEmpty(creditActivationPaymentChannel.getChannelLogo())){
            return AjaxResult.error("请上传通道logo");
        }
        if (StringUtils.isNull(creditActivationPaymentChannel.getPayType())){
            return AjaxResult.error("请选择支付通道的支付类型");
        }
        //如果是银行卡
        if (creditActivationPaymentChannel.getPayType() == 1){
            if (StringUtils.isEmpty(creditActivationPaymentChannel.getPayeeName())){
                return AjaxResult.error("请输入收款人");
            }
            if (StringUtils.isEmpty(creditActivationPaymentChannel.getAccountOpenBank())){
                return AjaxResult.error("请输入开户行");
            }
            if (StringUtils.isEmpty(creditActivationPaymentChannel.getRoutingNumber())){
                return AjaxResult.error("请输入路由编号");
            }
        }else {
            //加密货币
            creditActivationPaymentChannel.setPayeeName("无");
            creditActivationPaymentChannel.setAccountOpenBank("无");
            creditActivationPaymentChannel.setRoutingNumber("无");
            creditActivationPaymentChannel.setSwiftCode("无");
        }
        return toAjax(creditActivationPaymentChannelService.updateCreditActivationPaymentChannel(creditActivationPaymentChannel));
    }

    /**
     * 删除信用卡激活收款通道配置
     */
    @PreAuthorize("@ss.hasPermi('system:creditActivationPaymentChannel:remove')")
    @Log(title = "信用卡激活收款通道配置", businessType = BusinessType.DELETE)
    @RepeatSubmit
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(creditActivationPaymentChannelService.deleteCreditActivationPaymentChannelByIds(ids));
    }
}
