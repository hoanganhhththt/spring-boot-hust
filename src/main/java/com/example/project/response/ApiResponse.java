package com.example.project.response;

public class ApiResponse<T> {
    private String code;
    private T data;
    private String message;

    public ApiResponse() {
    }

    public ApiResponse(String code, T data, String message) {
        this.code = code;
        this.data = data;
        this.message = message;
    }

    public ApiResponse(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
