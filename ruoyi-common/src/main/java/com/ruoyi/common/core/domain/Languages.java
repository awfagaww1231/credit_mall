package com.ruoyi.common.core.domain;

import com.alibaba.fastjson2.JSONObject;

public class Languages {

    private String zh;//中文
    private String en;//英文
    private String tc;//繁体
    private String de;//德国
    private String es;//西班牙
    private String fr;//法国
    private String idn;//印度尼西亚
    private String jp;//日本
    private String ko;//韩国
    private String my;//马来西亚
    private String th;//泰国
    private String vi;//越南
    private String pt;//葡萄牙
    private String rus;//俄语
    private String blr;//白俄罗斯
    private String ida;//印度
    private String sa;//沙特阿拉伯
    private String ar;//阿拉伯
    private String it;//意大利

    public String getZh() {
        return zh;
    }

    public void setZh(String zh) {
        this.zh = zh;
    }

    public String getEn() {
        return en;
    }

    public void setEn(String en) {
        this.en = en;
    }

    public String getTc() {
        return tc;
    }

    public void setTc(String tc) {
        this.tc = tc;
    }

    public String getDe() {
        return de;
    }

    public void setDe(String de) {
        this.de = de;
    }

    public String getEs() {
        return es;
    }

    public void setEs(String es) {
        this.es = es;
    }

    public String getFr() {
        return fr;
    }

    public void setFr(String fr) {
        this.fr = fr;
    }

    public String getIdn() {
        return idn;
    }

    public void setIdn(String idn) {
        this.idn = idn;
    }

    public String getJp() {
        return jp;
    }

    public void setJp(String jp) {
        this.jp = jp;
    }

    public String getKo() {
        return ko;
    }

    public void setKo(String ko) {
        this.ko = ko;
    }

    public String getMy() {
        return my;
    }

    public void setMy(String my) {
        this.my = my;
    }

    public String getTh() {
        return th;
    }

    public void setTh(String th) {
        this.th = th;
    }

    public String getVi() {
        return vi;
    }

    public void setVi(String vi) {
        this.vi = vi;
    }

    public String getPt() {
        return pt;
    }

    public void setPt(String pt) {
        this.pt = pt;
    }

    public String getRus() {
        return rus;
    }

    public void setRus(String rus) {
        this.rus = rus;
    }

    public String getBlr() {
        return blr;
    }

    public void setBlr(String blr) {
        this.blr = blr;
    }

    public String getIda() {
        return ida;
    }

    public void setIda(String ida) {
        this.ida = ida;
    }

    public String getSa() {
        return sa;
    }

    public void setSa(String sa) {
        this.sa = sa;
    }

    public String getAr() {
        return ar;
    }

    public void setAr(String ar) {
        this.ar = ar;
    }

    public String getIt() {
        return it;
    }

    public void setIt(String it) {
        this.it = it;
    }

    public static void main(String[] args) {
       String  ss =  "{\n" +
                "\t'en':'en',\n" +
                "\t'tc':null,\n" +
                "\t'de':null,\n" +
                "\t'es':null,\n" +
                "\t'fr':null,\n" +
                "\t'idn':null,\n" +
                "\t'jp':null,\t\n" +
                "\t'ko':null,\n" +
                "\t'my':null,\t\n" +
                "\t'th':null,\n" +
                "\t'vi':null,\n" +
                "\t'pt':null,\n" +
                "\t'rus':null,\t\n" +
                "\t'blr':null,\n" +
                "\t'ida':null,\n" +
                "\t'sa':null,\n" +
                "\t'ar':null,\t\n" +
                "\t'it':null\n" +
                "}";
        String  s2  = "{\"en\":\"en\"}";
        JSONObject jsonObject = JSONObject.parseObject(s2);
        Languages languages = JSONObject.parseObject(s2, Languages.class);
        System.out.println(languages.getDe());
    }
}
