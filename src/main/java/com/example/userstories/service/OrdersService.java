package com.example.userstories.service;

import com.example.userstories.dto.request.OrdersRequest;
import com.example.userstories.dto.response.OrdersResponseDto;

import java.util.List;

public interface OrdersService {
    List<OrdersResponseDto> getAllOrders();
    OrdersResponseDto placeOrder(OrdersRequest ordersRequest, String symbol);
    OrdersResponseDto createOrder(OrdersRequest ordersRequest);
    OrdersResponseDto updateOrder(Integer id, OrdersRequest ordersRequest);
    void deleteOrder(Integer id);
}
