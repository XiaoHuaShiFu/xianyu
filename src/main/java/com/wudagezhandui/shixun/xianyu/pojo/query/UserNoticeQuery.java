package com.wudagezhandui.shixun.xianyu.pojo.query;

import com.wudagezhandui.shixun.xianyu.constant.UserNoticeType;

import java.util.List;

/**
 * 描述:
 *
 * @author xhsf
 * @email 827032783@qq.com
 */
public class UserNoticeQuery {

    private Integer pageNum = 1;

    private Integer pageSize = 10;

    private Integer id;

    private List<Integer> idList;

    private Integer userId;

    private UserNoticeType type;

    /**
     * 模糊搜素
     */
    private String content;

    /**
     * 是否是通过count搜索
     */
    private Boolean count;

    public UserNoticeQuery() {
    }

    public UserNoticeQuery(Integer pageNum, Integer pageSize, Integer id, List<Integer> idList, Integer userId,
                           UserNoticeType type, String content, Boolean count) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.id = id;
        this.idList = idList;
        this.userId = userId;
        this.type = type;
        this.content = content;
        this.count = count;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public UserNoticeType getType() {
        return type;
    }

    public void setType(UserNoticeType type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Boolean getCount() {
        return count;
    }

    public void setCount(Boolean count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "UserNoticeQuery{" +
                "pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                ", id=" + id +
                ", idList=" + idList +
                ", userId=" + userId +
                ", type=" + type +
                ", content='" + content + '\'' +
                ", count=" + count +
                '}';
    }
}
