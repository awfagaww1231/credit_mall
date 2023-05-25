package com.ruoyi.common.utils.amazon;

public enum AvailableStock {
    IN_STOCK("In Stock","现在有货"),
    LOW_STOCK("Low Stock","少量库存"),
    TEMPORARILY_OUT_OF_STOCK ("Temporarily out of stock.","暂时缺货"),
    CURRENTLY_UNAVAILABLE("Currently unavailable.","目前无货");

    private final String code;
    private final String desc;

    AvailableStock(String code, String desc)
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
