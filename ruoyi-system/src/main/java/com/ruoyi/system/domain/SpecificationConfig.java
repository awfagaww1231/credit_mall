package com.ruoyi.system.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;

/**
 * 商品规格配置对象 specification_config
 * 
 * @author ruoyi
 * @date 2023-04-23
 */
public class SpecificationConfig extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    //规格里的所有值
    private List<SpecificationValue> specificationValues;

    /** 规格名称 */
    @Excel(name = "规格名称")
    private String specificationName;

    /** 英文-规格名称 */
    @Excel(name = "英文-规格名称")
    private String specificationNameEn;

    /** 繁体-规格名称 */
    @Excel(name = "繁体-规格名称")
    private String specificationNameTc;

    /** 德国-规格名称 */
    @Excel(name = "德国-规格名称")
    private String specificationNameDe;

    /** 西班牙-规格名称 */
    @Excel(name = "西班牙-规格名称")
    private String specificationNameEs;

    /** 法国-规格名称 */
    @Excel(name = "法国-规格名称")
    private String specificationNameFr;

    /** 印度尼西亚-规格名称 */
    @Excel(name = "印度尼西亚-规格名称")
    private String specificationNameIdn;

    /** 日本-规格名称 */
    @Excel(name = "日本-规格名称")
    private String specificationNameJp;

    /** 韩国-规格名称 */
    @Excel(name = "韩国-规格名称")
    private String specificationNameKo;

    /** 马来西亚-规格名称 */
    @Excel(name = "马来西亚-规格名称")
    private String specificationNameMy;

    /** 泰国-规格名称 */
    @Excel(name = "泰国-规格名称")
    private String specificationNameTh;

    /** 越南-规格名称 */
    @Excel(name = "越南-规格名称")
    private String specificationNameVi;

    /** 葡萄牙-规格名称 */
    @Excel(name = "葡萄牙-规格名称")
    private String specificationNamePt;

    /** 俄语-规格名称 */
    @Excel(name = "俄语-规格名称")
    private String specificationNameRus;

    /** 白俄罗斯-规格名称 */
    @Excel(name = "白俄罗斯-规格名称")
    private String specificationNameBlr;

    /** 印度-规格名称 */
    @Excel(name = "印度-规格名称")
    private String specificationNameIda;

    /** 沙特阿拉伯-规格名称 */
    @Excel(name = "沙特阿拉伯-规格名称")
    private String specificationNameSa;

    /** 阿拉伯-规格名称 */
    @Excel(name = "阿拉伯-规格名称")
    private String specificationNameAr;

    /** 意大利-规格名称 */
    @Excel(name = "意大利-规格名称")
    private String specificationNameIt;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setSpecificationName(String specificationName) 
    {
        this.specificationName = specificationName;
    }

    public String getSpecificationName() 
    {
        return specificationName;
    }
    public void setSpecificationNameEn(String specificationNameEn) 
    {
        this.specificationNameEn = specificationNameEn;
    }

    public String getSpecificationNameEn() 
    {
        return specificationNameEn;
    }
    public void setSpecificationNameTc(String specificationNameTc) 
    {
        this.specificationNameTc = specificationNameTc;
    }

    public String getSpecificationNameTc() 
    {
        return specificationNameTc;
    }
    public void setSpecificationNameDe(String specificationNameDe) 
    {
        this.specificationNameDe = specificationNameDe;
    }

    public String getSpecificationNameDe() 
    {
        return specificationNameDe;
    }
    public void setSpecificationNameEs(String specificationNameEs) 
    {
        this.specificationNameEs = specificationNameEs;
    }

    public String getSpecificationNameEs() 
    {
        return specificationNameEs;
    }
    public void setSpecificationNameFr(String specificationNameFr) 
    {
        this.specificationNameFr = specificationNameFr;
    }

    public String getSpecificationNameFr() 
    {
        return specificationNameFr;
    }
    public void setSpecificationNameIdn(String specificationNameIdn) 
    {
        this.specificationNameIdn = specificationNameIdn;
    }

    public String getSpecificationNameIdn() 
    {
        return specificationNameIdn;
    }
    public void setSpecificationNameJp(String specificationNameJp) 
    {
        this.specificationNameJp = specificationNameJp;
    }

    public String getSpecificationNameJp() 
    {
        return specificationNameJp;
    }
    public void setSpecificationNameKo(String specificationNameKo) 
    {
        this.specificationNameKo = specificationNameKo;
    }

    public String getSpecificationNameKo() 
    {
        return specificationNameKo;
    }
    public void setSpecificationNameMy(String specificationNameMy) 
    {
        this.specificationNameMy = specificationNameMy;
    }

    public String getSpecificationNameMy() 
    {
        return specificationNameMy;
    }
    public void setSpecificationNameTh(String specificationNameTh) 
    {
        this.specificationNameTh = specificationNameTh;
    }

    public String getSpecificationNameTh() 
    {
        return specificationNameTh;
    }
    public void setSpecificationNameVi(String specificationNameVi) 
    {
        this.specificationNameVi = specificationNameVi;
    }

    public String getSpecificationNameVi() 
    {
        return specificationNameVi;
    }
    public void setSpecificationNamePt(String specificationNamePt) 
    {
        this.specificationNamePt = specificationNamePt;
    }

    public String getSpecificationNamePt() 
    {
        return specificationNamePt;
    }
    public void setSpecificationNameRus(String specificationNameRus) 
    {
        this.specificationNameRus = specificationNameRus;
    }

    public List<SpecificationValue> getSpecificationValues() {
        return specificationValues;
    }

    public void setSpecificationValues(List<SpecificationValue> specificationValues) {
        this.specificationValues = specificationValues;
    }

    public String getSpecificationNameRus()
    {
        return specificationNameRus;
    }
    public void setSpecificationNameBlr(String specificationNameBlr) 
    {
        this.specificationNameBlr = specificationNameBlr;
    }

    public String getSpecificationNameBlr() 
    {
        return specificationNameBlr;
    }
    public void setSpecificationNameIda(String specificationNameIda) 
    {
        this.specificationNameIda = specificationNameIda;
    }

    public String getSpecificationNameIda() 
    {
        return specificationNameIda;
    }
    public void setSpecificationNameSa(String specificationNameSa) 
    {
        this.specificationNameSa = specificationNameSa;
    }

    public String getSpecificationNameSa() 
    {
        return specificationNameSa;
    }
    public void setSpecificationNameAr(String specificationNameAr) 
    {
        this.specificationNameAr = specificationNameAr;
    }

    public String getSpecificationNameAr() 
    {
        return specificationNameAr;
    }
    public void setSpecificationNameIt(String specificationNameIt) 
    {
        this.specificationNameIt = specificationNameIt;
    }

    public String getSpecificationNameIt() 
    {
        return specificationNameIt;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("specificationName", getSpecificationName())
            .append("remark", getRemark())
            .append("specificationNameEn", getSpecificationNameEn())
            .append("specificationNameTc", getSpecificationNameTc())
            .append("specificationNameDe", getSpecificationNameDe())
            .append("specificationNameEs", getSpecificationNameEs())
            .append("specificationNameFr", getSpecificationNameFr())
            .append("specificationNameIdn", getSpecificationNameIdn())
            .append("specificationNameJp", getSpecificationNameJp())
            .append("specificationNameKo", getSpecificationNameKo())
            .append("specificationNameMy", getSpecificationNameMy())
            .append("specificationNameTh", getSpecificationNameTh())
            .append("specificationNameVi", getSpecificationNameVi())
            .append("specificationNamePt", getSpecificationNamePt())
            .append("specificationNameRus", getSpecificationNameRus())
            .append("specificationNameBlr", getSpecificationNameBlr())
            .append("specificationNameIda", getSpecificationNameIda())
            .append("specificationNameSa", getSpecificationNameSa())
            .append("specificationNameAr", getSpecificationNameAr())
            .append("specificationNameIt", getSpecificationNameIt())
            .toString();
    }
}
