package com.ruoyi.web.controller.system;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.annotation.RepeatSubmit;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.ShopGoodsInfo;
import com.ruoyi.system.domain.ShopInfo;
import com.ruoyi.system.service.IShopGoodsInfoService;
import com.ruoyi.system.service.IShopInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 店铺信息Controller
 * 
 * @author ruoyi
 * @date 2022-10-28
 */
@RestController
@RequestMapping("/system/shopInfo")
public class ShopInfoController extends BaseController
{
    @Autowired
    private IShopInfoService shopInfoService;

    @Autowired
    private IShopGoodsInfoService shopGoodsInfoService;

    /**
     * 查询店铺信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:shopInfo:list')")
    @GetMapping("/list")
    public TableDataInfo list(ShopInfo shopInfo)
    {
        startPage();
        List<ShopInfo> list = shopInfoService.selectShopInfoList(shopInfo);
        return getDataTable(list);
    }

    /**
     * 导出店铺信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:shopInfo:export')")
    @Log(title = "店铺信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ShopInfo shopInfo)
    {
        List<ShopInfo> list = shopInfoService.selectShopInfoList(shopInfo);
        ExcelUtil<ShopInfo> util = new ExcelUtil<ShopInfo>(ShopInfo.class);
        util.exportExcel(response, list, "店铺信息数据");
    }

    /**
     * 获取店铺信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:shopInfo:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(shopInfoService.selectShopInfoById(id));
    }

    /**
     * 新增店铺信息
     */
    @PreAuthorize("@ss.hasPermi('system:shopInfo:add')")
    @RepeatSubmit
    @Log(title = "店铺信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ShopInfo shopInfo)
    {
        return toAjax(shopInfoService.insertShopInfo(shopInfo));
    }

    /**
     * 修改店铺信息
     */
    @PreAuthorize("@ss.hasPermi('system:shopInfo:edit')")
    @Log(title = "店铺信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ShopInfo shopInfo)
    {
        return toAjax(shopInfoService.updateShopInfo(shopInfo));
    }

    /**
     * 删除店铺信息
     */
    @PreAuthorize("@ss.hasPermi('system:shopInfo:remove')")
    @Log(title = "店铺信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(shopInfoService.deleteShopInfoByIds(ids));
    }

    /**
     * 删除店铺信息
     */
    @PreAuthorize("@ss.hasPermi('system:shopInfo:updateStatus')")
    @GetMapping("/updateStatus")
    public AjaxResult updateStatus(Long id)
    {
        ShopInfo shopInfo = shopInfoService.selectShopInfoById(id);
        Integer status = shopInfo.getStatus();
        //如果想要启用店铺
        if (status == 2){
            shopInfo.setStatus(0);
            //同时启用店铺商品
            ShopGoodsInfo shopGoodsInfo = new ShopGoodsInfo();
            shopGoodsInfo.setShopId(shopInfo.getId());
            shopGoodsInfo.setStatus(-1);
            //首先必须平台上此商品是上架，店铺商品中才可启用
            shopGoodsInfo.setPlatformGoodsStatus(0);
            List<ShopGoodsInfo> shopGoodsInfos = shopGoodsInfoService.selectShopGoodsInfoList(shopGoodsInfo);
            for (int k = 0; k < shopGoodsInfos.size(); k++) {
                shopGoodsInfos.get(k).setStatus(0);
                shopGoodsInfoService.updateShopGoodsInfo(shopGoodsInfos.get(k));
            }
        }
        //如果想要禁用店铺
        else if (status == 0){
            shopInfo.setStatus(2);
            //同时停用店铺商品
            ShopGoodsInfo shopGoodsInfo = new ShopGoodsInfo();
            shopGoodsInfo.setShopId(shopInfo.getId());
            shopGoodsInfo.setStatus(-1);
            List<ShopGoodsInfo> shopGoodsInfos = shopGoodsInfoService.selectShopGoodsInfoList(shopGoodsInfo);
            for (int k = 0; k < shopGoodsInfos.size(); k++) {
                shopGoodsInfos.get(k).setStatus(1);
                shopGoodsInfoService.updateShopGoodsInfo(shopGoodsInfos.get(k));
            }
        }

        return toAjax(shopInfoService.updateShopInfo(shopInfo));
    }
}
