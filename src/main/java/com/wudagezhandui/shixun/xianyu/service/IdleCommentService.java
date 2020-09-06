package com.wudagezhandui.shixun.xianyu.service;

import com.github.pagehelper.PageInfo;
import com.wudagezhandui.shixun.xianyu.pojo.do0.IdleCommentDO;
import com.wudagezhandui.shixun.xianyu.pojo.query.IdleCommentQuery;
import com.wudagezhandui.shixun.xianyu.result.Result;

public interface IdleCommentService {

    Result<IdleCommentDO> saveIdleComment(IdleCommentDO idleCommentDO);

    Result<IdleCommentDO> getIdleComment(Integer id);

    Result<PageInfo<IdleCommentDO>> listIdleComments(IdleCommentQuery query);

}
