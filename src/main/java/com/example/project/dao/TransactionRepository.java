package com.example.project.dao;

import com.example.project.entity.TransactionDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionDTO, Integer> {

    List<TransactionDTO> findTransactionByDateTimeBetween(Timestamp fromDate, Timestamp toDate);
    Page<TransactionDTO> findTransactionByUserBuyAndDateTimeBetween(Integer userBuy, Timestamp fromDate, Timestamp toDate, Pageable pageable);
    Page<TransactionDTO> findTransactionByDateTimeBetween(Timestamp fromDate, Timestamp toDate, Pageable pageable);

}
