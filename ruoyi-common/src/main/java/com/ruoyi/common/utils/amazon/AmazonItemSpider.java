package com.ruoyi.common.utils.amazon;

import cn.hutool.core.net.url.UrlBuilder;
import cn.hutool.core.net.url.UrlQuery;
import cn.hutool.json.JSONUtil;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.bean.BeanUtils;
import org.apache.commons.math3.random.RandomDataGenerator;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class AmazonItemSpider {
    private static final Logger log = LoggerFactory.getLogger(AmazonItemSpider.class);

    /* private final  static String[] ua = {"Mozilla/5.0 (Windows NT 6.1; WOW64; rv:46.0) Gecko/20100101 Firefox/46.0",
             "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.87 Safari/537.36 OPR/37.0.2178.32",
             "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/534.57.2 (KHTML, like Gecko) Version/5.1.7 Safari/534.57.2",
             "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.101 Safari/537.36",
             "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/46.0.2486.0 Safari/537.36 Edge/13.10586",
             "Mozilla/5.0 (Windows NT 10.0; WOW64; Trident/7.0; rv:11.0) like Gecko",

             "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/113.0.0.0 Safari/537.36",
             "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/47.0.2526.106 BIDUBrowser/8.3 Safari/537.36",
             "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/47.0.2526.80 Safari/537.36 Core/1.47.277.400 QQBrowser/9.4.7658.400",
             "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/48.0.2564.116 UBrowser/5.6.12150.8 Safari/537.36",
             "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/38.0.2125.122 Safari/537.36 SE 2.X MetaSr 1.0",
             "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/48.0.2564.116 Safari/537.36 TheWorld 7",
             "Mozilla/5.0 (Windows NT 6.1; W…) Gecko/20100101 Firefox/60.0"};*/
    private final  static String[] ua = {
            "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_3) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.61 Safari/537.36 115Browser/25.0.4.2",
            "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/101.0.4951.41 Safari/537.36 115Browser/25.0.0.3 615",
            "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/113.0.0.0 Safari/537.36",
            "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/109.0.0.0 Safari/537.36",
            "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/103.0.0.0 Safari/537.36 115Browser/25.0.0.3 615"
    };
    /* "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/109.0.0.0 Safari/537.36"
             "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/113.0.0.0 Safari/537.36",*/
    public static void main(String[] args) throws Exception {
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE, MMMM dd yyyy",Locale.ENGLISH);
        String date =  "Thu, Mar 16 2023";
        Date parseDate = dateFormat.parse(date);
        System.out.println(parseDate);
        String format = dateFormat.format(new Date());
        System.out.println(format);
        AmazonItemSpider amazonItemSpider = new AmazonItemSpider();
        ItemCollTask task = new ItemCollTask();
       /* task.setCollType(CollType.SINGLE.getCode());
        task.setCollUrl("https://www.amazon.com/dp/B08V83JZH4/?psc=1");
        ItemCollInfo info = amazonItemSpider.collItemDetailInfo(task);
        System.out.println(JSONUtil.toJsonStr(info));*//*
        task.setCollType(CollType.KEYWORD.getCode());
        task.setCollUrl("https://www.amazon.com/s?i=computers-intl-ship&page=2");*/

        task.setCollType(CollType.SINGLE.getCode());
        task.setCollUrl("https://www.amazon.com/dp/B09JFP3R1D/?psc=1");
        List<ItemCollInfo> itemCollInfos = amazonItemSpider.collItemInfo(task,1);
        if (itemCollInfos !=null){
            itemCollInfos.forEach((item)->{
                System.out.println(JSONUtil.toJsonStr(item));
            });
        }
    }

    /**
     * 发起采集商品信息
     * @param task
     * @param page
     * @return
     * @throws Exception
     */
    public List<ItemCollInfo> collItemInfo(ItemCollTask task,Integer page) throws Exception {
        List<ItemCollInfo> itemCollInfos = new ArrayList<>();
        collItemInfo(task,page,itemCollInfos);
        return itemCollInfos;
    }

    /**
     * 重新采集单个商品
     * @param info
     * @return
     * @throws Exception
     */
    public ItemCollInfo collItemDetailInfo(ItemCollInfo info) throws Exception{
        TimeUnit.SECONDS.sleep(getRandomWaitTime());
        Document detailDoc = getDocument(info.getItemUrl(),info.getLcMain());
        if (detailDoc != null) {
            ItemCollInfo itemCollInfo = parseItemInfo(detailDoc);
            if (itemCollInfo != null) {
                itemCollInfo.setSourceCountry(info.getSourceCountry());
                itemCollInfo.setSourcePlatform(info.getSourcePlatform());
                itemCollInfo.setImportState(ImportState.UM_IMPORT.getCode());
                itemCollInfo.setCollStatus(CollStatus.SUCCESS.getCode());
                itemCollInfo.setCollType(info.getCollType());
                itemCollInfo.setTaskId(info.getTaskId());
                itemCollInfo.setUpdateTime(DateUtils.getNowDate());
                //itemCollInfo.setUpdateBy(SecurityUtils.getUsername());
                return itemCollInfo;
            }
            info.setCollStatus(CollStatus.FAIL.getCode());
            info.setUpdateTime(DateUtils.getNowDate());
            //info.setUpdateBy(SecurityUtils.getUsername());
        }
        return info;
    }

    private void collItemInfo(ItemCollTask task,Integer page,List<ItemCollInfo> itemCollInfos) throws Exception {
        String collType = task.getCollType();
        String collUrl = task.getCollUrl();
        if (CollType.KEYWORD.getCode().equals(collType)){//单品采集
            String decodeUrl = URLDecoder.decode(collUrl, "utf-8");
            Document doc = getDocument(decodeUrl,task.getLcMain());
            if (doc == null){
                log.error("请求商品信息失败！");
                return;
            }
            Elements elements = doc.select(".s-result-list.s-search-results.sg-row > div");
            TimeUnit.SECONDS.sleep(getRandomWaitTime());
            if (elements == null || elements.size() < 10){
                log.error("找不到足够的商品信息！");
                return;
            }
            if (elements.size() > 1) {
                int collNum = 0;
                int errorNum = 0;
                for (int idx = 0; idx < elements.size(); idx++) {
                    Element element = elements.get(idx);
                    String dataAsin = element.attr("data-asin");
                    if (StringUtils.isNotEmpty(dataAsin)) {
                        ItemCollTask dataTask = new ItemCollTask();
                        BeanUtils.copyProperties(task,dataTask);
                        dataTask.setCollType(CollType.SINGLE.getCode());
//                        dataTask.setCollUrl("https://www.amazon.com/dp/" + dataAsin + "/?psc=1");
                        dataTask.setCollUrl("https://www.amazon.com/dp/" + dataAsin + "/?psc=1");
                        log.info("准备采集数据 {} ------source------" + idx + "---" + dataTask.getCollUrl());
                        ItemCollInfo itemCollInfo = this.collItemDetailInfo(dataTask);
                        if (itemCollInfo != null && itemCollInfo.getItemCode() != null){
                            errorNum = 0;
                            itemCollInfos.add(itemCollInfo);
                            collNum ++;
                            if (collNum >= task.getCollNum()){
                                return;
                            }
                        }else {
                            if (errorNum >= 5){
                                log.info("此分类收集数据连续失败超过5次，跳过此分类");
                                return;
                            }else {
                                errorNum++;
                                log.info("收集数据连续失败"+errorNum+"次");
                            }
                        }
                    }
                }
                //采集更多的商品信息
                if (task.getCollNum() > collNum){
                    String nextPageUrl = getNextPageUrl(decodeUrl);
                    Long num = task.getCollNum() - collNum;
                    task.setCollNum(num);
                    task.setCollUrl(nextPageUrl);
                    collItemInfo(task, page,itemCollInfos);
                }
            }
        }else {
            String[] arrCollUrl = collUrl.split("\n");
            for (String tempColUrl : arrCollUrl) {
                task.setCollUrl(tempColUrl);
                ItemCollInfo itemCollInfo = this.collItemDetailInfo(task);
                if (itemCollInfo != null && itemCollInfo.getItemCode() != null){
                    itemCollInfos.add(itemCollInfo);
                }
            }
        }
    }

    private Document getDocument(String url,String lcMain ) throws Exception {
        /*
        String website = "https://www.amazon.com";
        String purl = "https://www.amazon.com/s?k=Gaming+headsets+wireless&i=deals-intl-ship&crid=1I2RQK6VJ2LE3&sprefix=gaming+headsets+wireless%2Cdeals-intl-ship%2C271";
        String ip = "127.0.0.1";
        Integer port = 9910;
        */
        Random r = new Random();
        int i = r.nextInt(4);
        Document doc = null;
        int reConnNum = 0;
        String lcMainSS = lcMain.replaceAll("-", "_");
        while(reConnNum < 5){//失败重连
            try {
                doc = Jsoup.connect(url)
                        .timeout(20000)
                        //加拿大
//                        .cookie("ubid-main","135-5195084-2439529")
                        //英国
                        .cookie("ubid-main","135-5195084-2439529")
                        //.cookie("ubid-main","130-3432372-9606523")//jp
                        //.cookie("ubid-main","135-2608342-3549048")
                        .cookie("sp-cdn","L5Z9:GB")//sp-cdn="L5Z9:JP"
                        .cookie("lc-main",lcMainSS)
                        .proxy("127.0.0.1", 10900)
                        //.data(map)
                        .ignoreContentType(true)
                        .ignoreHttpErrors(true)
                        .userAgent(ua[i])
                        .header("referer", "wyyy.media.com")//这个来源记得换..
                        .header("Accept-Language",lcMain)//en-US
                        .post();
                reConnNum = 5;
            } catch (Exception e) {//网络连接异常 发起重连
                e.printStackTrace();
                log.error("网络连接失败，重新发起采集信息。"+e.getMessage());
                reConnNum++;
                TimeUnit.MILLISECONDS.sleep(1200);
                if (reConnNum == 5){
                    //throw new ServiceException("网络连接失败，已超过重新下连接次数"+e.getMessage());
                    log.error("网络请求失败，已超过重新连接次数"+e.getMessage());
                }
            }
        }
        return doc;
    }

    /**
     * 采集单个商品信息
     * @param task
     * @return
     * @throws Exception
     */
    private ItemCollInfo collItemDetailInfo(ItemCollTask task) throws Exception{
        ItemCollInfo info = null;
        TimeUnit.SECONDS.sleep(getRandomWaitTime());
        Document detailDoc = getDocument(task.getCollUrl(),task.getLcMain());
        info = parseItemInfo(detailDoc);
        if (info != null){
            info.setItemUrl(task.getCollUrl());
            info.setSourceCountry(task.getCountry());
            info.setSourcePlatform(task.getPlatform());
            info.setImportState(ImportState.UM_IMPORT.getCode());
            info.setCollType(task.getCollType());
            info.setTaskId(task.getTaskId());
            info.setCreateTime(DateUtils.getNowDate());
            info.setCreateBy(task.getCreateBy());
        }
        return info;
    }

    /**
     * 解析采集商品信息
     * @param detailDoc
     * @return
     */
    private ItemCollInfo parseItemInfo(Document detailDoc) {

        ItemCollInfo info = new ItemCollInfo();
        if (detailDoc == null){
            log.error("请求商品信息失败，跳过此商品！");
            return null;
        }
        //找不到商品
        Elements pageTitles = detailDoc.select("head > title");
        if (pageTitles != null && pageTitles.size()>0){
            String title = pageTitles.first().text();
            if ("Page Not Found".equals(title)){
                log.error("商品已下架，跳过此商品！");
                info.setMonitorFailReason("商品已下架!");
                info.setAvailableStock(AvailableStock.CURRENTLY_UNAVAILABLE.getCode());
                info.setStockNum(0l);
                return info;
            }
        }

        //outOfStock Currently unavailable.
        Elements outOfStock = detailDoc.select("#outOfStock");
        if (outOfStock != null && outOfStock.size()>0){
            log.error("商品已下架，跳过此商品！");
            info.setMonitorFailReason("商品已下架!");
            info.setAvailableStock(AvailableStock.CURRENTLY_UNAVAILABLE.getCode());
            info.setStockNum(0l);
            return info;
        }

        //获取商品编码 data-csa-c-asin
        Elements asins = detailDoc.select("#corePrice_feature_div");
        if (asins.size()==0){
            asins = detailDoc.select("#corePrice_desktop");
            if (asins.size()==0){
                log.error("获取商品信息失败，跳过此商品！");
                return null;
            }
        }

        for (Element element: asins ) {
            info.setItemCode(element.attr("data-csa-c-asin"));
            break;
        }
        if (StringUtils.isEmpty(info.getItemCode())){
            //获取商品编码 data-csa-c-asin
            asins = detailDoc.select("#title_feature_div");
            for (Element element: asins ) {
                info.setItemCode(element.attr("data-csa-c-asin"));
                break;
            }
        }
        if (StringUtils.isEmpty(info.getItemCode())){
            log.error("获取商品编码失败，跳过此商品！");
            return null;
        }
        //获取商品价格
        Elements prices = detailDoc.select("#corePrice_feature_div .a-offscreen");
        if (prices.size()==0){
            prices = detailDoc.select("#corePrice_desktop .a-offscreen");
            if (prices.size()==0){
                log.error("获取商品价格失败，跳过此商品！");
                return null;
            }
        }

        for (Element element: prices ) {
            info.setItemPrice(new BigDecimal(element.text().replaceAll("US","").substring(1).replaceAll(",","")));
            info.setPriceType("price");
            break;
        }

//        //需要额外运费的商品不采集
//        Elements primaryDelivery = detailDoc.select("#mir-layout-DELIVERY_BLOCK-slot-PRIMARY_DELIVERY_MESSAGE_LARGE > span");
//        Element primaryDeliveryEle = primaryDelivery.first();
//        String deliveryPrice = primaryDeliveryEle.attr("data-csa-c-delivery-price");
//        if (deliveryPrice != null && !"FREE".equals(deliveryPrice)){
//            log.error("商品运费需要额外费用，跳过此商品！");
//            return null;
//        }

        //获取商品名称
        Elements titles = detailDoc.select("#productTitle");
        for (Element element: titles ) {
            info.setItemName(element.text());
            break;
        }
        //获取商品主图
        Elements mainImages = detailDoc.select("#imageBlock .imgTagWrapper img");
        for (Element element: mainImages ) {
            String tagImage = element.attr("src");
            info.setMainImages(tagImage);
            break;
        }

        //获取商品副图
        Elements altImages = detailDoc.select("#altImages * img");
        String tagImage ="";
        for (Element element: altImages ) {
            String src = element.attr("src");
            if (src.indexOf(".gif") < 0){
                tagImage += src +"\n";
            }
        }
        if (StringUtils.isEmpty(tagImage)) {
            //获取商品副图
            altImages = detailDoc.select("#thumbImages * img");
            for (Element element : altImages) {
                String src = element.attr("src");
                if (src.indexOf(".gif") < 0){
                    tagImage += src +"\n";
                }
            }
        }
        info.setTagImages(ErpUtil.handleBigImages(tagImage));

//        //获取商品库存
//        /**
//         *  Only 10 left in stock - order soon
//         *  Temporarily out of stock
//         */
//        Elements inStocks = detailDoc.select("#availability span");
//        for (Element element: inStocks ) {
//            String availableStock = element.text();
//            log.error("获取商品库存数量！"+availableStock);
//            if ("In Stock".equalsIgnoreCase(availableStock)){
//                info.setStockNum(999L);
//                availableStock = AvailableStock.IN_STOCK.getCode();
//            }else if (availableStock.startsWith("Only")){
//                String[] arrNum = availableStock.split(" ");
//                info.setStockNum(Long.parseLong(arrNum[1]));
//                availableStock = AvailableStock.LOW_STOCK.getCode();
//            } else if ("Temporarily out of stock".equalsIgnoreCase(availableStock)) {
//                info.setStockNum(0L);
//                availableStock = AvailableStock.TEMPORARILY_OUT_OF_STOCK.getCode();
//            }else if ("Currently unavailable.".equalsIgnoreCase(availableStock)) {
//                info.setStockNum(0L);
//                availableStock = AvailableStock.CURRENTLY_UNAVAILABLE.getCode();
//            }else {
//                log.error("商品库存无法确定，跳过此商品！");
//                return null;
//            }
//            info.setAvailableStock(availableStock);
//            break;
//        }

        //简要描述 feature-bullets
        Elements remarks = detailDoc.select("#feature-bullets .a-list-item");
        StringBuilder remarkBuilder = new StringBuilder();
        for (Element element: remarks) {
            remarkBuilder = remarkBuilder.append(element.text()).append("\n");
        }
        info.setItemFeature(remarkBuilder.toString());

        //详细描述
        Elements descriptions = detailDoc.select("#productDescription_feature_div #productDescription p> span");
        for (Element element: descriptions ) {
            info.setItemDescription(element.text());
            break;
        }

//        String year = DateUtils.dateTimeNow(DateUtils.YYYY);
//        //普通发货日期 mir-layout-DELIVERY_BLOCK-slot-PRIMARY_DELIVERY_MESSAGE_LARGE
//        Elements commDelivery = detailDoc.select("#mir-layout-DELIVERY_BLOCK-slot-PRIMARY_DELIVERY_MESSAGE_LARGE span> span");
//        for (Element element: commDelivery ) {//Wednesday, March 15
//            try{
//                String commDeliveryStr = element.text()+" "+year;
//                Date commDeliveryDate =  DateUtils.parseDate(commDeliveryStr,Locale.ENGLISH,"EEEE, MMMM dd yyyy");
//                info.setCommDeliveryDate(DateUtils.parseDateToStr("yyyy-MM-dd",commDeliveryDate));
//            }catch(Exception e){
//                log.error("普通发货日期获取失败！"+e.getMessage());
//            }
//            break;
//        }

//        //最快发货日期 mir-layout-DELIVERY_BLOCK-slot-SECONDARY_DELIVERY_MESSAGE_LARGE
//        Elements fastDelivery = detailDoc.select("#mir-layout-DELIVERY_BLOCK-slot-SECONDARY_DELIVERY_MESSAGE_LARGE > span > span.a-text-bold");
//
//        //Element element = fastDelivery.first();
//        for (Element element: fastDelivery ) {// Friday, March 10  Tomorrow, April 8
//            try{ String commDeliveryStr = element.text()+" "+year;
//                Date commDeliveryDate =  DateUtils.parseDate(commDeliveryStr,Locale.ENGLISH,"EEEE, MMMM dd yyyy");
//                info.setFastDeliverDate(DateUtils.parseDateToStr("yyyy-MM-dd",commDeliveryDate));
//            }catch(Exception e){
//                log.error("最快发货日期获取失败！"+e.getMessage());
//            }
//            break;
//        }
        //品牌 a-spacing-small po-brand
        Elements brands = detailDoc.select(".a-spacing-small .po-brand > td.a-span9 > span");
        for (Element element: brands ) {
            info.setItemBrand(element.text());
            break;
        }

        //获取重量 单位未pounds  #productDetails_detailBullets_sections1
        /*Elements productInformation = detailDoc.select("#productDetails_detailBullets_sections1 > tbody");
        if (productInformation != null){
            Element tbody = productInformation.first();
            if (tbody != null) {
                Elements rows = tbody.select("tr");
                for (Element row : rows) {
                    Element th = row.select("th").first();
                    Element td = row.select("td").first();
                    if (th != null && StringUtils.isNotEmpty(th.text())) {
                        if (StringUtils.equalsIgnoreCase("Item Weight", th.text().trim())) {
                            String itemWeight = td.text().replaceAll("\u200E","");//去除不可见字符
                            String[] arrWeight = itemWeight.split(" ");
                            String weightStr = arrWeight[0];
                            BigDecimal weight = new BigDecimal(weightStr);
                            String unit = arrWeight[1];
                            if (unit.equals("ounces")) {
                                weight = weight.divide(new BigDecimal("16")).setScale(4, RoundingMode.HALF_UP);
                            }
                            info.setShippingWeight(weight);
                            System.out.println(weight);
                            break;
                        }
                    }
                }
            }
        }*/
        //productDetails_techSpec_section_1
        /*if (info.getShippingWeight() == null){
            Elements productDetails_techSpec_section_1 = detailDoc.select("#productDetails_techSpec_section_1 > tbody");
            if (productDetails_techSpec_section_1 != null){
                Element tbody = productDetails_techSpec_section_1.first();
                if (tbody != null) {
                    Elements rows = tbody.select("tr");
                    for (Element row : rows) {
                        Element th = row.select("th").first();
                        Element td = row.select("td").first();
                        if (th != null && StringUtils.isNotEmpty(th.text())) {
                            if (StringUtils.equalsIgnoreCase("Item Weight", th.text().trim())) {
                                String itemWeight = td.text().replaceAll("\u200E","");
                                String[] arrWeight = itemWeight.split(" ");
                                String weightStr = arrWeight[0];
                                String unit = arrWeight[1];
                                System.out.println("weightStr"+weightStr);
                                BigDecimal weight = new BigDecimal(weightStr);
                                if (unit.equals("ounces")) {
                                    weight = weight.divide(new BigDecimal("16")).setScale(4, RoundingMode.HALF_UP);
                                }
                                info.setShippingWeight(weight);
                                System.out.println(weight);
                                break;
                            }
                        }
                    }
                }
            }
        }*/
        return info;
    }

    //生成随机等待时间。
    private long getRandomWaitTime(){
        RandomDataGenerator generator = new RandomDataGenerator();
        long waitTime = generator.nextLong(1, 5);
        return waitTime;

    }

    private int getUrlPageNum (String url){
        UrlBuilder urlBuilder = UrlBuilder.of(url, Charset.defaultCharset());
        Map<CharSequence, CharSequence> queryMap = urlBuilder.getQuery().getQueryMap();
        String page  = queryMap.get("page").toString();
        int pageNum = 1;
        try{
            pageNum = Integer.parseInt(page);
        }catch (Exception e){
        }
        return pageNum;
    }

    private String getNextPageUrl(String url){
        UrlBuilder urlBuilder = UrlBuilder.of(url, Charset.defaultCharset());
        Map<CharSequence, CharSequence> queryMap = urlBuilder.getQuery().getQueryMap();
        CharSequence sequencePage = queryMap.get("page");
        String page = "";
        Integer nextPage = 1;
        if (sequencePage != null){
            page  = sequencePage.toString();
            nextPage = Integer.parseInt(page)+1;
        }else {
            nextPage = 2;
        }
        Map<CharSequence, CharSequence> query = new  HashMap(queryMap);
        query.put("page",nextPage.toString());
        urlBuilder.setQuery(UrlQuery.of(query));
        return urlBuilder.toString();
    }




}
