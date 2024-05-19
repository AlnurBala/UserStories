package com.example.userstories.service;

import com.example.userstories.dto.request.UsersRequest;
import com.example.userstories.dto.UsersResponse;
import com.example.userstories.dto.response.UsersResponseDto;

import java.util.List;

public interface UsersService {
    List<UsersResponseDto> getAllUsers();

    UsersResponseDto createUser(UsersRequest userRequest);

    UsersResponseDto updateUser(Integer id, UsersRequest userRequest);

    void deleteUser(Integer id);
}
