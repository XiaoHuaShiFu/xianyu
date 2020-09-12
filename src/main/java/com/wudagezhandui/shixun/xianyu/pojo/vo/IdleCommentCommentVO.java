package com.wudagezhandui.shixun.xianyu.pojo.vo;

import java.util.Date;

public class IdleCommentCommentVO {
    private Integer id;

    private Integer userId;

    private UserVO user;

    private Integer idleCommentId;

    private Integer parentIdleCommentCommentId;

    private String content;

    private Date createTime;

    private Date updateTime;

    public IdleCommentCommentVO() {
    }

    public IdleCommentCommentVO(Integer id, Integer userId, UserVO user, Integer idleCommentId,
                                Integer parentIdleCommentCommentId, String content, Date createTime, Date updateTime) {
        this.id = id;
        this.userId = userId;
        this.user = user;
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

    public UserVO getUser() {
        return user;
    }

    public void setUser(UserVO user) {
        this.user = user;
    }

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
        return "IdleCommentCommentVO{" +
                "id=" + id +
                ", userId=" + userId +
                ", user=" + user +
                ", idleCommentId=" + idleCommentId +
                ", parentIdleCommentCommentId=" + parentIdleCommentCommentId +
                ", content='" + content + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
