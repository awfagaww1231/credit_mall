package com.ruoyi.customer.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 买家秀帖子评论对象 buyers_show_comment
 * 
 * @author ruoyi
 * @date 2022-11-28
 */
public class ApiBuyersShowComment extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 买家秀id */
    @Excel(name = "买家秀id")
    private Long buyersShowId;

    /** 评论用户id */
    @Excel(name = "评论用户id")
    private Long userId;


    /** 用户名 */
    @Excel(name = "用户名")
    private String userName;

    /** 头像地址 */
    @Excel(name = "头像地址")
    private String avatar;

    /** 评论内容 */
    @Excel(name = "评论内容")
    private String commentContent;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setBuyersShowId(Long buyersShowId) 
    {
        this.buyersShowId = buyersShowId;
    }

    public Long getBuyersShowId() 
    {
        return buyersShowId;
    }
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setCommentContent(String commentContent) 
    {
        this.commentContent = commentContent;
    }

    public String getCommentContent() 
    {
        return commentContent;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("buyersShowId", getBuyersShowId())
            .append("userId", getUserId())
            .append("commentContent", getCommentContent())
            .append("createTime", getCreateTime())
            .toString();
    }
}
