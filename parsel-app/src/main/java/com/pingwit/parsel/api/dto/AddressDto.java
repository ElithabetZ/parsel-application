package com.pingwit.parsel.api.dto;

import com.pingwit.parsel.entity.enums.Shortcode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddressDto {
    private Long id;
    private String postalCode;
    private String city;
    private String street;
    private String house;
    private Integer flat;
    private Shortcode shortCode;
}
