package com.ruoyi.web.controller.task;

import com.ruoyi.system.domain.UserLoanBill;
import com.ruoyi.system.domain.UserLoanRecord;
import com.ruoyi.system.mapper.UserLoanBillMapper;
import com.ruoyi.system.mapper.UserLoanRecordMapper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

//用户贷款定时器
@Component
public class UserLoanTask {


    @Resource
    private UserLoanBillMapper userLoanBillMapper;

    @Resource
    private UserLoanRecordMapper userLoanRecordMapper;


    //贷款每期bill账单生成(每月8号)
    @Scheduled(cron = "0 0 0 8 * ? ")
    void periodicOrderGeneration(){
        //生成新的账单
        UserLoanRecord userLoanRecord = new UserLoanRecord();
        userLoanRecord.setStatus(1);
        List<UserLoanRecord> userLoanRecords = userLoanRecordMapper.selectUserLoanRecordList(userLoanRecord);
        for (int i = 0; i < userLoanRecords.size(); i++) {
            UserLoanRecord userLoanRecordVo = userLoanRecords.get(i);

            //查看这个记录的已出账单
            UserLoanBill userLoanBillVo = new UserLoanBill();
            userLoanBillVo.setUserLoanRecordId(userLoanRecordVo.getId());
            List<UserLoanBill> userLoanBillsList = userLoanBillMapper.selectUserLoanBillList(userLoanBillVo);
            //已经生成账单数量
            int size = userLoanBillsList.size();
            //需要还款期数
            Integer repaymentPeriodNum = userLoanRecordVo.getRepaymentPeriodNum();
            if (size >=  repaymentPeriodNum){
                //如果已生成账单数量已经达到需要还款期数，则跳过
                continue;
            }
            //如果当月账单已经生成过，也跳过
            userLoanBillVo.setBillTime(new Date());
            userLoanBillsList = userLoanBillMapper.selectUserLoanBillList(userLoanBillVo);
            if (userLoanBillsList.size() >= 0){
                continue;
            }

            //生成本月账单
            UserLoanBill userLoanBillNew = new UserLoanBill();
            userLoanBillNew.setUserId(userLoanRecordVo.getUserId());
            userLoanBillNew.setRepaymentTotalAmountEveryPeriod(userLoanRecordVo.getRepaymentTotalAmountEveryPeriod());
            userLoanBillNew.setRepaidAmountThisPeriod(BigDecimal.ZERO);
            userLoanBillNew.setRepaymentStatus(0);
            userLoanBillNew.setUserLoanRecordId(userLoanRecordVo.getId());
            userLoanBillNew.setBillTime(new Date());
            userLoanBillMapper.insertUserLoanBill(userLoanBillNew);
        }
    }

    //贷款每期bill逾期定时器(每月8号)
    @Scheduled(cron = "0 0 0 8 * ? ")
    void periodicOrderOverdueTask(){
        //获取待结清的bill变更状态为逾期
        UserLoanBill userLoanBill = new UserLoanBill();
        userLoanBill.setRepaymentStatus(0);
        List<UserLoanBill> userLoanBills = userLoanBillMapper.selectUserLoanBillList(userLoanBill);
        for (int i = 0; i < userLoanBills.size(); i++) {
            UserLoanBill userLoanBillVo = new UserLoanBill();
            userLoanBillVo.setId(userLoanBills.get(i).getId());
            //设置状态为逾期
            userLoanBillVo.setRepaymentStatus(2);
            userLoanBillVo.setVersion(userLoanBills.get(i).getVersion());
            userLoanBillMapper.updateUserLoanBill(userLoanBillVo);
        }
    }

}
