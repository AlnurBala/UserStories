package com.example.userstories.mapper;

import com.example.userstories.dto.request.StocksRequest;
import com.example.userstories.dto.response.StocksResponseDto;
import com.example.userstories.entity.Stocks;
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
public interface StocksMapper {

    Stocks fromDTO(StocksRequest stocksRequest);

    StocksResponseDto toDTO(Stocks stocks);

    List<StocksResponseDto> toDTOs(List<Stocks> stocks);

    Stocks mapUpdateRequestToEntity(@MappingTarget Stocks stocks, StocksRequest stocksRequest);

}
