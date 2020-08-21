package com.wudagezhandui.shixun.xianyu.pojo.vo;

import com.wudagezhandui.shixun.xianyu.constant.TokenType;

/**
 * 描述: Token对象
 *
 * @author xhsf
 * @email 827032783@qq.com
 * @create 2019-08-11 11:23
 */
public class TokenVO {

    private Integer id;

    private TokenType type;

    private String token;

    public TokenVO() {
    }

    public TokenVO(Integer id, TokenType type, String token) {
        this.id = id;
        this.type = type;
        this.token = token;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public TokenType getType() {
        return type;
    }

    public void setType(TokenType type) {
        this.type = type;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "TokenVO{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
