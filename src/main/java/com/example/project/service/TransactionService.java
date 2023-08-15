package com.example.project.service;

import com.example.project.dao.TransactionDAO;
import com.example.project.entity.Transaction;
import com.example.project.entity.User;
import com.example.project.request.GetFeeReq;
import com.example.project.request.ListTransactionReq;
import com.example.project.request.UpdateTransactionReq;
import com.example.project.response.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface TransactionService {
    List<Transaction> findAllTransaction();

    Transaction save(Transaction theTransaction);

    Transaction findTransactionById(int theId);

    ApiResponse<?> findByUserBuyAndDateTimeBetween(ListTransactionReq request);

    List<GetFeeRes> getTotalFeeByUser(GetFeeReq request);

    List<GetFeeRes> getToltalFeeByUserBuy(GetFeeReq request);

    ApiResponse<?> getToltalFee(GetFeeReq request);

    ApiResponse<String> updateTransaction(UpdateTransactionReq request);

    ApiResponse<?> detailTransaction(DetailUser request);

}
