package com.pingwit.parsel.service;

import com.pingwit.parsel.entity.Contact;
import com.pingwit.parsel.repository.ContactRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.util.Optional;

import static java.lang.String.format;

@Service
public class ContactService {
    private final ContactRepository contactRepository;
    private final BeanUtilService beanUtilService;

    public ContactService(ContactRepository contactRepository, BeanUtilService beanUtilService){

        this.contactRepository = contactRepository;
        this.beanUtilService = beanUtilService;
    }

    @Transactional
    public Contact save(@Valid Contact contact) {
        return contactRepository.save(contact);
    }

    @Transactional(readOnly = true)
    public Optional<Contact> findById(Long id) {
        return contactRepository.findById(id);
    }

    @Transactional
    public Contact update(Contact contact) {
        Contact existing = findById(contact.getId()).orElseThrow(() ->
                new IllegalArgumentException(format("Contact with such id=%d wasn't found", contact.getId())));
        beanUtilService.copyProperties(contact, existing);

        return contactRepository.save(existing);
    }
}
