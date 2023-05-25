package com.ruoyi.web.controller.system;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.annotation.RepeatSubmit;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.redis.RedisCache;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.UserInfo;
import com.ruoyi.system.domain.UserPromotionStatistics;
import com.ruoyi.system.service.IUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.List;

/**
 * C端用户信息Controller
 * 
 * @author ruoyi
 * @date 2022-11-01
 */
@RestController
@RequestMapping("/system/userInfo")
public class UserInfoController extends BaseController
{
    @Autowired
    private IUserInfoService userInfoService;

    @Autowired
    private RedisCache redisCache;

    /**
     * 查询C端用户信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:userInfo:list')")
    @GetMapping("/list")
    public TableDataInfo list(UserInfo userInfo)
    {
        startPage();
        List<UserInfo> list = userInfoService.selectUserInfoList(userInfo);
        return getDataTable(list);
    }

    /**
     * 导出C端用户信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:userInfo:export')")
    @Log(title = "C端用户信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, UserInfo userInfo)
    {
        List<UserInfo> list = userInfoService.selectUserInfoList(userInfo);
        ExcelUtil<UserInfo> util = new ExcelUtil<UserInfo>(UserInfo.class);
        util.exportExcel(response, list, "C端用户信息数据");
    }

    /**
     * 获取C端用户信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:userInfo:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(userInfoService.selectUserInfoById(id));
    }

    /**
     * 新增C端用户信息
     */
    @PreAuthorize("@ss.hasPermi('system:userInfo:add')")
    @RepeatSubmit
    @Log(title = "C端用户信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody UserInfo userInfo)
    {
        if (StringUtils.isEmpty(userInfo.getUserName())){
            return AjaxResult.error("请输入用户名");
        }
        if (StringUtils.isEmpty(userInfo.getNickName())){
            return AjaxResult.error("请输入用户昵称");
        }
        if (StringUtils.isNull(userInfo.getMobile())){
            return AjaxResult.error("请输入手机号");
        }
        //验证手机号格式是否正确
        if (!StringUtils.isNumeric(userInfo.getMobile())){
            return AjaxResult.error("手机号格式不正确！");
        }
        if (userInfo.getMobile ().length () < 6){
            return AjaxResult.error ("手机号必须大等于6位数!");
        }
        if (StringUtils.isEmpty(userInfo.getPassword())){
            return AjaxResult.error("请输入登录密码");
        }
        if (StringUtils.isEmpty(userInfo.getPayPassword())){
            return AjaxResult.error("请输入资金密码");
        }
        if (StringUtils.isNull(userInfo.getUserType())){
            return AjaxResult.error("请选择用户类型");
        }
        if (StringUtils.isEmpty(userInfo.getEmail())){
//            return AjaxResult.error("请填写邮箱");
        }else {
            if (!userInfo.getEmail().contains("@")){
                return AjaxResult.error("请填写有效的邮箱");
            }
        }
        //记录创建人
        userInfo.setCreateUser (getUsername());
        return userInfoService.insertUserInfo(userInfo);
    }


    /**
     * 修改C端用户信息
     */
    @PreAuthorize("@ss.hasPermi('system:userInfo:edit')")
    @Log(title = "C端用户信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody UserInfo userInfo)
    {
        if (StringUtils.isNull(userInfo.getId())){
            return AjaxResult.error("请先选择需要修改信息的用户");
        }
        if (StringUtils.isNull(userInfo.getUserName())){
            return AjaxResult.error("请输入用户名");
        }
        if (StringUtils.isEmpty(userInfo.getUserName())){
            return AjaxResult.error("请输入用户名");
        }
        if (StringUtils.isNull(userInfo.getNickName())){
            return AjaxResult.error("请输入用户昵称");
        }
        if (StringUtils.isEmpty(userInfo.getNickName())){
            return AjaxResult.error("请输入用户昵称");
        }
        if (StringUtils.isNull(userInfo.getMobile())){
            return AjaxResult.error("请输入手机号");
        }
        //验证手机号格式是否正确
        if (!StringUtils.isNumeric(userInfo.getMobile())){
            return AjaxResult.error("手机号格式不正确！");
        }
        if (userInfo.getMobile ().length () < 6){
            return AjaxResult.error ("手机号必须大等于6位数!");
        }

        return toAjax(userInfoService.updateUserInfo(userInfo));
    }

    /**
     * 修改实名认证信息
     */
    @PreAuthorize("@ss.hasPermi('system:userInfo:updateAuthInfo')")
    @Log(title = "修改实名认证信息", businessType = BusinessType.UPDATE)
    @PostMapping("/updateAuthInfo")
    public AjaxResult updateAuthInfo(@RequestBody UserInfo userInfo)
    {
        if (StringUtils.isNull(userInfo.getId())){
            return AjaxResult.error("请选择需要修改实名认证信息的用户");
        }
        if (StringUtils.isEmpty(userInfo.getRealName())){
            return AjaxResult.error("请输入真实姓名");
        }
        if (StringUtils.isEmpty(userInfo.getIdNumber())){
            return AjaxResult.error("请输入身份证号");
        }
        UserInfo userInfoVo = new UserInfo();
        userInfoVo.setId(userInfo.getId());
        userInfoVo.setRealNameAuthStatus(userInfo.getRealNameAuthStatus());
        userInfoVo.setRealName(userInfo.getRealName());
        userInfoVo.setIdNumber(userInfo.getIdNumber());
        userInfoVo.setIdCardImg1(userInfo.getIdCardImg1());
        userInfoVo.setIdCardImg2(userInfo.getIdCardImg2());
        userInfoVo.setIdCardImg3(userInfo.getIdCardImg3());
        return userInfoService.updateAuthInfo(userInfoVo);
    }

    /**
     * 删除C端用户信息
     */
    @PreAuthorize("@ss.hasPermi('system:userInfo:remove')")
    @Log(title = "C端用户信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(userInfoService.deleteUserInfoByIds(ids));
    }

    //启用/禁用
    @GetMapping("/changeStatus")
    @PreAuthorize("@ss.hasPermi('system:userInfo:changeStatus')")
    public AjaxResult changeStatus(Long id) {
        UserInfo user = userInfoService.selectUserInfoById (id);
        if (StringUtils.isNull(user)){
            return AjaxResult.error("获取用户信息失败，请刷新页面后重新尝试");
        }
        if (user.getStatus ().toString ().equals ("0")){
            //判断是否已经登录
            String tokenBefore = redisCache.getCacheObject ("Apptoken"+id.toString ());
            //如果已经登录
            if (tokenBefore != null){
                String tokenKey = "app_login_tokens:" + tokenBefore;
                redisCache.deleteObject (tokenKey);
            }
        }
        return toAjax (userInfoService.updateStatus (id));
    }

    /**
     * 重置登录密码（默认密码123456）
     */
    @PostMapping("/resetPassword")
    @PreAuthorize("@ss.hasPermi('system:userInfo:resetPassword')")
    public AjaxResult resetPassword(@RequestBody UserInfo userInfo) {
        if (StringUtils.isNull(userInfo.getId())){
            return AjaxResult.error("请先选择需要修改登录密码的用户");
        }
        if (StringUtils.isNull(userInfo.getPassword())){
            return AjaxResult.error("请输入新的登录密码");
        }
        if (StringUtils.isEmpty(userInfo.getPassword())){
            return AjaxResult.error("请输入新的登录密码");
        }
        return toAjax(userInfoService.resetPassword (userInfo.getId(),userInfo.getPassword()));
    }

    /**
     * 重置支付密码（默认密码123456）
     */
    @GetMapping("/resetPayPassword")
    @PreAuthorize("@ss.hasPermi('system:userInfo:resetPayPassword')")
    public AjaxResult resetPayPassword(@RequestBody UserInfo userInfo) {
        if (StringUtils.isNull(userInfo.getId())){
            return AjaxResult.error("请先选择需要修改资金密码的用户");
        }
        if (StringUtils.isNull(userInfo.getPayPassword())){
            return AjaxResult.error("请输入新的资金密码");
        }
        if (StringUtils.isEmpty(userInfo.getPayPassword())){
            return AjaxResult.error("请输入新的资金密码");
        }
        return toAjax(userInfoService.resetPayPassword (userInfo.getId(),userInfo.getPayPassword()));
    }

    /**
     * 配置信用卡片额度与可用状态
     */
    @PostMapping("/setCreditCardInfo")
    @PreAuthorize("@ss.hasPermi('system:userInfo:setCreditCardInfo')")
    public AjaxResult setCreditCardInfo(@RequestBody UserInfo userInfo) {
        if (StringUtils.isNull(userInfo.getId())){
            return AjaxResult.error("请选择需要修改信息的用户");
        }
        if (StringUtils.isNull(userInfo.getCreditCardTotalAmount())){
            return AjaxResult.error("请输入需要配置的信用额度");
        }
        if (userInfo.getCreditCardTotalAmount().compareTo(BigDecimal.ZERO) < 0){
            return AjaxResult.error("信用额度必须大于等于0");
        }
        if (StringUtils.isNull(userInfo.getCreditCardEnableStatus())){
            return AjaxResult.error("请选择此用户信用卡的可用状态");
        }
        if (userInfo.getCreditCardEnableStatus() != 1 && userInfo.getCreditCardEnableStatus() != 2 && userInfo.getCreditCardEnableStatus() != 3){
            return AjaxResult.error("操作异常");
        }
        UserInfo userInfoVo = new UserInfo();
        userInfoVo.setId(userInfo.getId());
        userInfoVo.setCreditCardTotalAmount(userInfo.getCreditCardTotalAmount());
        userInfoVo.setCreditCardEnableStatus(userInfo.getCreditCardEnableStatus());
        return userInfoService.setCreditCardInfo(userInfoVo);
    }

    /**
     * 查看用户所有信息（包括房产证。驾驶证。行驶证等。。）
     */
    @GetMapping("/getUserAllAssetInformation")
    @PreAuthorize("@ss.hasPermi('system:userInfo:getUserAllAssetInformation')")
    public AjaxResult getUserAllAssetInformation(Long userId) {
        if (StringUtils.isNull(userId)){
            return AjaxResult.error("请选择需要查看信息的用户");
        }
        return userInfoService.getUserAllAssetInformation(userId);
    }


    /**
     * 查询C端用户信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:UserPromotionStatistics:list')")
    @GetMapping("/static/list")
    public TableDataInfo staticList(UserPromotionStatistics userPromotionStatistics)
    {
        startPage();
        List<UserPromotionStatistics> list = userInfoService.queryUserPromotionStatistics(userPromotionStatistics);
        return getDataTable(list);
    }

    /**
     * 导出C端用户信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:UserPromotionStatistics:export')")
    @Log(title = "用户推广统计数据", businessType = BusinessType.EXPORT)
    @PostMapping("/static/export")
    public void staticExport(HttpServletResponse response, UserPromotionStatistics userPromotionStatistics)
    {
        List<UserPromotionStatistics> list = userInfoService.queryUserPromotionStatistics(userPromotionStatistics);
        ExcelUtil<UserPromotionStatistics> util = new ExcelUtil<UserPromotionStatistics>(UserPromotionStatistics.class);
        util.exportExcel(response, list, "用户推广统计数据");
    }


}
