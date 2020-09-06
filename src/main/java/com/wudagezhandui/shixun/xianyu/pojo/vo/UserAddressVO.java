package com.wudagezhandui.shixun.xianyu.pojo.vo;

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

    private String fullName;

    private String phone;

    public UserAddressVO() {
    }

    public UserAddressVO(Integer id, Integer userId, String address, String fullName, String phone) {
        this.id = id;
        this.userId = userId;
        this.address = address;
        this.fullName = fullName;
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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
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
                ", fullname='" + fullName + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
