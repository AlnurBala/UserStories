package com.example.userstories.dto;

public record UsersResponse(
        String username,
        String emailAddress,
        String password
) {
}

