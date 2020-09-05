package com.wudagezhandui.shixun.xianyu.pojo.query;

import java.util.List;

/**
 * 描述:
 *
 * @author xhsf
 * @email 827032783@qq.com
 */
public class UserAddressQuery {

    private Integer pageNum = 1;

    private Integer pageSize = 10;

    private Integer id;

    private List<Integer> idList;

    private Integer userId;


    /**
     * 模糊搜素
     */
    private String address;

    private String fullname;

    private String phone;

    /**
     * 是否是通过count搜索
     */
    private Boolean count;

    public UserAddressQuery() {
    }

    public UserAddressQuery(Integer pageNum, Integer pageSize, Integer id, List<Integer> idList, Integer userId,
                             String address, String fullname, String phone ,Boolean count) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.id = id;
        this.idList = idList;
        this.userId = userId;
        this.address = address;
        this.fullname = fullname;
        this.phone = phone;
        this.count = count;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Integer> getIdList() {
        return idList;
    }

    public void setIdList(List<Integer> idList) {
        this.idList = idList;
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

    public Boolean getCount() {
        return count;
    }

    public void setCount(Boolean count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "UserAddressQuery{" +
                "pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                ", id=" + id +
                ", idList=" + idList +
                ", userId=" + userId +
                ", address='" + address + '\'' +
                ", fullname='" + fullname + '\'' +
                ", phone='" + phone + '\'' +
                ", count=" + count +
                '}';
    }
}
