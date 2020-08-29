package com.wudagezhandui.shixun.xianyu.pojo.do0;

import com.wudagezhandui.shixun.xianyu.pojo.group.Group;
import com.wudagezhandui.shixun.xianyu.validator.annotation.Id;

import java.io.File;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 描述:
 *
 *
 * @author Tsukikoillya
 */
public class IdleDO {

    public enum Status{
        NORMAL,
        SELLED,
        OFF
    }

    @Id(groups = {Group.class})
    private Integer id;

    //@Id(groups = {Group.class})
    private Integer user_id;

    private BigDecimal price;

    private BigDecimal postage;

    private String title;

    private String detail;

    private String image;

   // private MultipartFile images;

    private Status status;

    private Date createTime;

    private Date updateTime;

    public IdleDO(){

    }

    public IdleDO(Integer id,Integer userId, BigDecimal price, BigDecimal postage, String title, String detail, String image,
                  Status status, Date createTime, Date updateTime) {
        this.id = id;
        this.user_id = userId;
        this.price = price;
        this.postage = postage;
        this.title = title;
        this.detail = detail;
        this.image = image;
        this.status = status;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return user_id;
    }

    public void setUserId(Integer userId) {
        this.user_id = userId;
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
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

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }


    @Override
    public String toString() {
        return "IdleDO{" +
                "id=" + id +
                ", userid='" + user_id + '\'' +
                ", price='" + price + '\'' +
                ", postage='" + postage + '\'' +
                ", title='" + title + '\'' +
                ", detail='" + detail + '\'' +
                ", image='" + image + '\'' +
                ", status='" + status + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
