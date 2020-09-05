package com.wudagezhandui.shixun.xianyu.pojo.vo;

import com.wudagezhandui.shixun.xianyu.constant.UserNoticeType;

import java.util.Date;

/**
 * 描述:
 *
 * @author xhsf
 * @email 827032783@qq.com
 */
public class UserAddressVO {

    private Integer id;

    private Integer userId;

    private String address;

    private String fullname;

    private String phone;

    public UserAddressVO() {
    }

    public UserAddressVO(Integer id, Integer userId, String address, String fullname, String phone) {
        this.id = id;
        this.userId = userId;
        this.address = address;
        this.fullname = fullname;
        this.phone = phone;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "UserAddressVO{" +
                "id=" + id +
                ", userId=" + userId +
                ", address='" + address + '\'' +
                ", fullname='" + fullname + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
