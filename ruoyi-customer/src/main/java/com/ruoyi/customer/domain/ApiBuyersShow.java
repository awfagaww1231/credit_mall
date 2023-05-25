package com.ruoyi.customer.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import java.util.List;

/**
 * 买家秀帖子对象 buyers_show
 * 
 * @author ruoyi
 * @date 2022-11-25
 */
public class ApiBuyersShow extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 用户id（买家） */
    @Excel(name = "用户id", readConverterExp = "买=家")
    private Long userId;

    /** 用户名 */
    @Excel(name = "用户名")
    private String userName;

    /** 头像地址 */
    @Excel(name = "头像地址")
    private String avatar;


    /** 买家秀标题 */
    @Excel(name = "买家秀标题")
    private String showTitle;

    /** 买家秀内容 */
    @Excel(name = "买家秀内容")
    private String showContent;

    /** 买家秀图片 */
    @Excel(name = "买家秀图片")
    private String showImg;

    /** 帖子浏览量 */
    @Excel(name = "帖子浏览量")
    private Integer views;

    /** 店铺商品id */
    @Excel(name = "店铺商品id")
    private Long shopGoodsInfoId;

    /** 筛选使用 */
    @Excel(name = "筛选使用")
    private Long labelId;

    /** 排序类型 1：按热度排序 2：按时间排序*/
    @Excel(name = "排序类型")
    private int sortType;

    /** 标签信息集合 */
    @Excel(name = "标签信息集合")
    private List<ApiBuyersShowLabel> apiBuyersShowLabels;

    public List<ApiBuyersShowLabel> getApiBuyersShowLabels() {
        return apiBuyersShowLabels;
    }

    public String getShowImg() {
        return showImg;
    }

    public int getSortType() {
        return sortType;
    }

    public void setSortType(int sortType) {
        this.sortType = sortType;
    }

    public void setShowImg(String showImg) {
        this.showImg = showImg;
    }

    public void setApiBuyersShowLabels(List<ApiBuyersShowLabel> apiBuyersShowLabels) {
        this.apiBuyersShowLabels = apiBuyersShowLabels;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Long getLabelId() {
        return labelId;
    }

    public void setLabelId(Long labelId) {
        this.labelId = labelId;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setShowTitle(String showTitle) 
    {
        this.showTitle = showTitle;
    }

    public String getShowTitle() 
    {
        return showTitle;
    }
    public void setShowContent(String showContent) 
    {
        this.showContent = showContent;
    }

    public String getShowContent() 
    {
        return showContent;
    }
    public void setViews(Integer views) 
    {
        this.views = views;
    }

    public Integer getViews() 
    {
        return views;
    }
    public void setShopGoodsInfoId(Long shopGoodsInfoId) 
    {
        this.shopGoodsInfoId = shopGoodsInfoId;
    }

    public Long getShopGoodsInfoId() 
    {
        return shopGoodsInfoId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userId", getUserId())
            .append("showTitle", getShowTitle())
            .append("showContent", getShowContent())
            .append("views", getViews())
            .append("createTime", getCreateTime())
            .append("shopGoodsInfoId", getShopGoodsInfoId())
            .toString();
    }
}
