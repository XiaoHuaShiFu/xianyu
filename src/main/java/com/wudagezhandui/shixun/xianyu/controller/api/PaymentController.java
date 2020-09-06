package com.wudagezhandui.shixun.xianyu.controller.api;

import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.demo.trade.config.Configs;
import com.wudagezhandui.shixun.xianyu.aspect.annotation.ErrorHandler;
import com.wudagezhandui.shixun.xianyu.auth.TokenAuth;
import com.wudagezhandui.shixun.xianyu.constant.AlipayConst;
import com.wudagezhandui.shixun.xianyu.constant.TokenType;
import com.wudagezhandui.shixun.xianyu.pojo.do0.PaymentDO;
import com.wudagezhandui.shixun.xianyu.result.ErrorCode;
import com.wudagezhandui.shixun.xianyu.result.Result;
import com.wudagezhandui.shixun.xianyu.service.PaymentService;
import com.wudagezhandui.shixun.xianyu.validator.annotation.Id;
import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@RestController
@RequestMapping("v1/payments")
@Validated
public class PaymentController {

    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    private final Mapper mapper;

    private final PaymentService paymentService;

    @Autowired
    public PaymentController(Mapper mapper, PaymentService paymentService) {
        this.mapper = mapper;
        this.paymentService = paymentService;
    }

    /**
     * 对指定账单发起付款
     * @param orderNo
     * @return 返回二维码的url
     */
    @RequestMapping(value = "/pay.do/{orderNo}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @TokenAuth(tokenType = TokenType.USER)
    @ErrorHandler
    public Object pay(@PathVariable @Id Long orderNo) {

        Result<String> result = paymentService.pay(orderNo);
        return result.isSuccess() ? mapper.map(result.getData(), String.class) : result;
    }

    /**
     * 获取支付记录
     * @param id 支付记录编号
     * @return PaymentDO
     */
    @RequestMapping(value="/ID/{id}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ErrorHandler
    public Object get(@PathVariable @Id Integer id) {
        Result<PaymentDO> result = paymentService.getPayment(id);
        return !result.isSuccess() ? result : mapper.map(result.getData(), PaymentDO.class);
    }

    /**
     * 前端可用于查询订单是否已支付
     * @param orderNo
     * @return Boolean 是否已支付
     */
    @RequestMapping("query_order_pay_status.do")
    @ResponseBody
    public Result<Boolean> queryOrderPayStatus(Long orderNo) {
        Result result = paymentService.queryOrderPayStatus(orderNo);
        if(result.isSuccess()){
            return Result.success(true);
        }
        return Result.success(false);
    }

    /**
     * 支付宝回调处理
     * @param request
     * @return
     */
    @RequestMapping("alipay_callback.do")
    @ResponseBody
    public Object alipayCallback(HttpServletRequest request) {
        Map<String,String> params = new HashMap<>();

        Map requestParams = request.getParameterMap();
        for(Iterator iter = requestParams.keySet().iterator(); iter.hasNext();){
            String name = (String)iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for(int i = 0 ; i <values.length;i++){

                valueStr = (i == values.length -1)?valueStr + values[i]:valueStr + values[i]+",";
            }
            params.put(name,valueStr);
        }
        logger.info("支付宝回调,sign:{},trade_status:{},参数:{}",params.get("sign"),params.get("trade_status"),params.toString());

        //验证回调的正确性,是不是支付宝发的.避免重复通知.

        params.remove("sign_type");
        try {
            boolean alipayRSACheckedV2 = AlipaySignature.rsaCheckV2(params, Configs.getAlipayPublicKey(),"utf-8",Configs.getSignType());

            if(!alipayRSACheckedV2){
                return Result.fail(ErrorCode.INTERNAL_ERROR, "非法请求,验证不通过,再恶意请求我就报警找网警了");
            }
        } catch (AlipayApiException e) {
            logger.error("支付宝验证回调异常",e);
        }

        Result result = paymentService.aliCallback(params);
        if(result.isSuccess()) {
            return AlipayConst.Response.SUCCESS;
        }
        return AlipayConst.Response.FAILED;
    }
}
