package com.wudagezhandui.shixun.xianyu.pojo.vo;

import com.wudagezhandui.shixun.xianyu.pojo.modules.TheStatus;

import java.math.BigDecimal;
import java.util.Date;

public class OrderVO {
    private Long id;//自增 订单号
    private Date transactionTime;//交易时间
    private UserVO seller ;//卖家
    private UserVO buyer; //买家
    private IdleVO idle;//商品
    private BigDecimal freight;//运费
    private BigDecimal actualPay;//实际付款
    private BigDecimal totalPrice;//总价
    private TheStatus status;//订单状态
    private String aliPayNumber;//支付宝支付号
    private UserAddressVO address;//地址id

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getTransactionTime() {
        return transactionTime;
    }

    public void setTransactionTime(Date transactionTime) {
        this.transactionTime = transactionTime;
    }

    public UserVO getSeller() {
        return seller;
    }

    public void setSeller(UserVO seller) {
        this.seller = seller;
    }

    public UserVO getBuyer() {
        return buyer;
    }

    public void setBuyer(UserVO buyer) {
        this.buyer = buyer;
    }

    public IdleVO getIdle() {
        return idle;
    }

    public void setIdle(IdleVO idle) {
        this.idle = idle;
    }

    public BigDecimal getFreight() {
        return freight;
    }

    public void setFreight(BigDecimal freight) {
        this.freight = freight;
    }

    public BigDecimal getActualPay() {
        return actualPay;
    }

    public void setActualPay(BigDecimal actualPay) {
        this.actualPay = actualPay;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public TheStatus getStatus() {
        return status;
    }

    public void setStatus(TheStatus status) {
        this.status = status;
    }

    public String getAliPayNumber() {
        return aliPayNumber;
    }

    public void setAliPayNumber(String aliPayNumber) {
        this.aliPayNumber = aliPayNumber;
    }

    public UserAddressVO getAddress() {
        return address;
    }

    public void setAddress(UserAddressVO address) {
        this.address = address;
    }
}
