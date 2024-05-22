package com.example.userstories.controller;

import com.example.userstories.dto.request.CashBalanceRequest;
import com.example.userstories.dto.response.CashBalanceResponseDto;
import com.example.userstories.service.CashBalanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/cash-balance")
@RequiredArgsConstructor
public class CashBalanceController {

    private final CashBalanceService cashBalanceService;

    @GetMapping("/{id}")
    public ResponseEntity<CashBalanceResponseDto> getBalance(@PathVariable Integer id) {
        CashBalanceResponseDto response = cashBalanceService.getBalance(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/deposit")
    public ResponseEntity<Void> deposit(@RequestBody CashBalanceRequest request) {
        cashBalanceService.deposit(request.userId(), request.balance());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/withdraw")
    public ResponseEntity<Void> withdraw(@RequestBody CashBalanceRequest request) {
        cashBalanceService.withdraw(request.userId(), request.balance());
        return ResponseEntity.ok().build();
    }

}
