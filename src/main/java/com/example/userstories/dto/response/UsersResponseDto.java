package com.example.userstories.dto.response;

import com.example.userstories.dto.CashBalanceResponse;
import com.example.userstories.dto.OrdersResponse;
import com.example.userstories.dto.UsersResponse;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsersResponseDto {
    private Integer userId;
    private String username;
    private String emailAddress;
    private String password;
    private CashBalanceResponse cashBalance;
    private List<OrdersResponse> orders;
}
