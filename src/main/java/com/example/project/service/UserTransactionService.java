package com.example.project.service;

import com.example.project.entity.Transaction;
import com.example.project.entity.UserTransaction;
import com.example.project.request.ListTransactionReq;
import com.example.project.request.TransactionAndUsersReq;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Objects;

public interface UserTransactionService {

    void updateUserTransaction(UserTransaction theUserTransaction);

    UserTransaction findTransactionById(int theId);
    List<UserTransaction> findTransactionByUserId(int theId);

    void addTransactionAndUsers(TransactionAndUsersReq request);


//    BigDecimal getFeeUserId(int theId);
}
