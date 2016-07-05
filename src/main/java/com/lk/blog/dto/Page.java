package com.lk.blog.dto;

import java.io.Serializable;
import java.util.List;

public class Page<T> {
    /**
     * start from 0
     */
    private int pageNow = 0;
    private int pageSize = 20;
    private int totalNumber;
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

    public int getTotalPage() {
        if (this.pageSize == 0) {
            return 0;
        }
        return (int)Math.ceil(this.totalNumber / (double)this.pageSize);
    }

}
