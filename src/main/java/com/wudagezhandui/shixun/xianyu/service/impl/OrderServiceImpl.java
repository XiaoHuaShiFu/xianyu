package com.wudagezhandui.shixun.xianyu.service.impl;

import com.alipay.api.AlipayResponse;
import com.alipay.api.response.AlipayTradePrecreateResponse;
import com.alipay.demo.trade.config.Configs;
import com.alipay.demo.trade.model.ExtendParams;
import com.alipay.demo.trade.model.GoodsDetail;
import com.alipay.demo.trade.model.builder.AlipayTradePrecreateRequestBuilder;
import com.alipay.demo.trade.model.result.AlipayF2FPrecreateResult;
import com.alipay.demo.trade.service.AlipayTradeService;
import com.alipay.demo.trade.service.impl.AlipayTradeServiceImpl;
import com.alipay.demo.trade.utils.ZxingUtils;
import com.google.gson.Gson;
import com.wudagezhandui.shixun.xianyu.constant.AlipayConst;
import com.wudagezhandui.shixun.xianyu.constant.UserNoticeType;
import com.wudagezhandui.shixun.xianyu.dao.OrderMapper;
import com.wudagezhandui.shixun.xianyu.pojo.do0.IdleDO;
import com.wudagezhandui.shixun.xianyu.pojo.do0.OrderDO;
import com.wudagezhandui.shixun.xianyu.pojo.do0.PaymentDO;
import com.wudagezhandui.shixun.xianyu.pojo.do0.UserNoticeDO;
import com.wudagezhandui.shixun.xianyu.pojo.modules.TheStatus;
import com.wudagezhandui.shixun.xianyu.result.ErrorCode;
import com.wudagezhandui.shixun.xianyu.result.Result;
import com.wudagezhandui.shixun.xianyu.service.FileService;
import com.wudagezhandui.shixun.xianyu.service.IdleService;
import com.wudagezhandui.shixun.xianyu.service.OrderService;
import com.wudagezhandui.shixun.xianyu.service.UserNoticeService;
import com.wudagezhandui.shixun.xianyu.service.constant.FileConstant;
import com.wudagezhandui.shixun.xianyu.service.constant.PaymentConstant;
import com.wudagezhandui.shixun.xianyu.util.BeanUtils;
import com.wudagezhandui.shixun.xianyu.util.DateTimeUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.math.BigDecimal;
import java.util.*;

@Service("orderService")
public class OrderServiceImpl implements OrderService {

    private static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    private final OrderMapper mapper;

    private final UserNoticeService userNoticeService;

    private final IdleService idleService;

    private final Gson gson;

    @Autowired
    public OrderServiceImpl(OrderMapper mapper, UserNoticeService userNoticeService, IdleService idleService,
                            Gson gson, FileService fileService) {
        this.mapper = mapper;

        this.userNoticeService = userNoticeService;
        this.idleService = idleService;
        this.gson = gson;

    }


    @Override
    public Result<OrderDO> selectByPrimaryKey(Long id) {

        OrderDO orderDO = mapper.selectByPrimaryKey(id);
        if (orderDO == null) {
            return Result.fail(ErrorCode.INVALID_PARAMETER_NOT_FOUND, "The specified order for orderId="
                    + id + " does not exist.");
        }
        return Result.success(orderDO);

    }

    @Override
    public Result<OrderDO> insert(OrderDO order) {
        TheStatus statusNormal = new TheStatus();
        statusNormal.setStatus1("已拍下");
        statusNormal.setStatus2("待付款");
        statusNormal.setStatus3("待发售");
        statusNormal.setStatus4("待交易");
        order.setStatus(statusNormal);
        order.setTransactionTime(new Date());
        int count = mapper.insert(order);
        if (count < 1) {
            logger.error("Insert order fail.");
            return Result.fail(ErrorCode.INTERNAL_ERROR, "Insert order fail.");
        }

        IdleDO idleDO = idleService.getIdle(order.getIdleId().intValue()).getData();
        // 通知买家卖家订单创建成功
        UserNoticeDO buyerNotice = new UserNoticeDO();
        buyerNotice.setUserId(order.getBuyerId().intValue());
        buyerNotice.setType(UserNoticeType.IDLE_BUY);
        buyerNotice.setTitle("已成功下单");
        buyerNotice.setContent("您购买的闲置：" + idleDO.getTitle() + "的订单已经创建成功，点击查看订单详情。");
        buyerNotice.setNoticeTime(new Date());
        Map<String, Object> keyValue = new HashMap<>();
        keyValue.put("orderId", order.getIdleId());
        buyerNotice.setKeyValue(gson.toJson(keyValue));
        userNoticeService.saveUserNotice(buyerNotice);

        UserNoticeDO sellerNotice = new UserNoticeDO();
        sellerNotice.setUserId(order.getSellerId().intValue());
        sellerNotice.setType(UserNoticeType.IDLE_SELL);
        sellerNotice.setTitle("闲置已经成功卖出");
        sellerNotice.setContent("您上架的闲置：" + idleDO.getTitle() + "的订单已经创建成功，点击查看订单详情。");
        sellerNotice.setNoticeTime(new Date());
        sellerNotice.setKeyValue(gson.toJson(keyValue));
        userNoticeService.saveUserNotice(sellerNotice);


        return selectByPrimaryKey(order.getId());
    }

    @Override
    public Result<List<OrderDO>> selectBySellerId(Long id) {
        List<OrderDO> orderDO = mapper.selectBySellerId(id);
        if (null == orderDO || orderDO.size() == 0) {
            return Result.fail(ErrorCode.INVALID_PARAMETER_NOT_FOUND, "The specified order for sellerId="
                    + id + " does not exist.");
        }
        return Result.success(orderDO);
    }

    @Override
    public Result<List<OrderDO>> selectByBuyerId(Long id) {
        List<OrderDO> orderDO = mapper.selectByBuyerId(id);
        if (null == orderDO || orderDO.size() == 0) {
            return Result.fail(ErrorCode.INVALID_PARAMETER_NOT_FOUND, "The specified order for buyerId="
                    + id + " does not exist.");
        }
        return Result.success(orderDO);
    }

    @Override
    public Result<OrderDO> updateOrder(OrderDO order) {
        OrderDO orderDO = new OrderDO();
        orderDO.setId(order.getId());
        orderDO.setStatus(order.getStatus());
        orderDO.setFreight(order.getFreight());
        orderDO.setActualPay(order.getActualPay());
        orderDO.setTotalPrice(order.getTotalPrice());
        if (BeanUtils.allFieldIsNull(orderDO)) {
            return Result.fail(ErrorCode.INVALID_PARAMETER_IS_BLANK,
                    "The required parameter must be not all null.");
        }

        int count = mapper.updateOrder(orderDO);
        if (count < 1) {
            logger.error("Update idle failed. orderId: {}", orderDO.getId());
            return Result.fail(ErrorCode.INTERNAL_ERROR, "Update order failed.");
        }
        return selectByPrimaryKey(order.getId());
    }

}
