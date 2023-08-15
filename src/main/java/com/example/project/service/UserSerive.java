package com.example.project.service;

import com.example.project.entity.Transaction;
import com.example.project.entity.User;
import com.example.project.request.AddUserReq;
import com.example.project.request.DetailReq;
import com.example.project.request.ListUserReq;
import com.example.project.request.UpdateUserReq;
import com.example.project.response.ApiResponse;
import com.example.project.response.DetailUser;
import com.example.project.response.ListUserRes;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserSerive {

    List<User> findAllUser();

    void save(User theUser);

    boolean isPhoneNumberTaken(String phoneNumber);

    boolean isEmailTaken(String email);

    User findUserById(int theId);

    ApiResponse<DetailUser> detailUserById(DetailReq req);

    ApiResponse<String> addNewUser(AddUserReq request);

    ApiResponse<String> updateUser(UpdateUserReq request);

    ApiResponse<String> changeStatusUser(DetailReq request);

    ApiResponse<?> findByFullNameContaining(ListUserReq request);
}
