package com.example.project.service;

import com.example.project.entity.TransactionDTO;
import com.example.project.request.GetFeeReq;
import com.example.project.request.ListTransactionReq;
import com.example.project.request.TransactionAndUsersReq;
import com.example.project.request.UpdateTransactionReq;
import com.example.project.response.*;

import java.util.List;

public interface TransactionService {
    List<TransactionDTO> findAllTransaction();

    TransactionDTO save(TransactionDTO theTransactionDTO);

    TransactionDTO findTransactionById(int theId);

    ApiResponse<?> findByUserBuyAndDateTimeBetween(ListTransactionReq request);

    List<GetFeeRes> getTotalFeeByUser(GetFeeReq request);

    List<GetFeeRes> getToltalFeeByUserBuy(GetFeeReq request);

    ApiResponse<?> getToltalFee(GetFeeReq request);

    ApiResponse<String> updateTransaction(UpdateTransactionReq request);

    ApiResponse<?> detailTransaction(DetailUser request);

    void addTransactionAndUsers(TransactionAndUsersReq request);

}
