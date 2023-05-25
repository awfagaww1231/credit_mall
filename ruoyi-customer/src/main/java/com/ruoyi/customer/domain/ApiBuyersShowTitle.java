package com.ruoyi.customer.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 购物灵感标题对象 buyers_show_title
 * 
 * @author ruoyi
 * @date 2022-11-25
 */
public class ApiBuyersShowTitle extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 标题名称 */
    @Excel(name = "标题名称")
    private String titleName;

    /** 默认展示的标签id */
    @Excel(name = "默认展示的标签id")
    private Long labelId;

    /** 标题图片 */
    @Excel(name = "标题图片")
    private String img;

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setTitleName(String titleName) 
    {
        this.titleName = titleName;
    }

    public String getTitleName() 
    {
        return titleName;
    }

    public Long getLabelId() {
        return labelId;
    }

    public void setLabelId(Long labelId) {
        this.labelId = labelId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("titleName", getTitleName())
            .append("labelId", getLabelId())
            .toString();
    }
}
