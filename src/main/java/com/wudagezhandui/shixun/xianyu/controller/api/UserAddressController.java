package com.wudagezhandui.shixun.xianyu.controller.api;

import com.github.pagehelper.PageInfo;
import com.wudagezhandui.shixun.xianyu.aspect.annotation.ErrorHandler;
import com.wudagezhandui.shixun.xianyu.pojo.do0.UserAddressDO;
import com.wudagezhandui.shixun.xianyu.pojo.do0.UserNoticeDO;
import com.wudagezhandui.shixun.xianyu.pojo.group.GroupPost;
import com.wudagezhandui.shixun.xianyu.pojo.query.UserAddressQuery;
import com.wudagezhandui.shixun.xianyu.pojo.query.UserNoticeQuery;
import com.wudagezhandui.shixun.xianyu.pojo.vo.UserAddressVO;
import com.wudagezhandui.shixun.xianyu.pojo.vo.UserNoticeVO;
import com.wudagezhandui.shixun.xianyu.result.Result;
import com.wudagezhandui.shixun.xianyu.service.UserAddressService;
import com.wudagezhandui.shixun.xianyu.service.UserNoticeService;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 描述: 用户地址模块
 *
 * @author xhsf
 * @email 827032783@qq.com
 */
@RestController
@RequestMapping("v1/users/addresses")
@Validated
public class UserAddressController {

    private final Mapper mapper;

    private final UserAddressService userAddressService;

    @Autowired
    public UserAddressController(Mapper mapper, UserAddressService userAddressService) {
        this.mapper = mapper;
        this.userAddressService = userAddressService;
    }

    /**
     * 创建UserAddress并返回UserAddress
     * @param userAddressDO 用户地址信息
     * @return UserAddressVO
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
    public Object post(@Validated(GroupPost.class) UserAddressDO userAddressDO) {
        Result<UserAddressDO> result = userAddressService.saveUserAddress(userAddressDO);
        return !result.isSuccess() ? result : mapper.map(result.getData(), UserAddressVO.class);
    }


    /**
     * 查询userAddress或统计条数
     * @param query 查询参数
     * @return UserAddressVOList或用户地址的条数
     *
     * @success:
     * HttpStatus.OK
     */
    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    //@TokenAuth(tokenType = {TokenType.USER, TokenType.ADMIN})
    @ErrorHandler
    public Object get(UserAddressQuery query) {
        // 统计条数
        if (query.getCount() != null && query.getCount()) {
            return userAddressService.countUserAddresss(query).getData();
        }
        // 这里是查询列表
        else {
            Result<PageInfo<UserAddressDO>> result = userAddressService.listUserAddresses(query);
            if (!result.isSuccess()) {
                return result;
            }

            PageInfo<UserAddressDO> pageInfo = result.getData();
            List<UserAddressDO> userAddressDOList = pageInfo.getList();
            List<UserAddressVO> userAddressVOList = userAddressDOList.stream()
                    .map(userAddressDO -> mapper.map(userAddressDO, UserAddressVO.class))
                    .collect(Collectors.toList());
            PageInfo<UserAddressVO> pageInfo0 = mapper.map(pageInfo, PageInfo.class);
            pageInfo0.setList(userAddressVOList);

            return pageInfo0;
        }
    }



}
