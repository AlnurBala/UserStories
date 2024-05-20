package com.example.userstories.dto.request;

import java.sql.Timestamp;


public record OrdersRequest(
        Integer orderId,
        Integer userId,
        Integer stockId,
        String orderType,
        Double targetPrice,
        Timestamp filledTimestamp
) {
}

