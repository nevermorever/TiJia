package com.zyjd.tijia.api;

import java.util.List;


/*
* 统一的分页请求，返回数据格式
* count 当前页码
* next 下一页api地址
* previous 上一页api地址
* results 实际数据列表
*{
*   "count": 1,
*   "next": null,
*   "previous": null,
*   "results":
*   [
*        {
*            "key": "value",
*        }
*   ]
*}
*/
public class PaginationedResult<T> {
    private int count;
    private String next;
    private String previous;
    private List<T> results;

    public int getCount() {
        return count;
    }

    public String getNext() {
        return next;
    }

    public String getPrevious() {
        return previous;
    }

    public List<T> getResults() {
        return results;
    }

    @Override
    public String toString() {
        return "Result{" +
                "count=" + count +
                ", next=" + next +
                ", previous=" + previous +
                ", results=" + results +
                '}';
    }
}
