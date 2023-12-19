package com.pingwit.parsel.api.converters;

import com.pingwit.parsel.api.dto.AddressDto;
import com.pingwit.parsel.entity.Address;
import org.springframework.stereotype.Component;

@Component
public class AddressConverter {

    public AddressDto toDto(Address address){
        return AddressDto.builder()
                .id(address.getId())
                .postalCode(address.getPostalCode())
                .city(address.getCity())
                .street(address.getStreet())
                .house(address.getHouse())
                .flat(address.getFlat())
                .shortCode(address.getShortCode())
                .build();
    }

    public Address toModel(AddressDto dto){
        return Address.builder()
                .id(dto.getId())
                .postalCode(dto.getPostalCode())
                .city(dto.getCity())
                .street(dto.getStreet())
                .house(dto.getHouse())
                .flat(dto.getFlat())
                .shortCode(dto.getShortCode())
                .build();
    }
}
