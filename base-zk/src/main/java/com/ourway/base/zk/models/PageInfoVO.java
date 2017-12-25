package com.ourway.base.zk.models;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/17.
 */
public class PageInfoVO implements Serializable {
    private Integer currentIndex;
    private Integer totalCount;
    private Integer currentPage;
    private Integer totalPage;
    private List<Map<String, Object>> records;

    public Integer getCurrentIndex() {
        return currentIndex;
    }

    public void setCurrentIndex(Integer currentIndex) {
        this.currentIndex = currentIndex;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public List<Map<String, Object>> getRecords() {
        return records;
    }

    public void setRecords(List<Map<String, Object>> records) {
        this.records = records;
    }
}
