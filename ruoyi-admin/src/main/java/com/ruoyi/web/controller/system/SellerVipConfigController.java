package com.ruoyi.web.controller.system;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.annotation.RepeatSubmit;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.SellerVipConfig;
import com.ruoyi.system.service.ISellerVipConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.List;

/**
 * 商家会员等级条件配置信息Controller
 * 
 * @author ruoyi
 * @date 2022-11-11
 */
@RestController
@RequestMapping("/system/sellerVipConfig")
public class SellerVipConfigController extends BaseController
{
    @Autowired
    private ISellerVipConfigService sellerVipConfigService;

    /**
     * 查询商家会员等级条件配置信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:sellerVipConfig:list')")
    @GetMapping("/list")
    public TableDataInfo list(SellerVipConfig sellerVipConfig)
    {
        startPage();
        List<SellerVipConfig> list = sellerVipConfigService.selectSellerVipConfigList(sellerVipConfig);
        return getDataTable(list);
    }

    /**
     * 导出商家会员等级条件配置信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:sellerVipConfig:export')")
    @Log(title = "商家会员等级条件配置信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SellerVipConfig sellerVipConfig)
    {
        List<SellerVipConfig> list = sellerVipConfigService.selectSellerVipConfigList(sellerVipConfig);
        ExcelUtil<SellerVipConfig> util = new ExcelUtil<SellerVipConfig>(SellerVipConfig.class);
        util.exportExcel(response, list, "商家会员等级条件配置信息数据");
    }

    /**
     * 获取商家会员等级条件配置信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:sellerVipConfig:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(sellerVipConfigService.selectSellerVipConfigById(id));
    }

    /**
     * 新增商家会员等级条件配置信息
     */
    @PreAuthorize("@ss.hasPermi('system:sellerVipConfig:add')")
    @Log(title = "商家会员等级条件配置信息", businessType = BusinessType.INSERT)
    @PostMapping
    @RepeatSubmit
    public AjaxResult add(@RequestBody SellerVipConfig sellerVipConfig)
    {
        return toAjax(sellerVipConfigService.insertSellerVipConfig(sellerVipConfig));
    }

    /**
     * 修改商家会员等级条件配置信息
     */
    @PreAuthorize("@ss.hasPermi('system:sellerVipConfig:edit')")
    @Log(title = "商家会员等级条件配置信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SellerVipConfig sellerVipConfig)
    {
        if (StringUtils.isNotNull(sellerVipConfig.getVipLevel())){
            return AjaxResult.error("不允许修改对应等级");
        }
        if (StringUtils.isNull(sellerVipConfig.getRechargeAmout())){
            return AjaxResult.error("设置的所需充值金额不能为空");
        }
        if (sellerVipConfig.getRechargeAmout().compareTo(BigDecimal.ZERO) == -1){
            return AjaxResult.error("设置的所需充值金额不能小于0");
        }
        if (StringUtils.isNull(sellerVipConfig.getDevelopedNumber())){
            return AjaxResult.error("设置的所需发展下线人数不能为空");
        }
        if (sellerVipConfig.getDevelopedNumber()<0){
            return AjaxResult.error("设置的所需发展下线人数不能小于0");
        }
        return toAjax(sellerVipConfigService.updateSellerVipConfig(sellerVipConfig));
    }

    /**
     * 删除商家会员等级条件配置信息
     */
    @PreAuthorize("@ss.hasPermi('system:sellerVipConfig:remove')")
    @Log(title = "商家会员等级条件配置信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(sellerVipConfigService.deleteSellerVipConfigByIds(ids));
    }
}
