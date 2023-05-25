package com.ruoyi.customer.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.customer.domain.ApiBuyersShowComment;
import com.ruoyi.customer.mapper.ApiBuyersShowCommentMapper;
import com.ruoyi.customer.service.IApiBuyersShowCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 买家秀帖子评论Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-11-28
 */
@Service
public class ApiBuyersShowCommentServiceImpl implements IApiBuyersShowCommentService
{
    @Autowired
    private ApiBuyersShowCommentMapper buyersShowCommentMapper;

    /**
     * 查询买家秀帖子评论
     * 
     * @param id 买家秀帖子评论主键
     * @return 买家秀帖子评论
     */
    @Override
    public ApiBuyersShowComment selectBuyersShowCommentById(Long id)
    {
        return buyersShowCommentMapper.selectBuyersShowCommentById(id);
    }

    /**
     * 查询买家秀帖子评论列表
     * 
     * @param buyersShowComment 买家秀帖子评论
     * @return 买家秀帖子评论
     */
    @Override
    public List<ApiBuyersShowComment> selectBuyersShowCommentList(ApiBuyersShowComment buyersShowComment)
    {
        return buyersShowCommentMapper.selectBuyersShowCommentList(buyersShowComment);
    }

    /**
     * 新增买家秀帖子评论
     * 
     * @param buyersShowComment 买家秀帖子评论
     * @return 结果
     */
    @Override
    public int insertBuyersShowComment(ApiBuyersShowComment buyersShowComment)
    {
        buyersShowComment.setCreateTime(DateUtils.getNowDate());
        return buyersShowCommentMapper.insertBuyersShowComment(buyersShowComment);
    }

    /**
     * 修改买家秀帖子评论
     * 
     * @param buyersShowComment 买家秀帖子评论
     * @return 结果
     */
    @Override
    public int updateBuyersShowComment(ApiBuyersShowComment buyersShowComment)
    {
        return buyersShowCommentMapper.updateBuyersShowComment(buyersShowComment);
    }

    /**
     * 批量删除买家秀帖子评论
     * 
     * @param ids 需要删除的买家秀帖子评论主键
     * @return 结果
     */
    @Override
    public int deleteBuyersShowCommentByIds(Long[] ids)
    {
        return buyersShowCommentMapper.deleteBuyersShowCommentByIds(ids);
    }

    /**
     * 删除买家秀帖子评论信息
     * 
     * @param id 买家秀帖子评论主键
     * @return 结果
     */
    @Override
    public int deleteBuyersShowCommentById(Long id)
    {
        return buyersShowCommentMapper.deleteBuyersShowCommentById(id);
    }
}
