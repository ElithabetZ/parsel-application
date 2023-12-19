package com.pingwit.parsel.repository;

import com.pingwit.parsel.entity.User;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRepository extends PagingAndSortingRepository<User, Long> {
}
