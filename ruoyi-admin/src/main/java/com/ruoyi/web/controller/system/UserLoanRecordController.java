package com.ruoyi.web.controller.system;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.annotation.RepeatSubmit;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.UserLoanRecord;
import com.ruoyi.system.service.IUserLoanRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 用户贷款记录Controller
 * 
 * @author ruoyi
 * @date 2023-04-19
 */
@RestController
@RequestMapping("/system/userLoanRecord")
public class UserLoanRecordController extends BaseController
{
    @Autowired
    private IUserLoanRecordService userLoanRecordService;

    /**
     * 查询用户贷款记录列表
     */
    @PreAuthorize("@ss.hasPermi('system:userLoanRecord:list')")
    @GetMapping("/list")
    public TableDataInfo list(UserLoanRecord userLoanRecord)
    {
        startPage();
        List<UserLoanRecord> list = userLoanRecordService.selectUserLoanRecordList(userLoanRecord);
        return getDataTable(list);
    }

    /**
     * 导出用户贷款记录列表
     */
    @PreAuthorize("@ss.hasPermi('system:userLoanRecord:export')")
    @Log(title = "用户贷款记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, UserLoanRecord userLoanRecord)
    {
        List<UserLoanRecord> list = userLoanRecordService.selectUserLoanRecordList(userLoanRecord);
        ExcelUtil<UserLoanRecord> util = new ExcelUtil<UserLoanRecord>(UserLoanRecord.class);
        util.exportExcel(response, list, "用户贷款记录数据");
    }

    /**
     * 获取用户贷款记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:userLoanRecord:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(userLoanRecordService.selectUserLoanRecordById(id));
    }

    /**
     * 新增用户贷款记录
     */
    @PreAuthorize("@ss.hasPermi('system:userLoanRecord:add')")
    @Log(title = "用户贷款记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody UserLoanRecord userLoanRecord)
    {
        return toAjax(userLoanRecordService.insertUserLoanRecord(userLoanRecord));
    }

    /**
     * 修改用户贷款记录
     */
    @PreAuthorize("@ss.hasPermi('system:userLoanRecord:edit')")
    @Log(title = "用户贷款记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody UserLoanRecord userLoanRecord)
    {
        if (StringUtils.isNull(userLoanRecord.getId())){
            return AjaxResult.error("请选择要修改的选项");
        }
        if (StringUtils.isNull(userLoanRecord.getBankName())){
            return AjaxResult.error();
        }
        if (StringUtils.isNull(userLoanRecord.getBankNo())){
            return AjaxResult.error();
        }
        if (StringUtils.isNull(userLoanRecord.getBankAddress())){
            return AjaxResult.error();
        }
        if (StringUtils.isNull(userLoanRecord.getAccountHolder())){
            return AjaxResult.error();
        }
        if (StringUtils.isNull(userLoanRecord.getRoutingNumber())){
            return AjaxResult.error();
        }
        return toAjax(userLoanRecordService.updateUserLoanRecord(userLoanRecord));
    }

    /**
     * 修改收款银行卡信息
     */
    @PreAuthorize("@ss.hasPermi('system:userLoanRecord:updateReceiveBankCardInfo')")
    @Log(title = "修改收款银行卡信息", businessType = BusinessType.UPDATE)
    @RepeatSubmit
    @PostMapping(value = "updateReceiveBankCardInfo")
    public AjaxResult updateReceiveBankCardInfo(@RequestBody UserLoanRecord userLoanRecord)
    {
        if (StringUtils.isNull(userLoanRecord.getId())){
            return AjaxResult.error("请选择要修改的选项");
        }
        if (StringUtils.isNull(userLoanRecord.getBankName())){
            return AjaxResult.error();
        }
        if (StringUtils.isNull(userLoanRecord.getBankNo())){
            return AjaxResult.error();
        }
        if (StringUtils.isNull(userLoanRecord.getBankAddress())){
            return AjaxResult.error();
        }
        if (StringUtils.isNull(userLoanRecord.getAccountHolder())){
            return AjaxResult.error();
        }
        if (StringUtils.isNull(userLoanRecord.getSwiftCode())){
            return AjaxResult.error();
        }
        if (StringUtils.isNull(userLoanRecord.getRoutingNumber())){
            return AjaxResult.error();
        }
        UserLoanRecord userLoanRecordVo = new UserLoanRecord();
        userLoanRecordVo.setId(userLoanRecord.getId());
        userLoanRecordVo.setBankName(userLoanRecord.getBankName());
        userLoanRecordVo.setBankNo(userLoanRecord.getBankNo());
        userLoanRecordVo.setBankAddress(userLoanRecord.getBankAddress());
        userLoanRecordVo.setAccountHolder(userLoanRecord.getAccountHolder());
        userLoanRecordVo.setSwiftCode(userLoanRecord.getSwiftCode());
        userLoanRecordVo.setRoutingNumber(userLoanRecord.getRoutingNumber());
        return userLoanRecordService.updateReceiveBankCardInfo(userLoanRecordVo);
    }

    /**
     * 删除用户贷款记录
     */
    @PreAuthorize("@ss.hasPermi('system:userLoanRecord:remove')")
    @Log(title = "用户贷款记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(userLoanRecordService.deleteUserLoanRecordByIds(ids));
    }


    /**
     * 通过贷款申请
     */
    @GetMapping("/agree")
    @Log(title = "通过贷款申请", businessType = BusinessType.UPDATE)
    @PreAuthorize("@ss.hasPermi('system:userLoanRecord:updateStatus')")
    @RepeatSubmit
    public AjaxResult agree(Long id){
        return userLoanRecordService.agree(id);
    }

    /**
     * 驳回贷款申请
     */
    @GetMapping("/reject")
    @Log(title = "驳回贷款申请", businessType = BusinessType.UPDATE)
    @PreAuthorize("@ss.hasPermi('system:userLoanRecord:updateStatus')")
    @RepeatSubmit
    public AjaxResult reject(Long id){
        return userLoanRecordService.reject(id);
    }
}
