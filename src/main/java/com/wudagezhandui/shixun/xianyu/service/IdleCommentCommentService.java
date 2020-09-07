package com.wudagezhandui.shixun.xianyu.service;

import com.github.pagehelper.PageInfo;
import com.wudagezhandui.shixun.xianyu.pojo.do0.IdleCommentCommentDO;
import com.wudagezhandui.shixun.xianyu.pojo.query.IdleCommentCommentQuery;
import com.wudagezhandui.shixun.xianyu.result.Result;

public interface IdleCommentCommentService {

    Result<IdleCommentCommentDO> saveIdleCommentComment(IdleCommentCommentDO idleCommentCommentDO);

    Result<IdleCommentCommentDO> getIdleCommentComment(Integer id);

    Result<PageInfo<IdleCommentCommentDO>> listIdleCommentComments(IdleCommentCommentQuery query);
}
