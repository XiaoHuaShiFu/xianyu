package com.wudagezhandui.shixun.xianyu.service;

import com.wudagezhandui.shixun.xianyu.pojo.do0.OrderDO;
import org.springframework.core.annotation.Order;

import java.util.List;

public interface OrderService {
    OrderDO selectByPrimaryKey(Long id);

    void insert(OrderDO order);

    List<OrderDO> selectBySellerId(Long id);

    List<OrderDO> selectByBuyerId(Long id);

    void updateOrder(OrderDO order);

    void deleteByPrimaryKey(Long id);
}
