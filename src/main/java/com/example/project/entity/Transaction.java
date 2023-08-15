package com.example.project.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.persistence.*;
import org.springframework.beans.propertyeditors.CustomDateEditor;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name="date_time")
    private Timestamp dateTime;

    @Column(name = "average_amount")
    private BigDecimal averageAmount;

    @Column(name = "fee_amount")
    private BigDecimal feeAmount;

    @Column(name = "note")
    private String note;

    @Transient
    private List<Integer> lstUser;

    @Column(name = "user_buy")
    private Integer userBuy;

    // @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_transaction",
            joinColumns = @JoinColumn(name = "transaction_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> users;

    public Transaction() {
    }

    public Transaction(String title, Timestamp dateTime, BigDecimal averageAmount, BigDecimal feeAmount,Integer userBuy, String note) {
        this.title = title;
        this.dateTime = dateTime;
        this.averageAmount = averageAmount;
        this.feeAmount = feeAmount;
        this.userBuy = userBuy;
        this.note = note;
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

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
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

    public void addUser(User theUser) {
        if(users == null) {
            users = new ArrayList<>();
        }
        users.add(theUser);
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", dateTime=" + dateTime +
                ", averageAmount=" + averageAmount +
                ", feeAmount=" + feeAmount +
                ", note='" + note + '\'' +
                ", lstUser=" + lstUser +
                ", userBuy=" + userBuy +
                ", users=" + users +
                '}';
    }
}
