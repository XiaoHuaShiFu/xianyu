package com.wudagezhandui.shixun.xianyu.pojo.do0;

import java.util.Date;

public class IdleCommentCommentDO {

    private Integer id;

    private Integer userId;

    private Integer idleCommentId;

    private Integer parentIdleCommentCommentId;

    private String content;

    private Date createTime;

    private Date updateTime;

    public IdleCommentCommentDO(){

    }

    public IdleCommentCommentDO(Integer id, Integer userId, Integer idleCommentId, Integer parentIdleCommentCommentId, String content, Date createTime, Date updateTime) {
        this.id = id;
        this.userId = userId;
        this.idleCommentId = idleCommentId;
        this.parentIdleCommentCommentId = parentIdleCommentCommentId;
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

    public Integer getParentIdleCommentCommentId() { return this.parentIdleCommentCommentId; }

    public void setParentIdleCommentCommentId(Integer parentIdleCommentCommentId) { this.parentIdleCommentCommentId = parentIdleCommentCommentId; }

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
                ", parentIdleCommentCommentId = '" + parentIdleCommentCommentId + '\'' +
                ", content = '" + content + '\'' +
                ", createTime = " + createTime +
                ", updateTime = " + updateTime +
                '}';

    }
}
