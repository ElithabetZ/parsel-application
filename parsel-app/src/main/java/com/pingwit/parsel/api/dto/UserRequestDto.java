package com.pingwit.parsel.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDto {

    private Long id;
    private String name;
    private String surname;
    private Long addressID;
    private Long contactID;
}
