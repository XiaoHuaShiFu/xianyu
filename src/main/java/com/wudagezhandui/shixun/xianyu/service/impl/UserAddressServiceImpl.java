package com.wudagezhandui.shixun.xianyu.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wudagezhandui.shixun.xianyu.dao.UserAddressMapper;
import com.wudagezhandui.shixun.xianyu.dao.UserNoticeMapper;
import com.wudagezhandui.shixun.xianyu.pojo.do0.UserAddressDO;
import com.wudagezhandui.shixun.xianyu.pojo.do0.UserDO;
import com.wudagezhandui.shixun.xianyu.pojo.do0.UserNoticeDO;
import com.wudagezhandui.shixun.xianyu.pojo.query.UserAddressQuery;
import com.wudagezhandui.shixun.xianyu.pojo.query.UserNoticeQuery;
import com.wudagezhandui.shixun.xianyu.result.ErrorCode;
import com.wudagezhandui.shixun.xianyu.result.Result;
import com.wudagezhandui.shixun.xianyu.service.UserAddressService;
import com.wudagezhandui.shixun.xianyu.service.UserNoticeService;
import com.wudagezhandui.shixun.xianyu.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 描述:用户地址服务层
 *
 * @author xhsf
 * @email 827032783@qq.com
 */
@Service("userAddressService")
public class UserAddressServiceImpl implements UserAddressService {

    private static final Logger logger = LoggerFactory.getLogger(UserAddressServiceImpl.class);

    private final UserAddressMapper userAddressMapper;

    private final UserService userService;

    @Autowired
    public UserAddressServiceImpl(UserAddressMapper userAddressMapper, UserService userService) {
        this.userAddressMapper = userAddressMapper;
        this.userService = userService;
    }

    /**
     * 保存UserAddress
     * @param userAddressDO UserAddressDO
     * @return Result<UserAddressDO>
     */
    @Override
    // TODO: 2020/4/27 这里没进行参数检查
    public Result<UserAddressDO> saveUserAddress(UserAddressDO userAddressDO) {
        // 判断用户id存不存在
        Result<UserDO> getUserResult = userService.getUser(userAddressDO.getUserId());
        if (!getUserResult.isSuccess()) {
            return Result.fail(ErrorCode.INVALID_PARAMETER_NOT_FOUND,
                    "Insert false due to the userId={0} not exists.", userAddressDO.getUserId());
        }

        int count = userAddressMapper.insertUserAddress(userAddressDO);
        // 没有插入成功
        if (count < 1) {
            logger.error("Insert user_address fail.");
            return Result.fail(ErrorCode.INTERNAL_ERROR, "Insert user_address fail.");
        }

        return getUserAddress(userAddressDO.getId());
    }

    /**
     * 获取UserAddressDO通过id
     *
     * @param id 用户地址编号
     * @return UserAddressDO
     */
    @Override
    public Result<UserAddressDO> getUserAddress(Integer id) {
        UserAddressDO userAddressDO = userAddressMapper.getUserAddress(id);
        if (userAddressDO == null) {
            return Result.fail(ErrorCode.INVALID_PARAMETER_NOT_FOUND, "The specified id does not exist.");
        }
        return Result.success(userAddressDO);
    }

    /**
     * 获取UserAddressDOList通过查询参数query
     *
     * @param query 查询参数
     * @return UserAddressDOList
     */
    @Override
    public Result<PageInfo<UserAddressDO>> listUserAddresses(UserAddressQuery query) {
        PageHelper.startPage(query.getPageNum(), query.getPageSize());
        PageInfo<UserAddressDO> pageInfo = new PageInfo<>(userAddressMapper.listUserAddresses(query));

        return Result.success(pageInfo);
    }

    /**
     * 查询对应条件的用户通知数量
     *
     * @param query 查询参数
     * @return 用户通知数量
     */
    @Override
    public Result<Integer> countUserAddresss(UserAddressQuery query) {
        int count = userAddressMapper.count(query);
        return Result.success(count);
    }

}
