package com.pingwit.parsel.converters;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;

import com.pingwit.parsel.api.converters.TransactionConverter;
import com.pingwit.parsel.api.dto.TransactionDto;
import com.pingwit.parsel.entity.Transaction;
import com.pingwit.parsel.entity.enums.Payment;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class TransactionConverterTest {

    private TransactionConverter transactionConverter = new TransactionConverter();

    @ParameterizedTest
    @MethodSource("toDto_data")
    void toDto(Transaction transaction, TransactionDto expected){
        //when
        TransactionDto actual = transactionConverter.toDto(transaction);

        //then
        assertEquals(expected, actual);
    }

    private static Stream<Arguments> toDto_data(){
        return Stream.of(
                Arguments.of(
                        new Transaction(1L,15, Payment.IN_PROCESS),
                        new TransactionDto(1L,15,"IN PROCESS")),
                Arguments.of(
                        new Transaction(2L,10, Payment.PAID),
                        new TransactionDto(2L,10,"PAID"))
                );
    }

    @ParameterizedTest
    @MethodSource("toModel_data")
    void toModel(TransactionDto transaction, Transaction expected){
        //when
        Transaction actual = transactionConverter.toModel(transaction);

        //then
        assertEquals(expected, actual);
    }

    private static Stream<Arguments> toModel_data(){
        return Stream.of(
                Arguments.of(
                        new TransactionDto(1L,15,"IN_PROCESS"),
                        new Transaction(1L,15, Payment.IN_PROCESS)),
                Arguments.of(
                        TransactionDto.builder().id(1L).amount(15).build(),
                        Transaction.builder().id(1L).amount(15).build())
        );
    }
}
