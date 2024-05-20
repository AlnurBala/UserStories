package com.example.userstories.dto.response;

import com.example.userstories.dto.OrdersResponse;
import java.util.List;


public record StocksResponseDto(
        Integer stockId,
        String symbol,
        String companyName,
        Integer price,
        List<OrdersResponse> orders
) {
}

