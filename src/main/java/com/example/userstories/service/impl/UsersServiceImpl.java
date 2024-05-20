package com.example.userstories.service.impl;

import com.example.userstories.dto.request.UsersRequest;
import com.example.userstories.dto.response.UsersResponseDto;
import com.example.userstories.entity.Users;
import com.example.userstories.mapper.UsersMapper;
import com.example.userstories.repository.UsersRepository;
import com.example.userstories.service.UsersService;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UsersServiceImpl implements UsersService {

    private final UsersRepository usersRepository;
    private final UsersMapper usersMapper;

    @Override
    public List<UsersResponseDto> getAllUsers() {
        log.debug("Fetching all users");
        var userEntity = usersRepository.findAll();
        return usersMapper.toDTOs(userEntity);
    }

    @Override
    public UsersResponseDto createUser(UsersRequest usersRequest) {
        log.info("Creating user: {}", usersRequest);
        var userEntity = usersMapper.fromDTO(usersRequest);
        userEntity = usersRepository.save(userEntity);
        log.info("User created: {}", userEntity);
        return usersMapper.toDTO(userEntity);
    }

    @Override
    public UsersResponseDto updateUser(Integer id, UsersRequest usersRequest) {
        log.debug("Updating user with ID {}: {}", id, usersRequest);
        Optional<Users> userOptional = usersRepository.findById(id);
        if (!userOptional.isPresent()) {
            log.error("User with ID {} not found for update", id);
            return null;
        }
        Users newUser = userOptional.get();
        usersMapper.mapUpdateRequestToEntity(newUser, usersRequest);
        newUser = usersRepository.save(newUser);
        log.info("User updated: {}", newUser);
        return usersMapper.toDTO(newUser);
    }

    @Override
    public void deleteUser(Integer id) {
        log.warn("Deleting user with ID: {}", id);
        usersRepository.deleteById(id);
        log.info("User deleted with ID: {}", id);
    }

}
