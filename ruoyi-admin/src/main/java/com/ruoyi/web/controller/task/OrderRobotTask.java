package com.ruoyi.web.controller.task;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.ShopGoodsInfo;
import com.ruoyi.system.domain.ShopOrder;
import com.ruoyi.system.domain.UserInfo;
import com.ruoyi.system.mapper.ShopGoodsInfoMapper;
import com.ruoyi.system.mapper.ShopOrderMapper;
import com.ruoyi.system.mapper.UserInfoMapper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 下单机器人定时任务
 *
 * @author ruoyi
 * @date 2022-11-14
 */
@Component
public class OrderRobotTask {

    @Resource
    private UserInfoMapper userInfoMapper;

    @Resource
    private ShopGoodsInfoMapper shopGoodsInfoMapper;

    @Resource
    private ShopOrderMapper shopOrderMapper;

    //执行下单任务
    @Scheduled(cron = "0 0/3 * * * ?")
    void task(){
        //多少时间后开启线程
        long time = 0;
        //获取随机时间
        Random random = new Random();
        time = random.nextInt(1000*60*2)+0;

        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.schedule(()->{
            try {
                doTask();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        },time, TimeUnit.SECONDS);
        executorService.shutdown();
    }

    void doTask() throws Exception {
        //随机获取一个机器人
        UserInfo robot = userInfoMapper.selectRobotInfoRandom();
        //随机获取一个在售商品
        ShopGoodsInfo shopGoodsInfo = shopGoodsInfoMapper.selectShopGoodsInfoRandom();
        if (StringUtils.isNull(robot) | StringUtils.isNull(shopGoodsInfo)){
            return;
        }
        ShopOrder shopOrder = new ShopOrder();
        String orderCode =  String.valueOf(System.currentTimeMillis()) + String.valueOf(robot.getId());
        shopOrder.setOrderCode(orderCode);
        shopOrder.setGoodsId(shopGoodsInfo.getGoodsId());
        shopOrder.setQuantity(1);
        shopOrder.setSinglePrice(shopGoodsInfo.getSinglePrice());
        shopOrder.setTotalPrice(shopGoodsInfo.getSinglePrice());
        shopOrder.setCreateTime(DateUtils.getNowDate());
        shopOrder.setPayTime(new Date(DateUtils.getNowDate().getTime() + 1000));
        String receiveInfo = robot.getUserName() + "/" + robot.getMobile() + "/美国洛杉矶/美国洛杉矶";
        shopOrder.setReceiveInfo(receiveInfo);
        shopOrder.setStatus(1);
        shopOrder.setShopId(shopGoodsInfo.getShopId());
        shopOrder.setUserId(robot.getId());
        shopOrder.setShopGoodsInfoId(shopGoodsInfo.getId());
        int count = shopOrderMapper.insertShopOrder(shopOrder);
        if(count <= 0){
            throw new Exception("机器人下单异常");
        }

    }
}
