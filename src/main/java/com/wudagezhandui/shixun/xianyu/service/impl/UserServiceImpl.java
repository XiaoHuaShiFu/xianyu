package com.wudagezhandui.shixun.xianyu.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wudagezhandui.shixun.xianyu.dao.UserMapper;
import com.wudagezhandui.shixun.xianyu.pojo.do0.UserDO;
import com.wudagezhandui.shixun.xianyu.pojo.query.UserQuery;
import com.wudagezhandui.shixun.xianyu.result.ErrorCode;
import com.wudagezhandui.shixun.xianyu.result.Result;
import com.wudagezhandui.shixun.xianyu.service.FileService;
import com.wudagezhandui.shixun.xianyu.service.UserService;
import com.wudagezhandui.shixun.xianyu.service.constant.UserConstant;
import com.wudagezhandui.shixun.xianyu.util.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

/**
 * 描述:用户服务层
 *
 * @author xhsf
 * @email 827032783@qq.com
 * @create 2019-10-26
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserMapper userMapper;

    private final FileService fileService;

    @Autowired
    public UserServiceImpl(UserMapper userMapper, FileService fileService) {
        this.userMapper = userMapper;
        this.fileService = fileService;
    }

    /**
     * 获取UserDO通过username
     *
     * @param username 用户名
     * @return Result<UserDO>
     */
    @Override
    public Result<UserDO> getUserByUsername(String username) {
        UserDO userDO = userMapper.getUserByUsername(username);
        if (userDO == null) {
            return Result.fail(ErrorCode.INVALID_PARAMETER_NOT_FOUND, "The specified user for username="
                    + username + " does not exist.");
        }
        return Result.success(userDO);
    }
    
    /**
     * 保存User
     * @param userDO UserDO
     * @return Result<UserDO>
     */
    @Override
    @Transactional
    public Result<UserDO> saveUser(UserDO userDO) {
        // 用户名已经在数据库里
        int count = userMapper.countByUsername(userDO.getUsername());
        if (count >= 1) {
            return Result.fail(ErrorCode.OPERATION_CONFLICT,
                    "Request was denied due to conflict, the username already exists.");
        }

        count = userMapper.saveUser(userDO);
        //没有插入成功
        if (count < 1) {
            logger.error("Insert user fail.");
            return Result.fail(ErrorCode.INTERNAL_ERROR, "Insert user fail.");
        }

        return getUser(userDO.getId());
    }

    /**
     * 获取UserDO通过id
     *
     * @param id 用户编号
     * @return UserDO
     */
    @Override
    public Result<UserDO> getUser(Integer id) {
        UserDO userDO = userMapper.getUser(id);
        if (userDO == null) {
            return Result.fail(ErrorCode.INVALID_PARAMETER_NOT_FOUND,
                    "The specified id does not exist.");
        }
        return Result.success(userDO);
    }

    /**
     * 获取UserDOList通过查询参数query
     *
     * @param query 查询参数
     * @return UserDOList
     */
    @Override
    public Result<PageInfo<UserDO>> listUsers(UserQuery query) {
        PageHelper.startPage(query.getPageNum(), query.getPageSize());
        Page<UserDO> userDOList = (Page<UserDO>) userMapper.listUsers(query);
        PageInfo<UserDO> pageInfo = new PageInfo<>(userDOList);
        if (userDOList.size() < 1) {
            return Result.fail(ErrorCode.INVALID_PARAMETER_NOT_FOUND, "Not found.");
        }

        return Result.success(pageInfo);
    }

    /**
     * 更新用户信息
     *
     * @param userDO 要更新的信息
     * @return 更新后的用户信息
     */
    @Override
    public Result<UserDO> updateUser(UserDO userDO) {
        //只给更新某些属性
        UserDO userDO0 = new UserDO();
        userDO0.setUsername(userDO.getUsername());
        userDO0.setPassword(userDO.getPassword());
        userDO0.setNickName(userDO.getNickName());
        userDO0.setAliPayAccount(userDO.getAliPayAccount());
        //所有参数都为空
        if (BeanUtils.allFieldIsNull(userDO0)) {
            return Result.fail(ErrorCode.INVALID_PARAMETER_IS_BLANK,
                    "The required parameter must be not all null.");
        }

        userDO0.setId(userDO.getId());
        int count = userMapper.updateUser(userDO0);
        if (count < 1) {
            logger.error("Update user failed. userId: {}", userDO0.getId());
            return Result.fail(ErrorCode.INTERNAL_ERROR, "Update user failed.");
        }

        return getUser(userDO0.getId());
    }

    /**
     * 更新头像
     *
     * @param id userId
     * @param avatar MultipartFile
     * @return 新文件url
     */
    @Override
    public Result<UserDO> updateAvatar(Integer id, MultipartFile avatar) {
        // 获取用户信息，主要是为了获取旧文件Url
        UserDO userDO = userMapper.getUser(id);

        // 更新头像文件
        String newAvatarUrl = fileService.updateFile(avatar, userDO.getAvatarUrl(),
                UserConstant.PREFIX_AVATAR_FILE_DIRECTORY);

        // 更新数据库里的avatar_url
        UserDO userDO0 = new UserDO();
        userDO0.setId(id);
        userDO0.setAvatarUrl(newAvatarUrl);
        int count = userMapper.updateUser(userDO0);
        if (count < 1) {
            logger.error("Update avatar failed. id: {}", id);
            return Result.fail(ErrorCode.INTERNAL_ERROR, "Update avatar failed.");
        }

        userDO.setAvatarUrl(newAvatarUrl);
        return Result.success(userDO);
    }

}
