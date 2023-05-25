package com.ruoyi.customer.service;

import com.ruoyi.customer.domain.ApiBuyersShowComment;

import java.util.List;

/**
 * 买家秀帖子评论Service接口
 * 
 * @author ruoyi
 * @date 2022-11-28
 */
public interface IApiBuyersShowCommentService
{
    /**
     * 查询买家秀帖子评论
     * 
     * @param id 买家秀帖子评论主键
     * @return 买家秀帖子评论
     */
    public ApiBuyersShowComment selectBuyersShowCommentById(Long id);

    /**
     * 查询买家秀帖子评论列表
     * 
     * @param buyersShowComment 买家秀帖子评论
     * @return 买家秀帖子评论集合
     */
    public List<ApiBuyersShowComment> selectBuyersShowCommentList(ApiBuyersShowComment buyersShowComment);

    /**
     * 新增买家秀帖子评论
     * 
     * @param buyersShowComment 买家秀帖子评论
     * @return 结果
     */
    public int insertBuyersShowComment(ApiBuyersShowComment buyersShowComment);

    /**
     * 修改买家秀帖子评论
     * 
     * @param buyersShowComment 买家秀帖子评论
     * @return 结果
     */
    public int updateBuyersShowComment(ApiBuyersShowComment buyersShowComment);

    /**
     * 批量删除买家秀帖子评论
     * 
     * @param ids 需要删除的买家秀帖子评论主键集合
     * @return 结果
     */
    public int deleteBuyersShowCommentByIds(Long[] ids);

    /**
     * 删除买家秀帖子评论信息
     * 
     * @param id 买家秀帖子评论主键
     * @return 结果
     */
    public int deleteBuyersShowCommentById(Long id);
}
