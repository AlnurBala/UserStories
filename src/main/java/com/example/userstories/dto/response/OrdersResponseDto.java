package com.example.userstories.dto.response;

import com.example.userstories.dto.StocksResponse;
import com.example.userstories.dto.UsersResponse;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrdersResponseDto {
    private Integer orderId;
    private String orderType;
    private Double targetPrice;
    private String filledTimestamp;
    private StocksResponse stocks;
    private UsersResponse user;
}
