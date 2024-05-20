package com.example.userstories.service;

import com.example.userstories.dto.request.StocksRequest;
import com.example.userstories.dto.response.StocksResponseDto;
import java.util.List;

public interface StocksService {

    List<StocksResponseDto> getAllStocks();

    StocksResponseDto createStock(StocksRequest stocksRequest);

    StocksResponseDto updateStock(Integer id, StocksRequest stocksRequest);

    void deleteStock(Integer id);

}
