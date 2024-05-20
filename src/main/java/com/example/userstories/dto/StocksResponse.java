package com.example.userstories.dto;

public record StocksResponse(
        String symbol,
        String companyName,
        Integer price
) {
}

