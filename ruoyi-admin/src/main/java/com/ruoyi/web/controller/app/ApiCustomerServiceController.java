package com.ruoyi.web.controller.app;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.customer.common.AppletTokenService;
import com.ruoyi.customer.domain.AppletLoginUser;
import com.ruoyi.customer.mapper.ApiSysUserMapper;
import com.ruoyi.customer.service.IApiAuthService;
import com.ruoyi.system.domain.CustomerService;
import com.ruoyi.system.service.ICustomerServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * 客服配置Controller
 *
 * @author ruoyi
 * @date 2023-03-22
 */
@RestController
@RequestMapping("/api/customerService")
public class ApiCustomerServiceController extends BaseController
{
    @Autowired
    private ICustomerServiceService customerServiceService;


    @Autowired
    private AppletTokenService appletTokenService;

    @Autowired
    private IApiAuthService apiAuthService;

    @Autowired
    private ApiSysUserMapper apiSysUserMapper;

    /**
     * 查询客服配置列表
     */
    @GetMapping("/list")
    public TableDataInfo list(CustomerService customerService) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {

        List<CustomerService> customerList = new ArrayList<>();
        AppletLoginUser loginUser = appletTokenService.getLoginUser ();
        if(StringUtils.isNull(loginUser)){
            return getDataTable(customerList);
        }else {
            appletTokenService.refreshToken(loginUser);
        }
        Long supId = loginUser.getApiUserInfo().getSupId();
        if (supId == null){
            startPage();
            customerService.setInviteCode("SSSS");
            List<CustomerService> list = customerServiceService.selectCustomerServiceListLang(customerService);
            return getDataTable(list);
        }
        SysUser supUserInfo = apiSysUserMapper.selectUserById(supId);
        String inviteCode = supUserInfo.getReferralCode();

        startPage();
        List<CustomerService> list = customerServiceService.selectCustomerServiceListLang(customerService);
        CustomerService customerPub = null;
        for (CustomerService customer : list) {
            if ("SSSS".equals(customer.getInviteCode())){
                customerPub =  customer;
            }else {
                if(customer.getInviteCode().equals(inviteCode)){
                    customerList.add(customer);
                }
            }
        }
        if (customerList.isEmpty()){
            customerList.add(customerPub);
        }
        return getDataTable(customerList);
    }
}
