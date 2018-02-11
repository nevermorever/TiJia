package com.zyjd.tijia.entity;

import java.util.List;

public class PaginatedResult<T> {
    private int count;
    private String previous;
    private String next;
    private List<T> results;

    public int getCount() {
        return count;
    }

    public String getPrevious() {
        return previous;
    }

    public String getNext() {
        return next;
    }

    public List<T> getResults() {
        return results;
    }
}
