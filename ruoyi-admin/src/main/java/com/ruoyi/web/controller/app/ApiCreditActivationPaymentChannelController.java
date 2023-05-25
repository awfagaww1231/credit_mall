package com.ruoyi.web.controller.app;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.system.domain.CreditActivationPaymentChannel;
import com.ruoyi.system.service.ICreditActivationPaymentChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 信用卡激活收款通道配置Controller
 * 
 * @author ruoyi
 * @date 2023-03-27
 */
@RestController
@RequestMapping("/api/creditActivationPaymentChannel")
public class ApiCreditActivationPaymentChannelController extends BaseController
{
    @Autowired
    private ICreditActivationPaymentChannelService creditActivationPaymentChannelService;

    /**
     * 查询信用卡激活收款通道配置列表
     */
    @GetMapping("/list")
    public AjaxResult list(CreditActivationPaymentChannel creditActivationPaymentChannel)
    {
        startPage();
        creditActivationPaymentChannel.setStatus(0);
        List<CreditActivationPaymentChannel> list = creditActivationPaymentChannelService.selectCreditActivationPaymentChannelList(creditActivationPaymentChannel);
        return AjaxResult.success(getDataTable(list));
    }
}
