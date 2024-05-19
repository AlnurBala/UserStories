package com.example.userstories.dto.response;

import com.example.userstories.dto.OrdersResponse;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StocksResponseDto {
    private Integer stockId;
    private String symbol;
    private String companyName;
    private Integer price;
    private List<OrdersResponse> orders;

}
