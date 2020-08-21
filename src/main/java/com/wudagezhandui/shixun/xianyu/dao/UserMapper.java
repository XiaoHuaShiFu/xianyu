package com.wudagezhandui.shixun.xianyu.dao;

import com.wudagezhandui.shixun.xianyu.pojo.do0.UserDO;
import com.wudagezhandui.shixun.xianyu.pojo.query.UserQuery;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    /**
     * 保存用户
     * @param user 用户对象
     * @return 保存的数量
     */
    int saveUser(UserDO user);

    /**
     * 获取用户
     * @param id 用户编号
     * @return userDO
     */
    UserDO getUser(Integer id);

    /**
     * 获取用户
     * @param username 用户名
     * @return userDO
     */
    UserDO getUserByUsername(String username);
    
    /**
     * 获取query过滤参数后的用户列表，包含pageNum，pageSize等过滤参数，
     *
     * @param query 查询参数
     * @return UserDOList
     */
    List<UserDO> listUsers(UserQuery query);

    /**
     * 更新用户信息
     * @param userDO0 要更新的用户信息
     * @return 成功更新的条数
     */
    int updateUser(UserDO userDO0);

    /**
     * 获取用户数量通过用户名
     * @param username 用户名
     * @return 用户数量
     */
    int countByUsername(String username);

}