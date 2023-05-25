package com.ruoyi.system.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 系统app所有富文本多语言内容对象 rich_text_lang
 * 
 * @author ruoyi
 * @date 2023-03-26
 */
public class RichTextLang extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 内容 */
    @Excel(name = "内容")
    private String content;

    /** 文本类型 */
    @Excel(name = "文本类型")
    private Integer textType;

    /** 英文-内容 */
    @Excel(name = "英文-内容")
    private String contentEn;

    /** 繁体-内容 */
    @Excel(name = "繁体-内容")
    private String contentTc;

    /** 德国-内容 */
    @Excel(name = "德国-内容")
    private String contentDe;

    /** 西班牙-内容 */
    @Excel(name = "西班牙-内容")
    private String contentEs;

    /** 法国-内容 */
    @Excel(name = "法国-内容")
    private String contentFr;

    /** 印度尼西亚-内容 */
    @Excel(name = "印度尼西亚-内容")
    private String contentIdn;

    /** 日本-内容 */
    @Excel(name = "日本-内容")
    private String contentJp;

    /** 韩国-内容 */
    @Excel(name = "韩国-内容")
    private String contentKo;

    /** 马来西亚-内容 */
    @Excel(name = "马来西亚-内容")
    private String contentMy;

    /** 泰国-内容 */
    @Excel(name = "泰国-内容")
    private String contentTh;

    /** 越南-内容 */
    @Excel(name = "越南-内容")
    private String contentVi;

    /** 葡萄牙-内容 */
    @Excel(name = "葡萄牙-内容")
    private String contentPt;

    /** 俄语-内容 */
    @Excel(name = "俄语-内容")
    private String contentRus;

    /** 白俄罗斯-内容 */
    @Excel(name = "白俄罗斯-内容")
    private String contentBlr;

    /** 印度-内容 */
    @Excel(name = "印度-内容")
    private String contentIda;

    /** 沙特阿拉伯-内容 */
    @Excel(name = "沙特阿拉伯-内容")
    private String contentSa;

    /** 阿拉伯-内容 */
    @Excel(name = "阿拉伯-内容")
    private String contentAr;

    /** 意大利-内容 */
    @Excel(name = "意大利-内容")
    private String contentIt;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setContent(String content) 
    {
        this.content = content;
    }

    public String getContent() 
    {
        return content;
    }
    public void setTextType(Integer textType) 
    {
        this.textType = textType;
    }

    public Integer getTextType()
    {
        return textType;
    }
    public void setContentEn(String contentEn)
    {
        this.contentEn = contentEn;
    }

    public String getContentEn()
    {
        return contentEn;
    }
    public void setContentTc(String contentTc)
    {
        this.contentTc = contentTc;
    }

    public String getContentTc()
    {
        return contentTc;
    }
    public void setContentDe(String contentDe)
    {
        this.contentDe = contentDe;
    }

    public String getContentDe()
    {
        return contentDe;
    }
    public void setContentEs(String contentEs)
    {
        this.contentEs = contentEs;
    }

    public String getContentEs()
    {
        return contentEs;
    }
    public void setContentFr(String contentFr)
    {
        this.contentFr = contentFr;
    }

    public String getContentFr()
    {
        return contentFr;
    }
    public void setContentIdn(String contentIdn)
    {
        this.contentIdn = contentIdn;
    }

    public String getContentIdn()
    {
        return contentIdn;
    }
    public void setContentJp(String contentJp)
    {
        this.contentJp = contentJp;
    }

    public String getContentJp()
    {
        return contentJp;
    }
    public void setContentKo(String contentKo)
    {
        this.contentKo = contentKo;
    }

    public String getContentKo()
    {
        return contentKo;
    }
    public void setContentMy(String contentMy)
    {
        this.contentMy = contentMy;
    }

    public String getContentMy()
    {
        return contentMy;
    }
    public void setContentTh(String contentTh)
    {
        this.contentTh = contentTh;
    }

    public String getContentTh()
    {
        return contentTh;
    }
    public void setContentVi(String contentVi)
    {
        this.contentVi = contentVi;
    }

    public String getContentVi()
    {
        return contentVi;
    }
    public void setContentPt(String contentPt)
    {
        this.contentPt = contentPt;
    }

    public String getContentPt()
    {
        return contentPt;
    }
    public void setContentRus(String contentRus)
    {
        this.contentRus = contentRus;
    }

    public String getContentRus()
    {
        return contentRus;
    }
    public void setContentBlr(String contentBlr)
    {
        this.contentBlr = contentBlr;
    }

    public String getContentBlr()
    {
        return contentBlr;
    }
    public void setContentIda(String contentIda)
    {
        this.contentIda = contentIda;
    }

    public String getContentIda()
    {
        return contentIda;
    }
    public void setContentSa(String contentSa)
    {
        this.contentSa = contentSa;
    }

    public String getContentSa()
    {
        return contentSa;
    }
    public void setContentAr(String contentAr)
    {
        this.contentAr = contentAr;
    }

    public String getContentAr()
    {
        return contentAr;
    }
    public void setContentIt(String contentIt)
    {
        this.contentIt = contentIt;
    }

    public String getContentIt()
    {
        return contentIt;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("content", getContent())
            .append("textType", getTextType())
            .append("contentEn", getContentEn())
            .append("contentTc", getContentTc())
            .append("contentDe", getContentDe())
            .append("contentEs", getContentEs())
            .append("contentFr", getContentFr())
            .append("contentIdn", getContentIdn())
            .append("contentJp", getContentJp())
            .append("contentKo", getContentKo())
            .append("contentMy", getContentMy())
            .append("contentTh", getContentTh())
            .append("contentVi", getContentVi())
            .append("contentPt", getContentPt())
            .append("contentRus", getContentRus())
            .append("contentBlr", getContentBlr())
            .append("contentIda", getContentIda())
            .append("contentSa", getContentSa())
            .append("contentAr", getContentAr())
            .append("contentIt", getContentIt())
            .toString();
    }
}
