package com.example.userstories.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StocksResponse {
    private String symbol;
    private String companyName;
    private Integer price;
}
