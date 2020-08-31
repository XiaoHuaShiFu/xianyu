package com.wudagezhandui.shixun.xianyu.service;

import org.springframework.core.annotation.Order;

import java.util.List;

public interface OrderService {
    Order selectByPrimaryKey(Long id);

    void insert(Order order);

    List<Order> selectBySellerId(Long id);

    List<Order> selectByBuyerId(Long id);

    void updateOrder(Order order);

    void deleteByPrimaryKey(Long id);
}
