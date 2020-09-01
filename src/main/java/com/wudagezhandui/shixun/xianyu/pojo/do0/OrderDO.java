package com.wudagezhandui.shixun.xianyu.pojo.do0;

import com.wudagezhandui.shixun.xianyu.pojo.modules.TheStatus;

import java.math.BigDecimal;
import java.math.BigInteger;
public class OrderDO {
    private BigInteger id;//自增 订单号
    private String create_time;
    private String update_time;
    private String transaction_time;//交易时间
    private BigInteger seller_id;//卖家号
    private BigInteger buyer_id; //买家号
    private BigInteger idle_id;//商品号
    private BigDecimal freight;//运费
    private BigDecimal actual_pay;//实际付款
    private BigDecimal total_price;//总价
    private TheStatus status;//订单状态
    private String ali_pay_number;//支付宝支付号
    private BigInteger address_id;//地址





    public TheStatus getStatus() {
        return status;
    }
    public void setStatus(TheStatus status) {
        this.status = status;
    }
    public BigInteger getId() {
        return id;
    }
    public void setId(BigInteger id) {
        this.id = id;
    }



    public String getCreate_time() {
        return create_time;
    }
    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }
    public String getUpdate_time() {
        return update_time;
    }
    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }
    public String getTransaction_time() {
        return transaction_time;
    }
    public void setTransaction_time(String transaction_time) {
        this.transaction_time = transaction_time;
    }
    public BigInteger getSeller_id() {
        return seller_id;
    }

    public void setSeller_id(BigInteger seller_id) {
        this.seller_id = seller_id;
    }
    public BigInteger getBuyer_id() {
        return buyer_id;
    }
    public void setBuyer_id(BigInteger buyer_id) {
        this.buyer_id = buyer_id;
    }
    public BigInteger getIdle_id() {
        return idle_id;
    }
    public void setIdle_id(BigInteger idle_id) {
        this.idle_id = idle_id;
    }
    public BigDecimal getFreight() {
        return freight;
    }
    public void setFreight(BigDecimal freight) {
        this.freight = freight;
    }
    public BigDecimal getActual_pay() {
        return actual_pay;
    }
    public void setActual_pay(BigDecimal actual_pay) {
        this.actual_pay = actual_pay;
    }
    public BigDecimal getTotal_price() {
        return total_price;
    }
    public void setTotal_price(BigDecimal total_price) {
        this.total_price = total_price;
    }
    public String getAli_pay_number() {
        return ali_pay_number;
    }
    public void setAli_pay_number(String ali_pay_number) {
        this.ali_pay_number = ali_pay_number;
    }
    public BigInteger getAddress_id() {
        return address_id;
    }
    public void setAddress_id(BigInteger address_id) {
        this.address_id = address_id;
    }


}
