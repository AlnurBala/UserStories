package com.example.userstories.mapper;

import com.example.userstories.dto.request.StocksRequest;
import com.example.userstories.dto.request.UsersRequest;
import com.example.userstories.dto.response.StocksResponseDto;
import com.example.userstories.dto.response.UsersResponseDto;
import com.example.userstories.entity.Stocks;
import com.example.userstories.entity.Users;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        builder = @Builder(disableBuilder = true))
public interface StocksMapper {
    Stocks fromDTO(StocksRequest stocksRequest);

    StocksResponseDto toDTO(Stocks stocks);

    List<StocksResponseDto> toDTOs(List<Stocks> stocks);

    Stocks mapUpdateRequestToEntity(@MappingTarget Stocks stocks, StocksRequest stocksRequest);
}
