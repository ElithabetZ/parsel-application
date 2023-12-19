package com.pingwit.parsel.entity;

import com.pingwit.parsel.entity.enums.Shortcode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "Postal code can't be empty")
    private String postalCode;
    @NotNull(message = "City can't be empty")
    private String city;
    @NotNull(message = "Street can't be empty")
    private String street;
    @Column(name = "house_number")
    private String house;
    @Column(name = "flat_number")
    private Integer flat;
    @Enumerated(EnumType.STRING)
    @Column(name = "shortcode")
    private Shortcode shortCode;
}
