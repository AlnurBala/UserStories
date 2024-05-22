package com.example.userstories.service.impl;


import com.example.userstories.dto.request.AuthenticationRequest;
import com.example.userstories.dto.request.RegisterRequest;
import com.example.userstories.dto.response.AuthenticationResponseDto;
import com.example.userstories.entity.Users;
import com.example.userstories.enumeration.Role;
import com.example.userstories.exception.NotDataFound;
import com.example.userstories.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl {

    private static final Logger logger
            = LoggerFactory.getLogger(AuthenticationServiceImpl.class);

    private final UsersRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtServiceImpl jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponseDto register(RegisterRequest request) {
        logger.info("Registering user: {}", request.getEmailAddress());
        var user = Users.builder()
                .username(request.getUsername())
                .emailAddress(request.getEmailAddress())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRoles().isEmpty() ? Role.USER : request.getRoles().iterator().next())
                .build();
        repository.save(user);
        var jwtToken = jwtService.generateToken(user);
        logger.info("User registered successfully: {}", request.getEmailAddress());
        return AuthenticationResponseDto.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponseDto authenticate(AuthenticationRequest request) {
        logger.info("Authenticating user: {}", request.getEmailAddress());
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmailAddress(),
                        request.getPassword()
                )
        );
        var user = repository.findByEmailAddress(request.getEmailAddress()).orElseThrow(()-> new NotDataFound("Mail " +
                "is not found"));
        var jwtToken = jwtService.generateToken(user);
        logger.info("User authenticated successfully: {}", request.getEmailAddress());
        return AuthenticationResponseDto.builder()
                .token(jwtToken)
                .build();
    }
}
