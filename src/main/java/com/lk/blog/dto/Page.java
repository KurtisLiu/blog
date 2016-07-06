package com.lk.blog.dto;

import org.apache.ibatis.type.Alias;

import java.util.List;

@Alias("page")
public class Page<T> {
    /**
     * start from 0
     */
    private int pageNow = 0;
    private int pageSize = 20;
    private int totalNumber;
    private String query;
    private List<T> list;

    public int getPageNow() {
        return pageNow;
    }

    public void setPageNow(int pageNow) {
        this.pageNow = pageNow;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalNumber() {
        return totalNumber;
    }

    public void setTotalNumber(int totalNumber) {
        this.totalNumber = totalNumber;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public int getTotalPage() {
        if (this.pageSize == 0) {
            return 0;
        }
        return (int)Math.ceil(this.totalNumber / (double)this.pageSize);
    }

    public int getOffset() {
        return this.pageSize * this.pageNow;
    }

}
