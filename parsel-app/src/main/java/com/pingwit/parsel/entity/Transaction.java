package com.pingwit.parsel.entity;

import com.pingwit.parsel.entity.enums.Payment;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "Amount can't be empty")
    private Integer amount;
    @Enumerated(EnumType.STRING)
    @NotNull(message = "Payment can't be empty")
    private Payment payment;
}
