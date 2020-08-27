package com.wudagezhandui.shixun.xianyu.dao;

import com.wudagezhandui.shixun.xianyu.pojo.do0.IdleDO;
import com.wudagezhandui.shixun.xianyu.pojo.query.SearchQuery;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SearchMapper {

    /**
     * 获取query过滤参数后的闲置列表，包含pageNum，pageSize等过滤参数，
     * @param query 查询参数
     * @return 物品队列
     */
    List<IdleDO> searchIdles(SearchQuery query);

}
