package com.ruoyi.common.utils.amazon;

import cn.hutool.core.lang.id.NanoId;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ErpUtil {
    private static final char[] ITEM_CODE_ALPHABET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

    public static int  upcRandom(int num) {
        int random = (int) Math.floor((Math.random()+Math.floor(Math.random()*9+1))*Math.pow(10,num-1));
        return  random;
    }
    public static String getUpc(){
        //1位旗码+5位厂商码+5位商品代码
        int qm = upcRandom(1);
        int sp = upcRandom(5);
        int sc = upcRandom(5);
        String ts = qm+""+sp+""+sc;

        int e = 0, n = 0;
        for (int o = 0;o < ts.length();o++) {
            int num = Integer.parseInt(ts.substring(o,o+1));
            if (o%2 == 0) {
                e += num;
            } else {
                n += num;
            }
        }
        //校验位
        int check = 0;
        for (int r = (e *= 3)+n;r++%10 != 0;) {
            check++;
        }
        String upc = ts+""+check;
        return  upc;
    }

    /**
     * NanoId
     * @return
     */
    public static  String getItemCode(){
        String itemCode = "YJ"+ NanoId.randomNanoId(null, ITEM_CODE_ALPHABET, 12);
        return itemCode;
    }

    public static  String getOrderCode(){
        String Nanoid = NanoId.randomNanoId(null, ITEM_CODE_ALPHABET, 12);
        String prefix = DateUtils.parseDateToStr("yyyyMMdd", new Date());

        return prefix+Nanoid;
    }


    public static void main(String[] args) {
        String content = " amazon,redisCache.getCacheObject(\"erp.LimitWord\");george mini Pen Camera,Card Writer floyd redisCache.Mini Pen Camera,Card WritergetCacheObject(\"erp.LimitWord\");";
        System.out.println("input "+content);
    }

    public static String handleBigImages(String tagImages) {
        String itemImages = null;
        if (StringUtils.isNotEmpty(tagImages)) {
            List<String> itemImageList = new ArrayList<>();
            String[] arrTagImage = tagImages.split("\n");
            for (String imageUrl : arrTagImage) {
                if (StringUtils.isNotEmpty(imageUrl)) {
                    int extendPos = imageUrl.lastIndexOf(".");
                    if (extendPos >= 0) {
                        String extendStr = imageUrl.substring(extendPos);
                        String tagImageUrl = imageUrl.substring(0, extendPos);
                        extendPos = tagImageUrl.lastIndexOf(".");
                        tagImageUrl = tagImageUrl.substring(0, extendPos);
                        if (tagImageUrl.indexOf(".com") >= 0) {
                            tagImageUrl = tagImageUrl + extendStr;
                            itemImageList.add(tagImageUrl);
                        } else {
                            itemImageList.add(imageUrl);
                        }
                    }
                }
            }
            itemImages = StringUtils.join(itemImageList, ",");
            if (StringUtils.isEmpty(itemImages)){
                itemImages = StringUtils.replaceIgnoreCase(tagImages,"\n",",");
            }
        }
        return itemImages;
    }

    

}
