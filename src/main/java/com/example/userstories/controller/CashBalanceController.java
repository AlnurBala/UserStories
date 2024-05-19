package com.example.userstories.controller;

import com.example.userstories.dto.request.CashBalanceRequest;
import com.example.userstories.dto.CashBalanceResponse;
import com.example.userstories.dto.response.CashBalanceResponseDto;
import com.example.userstories.service.CashBalanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/cash-balance")
@RequiredArgsConstructor
public class CashBalanceController {
    private final CashBalanceService cashBalanceService;

    @GetMapping("/{userId}")
    public ResponseEntity<CashBalanceResponseDto> getBalance(@PathVariable Integer userId) {
        CashBalanceResponseDto response = cashBalanceService.getBalance(userId);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/deposit")
    public ResponseEntity<Void> deposit(@RequestBody CashBalanceRequest request) {
        cashBalanceService.deposit(request.getUserId(), request.getBalance());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/withdraw")
    public ResponseEntity<Void> withdraw(@RequestBody CashBalanceRequest request) {
        cashBalanceService.withdraw(request.getUserId(), request.getBalance());
        return ResponseEntity.ok().build();
    }
}
