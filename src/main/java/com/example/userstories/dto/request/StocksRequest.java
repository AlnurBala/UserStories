package com.example.userstories.dto.request;

import jakarta.persistence.Column;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StocksRequest {
    private Integer stockId;
    private String symbol;
    private String companyName;
    private Integer price;
}
