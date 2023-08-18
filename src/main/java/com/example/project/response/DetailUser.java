package com.example.project.response;

import com.example.project.entity.RoleDTO;

import java.util.ArrayList;
import java.util.List;

public class DetailUser {
    private int id;

    private String userName;
    private String fullName;
    private String email;
    private String phoneNumber;
    private String bankName;
    private String accountNumber;

    private List<Integer> roles;
    private int status;

    public DetailUser() {
    }

    public DetailUser(int id, String userName, String fullName, String email, String phoneNumber, String bankName, String accountNumber, int status, List<Integer> roles) {
        this.id = id;
        this.userName = userName;
        this.fullName = fullName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.bankName = bankName;
        this.accountNumber = accountNumber;
        this.status = status;
        this.roles = roles;
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

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<Integer> getRoles() {
        return roles;
    }

    public void setRoles(List<Integer> roles) {
        this.roles = roles;
    }

    public void addRole(RoleDTO theRoleDTO) {
        if(roles == null) {
            roles = new ArrayList<>();
        }
        roles.add(theRoleDTO.getId());
    }
}
