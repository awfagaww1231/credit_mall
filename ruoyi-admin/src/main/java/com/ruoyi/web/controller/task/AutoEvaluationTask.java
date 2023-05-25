package com.ruoyi.web.controller.task;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.domain.ShopOrder;
import com.ruoyi.system.domain.UserEvaluation;
import com.ruoyi.system.mapper.ShopOrderMapper;
import com.ruoyi.system.mapper.UserEvaluationMapper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

//自动评价定时器
@Component
public class AutoEvaluationTask {

    @Resource
    private UserEvaluationMapper userEvaluationMapper;

    @Resource
    private ShopOrderMapper shopOrderMapper;


    //自动评价
    @Scheduled(cron = "0 0/1 * * * ?")
    @Transactional
    public void autoEvaluation() throws Exception {
        String content = "好评！";
        //获取七天后还未评论的订单
        List<ShopOrder> shopOrders = userEvaluationMapper.selectNotYetEvaluationShopOrder();
        //让这些订单自行评论
        for (int i = 0; i < shopOrders.size(); i++) {
            ShopOrder shopOrder = shopOrders.get(i);
            //插入评论
            UserEvaluation userEvaluation = new UserEvaluation();
            userEvaluation.setContent(content);
            userEvaluation.setScore(5);
            userEvaluation.setCreateTime(DateUtils.getNowDate());
            userEvaluation.setShopOrderId(shopOrder.getId());
            userEvaluation.setShopGoodsInfoId(shopOrder.getShopGoodsInfoId());
            userEvaluation.setUserId(shopOrder.getUserId());
            int count = userEvaluationMapper.insertUserEvaluation(userEvaluation);
            if (count <= 0){
                throw new Exception("商品订单自动评价异常，订单号："+shopOrder.getOrderCode());
            }
            //变更订单为已完成
            shopOrder.setStatus(4);
            int count2 = shopOrderMapper.updateShopOrder(shopOrder);
            if (count2 <= 0){
                throw new Exception("商品订单自动评价异常，订单号："+shopOrder.getOrderCode());
            }
        }
    }
}
