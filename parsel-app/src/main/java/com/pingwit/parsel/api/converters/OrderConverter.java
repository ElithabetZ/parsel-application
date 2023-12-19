package com.pingwit.parsel.api.converters;

import com.pingwit.parsel.api.dto.OrderRequestDto;
import com.pingwit.parsel.api.dto.OrderResponseDto;
import com.pingwit.parsel.entity.Order;
import com.pingwit.parsel.service.RouteService;
import com.pingwit.parsel.service.TransactionService;
import com.pingwit.parsel.service.UserService;
import org.springframework.stereotype.Component;

import static java.lang.String.format;

@Component
public class OrderConverter {

    private final UserConverter userConverter;
    private final RouteConverter routeConverter;
    private final TransactionConverter transactionConverter;
    private final UserService userService;
    private final RouteService routeService;
    private final TransactionService transactionService;

    public OrderConverter(UserConverter userConverter, RouteConverter routeConverter,
                          TransactionConverter transactionConverter, UserService userService,
                          RouteService routeService, TransactionService transactionService) {
        this.userConverter = userConverter;
        this.routeConverter = routeConverter;
        this.transactionConverter = transactionConverter;
        this.userService = userService;
        this.routeService = routeService;
        this.transactionService = transactionService;
    }

    public OrderResponseDto toDto(Order order){
        return OrderResponseDto.builder()
                .id(order.getId())
                .sender(userConverter.toDto(order.getSender()))
                .receiver(userConverter.toDto(order.getReceiver()))
                .route(routeConverter.toDto(order.getRoute()))
                .transaction(transactionConverter.toDto(order.getTransaction()))
                .type(order.getType())
                .build();
    }

    public Order toModel(OrderRequestDto requestDto){
        Long sender = requestDto.getSenderID();
        Long receiver = requestDto.getReceiverID();
        Long route = requestDto.getRouteID();
        Long transaction = requestDto.getTransactionID();

        Order model = Order.builder()
                .id(requestDto.getId())
                .type(requestDto.getType())
                .build();

        if(sender != null){
            userService.findById(sender).orElseThrow(() -> new IllegalArgumentException(
                            format("Sender with such id=%d wasn't found", requestDto.getSenderID())));
            model.setSender(userService.findById(sender).get());
        }
        if(receiver != null){
            userService.findById(receiver).orElseThrow(() -> new IllegalArgumentException(
                    format("Receiver with such id=%d wasn't found", requestDto.getReceiverID())));
            model.setReceiver(userService.findById(receiver).get());
        }
        if(route != null){
            userService.findById(route).orElseThrow(() -> new IllegalArgumentException(
                    format("Route with such id=%d wasn't found", requestDto.getRouteID())));
            model.setRoute(routeService.findById(route).get());
        }
        if(transaction != null){
            userService.findById(transaction).orElseThrow(() -> new IllegalArgumentException(
                    format("Transaction with such id=%d wasn't found", requestDto.getTransactionID())));
            model.setTransaction(transactionService.findById(transaction).get());
        }

        return model;
    }
}
