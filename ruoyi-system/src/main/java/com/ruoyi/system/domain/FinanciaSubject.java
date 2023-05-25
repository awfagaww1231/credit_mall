package com.ruoyi.system.domain;

import java.io.Serializable;

public class FinanciaSubject implements Serializable {

    //科目级别
   private Long financiaLever;

   //科目编码
   private Long financiaCode;

   //科目名称
    private String financiaName;

    //父科目编码
    private Long parentFinanciaCode;

    //科目性质(支出/收入)
    private int orderType;

    public Long getFinanciaLever() {
        return financiaLever;
    }

    public void setFinanciaLever(Long financiaLever) {
        this.financiaLever = financiaLever;
    }

    public Long getFinanciaCode() {
        return financiaCode;
    }

    public void setFinanciaCode(Long financiaCode) {
        this.financiaCode = financiaCode;
    }

    public String getFinanciaName() {
        return financiaName;
    }

    public void setFinanciaName(String financiaName) {
        this.financiaName = financiaName;
    }

    public Long getParentFinanciaCode() {
        return parentFinanciaCode;
    }

    public void setParentFinanciaCode(Long parentFinanciaCode) {
        this.parentFinanciaCode = parentFinanciaCode;
    }

    public int getOrderType() {
        return orderType;
    }

    public void setOrderType(int orderType) {
        this.orderType = orderType;
    }
}
