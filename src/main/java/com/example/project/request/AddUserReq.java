package com.example.project.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class AddUserReq {
    @NotNull(message = "Vui lòng nhập Họ và tên")
    private String fullName;
    @NotNull(message = "Vui lòng nhập Email")
    @Email(message = "Email sai định dạng")
    private String email;
    @NotNull(message = "Vui lòng nhập Số điện thoại")
    @Size(min = 10, max = 10, message = "Số điện thoại sai định dạng")
    private String phoneNumber;
    @NotNull(message = "Vui lòng nhập Mật khẩu")
    private String password;
    @NotNull(message = "Vui lòng nhập Số tài khoản")
    private String accountNumber;
    @NotNull(message = "Vui lòng nhập Ngân hàng")
    private String bankName;
    private int isAdmin;
    private int status;

    public AddUserReq() {
    }

    public AddUserReq(String fullName, String email, String phoneNumber, String password, String accountNumber, String bankName, int isAdmin, int status) {
        this.fullName = fullName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.accountNumber = accountNumber;
        this.bankName = bankName;
        this.isAdmin = isAdmin;
        this.status = status;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public int getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(int isAdmin) {
        this.isAdmin = isAdmin;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
