package com.wudagezhandui.shixun.xianyu.pojo.do0;

import com.wudagezhandui.shixun.xianyu.constant.UserNoticeType;
import com.wudagezhandui.shixun.xianyu.pojo.group.Group;
import com.wudagezhandui.shixun.xianyu.pojo.group.GroupPost;
import com.wudagezhandui.shixun.xianyu.pojo.group.GroupPut;
import com.wudagezhandui.shixun.xianyu.validator.annotation.Id;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * 描述:
 *
 * @author xhsf
 * @email 827032783@qq.com
 */
public class UserNoticeDO {

    @Null(message = "INVALID_PARAMETER: The id must be null.",
            groups = {GroupPost.class})
    @Id(groups = {Group.class})
    private Integer id;

    @NotNull(message = "INVALID_PARAMETER_IS_NULL: The userId must be not null.",
            groups = {GroupPut.class})
    @Id(groups = {Group.class})
    private Integer userId;

    @NotNull(message = "INVALID_PARAMETER_IS_NULL: The type must be not null.",
            groups = {GroupPut.class})
    private UserNoticeType type;

    @NotBlank(message = "INVALID_PARAMETER_IS_BLANK: The title must be not blank.",
            groups = {GroupPost.class})
    @Size(message = "INVALID_PARAMETER_SIZE: The size of title must be between 1 to 40.",
            min = 1, max = 40,
            groups = {Group.class})
    private String title;

    @NotBlank(message = "INVALID_PARAMETER_IS_BLANK: The content must be not blank.",
            groups = {GroupPost.class})
    @Size(message = "INVALID_PARAMETER_SIZE: The size of content must be between 1 to 100.",
            min = 1, max = 100,
            groups = {Group.class})
    private String content;

    private String keyValue;

    @NotNull(message = "INVALID_PARAMETER_IS_NULL: The noticeTime must be not null.",
            groups = {GroupPut.class})
    private Date noticeTime;

    @Null(message = "INVALID_PARAMETER: The createTime must be null.",
            groups = {GroupPost.class, GroupPut.class})
    private Date createTime;

    @Null(message = "INVALID_PARAMETER: The createTime must be null.",
            groups = {GroupPost.class, GroupPut.class})
    private Date updateTime;

    public UserNoticeDO() {
    }

    public UserNoticeDO(Integer id, Integer userId, UserNoticeType type, String title,String content, String keyValue,
                        Date noticeTime, Date createTime, Date updateTime) {
        this.id = id;
        this.userId = userId;
        this.type = type;
        this.title = title;
        this.content = content;
        this.keyValue = keyValue;
        this.noticeTime = noticeTime;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getKeyValue() {
        return keyValue;
    }

    public void setKeyValue(String keyValue) {
        this.keyValue = keyValue;
    }

    public Date getNoticeTime() {
        return noticeTime;
    }

    public void setNoticeTime(Date noticeTime) {
        this.noticeTime = noticeTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "UserNoticeDO{" +
                "id=" + id +
                ", userId=" + userId +
                ", type=" + type +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", keyValue='" + keyValue + '\'' +
                ", noticeTime=" + noticeTime +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
