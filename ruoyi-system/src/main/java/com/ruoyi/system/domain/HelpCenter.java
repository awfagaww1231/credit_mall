package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 帮助中心对象 help_center
 * 
 * @author ruoyi
 * @date 2023-04-19
 */
public class HelpCenter extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 问题 */
    @Excel(name = "问题")
    private String question;

    /** 答案 */
    @Excel(name = "答案")
    private String answer;

    /** 英文-问题 */
    @Excel(name = "英文-问题")
    private String questionEn;

    /** 繁体-问题 */
    @Excel(name = "繁体-问题")
    private String questionTc;

    /** 德国-问题 */
    @Excel(name = "德国-问题")
    private String questionDe;

    /** 西班牙-问题 */
    @Excel(name = "西班牙-问题")
    private String questionEs;

    /** 法国-问题 */
    @Excel(name = "法国-问题")
    private String questionFr;

    /** 印度尼西亚-问题 */
    @Excel(name = "印度尼西亚-问题")
    private String questionIdn;

    /** 日本-问题 */
    @Excel(name = "日本-问题")
    private String questionJp;

    /** 韩国-问题 */
    @Excel(name = "韩国-问题")
    private String questionKo;

    /** 马来西亚-问题 */
    @Excel(name = "马来西亚-问题")
    private String questionMy;

    /** 泰国-问题 */
    @Excel(name = "泰国-问题")
    private String questionTh;

    /** 越南-问题 */
    @Excel(name = "越南-问题")
    private String questionVi;

    /** 葡萄牙-问题 */
    @Excel(name = "葡萄牙-问题")
    private String questionPt;

    /** 俄语-问题 */
    @Excel(name = "俄语-问题")
    private String questionRus;

    /** 白俄罗斯-问题 */
    @Excel(name = "白俄罗斯-问题")
    private String questionBlr;

    /** 印度-问题 */
    @Excel(name = "印度-问题")
    private String questionIda;

    /** 沙特阿拉伯-问题 */
    @Excel(name = "沙特阿拉伯-问题")
    private String questionSa;

    /** 阿拉伯-问题 */
    @Excel(name = "阿拉伯-问题")
    private String questionAr;

    /** 意大利-问题 */
    @Excel(name = "意大利-问题")
    private String questionIt;

    /** 英文-答案 */
    @Excel(name = "英文-答案")
    private String answerEn;

    /** 繁体-答案 */
    @Excel(name = "繁体-答案")
    private String answerTc;

    /** 德国-答案 */
    @Excel(name = "德国-答案")
    private String answerDe;

    /** 西班牙-答案 */
    @Excel(name = "西班牙-答案")
    private String answerEs;

    /** 法国-答案 */
    @Excel(name = "法国-答案")
    private String answerFr;

    /** 印度尼西亚-答案 */
    @Excel(name = "印度尼西亚-答案")
    private String answerIdn;

    /** 日本-答案 */
    @Excel(name = "日本-答案")
    private String answerJp;

    /** 韩国-答案 */
    @Excel(name = "韩国-答案")
    private String answerKo;

    /** 马来西亚-答案 */
    @Excel(name = "马来西亚-答案")
    private String answerMy;

    /** 泰国-答案 */
    @Excel(name = "泰国-答案")
    private String answerTh;

    /** 越南-答案 */
    @Excel(name = "越南-答案")
    private String answerVi;

    /** 葡萄牙-答案 */
    @Excel(name = "葡萄牙-答案")
    private String answerPt;

    /** 俄语-答案 */
    @Excel(name = "俄语-答案")
    private String answerRus;

    /** 白俄罗斯-答案 */
    @Excel(name = "白俄罗斯-答案")
    private String answerBlr;

    /** 印度-答案 */
    @Excel(name = "印度-答案")
    private String answerIda;

    /** 沙特阿拉伯-答案 */
    @Excel(name = "沙特阿拉伯-答案")
    private String answerSa;

    /** 阿拉伯-答案 */
    @Excel(name = "阿拉伯-答案")
    private String answerAr;

    /** 意大利-答案 */
    @Excel(name = "意大利-答案")
    private String answerIt;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setQuestion(String question) 
    {
        this.question = question;
    }

    public String getQuestion() 
    {
        return question;
    }
    public void setAnswer(String answer) 
    {
        this.answer = answer;
    }

    public String getAnswer() 
    {
        return answer;
    }
    public void setQuestionEn(String questionEn) 
    {
        this.questionEn = questionEn;
    }

    public String getQuestionEn() 
    {
        return questionEn;
    }
    public void setQuestionTc(String questionTc) 
    {
        this.questionTc = questionTc;
    }

    public String getQuestionTc() 
    {
        return questionTc;
    }
    public void setQuestionDe(String questionDe) 
    {
        this.questionDe = questionDe;
    }

    public String getQuestionDe() 
    {
        return questionDe;
    }
    public void setQuestionEs(String questionEs) 
    {
        this.questionEs = questionEs;
    }

    public String getQuestionEs() 
    {
        return questionEs;
    }
    public void setQuestionFr(String questionFr) 
    {
        this.questionFr = questionFr;
    }

    public String getQuestionFr() 
    {
        return questionFr;
    }
    public void setQuestionIdn(String questionIdn) 
    {
        this.questionIdn = questionIdn;
    }

    public String getQuestionIdn() 
    {
        return questionIdn;
    }
    public void setQuestionJp(String questionJp) 
    {
        this.questionJp = questionJp;
    }

    public String getQuestionJp() 
    {
        return questionJp;
    }
    public void setQuestionKo(String questionKo) 
    {
        this.questionKo = questionKo;
    }

    public String getQuestionKo() 
    {
        return questionKo;
    }
    public void setQuestionMy(String questionMy) 
    {
        this.questionMy = questionMy;
    }

    public String getQuestionMy() 
    {
        return questionMy;
    }
    public void setQuestionTh(String questionTh) 
    {
        this.questionTh = questionTh;
    }

    public String getQuestionTh() 
    {
        return questionTh;
    }
    public void setQuestionVi(String questionVi) 
    {
        this.questionVi = questionVi;
    }

    public String getQuestionVi() 
    {
        return questionVi;
    }
    public void setQuestionPt(String questionPt) 
    {
        this.questionPt = questionPt;
    }

    public String getQuestionPt() 
    {
        return questionPt;
    }
    public void setQuestionRus(String questionRus) 
    {
        this.questionRus = questionRus;
    }

    public String getQuestionRus() 
    {
        return questionRus;
    }
    public void setQuestionBlr(String questionBlr) 
    {
        this.questionBlr = questionBlr;
    }

    public String getQuestionBlr() 
    {
        return questionBlr;
    }
    public void setQuestionIda(String questionIda) 
    {
        this.questionIda = questionIda;
    }

    public String getQuestionIda() 
    {
        return questionIda;
    }
    public void setQuestionSa(String questionSa) 
    {
        this.questionSa = questionSa;
    }

    public String getQuestionSa() 
    {
        return questionSa;
    }
    public void setQuestionAr(String questionAr) 
    {
        this.questionAr = questionAr;
    }

    public String getQuestionAr() 
    {
        return questionAr;
    }
    public void setQuestionIt(String questionIt) 
    {
        this.questionIt = questionIt;
    }

    public String getQuestionIt() 
    {
        return questionIt;
    }
    public void setAnswerEn(String answerEn) 
    {
        this.answerEn = answerEn;
    }

    public String getAnswerEn() 
    {
        return answerEn;
    }
    public void setAnswerTc(String answerTc) 
    {
        this.answerTc = answerTc;
    }

    public String getAnswerTc() 
    {
        return answerTc;
    }
    public void setAnswerDe(String answerDe) 
    {
        this.answerDe = answerDe;
    }

    public String getAnswerDe() 
    {
        return answerDe;
    }
    public void setAnswerEs(String answerEs) 
    {
        this.answerEs = answerEs;
    }

    public String getAnswerEs() 
    {
        return answerEs;
    }
    public void setAnswerFr(String answerFr) 
    {
        this.answerFr = answerFr;
    }

    public String getAnswerFr() 
    {
        return answerFr;
    }
    public void setAnswerIdn(String answerIdn) 
    {
        this.answerIdn = answerIdn;
    }

    public String getAnswerIdn() 
    {
        return answerIdn;
    }
    public void setAnswerJp(String answerJp) 
    {
        this.answerJp = answerJp;
    }

    public String getAnswerJp() 
    {
        return answerJp;
    }
    public void setAnswerKo(String answerKo) 
    {
        this.answerKo = answerKo;
    }

    public String getAnswerKo() 
    {
        return answerKo;
    }
    public void setAnswerMy(String answerMy) 
    {
        this.answerMy = answerMy;
    }

    public String getAnswerMy() 
    {
        return answerMy;
    }
    public void setAnswerTh(String answerTh) 
    {
        this.answerTh = answerTh;
    }

    public String getAnswerTh() 
    {
        return answerTh;
    }
    public void setAnswerVi(String answerVi) 
    {
        this.answerVi = answerVi;
    }

    public String getAnswerVi() 
    {
        return answerVi;
    }
    public void setAnswerPt(String answerPt) 
    {
        this.answerPt = answerPt;
    }

    public String getAnswerPt() 
    {
        return answerPt;
    }
    public void setAnswerRus(String answerRus) 
    {
        this.answerRus = answerRus;
    }

    public String getAnswerRus() 
    {
        return answerRus;
    }
    public void setAnswerBlr(String answerBlr) 
    {
        this.answerBlr = answerBlr;
    }

    public String getAnswerBlr() 
    {
        return answerBlr;
    }
    public void setAnswerIda(String answerIda) 
    {
        this.answerIda = answerIda;
    }

    public String getAnswerIda() 
    {
        return answerIda;
    }
    public void setAnswerSa(String answerSa) 
    {
        this.answerSa = answerSa;
    }

    public String getAnswerSa() 
    {
        return answerSa;
    }
    public void setAnswerAr(String answerAr) 
    {
        this.answerAr = answerAr;
    }

    public String getAnswerAr() 
    {
        return answerAr;
    }
    public void setAnswerIt(String answerIt) 
    {
        this.answerIt = answerIt;
    }

    public String getAnswerIt() 
    {
        return answerIt;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("question", getQuestion())
            .append("answer", getAnswer())
            .append("questionEn", getQuestionEn())
            .append("questionTc", getQuestionTc())
            .append("questionDe", getQuestionDe())
            .append("questionEs", getQuestionEs())
            .append("questionFr", getQuestionFr())
            .append("questionIdn", getQuestionIdn())
            .append("questionJp", getQuestionJp())
            .append("questionKo", getQuestionKo())
            .append("questionMy", getQuestionMy())
            .append("questionTh", getQuestionTh())
            .append("questionVi", getQuestionVi())
            .append("questionPt", getQuestionPt())
            .append("questionRus", getQuestionRus())
            .append("questionBlr", getQuestionBlr())
            .append("questionIda", getQuestionIda())
            .append("questionSa", getQuestionSa())
            .append("questionAr", getQuestionAr())
            .append("questionIt", getQuestionIt())
            .append("answerEn", getAnswerEn())
            .append("answerTc", getAnswerTc())
            .append("answerDe", getAnswerDe())
            .append("answerEs", getAnswerEs())
            .append("answerFr", getAnswerFr())
            .append("answerIdn", getAnswerIdn())
            .append("answerJp", getAnswerJp())
            .append("answerKo", getAnswerKo())
            .append("answerMy", getAnswerMy())
            .append("answerTh", getAnswerTh())
            .append("answerVi", getAnswerVi())
            .append("answerPt", getAnswerPt())
            .append("answerRus", getAnswerRus())
            .append("answerBlr", getAnswerBlr())
            .append("answerIda", getAnswerIda())
            .append("answerSa", getAnswerSa())
            .append("answerAr", getAnswerAr())
            .append("answerIt", getAnswerIt())
            .toString();
    }
}
