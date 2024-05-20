package com.example.userstories.dto.response;

import com.example.userstories.dto.UsersResponse;


public record CashBalanceResponseDto(
        Integer userId,
        Double balance,
        UsersResponse users
) {
}

