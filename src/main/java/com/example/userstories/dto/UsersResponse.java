package com.example.userstories.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsersResponse {
    private String username;
    private String emailAddress;
    private String password;
}
