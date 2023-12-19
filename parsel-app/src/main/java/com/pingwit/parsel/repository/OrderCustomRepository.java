package com.pingwit.parsel.repository;

import com.pingwit.parsel.entity.Order;
import com.pingwit.parsel.entity.criteria.OrderCriteria;

import java.util.List;

public interface OrderCustomRepository {
    List<Order> findAllByCriteria(Long userId, OrderCriteria criteria);
}
