package com.wudagezhandui.shixun.xianyu.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wudagezhandui.shixun.xianyu.dao.IdleCommentCommentMapper;
import com.wudagezhandui.shixun.xianyu.pojo.do0.IdleCommentCommentDO;
import com.wudagezhandui.shixun.xianyu.pojo.do0.IdleCommentDO;
import com.wudagezhandui.shixun.xianyu.pojo.query.IdleCommentCommentQuery;
import com.wudagezhandui.shixun.xianyu.result.ErrorCode;
import com.wudagezhandui.shixun.xianyu.result.Result;
import com.wudagezhandui.shixun.xianyu.service.IdleCommentCommentService;
import com.wudagezhandui.shixun.xianyu.service.IdleCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service("IdleCommentCommentService")
public class IdleCommentCommentServiceImpl implements IdleCommentCommentService {

    private final IdleCommentCommentMapper idleCommentCommentMapper;

    @Autowired
    private final IdleCommentService idleCommentService;

    @Autowired
    public IdleCommentCommentServiceImpl(IdleCommentCommentMapper idleCommentCommentMapper, IdleCommentService idleCommentService) {
        this.idleCommentCommentMapper = idleCommentCommentMapper;
        this.idleCommentService = idleCommentService;
    }

    @Override
    public Result<IdleCommentCommentDO> saveIdleCommentComment(IdleCommentCommentDO idleCommentCommentDO) {
        //判断一级评论是否存在
        Result<IdleCommentDO> getIdleCommentResult = idleCommentService.getIdleComment(idleCommentCommentDO.getIdleCommentId());
        if(!getIdleCommentResult.isSuccess()) {
            return Result.fail(ErrorCode.INVALID_PARAMETER_NOT_FOUND,
                    "The comment of id={0} not exists.", idleCommentCommentDO.getId());
        }

        //保存闲置商品的评论
        idleCommentCommentDO.setCreateTime(new Date());
        int count = idleCommentCommentMapper.saveIdleCommentComment(idleCommentCommentDO);
        //如果没有插入成功
        if(count < 1) {
            return Result.fail(ErrorCode.INTERNAL_ERROR, "Insert idleCommentComment fail.");
        }

        return getIdleCommentComment(idleCommentCommentDO.getId());
    }

    @Override
    public Result<IdleCommentCommentDO> getIdleCommentComment(Integer id) {

        IdleCommentCommentDO idleCommentCommentDO = idleCommentCommentMapper.getIdleCommentComment(id);
        if(idleCommentCommentDO == null) {
            return Result.fail(ErrorCode.INVALID_PARAMETER_NOT_FOUND, "The specified id does not exist.");
        }
        return Result.success(idleCommentCommentDO);

    }

    @Override
    public Result<PageInfo<IdleCommentCommentDO>> listIdleCommentComments(IdleCommentCommentQuery query) {
        PageHelper.startPage(query.getPageNum(), query.getPageSize());
        PageInfo<IdleCommentCommentDO> pageInfo = new PageInfo<>(idleCommentCommentMapper.listIdleCommentComments(query));
        if (pageInfo.getList().size() < 1) {
            Result.fail(ErrorCode.INVALID_PARAMETER_NOT_FOUND, "Not found.");
        }

        return Result.success(pageInfo);
    }
}

