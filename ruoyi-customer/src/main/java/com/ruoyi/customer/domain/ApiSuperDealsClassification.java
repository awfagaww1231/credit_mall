package com.ruoyi.customer.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.common.utils.x.lang.LangUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * SuperDeals活动分类配置对象 super_deals_classification
 * 
 * @author ruoyi
 * @date 2022-11-21
 */
public class ApiSuperDealsClassification extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 活动分类名称 */
    @Excel(name = "活动分类名称")
    private String classificationName;

    /** 英文-活动分类名称 */
    @Excel(name = "英文-活动分类名称")
    private String classificationNameEn;

    /** 繁体-活动分类名称 */
    @Excel(name = "繁体-活动分类名称")
    private String classificationNameTc;

    /** 德国-活动分类名称 */
    @Excel(name = "德国-活动分类名称")
    private String classificationNameDe;

    /** 西班牙-活动分类名称 */
    @Excel(name = "西班牙-活动分类名称")
    private String classificationNameEs;

    /** 法国-活动分类名称 */
    @Excel(name = "法国-活动分类名称")
    private String classificationNameFr;

    /** 印度尼西亚-活动分类名称 */
    @Excel(name = "印度尼西亚-活动分类名称")
    private String classificationNameIdn;

    /** 日本-活动分类名称 */
    @Excel(name = "日本-活动分类名称")
    private String classificationNameJp;

    /** 韩国-活动分类名称 */
    @Excel(name = "韩国-活动分类名称")
    private String classificationNameKo;

    /** 马来西亚-活动分类名称 */
    @Excel(name = "马来西亚-活动分类名称")
    private String classificationNameMy;

    /** 泰国-活动分类名称 */
    @Excel(name = "泰国-活动分类名称")
    private String classificationNameTh;

    /** 越南-活动分类名称 */
    @Excel(name = "越南-活动分类名称")
    private String classificationNameVi;

    /** 葡萄牙-活动分类名称 */
    @Excel(name = "葡萄牙-活动分类名称")
    private String classificationNamePt;

    /** 俄语-活动分类名称 */
    @Excel(name = "俄语-活动分类名称")
    private String classificationNameRus;

    /** 白俄罗斯-活动分类名称 */
    @Excel(name = "白俄罗斯-活动分类名称")
    private String classificationNameBlr;

    /** 印度-活动分类名称 */
    @Excel(name = "印度-活动分类名称")
    private String classificationNameIda;

    /** 沙特阿拉伯-活动分类名称 */
    @Excel(name = "沙特阿拉伯-活动分类名称")
    private String classificationNameSa;

    /** 阿拉伯-活动分类名称 */
    @Excel(name = "阿拉伯-活动分类名称")
    private String classificationNameAr;

    /** 意大利-活动分类名称 */
    @Excel(name = "意大利-活动分类名称")
    private String classificationNameIt;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setClassificationName(String classificationName)
    {
        this.classificationName = classificationName;
    }

    public String getClassificationName()
    {
        String lang = LangUtils.getLang();
        try {
            String langClassificationName = (String) PropertyUtils.getProperty(this, "classificationName" + LangUtils.toUpperCaseFirstIndex(lang));
            if (langClassificationName != null){
                return langClassificationName;
            }
        } catch (Exception e) {

        }
        return classificationName;
    }
    public void setClassificationNameEn(String classificationNameEn)
    {
        this.classificationNameEn = classificationNameEn;
    }

    public String getClassificationNameEn()
    {
        return classificationNameEn;
    }
    public void setClassificationNameTc(String classificationNameTc)
    {
        this.classificationNameTc = classificationNameTc;
    }

    public String getClassificationNameTc()
    {
        return classificationNameTc;
    }
    public void setClassificationNameDe(String classificationNameDe)
    {
        this.classificationNameDe = classificationNameDe;
    }

    public String getClassificationNameDe()
    {
        return classificationNameDe;
    }
    public void setClassificationNameEs(String classificationNameEs)
    {
        this.classificationNameEs = classificationNameEs;
    }

    public String getClassificationNameEs()
    {
        return classificationNameEs;
    }
    public void setClassificationNameFr(String classificationNameFr)
    {
        this.classificationNameFr = classificationNameFr;
    }

    public String getClassificationNameFr()
    {
        return classificationNameFr;
    }
    public void setClassificationNameIdn(String classificationNameIdn)
    {
        this.classificationNameIdn = classificationNameIdn;
    }

    public String getClassificationNameIdn()
    {
        return classificationNameIdn;
    }
    public void setClassificationNameJp(String classificationNameJp)
    {
        this.classificationNameJp = classificationNameJp;
    }

    public String getClassificationNameJp()
    {
        return classificationNameJp;
    }
    public void setClassificationNameKo(String classificationNameKo)
    {
        this.classificationNameKo = classificationNameKo;
    }

    public String getClassificationNameKo()
    {
        return classificationNameKo;
    }
    public void setClassificationNameMy(String classificationNameMy)
    {
        this.classificationNameMy = classificationNameMy;
    }

    public String getClassificationNameMy()
    {
        return classificationNameMy;
    }
    public void setClassificationNameTh(String classificationNameTh)
    {
        this.classificationNameTh = classificationNameTh;
    }

    public String getClassificationNameTh()
    {
        return classificationNameTh;
    }
    public void setClassificationNameVi(String classificationNameVi)
    {
        this.classificationNameVi = classificationNameVi;
    }

    public String getClassificationNameVi()
    {
        return classificationNameVi;
    }
    public void setClassificationNamePt(String classificationNamePt)
    {
        this.classificationNamePt = classificationNamePt;
    }

    public String getClassificationNamePt()
    {
        return classificationNamePt;
    }
    public void setClassificationNameRus(String classificationNameRus)
    {
        this.classificationNameRus = classificationNameRus;
    }

    public String getClassificationNameRus()
    {
        return classificationNameRus;
    }
    public void setClassificationNameBlr(String classificationNameBlr)
    {
        this.classificationNameBlr = classificationNameBlr;
    }

    public String getClassificationNameBlr()
    {
        return classificationNameBlr;
    }
    public void setClassificationNameIda(String classificationNameIda)
    {
        this.classificationNameIda = classificationNameIda;
    }

    public String getClassificationNameIda()
    {
        return classificationNameIda;
    }
    public void setClassificationNameSa(String classificationNameSa)
    {
        this.classificationNameSa = classificationNameSa;
    }

    public String getClassificationNameSa()
    {
        return classificationNameSa;
    }
    public void setClassificationNameAr(String classificationNameAr)
    {
        this.classificationNameAr = classificationNameAr;
    }

    public String getClassificationNameAr()
    {
        return classificationNameAr;
    }
    public void setClassificationNameIt(String classificationNameIt)
    {
        this.classificationNameIt = classificationNameIt;
    }

    public String getClassificationNameIt()
    {
        return classificationNameIt;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("classificationName", getClassificationName())
                .append("classificationNameEn", getClassificationNameEn())
                .append("classificationNameTc", getClassificationNameTc())
                .append("classificationNameDe", getClassificationNameDe())
                .append("classificationNameEs", getClassificationNameEs())
                .append("classificationNameFr", getClassificationNameFr())
                .append("classificationNameIdn", getClassificationNameIdn())
                .append("classificationNameJp", getClassificationNameJp())
                .append("classificationNameKo", getClassificationNameKo())
                .append("classificationNameMy", getClassificationNameMy())
                .append("classificationNameTh", getClassificationNameTh())
                .append("classificationNameVi", getClassificationNameVi())
                .append("classificationNamePt", getClassificationNamePt())
                .append("classificationNameRus", getClassificationNameRus())
                .append("classificationNameBlr", getClassificationNameBlr())
                .append("classificationNameIda", getClassificationNameIda())
                .append("classificationNameSa", getClassificationNameSa())
                .append("classificationNameAr", getClassificationNameAr())
                .append("classificationNameIt", getClassificationNameIt())
                .toString();
    }
}
