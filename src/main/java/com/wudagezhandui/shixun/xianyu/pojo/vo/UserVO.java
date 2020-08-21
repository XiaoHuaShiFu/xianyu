package com.wudagezhandui.shixun.xianyu.pojo.vo;

/**
 * 描述:
 *
 * @author xhsf
 * @email 827032783@qq.com
 * @create 2020-03-24 0:41
 */
public class UserVO {
    private Integer id;

    private String username;

    private String password;

    private String nickName;

    private String avatarUrl;

    private String aliPayAccount;

    public UserVO() {
    }

    public UserVO(Integer id, String username, String password, String nickName, String avatarUrl,
                  String aliPayAccount) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.nickName = nickName;
        this.avatarUrl = avatarUrl;
        this.aliPayAccount = aliPayAccount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getAliPayAccount() {
        return aliPayAccount;
    }

    public void setAliPayAccount(String aliPayAccount) {
        this.aliPayAccount = aliPayAccount;
    }

    @Override
    public String toString() {
        return "UserVO{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", nickName='" + nickName + '\'' +
                ", avatarUrl='" + avatarUrl + '\'' +
                ", aliPayAccount='" + aliPayAccount + '\'' +
                '}';
    }
}
