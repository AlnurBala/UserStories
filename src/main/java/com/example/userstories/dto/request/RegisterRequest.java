package com.example.userstories.dto.request;


import com.example.userstories.enumeration.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.Collections;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    Integer userId;
    @NotBlank(message = "Username is required") String username;
    @NotBlank(message = "Email address is required") @Email(message = "Invalid email address format") String emailAddress;
    @NotBlank(message = "Password is required") @Size(min = 6, message = "Password must be at least 6 characters " +
            "long") String password;

    private Set<Role> roles = Collections.singleton(Role.USER);

}
