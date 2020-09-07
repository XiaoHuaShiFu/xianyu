package com.wudagezhandui.shixun.xianyu.dao;

import com.wudagezhandui.shixun.xianyu.pojo.do0.IdleCommentCommentDO;
import com.wudagezhandui.shixun.xianyu.pojo.query.IdleCommentCommentQuery;
import com.wudagezhandui.shixun.xianyu.pojo.query.IdleCommentQuery;
import org.apache.ibatis.annotations.Mapper;
import com.wudagezhandui.shixun.xianyu.pojo.do0.IdleCommentDO;

import java.util.List;

@Mapper
public interface IdleCommentCommentMapper {
    /*
     * 保存评论
     * @param idleCommentDO 二级评论
     * @ return
     */
    int saveIdleCommentComment(IdleCommentCommentDO idleCommentCommentDO);

    /*
     * 获取分享的评论
     * @param id评论的编号
     * @return IdleCommentDO
     */
    IdleCommentCommentDO getIdleCommentComment(Integer id);

    /*
     * 获取query过滤参数后的评论列表
     * @param query 队列
     * @return 消息队列
     */
    List<IdleCommentCommentDO> listIdleCommentComments(IdleCommentCommentQuery query);


}
