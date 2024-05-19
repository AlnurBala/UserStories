package com.example.userstories.mapper;

import com.example.userstories.dto.request.UsersRequest;
import com.example.userstories.dto.UsersResponse;
import com.example.userstories.dto.response.UsersResponseDto;
import com.example.userstories.entity.Users;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        builder = @Builder(disableBuilder = true))
public interface UsersMapper {
    Users fromDTO(UsersRequest userRequest);

    UsersResponseDto toDTO(Users users);

    List<UsersResponseDto> toDTOs(List<Users> users);

    Users mapUpdateRequestToEntity(@MappingTarget Users users, UsersRequest userRequest);

}
