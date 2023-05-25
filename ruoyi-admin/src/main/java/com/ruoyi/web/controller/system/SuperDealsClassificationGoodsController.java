package com.ruoyi.web.controller.system;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.annotation.RepeatSubmit;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.ShopGoodsInfo;
import com.ruoyi.system.domain.SuperDealsClassification;
import com.ruoyi.system.domain.SuperDealsClassificationGoods;
import com.ruoyi.system.service.ISuperDealsClassificationGoodsService;
import com.ruoyi.system.service.ISuperDealsClassificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * SuperDeals活动分类中的商品信息Controller
 * 
 * @author ruoyi
 * @date 2022-11-21
 */
@RestController
@RequestMapping("/system/superDealsClassificationGoods")
public class SuperDealsClassificationGoodsController extends BaseController
{
    @Autowired
    private ISuperDealsClassificationGoodsService superDealsClassificationGoodsService;

    @Autowired
    private ISuperDealsClassificationService superDealsClassificationService;

    /**
     * 查询SuperDeals活动分类中的商品信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:superDealsClassificationGoods:list')")
    @GetMapping("/list")
    public TableDataInfo list(SuperDealsClassificationGoods superDealsClassificationGoods)
    {
        startPage();
        List<SuperDealsClassificationGoods> list = superDealsClassificationGoodsService.selectSuperDealsClassificationGoodsList(superDealsClassificationGoods);
        return getDataTable(list);
    }

    /**
     * 获取SuperDeals活动分类中的商品信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:superDealsClassificationGoods:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(superDealsClassificationGoodsService.selectSuperDealsClassificationGoodsById(id));
    }

    /**
     * 新增SuperDeals活动分类中的商品信息
     */
    @PreAuthorize("@ss.hasPermi('system:superDealsClassificationGoods:add')")
    @Log(title = "SuperDeals活动分类中的商品信息", businessType = BusinessType.INSERT)
    @PostMapping
    @RepeatSubmit
    public AjaxResult add(@RequestBody SuperDealsClassificationGoods superDealsClassificationGoods)
    {
        if (StringUtils.isNull(superDealsClassificationGoods.getShopGoodsInfoId())){
            return AjaxResult.error("请选择要添加的商品");
        }
        if (StringUtils.isNull(superDealsClassificationGoods.getClassificationId())){
            return AjaxResult.error("请选择该商品所要添入的所属分类");
        }
        List<SuperDealsClassificationGoods> superDealsClassificationGoodsList = superDealsClassificationGoodsService.selectSuperDealsClassificationGoodsList(superDealsClassificationGoods);
        if (superDealsClassificationGoodsList.size() >= 1){
            return AjaxResult.error("该活动分类已经添加过此商品了");
        }
        return toAjax(superDealsClassificationGoodsService.insertSuperDealsClassificationGoods(superDealsClassificationGoods));
    }

    /**
     * 修改SuperDeals活动分类中的商品信息
     */
    @PreAuthorize("@ss.hasPermi('system:superDealsClassificationGoods:edit')")
    @Log(title = "SuperDeals活动分类中的商品信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SuperDealsClassificationGoods superDealsClassificationGoods)
    {
        if (StringUtils.isNull(superDealsClassificationGoods.getShopGoodsInfoId())){
            return AjaxResult.error("请选择要添加的商品");
        }
        if (StringUtils.isNull(superDealsClassificationGoods.getClassificationId())){
            return AjaxResult.error("请选择该商品所要添入的所属分类");
        }
        List<SuperDealsClassificationGoods> superDealsClassificationGoodsList = superDealsClassificationGoodsService.selectSuperDealsClassificationGoodsList(superDealsClassificationGoods);
        if (superDealsClassificationGoodsList.size() >= 1){
            return AjaxResult.error("该活动分类已经添加过此商品了");
        }
        return toAjax(superDealsClassificationGoodsService.updateSuperDealsClassificationGoods(superDealsClassificationGoods));
    }

    /**
     * 删除SuperDeals活动分类中的商品信息
     */
    @PreAuthorize("@ss.hasPermi('system:superDealsClassificationGoods:remove')")
    @Log(title = "SuperDeals活动分类中的商品信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(superDealsClassificationGoodsService.deleteSuperDealsClassificationGoodsByIds(ids));
    }

    /**
     * 查询可添加的商品列表
     */
    @GetMapping("/shopGoodsInfoList")
    public TableDataInfo platformGoodsInfoList(ShopGoodsInfo shopGoodsInfo)
    {
        startPage();
        shopGoodsInfo.setStatus(0);
        List<ShopGoodsInfo> list = superDealsClassificationGoodsService.selectShopGoodsInfoList(shopGoodsInfo);
        return getDataTable(list);
    }

    /**
     * 查询SuperDeals活动分类配置列表
     */
    @GetMapping("/superDealsClassificationList")
    public AjaxResult list(SuperDealsClassification superDealsClassification)
    {
        startPage();
        List<SuperDealsClassification> list = superDealsClassificationService.selectSuperDealsClassificationList(superDealsClassification);
        return AjaxResult.success(list);
    }

}
