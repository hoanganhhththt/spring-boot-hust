package com.example.project.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "fee")
public class Fee {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="fee_amount")
    private BigDecimal feeAmount;

    @Column(name="date_time")
    private LocalDateTime dateTime;

    public Fee() {
    }

    public Fee(BigDecimal feeAmount, LocalDateTime dateTime) {
        this.feeAmount = feeAmount;
        this.dateTime = dateTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BigDecimal getFeeAmount() {
        return feeAmount;
    }

    public void setFeeAmount(BigDecimal feeAmount) {
        this.feeAmount = feeAmount;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return "Fee{" +
                "id=" + id +
                ", feeAmount=" + feeAmount +
                ", dateTime=" + dateTime +
                '}';
    }
}
