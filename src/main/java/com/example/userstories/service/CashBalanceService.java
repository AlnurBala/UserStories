package com.example.userstories.service;

import com.example.userstories.dto.response.CashBalanceResponseDto;

import java.util.List;

public interface CashBalanceService {
    CashBalanceResponseDto getBalance(Integer userId);
    void deposit(Integer userId, Double amount);
    void withdraw(Integer userId, Double amount);


}
