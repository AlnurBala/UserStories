package com.example.userstories.controller;

import com.example.userstories.dto.request.UsersRequest;
import com.example.userstories.dto.UsersResponse;
import com.example.userstories.dto.response.UsersResponseDto;
import com.example.userstories.service.UsersService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UsersController {
    private final UsersService usersService;

    @GetMapping
    public List<UsersResponseDto> getAllUsers() {

        return usersService.getAllUsers();
    }

    @PostMapping
    public UsersResponseDto createUser(@RequestBody @Valid UsersRequest userRequest) {
        return usersService.createUser(userRequest);
    }

    @PutMapping("/{id}")
    public UsersResponseDto updateUser(@PathVariable Integer id, @RequestBody UsersRequest usersRequest) {
        return usersService.updateUser(id, usersRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteUserById(@PathVariable Integer id) {
        usersService.deleteUser(id);
    }


}
