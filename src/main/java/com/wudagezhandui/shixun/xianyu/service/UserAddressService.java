package com.wudagezhandui.shixun.xianyu.service;

import com.github.pagehelper.PageInfo;
import com.wudagezhandui.shixun.xianyu.pojo.do0.UserAddressDO;
import com.wudagezhandui.shixun.xianyu.pojo.do0.UserNoticeDO;
import com.wudagezhandui.shixun.xianyu.pojo.query.UserAddressQuery;
import com.wudagezhandui.shixun.xianyu.pojo.query.UserNoticeQuery;
import com.wudagezhandui.shixun.xianyu.result.Result;

/**
 * 描述: 用户地址Service
 *
 * @author xhsf
 * @email 827032783@qq.com
 */
public interface UserAddressService {

    Result<UserAddressDO> saveUserAddress(UserAddressDO userNAddressDO);

    Result<UserAddressDO> getUserAddress(Integer id);

    Result<PageInfo<UserAddressDO>> listUserAddresses(UserAddressQuery query);

    Result<Integer> countUserAddresss(UserAddressQuery query);
}
