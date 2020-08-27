package com.wudagezhandui.shixun.xianyu.pojo.query;

import com.wudagezhandui.shixun.xianyu.pojo.do0.IdleDO;

import java.math.BigDecimal;

public class SearchQuery {

    private Integer pageNum = 1;

    private Integer pageSize = 10;

    private IdleDO.Status status;

    /**
     * 最大价格
     */
    private BigDecimal maxPrice;

    /**
     * 搜索关键字
     */
    private String keyword;

    public SearchQuery() {}

    public SearchQuery(Integer pageNum, Integer pageSize, IdleDO.Status status, BigDecimal maxPrice, String keyword) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.status = status;
        this.maxPrice = maxPrice;
        this.keyword = keyword;
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

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public String toString() {
        return "SearchQuery{" +
                "pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                ", status=" + status +
                ", maxPrice=" + maxPrice +
                ", keyword='" + keyword + '\'' +
                '}';
    }
}
