package com.wudagezhandui.shixun.xianyu.pojo.vo;

import java.util.Date;

public class IdleCommentVO {

    private Integer id;

    private Integer userId;

    private UserVO user;

    private Integer idleId;

    private String content;

    private Integer comments;

    private Date createTime;

    private Date updateTime;

    public IdleCommentVO(){

    }

    public IdleCommentVO(Integer id, Integer userId, UserVO user, Integer idleId, String content, Integer comments,
                         Date createTime, Date updateTime) {

        this.id = id;
        this.userId = userId;
        this.user = user;
        this.idleId = idleId;
        this.content = content;
        this.comments = comments;
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

    public Integer getIdleId() { return this.idleId; }

    public void setIdleId(Integer idleId) { this.idleId = idleId; }

    public String getContent() { return this.content; }

    public void setContent(String content) { this.content = content; }

    public Integer getComments() { return this.comments; }

    public void setComments(Integer comments) { this.comments = comments; }

    public Date getCreateTime() { return this.createTime; }

    public void setCreateTime(Date createTime) { this.createTime = createTime; }

    public Date getUpdateTime() { return this.updateTime; }

    public void setUpdateTime(Date updateTime) { this.updateTime = updateTime; }

    @Override
    public String toString() {
        return "IdleCommentVO{" +
                "id=" + id +
                ", userId=" + userId +
                ", user=" + user +
                ", idleId=" + idleId +
                ", content='" + content + '\'' +
                ", comments=" + comments +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
