package com.pingwit.parsel.api.controlles;

import com.pingwit.parsel.api.converters.ContactConverter;
import com.pingwit.parsel.api.dto.ContactDto;
import com.pingwit.parsel.entity.Contact;
import com.pingwit.parsel.service.ContactService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/contact")
public class ContactController {

    private final ContactService contactService;
    private final ContactConverter contactConverter;

    public ContactController(ContactService contactService, ContactConverter contactConverter) {
        this.contactService = contactService;
        this.contactConverter = contactConverter;
    }

    @PostMapping
    public ResponseEntity<ContactDto> save(@RequestBody ContactDto dto) {
        ResponseEntity<ContactDto> response;
        Contact saved = contactService.save(contactConverter.toModel(dto));
        if (saved == null) {
            response = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            response = new ResponseEntity<>(contactConverter.toDto(saved), HttpStatus.OK);
        }
        return response;
    }

    @PutMapping
    public ResponseEntity<ContactDto> update(@RequestBody ContactDto dto) {
        Contact updated = contactService.update(contactConverter.toModel(dto));
        return new ResponseEntity<>(contactConverter.toDto(updated), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContactDto> getById(@PathVariable Long id){
        return contactService.findById(id)
                .map(result -> new ResponseEntity<>(contactConverter.toDto(result), HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.OK));
    }
}
