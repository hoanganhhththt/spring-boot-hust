package com.example.project.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "email")
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "password")
    private String password;

    @Column(name = "account_number")
    private String accountNumber;

    @Column(name = "bank_name")
    private String bankName;

    @Column(name = "is_admin")
    private Integer isAdmin;

    @Column(name = "status")
    private Integer status;


    // @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_transaction",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "transaction_id"))
    private List<Transaction> transactions;

    public User() {
    }

    public User(String fullName, String email, String phoneNumber, String password, String accountNumber, String bankName, Integer isAdmin, Integer status) {
        this.fullName = fullName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.accountNumber = accountNumber;
        this.bankName = bankName;
        this.isAdmin = isAdmin;
        this.status = status;
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

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public Integer getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Integer isAdmin) {
        this.isAdmin = isAdmin;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void addTransaction(Transaction theTransaction) {
        if(transactions == null) {
            transactions = new ArrayList<>();
        }
        transactions.add(theTransaction);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", password='" + password + '\'' +
                ", accountNumber='" + accountNumber + '\'' +
                ", bankName='" + bankName + '\'' +
                ", isAdmin=" + isAdmin +
                ", status=" + status +
                '}';
    }
}
