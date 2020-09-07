package com.wudagezhandui.shixun.xianyu.pojo.query;

import java.util.List;

public class IdleCommentCommentQuery {

    private Integer pageNum = 1;

    private Integer pageSize = 10;

    private Integer id;

    private Integer userId;

    private List<Integer> idList;

    private Integer idleCommentId;

    public IdleCommentCommentQuery() {

    }

    public IdleCommentCommentQuery(Integer pageNum, Integer pageSize, Integer id, List<Integer> idList, Integer userId, Integer idleCommentId) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.id = id;
        this.idList = idList;
        this.userId = userId;
        this.idleCommentId = idleCommentId;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() { return pageSize; }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Integer> getIdList() {
        return idList;
    }

    public void setIdList(List<Integer> idList) {
        this.idList = idList;
    }

    public Integer getUserId() { return userId; }

    public void setUserId(Integer userId) { this.userId = userId; }

    public Integer getIdleCommentId() {
        return idleCommentId;
    }

    public void setIdleCommentId(Integer idleCommentId) {
        this.idleCommentId = idleCommentId;
    }

    @Override
    public String toString() {
        return "ShareCommentQuery{" +
                "pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                ", id=" + id +
                ", idList=" + idList +
                ", userId=" + userId +
                ", idleCommentId=" + idleCommentId +
                '}';
    }
}
