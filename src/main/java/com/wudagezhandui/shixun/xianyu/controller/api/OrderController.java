package com.wudagezhandui.shixun.xianyu.controller.api;

import com.wudagezhandui.shixun.xianyu.aspect.annotation.ErrorHandler;
import com.wudagezhandui.shixun.xianyu.auth.TokenAuth;
import com.wudagezhandui.shixun.xianyu.constant.TokenType;
import com.wudagezhandui.shixun.xianyu.pojo.do0.IdleDO;
import com.wudagezhandui.shixun.xianyu.pojo.do0.OrderDO;
import com.wudagezhandui.shixun.xianyu.pojo.do0.UserDO;
import com.wudagezhandui.shixun.xianyu.pojo.group.Group;
import com.wudagezhandui.shixun.xianyu.pojo.group.GroupPost;
import com.wudagezhandui.shixun.xianyu.pojo.vo.IdleVO;
import com.wudagezhandui.shixun.xianyu.pojo.vo.OrderVO;
import com.wudagezhandui.shixun.xianyu.pojo.vo.UserVO;
import com.wudagezhandui.shixun.xianyu.result.Result;
import com.wudagezhandui.shixun.xianyu.service.IdleService;
import com.wudagezhandui.shixun.xianyu.service.OrderService;
import com.wudagezhandui.shixun.xianyu.service.UserService;
import com.wudagezhandui.shixun.xianyu.validator.annotation.Id;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("v1/orders")
@Validated
public class OrderController {
    private final Mapper mapper;

    private final OrderService orderService;
    private final IdleService idleService;
    private final UserService userService;
    @Autowired
    public OrderController(Mapper mapper, OrderService orderService,IdleService idleService,UserService userService) {
        this.mapper = mapper;
        this.orderService = orderService;
        this.userService=userService;
        this.idleService=idleService;
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

        Result<UserDO> buyerResult = userService.getUser(orderDO.getBuyerId().intValue());
        if(!buyerResult.isSuccess()){
            return Result.fail(buyerResult);
        }
        UserDO buyerDO=buyerResult.getData();
        UserVO buyerVO=mapper.map(buyerDO,UserVO.class);

        Result<UserDO> sellerResult = userService.getUser(orderDO.getSellerId().intValue());
        if(!sellerResult.isSuccess()){
            return Result.fail(sellerResult);
        }
        UserDO sellerDO=sellerResult.getData();
        UserVO sellerVO=mapper.map(sellerDO,UserVO.class);

        Result<IdleDO> idleResult = idleService.getIdle(orderDO.getIdleId().intValue());
        if(!idleResult.isSuccess()){
            return Result.fail(idleResult);
        }
        IdleDO idleDO=idleResult.getData();
        IdleVO idleVO=mapper.map(idleDO,IdleVO.class);

        Result<OrderDO> result = orderService.insert(orderDO);
        OrderVO orderVO=mapper.map(orderDO,OrderVO.class);
        orderVO.setSeller(sellerVO);
        orderVO.setBuyer(buyerVO);
        orderVO.setIdle(idleVO);
        return Result.success(orderVO);
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
        if(!result.isSuccess()){
            return Result.fail(result);
        }
        OrderDO orderDO=result.getData();
        //组装部分
        Result<UserDO> buyerResult = userService.getUser(orderDO.getBuyerId().intValue());
        if(!buyerResult.isSuccess()){
            return Result.fail(buyerResult);
        }
        UserDO buyerDO=buyerResult.getData();
        UserVO buyerVO=mapper.map(buyerDO,UserVO.class);

        Result<UserDO> sellerResult = userService.getUser(orderDO.getSellerId().intValue());
        if(!sellerResult.isSuccess()){
            return Result.fail(sellerResult);
        }
        UserDO sellerDO=sellerResult.getData();
        UserVO sellerVO=mapper.map(sellerDO,UserVO.class);

        Result<IdleDO> idleResult = idleService.getIdle(orderDO.getIdleId().intValue());
        if(!idleResult.isSuccess()){
            return Result.fail(idleResult);
        }
        IdleDO idleDO=idleResult.getData();
        IdleVO idleVO=mapper.map(idleDO,IdleVO.class);
        //组装
        OrderVO orderVO=mapper.map(orderDO,OrderVO.class);
        orderVO.setSeller(sellerVO);
        orderVO.setBuyer(buyerVO);
        orderVO.setIdle(idleVO);
        return Result.success(orderVO);
    }

    /**
     * 获取卖家order
     * @param id 卖家号
     * @return List<OrderVO>
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
    @RequestMapping(value="/SellerID/{id}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @TokenAuth(tokenType = TokenType.USER)
    @ErrorHandler
    public Object getSeller(@PathVariable @Id Integer id) {
        Result<List<OrderDO>> result = orderService.selectBySellerId(id.longValue());
        List<OrderDO> orderDOList=result.getData();
        List<OrderVO> orderVOList=new ArrayList<>();
        for(OrderDO orderDO:orderDOList) {
            Result<UserDO> buyerResult = userService.getUser(orderDO.getBuyerId().intValue());
            if (!buyerResult.isSuccess()) {
                return Result.fail(buyerResult);
            }
            UserDO buyerDO = buyerResult.getData();
            UserVO buyerVO = mapper.map(buyerDO, UserVO.class);

            Result<UserDO> sellerResult = userService.getUser(orderDO.getSellerId().intValue());
            if (!sellerResult.isSuccess()) {
                return Result.fail(sellerResult);
            }
            UserDO sellerDO = sellerResult.getData();
            UserVO sellerVO = mapper.map(sellerDO, UserVO.class);

            Result<IdleDO> idleResult = idleService.getIdle(orderDO.getIdleId().intValue());
            if (!idleResult.isSuccess()) {
                return Result.fail(idleResult);
            }
            IdleDO idleDO = idleResult.getData();
            IdleVO idleVO = mapper.map(idleDO, IdleVO.class);

            OrderVO orderVO=mapper.map(orderDO,OrderVO.class);
            orderVO.setSeller(sellerVO);
            orderVO.setBuyer(buyerVO);
            orderVO.setIdle(idleVO);
            orderVOList.add(orderVO);
        }
        return Result.success(orderVOList);
    }


    /**
     * 获取买家order
     * @param id 买家号
     * @return List<OrderVO>
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
    @RequestMapping(value="/BuyerID/{id}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @TokenAuth(tokenType = TokenType.USER)
    @ErrorHandler
    public Object getBuyer(@PathVariable @Id Integer id) {
        Result<List<OrderDO>> result = orderService.selectByBuyerId(id.longValue());
        List<OrderDO> orderDOList=result.getData();
        List<OrderVO> orderVOList=new ArrayList<>();
        for(OrderDO orderDO:orderDOList) {
            Result<UserDO> buyerResult = userService.getUser(orderDO.getBuyerId().intValue());
            if (!buyerResult.isSuccess()) {
                return Result.fail(buyerResult);
            }
            UserDO buyerDO = buyerResult.getData();
            UserVO buyerVO = mapper.map(buyerDO, UserVO.class);

            Result<UserDO> sellerResult = userService.getUser(orderDO.getSellerId().intValue());
            if (!sellerResult.isSuccess()) {
                return Result.fail(sellerResult);
            }
            UserDO sellerDO = sellerResult.getData();
            UserVO sellerVO = mapper.map(sellerDO, UserVO.class);

            Result<IdleDO> idleResult = idleService.getIdle(orderDO.getIdleId().intValue());
            if (!idleResult.isSuccess()) {
                return Result.fail(idleResult);
            }
            IdleDO idleDO = idleResult.getData();
            IdleVO idleVO = mapper.map(idleDO, IdleVO.class);

            OrderVO orderVO=mapper.map(orderDO,OrderVO.class);
            orderVO.setSeller(sellerVO);
            orderVO.setBuyer(buyerVO);
            orderVO.setIdle(idleVO);
            orderVOList.add(orderVO);
        }
        return Result.success(orderVOList);
    }









    /**
     * 更新Order并返回Order
     * @param orderDO orderDO信息
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
    public Object put( @Validated(Group.class) OrderDO orderDO) {
        Result<UserDO> buyerResult = userService.getUser(orderDO.getBuyerId().intValue());
        if(!buyerResult.isSuccess()){
            return Result.fail(buyerResult);
        }
        UserDO buyerDO=buyerResult.getData();
        UserVO buyerVO=mapper.map(buyerDO,UserVO.class);

        Result<UserDO> sellerResult = userService.getUser(orderDO.getSellerId().intValue());
        if(!sellerResult.isSuccess()){
            return Result.fail(sellerResult);
        }
        UserDO sellerDO=sellerResult.getData();
        UserVO sellerVO=mapper.map(sellerDO,UserVO.class);

        Result<IdleDO> idleResult = idleService.getIdle(orderDO.getIdleId().intValue());
        if(!idleResult.isSuccess()){
            return Result.fail(idleResult);
        }
        IdleDO idleDO=idleResult.getData();
        IdleVO idleVO=mapper.map(idleDO,IdleVO.class);
        Result<OrderDO> result = orderService.updateOrder(orderDO);

        OrderVO orderVO=mapper.map(orderDO,OrderVO.class);
        orderVO.setSeller(sellerVO);
        orderVO.setBuyer(buyerVO);
        orderVO.setIdle(idleVO);
        return Result.success(orderVO);
    }

}
