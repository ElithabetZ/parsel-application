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
public class OrderRequestDto {
    private Long id;
    private Long senderID;
    private Long receiverID;
    private Long routeID;
    private Long transactionID;
    private Type type;
}
