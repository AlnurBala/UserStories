package com.example.userstories.dto.request;

public record StocksRequest(
        Integer stockId,
        String symbol,
        String companyName,
        Integer price
) {
}

