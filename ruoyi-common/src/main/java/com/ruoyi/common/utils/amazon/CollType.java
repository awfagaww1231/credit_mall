package com.ruoyi.common.utils.amazon;

public enum CollType {
    SINGLE("1","单品采集"),KEYWORD("2","关键词采集");

    private final String code;
    private final String desc;

    CollType(String code, String desc)
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
