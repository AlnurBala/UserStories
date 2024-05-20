package com.example.userstories.controller;

import com.example.userstories.dto.request.OrdersRequest;
import com.example.userstories.dto.response.OrdersResponseDto;
import com.example.userstories.service.OrdersService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
