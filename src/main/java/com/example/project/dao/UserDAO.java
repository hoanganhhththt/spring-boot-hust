package com.example.project.dao;

import com.example.project.entity.Transaction;
import com.example.project.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

public interface UserDAO extends JpaRepository<User, Integer> {
    boolean existsByPhoneNumber(String phoneNumber);
    boolean existsByEmail(String email);

    Page<User> findByFullNameContaining(String fullName, Pageable pageable);
    List<User> findByFullNameContaining(String fullName);
}
