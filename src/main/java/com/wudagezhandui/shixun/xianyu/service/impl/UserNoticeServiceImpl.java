package com.wudagezhandui.shixun.xianyu.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wudagezhandui.shixun.xianyu.dao.UserNoticeMapper;
import com.wudagezhandui.shixun.xianyu.pojo.do0.UserDO;
import com.wudagezhandui.shixun.xianyu.pojo.do0.UserNoticeDO;
import com.wudagezhandui.shixun.xianyu.pojo.query.UserNoticeQuery;
import com.wudagezhandui.shixun.xianyu.result.ErrorCode;
import com.wudagezhandui.shixun.xianyu.result.Result;
import com.wudagezhandui.shixun.xianyu.service.UserNoticeService;
import com.wudagezhandui.shixun.xianyu.service.UserService;

/**
 * 描述:用户通知服务层
 *
 * @author xhsf
 * @email 827032783@qq.com
 */
@Service("userNoticeService")
public class UserNoticeServiceImpl implements UserNoticeService {

    private static final Logger logger = LoggerFactory.getLogger(UserNoticeServiceImpl.class);

    private final UserNoticeMapper userNoticeMapper;

    @Autowired
    private UserService userService;

    @Autowired
    public UserNoticeServiceImpl(UserNoticeMapper userNoticeMapper) {
        this.userNoticeMapper = userNoticeMapper;
    }

    /**
     * 保存UserNotice
     * @param userNoticeDO UserNoticeDO
     * @return Result<UserNoticeDO>
     */
    @Override
    // TODO: 2020/4/27 这里没进行参数检查
    public Result<UserNoticeDO> saveUserNotice(UserNoticeDO userNoticeDO) {
        // 判断用户id存不存在
        Result<UserDO> getUserResult = userService.getUser(userNoticeDO.getUserId());
        if (!getUserResult.isSuccess()) {
            return Result.fail(ErrorCode.INVALID_PARAMETER_NOT_FOUND,
                    "Insert false due to the userId={0} not exists.", userNoticeDO.getUserId());
        }

        int count = userNoticeMapper.insertUserNotice(userNoticeDO);
        // 没有插入成功
        if (count < 1) {
            logger.error("Insert user_notice fail.");
            return Result.fail(ErrorCode.INTERNAL_ERROR, "Insert user_notice fail.");
        }

        return getUserNotice(userNoticeDO.getId());
    }

    /**
     * 获取UserNoticeDO通过id
     *
     * @param id 用户通知编号
     * @return UserNoticeDO
     */
    @Override
    public Result<UserNoticeDO> getUserNotice(Integer id) {
        UserNoticeDO userNoticeDO = userNoticeMapper.getUserNotice(id);
        if (userNoticeDO == null) {
            return Result.fail(ErrorCode.INVALID_PARAMETER_NOT_FOUND, "The specified id does not exist.");
        }
        return Result.success(userNoticeDO);
    }

    /**
     * 获取UserNoticeDOList通过查询参数query
     *
     * @param query 查询参数
     * @return UserNoticeDOList
     */
    @Override
    public Result<PageInfo<UserNoticeDO>> listUserNotices(UserNoticeQuery query) {
        PageHelper.startPage(query.getPageNum(), query.getPageSize());
        PageInfo<UserNoticeDO> pageInfo = new PageInfo<>(userNoticeMapper.listUserNotices(query));
        if (pageInfo.getList().size() < 1) {
            Result.fail(ErrorCode.INVALID_PARAMETER_NOT_FOUND, "Not found.");
        }

        return Result.success(pageInfo);
    }

    /**
     * 查询对应条件的用户通知数量
     *
     * @param query 查询参数
     * @return 用户通知数量
     */
    @Override
    public Result<Integer> countUserNotices(UserNoticeQuery query) {
        int count = userNoticeMapper.count(query);
        return Result.success(count);
    }

}
