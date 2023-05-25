package com.ruoyi.web.controller.system;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.annotation.RepeatSubmit;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.PlatformPaymentChannel;
import com.ruoyi.system.service.IPlatformPaymentChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 平台收款通道配置Controller
 * 
 * @author ruoyi
 * @date 2023-03-20
 */
@RestController
@RequestMapping("/system/platformPaymentChannel")
public class PlatformPaymentChannelController extends BaseController
{
    @Autowired
    private IPlatformPaymentChannelService platformPaymentChannelService;

    /**
     * 查询平台收款通道配置列表
     */
    @PreAuthorize("@ss.hasPermi('system:platformPaymentChannel:list')")
    @GetMapping("/list")
    public TableDataInfo list(PlatformPaymentChannel platformPaymentChannel)
    {
        startPage();
        List<PlatformPaymentChannel> list = platformPaymentChannelService.selectPlatformPaymentChannelList(platformPaymentChannel);
        return getDataTable(list);
    }

    /**
     * 获取平台收款通道配置详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:platformPaymentChannel:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(platformPaymentChannelService.selectPlatformPaymentChannelById(id));
    }

    /**
     * 新增平台收款通道配置
     */
    @PreAuthorize("@ss.hasPermi('system:platformPaymentChannel:add')")
    @Log(title = "平台收款通道配置", businessType = BusinessType.INSERT)
    @RepeatSubmit
    @PostMapping
    public AjaxResult add(@RequestBody PlatformPaymentChannel platformPaymentChannel)
    {
        if (StringUtils.isEmpty(platformPaymentChannel.getChannelName())){
            return AjaxResult.error("请输入通道名称");
        }
        if (StringUtils.isEmpty(platformPaymentChannel.getChannelAccount())){
            return AjaxResult.error("请输入通道账户");
        }
        if (StringUtils.isEmpty(platformPaymentChannel.getChannelLogo())){
            return AjaxResult.error("请上传通道logo");
        }
        if (StringUtils.isNull(platformPaymentChannel.getPayType())){
            return AjaxResult.error("请选择支付通道的支付类型");
        }
        //如果是银行卡
        if (platformPaymentChannel.getPayType() == 1){
            if (StringUtils.isEmpty(platformPaymentChannel.getPayeeName())){
                return AjaxResult.error("请输入收款人");
            }
            if (StringUtils.isEmpty(platformPaymentChannel.getAccountOpenBank())){
                return AjaxResult.error("请输入开户行");
            }
            if (StringUtils.isEmpty(platformPaymentChannel.getRoutingNumber())){
                return AjaxResult.error("请输入路由编号");
            }
        }else {
            //加密货币
            platformPaymentChannel.setPayeeName("无");
            platformPaymentChannel.setAccountOpenBank("无");
            platformPaymentChannel.setRoutingNumber("无");
            platformPaymentChannel.setSwiftCode("无");
        }
        return toAjax(platformPaymentChannelService.insertPlatformPaymentChannel(platformPaymentChannel));
    }

    /**
     * 修改平台收款通道配置
     */
    @PreAuthorize("@ss.hasPermi('system:platformPaymentChannel:edit')")
    @Log(title = "平台收款通道配置", businessType = BusinessType.UPDATE)
    @RepeatSubmit
    @PutMapping
    public AjaxResult edit(@RequestBody PlatformPaymentChannel platformPaymentChannel)
    {
        if (StringUtils.isEmpty(platformPaymentChannel.getChannelName())){
            return AjaxResult.error("请输入通道名称");
        }
        if (StringUtils.isEmpty(platformPaymentChannel.getChannelAccount())){
            return AjaxResult.error("请输入通道账户");
        }
        if (StringUtils.isEmpty(platformPaymentChannel.getChannelLogo())){
            return AjaxResult.error("请上传通道logo");
        }
        if (StringUtils.isNull(platformPaymentChannel.getPayType())){
            return AjaxResult.error("请选择支付通道的支付类型");
        }
        //如果是银行卡
        if (platformPaymentChannel.getPayType() == 1){
            if (StringUtils.isEmpty(platformPaymentChannel.getPayeeName())){
                return AjaxResult.error("请输入收款人");
            }
            if (StringUtils.isEmpty(platformPaymentChannel.getAccountOpenBank())){
                return AjaxResult.error("请输入开户行");
            }
            if (StringUtils.isEmpty(platformPaymentChannel.getRoutingNumber())){
                return AjaxResult.error("请输入路由编号");
            }
        }else {
            //加密货币
            platformPaymentChannel.setPayeeName("无");
            platformPaymentChannel.setAccountOpenBank("无");
            platformPaymentChannel.setRoutingNumber("无");
            platformPaymentChannel.setSwiftCode("无");
        }
        return toAjax(platformPaymentChannelService.updatePlatformPaymentChannel(platformPaymentChannel));
    }

    /**
     * 删除平台收款通道配置
     */
    @PreAuthorize("@ss.hasPermi('system:platformPaymentChannel:remove')")
    @Log(title = "平台收款通道配置", businessType = BusinessType.DELETE)
    @RepeatSubmit
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(platformPaymentChannelService.deletePlatformPaymentChannelByIds(ids));
    }
}
