package com.ruoyi.customer.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 买家秀的标签对象 buyers_show_label
 * 
 * @author ruoyi
 * @date 2022-11-25
 */
public class ApiBuyersShowLabel extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 标签名称 */
    @Excel(name = "标签名称")
    private String labelName;

    /** 标签帖数 */
    @Excel(name = "标签帖数")
    private Integer postsNumber;

    public Integer getPostsNumber() {
        return postsNumber;
    }

    public void setPostsNumber(Integer postsNumber) {
        this.postsNumber = postsNumber;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setLabelName(String labelName) 
    {
        this.labelName = labelName;
    }

    public String getLabelName() 
    {
        return labelName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("labelName", getLabelName())
            .toString();
    }
}
