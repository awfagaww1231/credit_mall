package com.ruoyi.web.controller.app;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.system.domain.PlatformPaymentChannel;
import com.ruoyi.system.service.IPlatformPaymentChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 平台收款通道配置Controller
 *
 * @author ruoyi
 * @date 2023-03-20
 */
@RestController
@RequestMapping("/api/platformPaymentChannel")
public class ApiPlatformPaymentChannelController extends BaseController {

    @Autowired
    private IPlatformPaymentChannelService platformPaymentChannelService;

    /**
     * 查询平台收款通道配置列表
     */
    @GetMapping("/list")
    public TableDataInfo list(PlatformPaymentChannel platformPaymentChannel)
    {
        startPage();
        platformPaymentChannel.setStatus(0);
        List<PlatformPaymentChannel> list = platformPaymentChannelService.selectPlatformPaymentChannelList(platformPaymentChannel);
        return getDataTable(list);
    }
}
