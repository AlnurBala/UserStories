package com.example.userstories.dto.response;

import com.example.userstories.dto.CashBalanceResponse;
import com.example.userstories.dto.OrdersResponse;
import java.util.List;


public record UsersResponseDto(
        Integer userId,
        String username,
        String emailAddress,
        String password,
        CashBalanceResponse cashBalance,
        List<OrdersResponse> orders
) {
}

