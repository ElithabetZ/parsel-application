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
public class RouteDto {
    private Long id;
    private Shortcode sender;
    private Shortcode receiver;
    private String status;
}
