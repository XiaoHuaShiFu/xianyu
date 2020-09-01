package com.wudagezhandui.shixun.xianyu.pojo.vo;

import com.wudagezhandui.shixun.xianyu.pojo.do0.IdleDO;


import java.math.BigDecimal;

public class IdleVO {

    private Integer id;

    private Integer userId;

    private BigDecimal price;

    private BigDecimal postage;

    private String title;

    private String detail;

    private String image;

    private IdleDO.Status status;

    public IdleVO(){

    }

    public IdleVO(Integer id, Integer userId, BigDecimal price, BigDecimal postage, String title, String detail, String image, IdleDO.Status status) {
        this.id = id;
        this.userId = userId;
        this.price = price;
        this.postage = postage;
        this.title = title;
        this.detail = detail;
        this.image = image;
        this.status = status;
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPostage() {
        return postage;
    }

    public void setPostage(BigDecimal postage) {
        this.postage = postage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public IdleDO.Status getStatus() {
        return status;
    }

    public void setStatus(IdleDO.Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "IdleDO{" +
                "id=" + id +
                ", userid='" + userId + '\'' +
                ", price='" + price + '\'' +
                ", postage='" + postage + '\'' +
                ", title='" + title + '\'' +
                ", detail='" + detail + '\'' +
                ", image='" + image + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
