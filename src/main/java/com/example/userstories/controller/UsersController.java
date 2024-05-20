package com.example.userstories.controller;

import com.example.userstories.dto.request.UsersRequest;
import com.example.userstories.dto.response.UsersResponseDto;
import com.example.userstories.service.UsersService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/users")
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
