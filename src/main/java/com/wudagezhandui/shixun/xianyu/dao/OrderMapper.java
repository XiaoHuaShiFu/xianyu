package com.wudagezhandui.shixun.xianyu.dao;

import com.wudagezhandui.shixun.xianyu.pojo.do0.OrderDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderMapper {
    /**
     * 查找订单
     * @param id 订单号
     * @return 订单
     */
    OrderDO selectByPrimaryKey(Long id);
    /**
     * 生成订单
     * @param order 新的订单
     */
    void insert(OrderDO order);
    /**
     * 查找卖家所有订单
     * @param id 卖家id
     * @return 卖家所有订单
     */
    List<OrderDO> selectBySellerId(Long id);
    /**
     * 查找买家所有订单
     * id 买家id
     * @return 买家所有订单
     */
    List<OrderDO> selectByBuyerId(Long id);
    /**
     * 更新订单信息
     * @param order 新的订单信息
     */
    void updateOrder(OrderDO order);
    /**
     * 删除订单
     * @param id 订单号
     */
    void deleteByPrimaryKey(Long id);
}
