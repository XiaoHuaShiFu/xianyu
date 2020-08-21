package com.wudagezhandui.shixun.xianyu.service;


import com.wudagezhandui.shixun.xianyu.constant.TokenType;
import com.wudagezhandui.shixun.xianyu.pojo.ao.TokenAO;
import com.wudagezhandui.shixun.xianyu.result.Result;

/**
 * 描述:
 *
 * @author xhsf
 * @email 827032783@qq.com
 */
public interface TokenService {

    Result<TokenAO> saveToken(TokenAO tokenAO);

    Result<TokenAO> createAndSaveToken(TokenType tokenType, String username, String password);

    Result<TokenAO> getToken(String token);

    Result<TokenAO> getTokenAndAuthTokenTypeAndUpdateExpire(String token, TokenType[] tokenTypes, int seconds);

}
