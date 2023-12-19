package com.pingwit.parsel.api.converters;

import com.pingwit.parsel.api.dto.UserRequestDto;
import com.pingwit.parsel.api.dto.UserResponseDto;
import com.pingwit.parsel.entity.User;
import com.pingwit.parsel.service.AddressService;
import com.pingwit.parsel.service.ContactService;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {

    private final AddressConverter addressConverter;
    private final ContactConverter contactConverter;
    private final AddressService addressService;
    private final ContactService contactService;

    public UserConverter(AddressConverter addressConverter, ContactConverter contactConverter,
                         AddressService addressService, ContactService contactService) {
        this.addressConverter = addressConverter;
        this.contactConverter = contactConverter;
        this.addressService = addressService;
        this.contactService = contactService;
    }

    public UserResponseDto toDto(User user){
        return UserResponseDto.builder()
                .id(user.getId())
                .name(user.getName())
                .surname(user.getSurname())
                .address(addressConverter.toDto(user.getAddress()))
                .contact(contactConverter.toDto(user.getContact()))
                .build();
    }

    public User toModel(UserRequestDto requestDto){
        User model = User.builder()
                .id(requestDto.getId())
                .name(requestDto.getName())
                .surname(requestDto.getSurname())
                .build();

        if(requestDto.getAddressID() != null){
            model.setAddress(addressService.findById(requestDto.getAddressID()).get());
        }
        if(requestDto.getContactID() != null){
            model.setContact(contactService.findById(requestDto.getContactID()).get());
        }
        return model;
    }
}
