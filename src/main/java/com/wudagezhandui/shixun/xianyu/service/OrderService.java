package com.wudagezhandui.shixun.xianyu.service;

import com.wudagezhandui.shixun.xianyu.pojo.do0.OrderDO;
import com.wudagezhandui.shixun.xianyu.result.Result;

import java.util.List;

public interface OrderService {
    Result<OrderDO> selectByPrimaryKey(Long id);

    Result<OrderDO> insert(OrderDO order);

    Result<List<OrderDO>>selectBySellerId(Long id);

    Result<List<OrderDO>> selectByBuyerId(Long id);

    Result<OrderDO> updateOrder(OrderDO order);

}
