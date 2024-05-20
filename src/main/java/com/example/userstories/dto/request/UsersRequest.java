package com.example.userstories.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


public record UsersRequest(
        Integer userId,
        @NotBlank(message = "Username is required") String username,
        @NotBlank(message = "Email address is required") @Email(message = "Invalid email address format") String emailAddress,
        @NotBlank(message = "Password is required") @Size(min = 6, message = "Password must be at least 6 characters long") String password
) {
}
