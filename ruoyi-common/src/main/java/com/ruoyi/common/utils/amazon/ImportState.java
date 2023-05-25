package com.ruoyi.common.utils.amazon;

public enum ImportState {
        UM_IMPORT("0","未导入"),IMPORTED("1","已导入");

        private final String code;
        private final String desc;

        ImportState(String code, String desc)
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
