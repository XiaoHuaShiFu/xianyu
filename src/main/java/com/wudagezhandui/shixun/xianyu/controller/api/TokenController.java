package com.wudagezhandui.shixun.xianyu.controller.api;

import com.wudagezhandui.shixun.xianyu.aspect.annotation.ErrorHandler;
import com.wudagezhandui.shixun.xianyu.constant.TokenType;
import com.wudagezhandui.shixun.xianyu.pojo.ao.TokenAO;
import com.wudagezhandui.shixun.xianyu.pojo.vo.TokenVO;
import com.wudagezhandui.shixun.xianyu.result.Result;
import com.wudagezhandui.shixun.xianyu.service.TokenService;
import com.wudagezhandui.shixun.xianyu.validator.annotation.Password;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


/**
 * 描述: Token Web层
 *
 * @author xhsf
 * @email 827032783@qq.com
 */
@RestController
@RequestMapping("v1/tokens")
@Validated
public class TokenController {

    private final Mapper mapper;

    private final TokenService tokenService;

    @Autowired
    public TokenController(Mapper mapper, TokenService tokenService) {
        this.mapper = mapper;
        this.tokenService = tokenService;
    }

    /**
     * 创建token凭证，通过用户名+密码
    *
     * @param username 用户名
     * @param password 密码
     * @param tokenType token类型
     * @return TokenAO
     *
     * @success:
     * HttpStatus.CREATED
     *
     * @errors:
     * INVALID_PARAMETER: The code is not valid.
     * INVALID_PARAMETER_NOT_FOUND: The specified openid does not exist.
     *
     * INTERNAL_ERROR: Failed to create token.
     * INTERNAL_ERROR: Failed to set expire.
     *
     * @bindErrors
     * INVALID_PARAMETER_IS_BLANK
     * INVALID_PARAMETER_SIZE
     */
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    @ErrorHandler
    public Object post(
            @NotBlank(message = "INVALID_PARAMETER_IS_BLANK: The username must be not blank.") String username,
            @NotBlank(message = "INVALID_PARAMETER_IS_BLANK: The password must be not blank.")
            @Password String password,
            @NotNull(message = "INVALID_PARAMETER_IS_NULL: The tokenType must be not null.") TokenType tokenType) {
        Result<TokenAO> result = tokenService.createAndSaveToken(tokenType, username, password);

        return result.isSuccess() ? mapper.map(result.getData(), TokenVO.class) : result;
    }

}