package com.example.userstories.config;


import static com.example.userstories.enumeration.Permission.ADMIN_CREATE;
import static com.example.userstories.enumeration.Permission.ADMIN_DELETE;
import static com.example.userstories.enumeration.Permission.ADMIN_READ;
import static com.example.userstories.enumeration.Permission.ADMIN_UPDATE;
import static com.example.userstories.enumeration.Permission.USER_READ;
import static com.example.userstories.enumeration.Role.ADMIN;
import static com.example.userstories.enumeration.Role.USER;
import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.HttpMethod.PUT;
import static org.springframework.security.config.Customizer.withDefaults;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorizeRequests -> authorizeRequests
                        .requestMatchers("/api/v1/auth/register",
                                "/api/v1/auth/authenticate",
                                "/api/v1/support",
                                "/v2/api-docs",
                                "/v3/api-docs",
                                "/v3/api-docs/**",
                                "/swagger-resources",
                                "/swagger-resources/**",
                                "/configuration/ui",
                                "/configuration/security",
                                "/swagger-ui/**",
                                "/webjars/**",
                                "/swagger-ui.html"
                        ).permitAll()
                        .requestMatchers("/api/v1/orders/place").hasAnyRole(ADMIN.name(), USER.name())
                        .requestMatchers("/api/v1/cash-balance/**").hasAnyRole(ADMIN.name(), USER.name())

                        // Ensure only ADMIN can access other order-related endpoints
                        .requestMatchers("/api/v1/orders/**").hasRole(ADMIN.name())

                        // Other specific endpoints for users and admins
                        .requestMatchers(GET, "/api/v1/stocks").hasAnyAuthority(ADMIN_READ.name(), USER_READ.name())
                        .requestMatchers(POST, "/api/v1/stocks").hasAnyAuthority(ADMIN_CREATE.name())
                        .requestMatchers(PUT, "/api/v1/stocks/**").hasAnyAuthority(ADMIN_UPDATE.name())
                        .requestMatchers(DELETE, "/api/v1/stocks/**").hasAnyAuthority(ADMIN_DELETE.name())

                        .requestMatchers("/api/v1/users/**").hasRole(ADMIN.name())
                        .requestMatchers(GET, "/api/v1/users/**").hasAuthority(ADMIN_READ.name())
                        .requestMatchers(POST, "/api/v1/users/**").hasAuthority(ADMIN_CREATE.name())
                        .requestMatchers(PUT, "/api/v1/users/**").hasAuthority(ADMIN_UPDATE.name())
                        .requestMatchers(DELETE, "/api/v1/users/**").hasAuthority(ADMIN_DELETE.name())

                        .anyRequest().authenticated()
                )
                .sessionManagement(sessionManagement -> sessionManagement
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .exceptionHandling(exceptionHandling -> exceptionHandling
                        .authenticationEntryPoint((request, response, authException) ->
                                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED)
                        )
                        .accessDeniedHandler((request, response, accessDeniedException) ->
                                response.setStatus(HttpServletResponse.SC_FORBIDDEN)
                        )
                )
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .logout(withDefaults());
        return http.build();
    }
}
