package com.pingwit.parsel.api.controlles;

import com.pingwit.parsel.api.converters.OrderConverter;
import com.pingwit.parsel.api.dto.OrderRequestDto;
import com.pingwit.parsel.api.dto.OrderResponseDto;
import com.pingwit.parsel.entity.Order;
import com.pingwit.parsel.entity.criteria.OrderCriteria;
import com.pingwit.parsel.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;
    private final OrderConverter orderConverter;

    public OrderController(OrderService orderService, OrderConverter orderConverter) {
        this.orderService = orderService;
        this.orderConverter = orderConverter;
    }

    @PostMapping
    public ResponseEntity<OrderResponseDto> save(@RequestBody OrderRequestDto requestDto) {
        ResponseEntity<OrderResponseDto> response;
        Order saved = orderService.save(orderConverter.toModel(requestDto));
        if (saved == null) {
            response = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            response = new ResponseEntity<>(orderConverter.toDto(saved), HttpStatus.OK);
        }
        return response;
    }

    @PutMapping
    public ResponseEntity<OrderResponseDto> update(@RequestBody OrderRequestDto requestDto) {
        Order updated = orderService.update(orderConverter.toModel(requestDto));
        return new ResponseEntity<>(orderConverter.toDto(updated), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponseDto> getById(@PathVariable Long id) {
        return orderService.findById(id)
                .map(result -> new ResponseEntity<>(orderConverter.toDto(result), HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.OK));
    }

    @GetMapping("/search/sender/{userId}/criteria")
    public ResponseEntity<List<OrderResponseDto>> getAllByCriteria(
            @PathVariable Long userId, @RequestBody OrderCriteria orderCriteria) {
        return new ResponseEntity<>(
                orderService.findAllByCriteria(userId, orderCriteria).stream()
                        .map(result->orderConverter.toDto(result)).collect(Collectors.toList()),
                HttpStatus.OK);
    }
}
