package com.wudagezhandui.shixun.xianyu.pojo.do0;

import com.wudagezhandui.shixun.xianyu.pojo.modules.TheStatus;

import java.math.BigDecimal;
import java.util.Date;

public class OrderDO {
    private Long id;//自增 订单号
    private Date createTime;
    private Date updateTime;
    private Date transactionTime;//交易时间
    private Long sellerId;//卖家号
    private Long buyerId; //买家号
    private Long idleId;//商品号
    private BigDecimal freight;//运费
    private BigDecimal actualPay;//实际付款
    private BigDecimal totalPrice;//总价
    private TheStatus status;//订单状态
    private String aliPayNumber;//支付宝支付号
    private Long addressId;//地址



    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getTransactionTime() {
        return transactionTime;
    }

    public void setTransactionTime(Date transactionTime) {
        this.transactionTime = transactionTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }

    public Long getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(Long buyerId) {
        this.buyerId = buyerId;
    }

    public Long getIdleId() {
        return idleId;
    }

    public void setIdleId(Long idleId) {
        this.idleId = idleId;
    }

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
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


}
