package com.example.userstories.dto;

public record OrdersResponse(
        String orderType,
        Double targetPrice,
        String filledTimestamp
) {
}

