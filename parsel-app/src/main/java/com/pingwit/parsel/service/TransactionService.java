package com.pingwit.parsel.service;

import com.pingwit.parsel.entity.Transaction;
import com.pingwit.parsel.repository.TransactionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.Optional;

import static java.lang.String.format;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final BeanUtilService beanUtilService;

    public TransactionService(TransactionRepository transactionRepository, BeanUtilService beanUtilService){

        this.transactionRepository = transactionRepository;
        this.beanUtilService = beanUtilService;
    }

    @Transactional
    public Transaction save(@Valid Transaction transaction){return transactionRepository.save(transaction);}

    @Transactional(readOnly = true)
    public Optional<Transaction> findById(Long id) {
        return transactionRepository.findById(id);
    }

    @Transactional
    public Transaction update(Transaction transaction) {
        Transaction existing = findById(transaction.getId())
                .orElseThrow(() -> new IllegalArgumentException(format("Transaction with such id=%d wasn't found", transaction.getId())));
        beanUtilService.copyProperties(transaction, existing);

        return transactionRepository.save(existing);
    }

}
