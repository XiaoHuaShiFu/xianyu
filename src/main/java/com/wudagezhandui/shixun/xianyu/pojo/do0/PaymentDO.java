package com.wudagezhandui.shixun.xianyu.pojo.do0;

import java.util.Date;

public class PaymentDO {
    private Integer id;

    private Integer userId;

    private Long orderNo;

    private Date createTime;

    private Date updateTime;

    public PaymentDO(Integer id, Integer userId, Long orderNo, Date createTime, Date updateTime) {
        this.id = id;
        this.userId = userId;
        this.orderNo = orderNo;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public PaymentDO() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Long getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Long orderNo) {
        this.orderNo = orderNo;
    }

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
}
