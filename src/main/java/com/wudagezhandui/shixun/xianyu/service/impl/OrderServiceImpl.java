package com.wudagezhandui.shixun.xianyu.service.impl;
import com.wudagezhandui.shixun.xianyu.dao.OrderMapper;
import com.wudagezhandui.shixun.xianyu.pojo.do0.OrderDO;
import com.wudagezhandui.shixun.xianyu.pojo.modules.TheStatus;
import com.wudagezhandui.shixun.xianyu.result.ErrorCode;
import com.wudagezhandui.shixun.xianyu.result.Result;
import com.wudagezhandui.shixun.xianyu.service.OrderService;
import com.wudagezhandui.shixun.xianyu.util.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("OrderService")
public class OrderServiceImpl implements OrderService {

    private static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    private final OrderMapper mapper;

    @Autowired
    public OrderServiceImpl(OrderMapper mapper) {
        this.mapper = mapper;

    }


    @Override
    public Result<OrderDO> selectByPrimaryKey(Long id) {

        OrderDO orderDO = mapper.selectByPrimaryKey(id);
        if(orderDO==null){
            return Result.fail(ErrorCode.INVALID_PARAMETER_NOT_FOUND,"The specified order for orderId="
                    + id + " does not exist.");
        }
        return Result.success(orderDO);

    }

    @Override
    public Result<OrderDO> insert(OrderDO order) {

        TheStatus statusNormal=new TheStatus();
        statusNormal.setStatus1("已拍下");
        statusNormal.setStatus2("待付款");
        statusNormal.setStatus3("待发售");
        statusNormal.setStatus4("待交易");
        order.setStatus(statusNormal);
        order.setTransactionTime(new Date());
        int count=mapper.insert(order);
        if(count<1){
            logger.error("Insert order fail.");
            return Result.fail(ErrorCode.INTERNAL_ERROR, "Insert order fail.");
        }

        return selectByPrimaryKey(order.getId().longValue());
    }

    @Override
    public Result<List<OrderDO>> selectBySellerId(Long id) {
        List<OrderDO> orderDO = mapper.selectBySellerId(id);
        if(null == orderDO || orderDO.size() ==0){
            return Result.fail(ErrorCode.INVALID_PARAMETER_NOT_FOUND,"The specified order for sellerId="
                    + id + " does not exist.");
        }
        return Result.success(orderDO);
    }

    @Override
    public Result<List<OrderDO>> selectByBuyerId(Long id) {
        List<OrderDO> orderDO = mapper.selectByBuyerId(id);
        if(null == orderDO || orderDO.size() ==0){
            return Result.fail(ErrorCode.INVALID_PARAMETER_NOT_FOUND,"The specified order for buyerId="
                    + id + " does not exist.");
        }
        return Result.success(orderDO);
    }

    @Override
    public Result<OrderDO> updateOrder(OrderDO order) {
        OrderDO orderDO=new OrderDO();
        orderDO.setStatus(order.getStatus());
        orderDO.setFreight(order.getFreight());
        orderDO.setActualPay(order.getActualPay());
        orderDO.setTotalPrice(order.getTotalPrice());
        orderDO.setTransactionTime((order.getTransactionTime()));
        if(BeanUtils.allFieldIsNull(orderDO)){
            return Result.fail(ErrorCode.INVALID_PARAMETER_IS_BLANK,
                    "The required parameter must be not all null.");
        }
        orderDO.setId(order.getId());
        orderDO.setBuyerId(order.getBuyerId());
        orderDO.setSellerId(order.getSellerId());
        orderDO.setIdleId(order.getIdleId());
        orderDO.setAliPayNumber(order.getAliPayNumber());

        int count =  mapper.updateOrder(orderDO);
        if(count<1){
            logger.error("Update idle failed. orderId: {}", orderDO.getId());
            return Result.fail(ErrorCode.INTERNAL_ERROR, "Update order failed.");
        }
        return selectByPrimaryKey(order.getId().longValue());
    }


}
