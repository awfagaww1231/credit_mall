package com.ruoyi.system.service.impl;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.amazon.AmazonItemSpider;
import com.ruoyi.common.utils.amazon.ItemCollInfo;
import com.ruoyi.common.utils.amazon.ItemCollTask;
import com.ruoyi.system.domain.*;
import com.ruoyi.system.mapper.CategoryMapper;
import com.ruoyi.system.mapper.GoodsSpecificationMapper;
import com.ruoyi.system.mapper.PlatformGoodsInfoMapper;
import com.ruoyi.system.mapper.ShopGoodsInfoMapper;
import com.ruoyi.system.service.ICategoryService;
import com.ruoyi.system.service.IPlatformGoodsInfoService;
import com.ruoyi.system.service.IShopGoodsInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 平台商品信息(平台的)Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-10-27
 */
@Service
public class PlatformGoodsInfoServiceImpl implements IPlatformGoodsInfoService 
{
    @Resource
    private PlatformGoodsInfoMapper platformGoodsInfoMapper;
    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private IShopGoodsInfoService shopGoodsInfoService;

    @Resource
    private ShopGoodsInfoMapper shopGoodsInfoMapper;

    @Resource
    private CategoryMapper categoryMapper;

    @Resource
    private GoodsSpecificationMapper goodsSpecificationMapper;
    /**
     * 查询平台商品信息(平台的)
     * 
     * @param id 平台商品信息(平台的)主键
     * @return 平台商品信息(平台的)
     */
    @Override
    public PlatformGoodsInfo selectPlatformGoodsInfoById(Long id)
    {
        PlatformGoodsInfo platformGoodsInfo = platformGoodsInfoMapper.selectPlatformGoodsInfoById(id);
        List<SpecificationConfig> specificationConfigs = getSpecification(platformGoodsInfo.getId());
        platformGoodsInfo.setSpecificationConfigs(specificationConfigs);
        return platformGoodsInfo;
    }



    @Override
    public PlatformGoodsInfo selectPlatformGoodsInfoById(Long id, Long languageId) {
        PlatformGoodsInfo platformGoodsInfo = platformGoodsInfoMapper.selectPlatformGoodsInfoLanguageById(id,languageId);
        List<SpecificationConfig> specificationConfigs = getSpecification(platformGoodsInfo.getId());
        platformGoodsInfo.setSpecificationConfigs(specificationConfigs);
        return platformGoodsInfo;
    }


    /**
     * 查询平台商品信息(平台的)列表
     * 
     * @param platformGoodsInfo 平台商品信息(平台的)
     * @return 平台商品信息(平台的)
     */
    @Override
    public List<PlatformGoodsInfo> selectPlatformGoodsInfoList(PlatformGoodsInfo platformGoodsInfo)
    {
        platformGoodsInfo.setIsDel(0);
        List<PlatformGoodsInfo> platformGoodsInfos = platformGoodsInfoMapper.selectPlatformGoodsInfoList(platformGoodsInfo);
        //赋值：类别信息
        for (int i = 0; i < platformGoodsInfos.size(); i++) {
            String categoryName = categoryService.getCategoryName(platformGoodsInfos.get(i).getMinorClass());
            platformGoodsInfos.get(i).setCategoryName(categoryName);
        }
        return platformGoodsInfos;
    }

    /**
     * 新增平台商品信息(平台的)
     * 
     * @param platformGoodsInfo 平台商品信息(平台的)
     * @return 结果
     */
    @Override
    @Transactional
    public AjaxResult insertPlatformGoodsInfo(PlatformGoodsInfo platformGoodsInfo)
    {
        platformGoodsInfo.setCreateTime(DateUtils.getNowDate());
        int insertPlatformGoodsInfo = platformGoodsInfoMapper.insertPlatformGoodsInfo(platformGoodsInfo);
        if (insertPlatformGoodsInfo <= 0){
            throw new RuntimeException();
        }
        List<SpecificationConfig> specificationConfigs = platformGoodsInfo.getSpecificationConfigs();
        //新增商品规格
        if (StringUtils.isNotNull(specificationConfigs)){
            for (int i = 0; i < specificationConfigs.size(); i++) {
                List<SpecificationValue> specificationValues = specificationConfigs.get(i).getSpecificationValues();
                for (int j = 0; j < specificationValues.size(); j++) {
                    GoodsSpecification goodsSpecification = new GoodsSpecification();
                    goodsSpecification.setGoodsId(platformGoodsInfo.getId());
                    goodsSpecification.setSpecificationConfigId(specificationConfigs.get(i).getId());
                    goodsSpecification.setSpecificationValueId(specificationValues.get(j).getId());
                    GoodsSpecification goodsSpecificationVo = goodsSpecificationMapper.selectGoodsSpecification(goodsSpecification);
                    if (StringUtils.isNull(goodsSpecificationVo)){
                        //新增商品规格绑定信息
                        int insertGoodsSpecification = goodsSpecificationMapper.insertGoodsSpecification(goodsSpecification);
                        if (insertGoodsSpecification <= 0){
                            throw new RuntimeException();
                        }
                    }
                }
            }
        }

        //同步更新中文语言信息
        this.changeLanguageValue(platformGoodsInfo);

        return AjaxResult.success();
    }

    /**
     * 修改平台商品信息(平台的)
     * 
     * @param platformGoodsInfo 平台商品信息(平台的)
     * @return 结果
     */
    @Override
    @Transactional
    public AjaxResult updatePlatformGoodsInfo(PlatformGoodsInfo platformGoodsInfo)
    {
        platformGoodsInfo.setUpdateTime(DateUtils.getNowDate());
        PlatformGoodsInfo platformGoodsInfoVo = platformGoodsInfoMapper.selectPlatformGoodsInfoById(platformGoodsInfo.getId());
        if (StringUtils.isNull(platformGoodsInfoVo)){
            return AjaxResult.error();
        }
        //如果价格有发生变动
        if (platformGoodsInfoVo.getPrice().compareTo(platformGoodsInfo.getPrice()) != 0){
            //平台修改价格的同时，把各个店铺售卖此商品的价格也需统一
            Long goodsId = platformGoodsInfo.getId();
            //修改后的价格
            BigDecimal price = platformGoodsInfo.getPrice();
            int updatePriceByGoodsId = shopGoodsInfoMapper.updatePriceByGoodsId(goodsId, price);
            if (updatePriceByGoodsId <= 0){
                //throw new RuntimeException();
            }
        }

        if ((StringUtils.isNotNull(platformGoodsInfoVo.getDiscountPrice()) & StringUtils.isNull(platformGoodsInfo.getDiscountPrice())) |
                StringUtils.isNull(platformGoodsInfoVo.getDiscountPrice()) & StringUtils.isNotNull(platformGoodsInfo.getDiscountPrice())){
            //平台修改价格的同时，把各个店铺售卖此商品的价格也需统一
            Long goodsId = platformGoodsInfo.getId();
            //修改后的价格
            BigDecimal discountPrice = platformGoodsInfo.getDiscountPrice();
            int updateDiscountPriceByGoodsId = shopGoodsInfoMapper.updateDiscountPriceByGoodsId(goodsId, discountPrice);
            if (updateDiscountPriceByGoodsId <= 0){
               // throw new RuntimeException();
            }
        }

        if (StringUtils.isNotNull(platformGoodsInfoVo.getDiscountPrice()) & StringUtils.isNotNull(platformGoodsInfo.getDiscountPrice())){
            //如果优惠有发生变动
            if (platformGoodsInfoVo.getDiscountPrice().compareTo(platformGoodsInfo.getDiscountPrice()) != 0){
                //平台修改价格的同时，把各个店铺售卖此商品的价格也需统一
                Long goodsId = platformGoodsInfo.getId();
                //修改后的价格
                BigDecimal discountPrice = platformGoodsInfo.getDiscountPrice();
                int updateDiscountPriceByGoodsId = shopGoodsInfoMapper.updateDiscountPriceByGoodsId(goodsId, discountPrice);
                if (updateDiscountPriceByGoodsId <= 0){
                   // throw new RuntimeException();
                }
            }
        }

        List<SpecificationConfig> specificationConfigs = platformGoodsInfo.getSpecificationConfigs();
        //新增商品规格
        if (StringUtils.isNotNull(specificationConfigs)){
            //先清空相关的商品规格
            goodsSpecificationMapper.deleteGoodsSpecificationByGoodsId(platformGoodsInfo.getId());
            for (int i = 0; i < specificationConfigs.size(); i++) {
                List<SpecificationValue> specificationValues = specificationConfigs.get(i).getSpecificationValues();
                for (int j = 0; j < specificationValues.size(); j++) {
                    GoodsSpecification goodsSpecification = new GoodsSpecification();
                    goodsSpecification.setGoodsId(platformGoodsInfo.getId());
                    goodsSpecification.setSpecificationConfigId(specificationConfigs.get(i).getId());
                    goodsSpecification.setSpecificationValueId(specificationValues.get(j).getId());
                    GoodsSpecification goodsSpecificationVo = goodsSpecificationMapper.selectGoodsSpecification(goodsSpecification);
                    if (StringUtils.isNull(goodsSpecificationVo)){
                        //新增商品规格绑定信息
                        int insertGoodsSpecification = goodsSpecificationMapper.insertGoodsSpecification(goodsSpecification);
                        if (insertGoodsSpecification <= 0){
                            throw new RuntimeException();
                        }
                    }
                }
            }
        }

        //多语言更新
        if (platformGoodsInfo.getLanguageId() != null ){
            this.changeLanguageValue(platformGoodsInfo);
            if (1L != platformGoodsInfo.getLanguageId()){
                platformGoodsInfo.setGoodsName(null);
                platformGoodsInfo.setIntroduce(null);
                platformGoodsInfo.setDetail(null);
                platformGoodsInfo.setGoodsImg(null);
                platformGoodsInfo.setImgList(null);
            }
        }

        //更新信息
        int updatePlatformGoodsInfo = platformGoodsInfoMapper.updatePlatformGoodsInfo(platformGoodsInfo);
        if (updatePlatformGoodsInfo <= 0){
            throw new RuntimeException();
        }

        return AjaxResult.success();
    }

    /**
     * 批量删除平台商品信息(平台的)
     * 
     * @param ids 需要删除的平台商品信息(平台的)主键
     * @return 结果
     */
    @Override
    public int deletePlatformGoodsInfoByIds(Long[] ids)
    {
        //平台商品删除的时候，变更所有涉及到的店铺商品状态为平台删除
        ShopGoodsInfo shopGoodsInfo = new ShopGoodsInfo();
        for (int i = 0; i < ids.length; i++) {
            Long goodsId = ids[i];
            shopGoodsInfo.setGoodsId(goodsId);
            List<ShopGoodsInfo> shopGoodsInfos = shopGoodsInfoService.selectShopGoodsInfoList(shopGoodsInfo);
            for (int j = 0; j < shopGoodsInfos.size(); j++) {
                shopGoodsInfos.get(j).setStatus(2);
                shopGoodsInfoService.updateShopGoodsInfo(shopGoodsInfos.get(j));
            }
        }
        return platformGoodsInfoMapper.deletePlatformGoodsInfoByIds(ids);
    }

    /**
     * 删除平台商品信息(平台的)信息
     * 
     * @param id 平台商品信息(平台的)主键
     * @return 结果
     */
    @Override
    public int deletePlatformGoodsInfoById(Long id)
    {
        return platformGoodsInfoMapper.deletePlatformGoodsInfoById(id);
    }

    @Override
    @Transactional
    public AjaxResult onTheShelf(List<Long> ids) {
        //平台商品上架的时候，变更所有涉及到的店铺商品状态为上架
        ShopGoodsInfo shopGoodsInfo = new ShopGoodsInfo();
        for (int i = 0; i < ids.size(); i++) {
            Long goodsId = ids.get(i);
            shopGoodsInfo.setGoodsId(goodsId);
            //店铺状态是正常营业的
            shopGoodsInfo.setShopStatus(0);
            List<ShopGoodsInfo> shopGoodsInfos = shopGoodsInfoService.selectShopGoodsInfoList(shopGoodsInfo);
            for (int j = 0; j < shopGoodsInfos.size(); j++) {
                shopGoodsInfos.get(j).setStatus(0);
                int updateShopGoodsInfo = shopGoodsInfoService.updateShopGoodsInfo(shopGoodsInfos.get(j));
                if (updateShopGoodsInfo <= 0){
                    throw new RuntimeException();
                }
            }
        }
        int onTheShelf = platformGoodsInfoMapper.onTheShelf(ids);
        if (onTheShelf <= 0){
            throw new RuntimeException();
        }
        return AjaxResult.success();
    }

    @Override
    @Transactional
    public AjaxResult offTheShelf(List<Long> ids) {
        //平台商品下架的时候，变更所有涉及到的店铺商品状态为下架
        ShopGoodsInfo shopGoodsInfo = new ShopGoodsInfo();
        for (int i = 0; i < ids.size(); i++) {
            Long goodsId = ids.get(i);
            shopGoodsInfo.setGoodsId(goodsId);
            List<ShopGoodsInfo> shopGoodsInfos = shopGoodsInfoService.selectShopGoodsInfoList(shopGoodsInfo);
            for (int j = 0; j < shopGoodsInfos.size(); j++) {
                shopGoodsInfos.get(j).setStatus(1);
                int updateShopGoodsInfo = shopGoodsInfoService.updateShopGoodsInfo(shopGoodsInfos.get(j));
                if (updateShopGoodsInfo <= 0){
                    throw new RuntimeException();
                }
            }
        }
        int offTheShelf = platformGoodsInfoMapper.offTheShelf(ids);
        if (offTheShelf <= 0){
            throw new RuntimeException();
        }
        return AjaxResult.success();
    }

    @Override
    public String importUser(List<PlatformGoodsInfo> platformGoodsInfos, Boolean isUpdateSupport) {
        if (StringUtils.isNull(platformGoodsInfos) || platformGoodsInfos.size() == 0)
        {
            throw new ServiceException("导入用户数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();

        for (int i = 0; i < platformGoodsInfos.size(); i++) {
            PlatformGoodsInfo platformGoodsInfo = platformGoodsInfos.get(i);
            int wrongFlag = 0;
            String msg = "<br/>" + "excel列表第" + (i+1) + "条数据信息的商品:";
            //验证输入格式
            //检验输入的商品名称是否为空
            if (StringUtils.isNull(platformGoodsInfo.getGoodsName()) | StringUtils.isEmpty(platformGoodsInfo.getGoodsName())){
                wrongFlag = 1;
                msg = msg + "商品名称为空、";
            }
            //检验输入的商品价格是否为空
            if (StringUtils.isNull(platformGoodsInfo.getPrice())){
                wrongFlag = 1;
                msg = msg + "商品价格为空、";
            }
            //检验输入的商品所属类别是否为空
            if (StringUtils.isNull(platformGoodsInfo.getCategoryName()) |  StringUtils.isEmpty(platformGoodsInfo.getCategoryName())){
                wrongFlag = 1;
                msg = msg + "商品所属类别为空、";
            }
            //检验商品数量是否为空
            if (StringUtils.isNull(platformGoodsInfo.getGoodsInventory())){
                wrongFlag = 1;
                msg = msg + "商品数量为空、";
            }
            if (StringUtils.isNotNull(platformGoodsInfo.getCategoryName()) &  StringUtils.isNotEmpty(platformGoodsInfo.getCategoryName())){
                //如果商品类目不为空
                String categoryName = platformGoodsInfo.getCategoryName();
                String[] split = platformGoodsInfo.getCategoryName().split("\\|");
                if (split.length != 2){
                    wrongFlag = 1;
                    msg = msg + "商品所属类目格式输入错误、";
                }else {
                    String fatherCategory = split[0];
                    String sonCategory = split[1];
                    //根据类目名称查找类目信息
                    Category fatherCategoryVo = categoryService.selectCategoryByName(fatherCategory);
                    Category sonCategoryVo = categoryService.selectCategoryByName(sonCategory);
                    if (StringUtils.isNull(fatherCategoryVo) | StringUtils.isNull(sonCategoryVo)){
                        wrongFlag = 1;
                        msg = msg + "不存在商品类目"+platformGoodsInfo.getCategoryName()+"、";
                    }else{
                        //如果一级类目和二级类目都存在
                        if (!sonCategoryVo.getParentId().equals(fatherCategoryVo.getId())){
                            wrongFlag = 1;
                            msg = msg + "商品类目"+fatherCategoryVo.getCategoryName()+"的子类中没有类目"+sonCategoryVo.getCategoryName()+"、";
                        }else{
                            //为此商品填入所属类目信息
                            platformGoodsInfo.setMinorClass(sonCategoryVo.getId());
                        }
                    }
                }
            }
            if (platformGoodsInfo.getPrice().compareTo(BigDecimal.ZERO) == -1){
                wrongFlag = 1;
                msg = msg + "商品价格输入有误、";
            }
            if (platformGoodsInfo.getGoodsInventory() < 0){
                wrongFlag = 1;
                msg = msg + "商品数量输入有误、";
            }

            //一切正常，开始写入数据
            if (wrongFlag == 0){
                Long goodsInfoId = platformGoodsInfo.getId();
                //验证是否存在此商品信息
                PlatformGoodsInfo platformGoodsInfoVo = platformGoodsInfoMapper.selectPlatformGoodsInfoById(goodsInfoId);
                //如果此商品不存在，则新增此商品信息
                if (StringUtils.isNull(platformGoodsInfoVo)){
                    this.insertPlatformGoodsInfo(platformGoodsInfo);
                    successNum++;
                    msg = msg + "导入成功";
                }
                //如果此商品存在，则更新此商品信息
                if (StringUtils.isNotNull(platformGoodsInfoVo)){
                    this.updatePlatformGoodsInfo(platformGoodsInfo);
                    successNum++;
                    msg = msg + "更新成功";
                }
                successMsg.append(msg);
            }else {
                failureMsg.append(msg);
                failureNum ++;
            }
        }
        if (failureNum > 0)
        {
            failureMsg.insert(0, "很抱歉，导入失败！共 " + failureNum + " 条数据格式不正确，错误如下：");
            throw new ServiceException(failureMsg.toString());
        }
        else
        {
            successMsg.insert(0, "恭喜您，数据已全部导入成功！共 " + successNum + " 条，数据如下：");
        }
        return successMsg.toString();
    }

    //获取商品绑定规格信息
    public List<SpecificationConfig> getSpecification(Long goodsId){
        List<SpecificationConfig> specificationConfigs = goodsSpecificationMapper.selectSpecificationConfigsByGoodsId(goodsId);
        for (int i = 0; i < specificationConfigs.size(); i++) {
            List<SpecificationValue> specificationValues = goodsSpecificationMapper.selectSpecificationValues(goodsId, specificationConfigs.get(i).getId());
            specificationConfigs.get(i).setSpecificationValues(specificationValues);
        }
        return specificationConfigs;
    }

    //爬虫拉取亚马逊商品数据
    public AjaxResult getAmazonGoodsInfo(List<Long> categoryIds,Long collNum) throws Exception {
        if (categoryIds.size() == 0){
            categoryIds = null;
        }
        List<Category> categories = categoryMapper.selectCategoryListJsoup(categoryIds);
//        RedisCache redisCache = SpringUtils.getBean(RedisCache.class);
        for (Category category : categories) {
            Long id = category.getId();
//            Object obj = redisCache.getCacheObject("getAmazonGoodsInfo");
//            if (obj == null){
//                redisCache.setCacheObject("getAmazonGoodsInfo",id-1);
//                obj = id-1;
//            }
//            Long getAmazonGoodsInfo = Long.valueOf(obj.toString());
//            if (id <= getAmazonGoodsInfo){
//                continue;
//            }
            String categoryName = categoryService.getCategoryName(id);//取英文
            String searchKeyword = category.getSearchKeyword();
            if (StringUtils.isEmpty(searchKeyword)){
                searchKeyword = categoryName.replaceAll("\\|","+");
            }else {
                searchKeyword = searchKeyword.replaceAll(" ","+");
            }
            //String categoryName = category.getCategoryName();
            AmazonItemSpider amazonItemSpider = new AmazonItemSpider();
            ItemCollTask itemCollTask = new ItemCollTask();
            itemCollTask.setCollNum(collNum);
            itemCollTask.setCollUrl("https://www.amazon.com/s?k="+searchKeyword);
            itemCollTask.setCollType("2");
            List<ItemCollInfo> itemCollInfos = amazonItemSpider.collItemInfo(itemCollTask, 1);
            if (itemCollInfos == null){
                continue;
            }
            for (int i = 0; i < itemCollInfos.size(); i++) {
                ItemCollInfo itemCollInfo = itemCollInfos.get(i);
                PlatformGoodsInfo platformGoodsInfo = new PlatformGoodsInfo();
                platformGoodsInfo.setGoodsName(itemCollInfo.getItemName());
                platformGoodsInfo.setItemCode(itemCollInfo.getItemCode());
                platformGoodsInfo.setItemUrl(itemCollInfo.getItemUrl());
                platformGoodsInfo.setGoodsImg(itemCollInfo.getMainImages());
                platformGoodsInfo.setImgList(itemCollInfo.getTagImages());
                platformGoodsInfo.setPrice(itemCollInfo.getItemPrice());//.divide(new BigDecimal(7.5),2,BigDecimal.ROUND_HALF_UP));
                platformGoodsInfo.setGoodsInventory(999L);
                platformGoodsInfo.setCreateTime(new Date());
                platformGoodsInfo.setDetail(itemCollInfo.getItemDescription());
                platformGoodsInfo.setIntroduce(itemCollInfo.getItemFeature());
                platformGoodsInfo.setMinorClass(id);
                try {
                    platformGoodsInfoMapper.insertPlatformGoodsInfo(platformGoodsInfo);
                    //更新英文语言
                    itemCollInfo.setLcMain("en-US");//采集英文
                    ItemCollInfo itemInfoEn = amazonItemSpider.collItemDetailInfo(itemCollInfo);
                    PlatformGoodsInfo platformGoodsInfoEn = new PlatformGoodsInfo();
                    platformGoodsInfoEn.setId(platformGoodsInfo.getId());
                    platformGoodsInfoEn.setGoodsName(itemInfoEn.getItemName());
                    platformGoodsInfoEn.setGoodsImg(itemInfoEn.getMainImages());
                    platformGoodsInfoEn.setImgList(itemInfoEn.getTagImages());
                    platformGoodsInfoEn.setPrice(itemInfoEn.getItemPrice());//.divide(new BigDecimal(7.5),2,BigDecimal.ROUND_HALF_UP));
                    platformGoodsInfoEn.setGoodsInventory(999L);
                    platformGoodsInfoEn.setCreateTime(new Date());
                    platformGoodsInfoEn.setDetail(itemInfoEn.getItemDescription());
                    platformGoodsInfoEn.setIntroduce(itemInfoEn.getItemFeature());
                    platformGoodsInfoEn.setMinorClass(id);
                    platformGoodsInfoEn.setLanguageId(2l);//英文语言ID
                    changeLanguageValue(platformGoodsInfoEn);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
//            redisCache.setCacheObject("getAmazonGoodsInfo",id);
            System.out.println("分类"+categoryName + "采集数据完成");
        }
        return AjaxResult.success();
    }

    @Override
    public int changeLanguageValue(PlatformGoodsInfo platformGoodsInfo) {
        Long languageId = platformGoodsInfo.getLanguageId();
        Long goodsId = platformGoodsInfo.getId();
        PlatformGoodsInfo languageObject = platformGoodsInfoMapper.selectLanguageObject(languageId, goodsId);
        //如果多语言信息不存在
        if (StringUtils.isNull(languageObject)){
            return platformGoodsInfoMapper.insertLanguageValue(platformGoodsInfo);
        }else {
            return platformGoodsInfoMapper.changeLanguageValue(platformGoodsInfo);
        }
    }
    /**
     * 查询3级分类列表
     */
    @Override
    public List<Category> getMinorClassList(Category category){
        category.setCategoryLevel(3);
        List<Category> categories = categoryMapper.selectCategoryList(category);
        return categories;
    }
}
