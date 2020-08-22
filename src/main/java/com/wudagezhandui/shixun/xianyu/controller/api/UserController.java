package com.wudagezhandui.shixun.xianyu.controller.api;

import com.github.pagehelper.PageInfo;
import com.wudagezhandui.shixun.xianyu.aspect.annotation.ErrorHandler;
import com.wudagezhandui.shixun.xianyu.auth.TokenAuth;
import com.wudagezhandui.shixun.xianyu.constant.TokenType;
import com.wudagezhandui.shixun.xianyu.pojo.ao.TokenAO;
import com.wudagezhandui.shixun.xianyu.pojo.do0.UserDO;
import com.wudagezhandui.shixun.xianyu.pojo.group.Group;
import com.wudagezhandui.shixun.xianyu.pojo.group.GroupPost;
import com.wudagezhandui.shixun.xianyu.pojo.query.UserQuery;
import com.wudagezhandui.shixun.xianyu.pojo.vo.UserVO;
import com.wudagezhandui.shixun.xianyu.result.ErrorCode;
import com.wudagezhandui.shixun.xianyu.result.Result;
import com.wudagezhandui.shixun.xianyu.service.UserService;
import com.wudagezhandui.shixun.xianyu.validator.annotation.Id;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.util.stream.Collectors;

/**
 * 描述: 用户模块
 *
 * @author xhsf
 * @email 827032783@qq.com
 * @create 2020-03-16 23:55
 */
@RestController
@RequestMapping("v1/users")
@Validated
public class UserController {

    private final Mapper mapper;
    
    private final UserService userService;

    @Autowired
    public UserController(Mapper mapper, UserService userService) {
        this.mapper = mapper;
        this.userService = userService;
    }

    /**
     * 创建User并返回User
     * @param userDO 用户信息
     * @return UserVO
     *
     * @success:
     * HttpStatus.CREATED
     *
     * @bindErrors
     * INVALID_PARAMETER
     * INVALID_PARAMETER_IS_NULL
     * INVALID_PARAMETER_IS_BLANK
     * INVALID_PARAMETER_SIZE
     * INVALID_PARAMETER_VALUE_BELOW
     */
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    @ErrorHandler
    public Object post(@Validated(GroupPost.class) @RequestBody UserDO userDO) {
        Result<UserDO> result = userService.saveUser(userDO);
        return !result.isSuccess() ? result : mapper.map(result.getData(), UserVO.class);
    }

    /**
     * 获取user
     * @param id 用户编号
     * @return UserVO
     *
     * @success:
     * HttpStatus.OK
     *
     * @errors:
     * FORBIDDEN_SUB_USER
     *
     * @bindErrors
     * INVALID_PARAMETER_VALUE_BELOW: The name of id below, min: 0.
     */
    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @TokenAuth(tokenType = TokenType.USER)
    @ErrorHandler
    public Object get(@PathVariable @Id Integer id) {
        Result<UserDO> result = userService.getUser(id);
        return !result.isSuccess() ? result : mapper.map(result.getData(), UserVO.class);
    }

    /**
     * 查询user
     * @param query 查询参数
     * @return UserVOList
     *
     * @success:
     * HttpStatus.OK
     */
    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @TokenAuth(tokenType = TokenType.USER)
    @ErrorHandler
    public Object get(UserQuery query) {
        Result<PageInfo<UserDO>> result = userService.listUsers(query);
        if (!result.isSuccess()) {
            return result;
        }

        PageInfo<UserVO> pageInfo = mapper.map(result.getData(), PageInfo.class);
        pageInfo.setList(result.getData().getList().stream()
                .map(userDO -> mapper.map(userDO, UserVO.class))
                .collect(Collectors.toList()));
        return pageInfo;
    }

    /**
     * 更新User并返回User
     * @param tokenAO TokenAO
     * @param userDO User信息
     * @return UserVO
     *
     * @success:
     * HttpStatus.OK
     *
     * @errors:
     *
     * @bindErrors
     * INVALID_PARAMETER
     * INVALID_PARAMETER_IS_NULL
     * INVALID_PARAMETER_IS_BLANK
     * INVALID_PARAMETER_SIZE
     * INVALID_PARAMETER_VALUE_BELOW
     */
    @RequestMapping(method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK)
    @TokenAuth(tokenType = TokenType.USER)
    @ErrorHandler
    public Object put(TokenAO tokenAO, @Validated(Group.class) @RequestBody UserDO userDO) {
        if (!userDO.getId().equals(tokenAO.getId())) {
            return Result.fail(ErrorCode.FORBIDDEN_SUB_USER);
        }
        Result<UserDO> result = userService.updateUser(userDO);

        return !result.isSuccess() ? result : mapper.map(result.getData(), UserVO.class);
    }

    /**
     * 修改头像
     *
     * @param tokenAO TokenAO
     * @param avatar MultipartFile
     * @return UserVO
     *
     * @success:
     * HttpStatus.OK
     *
     * @errors:
     * INTERNAL_ERROR: Upload file failed.
     * INTERNAL_ERROR: Delete file failed.
     * INTERNAL_ERROR: Update avatar exception.
     *
     * @bindErrors
     * INVALID_PARAMETER_IS_NULL
     */
    @RequestMapping(value="/avatars", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK)
    @TokenAuth(tokenType = TokenType.USER)
    @ErrorHandler
    public Object putAvatar(
            TokenAO tokenAO,
            @NotNull(message = "INVALID_PARAMETER_IS_BLANK: The id must be not blank.") @Id Integer id,
            @NotNull(message = "INVALID_PARAMETER_IS_NULL: The required avatar must be not null.") MultipartFile avatar) {
        if (!tokenAO.getId().equals(id)) {
            return Result.fail(ErrorCode.FORBIDDEN_SUB_USER);
        }
        Result<UserDO> result = userService.updateAvatar(tokenAO.getId(), avatar);

        return result.isSuccess() ? mapper.map(result.getData(), UserVO.class) : result;
    }

}
