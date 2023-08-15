package com.example.project.dao;

import com.example.project.entity.Transaction;
import com.example.project.entity.User;
import com.example.project.entity.UserTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

public interface UserTransactionDAO extends JpaRepository<UserTransaction, Integer> {
    List<UserTransaction> findByUserId(int theId);
}
