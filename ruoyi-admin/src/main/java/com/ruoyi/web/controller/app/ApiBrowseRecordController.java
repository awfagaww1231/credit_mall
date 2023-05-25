package com.ruoyi.web.controller.app;

import com.ruoyi.common.constant.HttpStatus;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.customer.common.AppletTokenService;
import com.ruoyi.customer.domain.ApiBrowseRecord;
import com.ruoyi.customer.domain.AppletLoginUser;
import com.ruoyi.customer.service.IApiBrowseRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 用户浏览足迹记录Controller +
 * 
 * @author ruoyi
 * @date 2022-11-07
 */
@RestController
@RequestMapping("/api/browseRecord")
public class ApiBrowseRecordController extends BaseController
{
    @Autowired
    private IApiBrowseRecordService apiBrowseRecordService;

    @Autowired
    private AppletTokenService appletTokenService;

    /**
     * 查询用户浏览足迹记录列表
     */
    @GetMapping("/browseRecordList")
    public AjaxResult list(ApiBrowseRecord apiBrowseRecord)
    {
        AppletLoginUser loginUser = appletTokenService.getLoginUser ();
        if(StringUtils.isNull(loginUser)){
            return AjaxResult.error(HttpStatus.UNAUTHORIZED,"登录信息失效");
        }else {
            appletTokenService.refreshToken(loginUser);
        }
        startPage();
        apiBrowseRecord.setUserId(loginUser.getApiUserInfo().getId());
        List<ApiBrowseRecord> list = apiBrowseRecordService.selectBrowseRecordList(apiBrowseRecord);
        return AjaxResult.success(getDataTable(list));
    }
}
