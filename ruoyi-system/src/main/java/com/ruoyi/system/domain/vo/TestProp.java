package com.ruoyi.system.domain.vo;

import org.apache.commons.beanutils.PropertyUtils;

public class TestProp {

    private static String no = "3";
    private String testName;
    private String testVal;
    private String testVal1;
    private String testVal2;
    private String testVal3;
    public TestProp(){

    }
    public TestProp(String testName, String testVal, String testVal1, String testVal2, String testVal3) {
        this.testName = testName;
        this.testVal = testVal;
        this.testVal1 = testVal1;
        this.testVal2 = testVal2;
        this.testVal3 = testVal3;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public String getTestVal() {
        try {
            String val = (String)PropertyUtils.getProperty(this, "testVal" + no);
            return  val;
        } catch (Exception e) {
        }
        return testVal;
    }

    public void setTestVal(String testVal) {
        this.testVal = testVal;
    }

    public String getTestVal1() {
        return testVal1;
    }

    public void setTestVal1(String testVal1) {
        this.testVal1 = testVal1;
    }

    public String getTestVal2() {
        return testVal2;
    }

    public void setTestVal2(String testVal2) {
        this.testVal2 = testVal2;
    }

    public String getTestVal3() {
        return testVal3;
    }

    public void setTestVal3(String testVal3) {
        this.testVal3 = testVal3;
    }

    public static void main(String[] args) {

       TestProp prop = new TestProp("TestName","TestVal","TestVal1","TestVal2",null);
        System.out.println(prop.getTestVal());
    }

}
