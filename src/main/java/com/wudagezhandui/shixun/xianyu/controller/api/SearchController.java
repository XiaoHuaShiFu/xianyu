package com.wudagezhandui.shixun.xianyu.controller.api;

import com.github.pagehelper.PageInfo;
import com.wudagezhandui.shixun.xianyu.aspect.annotation.ErrorHandler;
import com.wudagezhandui.shixun.xianyu.pojo.do0.IdleDO;
import com.wudagezhandui.shixun.xianyu.pojo.do0.UserDO;
import com.wudagezhandui.shixun.xianyu.pojo.query.IdleQuery;
import com.wudagezhandui.shixun.xianyu.pojo.query.UserQuery;
import com.wudagezhandui.shixun.xianyu.pojo.vo.IdleVO;
import com.wudagezhandui.shixun.xianyu.pojo.vo.UserVO;
import com.wudagezhandui.shixun.xianyu.result.Result;
import com.wudagezhandui.shixun.xianyu.service.IdleService;
import com.wudagezhandui.shixun.xianyu.service.UserService;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

/**
 * 描述：搜索模块
 *
 * @author xhsf
 * @create 2020年8月27日
 */

@RestController
@RequestMapping("v1/search")
@Validated
public class SearchController {

    private final Mapper mapper;

    private final IdleService idleService;

    private final UserService userService;

    @Autowired
    public SearchController(Mapper mapper, IdleService idleService, UserService userService) {
        this.mapper = mapper;
        this.idleService = idleService;
        this.userService = userService;
    }

    /**
     * 查询闲置
     * @param query 查询参数
     * @return IdleVOList
     *
     * @success:
     * HttpStatus.OK
     */
    @GetMapping("/idles")
    @ResponseStatus(value = HttpStatus.OK)
    @ErrorHandler
    public Object getIdles(IdleQuery query) {
        Result<PageInfo<IdleDO>> result = idleService.listIdles(query);
        if (!result.isSuccess()) {
            return result;
        }

        PageInfo<IdleVO> pageInfo = mapper.map(result.getData(), PageInfo.class);
        pageInfo.setList(result.getData().getList().stream()
                .map(idleDO -> mapper.map(idleDO, IdleVO.class))
                .collect(Collectors.toList()));
        return pageInfo;
    }

    /**
     * 查询用户
     * @param query 查询参数
     * @return UserVOList
     *
     * @success:
     * HttpStatus.OK
     */
    @GetMapping("/users")
    @ResponseStatus(value = HttpStatus.OK)
    @ErrorHandler
    public Object getUsers(UserQuery query) {
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


}
