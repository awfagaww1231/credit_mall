package com.ruoyi.system.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 商品规格值对象 specification_value
 * 
 * @author ruoyi
 * @date 2023-04-23
 */
public class SpecificationValue extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 对应的规格id */
    @Excel(name = "对应的规格id")
    private Long goodsSpecificationId;

    /** 规格值 */
    @Excel(name = "规格值")
    private String specificationValue;

    /** 英文-规格名称 */
    @Excel(name = "英文-规格名称")
    private String specificationValueEn;

    /** 繁体-规格名称 */
    @Excel(name = "繁体-规格名称")
    private String specificationValueTc;

    /** 德国-规格名称 */
    @Excel(name = "德国-规格名称")
    private String specificationValueDe;

    /** 西班牙-规格名称 */
    @Excel(name = "西班牙-规格名称")
    private String specificationValueEs;

    /** 法国-规格名称 */
    @Excel(name = "法国-规格名称")
    private String specificationValueFr;

    /** 印度尼西亚-规格名称 */
    @Excel(name = "印度尼西亚-规格名称")
    private String specificationValueIdn;

    /** 日本-规格名称 */
    @Excel(name = "日本-规格名称")
    private String specificationValueJp;

    /** 韩国-规格名称 */
    @Excel(name = "韩国-规格名称")
    private String specificationValueKo;

    /** 马来西亚-规格名称 */
    @Excel(name = "马来西亚-规格名称")
    private String specificationValueMy;

    /** 泰国-规格名称 */
    @Excel(name = "泰国-规格名称")
    private String specificationValueTh;

    /** 越南-规格名称 */
    @Excel(name = "越南-规格名称")
    private String specificationValueVi;

    /** 葡萄牙-规格名称 */
    @Excel(name = "葡萄牙-规格名称")
    private String specificationValuePt;

    /** 俄语-规格名称 */
    @Excel(name = "俄语-规格名称")
    private String specificationValueRus;

    /** 白俄罗斯-规格名称 */
    @Excel(name = "白俄罗斯-规格名称")
    private String specificationValueBlr;

    /** 印度-规格名称 */
    @Excel(name = "印度-规格名称")
    private String specificationValueIda;

    /** 沙特阿拉伯-规格名称 */
    @Excel(name = "沙特阿拉伯-规格名称")
    private String specificationValueSa;

    /** 阿拉伯-规格名称 */
    @Excel(name = "阿拉伯-规格名称")
    private String specificationValueAr;

    /** 意大利-规格名称 */
    @Excel(name = "意大利-规格名称")
    private String specificationValueIt;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setGoodsSpecificationId(Long goodsSpecificationId) 
    {
        this.goodsSpecificationId = goodsSpecificationId;
    }

    public Long getGoodsSpecificationId() 
    {
        return goodsSpecificationId;
    }
    public void setSpecificationValue(String specificationValue) 
    {
        this.specificationValue = specificationValue;
    }

    public String getSpecificationValue() 
    {
        return specificationValue;
    }
    public void setSpecificationValueEn(String specificationValueEn) 
    {
        this.specificationValueEn = specificationValueEn;
    }

    public String getSpecificationValueEn() 
    {
        return specificationValueEn;
    }
    public void setSpecificationValueTc(String specificationValueTc) 
    {
        this.specificationValueTc = specificationValueTc;
    }

    public String getSpecificationValueTc() 
    {
        return specificationValueTc;
    }
    public void setSpecificationValueDe(String specificationValueDe) 
    {
        this.specificationValueDe = specificationValueDe;
    }

    public String getSpecificationValueDe() 
    {
        return specificationValueDe;
    }
    public void setSpecificationValueEs(String specificationValueEs) 
    {
        this.specificationValueEs = specificationValueEs;
    }

    public String getSpecificationValueEs() 
    {
        return specificationValueEs;
    }
    public void setSpecificationValueFr(String specificationValueFr) 
    {
        this.specificationValueFr = specificationValueFr;
    }

    public String getSpecificationValueFr() 
    {
        return specificationValueFr;
    }
    public void setSpecificationValueIdn(String specificationValueIdn) 
    {
        this.specificationValueIdn = specificationValueIdn;
    }

    public String getSpecificationValueIdn() 
    {
        return specificationValueIdn;
    }
    public void setSpecificationValueJp(String specificationValueJp) 
    {
        this.specificationValueJp = specificationValueJp;
    }

    public String getSpecificationValueJp() 
    {
        return specificationValueJp;
    }
    public void setSpecificationValueKo(String specificationValueKo) 
    {
        this.specificationValueKo = specificationValueKo;
    }

    public String getSpecificationValueKo() 
    {
        return specificationValueKo;
    }
    public void setSpecificationValueMy(String specificationValueMy) 
    {
        this.specificationValueMy = specificationValueMy;
    }

    public String getSpecificationValueMy() 
    {
        return specificationValueMy;
    }
    public void setSpecificationValueTh(String specificationValueTh) 
    {
        this.specificationValueTh = specificationValueTh;
    }

    public String getSpecificationValueTh() 
    {
        return specificationValueTh;
    }
    public void setSpecificationValueVi(String specificationValueVi) 
    {
        this.specificationValueVi = specificationValueVi;
    }

    public String getSpecificationValueVi() 
    {
        return specificationValueVi;
    }
    public void setSpecificationValuePt(String specificationValuePt) 
    {
        this.specificationValuePt = specificationValuePt;
    }

    public String getSpecificationValuePt() 
    {
        return specificationValuePt;
    }
    public void setSpecificationValueRus(String specificationValueRus) 
    {
        this.specificationValueRus = specificationValueRus;
    }

    public String getSpecificationValueRus() 
    {
        return specificationValueRus;
    }
    public void setSpecificationValueBlr(String specificationValueBlr) 
    {
        this.specificationValueBlr = specificationValueBlr;
    }

    public String getSpecificationValueBlr() 
    {
        return specificationValueBlr;
    }
    public void setSpecificationValueIda(String specificationValueIda) 
    {
        this.specificationValueIda = specificationValueIda;
    }

    public String getSpecificationValueIda() 
    {
        return specificationValueIda;
    }
    public void setSpecificationValueSa(String specificationValueSa) 
    {
        this.specificationValueSa = specificationValueSa;
    }

    public String getSpecificationValueSa() 
    {
        return specificationValueSa;
    }
    public void setSpecificationValueAr(String specificationValueAr) 
    {
        this.specificationValueAr = specificationValueAr;
    }

    public String getSpecificationValueAr() 
    {
        return specificationValueAr;
    }
    public void setSpecificationValueIt(String specificationValueIt) 
    {
        this.specificationValueIt = specificationValueIt;
    }

    public String getSpecificationValueIt() 
    {
        return specificationValueIt;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("goodsSpecificationId", getGoodsSpecificationId())
            .append("specificationValue", getSpecificationValue())
            .append("specificationValueEn", getSpecificationValueEn())
            .append("specificationValueTc", getSpecificationValueTc())
            .append("specificationValueDe", getSpecificationValueDe())
            .append("specificationValueEs", getSpecificationValueEs())
            .append("specificationValueFr", getSpecificationValueFr())
            .append("specificationValueIdn", getSpecificationValueIdn())
            .append("specificationValueJp", getSpecificationValueJp())
            .append("specificationValueKo", getSpecificationValueKo())
            .append("specificationValueMy", getSpecificationValueMy())
            .append("specificationValueTh", getSpecificationValueTh())
            .append("specificationValueVi", getSpecificationValueVi())
            .append("specificationValuePt", getSpecificationValuePt())
            .append("specificationValueRus", getSpecificationValueRus())
            .append("specificationValueBlr", getSpecificationValueBlr())
            .append("specificationValueIda", getSpecificationValueIda())
            .append("specificationValueSa", getSpecificationValueSa())
            .append("specificationValueAr", getSpecificationValueAr())
            .append("specificationValueIt", getSpecificationValueIt())
            .toString();
    }
}
