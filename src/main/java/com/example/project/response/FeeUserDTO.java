package com.example.project.response;

import java.math.BigDecimal;

public class FeeUserDTO {
    private int id;
    private String fullName;
    private BigDecimal feeAmount;

    private BigDecimal feeUse;

    public FeeUserDTO() {
    }

    public FeeUserDTO(int id, String fullName, BigDecimal feeAmount, BigDecimal feeUse) {
        this.id = id;
        this.fullName = fullName;
        this.feeAmount = feeAmount;
        this.feeUse = feeUse;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public BigDecimal getFeeAmount() {
        return feeAmount;
    }

    public void setFeeAmount(BigDecimal feeAmount) {
        this.feeAmount = feeAmount;
    }

    public BigDecimal getFeeUse() {
      return feeUse;
    }

    public void setFeeUse(BigDecimal feeUse) {
        this.feeUse = feeUse;
    }
}
