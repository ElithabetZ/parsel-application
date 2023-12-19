package com.pingwit.parsel.api.dto;

import com.pingwit.parsel.entity.enums.Type;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponseDto {
    private Long id;
    private UserResponseDto sender;
    private UserResponseDto receiver;
    private RouteDto route;
    private TransactionDto transaction;
    private Type type;
}
