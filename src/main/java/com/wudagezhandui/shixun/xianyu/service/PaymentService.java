package com.wudagezhandui.shixun.xianyu.service;

import com.wudagezhandui.shixun.xianyu.pojo.do0.PaymentDO;
import com.wudagezhandui.shixun.xianyu.result.Result;

import java.util.Map;

public interface PaymentService {

    Result<String> pay(Long orderNo);

    Result aliCallback(Map<String, String> params);

    Result queryOrderPayStatus(Long orderNo);

    Result<PaymentDO> getPayment(Integer id);
}
