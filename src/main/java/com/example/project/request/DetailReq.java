package com.example.project.request;

import jakarta.validation.constraints.NotNull;

public class DetailReq {
    @NotNull(message = "Id không được bỏ trống")
    private int id;

    public DetailReq() {
    }

    public DetailReq(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
