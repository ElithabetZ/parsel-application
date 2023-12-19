package com.pingwit.parsel.repository;

import com.pingwit.parsel.entity.Address;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AddressRepository extends PagingAndSortingRepository<Address, Long> {

    @Query(value = "SELECT * FROM address\n" +
                   "WHERE shortcode IN(:codes)",
           nativeQuery = true)
    List<Address> findByShortCodes(@Param("codes") List<String> shortCodes);
}
