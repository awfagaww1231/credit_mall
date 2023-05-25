package com.ruoyi.web.controller.app;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.system.service.ILoanApplyInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 贷款申请资料Controller
 * 
 * @author ruoyi
 * @date 2023-04-03
 */
@RestController
@RequestMapping("/system/loanApplyInfo")
public class ApiLoanApplyInfoController extends BaseController {

    @Autowired
    private ILoanApplyInfoService loanApplyInfoService;
//
//    /**
//     * 去填写贷款申请资料
//     */
//    @GetMapping("/toFillLoanApplyInfo")
//    public AjaxResult toFillLoanApplyInfo()
//    {
//        return loanApplyInfoService.toFillLoanApplyInfo();
//    }


}
