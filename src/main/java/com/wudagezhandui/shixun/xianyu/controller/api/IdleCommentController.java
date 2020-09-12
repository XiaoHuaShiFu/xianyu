package com.wudagezhandui.shixun.xianyu.controller.api;

import com.github.pagehelper.PageInfo;
import com.wudagezhandui.shixun.xianyu.aspect.annotation.ErrorHandler;
import com.wudagezhandui.shixun.xianyu.pojo.do0.IdleCommentDO;
import com.wudagezhandui.shixun.xianyu.pojo.do0.UserDO;
import com.wudagezhandui.shixun.xianyu.pojo.group.GroupPost;
import com.wudagezhandui.shixun.xianyu.pojo.query.IdleCommentQuery;
import com.wudagezhandui.shixun.xianyu.pojo.vo.IdleCommentVO;
import com.wudagezhandui.shixun.xianyu.pojo.vo.UserVO;
import com.wudagezhandui.shixun.xianyu.result.Result;
import com.wudagezhandui.shixun.xianyu.service.IdleCommentService;
import com.wudagezhandui.shixun.xianyu.service.UserService;
import com.wudagezhandui.shixun.xianyu.validator.annotation.Id;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("v1/comments")
@Validated
public class IdleCommentController {

    private final Mapper mapper;

    private final IdleCommentService idleCommentService;

    private final UserService userService;

    @Autowired
    public IdleCommentController(Mapper mapper, IdleCommentService idleCommentService, UserService userService) {
        this.mapper = mapper;
        this.idleCommentService = idleCommentService;
        this.userService = userService;
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    @ErrorHandler
    public Object post(@Validated(GroupPost.class) @RequestBody IdleCommentDO idleCommentDO) {
        Result<IdleCommentDO> result = idleCommentService.saveIdleComment(idleCommentDO);
        if (!result.isSuccess()) {
            return result;
        }

        IdleCommentVO idleCommentVO = mapper.map(result.getData(), IdleCommentVO.class);
        Result<UserDO> userDO = userService.getUser(idleCommentVO.getUserId());
        UserVO userVO = mapper.map(userDO.getData(), UserVO.class);
        idleCommentVO.setUser(userVO);
        return idleCommentVO;
    }

    @GetMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    @ErrorHandler
    @Deprecated
    public Object get(@PathVariable @Id Integer id) {
        Result<IdleCommentDO> result = idleCommentService.getIdleComment(id);
        return !result.isSuccess() ? result : mapper.map(result.getData(), IdleCommentVO.class);
    }

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    @ErrorHandler
    public Object get(IdleCommentQuery query) {
        Result<PageInfo<IdleCommentDO>> result = idleCommentService.listIdleComments(query);
        if (!result.isSuccess()) {
            return result;
        }

        PageInfo<IdleCommentVO> pageInfo = mapper.map(result.getData(), PageInfo.class);
        pageInfo.setList(result.getData().getList().stream()
                .map(idleCommentDO -> {
                    IdleCommentVO idleCommentVO = mapper.map(idleCommentDO, IdleCommentVO.class);
                    Result<UserDO> userDO = userService.getUser(idleCommentVO.getUserId());
                    UserVO userVO = mapper.map(userDO.getData(), UserVO.class);
                    idleCommentVO.setUser(userVO);
                    return idleCommentVO;})
                .collect(Collectors.toList()));
        return pageInfo;
    }
}
