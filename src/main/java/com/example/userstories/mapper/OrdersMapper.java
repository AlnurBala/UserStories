package com.example.userstories.mapper;

import com.example.userstories.dto.request.OrdersRequest;
import com.example.userstories.dto.response.OrdersResponseDto;
import com.example.userstories.entity.Orders;
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
public interface OrdersMapper {

    Orders fromDto(OrdersRequest ordersRequest);

    OrdersResponseDto toDto(Orders orders);

    List<OrdersResponseDto> toDtos(List<Orders> ordersList);

    Orders mapUpdateRequestToEntity(@MappingTarget Orders orders, OrdersRequest ordersRequestDto);

}
