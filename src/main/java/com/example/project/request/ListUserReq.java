package com.example.project.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;

import java.sql.Timestamp;

public class ListUserReq {
    private String fullName;
    private int pageNumber;
    private int pageSize;

    public ListUserReq(String fullName, int pageNumber, int pageSize) {
        this.fullName = fullName;
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
    }

    public ListUserReq(String fullName) {
        this.fullName = fullName;
    }

    public ListUserReq() {
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }


    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
