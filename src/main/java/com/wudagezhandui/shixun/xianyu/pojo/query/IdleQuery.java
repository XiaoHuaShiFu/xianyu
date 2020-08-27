package com.wudagezhandui.shixun.xianyu.pojo.query;

import com.wudagezhandui.shixun.xianyu.pojo.do0.IdleDO;

import java.math.BigDecimal;
import java.util.List;

public class IdleQuery {

    private Integer pageNum = 1;

    private Integer pageSize = 10;

    private Integer id;

    private Integer userId;

    private IdleDO.Status status;

    /**
     * 最大价格
     */
    private BigDecimal maxPrice;

    private List<Integer> idList;

    /**
     * 模糊搜素
     */
    private String name;

    /**
     * 通过描述进行模糊搜索
     */
    private String detail;

    public IdleQuery() {}

    public IdleQuery(Integer pageNum, Integer pageSize, Integer id, Integer userId, IdleDO.Status status,
                     BigDecimal maxPrice, List<Integer> idList, String name, String detail) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.id = id;
        this.userId = userId;
        this.status = status;
        this.maxPrice = maxPrice;
        this.idList = idList;
        this.name = name;
        this.detail = detail;
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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public IdleDO.Status getStatus() {
        return status;
    }

    public void setStatus(IdleDO.Status status) {
        this.status = status;
    }

    public BigDecimal getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(BigDecimal maxPrice) {
        this.maxPrice = maxPrice;
    }

    public List<Integer> getIdList() {
        return idList;
    }

    public void setIdList(List<Integer> idList) {
        this.idList = idList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    @Override
    public String toString() {
        return "IdleQuery{" +
                "pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                ", id=" + id +
                ", userId=" + userId +
                ", status=" + status +
                ", maxPrice=" + maxPrice +
                ", idList=" + idList +
                ", name='" + name + '\'' +
                ", detail='" + detail + '\'' +
                '}';
    }
}
