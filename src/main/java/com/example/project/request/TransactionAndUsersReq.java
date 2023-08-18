package com.example.project.request;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class TransactionAndUsersReq {

    @NotNull(message = "Mặt hàng không được bỏ trống")
    private String title;

    @NotNull(message = "Ngày mua không được bỏ trống")
    @JsonFormat(
            shape = JsonFormat.Shape.STRING,
            pattern = "dd/MM/yyyy HH:mm:ss",
            timezone = "Asia/Ho_Chi_Minh"
    )
    private LocalDateTime dateTime;

    @NotNull(message = "Số tiền trung bình không được bỏ trống")
    private BigDecimal averageAmount;

    @NotNull(message = "Số tiền mua hàng không được bỏ trống")
    private BigDecimal feeAmount;

    @NotNull(message = "Ghi chú không được bỏ trống")
    private String note;

    @NotNull(message = "Người sử dụng không được bỏ trống")
    private List<Integer> lstUser;

    @NotNull(message = "Người mua không được bỏ trống")
    private int userBuy;

    public TransactionAndUsersReq() {
    }

    public TransactionAndUsersReq(String title, LocalDateTime dateTime, BigDecimal averageAmount, BigDecimal feeAmount, String note, int userBuy) {
        this.title = title;
        this.dateTime = dateTime;
        this.averageAmount = averageAmount;
        this.feeAmount = feeAmount;
        this.note = note;
        this.userBuy = userBuy;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public BigDecimal getAverageAmount() {
        return averageAmount;
    }

    public void setAverageAmount(BigDecimal averageAmount) {
        this.averageAmount = averageAmount;
    }

    public BigDecimal getFeeAmount() {
        return feeAmount;
    }

    public void setFeeAmount(BigDecimal feeAmount) {
        this.feeAmount = feeAmount;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public List<Integer> getLstUser() {
        return lstUser;
    }

    public void setLstUser(List<Integer> lstUser) {
        this.lstUser = lstUser;
    }

    public int getUserBuy() {
        return userBuy;
    }

    public void setUserBuy(int userBuy) {
        this.userBuy = userBuy;
    }

    @Override
    public String toString() {
        return "TransactionAndUsersReq{" +
                "title='" + title + '\'' +
                ", dateTime=" + dateTime +
                ", averageAmount=" + averageAmount +
                ", feeAmount=" + feeAmount +
                ", note='" + note + '\'' +
                ", lstUser=" + lstUser +
                ", userBuy=" + userBuy +
                '}';
    }
}
