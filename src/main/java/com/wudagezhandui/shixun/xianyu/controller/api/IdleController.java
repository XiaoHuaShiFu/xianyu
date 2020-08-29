package com.wudagezhandui.shixun.xianyu.controller.api;

import com.github.pagehelper.PageInfo;
import com.wudagezhandui.shixun.xianyu.aspect.annotation.ErrorHandler;
import com.wudagezhandui.shixun.xianyu.auth.TokenAuth;
import com.wudagezhandui.shixun.xianyu.constant.TokenType;
import com.wudagezhandui.shixun.xianyu.pojo.ao.TokenAO;
import com.wudagezhandui.shixun.xianyu.pojo.do0.IdleDO;
import com.wudagezhandui.shixun.xianyu.pojo.do0.UserDO;
import com.wudagezhandui.shixun.xianyu.pojo.group.Group;
import com.wudagezhandui.shixun.xianyu.pojo.group.GroupPost;
import com.wudagezhandui.shixun.xianyu.pojo.query.IdleQuery;
import com.wudagezhandui.shixun.xianyu.pojo.query.UserQuery;
import com.wudagezhandui.shixun.xianyu.pojo.vo.IdleVO;
import com.wudagezhandui.shixun.xianyu.pojo.vo.UserVO;
import com.wudagezhandui.shixun.xianyu.result.ErrorCode;
import com.wudagezhandui.shixun.xianyu.result.Result;
import com.wudagezhandui.shixun.xianyu.service.IdleService;
import com.wudagezhandui.shixun.xianyu.validator.annotation.Id;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

/**
 * 描述:闲置物品模块
 *
 * @author tsukikoillya
 * @create 2020年8月25日16:34:52
 */

@RestController
@RequestMapping("v1/idles")
@Validated
public class IdleController {

    private final Mapper mapper;

    private final IdleService idleService;

    @Autowired
    public IdleController(Mapper mapper, IdleService idleService) {
        this.mapper = mapper;
        this.idleService = idleService;
    }

    /**
     * 创建Idle并返回Idle
     * @param Idle 物品信息
     * @return IdleVO
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
    public Object post(@Validated(GroupPost.class) @RequestBody IdleDO Idle) {
        //System.out.println(Idle.getUser_id());
        Result<IdleDO> result = idleService.saveIdle(Idle);
        return !result.isSuccess() ? result : mapper.map(result.getData(), IdleVO.class);
    }

    /**
     * 获取物品
     * @param id 物品编号
     * @return IdleVO
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
    @RequestMapping(value="/ID/{id}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ErrorHandler
    public Object get(@PathVariable @Id Integer id) {
        Result<IdleDO> result = idleService.getIdle(id);
        return !result.isSuccess() ? result : mapper.map(result.getData(), IdleVO.class);
    }

    /**
     * 获取物品
     * @param name 物品名称
     * @return IdleVO
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
    @RequestMapping(value="/Name/{name}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ErrorHandler
    public Object get(@PathVariable  String name) {
        Result<IdleDO> result = idleService.getIdleByName(name);
        return !result.isSuccess() ? result : mapper.map(result.getData(), IdleVO.class);
    }

    /**
     * 查询物品
     * @param query 查询参数
     * @return IdleVOList
     *
     * @success:
     * HttpStatus.OK
     */
    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ErrorHandler
    public Object get(IdleQuery query) {
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
     * 更新Idle并返回Idle
     * @param tokenAO TokenAO
     * @param idle idle信息
     * @return IdleVO
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
    public Object put(TokenAO tokenAO, @Validated(Group.class) @RequestBody IdleDO idle) {
        if (!idle.getUser_id().equals(tokenAO.getId())) {
            return Result.fail(ErrorCode.FORBIDDEN_SUB_USER);
        }
        Result<IdleDO> result = idleService.updateIdle(idle);

        return !result.isSuccess() ? result : mapper.map(result.getData(), IdleVO.class);
    }
}
