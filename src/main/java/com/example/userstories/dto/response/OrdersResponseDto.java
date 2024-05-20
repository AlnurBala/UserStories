package com.example.userstories.dto.response;

import com.example.userstories.dto.StocksResponse;
import com.example.userstories.dto.UsersResponse;


public record OrdersResponseDto(
        Integer orderId,
        String orderType,
        Double targetPrice,
        String filledTimestamp,
        StocksResponse stocks,
        UsersResponse user
) {
}

