package com.wudagezhandui.shixun.xianyu.dao;

import com.wudagezhandui.shixun.xianyu.pojo.query.IdleCommentQuery;
import org.apache.ibatis.annotations.Mapper;
import com.wudagezhandui.shixun.xianyu.pojo.do0.IdleCommentDO;

import java.util.List;

@Mapper
public interface IdleCommentMapper {
    /**
     * 保存评论
     * @param idleCommentDO 一级评论
     * @ return
     */
    int saveIdleComment(IdleCommentDO idleCommentDO);

    /**
     * 获取分享的评论
     * @param id 评论的编号
     * @return IdleCommentDO
     */
    IdleCommentDO getIdleComment(Integer id);

    /**
     * 获取query过滤参数后的评论列表
     * @param query 队列
     * @return 消息队列
     */
    List<IdleCommentDO> listIdleComments(IdleCommentQuery query);

    /**
     * 增加comments
     * @param id 编号
     * @return comments
     */
    int increaseComments(Integer id);

}
