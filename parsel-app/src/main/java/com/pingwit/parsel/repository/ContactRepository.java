package com.pingwit.parsel.repository;

import com.pingwit.parsel.entity.Contact;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ContactRepository extends PagingAndSortingRepository<Contact, Long> {
}
