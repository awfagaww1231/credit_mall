package com.ruoyi.web.controller.system;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.annotation.RepeatSubmit;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.PlatformGoodsInfo;
import com.ruoyi.system.domain.ShopGoodsInfo;
import com.ruoyi.system.domain.ShopInfo;
import com.ruoyi.system.service.IPlatformGoodsInfoService;
import com.ruoyi.system.service.IShopGoodsInfoService;
import com.ruoyi.system.service.IShopInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 店铺的商品信息Controller
 * 
 * @author ruoyi
 * @date 2022-11-01
 */
@RestController
@RequestMapping("/system/shopGoodsInfo")
public class ShopGoodsInfoController extends BaseController
{
    @Autowired
    private IShopGoodsInfoService shopGoodsInfoService;

    @Autowired
    private IShopInfoService shopInfoService;

    @Autowired
    private IPlatformGoodsInfoService platformGoodsInfoService;

    /**
     * 查询店铺的商品信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:shopGoodsInfo:list')")
    @GetMapping("/list")
    public TableDataInfo list(ShopGoodsInfo shopGoodsInfo)
    {
        startPage();
        List<ShopGoodsInfo> list = shopGoodsInfoService.selectShopGoodsInfoList(shopGoodsInfo);
        return getDataTable(list);
    }

    /**
     * 导出店铺的商品信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:shopGoodsInfo:export')")
    @Log(title = "店铺的商品信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ShopGoodsInfo shopGoodsInfo)
    {
        List<ShopGoodsInfo> list = shopGoodsInfoService.selectShopGoodsInfoList(shopGoodsInfo);
        ExcelUtil<ShopGoodsInfo> util = new ExcelUtil<ShopGoodsInfo>(ShopGoodsInfo.class);
        util.exportExcel(response, list, "店铺的商品信息数据");
    }

    /**
     * 获取店铺的商品信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:shopGoodsInfo:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(shopGoodsInfoService.selectShopGoodsInfoById(id));
    }

    /**
     * 新增店铺的商品信息
     */
    @PreAuthorize("@ss.hasPermi('system:shopGoodsInfo:add')")
    @RepeatSubmit
    @Log(title = "店铺的商品信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ShopGoodsInfo shopGoodsInfo)
    {
        ShopGoodsInfo shopGoodsInfoVo = shopGoodsInfoService.selectShopGoodsInfoByShopIdAndGoodsId(shopGoodsInfo.getShopId(), shopGoodsInfo.getGoodsId());
        if (StringUtils.isNotNull(shopGoodsInfoVo)){
            return AjaxResult.error("此店铺已经添加过该商品");
        }
        if (StringUtils.isNull(shopGoodsInfo)){
            return AjaxResult.error("商品出售价格不允许为空");
        }
        return toAjax(shopGoodsInfoService.insertShopGoodsInfo(shopGoodsInfo));
    }

    /**
     * 修改店铺的商品信息
     */
    @PreAuthorize("@ss.hasPermi('system:shopGoodsInfo:edit')")
    @Log(title = "店铺的商品信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ShopGoodsInfo shopGoodsInfo)
    {
        return toAjax(shopGoodsInfoService.updateShopGoodsInfo(shopGoodsInfo));
    }

    /**
     * 删除店铺的商品信息
     */
    @PreAuthorize("@ss.hasPermi('system:shopGoodsInfo:remove')")
    @Log(title = "店铺的商品信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(shopGoodsInfoService.deleteShopGoodsInfoByIds(ids));
    }

    /**
     * 查询店铺信息列表
     */
    @GetMapping("/shopInfoList")
    public TableDataInfo shopInfoList(ShopInfo shopInfo)
    {
        startPage();
        List<ShopInfo> list = shopInfoService.selectShopInfoList(shopInfo);
        return getDataTable(list);
    }

    /**
     * 查询可添加的商品列表
     */
    @GetMapping("/platformGoodsInfoList")
    public TableDataInfo platformGoodsInfoList(PlatformGoodsInfo platformGoodsInfo)
    {
        startPage();
        platformGoodsInfo.setStatus(0);
        platformGoodsInfo.setIsDel(0);
        List<PlatformGoodsInfo> list = platformGoodsInfoService.selectPlatformGoodsInfoList(platformGoodsInfo);
        return getDataTable(list);
    }

    /**
     * 批量设置商品精选
     */
    @PostMapping("/toSetFeatured")
    @PreAuthorize("@ss.hasPermi('system:shopGoodsInfo:toSetFeatured')")
    public AjaxResult toSetFeatured(@RequestBody List<Long> ids)
    {
        return toAjax(shopGoodsInfoService.toSetFeatured(ids));
    }

    /**
     * 批量取消商品精选
     */
    @PostMapping("/toCancelFeatured")
    @PreAuthorize("@ss.hasPermi('system:shopGoodsInfo:toCancelFeatured')")
    public AjaxResult toCancelFeatured(@RequestBody List<Long> ids)
    {
        return toAjax(shopGoodsInfoService.toCancelFeatured(ids));
    }

    /**
     * 批量设置商品特价
     */
    @PostMapping("/toSetSpecial")
    @PreAuthorize("@ss.hasPermi('system:shopGoodsInfo:toSetSpecial')")
    public AjaxResult toSetSpecial(@RequestBody List<Long> ids)
    {
        return toAjax(shopGoodsInfoService.toSetSpecial(ids));
    }

    /**
     * 批量取消商品特价
     */
    @PostMapping("/toCancelSpecial")
    @PreAuthorize("@ss.hasPermi('system:shopGoodsInfo:toCancelSpecial')")
    public AjaxResult toCancelSpecial(@RequestBody List<Long> ids)
    {
        return toAjax(shopGoodsInfoService.toCancelSpecial(ids));
    }


}
