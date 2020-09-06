package com.wudagezhandui.shixun.xianyu.constant;

public class AlipayConst {
    /**
     * alipay交易状态
     */
    public interface TradeStatus {
        String WAIT_BUYER_PAY = "WAIT_BUYER_PAY";
        String TRADE_CLOSED = "TRADE_CLOSED";
        String TRADE_SUCCESS = "TRADE_SUCCESS";
        String TRADE_FINISHED = "TRADE_FINISHED";
    }

    /**
     * 返回状态
     */
    public interface Response {
        String SUCCESS = "success";
        String FAILED = "failed";
    }
}
