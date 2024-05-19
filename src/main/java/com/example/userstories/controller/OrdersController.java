package com.example.userstories.controller;

import com.example.userstories.dto.request.OrdersRequest;
import com.example.userstories.dto.response.OrdersResponseDto;
import com.example.userstories.service.OrdersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrdersController {
    private final OrdersService ordersService;

    @PostMapping("/place")
    public ResponseEntity<OrdersResponseDto> placeOrder(@RequestBody OrdersRequest ordersRequest, @RequestParam String symbol) {
        OrdersResponseDto responseDto = ordersService.placeOrder(ordersRequest, symbol);
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping
    public ResponseEntity<List<OrdersResponseDto>> getAllOrders() {
        List<OrdersResponseDto> responseDtoList = ordersService.getAllOrders();
        return ResponseEntity.ok(responseDtoList);
    }

    @PostMapping
    public ResponseEntity<OrdersResponseDto> createOrder(@RequestBody OrdersRequest ordersRequest) {
        OrdersResponseDto responseDto = ordersService.createOrder(ordersRequest);
        return ResponseEntity.ok(responseDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrdersResponseDto> updateOrder(@PathVariable Integer id, @RequestBody OrdersRequest ordersRequest) {
        OrdersResponseDto responseDto = ordersService.updateOrder(id, ordersRequest);
        return ResponseEntity.ok(responseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Integer id) {
        ordersService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }
}
