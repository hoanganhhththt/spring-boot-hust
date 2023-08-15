package com.example.project.request;

import com.example.project.entity.User;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class UpdateTransactionReq {
    @NotNull(message = "ID không được bỏ trống")
    private int id;
    @NotNull(message = "Món hàng không được bỏ trống")
    private String title;
    @NotNull(message = "Số tiền trung bình không được bỏ trống")
    private BigDecimal averageAmount;
    @NotNull(message = "Giá tiền không được bỏ trống")
    private BigDecimal feeAmount;
    @NotNull(message = "Ghi chú không được bỏ trống")
    private String note;

    @NotNull(message = "Người sử dụng không được bỏ trống")
    private List<Integer> lstUser;
    @NotNull(message = "Người mua không được bỏ trống")
    private Integer userBuy;

    public UpdateTransactionReq() {
    }

    public UpdateTransactionReq(int id, String title, BigDecimal averageAmount, BigDecimal feeAmount, String note, List<Integer> lstUser, Integer userBuy) {
        this.id = id;
        this.title = title;
        this.averageAmount = averageAmount;
        this.feeAmount = feeAmount;
        this.note = note;
        this.lstUser = lstUser;
        this.userBuy = userBuy;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getFeeAmount() {
        return feeAmount;
    }

    public void setFeeAmount(BigDecimal feeAmount) {
        this.feeAmount = feeAmount;
    }

    public List<Integer> getLstUser() {
        return lstUser;
    }

    public void setLstUser(List<Integer> lstUser) {
        this.lstUser = lstUser;
    }

    public Integer getUserBuy() {
        return userBuy;
    }

    public void setUserBuy(Integer userBuy) {
        this.userBuy = userBuy;
    }

    public BigDecimal getAverageAmount() {
        return averageAmount;
    }

    public void setAverageAmount(BigDecimal averageAmount) {
        this.averageAmount = averageAmount;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

}
