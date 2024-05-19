package com.example.userstories.mapper;

import com.example.userstories.dto.request.OrdersRequest;
import com.example.userstories.dto.response.OrdersResponseDto;
import com.example.userstories.entity.Orders;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        builder = @Builder(disableBuilder = true))
public interface OrdersMapper {
    Orders fromDto(OrdersRequest ordersRequest);

    OrdersResponseDto toDto(Orders orders);

    List<OrdersResponseDto> toDtos(List<Orders> ordersList);

    Orders mapUpdateRequestToEntity(@MappingTarget Orders orders, OrdersRequest ordersRequestDto);
}
