package com.pingwit.parsel.api.converters;

import com.pingwit.parsel.api.dto.ContactDto;
import com.pingwit.parsel.entity.Contact;
import org.springframework.stereotype.Component;

@Component
public class ContactConverter {

    public ContactDto toDto(Contact contact){
        return ContactDto.builder()
                .id(contact.getId())
                .number(contact.getNumber())
                .email(contact.getEmail())
                .build();
    }

    public Contact toModel(ContactDto dto){
        return Contact.builder()
                .id(dto.getId())
                .number(dto.getNumber())
                .email(dto.getEmail())
                .build();
    }
}
