package com.ruoyi.web.controller.app;

import com.ruoyi.common.annotation.RepeatSubmit;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.customer.common.AppletTokenService;
import com.ruoyi.customer.domain.AppletLoginUser;
import com.ruoyi.system.domain.UserLoanRecord;
import com.ruoyi.system.mapper.UserLoanRecordMapper;
import com.ruoyi.system.service.IUserLoanRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户贷款记录Controller
 * 
 * @author ruoyi
 * @date 2023-04-19
 */
@RestController
@RequestMapping("/api/userLoanRecord")
public class ApiUserLoanRecordController extends BaseController
{
    @Autowired
    private IUserLoanRecordService userLoanRecordService;

    @Autowired
    private AppletTokenService appletTokenService;

    @Autowired
    private UserLoanRecordMapper userLoanRecordMapper;


    /**
     * 查询用户贷款记录列表
     */
    @GetMapping("/userLoanRecordList")
    public AjaxResult userLoanRecordList(UserLoanRecord userLoanRecord)
    {
        AppletLoginUser loginUser = appletTokenService.getLoginUser();
        if (StringUtils.isNull(loginUser)) {
            return AjaxResult.error(401, "登录信息失效");
        } else {
            appletTokenService.refreshToken(loginUser);
        }
        startPage();
        userLoanRecord.setUserId(loginUser.getApiUserInfo().getId());
        List<UserLoanRecord> list = userLoanRecordMapper.selectUserLoanRecordList(userLoanRecord);
        return AjaxResult.success(getDataTable(list));
    }


    /**
     * 信用额度提款
     */
    @PostMapping("/addUserLoanRecord")
    @RepeatSubmit
    public AjaxResult addUserLoanRecord(@RequestBody UserLoanRecord userLoanRecord)
    {
        AppletLoginUser loginUser = appletTokenService.getLoginUser();
        if (StringUtils.isNull(loginUser)) {
            return AjaxResult.error(401, "登录信息失效");
        } else {
            appletTokenService.refreshToken(loginUser);
        }
        if (StringUtils.isNull(userLoanRecord.getInstallmentInterestConfigId())){
            return AjaxResult.error("请选择还款期数");
        }
        if (StringUtils.isNull(userLoanRecord.getLoanTotalAmount())){
            return AjaxResult.error("请输入贷款金额");
        }
        if (StringUtils.isEmpty(userLoanRecord.getPayPassword())){
            return AjaxResult.error("请输入资金密码");
        }
        userLoanRecord.setUserId(loginUser.getApiUserInfo().getId());
        return userLoanRecordService.addUserLoanRecord(userLoanRecord);
    }


}
