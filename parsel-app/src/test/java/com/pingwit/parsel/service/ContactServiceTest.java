package com.pingwit.parsel.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import com.pingwit.parsel.entity.Contact;
import com.pingwit.parsel.repository.ContactRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

@ExtendWith(SpringExtension.class)
public class ContactServiceTest {

    @InjectMocks
    private ContactService contactService;
    @Mock
    private ContactRepository contactRepository;
    @Mock
    private BeanUtilService beanUtilService;

    @Test
    void update() {
        //given
        Contact contact = Contact.builder().id(1L).email("kate@gmail.com").build();
        Contact existing = Contact.builder().id(1L).number("45254254").email("mike@gmail.com").build();
        Contact expected = Contact.builder().id(1L).number("45254254").email("kate@gmail.com").build();

        when(contactRepository.findById(contact.getId())).thenReturn(Optional.of(existing));
        doNothing().when(beanUtilService).copyProperties(contact,existing);
        when(contactRepository.save(existing)).thenReturn(expected);

        //when
        Contact actual = contactService.update(contact);

        //then
        assertEquals(expected, actual);
    }
}
