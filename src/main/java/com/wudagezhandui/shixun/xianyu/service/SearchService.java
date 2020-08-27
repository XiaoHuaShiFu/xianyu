package com.wudagezhandui.shixun.xianyu.service;

import com.github.pagehelper.PageInfo;
import com.wudagezhandui.shixun.xianyu.pojo.do0.IdleDO;
import com.wudagezhandui.shixun.xianyu.pojo.query.SearchQuery;
import com.wudagezhandui.shixun.xianyu.result.Result;


/***
 * 搜素服务
 */

public interface SearchService {
    Result<PageInfo<IdleDO>> searchIdles(SearchQuery query);

}
