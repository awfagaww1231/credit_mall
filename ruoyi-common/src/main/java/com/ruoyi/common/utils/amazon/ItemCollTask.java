package com.ruoyi.common.utils.amazon;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 商品采集任务对象 item_coll_task
 * 
 * @author ruoyi
 * @date 2023-03-07
 */
public class ItemCollTask extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 任务ID */
    private Long taskId;

    /** 采集类型 1：单品采集 2：关键词采集 */
    @Excel(name = "采集类型")
    private String collType;

    /** 电商平台 */
    @Excel(name = "电商平台")
    private String platform;

    /** 站点国家 */
    @Excel(name = "站点国家")
    private String country;

    /** 采集URL */
    @Excel(name = "采集URL")
    private String collUrl;

    /** 采集数量 */
    @Excel(name = "采集数量")
    private Long collNum;

    /** 任务状态 0已取消 1未开始 2进行中 3已完成  */
    @Excel(name = "任务状态 ")
    private String taskStatus;

    /** 开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "开始时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date beginTime;

    /** 结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "结束时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;

    /** 删除标识 */
    private String delFlag;

    //采集语言
    private String lcMain ="zh-CN";


    public void setTaskId(Long taskId) 
    {
        this.taskId = taskId;
    }

    public Long getTaskId() 
    {
        return taskId;
    }
    public void setCollType(String collType) 
    {
        this.collType = collType;
    }

    public String getCollType() 
    {
        return collType;
    }
    public void setPlatform(String platform) 
    {
        this.platform = platform;
    }

    public String getPlatform() 
    {
        return platform;
    }
    public void setCountry(String country) 
    {
        this.country = country;
    }

    public String getCountry() 
    {
        return country;
    }
    public void setCollUrl(String collUrl) 
    {
        this.collUrl = collUrl;
    }

    public String getCollUrl() 
    {
        return collUrl;
    }
    public void setCollNum(Long collNum) 
    {
        this.collNum = collNum;
    }

    public Long getCollNum() 
    {
        return collNum;
    }
    public void setTaskStatus(String taskStatus) 
    {
        this.taskStatus = taskStatus;
    }

    public String getTaskStatus() 
    {
        return taskStatus;
    }
    public void setBeginTime(Date beginTime) 
    {
        this.beginTime = beginTime;
    }

    public Date getBeginTime() 
    {
        return beginTime;
    }
    public void setEndTime(Date endTime) 
    {
        this.endTime = endTime;
    }

    public Date getEndTime() 
    {
        return endTime;
    }
    public void setDelFlag(String delFlag) 
    {
        this.delFlag = delFlag;
    }

    public String getDelFlag() 
    {
        return delFlag;
    }

    public String getLcMain() {
        return lcMain;
    }

    public void setLcMain(String lcMain) {
        this.lcMain = lcMain;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("taskId", getTaskId())
            .append("collType", getCollType())
            .append("platform", getPlatform())
            .append("country", getCountry())
            .append("collUrl", getCollUrl())
            .append("collNum", getCollNum())
            .append("taskStatus", getTaskStatus())
            .append("beginTime", getBeginTime())
            .append("endTime", getEndTime())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("lcMain", getLcMain())
            .toString();
    }
}
