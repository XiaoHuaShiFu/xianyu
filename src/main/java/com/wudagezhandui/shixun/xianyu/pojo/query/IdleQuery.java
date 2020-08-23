package com.wudagezhandui.shixun.xianyu.pojo.query;

import java.util.List;

public class IdleQuery {

    private Integer pageNum = 1;

    private Integer pageSize = 10;

    private Integer id;

    private List<Integer> idList;

    /**
     * 模糊搜素
     */
    private String idlename;

    public IdleQuery(){

    }

    public IdleQuery(Integer pageNum, Integer pageSize, Integer id, List<Integer> idList, String idlename){
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.id = id;
        this.idList = idList;
        this.idlename = idlename;
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

    public String getIdlename() {
        return idlename;
    }

    public void setIdlename(String idlename) {
        this.idlename = idlename;
    }

    @Override
    public String toString() {
        return "IdleQuery{" +
                "pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                ", id=" + id +
                ", idList=" + idList +
                ", idlename='" + idlename +
                '}';
    }
}
