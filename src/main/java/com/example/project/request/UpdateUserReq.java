package com.example.project.request;



import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

public class UpdateUserReq {
    @NotNull(message = "Lỗi hệ thống")
    private int id;
    @NotNull(message = "Vui lòng nhập Họ và tên")
    private String fullName;
    @NotNull(message = "Vui lòng nhập Email")
    @Email(message = "Email sai định dạng")
    private String email;
    @NotNull(message = "Vui lòng nhập Số điện thoại")
    @Size(min = 10, max = 10, message = "Số điện thoại sai định dạng")
    private String phoneNumber;
    @NotNull(message = "Vui lòng nhập Số tài khoản")
    private String accountNumber;
    @NotNull(message = "Vui lòng nhập Ngân hàng")
    private String bankName;

    @NotNull(message = "Vui lòng chọn Nhóm quyền")
    private List<Integer> roles;

    public UpdateUserReq() {
    }

    public UpdateUserReq(int id, String fullName, String email, String phoneNumber, String accountNumber, String bankName) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.accountNumber = accountNumber;
        this.bankName = bankName;
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

    public List<Integer> getRoles() {
        return roles;
    }

    public void setRoles(List<Integer> roles) {
        this.roles = roles;
    }
}
