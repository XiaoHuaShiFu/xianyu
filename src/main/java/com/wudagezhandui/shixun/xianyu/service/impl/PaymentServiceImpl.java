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
import com.wudagezhandui.shixun.xianyu.constant.AlipayConst;
import com.wudagezhandui.shixun.xianyu.dao.PaymentMapper;
import com.wudagezhandui.shixun.xianyu.pojo.do0.IdleDO;
import com.wudagezhandui.shixun.xianyu.pojo.do0.OrderDO;
import com.wudagezhandui.shixun.xianyu.pojo.do0.PaymentDO;
import com.wudagezhandui.shixun.xianyu.result.ErrorCode;
import com.wudagezhandui.shixun.xianyu.result.Result;
import com.wudagezhandui.shixun.xianyu.service.FileService;
import com.wudagezhandui.shixun.xianyu.service.IdleService;
import com.wudagezhandui.shixun.xianyu.service.OrderService;
import com.wudagezhandui.shixun.xianyu.service.PaymentService;
import com.wudagezhandui.shixun.xianyu.service.constant.FileConstant;
import com.wudagezhandui.shixun.xianyu.service.constant.PaymentConstant;
import com.wudagezhandui.shixun.xianyu.util.DateTimeUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service("paymentService")
public class PaymentServiceImpl implements PaymentService {

    private static AlipayTradeService tradeService;
    static {

        /** 一定要在创建AlipayTradeService之前调用Configs.init()设置默认参数
         *  Configs会读取classpath下的zfbinfo.properties文件配置信息，如果找不到该文件则确认该文件是否在classpath目录
         */
        Configs.init("zfbinfo.properties");

        /** 使用Configs提供的默认参数
         *  AlipayTradeService可以使用单例或者为静态成员对象，不需要反复new
         */
        tradeService = new AlipayTradeServiceImpl.ClientBuilder().build();
    }

    private static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    private final PaymentMapper paymentMapper;

    private final OrderService orderService;
    private final IdleService idleService;
    private final FileService fileService;

    @Autowired
    public PaymentServiceImpl(PaymentMapper paymentMapper, OrderService orderService,
                              IdleService idleService, FileService fileService) {
        this.paymentMapper = paymentMapper;
        this.orderService = orderService;
        this.idleService = idleService;
        this.fileService = fileService;
    }

    @Override
    public Result<String> pay(Long orderNo) {
        OrderDO order = orderService.selectByPrimaryKey(orderNo).getData();
        if(order == null) {
            return Result.fail(ErrorCode.INVALID_PARAMETER_IS_BLANK,"订单不存在！");
        }

        // (必填) 商户网站订单系统中唯一订单号，64个字符以内，只能包含字母、数字、下划线，
        // 需保证商户系统端不能重复，建议通过数据库sequence生成，
        String outTradeNo = order.getId().toString();

        // (必填) 订单标题，粗略描述用户的支付目的。如“xxx品牌xxx门店当面付扫码消费”
        String subject = new StringBuilder().append("贤鱼扫码支付，订单号：").append(outTradeNo).toString();

        // (必填) 订单总金额，单位为元，不能超过1亿元
        // 如果同时传入了【打折金额】,【不可打折金额】,【订单总金额】三者,则必须满足如下条件:【订单总金额】=【打折金额】+【不可打折金额】
        String totalAmount = order.getTotalPrice().toString();

        // (可选) 订单不可打折金额，可以配合商家平台配置折扣活动，如果酒水不参与打折，则将对应金额填写至此字段
        // 如果该值未传入,但传入了【订单总金额】,【打折金额】,则该值默认为【订单总金额】-【打折金额】
        String undiscountableAmount = "0";

        // 卖家支付宝账号ID，用于支持一个签约账号下支持打款到不同的收款账号，(打款到sellerId对应的支付宝账号)
        // 如果该字段为空，则默认为与支付宝签约的商户的PID，也就是appid对应的PID
        String sellerId = "";

        // 订单描述，可以对交易或商品进行一个详细地描述，比如填写"购买商品2件共15.00元"
        String body = new StringBuilder().append(outTradeNo).append("购买商品共").append(totalAmount).toString();

        // 商户操作员编号，添加此参数可以为商户操作员做销售统计
        String operatorId = "test_operator_id";

        // (必填) 商户门店编号，通过门店号和商家后台可以配置精准到门店的折扣信息，详询支付宝技术支持
        String storeId = "test_store_id";

        // 业务扩展参数，目前可添加由支付宝分配的系统商编号(通过setSysServiceProviderId方法)，详情请咨询支付宝技术支持
        ExtendParams extendParams = new ExtendParams();
        extendParams.setSysServiceProviderId("2088100200300400500");

        // 支付超时，定义为120分钟
        String timeoutExpress = "120m";

        // 商品明细列表，需填写购买商品详细信息，
        List<GoodsDetail> goodsDetailList = new ArrayList<GoodsDetail>();
        IdleDO idleDO = idleService.getIdle(order.getIdleId().intValue()).getData();
        GoodsDetail goods = GoodsDetail.newInstance(idleDO.getId().toString(), idleDO.getTitle(),
                idleDO.getPrice().multiply(new BigDecimal("100")).longValue(), 1);
        // 创建好一个商品后添加至商品明细列表
        goodsDetailList.add(goods);

        // 创建扫码支付请求builder，设置请求参数
        AlipayTradePrecreateRequestBuilder builder = new AlipayTradePrecreateRequestBuilder()
                .setSubject(subject).setTotalAmount(totalAmount).setOutTradeNo(outTradeNo)
                .setUndiscountableAmount(undiscountableAmount).setSellerId(sellerId).setBody(body)
                .setOperatorId(operatorId).setStoreId(storeId).setExtendParams(extendParams)
                .setTimeoutExpress(timeoutExpress)
                .setNotifyUrl("https://xiaohuashifu.top/v1/payments/alipay_callback.do")//支付宝服务器主动通知商户服务器里指定的页面http路径,根据需要设置
                .setGoodsDetailList(goodsDetailList);

        AlipayF2FPrecreateResult result = tradeService.tradePrecreate(builder);
        switch (result.getTradeStatus()) {
            case SUCCESS:
                logger.info("支付宝预下单成功: )");

                AlipayTradePrecreateResponse response = result.getResponse();
                dumpResponse(response);

                String path = FileConstant.HOST + PaymentConstant.PREFIX_QRCODE_FILE_DIRECTORY;

                // 需要修改为运行机器上的路径
                String qrPath = String.format(path+"qr-%s.png", response.getOutTradeNo());
                File targetFile = ZxingUtils.getQRCodeImge(response.getQrCode(), 256, qrPath);

                fileService.save(path, targetFile);

                logger.info("qrPath:" + qrPath);
                return Result.success(qrPath);

            case FAILED:
                return Result.fail(ErrorCode.INTERNAL_ERROR, "支付宝预下单失败!!!");

            case UNKNOWN:
                return Result.fail(ErrorCode.INTERNAL_ERROR, "系统异常，预下单状态未知!!!");

            default:
                return Result.fail(ErrorCode.INTERNAL_ERROR, "不支持的交易状态，交易返回异常!!!");
        }

    }
    // 简单打印应答
    private void dumpResponse(AlipayResponse response) {
        if (response != null) {
            logger.info(String.format("code:%s, msg:%s", response.getCode(), response.getMsg()));
            if (StringUtils.isNotEmpty(response.getSubCode())) {
                logger.info(String.format("subCode:%s, subMsg:%s", response.getSubCode(),
                        response.getSubMsg()));
            }
            logger.info("body:" + response.getBody());
        }
    }

    @Override
    public Result aliCallback(Map<String, String> params) {
        Long orderNo = Long.parseLong(params.get("out_trade_no"));
        String tradeNo = params.get("trade_no");
        String tradeStatus = params.get("trade_status");
        OrderDO order = orderService.selectByPrimaryKey(orderNo).getData();
        if(order == null) {
            return Result.fail(ErrorCode.INVALID_PARAMETER_VALUE ,"非贤鱼订单，回调忽略");
        }
        if(order.getStatus().getStatus2().equals("已付款")) {
            return Result.success("支付宝重复调用");
        }
        if(AlipayConst.TradeStatus.TRADE_SUCCESS.equals(tradeStatus)) {
            order.setUpdateTime(DateTimeUtil.strToDate(params.get("gmt_payment")));
            order.getStatus().setStatus2("已付款");
            orderService.updateOrder(order);
        }

        PaymentDO payment = new PaymentDO();
        payment.setUserId(order.getBuyerId().intValue());
        payment.setOrderNo(order.getId());
        paymentMapper.insert(payment);

        return Result.success();

    }

    @Override
    public Result queryOrderPayStatus(Long orderNo) {
        OrderDO order = orderService.selectByPrimaryKey(orderNo).getData();
        if(order == null) {
            return Result.fail(ErrorCode.INVALID_PARAMETER_VALUE ,"非贤鱼订单");
        }
        if(order.getStatus().getStatus2().equals("已付款")) {
            return Result.success();
        }
        return Result.fail(ErrorCode.INTERNAL_ERROR, "订单未付款");
    }

    @Override
    public Result<PaymentDO> getPayment(Integer id) {
        PaymentDO paymentDO = paymentMapper.selectByPrimaryKey(id);
        if(paymentDO == null) {
            return Result.fail(ErrorCode.INVALID_PARAMETER_NOT_FOUND,
                    "The specified payment does not exist.");
        }
        return Result.success(paymentDO);
    }
}
