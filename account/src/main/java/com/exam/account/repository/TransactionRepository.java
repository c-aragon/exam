package com.exam.account.repository;

import com.exam.account.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findAllByAccount_IdAndDateBetween(Long id, LocalDateTime startDate, LocalDateTime endDate);

}
