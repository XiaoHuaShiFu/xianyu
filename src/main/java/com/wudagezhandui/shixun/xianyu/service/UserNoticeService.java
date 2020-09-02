package com.wudagezhandui.shixun.xianyu.service;

import com.github.pagehelper.PageInfo;
import com.wudagezhandui.shixun.xianyu.pojo.do0.UserNoticeDO;
import com.wudagezhandui.shixun.xianyu.pojo.query.UserNoticeQuery;
import com.wudagezhandui.shixun.xianyu.result.Result;

/**
 * 描述: 用户通知Service
 *
 * @author xhsf
 * @email 827032783@qq.com
 */
public interface UserNoticeService {

    Result<UserNoticeDO> saveUserNotice(UserNoticeDO userNoticeDO);

    Result<UserNoticeDO> getUserNotice(Integer id);

    Result<PageInfo<UserNoticeDO>> listUserNotices(UserNoticeQuery query);

    Result<Integer> countUserNotices(UserNoticeQuery query);
}
