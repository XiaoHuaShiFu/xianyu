package com.wudagezhandui.shixun.xianyu.service.impl;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wudagezhandui.shixun.xianyu.dao.SearchMapper;
import com.wudagezhandui.shixun.xianyu.pojo.do0.IdleDO;
import com.wudagezhandui.shixun.xianyu.pojo.query.SearchQuery;
import com.wudagezhandui.shixun.xianyu.result.ErrorCode;
import com.wudagezhandui.shixun.xianyu.result.Result;
import com.wudagezhandui.shixun.xianyu.service.SearchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/***
 * 搜素服务
 */
@Service("searchService")
public class SearchServiceImpl implements SearchService {

    private static final Logger logger = LoggerFactory.getLogger(SearchServiceImpl.class);

    private final SearchMapper searchMapper;

    @Autowired
    public SearchServiceImpl(SearchMapper searchMapper) {
        this.searchMapper = searchMapper;
    }

    /***
     * 获取Idles通过查询参数query
     *
     * @param query 查询参数
     * @return idleDOList
     */
    @Override
    public Result<PageInfo<IdleDO>> searchIdles(SearchQuery query) {
        PageHelper.startPage(query.getPageNum(), query.getPageSize());
        Page<IdleDO> idleDOList = (Page<IdleDO>) searchMapper.searchIdles(query);
        PageInfo<IdleDO> pageInfo = new PageInfo<>(idleDOList);
        if (idleDOList.size() < 1) {
            return Result.fail(ErrorCode.INVALID_PARAMETER_NOT_FOUND, "Not found.");
        }

        return Result.success(pageInfo);
    }

}
