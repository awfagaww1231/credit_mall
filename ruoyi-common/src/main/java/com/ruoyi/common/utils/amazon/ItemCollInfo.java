package com.ruoyi.common.utils.amazon;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 从第三方平台进行商品采集的信息数据对象 item_coll_info
 * 
 * @author ruoyi
 * @date 2023-03-07
 */
public class ItemCollInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 商品ID */
    private Long itemCollId;

    /** 商品编码 */
    @Excel(name = "商品编码")
    private String itemCode;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String itemUrl;

    /** 商品名称 */
    @Excel(name = "商品名称")
    private String itemName;

    /** 变体类型 */
    @Excel(name = "变体类型")
    private Long variantType;

    /** 商品价格 */
    @Excel(name = "商品价格")
    private BigDecimal itemPrice;

    /** 价格类型 */
    @Excel(name = "价格类型")
    private String priceType;

    /** 商品预览图 */
    @Excel(name = "商品预览图")
    private String tagImages;

    /** 商品主图 */
    @Excel(name = "商品主图")
    private String mainImages;

    /** 可用库存 */
    @Excel(name = "可用库存")
    private String availableStock;

    /** 商品品牌 */
    @Excel(name = "商品品牌")
    private String itemBrand;

    /** 库存数量 */
    @Excel(name = "库存数量")
    private Long stockNum;

    /** 包装重量 */
    @Excel(name = "库存数量")
    private BigDecimal shippingWeight;

    /** 商品特色 */
    @Excel(name = "商品特色")
    private String itemFeature;

    /** 商品描述 */
    @Excel(name = "商品描述")
    private String itemDescription;

    /** 来源平台 */
    @Excel(name = "来源平台")
    private String sourcePlatform;

    /** 来源国家 */
    @Excel(name = "来源国家")
    private String sourceCountry;

    /** 普通发货日期 */
    @Excel(name = "普通发货日期")
    private String commDeliveryDate;

    /** 最快发货日期 */
    @Excel(name = "最快发货日期")
    private String fastDeliverDate;

    /** 采集类型 */
    @Excel(name = "采集类型")
    private String collType;

    /** 采集状态 0采集失败 1待采集 2采集中 3采集成功 */
    @Excel(name = "采集状态 0采集失败 1待采集 2采集中 3采集成功")
    private String collStatus;

    /** 导入商品状态 */
    @Excel(name = "导入商品状态")
    private String importState;

    /** 关联任务id */
    @Excel(name = "关联任务id")
    private Long taskId;

    /** 删除标识 */
    private String delFlag;

    /** 库存数量 */
    @Excel(name = "监控库存数量")
    private Long monitorStockNum;
    /** 可用库存 */
    @Excel(name = "监控可用库存")
    private String monitorAvailableStock;
    /** 商品价格 */
    @Excel(name = "监控商品价格")
    private BigDecimal monitorItemPrice;

    @Excel(name = "监控结果")
    private String monitorResult;

    @Excel(name = "监控失败原因")
    private String monitorFailReason;

    @Excel(name = "监控时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date monitorTime;

    //采集语言
    private String lcMain ="zh-CN";

    public void setItemCollId(Long itemCollId) 
    {
        this.itemCollId = itemCollId;
    }

    public Long getItemCollId() 
    {
        return itemCollId;
    }
    public void setItemCode(String itemCode) 
    {
        this.itemCode = itemCode;
    }

    public String getItemCode() 
    {
        return itemCode;
    }
    public void setItemUrl(String itemUrl) 
    {
        this.itemUrl = itemUrl;
    }

    public String getItemUrl() 
    {
        return itemUrl;
    }
    public void setItemName(String itemName) 
    {
        this.itemName = itemName;
    }

    public String getItemName() 
    {
        return itemName;
    }
    public void setVariantType(Long variantType) 
    {
        this.variantType = variantType;
    }

    public Long getVariantType() 
    {
        return variantType;
    }
    public void setItemPrice(BigDecimal itemPrice) 
    {
        this.itemPrice = itemPrice;
    }

    public BigDecimal getItemPrice() 
    {
        return itemPrice;
    }
    public void setPriceType(String priceType) 
    {
        this.priceType = priceType;
    }

    public String getPriceType() 
    {
        return priceType;
    }
    public void setTagImages(String tagImages) 
    {
        this.tagImages = tagImages;
    }

    public String getTagImages() 
    {
        return tagImages;
    }
    public void setMainImages(String mainImages) 
    {
        this.mainImages = mainImages;
    }

    public String getMainImages() 
    {
        return mainImages;
    }
    public void setAvailableStock(String availableStock) 
    {
        this.availableStock = availableStock;
    }

    public String getAvailableStock() 
    {
        return availableStock;
    }
    public void setItemBrand(String itemBrand) 
    {
        this.itemBrand = itemBrand;
    }

    public String getItemBrand() 
    {
        return itemBrand;
    }
    public void setStockNum(Long stockNum) 
    {
        this.stockNum = stockNum;
    }

    public Long getStockNum() 
    {
        return stockNum;
    }
    public void setItemFeature(String itemFeature) 
    {
        this.itemFeature = itemFeature;
    }

    public String getItemFeature() 
    {
        return itemFeature;
    }
    public void setItemDescription(String itemDescription) 
    {
        this.itemDescription = itemDescription;
    }

    public String getItemDescription() 
    {
        return itemDescription;
    }
    public void setSourcePlatform(String sourcePlatform) 
    {
        this.sourcePlatform = sourcePlatform;
    }

    public String getSourcePlatform() 
    {
        return sourcePlatform;
    }
    public void setSourceCountry(String sourceCountry) 
    {
        this.sourceCountry = sourceCountry;
    }

    public String getSourceCountry() 
    {
        return sourceCountry;
    }
    public void setCommDeliveryDate(String commDeliveryDate) 
    {
        this.commDeliveryDate = commDeliveryDate;
    }

    public String getCommDeliveryDate() 
    {
        return commDeliveryDate;
    }
    public void setFastDeliverDate(String fastDeliverDate) 
    {
        this.fastDeliverDate = fastDeliverDate;
    }

    public String getFastDeliverDate() 
    {
        return fastDeliverDate;
    }
    public void setCollType(String collType) 
    {
        this.collType = collType;
    }

    public String getCollType() 
    {
        return collType;
    }
    public void setCollStatus(String collStatus) 
    {
        this.collStatus = collStatus;
    }

    public String getCollStatus() 
    {
        return collStatus;
    }
    public void setImportState(String importState) 
    {
        this.importState = importState;
    }

    public String getImportState() 
    {
        return importState;
    }
    public void setTaskId(Long taskId) 
    {
        this.taskId = taskId;
    }

    public Long getTaskId() 
    {
        return taskId;
    }
    public void setDelFlag(String delFlag) 
    {
        this.delFlag = delFlag;
    }

    public String getDelFlag() 
    {
        return delFlag;
    }

    public BigDecimal getShippingWeight() {
        return shippingWeight;
    }

    public void setShippingWeight(BigDecimal shippingWeight) {
        this.shippingWeight = shippingWeight;
    }

    public Long getMonitorStockNum() {
        return monitorStockNum;
    }

    public void setMonitorStockNum(Long monitorStockNum) {
        this.monitorStockNum = monitorStockNum;
    }

    public String getMonitorAvailableStock() {
        return monitorAvailableStock;
    }

    public void setMonitorAvailableStock(String monitorAvailableStock) {
        this.monitorAvailableStock = monitorAvailableStock;
    }

    public BigDecimal getMonitorItemPrice() {
        return monitorItemPrice;
    }

    public void setMonitorItemPrice(BigDecimal monitorItemPrice) {
        this.monitorItemPrice = monitorItemPrice;
    }

    public String getMonitorResult() {
        return monitorResult;
    }

    public void setMonitorResult(String monitorResult) {
        this.monitorResult = monitorResult;
    }

    public String getMonitorFailReason() {
        return monitorFailReason;
    }

    public void setMonitorFailReason(String monitorFailReason) {
        this.monitorFailReason = monitorFailReason;
    }

    public Date getMonitorTime() {
        return monitorTime;
    }

    public void setMonitorTime(Date monitorTime) {
        this.monitorTime = monitorTime;
    }

    public String getLcMain() {
        return lcMain;
    }

    public void setLcMain(String lcMain) {
        this.lcMain = lcMain;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("itemCollId", getItemCollId())
            .append("itemCode", getItemCode())
            .append("itemUrl", getItemUrl())
            .append("itemName", getItemName())
            .append("variantType", getVariantType())
            .append("itemPrice", getItemPrice())
            .append("priceType", getPriceType())
            .append("tagImages", getTagImages())
            .append("mainImages", getMainImages())
            .append("availableStock", getAvailableStock())
            .append("itemBrand", getItemBrand())
            .append("stockNum", getStockNum())
            .append("shippingWeight", getShippingWeight())
            .append("itemFeature", getItemFeature())
            .append("itemDescription", getItemDescription())
            .append("sourcePlatform", getSourcePlatform())
            .append("sourceCountry", getSourceCountry())
            .append("commDeliveryDate", getCommDeliveryDate())
            .append("fastDeliverDate", getFastDeliverDate())
            .append("collType", getCollType())
            .append("collStatus", getCollStatus())
            .append("importState", getImportState())
            .append("taskId", getTaskId())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("monitorItemPrice", getMonitorItemPrice())
            .append("monitorAvailableStock", getMonitorAvailableStock())
            .append("monitorStockNum", getMonitorStockNum())
            .append("monitorResult", getMonitorResult())
            .append("monitorFailReason", getMonitorFailReason())
            .append("monitorTime", getMonitorTime())
            .append("lcMain", getLcMain())
            .toString();
    }
}
