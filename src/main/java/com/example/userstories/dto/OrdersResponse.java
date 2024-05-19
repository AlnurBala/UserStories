package com.example.userstories.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrdersResponse {
    private String orderType;
    private Double targetPrice;
    private String filledTimestamp;
}
