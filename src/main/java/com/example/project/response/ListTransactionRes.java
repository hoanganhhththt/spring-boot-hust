package com.example.project.response;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class ListTransactionRes {
    private int id;
    private String title;
    private Timestamp dateTime;
    private BigDecimal averageAmount;
    private BigDecimal feeAmount;
    private String note;

    private String userBuy;
    private List<String> users;

    public ListTransactionRes(int id, String title, Timestamp dateTime, BigDecimal averageAmount, BigDecimal feeAmount, String note, String userBuy) {
        this.id = id;
        this.title = title;
        this.dateTime = dateTime;
        this.averageAmount = averageAmount;
        this.feeAmount = feeAmount;
        this.note = note;
        this.userBuy = userBuy;
    }

    public ListTransactionRes() {
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

    public Timestamp getDateTime() {
        return dateTime;
    }

    public void setDateTime(Timestamp dateTime) {
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

    public List<String> getUsers() {
        return users;
    }

    public void setUsers(List<String> users) {
        this.users = users;
    }

    public String getUserBuy() {
        return userBuy;
    }

    public void setUserBuy(String userBuy) {
        this.userBuy = userBuy;
    }

    public void add(String theUser) {
        if(users == null) {
            users = new ArrayList<>();
        }
        users.add(theUser);
    }
}
