package com.ruoyi.system.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 轮播图对象 banner_img
 * 
 * @author ruoyi
 * @date 2023-04-04
 */
public class BannerImg extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 轮播图片 */
    @Excel(name = "轮播图片")
    private String bannerImg;

    /** 详情内容 */
    @Excel(name = "详情内容")
    private String content;

    /** 状态：0：启用 1：禁用 */
    @Excel(name = "状态：0：启用 1：禁用")
    private Integer status;

    /** 语言id */
    @Excel(name = "语言id")
    private Long languageId;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setBannerImg(String bannerImg) 
    {
        this.bannerImg = bannerImg;
    }

    public String getBannerImg() 
    {
        return bannerImg;
    }
    public void setContent(String content) 
    {
        this.content = content;
    }

    public String getContent() 
    {
        return content;
    }
    public void setStatus(Integer status) 
    {
        this.status = status;
    }

    public Integer getStatus() 
    {
        return status;
    }
    public void setLanguageId(Long languageId) 
    {
        this.languageId = languageId;
    }

    public Long getLanguageId() 
    {
        return languageId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("bannerImg", getBannerImg())
            .append("content", getContent())
            .append("status", getStatus())
            .append("languageId", getLanguageId())
            .toString();
    }
}
