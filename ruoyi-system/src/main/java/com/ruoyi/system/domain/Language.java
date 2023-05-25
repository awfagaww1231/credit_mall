package com.ruoyi.system.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 国家语言对象 language
 * 
 * @author ruoyi
 * @date 2022-11-17
 */
public class Language extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 语言 */
    @Excel(name = "语言")
    private String language;

    /** 语种简称 */
    @Excel(name = "语种简称")
    private String abbreviations;

    /** 国旗图标地址 */
    @Excel(name = "国旗图标地址")
    private String nationalflag;

    /** 多语言值 */
    @Excel(name = "多语言值")
    private String value;

    /** 状态：0：启用 1：禁用 */
    @Excel(name = "状态：0：启用 1：禁用")
    private Integer status;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setLanguage(String language) 
    {
        this.language = language;
    }

    public String getLanguage() 
    {
        return language;
    }
    public void setAbbreviations(String abbreviations) 
    {
        this.abbreviations = abbreviations;
    }

    public String getAbbreviations() 
    {
        return abbreviations;
    }
    public void setNationalflag(String nationalflag) 
    {
        this.nationalflag = nationalflag;
    }

    public String getNationalflag() 
    {
        return nationalflag;
    }
    public void setValue(String value) 
    {
        this.value = value;
    }

    public String getValue() 
    {
        return value;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("language", getLanguage())
            .append("abbreviations", getAbbreviations())
            .append("nationalflag", getNationalflag())
            .append("value", getValue())
            .append("status", getStatus())
            .toString();
    }
}
