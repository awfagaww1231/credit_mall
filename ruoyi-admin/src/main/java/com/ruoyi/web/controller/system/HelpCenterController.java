package com.ruoyi.web.controller.system;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.HelpCenter;
import com.ruoyi.system.service.IHelpCenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * 帮助中心Controller
 * 
 * @author ruoyi
 * @date 2023-04-19
 */
@RestController
@RequestMapping("/system/helpCenter")
public class HelpCenterController extends BaseController
{
    @Autowired
    private IHelpCenterService helpCenterService;

    /**
     * 查询帮助中心列表
     */
    @PreAuthorize("@ss.hasPermi('system:helpCenter:list')")
    @GetMapping("/list")
    public TableDataInfo list(HelpCenter helpCenter) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        startPage();
        List<HelpCenter> list = helpCenterService.selectHelpCenterList(helpCenter);
        return getDataTable(list);
    }

    /**
     * 导出帮助中心列表
     */
    @PreAuthorize("@ss.hasPermi('system:helpCenter:export')")
    @Log(title = "帮助中心", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, HelpCenter helpCenter) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        List<HelpCenter> list = helpCenterService.selectHelpCenterList(helpCenter);
        ExcelUtil<HelpCenter> util = new ExcelUtil<HelpCenter>(HelpCenter.class);
        util.exportExcel(response, list, "帮助中心数据");
    }

    /**
     * 获取帮助中心详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:helpCenter:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(helpCenterService.selectHelpCenterById(id));
    }

    /**
     * 新增帮助中心
     */
    @PreAuthorize("@ss.hasPermi('system:helpCenter:add')")
    @Log(title = "帮助中心", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody HelpCenter helpCenter)
    {
        if (StringUtils.isEmpty(helpCenter.getQuestion())){
            return AjaxResult.error("请输入问题");
        }
        if (StringUtils.isEmpty(helpCenter.getAnswer())){
            return AjaxResult.error("请输入答案");
        }
        return toAjax(helpCenterService.insertHelpCenter(helpCenter));
    }

    /**
     * 修改帮助中心
     */
    @PreAuthorize("@ss.hasPermi('system:helpCenter:edit')")
    @Log(title = "帮助中心", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody HelpCenter helpCenter)
    {
        return toAjax(helpCenterService.updateHelpCenter(helpCenter));
    }

    /**
     * 删除帮助中心
     */
    @PreAuthorize("@ss.hasPermi('system:helpCenter:remove')")
    @Log(title = "帮助中心", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(helpCenterService.deleteHelpCenterByIds(ids));
    }
}
