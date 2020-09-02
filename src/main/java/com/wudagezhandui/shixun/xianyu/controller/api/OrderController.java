package com.wudagezhandui.shixun.xianyu.controller.api;

import com.wudagezhandui.shixun.xianyu.aspect.annotation.ErrorHandler;
import com.wudagezhandui.shixun.xianyu.auth.TokenAuth;
import com.wudagezhandui.shixun.xianyu.constant.TokenType;
import com.wudagezhandui.shixun.xianyu.pojo.ao.TokenAO;
import com.wudagezhandui.shixun.xianyu.pojo.do0.OrderDO;
import com.wudagezhandui.shixun.xianyu.pojo.group.Group;
import com.wudagezhandui.shixun.xianyu.pojo.group.GroupPost;
import com.wudagezhandui.shixun.xianyu.pojo.vo.OrderVO;
import com.wudagezhandui.shixun.xianyu.result.Result;
import com.wudagezhandui.shixun.xianyu.service.OrderService;
import com.wudagezhandui.shixun.xianyu.validator.annotation.Id;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("v1/orders")
@Validated
public class OrderController {
    private final Mapper mapper;

    private final OrderService orderService;

    @Autowired
    public OrderController(Mapper mapper, OrderService orderService) {
        this.mapper = mapper;
        this.orderService = orderService;
    }

    /**
     * 创建order并返回order
     * @param orderDO 用户信息
     * @return orderVO
     *
     * @success:
     * HttpStatus.CREATED
     *
     * @bindErrors
     * INVALID_PARAMETER
     * INVALID_PARAMETER_IS_NULL
     * INVALID_PARAMETER_IS_BLANK
     * INVALID_PARAMETER_SIZE
     * INVALID_PARAMETER_VALUE_BELOW
     */
    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    @ErrorHandler
    public Object post(@Validated(GroupPost.class) @RequestBody OrderDO orderDO) {
        Result<OrderDO> result = orderService.insert(orderDO);
        return !result.isSuccess() ? result : mapper.map(result.getData(), OrderVO.class);
    }

    /**
     * 获取order
     * @param id 订单编号
     * @return OrderVO
     *
     * @success:
     * HttpStatus.OK
     *
     * @errors:
     * FORBIDDEN_SUB_USER
     *
     * @bindErrors
     * INVALID_PARAMETER_VALUE_BELOW: The name of id below, min: 0.
     */
    @RequestMapping(value="/OrderID/{id}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @TokenAuth(tokenType = TokenType.USER)
    @ErrorHandler
    public Object get(@PathVariable @Id Integer id) {
        Result<OrderDO> result = orderService.selectByPrimaryKey(id.longValue());
        return !result.isSuccess() ? result : mapper.map(result.getData(), OrderVO.class);
    }

    /**
     * 更新Order并返回Order
     * @param order order信息
     * @return OrderVO
     *
     * @success:
     * HttpStatus.OK
     *
     * @errors:
     *
     * @bindErrors
     * INVALID_PARAMETER
     * INVALID_PARAMETER_IS_NULL
     * INVALID_PARAMETER_IS_BLANK
     * INVALID_PARAMETER_SIZE
     * INVALID_PARAMETER_VALUE_BELOW
     */
    @RequestMapping(method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK)
    @TokenAuth(tokenType = TokenType.USER)
    @ErrorHandler
    public Object put( @Validated(Group.class) OrderDO order) {

        Result<OrderDO> result = orderService.updateOrder(order);

        return !result.isSuccess() ? result : mapper.map(result.getData(), OrderVO.class);
    }



}
