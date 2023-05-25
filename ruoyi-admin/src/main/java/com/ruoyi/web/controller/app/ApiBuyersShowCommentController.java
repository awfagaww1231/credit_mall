package com.ruoyi.web.controller.app;

import java.util.ArrayList;
import java.util.List;

import com.ruoyi.common.annotation.RepeatSubmit;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.customer.common.AppletTokenService;
import com.ruoyi.customer.domain.ApiBuyersShowComment;
import com.ruoyi.customer.domain.AppletLoginUser;
import com.ruoyi.customer.service.IApiBuyersShowCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;

import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 买家秀帖子评论Controller
 * 
 * @author ruoyi
 * @date 2022-11-28
 */
@RestController
@RequestMapping("/api/buyersShowComment")
public class ApiBuyersShowCommentController extends BaseController
{
    @Autowired
    private IApiBuyersShowCommentService buyersShowCommentService;

    @Autowired
    private AppletTokenService appletTokenService;

    /**
     * 查询买家秀帖子评论列表
     */
    @GetMapping("/buyersShowCommentList")
    public AjaxResult list(ApiBuyersShowComment buyersShowComment)
    {
        if (StringUtils.isNull(buyersShowComment.getBuyersShowId())){
            return AjaxResult.success(getDataTable(new ArrayList<>()));
        }
        startPage();
        List<ApiBuyersShowComment> list = buyersShowCommentService.selectBuyersShowCommentList(buyersShowComment);
        return AjaxResult.success(getDataTable(list));
    }


    /**
     * 获取买家秀帖子评论详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(buyersShowCommentService.selectBuyersShowCommentById(id));
    }

    /**
     * 新增买家秀帖子评论
     */
    @PostMapping("/addBuyersShowComment")
    @RepeatSubmit
    public AjaxResult addBuyersShowComment(@RequestBody ApiBuyersShowComment buyersShowComment)
    {
        AppletLoginUser loginUser = appletTokenService.getLoginUser();
        if (StringUtils.isNull(loginUser)) {
            return AjaxResult.error(401, "登录信息失效");
        } else {
            appletTokenService.refreshToken(loginUser);
        }
        buyersShowComment.setUserId(loginUser.getApiUserInfo().getId());
        return toAjax(buyersShowCommentService.insertBuyersShowComment(buyersShowComment));
    }

    /**
     * 删除买家秀帖子评论
     */
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        AppletLoginUser loginUser = appletTokenService.getLoginUser();
        if (StringUtils.isNull(loginUser)) {
            return AjaxResult.error(401, "登录信息失效");
        } else {
            appletTokenService.refreshToken(loginUser);
        }
        return toAjax(buyersShowCommentService.deleteBuyersShowCommentByIds(ids));
    }
}
