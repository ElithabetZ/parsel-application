package com.pingwit.parsel.service;

import com.pingwit.parsel.entity.Order;
import com.pingwit.parsel.entity.criteria.OrderCriteria;
import com.pingwit.parsel.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

import static java.lang.String.format;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final BeanUtilService beanUtilService;

    public OrderService(OrderRepository orderRepository, BeanUtilService beanUtilService) {

        this.orderRepository = orderRepository;
        this.beanUtilService = beanUtilService;
    }

    @Transactional
    public Order save(@Valid Order order) {
        return orderRepository.save(order);
    }

    @Transactional(readOnly = true)
    public Optional<Order> findById(Long userId) {
        return orderRepository.findById(userId);
    }

    @Transactional
    public Order update(Order order) {
        Order existing = findById(order.getId())
                .orElseThrow(() -> new IllegalArgumentException(format("Order with such userId=%d wasn't found", order.getId())));
        beanUtilService.copyProperties(order, existing);

        return orderRepository.save(existing);
    }

    @Transactional(readOnly = true)
    public List<Order> findAllByCriteria(Long userId, OrderCriteria orderCriteria) {
        return orderRepository.findAllByCriteria(userId, orderCriteria);
    }

}


