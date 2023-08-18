package com.example.project.controller;

import com.example.project.request.GetFeeReq;
import com.example.project.request.ListTransactionReq;
import com.example.project.request.TransactionAndUsersReq;
import com.example.project.request.UpdateTransactionReq;
import com.example.project.response.*;
import com.example.project.service.TransactionService;
import com.example.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/transaction")
public class TransactionRestController {

    private TransactionService transactionService;
    private UserService userSerive;


    @Autowired
    public TransactionRestController(TransactionService theTransactionService, UserService theUserService) {
        transactionService = theTransactionService;
        userSerive = theUserService;
    }


    @PostMapping("/list")
    public ApiResponse<?> findListTransaction(@RequestBody @Valid ListTransactionReq req, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            String errorMessage = bindingResult.getFieldError().getDefaultMessage();
            ApiResponse<String> response = new ApiResponse<String>("11", "", errorMessage);
            return response;
        } else {
            ApiResponse<?> response = transactionService.findByUserBuyAndDateTimeBetween(req);
            return response;
        }
    }

    @PostMapping("/getTotalFee")
    public ApiResponse<?> getTotalFeeByUser(@RequestBody @Valid GetFeeReq request, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            String errorMessage = bindingResult.getFieldError().getDefaultMessage();
            ApiResponse<String> response = new ApiResponse<String>("11", "", errorMessage);
            return response;
        } else {
            ApiResponse<?> response = transactionService.getToltalFee(request);
            return response;
        }
    }

    @PostMapping("/update")
    public ApiResponse<String> updateTransaction(@RequestBody @Valid UpdateTransactionReq request, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            String errorMessage = bindingResult.getFieldError().getDefaultMessage();
            ApiResponse<String> response = new ApiResponse<String>("11", "", errorMessage);
            return response;
        } else {
            ApiResponse<String> response = transactionService.updateTransaction(request);
            return response;
        }
    }

    @PostMapping("/detail")
    public ApiResponse<?> detailTransaction(@RequestBody @Valid DetailUser request, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            String errorMessage = bindingResult.getFieldError().getDefaultMessage();
            ApiResponse<String> response = new ApiResponse<String>("11", "", errorMessage);
            return response;
        } else {
            ApiResponse<?> response = transactionService.detailTransaction(request);
            return response;
        }
    }

    @PostMapping("/add")
    public ApiResponse<?> addTransactionAndUsers(@RequestBody @Valid TransactionAndUsersReq request, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            String errorMessage = bindingResult.getFieldError().getDefaultMessage();
            ApiResponse<String> response = new ApiResponse<String>("11", "", errorMessage);
            return response;
        } else {
            transactionService.addTransactionAndUsers(request);
            ApiResponse<String> res = new ApiResponse<>("00", "Thêm mới giao dịch thành công");
            return res;
        }
    }

}
