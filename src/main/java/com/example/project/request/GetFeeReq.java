package com.example.project.request;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

public class GetFeeReq {
    @NotNull(message = "Từ ngày không được bỏ trống")
    @JsonFormat(
            shape = JsonFormat.Shape.STRING,
            pattern = "dd/MM/yyyy HH:mm:ss",
            timezone = "Asia/Ho_Chi_Minh"
    )
    private Timestamp fromDate;

    @NotNull(message = "Đến ngày không được bỏ trống")
    @JsonFormat(
            shape = JsonFormat.Shape.STRING,
            pattern = "dd/MM/yyyy HH:mm:ss",
            timezone = "Asia/Ho_Chi_Minh"
    )
    private Timestamp toDate;

    public GetFeeReq() {
    }

    public GetFeeReq(Timestamp fromDate, Timestamp toDate) {
        this.fromDate = fromDate;
        this.toDate = toDate;
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
}
