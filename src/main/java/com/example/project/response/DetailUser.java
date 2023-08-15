package com.example.project.response;

public class DetailUser {
    private int id;
    private String fullName;
    private String email;
    private String phoneNumber;
    private String bankName;
    private String accountNumber;
    private int status;

    public DetailUser() {
    }

    public DetailUser(int id, String fullName, String email, String phoneNumber, String bankName, String accountNumber, int status) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.bankName = bankName;
        this.accountNumber = accountNumber;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getBankName() {
        return bankName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public int getStatus() {
        return status;
    }
}
