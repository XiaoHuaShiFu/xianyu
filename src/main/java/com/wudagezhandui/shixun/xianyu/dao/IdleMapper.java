package com.wudagezhandui.shixun.xianyu.dao;

import com.wudagezhandui.shixun.xianyu.pojo.do0.IdleDO;
import com.wudagezhandui.shixun.xianyu.pojo.do0.UserDO;
import com.wudagezhandui.shixun.xianyu.pojo.query.IdleQuery;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IdleMapper {

    /**
     * 保存闲置物品信息
     * @param idle 物品
     * @return 保存的信息数
     */
    int saveIdle(IdleDO idle);

    /**
     * 返回闲置物品信息
     * @param id 物品id
     * @return 物品信息
     */
    IdleDO getIdle(Integer id);

    /**
     * 通过物品名字搜索
     * @param name 物品名字
     * @return 物品信息
     */
    IdleDO getIdleByName(String name);

    /**
     * 获取query过滤参数后的用户列表，包含pageNum，pageSize等过滤参数，
     * @param query 队列
     * @return 物品队列
     */
    List<IdleDO> listIdles(IdleQuery query);

    /**
     * 更新物品信息
     * @param idleDO0 要更新的物品信息
     * @param user 更新的用户
     * @return 成功更新的条数
     */
    int updateIdle(IdleDO idleDO0, UserDO user);

    /**
     * 获取物品数量通过物品名
     * @param name 物品名
     * @return 物品数量
     */
    int countByUsername(String name);
}
