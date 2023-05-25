package com.ruoyi.web.controller.app;

import com.ruoyi.common.constant.HttpStatus;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.customer.common.AppletTokenService;
import com.ruoyi.customer.domain.ApiUserBillDetails;
import com.ruoyi.customer.domain.AppletLoginUser;
import com.ruoyi.customer.service.IApiUserBillDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * C端用户App账单明细Controller +
 * 
 * @author ruoyi
 * @date 2022-11-10
 */
@RestController
@RequestMapping("/api/apiUserBillDetails")
public class ApiUserBillDetailsController extends BaseController
{
    @Autowired
    private IApiUserBillDetailsService apiUserBillDetailsService;

    @Autowired
    private AppletTokenService appletTokenService;

    /**
     * 查询C端用户账单明细列表
     */
    @GetMapping("/billList")
    public AjaxResult list(ApiUserBillDetails apiUserBillDetails)
    {
        AppletLoginUser loginUser = appletTokenService.getLoginUser ();
        if(StringUtils.isNull(loginUser)){
            return AjaxResult.error(HttpStatus.UNAUTHORIZED,"登录信息失效");
        }else {
            appletTokenService.refreshToken(loginUser);
        }
        Integer type = apiUserBillDetails.getType();
        if (type == null){
            type = 0;
        }
        Calendar calendar = Calendar.getInstance();
        //查一周
        if (type == 1){
            calendar.add (Calendar.DAY_OF_YEAR,-7);
            Date beforeSevenDay = calendar.getTime();
            apiUserBillDetails.setStartTime(beforeSevenDay);
            apiUserBillDetails.setEndTime(DateUtils.getNowDate());
        }
        //查一个月
        if (type == 2){
            calendar = Calendar.getInstance();
            calendar.add (Calendar.MONTH,-1);
            Date beforeOneMonth = calendar.getTime();
            apiUserBillDetails.setStartTime(beforeOneMonth);
            apiUserBillDetails.setEndTime(DateUtils.getNowDate());
        }
        //查三个月
        if (type == 3){
            calendar = Calendar.getInstance();
            calendar.add (Calendar.MONTH,-3);
            Date beforeThreeMonth = calendar.getTime();
            apiUserBillDetails.setStartTime(beforeThreeMonth);
            apiUserBillDetails.setEndTime(DateUtils.getNowDate());
        }
        startPage();
        apiUserBillDetails.setUserId(loginUser.getApiUserInfo().getId());
        List<ApiUserBillDetails> list = apiUserBillDetailsService.selectUserBillDetailsList(apiUserBillDetails);
        return AjaxResult.success(getDataTable(list));
    }
}
