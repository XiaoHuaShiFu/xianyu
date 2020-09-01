package com.wudagezhandui.shixun.xianyu.service.impl;
import com.wudagezhandui.shixun.xianyu.dao.OrderMapper;
import com.wudagezhandui.shixun.xianyu.pojo.do0.OrderDO;
import com.wudagezhandui.shixun.xianyu.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("OrderService")
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper mapper;

    @Override
    public OrderDO selectByPrimaryKey(Long id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public void insert(OrderDO order) {
        mapper.insert(order);
    }

    @Override
    public List<OrderDO> selectBySellerId(Long id) {
        return mapper.selectBySellerId(id);
    }

    @Override
    public List<OrderDO> selectByBuyerId(Long id) {
        return mapper.selectByBuyerId(id);
    }

    @Override
    public void updateOrder(OrderDO order) {
        mapper.updateOrder(order);
    }

    @Override
    public void deleteByPrimaryKey(Long id){
        mapper.deleteByPrimaryKey(id);
    }
}
