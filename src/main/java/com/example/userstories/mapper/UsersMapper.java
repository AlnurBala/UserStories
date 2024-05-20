package com.example.userstories.mapper;

import com.example.userstories.dto.request.UsersRequest;
import com.example.userstories.dto.response.UsersResponseDto;
import com.example.userstories.entity.Users;
import java.util.List;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

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
