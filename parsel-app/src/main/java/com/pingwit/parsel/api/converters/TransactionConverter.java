package com.pingwit.parsel.api.converters;

import com.pingwit.parsel.api.dto.TransactionDto;
import com.pingwit.parsel.entity.Transaction;
import com.pingwit.parsel.entity.enums.Payment;
import org.springframework.stereotype.Component;

@Component
public class TransactionConverter {

    public TransactionDto toDto(Transaction transaction){
        return TransactionDto.builder()
                .id(transaction.getId())
                .amount(transaction.getAmount())
                .payment(transaction.getPayment().getPayment())
                .build();
    }

    public Transaction toModel(TransactionDto dto){
        Transaction model = Transaction.builder()
                .id(dto.getId())
                .amount(dto.getAmount())
                .build();
        if(dto.getPayment() != null){model.setPayment((Payment.valueOf(dto.getPayment())));}
        return model;
    }
}
