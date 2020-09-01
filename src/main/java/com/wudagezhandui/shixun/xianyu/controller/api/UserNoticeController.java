package com.wudagezhandui.shixun.xianyu.controller.api;

import com.github.pagehelper.PageInfo;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.wudagezhandui.shixun.xianyu.aspect.annotation.ErrorHandler;
import com.wudagezhandui.shixun.xianyu.auth.TokenAuth;
import com.wudagezhandui.shixun.xianyu.constant.TokenType;
import com.wudagezhandui.shixun.xianyu.pojo.do0.UserNoticeDO;
import com.wudagezhandui.shixun.xianyu.pojo.group.GroupPost;
import com.wudagezhandui.shixun.xianyu.pojo.query.UserNoticeQuery;
import com.wudagezhandui.shixun.xianyu.pojo.vo.UserNoticeVO;
import com.wudagezhandui.shixun.xianyu.result.Result;
import com.wudagezhandui.shixun.xianyu.service.UserNoticeService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 描述: 用户通知模块
 *
 * @author xhsf
 * @email 827032783@qq.com
 */
@RestController
@RequestMapping("v1/users/notices")
@Validated
public class UserNoticeController {

    private final Mapper mapper;

    private final UserNoticeService userNoticeService;

    @Autowired
    public UserNoticeController(Mapper mapper, UserNoticeService userNoticeService) {
        this.mapper = mapper;
        this.userNoticeService = userNoticeService;
    }

    /**
     * 创建UserNotice并返回UserNotice
     * @param userNoticeDO 用户通知信息
     * @return UserNoticeVO
     *
     * @success:
     * HttpStatus.CREATED
     *
     * @errors:
     * INVALID_PARAMETER
     * OPERATION_CONFLICT
     *
     * @bindErrors
     * INVALID_PARAMETER
     * INVALID_PARAMETER_IS_NULL
     * INVALID_PARAMETER_IS_BLANK
     * INVALID_PARAMETER_SIZE
     * INVALID_PARAMETER_VALUE_BELOW
     */
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    //@TokenAuth(tokenType = {TokenType.USER, TokenType.ADMIN})
    @ErrorHandler
    public Object post(@Validated(GroupPost.class) UserNoticeDO userNoticeDO) {
        Result<UserNoticeDO> result = userNoticeService.saveUserNotice(userNoticeDO);
        return !result.isSuccess() ? result : mapper.map(result.getData(), UserNoticeVO.class);
    }


    /**
     * 查询userNotice或统计条数
     * @param query 查询参数
     * @return UserNoticeVOList或用户通知的条数
     *
     * @success:
     * HttpStatus.OK
     */
    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    //@TokenAuth(tokenType = {TokenType.USER, TokenType.ADMIN})
    @ErrorHandler
    public Object get(UserNoticeQuery query) {
        // 统计条数
        if (query.getCount() != null && query.getCount()) {
            return userNoticeService.countUserNotices(query).getData();
        }
        // 这里是查询列表
        else {
            Result<PageInfo<UserNoticeDO>> result = userNoticeService.listUserNotices(query);
            if (!result.isSuccess()) {
                return result;
            }

            PageInfo<UserNoticeDO> pageInfo = result.getData();
            List<UserNoticeDO> userNoticeDOList = pageInfo.getList();
            List<UserNoticeVO> userNoticeVOList = userNoticeDOList.stream()
                    .map(userNoticeDO -> mapper.map(userNoticeDO, UserNoticeVO.class))
                    .collect(Collectors.toList());
            PageInfo<UserNoticeVO> pageInfo0 = mapper.map(pageInfo, PageInfo.class);
            pageInfo0.setList(userNoticeVOList);

            return pageInfo0;
        }
    }



}
