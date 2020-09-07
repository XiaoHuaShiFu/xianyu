package com.wudagezhandui.shixun.xianyu.pojo.vo;

import java.util.Date;

public class IdleCommentCommentVO {
    private Integer id;

    private Integer userId;

    private Integer idleCommentId;

    private Integer idleCommentCommentId;

    private String content;

    private Date createTime;

    private Date updateTime;

    public IdleCommentCommentVO() {
    }

    public IdleCommentCommentVO(Integer id, Integer userId, Integer idleCommentId, Integer idleCommentCommentId, String content, Date createTime, Date updateTime) {
        this.id = id;
        this.userId = userId;
        this.idleCommentId = idleCommentId;
        this.idleCommentCommentId = idleCommentCommentId;
        this.content = content;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Integer getId() { return this.id; }

    public void setId(Integer id) { this.id = id; }

    public Integer getUserId() { return this.userId; }

    public void setUserId(Integer userId) { this.userId = userId; }

    public Integer getIdleCommentId() { return this.idleCommentId; }

    public void setIdleCommentId(Integer idleCommentId) { this.idleCommentId = idleCommentId; }

    public Integer getIdleCommentCommentId() { return this.idleCommentCommentId; }

    public void setIdleCommentCommentId(Integer idleCommentCommentId) { this.idleCommentCommentId = idleCommentCommentId; }

    public String getContent() { return this.content; }

    public void setContent(String content) { this.content = content; }

    public Date getCreateTime() { return this.createTime; }

    public void setCreateTime(Date createTime) { this.createTime = createTime; }

    public Date getUpdateTime() { return this.updateTime; }

    public void setUpdateTime(Date updateTime) { this.updateTime = updateTime; }

    @Override
    public String toString() {
        return "idleCommentDO{" +
                "id = " + id +
                ", userId = '" + userId + '\'' +
                ", idleCommentId = '" + idleCommentId + '\'' +
                ", idleCommentCommentId = '" + idleCommentCommentId + '\'' +
                ", content = '" + content + '\'' +
                ", createTime = " + createTime +
                ", updateTime = " + updateTime +
                '}';

    }
}
