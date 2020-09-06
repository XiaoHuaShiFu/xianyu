package com.wudagezhandui.shixun.xianyu.dao;

import com.wudagezhandui.shixun.xianyu.pojo.do0.UserAddressDO;
import com.wudagezhandui.shixun.xianyu.pojo.do0.UserNoticeDO;
import com.wudagezhandui.shixun.xianyu.pojo.query.UserAddressQuery;
import com.wudagezhandui.shixun.xianyu.pojo.query.UserNoticeQuery;

import java.util.List;

public interface UserAddressMapper {

    /**
     * 保存地址
     * @param userAddressDO 用户地址对象
     * @return 保存的数量
     */
    int insertUserAddress(UserAddressDO userAddressDO);

    /**
     * 获取用户地址
     * @param id 用户地址编号
     * @return UserAddressDO
     */
    UserAddressDO getUserAddress(Integer id);

    /**
     * 获取query过滤参数后的用户地址列表，包含pageNum，pageSize等过滤参数，
     *
     * @param query 查询参数
     * @return UserAddressDOList
     */
    List<UserAddressDO> listUserAddresses(UserAddressQuery query);

    /**
     * 用户地址的数量
     * @param query 查询参数
     * @return 用户地址的数量
     */
    int count(UserAddressQuery query);

}
