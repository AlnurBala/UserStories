package com.example.userstories.service.impl;

import com.example.userstories.dto.response.CashBalanceResponseDto;
import com.example.userstories.entity.CashBalance;
import com.example.userstories.exception.CashBalanceNotFoundException;
import com.example.userstories.exception.InsufficientBalanceException;
import com.example.userstories.mapper.CashBalanceMapper;
import com.example.userstories.repository.CashBalanceRepository;
import com.example.userstories.service.CashBalanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CashBalanceServiceImpl implements CashBalanceService {

    private final CashBalanceRepository cashBalanceRepository;
    private final CashBalanceMapper cashBalanceMapper;

    @Override
    public CashBalanceResponseDto getBalance(Integer userId) {
        CashBalance cashBalance = cashBalanceRepository.findByUserId(userId);
        if (cashBalance == null) {
            throw new CashBalanceNotFoundException("Cash balance not found for user");
        }
        return cashBalanceMapper.cashBalanceToCashBalanceResponseDto(cashBalance);
    }

    @Override
    @Transactional
    public void deposit(Integer userId, Double amount) {
        CashBalance cashBalance = cashBalanceRepository.findByUserId(userId);
        if (cashBalance == null) {
            cashBalance = new CashBalance();
            cashBalance.setUserId(userId);
            cashBalance.setBalance(amount);
        } else {
            cashBalance.setBalance(cashBalance.getBalance() + amount);
        }
        cashBalanceRepository.save(cashBalance);
    }

    @Override
    @Transactional
    public void withdraw(Integer userId, Double amount) {
     CashBalance cashBalance = cashBalanceRepository.findFirstByUserId(userId)
             .orElseThrow(()->new CashBalanceNotFoundException("Cash balance not found for user"));
        if (cashBalance.getBalance() < amount) {
            throw new InsufficientBalanceException("Insufficient balance");
        }
        cashBalance.setBalance(cashBalance.getBalance() - amount);
        cashBalanceRepository.save(cashBalance);
    }

}

