package com.ruoyi.web.controller.system;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.annotation.RepeatSubmit;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.service.TokenService;
import com.ruoyi.system.domain.ShopReview;
import com.ruoyi.system.service.IShopReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 店铺入驻审核Controller
 * 
 * @author ruoyi
 * @date 2022-10-28
 */
@RestController
@RequestMapping("/system/shopReview")
public class ShopReviewController extends BaseController
{
    @Autowired
    private IShopReviewService shopReviewService;

    @Autowired
    private TokenService tokenService;

    /**
     * 查询店铺入驻审核列表
     */
    @PreAuthorize("@ss.hasPermi('system:shopReview:list')")
    @GetMapping("/list")
    public TableDataInfo list(ShopReview shopReview)
    {
        startPage();
        List<ShopReview> list = shopReviewService.selectShopReviewList(shopReview);
        return getDataTable(list);
    }

    /**
     * 导出店铺入驻审核列表
     */
    @PreAuthorize("@ss.hasPermi('system:shopReview:export')")
    @Log(title = "店铺入驻审核", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ShopReview shopReview)
    {
        List<ShopReview> list = shopReviewService.selectShopReviewList(shopReview);
        ExcelUtil<ShopReview> util = new ExcelUtil<ShopReview>(ShopReview.class);
        util.exportExcel(response, list, "店铺入驻审核数据");
    }

    /**
     * 获取店铺入驻审核详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:shopReview:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(shopReviewService.selectShopReviewById(id));
    }

    /**
     * 新增店铺入驻审核
     */
    @PreAuthorize("@ss.hasPermi('system:shopReview:add')")
    @RepeatSubmit
    @Log(title = "店铺入驻审核", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ShopReview shopReview)
    {
        SysUser user = tokenService.getLoginUser().getUser();
        if (StringUtils.isNotNull(user)){
            shopReview.setShopOwnerId(user.getUserId());
        }else {
            return AjaxResult.error("未获得登录信息");
        }
        return toAjax(shopReviewService.insertShopReview(shopReview));
    }

    /**
     * 修改店铺入驻审核
     */
    @PreAuthorize("@ss.hasPermi('system:shopReview:edit')")
    @Log(title = "店铺入驻审核", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ShopReview shopReview)
    {
        return toAjax(shopReviewService.updateShopReview(shopReview));
    }

    /**
     * 删除店铺入驻审核
     */
    @PreAuthorize("@ss.hasPermi('system:shopReview:remove')")
    @Log(title = "店铺入驻审核", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(shopReviewService.deleteShopReviewByIds(ids));
    }

    /**
     * 通过/拒绝审核申请
     */
    @PreAuthorize("@ss.hasPermi('system:shopReview:updateStatus')")
    @RepeatSubmit
    @PostMapping(value = "/updateStatus")
    public AjaxResult updateStatus(@RequestBody ShopReview shopReview)
    {
        return AjaxResult.success(shopReviewService.updateStatus(shopReview));
    }

}
