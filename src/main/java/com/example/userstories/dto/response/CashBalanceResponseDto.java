package com.example.userstories.dto.response;

import com.example.userstories.dto.UsersResponse;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CashBalanceResponseDto {
    private Integer userId;
    private Double balance;
    private UsersResponse users;
}
