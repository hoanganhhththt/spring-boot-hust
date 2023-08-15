package com.example.project.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;

import java.sql.Timestamp;

public class ListTransactionReq {

    @NotNull(message = "Từ ngày không được bỏ trống")
    @JsonFormat(
            shape = JsonFormat.Shape.STRING,
            pattern = "dd/MM/yyyy HH:mm:ss",
            timezone = "Asia/Ho_Chi_Minh"
    )
    private Timestamp fromDate;

    @NotNull(message = "Từ ngày không được bỏ trống")
    @JsonFormat(
            shape = JsonFormat.Shape.STRING,
            pattern = "dd/MM/yyyy HH:mm:ss",
            timezone = "Asia/Ho_Chi_Minh"
    )
    private Timestamp toDate;
    private Integer userBuy;
    @NotNull(message = "Số trang không được bỏ trống")
    private int pageNumber;
    @NotNull(message = "Số lượng phần tử mỗi trang không được bỏ trống")
    private int pageSize;

    public ListTransactionReq() {
    }

    public ListTransactionReq(Timestamp fromDate, Timestamp toDate, Integer userBuy, Integer pageNumber, Integer pageSize) {
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.userBuy = userBuy;
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
    }

    public Timestamp getFromDate() {
        return fromDate;
    }

    public void setFromDate(Timestamp fromDate) {
        this.fromDate = fromDate;
    }

    public Timestamp getToDate() {
        return toDate;
    }

    public void setToDate(Timestamp toDate) {
        this.toDate = toDate;
    }

    public int getUserBuy() {
        return userBuy;
    }

    public void setUserBuy(int userBuy) {
        this.userBuy = userBuy;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public String toString() {
        return "ListTransaction{" +
                "fromDate=" + fromDate +
                ", toDate=" + toDate +
                ", userBuy=" + userBuy +
                ", pageNumber=" + pageNumber +
                ", pageSize=" + pageSize +
                '}';
    }
}

