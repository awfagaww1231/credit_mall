package com.ruoyi.web.controller.system;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.annotation.RepeatSubmit;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.redis.RedisCache;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.*;
import com.ruoyi.system.service.ICategoryService;
import com.ruoyi.system.service.IPlatformGoodsInfoService;
import com.ruoyi.system.service.ISpecificationConfigService;
import com.ruoyi.system.service.ISpecificationValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 平台商品信息(平台的)Controller
 * 
 * @author ruoyi
 * @date 2022-10-27
 */
@RestController
@RequestMapping("/system/platformGoodsInfo")
public class PlatformGoodsInfoController extends BaseController
{
    @Autowired
    private IPlatformGoodsInfoService platformGoodsInfoService;

    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private ISpecificationConfigService specificationConfigService;

    @Autowired
    private ISpecificationValueService specificationValueService;


    @Autowired
    private RedisCache redisCache;

    /**
     * 查询平台商品信息(平台的)列表
     */
    @PreAuthorize("@ss.hasPermi('system:platformGoodsInfo:list')")
    @GetMapping("/list")
    public TableDataInfo list(PlatformGoodsInfo platformGoodsInfo)
    {
        startPage();
        List<PlatformGoodsInfo> list = platformGoodsInfoService.selectPlatformGoodsInfoList(platformGoodsInfo);
        return getDataTable(list);
    }

    /**
     * 导出平台商品信息(平台的)列表
     */
    @PreAuthorize("@ss.hasPermi('system:platformGoodsInfo:export')")
    @Log(title = "平台商品信息(平台的)", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PlatformGoodsInfo platformGoodsInfo)
    {
        List<PlatformGoodsInfo> list = platformGoodsInfoService.selectPlatformGoodsInfoList(platformGoodsInfo);
        ExcelUtil<PlatformGoodsInfo> util = new ExcelUtil<PlatformGoodsInfo>(PlatformGoodsInfo.class);
        util.exportExcel(response, list, "平台商品信息(平台的)数据");
    }

    /**
     * 导入平台商品信息(平台的)列表
     */
    @Log(title = "平台商品信息(平台的)", businessType = BusinessType.IMPORT)
    @PreAuthorize("@ss.hasPermi('system:platformGoodsInfo:import')")
    @PostMapping("/import")
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception
    {
        ////
        ////如果时间太久。可以考虑使用线程运行
        ExcelUtil<PlatformGoodsInfo> util = new ExcelUtil<PlatformGoodsInfo>(PlatformGoodsInfo.class);
        List<PlatformGoodsInfo> platformGoodsInfos = util.importExcel(file.getInputStream());
        String message = platformGoodsInfoService.importUser(platformGoodsInfos, updateSupport);
        return AjaxResult.success(message);
    }


    /**
     * 获取平台商品信息(平台的)详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:platformGoodsInfo:query')")
    @GetMapping(value = "/{id}/{languageId}")
    public AjaxResult getInfo(@PathVariable("id") Long id, @PathVariable("languageId") Long languageId )
    {
        return AjaxResult.success(platformGoodsInfoService.selectPlatformGoodsInfoById(id,languageId));
    }

    /**
     * 新增平台商品信息(平台的)
     */
    @PreAuthorize("@ss.hasPermi('system:platformGoodsInfo:add')")
    @Log(title = "平台商品信息(平台的)", businessType = BusinessType.INSERT)
    @RepeatSubmit
    @PostMapping
    public AjaxResult add(@RequestBody PlatformGoodsInfo platformGoodsInfo)
    {
        if (StringUtils.isEmpty(platformGoodsInfo.getGoodsName())){
            return AjaxResult.error("请输入商品名称");
        }
        if (StringUtils.isEmpty(platformGoodsInfo.getGoodsImg())){
            return AjaxResult.error("请上传商品图片封面");
        }
        if (StringUtils.isEmpty(platformGoodsInfo.getImgList())){
            return AjaxResult.error("商品其他图片至少需要一张");
        }
        if (StringUtils.isNull(platformGoodsInfo.getPrice())){
            return AjaxResult.error("请输入商品价格");
        }
        if (platformGoodsInfo.getPrice().compareTo(BigDecimal.ZERO) == -1){
            return AjaxResult.error("商品价格不允许低于0");
        }
        if (StringUtils.isNull(platformGoodsInfo.getGoodsInventory())){
            return AjaxResult.error("请输入商品库存数量");
        }
        if (platformGoodsInfo.getGoodsInventory() < 0){
            return AjaxResult.error("商品库存数量不允许低于0");
        }
        if (StringUtils.isEmpty(platformGoodsInfo.getIntroduce())){
            return AjaxResult.error("请输入商品介绍");
        }
        if (StringUtils.isEmpty(platformGoodsInfo.getDetail())){
            return AjaxResult.error("请输入商品详情");
        }
        if (StringUtils.isNull(platformGoodsInfo.getMinorClass())){
            return AjaxResult.error("请选择商品的所属类别");
        }
        return platformGoodsInfoService.insertPlatformGoodsInfo(platformGoodsInfo);
    }

    /**
     * 修改平台商品信息(平台的)
     */
    @PreAuthorize("@ss.hasPermi('system:platformGoodsInfo:edit')")
    @Log(title = "平台商品信息(平台的)", businessType = BusinessType.UPDATE)
    @RepeatSubmit
    @PutMapping
    public AjaxResult edit(@RequestBody PlatformGoodsInfo platformGoodsInfo)
    {
        if (StringUtils.isNull(platformGoodsInfo.getId())){
            return AjaxResult.error("请先选择需要修改信息的商品");
        }
        if (StringUtils.isEmpty(platformGoodsInfo.getGoodsName())){
            return AjaxResult.error("请输入商品名称");
        }
        if (StringUtils.isEmpty(platformGoodsInfo.getGoodsImg())){
            return AjaxResult.error("请上传商品图片封面");
        }
        if (StringUtils.isEmpty(platformGoodsInfo.getImgList())){
            return AjaxResult.error("商品其他图片至少需要一张");
        }
        if (platformGoodsInfo.getImgList().split(",").length > 5){
            return AjaxResult.error("商品其他图片最多只能五张");
        }
        if (StringUtils.isNull(platformGoodsInfo.getPrice())){
            return AjaxResult.error("请输入商品价格");
        }
        if (platformGoodsInfo.getPrice().compareTo(BigDecimal.ZERO) == -1){
            return AjaxResult.error("商品价格不允许低于0");
        }
        if (StringUtils.isNull(platformGoodsInfo.getGoodsInventory())){
            return AjaxResult.error("请输入商品库存数量");
        }
        if (platformGoodsInfo.getGoodsInventory() < 0){
            return AjaxResult.error("商品库存数量不允许低于0");
        }
        if (StringUtils.isEmpty(platformGoodsInfo.getIntroduce())){
            return AjaxResult.error("请输入商品介绍");
        }
        if (StringUtils.isEmpty(platformGoodsInfo.getDetail())){
            return AjaxResult.error("请输入商品详情");
        }
        if (StringUtils.isNull(platformGoodsInfo.getMinorClass())){
            return AjaxResult.error("请选择商品的所属类别");
        }
        return platformGoodsInfoService.updatePlatformGoodsInfo(platformGoodsInfo);
    }

    /**
     * 修改平台商品价格(平台的)
     */
    @PreAuthorize("@ss.hasPermi('system:platformGoodsInfo:edit')")
    @Log(title = "修改平台商品信息价格(平台的)", businessType = BusinessType.UPDATE)
    @RepeatSubmit
    @PostMapping("/editPrice")
    public AjaxResult editPrice(@RequestBody PlatformGoodsInfo platformGoodsInfo)
    {
        if (StringUtils.isNull(platformGoodsInfo.getId())){
            return AjaxResult.error("请先选择需要修改价格的商品");
        }
        if (StringUtils.isNull(platformGoodsInfo.getPrice())){
            return AjaxResult.error("请输入商品价格");
        }
        if (platformGoodsInfo.getPrice().compareTo(BigDecimal.ZERO) == -1){
            return AjaxResult.error("商品价格不允许低于0");
        }
        return platformGoodsInfoService.updatePlatformGoodsInfo(platformGoodsInfo);
    }

    /**
     * 删除平台商品信息(平台的)
     */
    @PreAuthorize("@ss.hasPermi('system:platformGoodsInfo:remove')")
    @Log(title = "平台商品信息(平台的)", businessType = BusinessType.DELETE)
    @RepeatSubmit
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(platformGoodsInfoService.deletePlatformGoodsInfoByIds(ids));
    }

    /**
     * 批量上架(平台的)
     */
    @PreAuthorize("@ss.hasPermi('system:platformGoodsInfo:onTheShelf')")
    @Log(title = "平台商品批量上架", businessType = BusinessType.UPDATE)
    @RepeatSubmit
    @PostMapping("/onTheShelf")
    public AjaxResult onTheShelf(@RequestBody List<Long> ids)
    {
        return platformGoodsInfoService.onTheShelf(ids);
    }

    /**
     * 批量下架(平台的)
     */
    @PreAuthorize("@ss.hasPermi('system:platformGoodsInfo:offTheShelf')")
    @Log(title = "平台商品批量下架", businessType = BusinessType.UPDATE)
    @RepeatSubmit
    @PostMapping("/offTheShelf")
    public AjaxResult offTheShelf(@RequestBody List<Long> ids)
    {
        return platformGoodsInfoService.offTheShelf(ids);
    }

    /**
     * 查看所有商品类别
     */
    @GetMapping("/categoryList")
    public TableDataInfo categoryList(Category category)
    {
        category.setCategoryLevel(3);
        List<Category> categories = categoryService.selectCategoryList(category);
        for (int i = 0; i < categories.size(); i++) {
            String categoryName = categoryService.getCategoryName(categories.get(i).getId());
            categories.get(i).setCategoryName(categoryName);
        }
        return getDataTable(categories);
    }

    /**
     * 查询商品规格配置列表
     */
    @GetMapping("/specificationConfigList")
    public AjaxResult specificationConfigList(SpecificationConfig specificationConfig)
    {
        List<SpecificationConfig> list = specificationConfigService.selectSpecificationConfigList(specificationConfig);
        return AjaxResult.success(list);
    }

    /**
     * 查询商品规格值列表
     */
    @GetMapping("/specificationValueList")
    public AjaxResult specificationValueList(SpecificationValue specificationValue)
    {
        List<SpecificationValue> list = specificationValueService.selectSpecificationValueList(specificationValue);
        return AjaxResult.success(list);
    }


    //爬虫拉取亚马逊商品数据
    /**
     * 爬虫拉取亚马逊商品数据
     */
    @PostMapping("/getAmazonGoodsInfo")
    @RepeatSubmit
    public AjaxResult getAmazonGoodsInfo(@RequestBody AmazonGoodsInfoJsoup amazonGoodsInfoJsoup) throws Exception {
        Object getAmazonGoodsInfo = redisCache.getCacheObject("getAmazonGoodsInfo");
        if (getAmazonGoodsInfo != null){
            return AjaxResult.error("还在进行上一次的数据拉取，请等待其拉取结束后再操作");
        }
        if (StringUtils.isNull(amazonGoodsInfoJsoup.getCollNum())){
            return AjaxResult.error("请选择每种分类拉去的新商品条数");
        }
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        try{
            executorService.execute(()->{
                try {
                    redisCache.setCacheObject("getAmazonGoodsInfo",0);
                    platformGoodsInfoService.getAmazonGoodsInfo(amazonGoodsInfoJsoup.getCategoryIds(),amazonGoodsInfoJsoup.getCollNum());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }finally {
                    //清除缓存
                    redisCache.deleteObject("getAmazonGoodsInfo");
                }
            });
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            //关闭线程池
            executorService.shutdown();
        }
        return AjaxResult.success();
    }

    /**
     * 查询3级分类列表
     */
    @GetMapping("/getMinorClassList")
    public TableDataInfo getMinorClassList(Category category){
        startPage();
        List<Category> minorClassList = platformGoodsInfoService.getMinorClassList(category);
        return getDataTable(minorClassList);
    }
}
