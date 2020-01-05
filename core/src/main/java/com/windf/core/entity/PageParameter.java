package com.windf.core.entity;

import java.io.Serializable;

/**
 * 分页参数，一般用于接受分页参数
 */
public class PageParameter implements Serializable {

    private static final long default_index = 1L;
    private static final int default_size = 10;

    public static PageParameter getNeedNotPageInstance() {
        PageParameter pageParameter = new PageParameter();
        pageParameter.setIndex(-1L);
        pageParameter.setSize(-1);
        return pageParameter;
    }

    private Long index;
    private Integer size;

    public PageParameter() {
        index = default_index;
        size = default_size;
    }

    public PageParameter(Long pageIndex, Integer pageSize) {
        this.index = pageIndex;
        this.size = pageSize;
    }

    public boolean needNotPage() {
        return this.size < 0;
    }

    public Long getIndex() {
        return index;
    }

    public void setIndex(Long index) {
        this.index = index;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

}
