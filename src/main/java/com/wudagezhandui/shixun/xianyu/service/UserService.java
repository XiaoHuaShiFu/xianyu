package com.wudagezhandui.shixun.xianyu.service;

import com.github.pagehelper.PageInfo;
import com.wudagezhandui.shixun.xianyu.pojo.do0.UserDO;
import com.wudagezhandui.shixun.xianyu.pojo.query.UserQuery;
import com.wudagezhandui.shixun.xianyu.result.Result;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * 描述: 用户Service
 *
 * @author xhsf
 * @email 827032783@qq.com
 * @create 2019-10-9
 */
public interface UserService {

    Result<UserDO> saveUser(UserDO userDO);

    Result<UserDO> getUser(Integer id);

    Result<UserDO> getUserByUsername(String username);

    Result<PageInfo<UserDO>> listUsers(UserQuery query);
    
    Result<UserDO> updateUser(UserDO userDO);

    Result<UserDO> updateAvatar(Integer id, MultipartFile avatar);

}
