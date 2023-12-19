package com.pingwit.parsel.repository;

import com.pingwit.parsel.entity.Order;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface OrderRepository extends PagingAndSortingRepository<Order, Long>, OrderCustomRepository {
}
