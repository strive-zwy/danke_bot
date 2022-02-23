package com.danke.bean;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author ：zwy
 * @Date ：2021/1/25 13:15
 * @Version ：1.0
 * @Description ：分页
 **/
@Data
public class PageBean<T> {
    private List<T> list;

    private boolean showPrevious = true; // 是否显示前一页
    private boolean showFirstPage = true; // 是否显示第一页
    private boolean showNext = true; // 是否显示显示下一页
    private boolean showEndPage = true; // 是否显示最后一页
    private Integer page; // 当前页码
    private Integer totlePage; // 共有多少页
    private List<Integer> pages = new ArrayList<>(); // 页码列表

    public void setPagination(Integer totleCount, Integer page, Integer size) {
        this.page = page;
        this.totlePage = totleCount % size == 0 ? totleCount / size : totleCount / size + 1;
        for (int i = page-3; i < page+4; i++) {
            if (i > 0 && i <= totlePage) pages.add(i);
        }
        if (page == 1) showPrevious = false;
        if (page.equals(totlePage)) showNext = false;
        if (pages.contains(1)) showFirstPage = false;
        if (pages.contains(totlePage)) showEndPage = false;
    }
}
