package com.wudagezhandui.shixun.xianyu.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wudagezhandui.shixun.xianyu.dao.IdleCommentMapper;
import com.wudagezhandui.shixun.xianyu.pojo.do0.IdleCommentDO;
import com.wudagezhandui.shixun.xianyu.pojo.do0.IdleDO;
import com.wudagezhandui.shixun.xianyu.pojo.query.IdleCommentQuery;
import com.wudagezhandui.shixun.xianyu.result.ErrorCode;
import com.wudagezhandui.shixun.xianyu.result.Result;
import com.wudagezhandui.shixun.xianyu.service.IdleCommentService;
import com.wudagezhandui.shixun.xianyu.service.IdleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("idleCommentService")
public class IdleCommentServiceImpl implements IdleCommentService {

    private final IdleCommentMapper idleCommentMapper;

    @Autowired
    private final IdleService idleService;

    @Autowired
    public IdleCommentServiceImpl(IdleCommentMapper idleCommentMapper, IdleService idleService){
        this.idleCommentMapper = idleCommentMapper;
        this.idleService = idleService;
    }

    @Override
    public Result<IdleCommentDO> saveIdleComment(IdleCommentDO idleCommentDO) {
        // 判断商品是否存在
        Result<IdleDO> getIdleResult = idleService.getIdle(idleCommentDO.getIdleId());
        if(!getIdleResult.isSuccess()) {
            return Result.fail(ErrorCode.INVALID_PARAMETER_NOT_FOUND,
                    "The idle of id={0} not exists.", idleCommentDO.getId());
        }

        // 保存闲置商品的评论
        int count = idleCommentMapper.saveIdleComment(idleCommentDO);
        // 如果没有插入成功
        if(count < 1) {
            return Result.fail(ErrorCode.INTERNAL_ERROR, "Insert idleComment fail.");
        }

        return getIdleComment(idleCommentDO.getId());
    }
    /*
     * 获取IdleCommentDO 通过 id
     * @param id评论的编号
     * @return idleCommentDO
     */
    @Override
    public Result<IdleCommentDO> getIdleComment(Integer id) {
        IdleCommentDO idleCommentDO = idleCommentMapper.getIdleComment(id);
        if(idleCommentDO == null) {
            return Result.fail(ErrorCode.INVALID_PARAMETER_NOT_FOUND, "The specified id does not exist.");
        }
        return Result.success(idleCommentDO);
    }

    /**
     * 获取ShareCommentDOList通过查询参数query
     *
     * @param query 查询参数
     * @return IdleCommentlist
     */
    @Override
    public Result<PageInfo<IdleCommentDO>> listIdleComments(IdleCommentQuery query) {
        PageHelper.startPage(query.getPageNum(), query.getPageSize());
        PageInfo<IdleCommentDO> pageInfo = new PageInfo<>(idleCommentMapper.listIdleComments(query));
        return Result.success(pageInfo);
    }

    @Override
    public Result<IdleCommentDO> increaseComments(Integer id) {
        idleCommentMapper.increaseComments(id);
        return getIdleComment(id);
    }

}
