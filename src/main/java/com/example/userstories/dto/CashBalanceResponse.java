package com.example.userstories.dto;

import lombok.*;
import org.springframework.data.domain.PageRequest;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CashBalanceResponse {
    private Double balance;
}
