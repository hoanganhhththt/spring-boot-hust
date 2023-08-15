package com.example.project.controller;

import com.example.project.entity.User;
import com.example.project.request.AddUserReq;
import com.example.project.request.DetailReq;
import com.example.project.request.ListUserReq;
import com.example.project.request.UpdateUserReq;
import com.example.project.response.ApiResponse;
import com.example.project.response.DetailUser;
import com.example.project.response.ListUserRes;
import com.example.project.service.TransactionService;
import com.example.project.service.UserSerive;
import com.example.project.service.UserTransactionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserRestController {

    private UserSerive userSerive;

    private TransactionService transactionService;

    private UserTransactionService userTransactionService;

    @Autowired
    public UserRestController(UserSerive theUserService, TransactionService theTransactionService, UserTransactionService theUserTransacionService) {
        userSerive = theUserService;
        transactionService = theTransactionService;
        userTransactionService = theUserTransacionService;
    }

    @PostMapping("/add")
    public ApiResponse<String> addUser(@RequestBody @Valid AddUserReq theUser, BindingResult bindingResult) {

        if(bindingResult.hasErrors()) {
            String errorMessage = bindingResult.getFieldError().getDefaultMessage();
            ApiResponse<String> response = new ApiResponse<String>("11", "", errorMessage);
            return response;
        }
        ApiResponse<String> response = userSerive.addNewUser(theUser);
        return response;
    }

    @PostMapping("/detail")
    public ApiResponse<DetailUser> getUserById(@RequestBody @Valid DetailReq req) {

        ApiResponse<DetailUser> user = userSerive.detailUserById(req);
        return user;
    }

    @PostMapping("/update")
    public ApiResponse<String> updateUser(@RequestBody @Valid UpdateUserReq theUser, BindingResult bindingResult) {

        if(bindingResult.hasErrors()) {
            String errorMessage = bindingResult.getFieldError().getDefaultMessage();
            ApiResponse<String> response = new ApiResponse<String>("11", "", errorMessage);
            return response;
        }
        ApiResponse<String> response = userSerive.updateUser(theUser);
        return response;
    }

    @PostMapping("/change-status")
    public ApiResponse<String> changeStatusUser(@RequestBody @Valid DetailReq theUser, BindingResult bindingResult) {

        if(bindingResult.hasErrors()) {
            String errorMessage = bindingResult.getFieldError().getDefaultMessage();
            ApiResponse<String> response = new ApiResponse<String>("11", "", errorMessage);
            return response;
        }
        ApiResponse<String> response = userSerive.changeStatusUser(theUser);
        return response;
    }

    @PostMapping("/list")
    public ApiResponse<?> listUser(@RequestBody @Valid ListUserReq request, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            String errorMessage = bindingResult.getFieldError().getDefaultMessage();
            ApiResponse<String> response = new ApiResponse<String>("11","", errorMessage);
            return response;
        }
        ApiResponse<?> response = userSerive.findByFullNameContaining(request);
        return response;
    }

}
