package com.example.userstories.mapper;

import com.example.userstories.dto.CashBalanceResponse;
import com.example.userstories.dto.response.CashBalanceResponseDto;
import com.example.userstories.dto.response.UsersResponseDto;
import com.example.userstories.entity.CashBalance;
import com.example.userstories.entity.Users;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CashBalanceMapper {
    CashBalanceResponseDto toDto(CashBalance cashBalance);
//    List<CashBalanceResponseDto> toDtos(List<CashBalance> cashBalances);

    @Mapping(source = "user", target = "users")
    CashBalanceResponseDto cashBalanceToCashBalanceResponseDto(CashBalance cashBalance);

    @Mapping(source = "cashBalance", target = "cashBalance")
    UsersResponseDto usersToUsersResponseDto(Users users);
}
