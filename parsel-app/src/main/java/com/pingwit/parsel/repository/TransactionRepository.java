package com.pingwit.parsel.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import com.pingwit.parsel.entity.Transaction;

public interface TransactionRepository extends PagingAndSortingRepository<Transaction, Long> {
}
