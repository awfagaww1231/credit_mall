package com.ruoyi.common.utils.amazon;

public enum LanguageMaps {
    ZH("1","zh-CN"),
    EN("2","en-US"),
    TC("3","zh-TW"),
    JP("4","采集成功"),
    DE("5","de-DE"),
    FR("6","采集中"),
    TH("7","采集成功"),
    IDA("8","待采集"),
    my("9","采集中"),
    rus("10","采集成功"),
    vi("11","待采集"),
    es("12","采集中"),
    pt("13","采集成功");

    private final String code;
    private final String desc;

    LanguageMaps(String code, String desc)
    {
        this.code = code;
        this.desc = desc;
    }

    public String getCode()
    {
        return code;
    }

    public String getDesc()
    {
        return desc;
    }
}
