package com.wudagezhandui.shixun.xianyu.pojo.do0;


import com.wudagezhandui.shixun.xianyu.pojo.group.Group;
import com.wudagezhandui.shixun.xianyu.pojo.group.GroupPost;
import com.wudagezhandui.shixun.xianyu.validator.annotation.Id;
import com.wudagezhandui.shixun.xianyu.validator.annotation.Password;
import com.wudagezhandui.shixun.xianyu.validator.annotation.Url;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * 描述:
 *
 * @author xhsf
 * @email 827032783@qq.com
 * @create 2020-03-25 18:37
 */
public class UserDO {

    @Id(groups = {Group.class})
    private Integer id;

    @NotBlank(message = "INVALID_PARAMETER_IS_BLANK: The username must be not blank.",
            groups = {GroupPost.class})
    @Size(message = "INVALID_PARAMETER_SIZE: The size of username must be between 1 to 20.",
            min = 1, max = 20,
            groups = {Group.class})
    private String username;

    @NotBlank(message = "INVALID_PARAMETER_IS_BLANK: The password must be not blank.",
            groups = {GroupPost.class})
    @Password(groups = {Group.class})
    private String password;

    @Size(message = "INVALID_PARAMETER_SIZE: The size of nickName must be between 1 to 20.",
            min = 1, max = 20,
            groups = {Group.class})
    private String nickName;

    @Url(groups = {Group.class})
    private String avatarUrl;

    private String aliPayAccount;

    private Date createTime;

    private Date updateTime;

    public UserDO() {
    }

    public UserDO(Integer id, String password, String nickName, String avatarUrl, String aliPayAccount,
                  Date createTime, Date updateTime) {
        this.id = id;
        this.aliPayAccount = aliPayAccount;
        this.password = password;
        this.nickName = nickName;
        this.avatarUrl = avatarUrl;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAliPayAccount() {
        return aliPayAccount;
    }

    public void setAliPayAccount(String aliPayAccount) {
        this.aliPayAccount = aliPayAccount;
    }

    @Override
    public String toString() {
        return "UserDO{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", nickName='" + nickName + '\'' +
                ", avatarUrl='" + avatarUrl + '\'' +
                ", aliPayAccount='" + aliPayAccount + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
