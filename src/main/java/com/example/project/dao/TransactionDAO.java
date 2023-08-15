package com.example.project.dao;

import com.example.project.entity.Fee;
import com.example.project.entity.Transaction;
import com.example.project.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public interface TransactionDAO extends JpaRepository<Transaction, Integer> {

    List<Transaction> findTransactionByDateTimeBetween(Timestamp fromDate, Timestamp toDate);
    Page<Transaction> findTransactionByUserBuyAndDateTimeBetween(Integer userBuy,Timestamp fromDate, Timestamp toDate, Pageable pageable);
    Page<Transaction> findTransactionByDateTimeBetween(Timestamp fromDate, Timestamp toDate, Pageable pageable);

}
