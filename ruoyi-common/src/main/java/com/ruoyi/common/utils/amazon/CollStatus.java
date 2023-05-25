package com.ruoyi.common.utils.amazon;

public enum CollStatus {
    FAIL("0","采集失败"),WAIT("1","待采集"),GOING("2","采集中"),SUCCESS("3","采集成功");

    private final String code;
    private final String desc;

    CollStatus(String code, String desc)
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
