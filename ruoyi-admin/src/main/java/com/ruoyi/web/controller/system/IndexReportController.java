package com.ruoyi.web.controller.system;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysRole;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.system.domain.IndexReport;
import com.ruoyi.system.service.IIndexReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 首页报表Controller
 *
 * @author ruoyi
 * @date 2022-11-07
 */
@RestController
@RequestMapping("/system/indexReport")
public class IndexReportController extends BaseController {

    @Autowired
    private IIndexReportService indexReportService;

    @GetMapping("/indexReport")
    public AjaxResult indexReport(){
        IndexReport indexReport = null;
        // 获取当前的用户
        LoginUser loginUser = SecurityUtils.getLoginUser();
        List<SysRole> roles = loginUser.getUser().getRoles();
        for (int i = 0; i < roles.size(); i++) {
            SysRole role = roles.get(i);
            String dataScope = role.getDataScope();
            //如果是全部数据权限
            if (String.valueOf(1).equals(dataScope))
            {
                indexReport = indexReportService.indexReport();
            }
        }
        return AjaxResult.success().put("indexReport",indexReport);
    }


}
