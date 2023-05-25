package com.ruoyi.system.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 多语言配置包对象 lang_mgr
 * 
 * @author ruoyi
 * @date 2023-03-22
 */
public class LangMgr extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** key */
    @Excel(name = "key")
    private String langKey;

    /** 简体中文 */
    @Excel(name = "简体中文")
    private String zh;

    /** 英文 */
    @Excel(name = "英文")
    private String en;

    /** 繁体 */
    @Excel(name = "繁体")
    private String tc;

    /** 德国 */
    @Excel(name = "德国")
    private String de;

    /** 西班牙 */
    @Excel(name = "西班牙")
    private String es;

    /** 法国 */
    @Excel(name = "法国")
    private String fr;

    /** 印度尼西亚 */
    @Excel(name = "印度尼西亚")
    private String idn;

    /** 日本 */
    @Excel(name = "日本")
    private String jp;

    /** 韩国 */
    @Excel(name = "韩国")
    private String ko;

    /** 马来西亚 */
    @Excel(name = "马来西亚")
    private String my;

    /** 泰国 */
    @Excel(name = "泰国")
    private String th;

    /** 越南 */
    @Excel(name = "越南")
    private String vi;

    /** 葡萄牙 */
    @Excel(name = "葡萄牙")
    private String pt;

    /** 俄语 */
    @Excel(name = "俄语")
    private String rus;

    /** 白俄罗斯 */
    @Excel(name = "白俄罗斯")
    private String blr;

    /** 印度 */
    @Excel(name = "印度")
    private String ida;

    /** 沙特阿拉伯 */
    @Excel(name = "沙特阿拉伯")
    private String sa;

    /** 阿拉伯 */
    @Excel(name = "阿拉伯")
    private String ar;

    /** 意大利 */
    @Excel(name = "意大利")
    private String it;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setLangKey(String langKey) 
    {
        this.langKey = langKey;
    }

    public String getLangKey() 
    {
        return langKey;
    }
    public void setZh(String zh) 
    {
        this.zh = zh;
    }

    public String getZh() 
    {
        return zh;
    }
    public void setEn(String en) 
    {
        this.en = en;
    }

    public String getEn() 
    {
        return en;
    }
    public void setTc(String tc) 
    {
        this.tc = tc;
    }

    public String getTc() 
    {
        return tc;
    }
    public void setDe(String de) 
    {
        this.de = de;
    }

    public String getDe() 
    {
        return de;
    }
    public void setEs(String es) 
    {
        this.es = es;
    }

    public String getEs() 
    {
        return es;
    }
    public void setFr(String fr) 
    {
        this.fr = fr;
    }

    public String getFr() 
    {
        return fr;
    }
    public void setIdn(String idn) 
    {
        this.idn = idn;
    }

    public String getIdn() 
    {
        return idn;
    }
    public void setJp(String jp) 
    {
        this.jp = jp;
    }

    public String getJp() 
    {
        return jp;
    }
    public void setKo(String ko) 
    {
        this.ko = ko;
    }

    public String getKo() 
    {
        return ko;
    }
    public void setMy(String my) 
    {
        this.my = my;
    }

    public String getMy() 
    {
        return my;
    }
    public void setTh(String th) 
    {
        this.th = th;
    }

    public String getTh() 
    {
        return th;
    }
    public void setVi(String vi) 
    {
        this.vi = vi;
    }

    public String getVi() 
    {
        return vi;
    }
    public void setPt(String pt) 
    {
        this.pt = pt;
    }

    public String getPt() 
    {
        return pt;
    }
    public void setRus(String rus) 
    {
        this.rus = rus;
    }

    public String getRus() 
    {
        return rus;
    }
    public void setBlr(String blr) 
    {
        this.blr = blr;
    }

    public String getBlr() 
    {
        return blr;
    }
    public void setIda(String ida) 
    {
        this.ida = ida;
    }

    public String getIda() 
    {
        return ida;
    }
    public void setSa(String sa) 
    {
        this.sa = sa;
    }

    public String getSa() 
    {
        return sa;
    }
    public void setAr(String ar) 
    {
        this.ar = ar;
    }

    public String getAr() 
    {
        return ar;
    }
    public void setIt(String it) 
    {
        this.it = it;
    }

    public String getIt() 
    {
        return it;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("langKey", getLangKey())
            .append("zh", getZh())
            .append("en", getEn())
            .append("tc", getTc())
            .append("de", getDe())
            .append("es", getEs())
            .append("fr", getFr())
            .append("idn", getIdn())
            .append("jp", getJp())
            .append("ko", getKo())
            .append("my", getMy())
            .append("th", getTh())
            .append("vi", getVi())
            .append("pt", getPt())
            .append("rus", getRus())
            .append("blr", getBlr())
            .append("ida", getIda())
            .append("sa", getSa())
            .append("ar", getAr())
            .append("it", getIt())
            .append("remark", getRemark())
            .toString();
    }
}
