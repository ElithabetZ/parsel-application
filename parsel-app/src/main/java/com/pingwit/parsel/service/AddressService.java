package com.pingwit.parsel.service;

import com.pingwit.parsel.entity.Address;
import com.pingwit.parsel.entity.enums.Shortcode;
import com.pingwit.parsel.repository.AddressRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.lang.String.format;

@Service
public class AddressService {
    private final AddressRepository addressRepository;
    private final BeanUtilService beanUtilService;

    public AddressService(AddressRepository addressRepository, BeanUtilService beanUtilService) {

        this.addressRepository = addressRepository;
        this.beanUtilService = beanUtilService;
    }

    @Transactional
    public Address save(@Valid Address address) {
        return addressRepository.save(address);
    }

    @Transactional(readOnly = true)
    public Optional<Address> findById(Long id) {
        return addressRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<Address> findByShortCodes(List<Shortcode> codes) {
        return addressRepository.findByShortCodes(codes.stream().map(s->s.toString()).collect(Collectors.toList()));
    }

    @Transactional
    public Address update(Address address) {
        Address existing = findById(address.getId())
                .orElseThrow(() -> new IllegalArgumentException(format("Address with such id=%d wasn't found", address.getId())));
        beanUtilService.copyProperties(address, existing);

        return addressRepository.save(existing);
    }
}
