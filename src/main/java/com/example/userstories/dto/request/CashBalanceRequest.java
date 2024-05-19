package com.example.userstories.dto.request;

import jakarta.persistence.Column;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CashBalanceRequest {
    private Integer userId;
    private Double balance;
}
