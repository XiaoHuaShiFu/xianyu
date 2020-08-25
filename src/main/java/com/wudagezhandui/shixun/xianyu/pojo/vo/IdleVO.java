package com.wudagezhandui.shixun.xianyu.pojo.vo;

import com.wudagezhandui.shixun.xianyu.pojo.do0.IdleDO;


import java.math.BigDecimal;

public class IdleVO {

    private Integer id;

    private Integer userId;

    private BigDecimal price;

    private String title;

    private String detail;

    private String image;

    private IdleDO.Status status;

    public IdleVO(){

    }

    public IdleVO(Integer id, Integer userId, BigDecimal price, String title, String detail, String image, IdleDO.Status status) {
        this.id = id;
        this.userId = userId;
        this.price = price;
        this.title = title;
        this.detail = detail;
        this.image = image;
        this.status = status;
    }

    @Override
    public String toString() {
        return "IdleDO{" +
                "id=" + id +
                ", userid='" + userId + '\'' +
                ", price='" + price + '\'' +
                ", title='" + title + '\'' +
                ", detail='" + detail + '\'' +
                ", image='" + image + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
