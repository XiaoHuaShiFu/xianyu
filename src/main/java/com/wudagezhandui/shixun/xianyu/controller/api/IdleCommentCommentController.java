package com.wudagezhandui.shixun.xianyu.controller.api;

import com.github.pagehelper.PageInfo;
import com.wudagezhandui.shixun.xianyu.aspect.annotation.ErrorHandler;
import com.wudagezhandui.shixun.xianyu.pojo.do0.IdleCommentCommentDO;
import com.wudagezhandui.shixun.xianyu.pojo.do0.IdleCommentDO;
import com.wudagezhandui.shixun.xianyu.pojo.group.GroupPost;
import com.wudagezhandui.shixun.xianyu.pojo.query.IdleCommentCommentQuery;
import com.wudagezhandui.shixun.xianyu.pojo.query.IdleCommentQuery;
import com.wudagezhandui.shixun.xianyu.pojo.vo.IdleCommentCommentVO;
import com.wudagezhandui.shixun.xianyu.pojo.vo.IdleCommentVO;
import com.wudagezhandui.shixun.xianyu.result.Result;
import com.wudagezhandui.shixun.xianyu.service.IdleCommentCommentService;
import com.wudagezhandui.shixun.xianyu.service.IdleCommentService;
import com.wudagezhandui.shixun.xianyu.validator.annotation.Id;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("v1/commentcomments")
@Validated
public class IdleCommentCommentController {

    private final Mapper mapper;

    private final IdleCommentCommentService idleCommentCommentService;

    @Autowired
    public IdleCommentCommentController(Mapper mapper, IdleCommentCommentService idleCommentCommentService) {
        this.mapper = mapper;
        this.idleCommentCommentService = idleCommentCommentService;
    }

    @PostMapping("/test")
    @ResponseStatus(value = HttpStatus.CREATED)
    @ErrorHandler
    public Object post(@Validated(GroupPost.class) @RequestBody IdleCommentCommentDO idleCommentCommentDO) {
        Result<IdleCommentCommentDO> result = idleCommentCommentService.saveIdleCommentComment(idleCommentCommentDO);
        return !result.isSuccess() ? result : mapper.map(result.getData(), IdleCommentCommentVO.class);
    }

    @RequestMapping(value="/ID/{id}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ErrorHandler
    public Object get(@PathVariable @Id Integer id) {
        Result<IdleCommentCommentDO> result = idleCommentCommentService.getIdleCommentComment(id);
        return !result.isSuccess() ? result : mapper.map(result.getData(), IdleCommentCommentVO.class);
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ErrorHandler
    public Object get(IdleCommentCommentQuery query) {
        Result<PageInfo<IdleCommentCommentDO>> result = idleCommentCommentService.listIdleCommentComments(query);
        if (!result.isSuccess()) {
            return result;
        }

        PageInfo<IdleCommentCommentVO> pageInfo = mapper.map(result.getData(), PageInfo.class);
        pageInfo.setList(result.getData().getList().stream()
                .map(idleCommentCommentDO -> mapper.map(idleCommentCommentDO, IdleCommentCommentVO.class))
                .collect(Collectors.toList()));
        return pageInfo;
    }
}
