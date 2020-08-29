package com.wudagezhandui.shixun.xianyu.dao;

import com.wudagezhandui.shixun.xianyu.pojo.do0.UserNoticeDO;
import com.wudagezhandui.shixun.xianyu.pojo.query.UserNoticeQuery;

import java.util.List;

public interface UserNoticeMapper {

    /**
     * 保存用户通知
     * @param userNoticeDO 用户通知对象
     * @return 保存的数量
     */
    int insertUserNotice(UserNoticeDO userNoticeDO);

    /**
     * 获取用户通知
     * @param id 用户通知编号
     * @return UserNoticeDO
     */
    UserNoticeDO getUserNotice(Integer id);

    /**
     * 获取query过滤参数后的用户通知列表，包含pageNum，pageSize等过滤参数，
     *
     * @param query 查询参数
     * @return UserNoticeDOList
     */
    List<UserNoticeDO> listUserNotices(UserNoticeQuery query);

    /**
     * 用户通知的数量
     * @param query 查询参数
     * @return 用户通知的数量
     */
    int count(UserNoticeQuery query);

}
