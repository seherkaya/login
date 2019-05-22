package com.gpch.login.model;

import org.springframework.data.domain.Page;

import java.util.LinkedList;

public class CustomPageable {

    private boolean isFirst;
    private boolean isLast;
    private int number;
    private int totalPages;
    private long totalElements;
    private LinkedList<Object> list;

    public CustomPageable(Page page) {
        list = new LinkedList<>(page.getContent());
        totalElements = page.getTotalElements();
        totalPages = page.getTotalPages();
        isFirst = page.isFirst();
        isLast = page.isLast();
        number = page.getNumber();
    }

    public boolean isFirst() {
        return isFirst;
    }

    public void setFirst(boolean first) {
        isFirst = first;
    }

    public boolean isLast() {
        return isLast;
    }

    public void setLast(boolean last) {
        isLast = last;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }

    public LinkedList<Object> getList() {
        return list;
    }

    public void setList(LinkedList<Object> list) {
        this.list = list;
    }
}
