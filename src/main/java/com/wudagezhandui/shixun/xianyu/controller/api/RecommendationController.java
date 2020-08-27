package com.wudagezhandui.shixun.xianyu.controller.api;

import com.github.pagehelper.PageInfo;
import com.wudagezhandui.shixun.xianyu.aspect.annotation.ErrorHandler;
import com.wudagezhandui.shixun.xianyu.pojo.do0.IdleDO;
import com.wudagezhandui.shixun.xianyu.pojo.query.IdleQuery;
import com.wudagezhandui.shixun.xianyu.pojo.query.RecommendationQuery;
import com.wudagezhandui.shixun.xianyu.pojo.vo.IdleVO;
import com.wudagezhandui.shixun.xianyu.result.Result;
import com.wudagezhandui.shixun.xianyu.service.IdleService;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

/**
 * 描述：推荐模块
 *
 * @author xhsf
 * @create 2020年8月27日
 */

@RestController
@RequestMapping("v1/recommendation")
@Validated
public class RecommendationController {

    private final Mapper mapper;

    private final IdleService idleService;

    @Autowired
    public RecommendationController(Mapper mapper, IdleService idleService) {
        this.mapper = mapper;
        this.idleService = idleService;
    }

    /**
     * 推荐内容
     * @param query 查询参数
     * @return IdleVOList
     *
     * @success:
     * HttpStatus.OK
     */
    @GetMapping("/idles")
    @ResponseStatus(value = HttpStatus.OK)
    @ErrorHandler
    public Object recommend(RecommendationQuery query) {
        IdleQuery idleQuery = new IdleQuery();
        idleQuery.setPageNum(query.getPageNum());
        idleQuery.setPageSize(query.getPageSize());
        Result<PageInfo<IdleDO>> result = idleService.listIdles(idleQuery);
        if (!result.isSuccess()) {
            return result;
        }

        PageInfo<IdleVO> pageInfo = mapper.map(result.getData(), PageInfo.class);
        pageInfo.setList(result.getData().getList().stream()
                .map(idleDO -> mapper.map(idleDO, IdleVO.class))
                .collect(Collectors.toList()));
        return pageInfo;
    }

}
