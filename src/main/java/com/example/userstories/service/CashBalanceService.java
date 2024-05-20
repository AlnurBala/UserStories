package com.example.userstories.service;

import com.example.userstories.dto.response.CashBalanceResponseDto;

public interface CashBalanceService {

    CashBalanceResponseDto getBalance(Integer userId);

    void deposit(Integer userId, Double amount);

    void withdraw(Integer userId, Double amount);


}
