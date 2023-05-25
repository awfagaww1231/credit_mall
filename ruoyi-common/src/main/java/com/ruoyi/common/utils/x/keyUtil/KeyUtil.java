package com.ruoyi.common.utils.x.keyUtil;

import java.util.Random;

public class KeyUtil {

    //生成订单号
    public static String getOrderCode(){
        return "";
    }

    //生成推荐码
    //length 生成的长度
    public static String getInviteCode(Integer length){
        //推荐码生成
        String base = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        char[] inviteCodeChars = new char[length];
        for (int i = 0; i < length; i++) {
            inviteCodeChars[i] = base.charAt(random.nextInt(base.length()));
        }
        return String.valueOf(inviteCodeChars);
    }
}
