package com.wudagezhandui.shixun.xianyu.service.impl;

import com.google.gson.Gson;
import com.wudagezhandui.shixun.xianyu.constant.TokenExpire;
import com.wudagezhandui.shixun.xianyu.constant.TokenType;
import com.wudagezhandui.shixun.xianyu.pojo.ao.TokenAO;
import com.wudagezhandui.shixun.xianyu.pojo.do0.UserDO;
import com.wudagezhandui.shixun.xianyu.result.ErrorCode;
import com.wudagezhandui.shixun.xianyu.result.Result;
import com.wudagezhandui.shixun.xianyu.service.CacheService;
import com.wudagezhandui.shixun.xianyu.service.TokenService;
import com.wudagezhandui.shixun.xianyu.service.UserService;
import com.wudagezhandui.shixun.xianyu.service.constant.RedisStatus;
import com.wudagezhandui.shixun.xianyu.service.constant.TokenConstant;
import com.wudagezhandui.shixun.xianyu.util.SHA256;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.UUID;

/**
 * 描述: Token相关服务
 *
 * @author xhsf
 * @email 827032783@qq.com
 */
@Service("tokenService")
public class TokenServiceImpl implements TokenService {

    private static final Logger logger = LoggerFactory.getLogger(TokenServiceImpl.class);

    private final CacheService cacheService;

    private final UserService userService;

    private final Gson gson;

    @Autowired
    public TokenServiceImpl(CacheService cacheService, UserService userService, Gson gson) {
        this.cacheService = cacheService;
        this.userService = userService;
        this.gson = gson;
    }

    /**
     * 在缓存里添加token，并设置过期时间
     *
     * @param tokenAO TokenAO
     * @return Result<TokenAO>
     */
    @Override
    public Result<TokenAO> saveToken(TokenAO tokenAO) {
        // 移除旧Token
        findAndRemoveOldToken(tokenAO);

        // 保存TokenAO，该token以id作为key
        String keyOfId = MessageFormat.format(TokenConstant.PREFIX_OF_ID_TOKEN_FOR_REDIS_KEY,
                tokenAO.getType(), tokenAO.getId());
        String code = cacheService.set(keyOfId, gson.toJson(tokenAO));
        // 保存失败
        if (!code.equals(RedisStatus.OK.name())) {
            logger.error("Failed to create token, key: {}, type: {} and id: {}",
                    keyOfId, tokenAO.getToken(), tokenAO.getId());
            return Result.fail(ErrorCode.INTERNAL_ERROR, "Failed to create token.");
        }
        // 设置过期时间
        Long result = cacheService.expire(keyOfId, TokenExpire.NORMAL.getExpire());
        if (result.equals(0L)) {
            cacheService.del(tokenAO.getToken());
            logger.error("Failed to set expire, key: {}, type: {} and id: {}",
                    keyOfId, tokenAO.getToken(), tokenAO.getId());
            return Result.fail(ErrorCode.INTERNAL_ERROR, "Failed to set expire.");
        }

        // 保存TokenAO，该token以token作为key
        String keyOfToken = MessageFormat.format(TokenConstant.PREFIX_OF_TOKEN_FOR_REDIS_KEY, tokenAO.getToken());
        code = cacheService.set(keyOfToken, gson.toJson(tokenAO));
        // 保存失败
        if (!code.equals(RedisStatus.OK.name())) {
            logger.error("Failed to create token, key: {}, type: {} and id: {}",
                    keyOfToken, tokenAO.getToken(), tokenAO.getId());
            return Result.fail(ErrorCode.INTERNAL_ERROR, "Failed to create token.");
        }
        // 设置过期时间
        result = cacheService.expire(keyOfToken, TokenExpire.NORMAL.getExpire());
        if (result.equals(0L)) {
            cacheService.del(tokenAO.getToken());
            logger.error("Failed to set expire, key: {}, type: {} and id: {}",
                    keyOfToken, tokenAO.getToken(), tokenAO.getId());
            return Result.fail(ErrorCode.INTERNAL_ERROR, "Failed to set expire.");
        }

        return Result.success(tokenAO);
    }

    /**
     * 创建token并保存到redis里
     *
     * @param username 用户名
     * @param password 密码
     * @param tokenType token类型
     * @return Result<TokenAO>
     */
    @Override
    public Result<TokenAO> createAndSaveToken(TokenType tokenType, String username, String password) {
        TokenAO tokenAO = new TokenAO();
        if (tokenType == TokenType.USER) {
            Result<UserDO> result = userService.getUserByUsername(username);
            if (!result.isSuccess()) {
                return Result.fail(result.getErrorCode(), result.getMessage());
            }

            UserDO user = result.getData();
            if (!user.getPassword().equals(password)) {
                return Result.fail(ErrorCode.UNAUTHORIZED, "Wrong password.");
            }

            tokenAO.setId(user.getId());
            tokenAO.setType(tokenType);
        }

        // 如果旧token存在，则直接获取旧token，并更新过期时间
        Result<TokenAO> getTokenResult = getTokenByIdAndTokenTypeAndUpdateExpire(tokenAO);
        if (getTokenResult.isSuccess()) {
            return getTokenResult;
        }
        String token = createToken();
        tokenAO.setToken(token);
        return saveToken(tokenAO);
    }

    /**
     * 获取token
     *
     * @param token token
     * @return Result<TokenAO>
     */
    @Override
    public Result<TokenAO> getToken(String token) {
        String keyOfToken = MessageFormat.format(TokenConstant.PREFIX_OF_TOKEN_FOR_REDIS_KEY, token);
        String jsonToken = cacheService.get(keyOfToken);
        // token不在
        if (jsonToken == null) {
            return Result.fail(ErrorCode.UNAUTHORIZED);
        }

        return Result.success(gson.fromJson(jsonToken, TokenAO.class));
    }

    /**
     * 获取token并认证是不是可以通过认证的类型并更新过期时间
     *
     * @param token 要认证类型
     * @param tokenTypes 可以通过认证的列表
     * @param seconds 过期时间长度
     *
     * @return Result<TokenAO>
     */
    @Override
    public Result<TokenAO> getTokenAndAuthTokenTypeAndUpdateExpire(String token, TokenType[] tokenTypes, int seconds) {
        Result<TokenAO> result = getToken(token);
        if (!result.isSuccess()) {
            return Result.fail(result.getErrorCode(), result.getMessage());
        }

        // 不是所需的token类型
        TokenAO tokenAO = result.getData();
        if (!authTokenType(tokenAO.getType(), tokenTypes)) {
            return Result.fail(ErrorCode.FORBIDDEN_SUB_USER);
        }

        // 更新token所对应key的TokenAO过期时间
        String keyOfToken = MessageFormat.format(TokenConstant.PREFIX_OF_TOKEN_FOR_REDIS_KEY, token);
        Long expire = cacheService.expire(keyOfToken, seconds);
        if (expire == 0) {
            logger.warn("Set redis expire fail, key: {}, token: {} id: {} type: {}",
                    keyOfToken, tokenAO.getToken(), tokenAO.getId(), tokenAO.getType());
        }
        // 更新id所对应key的TokenAO过期时间
        String keyOfId = MessageFormat.format(TokenConstant.PREFIX_OF_ID_TOKEN_FOR_REDIS_KEY,
                tokenAO.getType(), tokenAO.getId());
        expire = cacheService.expire(keyOfId, seconds);
        if (expire == 0) {
            logger.warn("Set redis expire fail, key: {}, token: {} id: {} type: {}",
                    keyOfId, tokenAO.getToken(), tokenAO.getId(), tokenAO.getType());
        }

        return result;
    }

    /**
     * 获取token通过id和TokenType
     * @param tokenAO 用于查询token
     * @return 获取结果
     */
    private Result<TokenAO> getTokenByIdAndTokenTypeAndUpdateExpire(TokenAO tokenAO) {
        String keyOfId = MessageFormat.format(TokenConstant.PREFIX_OF_ID_TOKEN_FOR_REDIS_KEY,
                tokenAO.getType(), tokenAO.getId());
        String tokenById = cacheService.get(keyOfId);
        // 如果Token不存在则返回失败删除处理
        if (tokenById == null) {
            return Result.fail(ErrorCode.INVALID_PARAMETER_NOT_FOUND, "The token not exists.");
        }
        TokenAO oldTokenAO = gson.fromJson(tokenById, TokenAO.class);
        String keyOfToken = MessageFormat.format(TokenConstant.PREFIX_OF_TOKEN_FOR_REDIS_KEY, oldTokenAO.getToken());

        // 更新token所对应key的TokenAO过期时间
        Long expire = cacheService.expire(keyOfToken, TokenExpire.NORMAL.getExpire());
        if (expire == 0) {
            logger.warn("Set redis expire fail, key: {}, token: {} id: {} type: {}",
                    keyOfToken, tokenAO.getToken(), tokenAO.getId(), tokenAO.getType());
        }
        // 更新id所对应key的TokenAO过期时间
        expire = cacheService.expire(keyOfId, TokenExpire.NORMAL.getExpire());
        if (expire == 0) {
            logger.warn("Set redis expire fail, key: {}, token: {} id: {} type: {}",
                    keyOfId, tokenAO.getToken(), tokenAO.getId(), tokenAO.getType());
        }
        return Result.success(oldTokenAO);
    }

    /**
     * 查找并移除旧的Token
     * 1.通过ID查找TokenAO
     * 2.如果存在则通过Token查找TokenAO
     * 3.移除ID和Token所对应的Token
     */
    private void findAndRemoveOldToken(TokenAO tokenAO) {
        String keyOfId = MessageFormat.format(TokenConstant.PREFIX_OF_ID_TOKEN_FOR_REDIS_KEY,
                tokenAO.getType(), tokenAO.getId());
        String tokenById = cacheService.get(keyOfId);
        // 如果Token存在则进行删除处理
        if (tokenById != null) {
            TokenAO oldTokenAO = gson.fromJson(tokenById, TokenAO.class);
            String keyOfToken = MessageFormat.format(TokenConstant.PREFIX_OF_TOKEN_FOR_REDIS_KEY, oldTokenAO.getToken());
            cacheService.del(keyOfId);
            cacheService.del(keyOfToken);
        }
    }

    /**
     * 认证tokenType
     *
     * @param type 要认证类型
     * @param tokenTypes 可以通过认证的列表
     * @return 是否认证成功
     */
    private boolean authTokenType(TokenType type, TokenType[] tokenTypes) {
        if (tokenTypes.length == 0) {
            return true;
        }
        for (TokenType tokenType : tokenTypes) {
            if (tokenType == type) {
                return true;
            }
        }
        return false;
    }

    /**
     * 创建token
     *
     * @return token
     */
    private String createToken() {
        return SHA256.encryption(UUID.randomUUID().toString());
    }

}
