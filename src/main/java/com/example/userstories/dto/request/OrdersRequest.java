package com.example.userstories.dto.request;

import jakarta.persistence.Column;
import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrdersRequest {
    private Integer orderId;
    private Integer userId;
    private Integer stockId;

    private String orderType;
    private Double targetPrice;
    private Timestamp filledTimestamp;

}
