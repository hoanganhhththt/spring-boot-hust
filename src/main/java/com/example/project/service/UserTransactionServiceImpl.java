package com.example.project.service;

import com.example.project.dao.TransactionDAO;
import com.example.project.dao.UserDAO;
import com.example.project.dao.UserTransactionDAO;
import com.example.project.entity.Transaction;
import com.example.project.entity.User;
import com.example.project.entity.UserTransaction;
import com.example.project.request.ListTransactionReq;
import com.example.project.request.TransactionAndUsersReq;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserTransactionServiceImpl implements UserTransactionService{

    UserTransactionDAO userTransactionDAO;

    TransactionDAO transactionDAO;

    UserDAO userDAO;

    @Autowired
    public UserTransactionServiceImpl(UserTransactionDAO theUserTransactionDAO, UserDAO theUserDAO, TransactionDAO theTransactionDAO) {
        userTransactionDAO = theUserTransactionDAO;
        userDAO = theUserDAO;
        transactionDAO = theTransactionDAO;
    }

    @Transactional
    @Override
    public void updateUserTransaction(UserTransaction theUserTransaction) {
        userTransactionDAO.save(theUserTransaction);
    }

    @Override
    public UserTransaction findTransactionById(int theId) {
        Optional<UserTransaction> result = userTransactionDAO.findById(theId);
        UserTransaction theUserTransaction = null;
        if(result.isPresent()) {
            theUserTransaction = result.get();
        } else{
            throw new RuntimeException("Did not find user id -" + theId);
        }
        return theUserTransaction;
    }

    @Override
    public List<UserTransaction> findTransactionByUserId(int theId) {
        List<UserTransaction> lstUserTransaction = userTransactionDAO.findByUserId(theId);
        return lstUserTransaction;
    }

    @Transactional
    @Override
    public void addTransactionAndUsers(TransactionAndUsersReq request) {
        BigDecimal averageAmount = request.getFeeAmount().divide(BigDecimal.valueOf(request.getLstUser().size()),0,BigDecimal.ROUND_HALF_UP);
        // tạo mới Transaction
        Transaction tempTransaction = new Transaction(request.getTitle(),
                Timestamp.valueOf(LocalDateTime.now()),
                averageAmount,
                request.getFeeAmount(),
                request.getUserBuy(),
                request.getNote()
        );

        List<Integer> lstUser = request.getLstUser();
        // Lọc từng phần tử user để thêm vào Transacton có quan hệ N-N
        for(Integer user: lstUser) {
            User tempUser = userDAO.findById(user).get();
            tempTransaction.addUser(tempUser);
        }
        transactionDAO.save(tempTransaction);
    }


//    @Override
//    public BigDecimal getFeeUserId(int theId) {
//        List<UserTransaction> tempUserTransaction = userTransactionDAO.findTransactionByUserId(theId);
//        for(UserTransaction userTransaction: tempUserTransaction) {
//            Transaction tempTransaction = (Transaction) userTransaction.getTransaction();
//
//        }
//    };
}
